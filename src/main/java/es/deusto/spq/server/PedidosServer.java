package es.deusto.spq.server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;

/**
 * Clase server para los Pedidos para la comunicación 
 * con la BD
 *
 */

@Path("/pedidos")
public class PedidosServer {
	
	/**
	 * Devuelve los pedidos de un cliente concreto
	 * @param Dni el Dni de el cliente
	 * @return lista de pedidos del cliente
	 */
    @GET
    @Path("{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> getPedidos(@PathParam("dni") String Dni) {
    	List<Pedido> allPedidos = DBManager.getInstance().getPedidos();
    	ArrayList<Pedido> pedidosCliente = new ArrayList<>();
    	Cliente client = DBManager.getInstance().getCliente(Dni);
    	String dni1 = client.getDNI();
    	for (Pedido pedido : allPedidos) {
			Cliente clientePedido = DBManager.getInstance().getCliente(pedido.getCliente().getDNI());
			String dni2 = clientePedido.getDNI();
			if(dni1.equals(dni2)) {
				pedidosCliente.add(pedido);
	    		
	    	}
			
		}
    	return pedidosCliente;
    	
		
    }
    
    /**
     * Guarda un pedido en la BD
     * @param pedido el pedido a guardar
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPedido(Pedido pedido) {
        Cliente cliente = DBManager.getInstance().getCliente(pedido.getCliente().getDNI());
        List<Producto> productoPedido = new ArrayList<>();
        for (Producto p: pedido.getProducto()){
            Producto producto = DBManager.getInstance().getProducto(p.getNombre());
            productoPedido.add(producto);
        }
        pedido.setCliente(cliente);
        pedido.setProducto((ArrayList<Producto>) productoPedido);
        DBManager.getInstance().store(pedido);
    }
    
    /**
     * Actualiza el estado de un pedido
     * @param pedido el pedido a actualizar
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void devolverPedido(Pedido pedido) {
       
        Pedido p = DBManager.getInstance().getPedido(pedido.getFecha());
        p.setEstado("Devuelto");
        DBManager.getInstance().store(p);
    }
}
