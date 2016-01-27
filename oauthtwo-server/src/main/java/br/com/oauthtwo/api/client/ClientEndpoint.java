package br.com.oauthtwo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.oauthtwo.model.Client;
import br.com.oauthtwo.store.Keeper;


@Path("/client")
public class ClientResource {

	Keeper	keeper	= Keeper.getInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Client create(Client client) {
		return keeper.storeClient(client);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client sayHello(@PathParam("id") String id) {
		return keeper.retrieveClient(id);
	}

	@GET
	public String sayHello() {
		return "Hello world!!\n";
	}

}
