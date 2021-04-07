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

import dao.DBManager;
import models.Categoria;

@Path("/categorias")
public class CategoriasServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> getCategorias() {
		List<Categoria> categorias = DBManager.getInstance().getCategorias();
		return categorias;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCategoria(Categoria categoria) {
        DBManager.getInstance().store(categoria);
    }
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
