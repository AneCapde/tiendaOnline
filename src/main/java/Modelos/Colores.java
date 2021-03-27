package Modelos;

import javax.jdo.annotations.PrimaryKey;

public class Colores {

	@PrimaryKey
	public int id_color;
	
	private String nombre;

	public Colores(int id_color, String nombre) {
		super();
		this.id_color = id_color;
		this.nombre = nombre;
	}

	public int getId_color() {
		return id_color;
	}

	public void setId_color(int id_color) {
		this.id_color = id_color;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Colores [id_color=" + id_color + ", nombre=" + nombre + "]";
	}
	
	
}
