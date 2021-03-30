package Modelos;

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
	@Persistent(defaultFetchGroup = "true")
	private Cliente cliente;
	private Productos producto;
	
	public Pedido(Date fecha, String estado, int importe, int cantidad, Cliente cliente) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.importe = importe;
		this.cantidad = cantidad;
		this.cliente = cliente;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Cliente getCliente() {
		return this.cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return "Pedido [fecha=" + fecha + ", estado=" + estado + ", importe=" + importe
				+ ", cantidad=" + cantidad  + "]";
	}
	
	
	
}
