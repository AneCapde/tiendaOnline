package Modelos;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable(detachable = "true")
public class Productos {

	public String nombre;
	public String descripcion;
	public int stock;
	public int precio;
	public String imagen;
	private Categoria categoria;
	private Marca marca;

	@Persistent(defaultFetchGroup = "true", mappedBy = "producto", dependentElement = "true")
	@Join
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

	

	public Productos( String nombre, String descripcion, int stock, int precio, String imagen) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.imagen = imagen;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Productos [nombre=" + nombre + ", descripcion=" + descripcion
				+ ", stock=" + stock + ", precio=" + precio + ", imagen=" + imagen + 
				"]";
	}
	
	
	
}
