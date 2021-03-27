package Modelos;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.PrimaryKey;

public class SubCategoria {

	@PrimaryKey
	public int id_subCategoria;
	
	private String nombre;
	private String descripcion;
	
	@ForeignKey
	private int id_categoria;

	public SubCategoria(int id_subCategoria, String nombre, String descripcion, int id_categoria) {
		super();
		this.id_subCategoria = id_subCategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id_categoria = id_categoria;
	}

	public int getId_subCategoria() {
		return id_subCategoria;
	}

	public void setId_subCategoria(int id_subCategoria) {
		this.id_subCategoria = id_subCategoria;
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

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + id_categoria;
		result = prime * result + id_subCategoria;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		SubCategoria other = (SubCategoria) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id_categoria != other.id_categoria)
			return false;
		if (id_subCategoria != other.id_subCategoria)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubCategoria [id_subCategoria=" + id_subCategoria + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", id_categoria=" + id_categoria + "]";
	}
	
	
}
