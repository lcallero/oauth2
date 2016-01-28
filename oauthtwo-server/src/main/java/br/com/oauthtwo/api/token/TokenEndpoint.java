package br.com.oauthtwo.api.token;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/token")
public class TokenEndpoint {

	TokenStore	tokenStore	= TokenStore.getInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Token create(Token t) {

		return tokenStore.storeToken(t);
	}

	@GET
	@Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Token sayHello(@PathParam("token") String token) {
		return tokenStore.retrieveToken(token);
	}

	@GET
	public String sayHello() {
		return "Hello world, I'm taking care of Tokens!!!\n";
	}

}
