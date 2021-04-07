package es.deusto.spq.server;

import java.util.ArrayList;
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

    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // public boolean comprobarDatos(@PathParam("rawdata") ArrayList<String> datos) {	
    //     List<Cliente> clientes = DBManager.getInstance().getClientes();					
	// 	boolean correcto = false;
	// 	for (Cliente cliente : clientes) {
	// 		if (cliente.getEmail().equals(datos.get(0))) {
	// 			if(cliente.getPassword().equals(datos.get(1))) {
	// 				correcto = true;
    //             }
	// 		}
	// 	}
	// 	return correcto;
	// }
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
