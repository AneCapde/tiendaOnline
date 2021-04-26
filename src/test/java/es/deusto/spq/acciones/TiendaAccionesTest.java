package es.deusto.spq.acciones;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;

public class TiendaAccionesTest {
	Categoria cat;
	Categoria cat2;
	TiendaAcciones acciones;
	SubCategoria subCat;
	SubCategoria subCat2;
	Marca marc1;
	Marca marc2;
	Producto prod;
	Producto prod2;
	ArrayList<Producto> arr = new ArrayList<Producto>();
	@Before
	public void inicializar() {
		acciones = new TiendaAcciones();
		cat = new Categoria("NombreCat", "DescripcionCat");
		cat2 = new Categoria("NombreCat2", "DescripcionCat");
		subCat = new SubCategoria("NombreSub", "DescripcionSub", cat);
		subCat2 = new SubCategoria("NombreSub2", "DescripcionSub", cat2);
		marc1 = new Marca("Nombre", "Descripcion");
		marc2 = new Marca("Nombre2", "Descripcion");
		prod = new Producto("producto", "descripcion", 2, 2, "img", subCat, marc1);
		prod2 = new Producto("2producto", "descripcion", 2, 2, "img", subCat2, marc2);
		
		arr.add(prod);
		arr.add(prod2);
//		fail("Not yet implemented");
	}
	
	@Test
	public void testBuscarSubca() {
		ArrayList<Producto> res = acciones.buscar("", null, subCat, null, null, null, arr);
		ArrayList<Producto> exp = new ArrayList<Producto>();
		exp.add(prod);
		assertEquals(exp, res);
	}
	
	@Test
	public void testBuscarCat() {
		ArrayList<Producto> res = acciones.buscar("", cat, null, null, null, null, arr);
		ArrayList<Producto> exp = new ArrayList<Producto>();
		exp.add(prod);
		assertEquals(exp, res);
	}
	@Test
	public void testBuscarMarc() {
		ArrayList<Producto> res = acciones.buscar("", null, null, marc2 , null, null, arr);
		ArrayList<Producto> exp = new ArrayList<Producto>();
		exp.add(prod2);
		assertEquals(exp, res);
	}
	
	@Test
	public void testBuscarText() {
		ArrayList<Producto> res = acciones.buscar("2prod", null, null, null, null, null, arr);
		ArrayList<Producto> exp = new ArrayList<Producto>();
		exp.add(prod2);
		assertEquals(exp, res);
	}

}
