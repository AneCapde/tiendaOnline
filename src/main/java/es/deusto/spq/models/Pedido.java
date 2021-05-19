package es.deusto.spq.models;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Pedido {

	private Date fecha;
	private String estado;
	private int importe;
	private int cantidad;
	private String lugar;
	@Persistent(defaultFetchGroup = "true")
	private Cliente cliente;
	
	@Persistent(defaultFetchGroup = "true")
	@Join
	private ArrayList<Producto> productoPedido = new ArrayList<>();
	
	/**
	 * Crea un nuevo Pedido
	 * @param cliente	Cliente que realzia el pedido
	 * @param fecha		Fecha en la que se realzia en pedido
	 * @param estado	Estado actual del pedido
	 * @param importe	Importe que supone el pedido
	 * @param cantidad	Cantidad de articulos dentro del pedido
	 * @param lugar		Lugar en el que se desea recibir el pedido
	 */
	public Pedido(Cliente cliente, Date fecha, String estado, int importe, int cantidad, String lugar) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.estado = estado;
		this.importe = importe;
		this.cantidad = cantidad;
		this.lugar = lugar;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return this.fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return this.estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getImporte() {
		return this.importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
	public int getCantidad() {
		return this.cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public ArrayList<Producto> getProducto() {
		return productoPedido;
	}

	public void setProducto(ArrayList<Producto> productoPedido) {
		this.productoPedido = productoPedido;
	}

	@Override
	public String toString() {
		return "Pedido [fecha=" + fecha + ", estado=" + estado + ", importe=" + importe + ", cantidad=" + cantidad
				+ ", cliente=" + cliente + ", producto=" + productoPedido + "]";
	}
	
}
