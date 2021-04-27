package es.deusto.spq.models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.models.Cliente.Genero;

public class ProductoTest {
	
	//pro2,mar2 igual no es necesario (inicializar)
	Producto pro,pro2;
	Marca mar,mar2;
	SubCategoria sub,sub2;
	Categoria cat,cat2;
	ArrayList<Colores> colores; 
	ArrayList<Tallas> tallas;
	
	//Tenemos dos constructores de productos
	@Before
	public void initiali() {
		cat = new Categoria("","");
		sub = new SubCategoria("","",cat);
		mar = new Marca("","");
		colores = new ArrayList<>(); 
		tallas = new ArrayList<>();
		pro = new Producto("producto","descripcion",5,5,"h",sub,mar);
		pro2 = new Producto("producto3","descripcion3",7,7,"e",sub,mar,colores,tallas);
		
	}

	@Test
	public void testGetNombre() {
		String resul = pro.getNombre();
		assertEquals("producto", resul);
	}

	@Test
	public void testSetNombre() {
		pro.setNombre("producto2");
		String resul = pro.getNombre();
		assertEquals("producto2", resul);
	}

	@Test
	public void testGetDescripcion() {
		String resul = pro.getDescripcion();
		assertEquals("descripcion", resul);
	}

	@Test
	public void testSetDescripcion() {
		pro.setDescripcion("descripcion2");
		String resul = pro.getDescripcion();
		assertEquals("descripcion2", resul);
	}

	@Test
	public void testGetStock() {
		int resul = pro.getStock();
		assertEquals(5,resul);
	}

	@Test
	public void testSetStock() {
		pro.setStock(7);
		int resul = pro.getStock();
		assertEquals(7, resul);
		
	}

	@Test
	public void testGetPrecio() {
		int resul = pro.getPrecio();
		assertEquals(5,resul);
		
	}

	@Test
	public void testSetPrecio() {
		pro.setPrecio(7);
		int resul = pro.getPrecio();
		assertEquals(7, resul);
	}

	@Test
	public void testGetImagen() {
		String resul = pro.getImagen();
		assertEquals("h", resul);
	}

	@Test
	public void testSetImagen() {
		pro.setImagen("hh");;
		String resul = pro.getImagen();
		assertEquals("hh", resul);
		
	}

	@Test
	public void testGetMarca() {
		Marca resul = pro.getMarca();
		assertEquals(mar,resul);
	}
	
	@Test
	public void testSetMarca() {
		pro.setMarca(mar2);
		Marca resul = pro.getMarca();
		assertEquals(mar2, resul);
	}

	@Test
	public void testGetSubcategoria() {
		SubCategoria resul = pro.getSubcategoria();
		assertEquals(sub,resul);
	}

	@Test
	public void testSetSubcategoria() {
		pro.setSubcategoria(sub2);
		SubCategoria resul = pro.getSubcategoria();
		assertEquals(sub2, resul);
	}

	@Test
	public void testSetGetColores() {
		colores = new ArrayList<>();
		pro2 = new Producto("producto3","descripcion3",7,7,"e",sub,mar,colores,tallas);
		ArrayList<Colores> resul = pro2.getColores();
		assertEquals(colores, resul);
	}
	
	@Test
	public void testSetGetTallas() {
		tallas = new ArrayList<>();
		pro2 = new Producto("producto3","descripcion3",7,7,"e",sub,mar,colores,tallas);
		ArrayList<Tallas> resul = pro2.getTallas();
		assertEquals(tallas, resul);
	}
	
	@Test
	public void testToStringDebug() {
		String resul = pro.toStringDebug();
		assertEquals("Producto [nombre=producto, descripcion=descripcion, stock=5, precio=5, imagen=h]", resul);	
	}

	@Test
	public void testToString() {
		String resul = pro.toString();
		assertEquals("producto", resul);	
	}

}
