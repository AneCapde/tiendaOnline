package es.deusto.spq.server;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Pago;

@Path("/pagos")
public class PagosServer {
    
    @GET
    @Path("/paypal")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> getCredencialesPaypal(Cliente cliente) {
        HashMap<String, String> credencialesPaypal = DBManager.getInstance().getPaypal(cliente);
		return credencialesPaypal;
	}
    @GET
    @Path("/visa")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> getCredencialesVisa(Cliente cliente) {
        HashMap<String, String> credencialesVisa = DBManager.getInstance().getVisa(cliente);
	    return credencialesVisa;
	}
    @POST
    @Path("/paypal")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPago(Pago pago) {
        DBManager.getInstance().store(pago);
	}
    @POST
    @Path("/visa")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePago(Pago pago) {
        DBManager.getInstance().updatePago(pago);
	}
}
