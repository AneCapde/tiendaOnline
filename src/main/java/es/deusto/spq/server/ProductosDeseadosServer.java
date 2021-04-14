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

import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.ProductosDeseados;

@Path("/productosDeseados")
public class ProductosDeseadosServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductosDeseados> getProductosDeseados() {
		List<ProductosDeseados> productosDeseados = DBManager.getInstance().getProductosDeseados();
		return productosDeseados;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPedido(ProductosDeseados productosDeseados) {
        DBManager.getInstance().store(productosDeseados);
    }
    @DELETE
    @Path("/{code}")
    public Response deleteProductosDeseados(@PathParam("code") int code) {
        if (code == 10) {
            System.out.println("Deleting pedido...");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
