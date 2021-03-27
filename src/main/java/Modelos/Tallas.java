package Modelos;

import javax.jdo.annotations.PrimaryKey;

public class Tallas {
	
	@PrimaryKey
	public int id_talla;
	
	public String talla;

	public Tallas(int id_talla, String talla) {
		super();
		this.id_talla = id_talla;
		this.talla = talla;
	}

	public int getId_talla() {
		return id_talla;
	}

	public void setId_talla(int id_talla) {
		this.id_talla = id_talla;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_talla;
		result = prime * result + ((talla == null) ? 0 : talla.hashCode());
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
		Tallas other = (Tallas) obj;
		if (id_talla != other.id_talla)
			return false;
		if (talla == null) {
			if (other.talla != null)
				return false;
		} else if (!talla.equals(other.talla))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tallas [id_talla=" + id_talla + ", talla=" + talla + "]";
	}
	
	
	
}
