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
    @GET
    @Path("/pago/{DNI}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pago pago(@PathParam("DNI") String dni) {
        Cliente cliente = DBManager.getInstance().getCliente(dni);
        Pago pago = DBManager.getInstance().getPago(cliente);
	    return pago;
	}
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePago(Pago pago) {
        Cliente c = DBManager.getInstance().getCliente(pago.getDNI());
        Pago p = DBManager.getInstance().getPago(c);

        p.setCredencialesVisa(pago.getCredencialesVisa());
        p.setCredencialesPaypal(pago.getCredencialesPaypal());
        System.out.println(p.getDNI() + " " + p.getCredencialesPaypal() + " " + p.getCredencialesVisa());

        //SE AÑADE UN OBJETO NUEVO
        DBManager.getInstance().store(p);
	}
}
