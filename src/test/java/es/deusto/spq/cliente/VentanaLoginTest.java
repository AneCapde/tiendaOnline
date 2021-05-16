package es.deusto.spq.cliente;

import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Cliente.Genero;


@RunWith(MockitoJUnitRunner.class)
public class VentanaLoginTest {
	VentanaLogin ventanaLogin;
	WebTarget web = Mockito.mock(WebTarget.class);
	
	@Mock
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
	Cliente cliente;
	
	/**
	 * Este método se ejecuta antes de que se ejecute cualquier método de prueba JUnit: se anota con la anotación Before.
	*/
	@Before
	public void inicio(){
		cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario", Genero.MUJER, 48920, "usuario", "usuario");
		ventanaLogin = new VentanaLogin(tiendaGUI, web);
	}
	
	/**
	 * Metodo poara validar que el metodo Aceptar de la Ventana login funciona adecuadamente
	 */
	@Test
	public void testAceptar() {
//		ventanaLogin.aceptar(tiendaGUI, web);
	}

}
