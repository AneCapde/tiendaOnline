package Modelos;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Pedido {

	@PrimaryKey
	public int id_pedido;
	private Date fecha;
	private String estado;
	private int importe;
	private int cantidad;
	@ForeignKey
	private String DNI;
	
	public Pedido(int id_pedido, Date fecha, String estado, int importe, int cantidad, String dNI) {
		super();
		this.id_pedido = id_pedido;
		this.fecha = fecha;
		this.estado = estado;
		this.importe = importe;
		this.cantidad = cantidad;
		DNI = dNI;
	}
	public int getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
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

	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + cantidad;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id_pedido;
		result = prime * result + importe;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		if (cantidad != other.cantidad)
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id_pedido != other.id_pedido)
			return false;
		if (importe != other.importe)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pedido [id_pedido=" + id_pedido + ", fecha=" + fecha + ", estado=" + estado + ", importe=" + importe
				+ ", cantidad=" + cantidad + ", DNI=" + DNI + "]";
	}
	
	
	
}
