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
import es.deusto.spq.models.Marca;

/**
 * Clase server para las Marcas para la comunicación 
 * con la BD
 */

@Path("/marcas")
public class MarcasServer {
	
	/**
	 * Devuelve el listado de marcas de la BD
	 * @return lista de marcas
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Marca> getMarcas() {
		List<Marca> marcas = DBManager.getInstance().getMarcas();
		return marcas;
    }
    
    /**
     * Guarda una marca en la BD
     * @param marca la marca a guardar 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMarca(Marca marca) {
        DBManager.getInstance().store(marca);
    }
    
    /**
     * Borra una marca de la BD
     * @param code el code
     * @return devuelve ok o not found dependiendo de si ha encontrado la categoria
     */
    @DELETE
    @Path("/{code}")
    public Response deleteMarca(@PathParam("code") int code) {
        if (code == 10) {
            System.out.println("Deleting marca...");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
