package Modelos;

public class Colores {

	private String nombre;

	public Colores(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Colores [nombre=" + nombre + "]";
	}
	
	
}
