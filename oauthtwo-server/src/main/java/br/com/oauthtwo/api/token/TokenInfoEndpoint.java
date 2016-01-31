package br.com.oauthtwo.api.token;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.util.Base64;

@Path("/tokeninfo")
public class TokenInfoEndpoint {
    TokenStore tokenStore = TokenStore.getInstance();

    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Token sayHello(@QueryParam("access_token") String access_token, @Context HttpServletRequest request) {

	String authorization = request.getHeader("Authorization");
	System.out.println("Authorization header:" + authorization);

	String encodedString = authorization.split("Basic")[1].trim();

	System.out.println("encodedString" + encodedString);

	String credentials = Base64.decodeAsString(encodedString.getBytes());
	System.out.println("Credentials: '" + credentials + "' access_token:'" + access_token+"'");

	Token token = tokenStore.retrieveToken(access_token);
	if (null == token)
	    throw new NotFoundException();
	return token;
    }
}
