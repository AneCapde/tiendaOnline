package es.deusto.spq.models;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Marca {
	
	private String nombre;
	private String descripcion;
	
	public Marca(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String toStringDebug() {
		return "Marca [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	@Override
	public String toString() {
		return nombre;
	}
	
	

}
