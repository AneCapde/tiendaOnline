package es.deusto.spq.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.cliente.TiendaGUI;

public class IdiomasTest {

	@Before 
	public void setUp() {
	   PrepararDatos.getInstance().cargarDatosXML();
	}
	
	@Test
	public void testSeleccionarPalabraEspañol() {
		TiendaGUI.idioma = Idiomas.Español;
//		Idiomas.Español;
		assertEquals("Español", Idiomas.seleccionarPalabra("nombre1"));
	}
	
	@Test
	public void testSeleccionarPalabraIngles() {
		TiendaGUI.idioma = Idiomas.Ingles;
//		Idiomas.Español;
		assertEquals("English", Idiomas.seleccionarPalabra("nombre1"));
	}
	
}
