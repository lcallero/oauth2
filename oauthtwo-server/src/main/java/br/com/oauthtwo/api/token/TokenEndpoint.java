package br.com.oauthtwo.api.token;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.oauthtwo.api.client.Client;
import br.com.oauthtwo.api.client.ClientStore;

@Path("/token")
public class TokenEndpoint {

    private static ObjectMapper jackson = new ObjectMapper();

    private static int seed = 0;

    private static final long UMA_HORA = (1000 * 60) * 60;

    TokenStore tokenStore = TokenStore.getInstance();
    ClientStore clientStore = ClientStore.getInstance();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listAll() {

	try {
	    tokenStore.listAll();
	    return jackson.writeValueAsString(tokenStore.listAll());
	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}
	return "shit!\n";

    }

    @GET
    @Path("/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Token retrieve(@PathParam("token") String token) {
	return tokenStore.retrieveToken(token);
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Token create(Token t) {
	return tokenStore.storeToken(t);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String getToken(@Context HttpServletRequest request, @Context HttpServletResponse response, @FormParam("scope") String scope) {

	String authorizationHeader = request.getHeader("Authorization");

	if (null == authorizationHeader) {
	    throw new BadRequestException("Missing Authorization header");
	} else {
	    String encodedString = authorizationHeader.split("Basic")[1].trim();
	    String credentials = Base64.decodeAsString(encodedString.getBytes());
	    System.out.println("Credentials: " + credentials);
	    try {
		String clientId = credentials.split(":")[0];
		String[] scopes = new String[] { scope };
		Token accessToken = buildNewToken(searchClientByClientId(clientId).getClientId(), scopes);
		if (null != accessToken) {
		    tokenStore.storeToken(accessToken);
		}
		return accessToken.toString();
	    } catch (ArrayIndexOutOfBoundsException e) {
		e.printStackTrace();
		throw new BadRequestException("Invalid credentials");
	    } catch (NotFoundException e) {
		e.printStackTrace();
		throw e;
	    }
	}
    }

    private Token buildNewToken(String clientId, String[] scopes) {
	Token token = new Token();
	token.setAccessToken("access_token_" + ++seed);
	token.setType("Bearer");
	token.setExpires_in(System.currentTimeMillis() + UMA_HORA);
	token.setScopes(scopes);
	token.setAudience(clientStore.retriveClientByClientId(clientId).getId() + "");
	Principal principal = new Principal(clientId, clientStore.retriveClientByClientId(clientId).getName());
	token.setPrincipal(principal);
	return token;
    }

    private Client searchClientByClientId(String clientId) throws NotFoundException {
	Client client = clientStore.retriveClientByClientId(clientId);
	if (null != client) {
	    return client;
	} else {
	    throw new NotFoundException("Client not found:" + clientId);
	}
    }
}
