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

import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;

@Path("/pedidos")
public class PedidosServer {

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
    
    @DELETE
    @Path("/{code}")
    public Response deletePedido(@PathParam("code") int code) {
        if (code == 10) {
            System.out.println("Deleting pedido...");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
