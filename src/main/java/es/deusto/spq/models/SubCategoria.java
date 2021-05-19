package es.deusto.spq.models;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
@PersistenceCapable(detachable = "true")
public class SubCategoria {
	
	private String nombre;
	private String descripcion;
	@Persistent(defaultFetchGroup = "true")
	private Categoria categoria;

	/**
	 * Crea una nueva SubCategoria
	 * @param nombre		Nombre de la SubCategoria
	 * @param descripcion	Descripcion de la SubCategoria
	 * @param categoria		Categoria a la que pertenece la SubCategoria
	 */
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
		return "SubCategoria [nombre=" + nombre + ", descripcion="+ descripcion + " dentro de Categorria==" + categoria.getNombre() + "]";
	}
	@Override
	public String toString() {
		return nombre;
	}
	
	
}
