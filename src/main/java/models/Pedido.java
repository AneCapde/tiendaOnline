package models;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Pedido {

	private Date fecha;
	private String estado;
	private int importe;
	private int cantidad;
	@Persistent(defaultFetchGroup = "true")
	private Cliente cliente;
	@Persistent(defaultFetchGroup = "true")
	private Producto producto;
	
	public Pedido(Date fecha, String estado, int importe, int cantidad, Producto producto) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.importe = importe;
		this.cantidad = cantidad;
		this.producto = producto;
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
	
	@Override
	public String toString() {
		return "Pedido [fecha=" + fecha + ", estado=" + estado + ", importe=" + importe + ", cantidad=" + cantidad  + "]";
	}
	
	
	
}
