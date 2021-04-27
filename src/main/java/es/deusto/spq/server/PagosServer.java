package es.deusto.spq.server;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Pago;

@Path("/pagos")
public class PagosServer {
    
    @GET
    @Path("/paypal/{DNI}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> getCredencialesPaypal(@PathParam("DNI") String dni) {
        Cliente cliente = DBManager.getInstance().getCliente(dni);
        HashMap<String, String> credencialesPaypal = DBManager.getInstance().getPaypal(cliente);
        System.out.println(DBManager.getInstance().getPaypal(cliente));
		return credencialesPaypal;
	}
    @GET
    @Path("/visa/{DNI}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> getCredencialesVisa(@PathParam("DNI") String dni) {
        Cliente cliente = DBManager.getInstance().getCliente(dni);
        HashMap<String, String> credencialesVisa = DBManager.getInstance().getVisa(cliente);
	    return credencialesVisa;
	}
    @POST
    @Path("/paypal")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePaypal(Pago pago) {
        DBManager.getInstance().updatePago(pago);
	}
    @POST
    @Path("/visa")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateVisa(Pago pago) {
        DBManager.getInstance().updatePago(pago);
	}
}
