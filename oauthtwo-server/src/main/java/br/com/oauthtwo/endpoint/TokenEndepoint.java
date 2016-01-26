package br.com.oauthtwo.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.oauthtwo.model.AccessToken;
import br.com.oauthtwo.model.Client;
import br.com.oauthtwo.store.Keeper;


@Path("/token")
public class TokenEndepoint {

	Keeper	keeper	= Keeper.getInstance();

//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public AccessToken request(AccessToken token) {
//		return keeper.storeToken(client);
//	}
//
//	@GET
//	@Path("/{token}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public AccessToken sayHello(@PathParam("accesToken") String token) {
//		return keeper.retrieveAccessToken(token);
//	}

	@GET
	public String sayHello() {
		return "Hello world!!\n";
	}

}
