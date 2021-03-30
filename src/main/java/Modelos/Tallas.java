package Modelos;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Tallas {
	
	public String talla;

	public Tallas(String talla) {
		super();
		this.talla = talla;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	@Override
	public String toString() {
		return "Tallas [talla=" + talla + "]";
	}
	
	
	
}
