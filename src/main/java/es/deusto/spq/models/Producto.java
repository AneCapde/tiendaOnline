package es.deusto.spq.models;

import java.util.ArrayList;


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
	@Persistent(defaultFetchGroup = "true", dependentElement = "true")
	@Join
	private ArrayList<Colores> colores;
	@Persistent(defaultFetchGroup = "true", dependentElement = "true")
	@Join
	private ArrayList<Tallas> tallas;
	@Persistent(defaultFetchGroup = "true", dependentElement = "true")
	@Join
	private ArrayList<String> comentarios;
	
	/**
	 * Crea un nuevo Producto
	 * @param nombre		Nombre del producto
	 * @param descripcion	Descripcion del Producto
	 * @param stock			Stock de el producto en la tienda
	 * @param precio		Precio del Producto
	 * @param imagen		Imagen del Producto
	 * @param subcategoria	Subcategoria del producto
	 * @param marca			Marca del Producto
	 */
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
	
//	public Producto( String nombre, String descripcion, int stock, int precio, String imagen, SubCategoria subcategoria, Marca marca, ArrayList<String> comentarios) {
//		super();
//		this.nombre = nombre;
//		this.descripcion = descripcion;
//		this.stock = stock;
//		this.precio = precio;
//		this.imagen = imagen;
//		this.subcategoria = subcategoria;
//		this.marca = marca;
//		this.comentarios = new ArrayList<>();
//	}
	
	
	public Producto( String nombre, String descripcion, int stock, int precio, String imagen, SubCategoria subcategoria, Marca marca, ArrayList<Colores> colores, ArrayList<Tallas> tallas) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.imagen = imagen;
		this.subcategoria = subcategoria;
		this.marca = marca;
		this.setColores(colores);
		this.setTallas(tallas);
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
	
	public SubCategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(SubCategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String toStringDebug() {
		return "Producto [nombre=" + nombre + ", descripcion=" + descripcion
				+ ", stock=" + stock + ", precio=" + precio + ", imagen=" + imagen + "]";
	}

	@Override
	public String toString() {
		return nombre;
	}

	public ArrayList<Colores> getColores() {
		return colores;
	}

	public void setColores(ArrayList<Colores> colores) {
		this.colores = colores;
	}

	public ArrayList<Tallas> getTallas() {
		return tallas;
	}

	public void setTallas(ArrayList<Tallas> tallas) {
		this.tallas = tallas;
	}

	public ArrayList<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<String> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
}
