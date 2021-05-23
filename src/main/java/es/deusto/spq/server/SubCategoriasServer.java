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
import es.deusto.spq.models.SubCategoria;

/**
 * Clase server para las subcategorias para la comunicación 
 * con la BD
 */

@Path("/subcategorias")
public class SubCategoriasServer {

	/**
	 * Devuelve una lista de subcategorias de la BD
	 * @return lista de subcategorias
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubCategoria> getSubCategorias() {
		List<SubCategoria> subCategorias = DBManager.getInstance().getSubcategorias();
		return subCategorias;
    }
    
    /**
     * Guarda una subcategoria en la BD
     * @param subCategoria la subcategoria que se quiere guardar
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addSubcategoria(SubCategoria subCategoria) {
        DBManager.getInstance().store(subCategoria);
    }
    
    /**
     * Borra una subcategoria de la BD
     * @param code el code
     * @return devuelve ok o not found dependiendo de si ha encontrado la subcategoria
     */   
    @DELETE
    @Path("/{code}")
    public Response deleteSubcategoria(@PathParam("code") int code) {
        if (code == 10) {
            System.out.println("Deleting subcategoria...");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
