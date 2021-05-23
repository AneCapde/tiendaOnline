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
import es.deusto.spq.models.Categoria;

/**
 * Clase server para las Categorias para la comunicación 
 * con la BD
 */

@Path("/categorias")
public class CategoriasServer {

	/**
	 * Devuelve una lista de categorias de la BD
	 * @return lista de cagetorias
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> getCategorias() {
		List<Categoria> categorias = DBManager.getInstance().getCategorias();
		return categorias;
    }
    
    /**
     * Guarda una categoria en la BD
     * @param categoria la categoria que se va a guardar
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public static void addCategoria(Categoria categoria) {
        DBManager.getInstance().store(categoria);
    }
    
    /**
     * Borra una categoria
     * @param code el code
     * @return devuelve ok o not found dependiendo de si ha encontrado la categoria
     */
    @DELETE
    @Path("/{code}")
    public Response deleteCategoria(@PathParam("code") int code) {
        if (code == 10) {
            System.out.println("Deleting categoria...");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
