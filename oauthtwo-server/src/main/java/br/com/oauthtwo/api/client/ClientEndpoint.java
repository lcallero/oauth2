package br.com.oauthtwo.api.client;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/client")
public class ClientEndpoint {

	ClientStore	clientStore	= ClientStore.getInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Client create(Client client, @Context final HttpServletResponse response) {
		clientStore.storeClient(client);
		response.setStatus(Response.Status.CREATED.getStatusCode());
		try {
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clientStore.storeClient(client);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client get(@PathParam("id") String id) {
		Client c = clientStore.retrieveClient(id);
		if (c == null || !c.getClientId().equalsIgnoreCase(id)) {
			throw new NotFoundException("Client '" + id + "' nao localizado.");
		}
		return c;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam("id") String id) {
		Client c = clientStore.removeClient(id);
		if (c != null && c.getClientId().equalsIgnoreCase(id)) {
			return Response.status(Response.Status.OK).entity("Client removed: " + c).build();
		}
		return Response.status(Status.NOT_FOUND).entity("Client '" + id + "' not found.").build();
	}

	@GET
	public String sayHello() {
		return "Hello world, I'm taking care of Clients!!!\n";
	}

}
