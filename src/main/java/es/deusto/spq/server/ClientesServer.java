package es.deusto.spq.server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.DBManager;
import models.Cliente;

@Path("/clientes")
public class ClientesServer {

    @GET
    @Path("/{email}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String comprobarDatos(@PathParam("email") String email, @PathParam("password") String password) {	
        List<Cliente> clientes = DBManager.getInstance().getClientes();				
    	String correcto = "false";
		for (Cliente cliente : clientes) {
	 		if (cliente.getEmail().equals(email)) {
	 			if(cliente.getPassword().equals(password)) {
	 				correcto = "true";
                 }
	 		}
		}
        return correcto;
	}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes() {
		List<Cliente> clientes = DBManager.getInstance().getClientes();
		return clientes;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCliente(Cliente cliente) {
        DBManager.getInstance().store(cliente);
    }
    @DELETE
    @Path("/{code}")
    public Response deleteUser(@PathParam("code") int code) {
        if (code == 10) {
            System.out.println("Deleting cliente...");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
