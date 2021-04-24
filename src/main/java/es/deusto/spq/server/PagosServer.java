package es.deusto.spq.server;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.Pago;

@Path("/pagos")
public class PagosServer {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> getCredencialesPaypal(String DNI) {
        HashMap<String, String> credencialesPaypal = DBManager.getInstance().getPaypal(DNI);
		return credencialesPaypal;
	}
    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> getCredencialesVisa(String DNI) {
        HashMap<String, String> credencialesVisa = DBManager.getInstance().getVisa(DNI);
		return credencialesVisa;
	}
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPago(Pago pago) {
        DBManager.getInstance().store(pago);
	}
    // @POST
    // @Consumes(MediaType.APPLICATION_JSON)
    public void updatePago(Pago pago) {
        DBManager.getInstance().updatePago(pago);
	}
}
