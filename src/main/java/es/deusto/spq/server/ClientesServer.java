package es.deusto.spq.server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Producto;

/**
 * Clase server para los Clientes para la comunicación 
 * con la BD
 */
@Path("/clientes")
public class ClientesServer {

	/**
	 * Se comprueban los datos de un cliente
	 * @param email el email del cliente
	 * @param password el password del cliente
	 * @return si los datos se comprueban bien se devuelve el cliente
	 */
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

    /**
     * Guarda un cliente en la BD
     * @param cliente el cliente a guardar
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCliente(Cliente cliente) {
        DBManager.getInstance().store(cliente);
    }
    
    /**
     * Actualiza un cliente en la BD
     * @param cliente el cliente a actualizar
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCliente(Cliente cliente) {
        Cliente cliente_per = DBManager.getInstance().getCliente(cliente.getDNI());
        cliente_per.setProductosDeseados(new ArrayList<>());
        for (int i = 0; i < cliente.getProductosDeseados().size(); i++){
            Producto producto_per = DBManager.getInstance().getProducto(cliente.getProductosDeseados().get(i).getNombre());
            cliente_per.getProductosDeseados().add(producto_per);
        }
        DBManager.getInstance().store(cliente_per);
    }
}
