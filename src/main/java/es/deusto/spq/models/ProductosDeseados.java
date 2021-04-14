package es.deusto.spq.models;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class ProductosDeseados {
	
	@Persistent(defaultFetchGroup = "true")
	private Cliente cliente;
	@Persistent(defaultFetchGroup = "true")
	private Producto producto;
	
	public ProductosDeseados(Cliente cliente, Producto producto) {
		super();
		this.cliente = cliente;
		this.producto = producto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "ListaDeseados [cliente=" + cliente + ", producto=" + producto + "]";
	}

}
