package Modelos;

import javax.jdo.annotations.ForeignKey;

public class ProductoTallas {

	@ForeignKey
	private int id_talla;
	
	@ForeignKey
	private int id_producto;

	public ProductoTallas(int id_talla, int id_producto) {
		super();
		this.id_talla = id_talla;
		this.id_producto = id_producto;
	}

	public int getId_talla() {
		return id_talla;
	}

	public void setId_talla(int id_talla) {
		this.id_talla = id_talla;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_producto;
		result = prime * result + id_talla;
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
		ProductoTallas other = (ProductoTallas) obj;
		if (id_producto != other.id_producto)
			return false;
		if (id_talla != other.id_talla)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductoTallas [id_talla=" + id_talla + ", id_producto=" + id_producto + "]";
	}
	
	
}
