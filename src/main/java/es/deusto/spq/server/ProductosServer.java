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

/**
 * Clase server para los Productos para la comunicación 
 * con la BD
 */

@Path("/productos")
public class ProductosServer {

	/**
	 * Devuelve una lista de productos de la BD
	 * @return lista de productos
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getProductos() {
		List<Producto> productos = DBManager.getInstance().getProductos();
		return productos;
    }
    
    /**
     * Devuelve una lista de los productos más buscados en la tiendaOnline
     * @return lista de los productos más buscados
     */
    @GET
    @Path("/masBuscado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getMasComprados() {
		List<Producto> productos = DBManager.getInstance().getMasComprados();
		System.out.println(productos);
		return productos;
    }
    
    /**
     * Guarda un producto en la BD
     * @param producto el producto que se quiere guardar
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProducto(Producto producto) {
        DBManager.getInstance().store(producto);
    }
    
    /**
     * Actualiza un producto en la BD
     * @param producto el producto a actualizar
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProducto(Producto producto) {
    	Producto produc = DBManager.getInstance().getProducto(producto.nombre);
    	produc.setComentarios(producto.getComentarios());
    		
    	
    	DBManager.getInstance().store(produc);
}
    /**
     * Borra un producto de la BD
     * @param code el code
     * @return devuelve ok o not found dependiendo de si ha encontrado la subcategoria
     */
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
