package br.com.oauthtwo.api.token;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.oauthtwo.api.client.ClientStore;


@Path("/token")
public class TokenEndpoint {

	private static ObjectMapper	jackson		= new ObjectMapper();

	TokenStore					tokenStore	= TokenStore.getInstance();
	ClientStore					clientStore	= ClientStore.getInstance();

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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Token create(Token t) {
		return tokenStore.storeToken(t);
	}

	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHello(@Context HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		System.out.println("Authorization header:" + authorization);

		String encodedString = authorization.split("Basic")[1].trim();

		System.out.println("encodedString" + encodedString);

		String credentials = Base64.decodeAsString(encodedString.getBytes());
		System.out.println("Client: " + credentials);

		return "Hello world, soon you'll have new Tokens!!!\n";
	}

}
