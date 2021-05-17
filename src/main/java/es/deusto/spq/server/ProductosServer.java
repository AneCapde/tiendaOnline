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
import es.deusto.spq.models.Producto;

@Path("/productos")
public class ProductosServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getProductos() {
		List<Producto> productos = DBManager.getInstance().getProductos();
		return productos;
    }
    
    @GET
    @Path("/masBuscado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getMasComprados() {
		List<Producto> productos = DBManager.getInstance().getMasComprados();
		System.out.println(productos);
		return productos;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProducto(Producto producto) {
        DBManager.getInstance().store(producto);
    }
    @DELETE
    @Path("/{code}")
    public Response deleteUser(@PathParam("code") int code) {
        if (code == 10) {
            System.out.println("Deleting produtco...");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
