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

@Path("/subcategorias")
public class SubCategoriasServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubCategoria> getSubCategorias() {
		List<SubCategoria> subCategorias = DBManager.getInstance().getSubcategorias();
		return subCategorias;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addSubcategoria(SubCategoria subCategoria) {
        DBManager.getInstance().store(subCategoria);
    }
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
