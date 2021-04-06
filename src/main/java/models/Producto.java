package models;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable(detachable = "true")
public class Producto {

	public String nombre;
	public String descripcion;
	public int stock;
	public int precio;
	public String imagen;
	@Persistent(defaultFetchGroup = "true")
	private Marca marca;
	@Persistent(defaultFetchGroup = "true")
	private SubCategoria subcategoria;

	@Persistent(defaultFetchGroup = "true", mappedBy = "producto", dependentElement = "true")
	@Join
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

	@Persistent(defaultFetchGroup = "true", dependentElement = "true")
	@Join
	private HashMap<Colores, Tallas> tallas_colores = new HashMap<Colores, Tallas>();

	
	public Producto( String nombre, String descripcion, int stock, int precio, String imagen, SubCategoria subcategoria, Marca marca) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.imagen = imagen;
		this.subcategoria = subcategoria;
		this.marca = marca;
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
	public Marca getMarca() {
		return this.marca;
	}
	

	public ArrayList<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void removePedidos() {
		for (Pedido r : this.pedidos) {
			this.pedidos.remove(r);
		}
	}
	public HashMap<Colores, Tallas> getTallas_colores() {
		return this.tallas_colores;
	}
	
	public SubCategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(SubCategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void setTallas_colores(HashMap<Colores, Tallas> tallas_colores) {
		this.tallas_colores = tallas_colores;
	}

	public String toStringDebug() {
		return "Producto [nombre=" + nombre + ", descripcion=" + descripcion
				+ ", stock=" + stock + ", precio=" + precio + ", imagen=" + imagen + "]";
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
	
}
