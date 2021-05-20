package es.deusto.spq.acciones;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Cliente.Genero;

public class TiendaAccionesTest {
	Categoria cat;
	Categoria cat2;
	SubCategoria subCat;
	SubCategoria subCat2;
	SubCategoria subCat3;
	Marca marc1;
	Marca marc2;
	Producto prod;
	Producto prod2;
	ArrayList<Producto> arr = new ArrayList<Producto>();
	ArrayList<SubCategoria> subcats = new ArrayList<SubCategoria>();
	
	@Before
	public void inicializar() {
		cat = new Categoria("NombreCat", "DescripcionCat");
		cat2 = new Categoria("NombreCat2", "DescripcionCat");
		subCat = new SubCategoria("NombreSub", "DescripcionSub", cat);
		subCat2 = new SubCategoria("NombreSub2", "DescripcionSub", cat2);
		subCat3 = new SubCategoria("NombreSub3", "DescripcionSub", cat2);
		marc1 = new Marca("Nombre", "Descripcion");
		marc2 = new Marca("Nombre2", "Descripcion");
		prod = new Producto("producto", "descripcion", 2, 2, "img", subCat, marc1);
		prod2 = new Producto("2producto", "descripcion", 2, 2, "img", subCat2, marc2);
		subcats.add(subCat);
		subcats.add(subCat2);
		subcats.add(subCat3);
		arr.add(prod);
		arr.add(prod2);
	}
	
	@Test
	public void testBuscarSubca() {
		ArrayList<Producto> res = TiendaAcciones.getInstance().buscar("", null, subCat, null, null, null, arr);
		ArrayList<Producto> exp = new ArrayList<Producto>();
		exp.add(prod);
		assertEquals(exp, res);
	}
	
	@Test
	public void testBuscarCat() {
		ArrayList<Producto> res = TiendaAcciones.getInstance().buscar("", cat, null, null, null, null, arr);
		ArrayList<Producto> exp = new ArrayList<Producto>();
		exp.add(prod);
		assertEquals(exp, res);
	}
	@Test
	public void testBuscarMarc() {
		ArrayList<Producto> res = TiendaAcciones.getInstance().buscar("", null, null, marc2 , null, null, arr);
		ArrayList<Producto> exp = new ArrayList<Producto>();
		exp.add(prod2);
		assertEquals(exp, res);
	}
	
	@Test
	public void testBuscarText() {
		ArrayList<Producto> res = TiendaAcciones.getInstance().buscar("2prod", null, null, null, null, null, arr);
		ArrayList<Producto> exp = new ArrayList<Producto>();
		exp.add(prod2);
		assertEquals(exp, res);
	}
	@Test
	public void rellenarSubcategoriasTest() {
		ArrayList<SubCategoria> res = TiendaAcciones.getInstance().rellenarSubcategorias(cat2, subcats);
		ArrayList<SubCategoria> exp = new ArrayList<SubCategoria>();
		exp.add(subCat2);
		exp.add(subCat3);
		assertEquals(exp, res);
	}
	@Test
	public void rellenarSubcategoriasTestVacio() {
		ArrayList<SubCategoria> res = TiendaAcciones.getInstance().rellenarSubcategorias(null, subcats);
		ArrayList<SubCategoria> exp = new ArrayList<SubCategoria>();
		assertEquals(exp, res);
	}
}
