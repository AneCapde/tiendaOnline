package es.deusto.spq.cliente;

import java.util.List;

import javax.ws.rs.client.WebTarget;

import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Producto;

public interface IListaDeseados {

	public void updateClient(final WebTarget appTarget);
	public void eliminar();
	public void anyadir();
	public List<Producto> getProductosDeseados();
}
