package es.deusto.spq.server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Producto;

@Path("/clientes")
public class ClientesServer {

    @GET
    @Path("/{email}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente comprobarDatos(@PathParam("email") String email, @PathParam("password") String password) {    
        List<Cliente> clientes = DBManager.getInstance().getClientes();				
		for (Cliente cliente : clientes) {
	 		if (cliente.getEmail().equals(email)) {
	 			if(cliente.getPassword().equals(password)) {
	 				return cliente;
                 }
	 		}
		}
        return null;
	}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCliente(Cliente cliente) {
        DBManager.getInstance().store(cliente);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCliente(Cliente cliente) {
        Cliente cliente_per = DBManager.getInstance().getCliente(cliente.getDNI());
        for (Producto p : cliente.getProductosDeseados()){
            Producto producto_per = DBManager.getInstance().getProducto(p.getNombre());
            cliente_per.getProductosDeseados().add(producto_per);
        }
        DBManager.getInstance().updateCliente(cliente_per);
    }
    
//    @POST
//    @Path("/{deseado}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void deleteCliente(@PathParam ("deseado") Cliente cliente) {
//    	Cliente cliente_per = DBManager.getInstance().getCliente(cliente.getDNI());
//        for (Producto p : cliente.getProductosDeseados()){
//            Producto producto_per = DBManager.getInstance().getProducto(p.getNombre());
//            cliente_per.getProductosDeseados().remove(producto_per);
//        }
//        DBManager.getInstance().updateCliente(cliente_per);
//    }
}
