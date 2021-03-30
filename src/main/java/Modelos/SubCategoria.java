package Modelos;

public class SubCategoria {

	public int id_subCategoria;
	
	private String nombre;
	private String descripcion;

	private int id_categoria;

	public SubCategoria(int id_subCategoria, String nombre, String descripcion, int id_categoria) {
		super();
		this.id_subCategoria = id_subCategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id_categoria = id_categoria;
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

	@Override
	public String toString() {
		return "SubCategoria [id_subCategoria=" + id_subCategoria + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", id_categoria=" + id_categoria + "]";
	}
	
	
}
