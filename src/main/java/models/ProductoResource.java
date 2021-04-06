package models;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DBManager;

@Path("productos")
public class ProductoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getProductos() {

		List<Producto> productos = DBManager.getInstance().getProductos();

		return productos;
    }
    
}
