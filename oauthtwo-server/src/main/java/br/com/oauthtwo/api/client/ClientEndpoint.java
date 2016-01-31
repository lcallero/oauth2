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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/client")
public class ClientEndpoint {

    private static ObjectMapper jackson = new ObjectMapper();
    ClientStore clientStore = ClientStore.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Client create(Client client, @Context HttpServletResponse response) {
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
    public Client get(@PathParam("id") Long id) {
	Client c = clientStore.retrieveClient(id);
	if (c == null) {
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
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello() {
	try {
	    clientStore.listAllClients();
	    return jackson.writeValueAsString(clientStore.listAllClients());
	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}
	return "shit!\n";
    }

}
