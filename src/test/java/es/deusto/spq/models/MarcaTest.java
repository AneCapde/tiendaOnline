package es.deusto.spq.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MarcaTest {
	
	Marca mar;
	@Before
	public void initiali(){
		mar = new Marca("Nombre", "Descripcion");
	}
	

	@Test
	public void testGetNombre() {
		String resul = mar.getNombre();
		assertEquals("Nombre", resul);
	}

	@Test
	public void testSetNombre() {
		mar.setNombre("Nombre2");
		String resul = mar.getNombre();
		assertEquals("Nombre2", resul);
	}

	@Test
	public void testGetDescription() {
		String resul = mar.getDescripcion();
		assertEquals("Descripcion", resul);
	}

	@Test
	public void testSetDescription() {
		mar.setDescripcion("Descripcion2");
		String resul = mar.getDescripcion();
		assertEquals("Descripcion2", resul);
	}

	@Test
	public void testToStringDebug() {
		String resul = mar.toStringDebug();
		assertEquals("Marca [nombre=Nombre, descripcion=Descripcion]", resul);
	}

	@Test
	public void testTostring() {
		String resul = mar.toString();
		assertEquals("Nombre", resul);
		//fail("Not yet implemented");
	}

}
