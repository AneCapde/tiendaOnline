package Modelos;

import javax.jdo.annotations.ForeignKey;

public class ProductoColor {

	@ForeignKey
	private int id_color;
	
	@ForeignKey
	private int id_producto;

	public ProductoColor(int id_color, int id_producto) {
		super();
		this.id_color = id_color;
		this.id_producto = id_producto;
	}

	public int getId_color() {
		return id_color;
	}

	public void setId_color(int id_color) {
		this.id_color = id_color;
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
		result = prime * result + id_color;
		result = prime * result + id_producto;
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
		ProductoColor other = (ProductoColor) obj;
		if (id_color != other.id_color)
			return false;
		if (id_producto != other.id_producto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductoColor [id_color=" + id_color + ", id_producto=" + id_producto + "]";
	}
	
	
}
