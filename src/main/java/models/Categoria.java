package models;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Categoria {

	public String nombre;
	public String descripcion;
	@Persistent(defaultFetchGroup = "true", mappedBy = "categoria", dependentElement = "true")
	@Join
	private ArrayList<SubCategoria> subcategorias = new ArrayList<SubCategoria>();

	
	public Categoria(String nombre, String descripcion) {
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
		return "Categoria [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	
}
