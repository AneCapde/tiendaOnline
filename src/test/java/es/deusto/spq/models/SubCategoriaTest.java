package es.deusto.spq.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SubCategoriaTest {
	
	SubCategoria subCat;
	Categoria cat;
	@Before
	public void initiali(){
		cat = new Categoria("NombreCat", "DescripcionCat");
		subCat = new SubCategoria("NombreSub", "DescripcionSub", cat);
	}

	@Test
	public void testGetNombre() {
		String resul = subCat.getNombre();
		assertEquals("NombreSub", resul);
	}

	@Test
	public void testSetNombre() {
		subCat.setNombre("Nombre2");
		String resul = subCat.getNombre();
		assertEquals("Nombre2", resul);
	}

	@Test
	public void testGetDescription() {
		String resul = subCat.getDescripcion();
		assertEquals("DescripcionSub", resul);
	}

	@Test
	public void testSetDescription() {
		subCat.setDescripcion("Descripcion2");
		String resul = subCat.getDescripcion();
		assertEquals("Descripcion2", resul);
	}

	@Test
	public void testGetCategoria() {
		Categoria resul = subCat.getCategoria();
		assertEquals(cat, resul);
	}

	@Test
	public void testSetCategoria() {
		Categoria camb = new Categoria("test", "test");
		subCat.setCategoria(camb);
		Categoria resul = subCat.getCategoria();
		assertEquals(camb, resul);
	}

	@Test
	public void testToStringDebug() {
		String result = subCat.toStringDebug();
		String espec = "SubCategoria [nombre=NombreSub, descripcion=DescripcionSub dentro de Categorria==NombreCat]";
		assertEquals(espec, result);
	}

	@Test
	public void testToString() {
		String result = subCat.toString();
		String espec = "NombreSub";
		assertEquals(espec, result);
	}

}
