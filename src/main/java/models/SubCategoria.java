package models;

import javax.jdo.annotations.PersistenceCapable;
@PersistenceCapable(detachable = "true")
public class SubCategoria {
	
	private String nombre;
	private String descripcion;
	private Categoria categoria;


	public SubCategoria(String nombre, String descripcion, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
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

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String toStringDebug() {
		return "SubCategoria [nombre=" + nombre + ", descripcion="+ descripcion + "]";
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	
}
