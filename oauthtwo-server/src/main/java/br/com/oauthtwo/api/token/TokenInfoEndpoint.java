package br.com.oauthtwo.api.token;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.util.Base64;


@Path("/tokeninfo")
public class TokenInfoEndpoint {
	TokenStore	tokenStore	= TokenStore.getInstance();

	@GET
	@Path("/{token}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Token sayHello(@PathParam("token") String token, @Context HttpServletRequest request) {

		String encodedString = request.getHeader("Authorization").split("Basic")[0];
		String credentials = Base64.decodeAsString(encodedString.getBytes());

		System.out.println(credentials);
		
		return tokenStore.retrieveToken(token);
	}
}
