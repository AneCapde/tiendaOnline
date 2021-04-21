package es.deusto.spq.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import es.deusto.spq.models.CategoriaTest;

public class CategoriaTest {

	Categoria cat;
	@Before
	public void initiali(){
		cat = new Categoria("Nombre", "Descripcion");
	}
	

	@Test
	public void testGetNombre() {
		String resul = cat.getNombre();
		assertEquals("Nombre", resul);
	}

	@Test
	public void testSetNombre() {
		cat.setNombre("Nombre2");
		String resul = cat.getNombre();
		assertEquals("Nombre2", resul);
	}

	@Test
	public void testGetDescription() {
		String resul = cat.getDescripcion();
		assertEquals("Descripcion", resul);
	}

	@Test
	public void testSetDescription() {
		cat.setDescripcion("Descripcion2");
		String resul = cat.getDescripcion();
		assertEquals("Descripcion2", resul);
	}

	@Test
	public void testToStringDebug() {
		String resul = cat.toStringDebug();
		assertEquals("Categoria [nombre=Nombre, descripcion=Descripcion]", resul);
	}

	@Test
	public void testTostring() {
		String resul = cat.toString();
		assertEquals("Nombre", resul);
		//fail("Not yet implemented");
	}

}
