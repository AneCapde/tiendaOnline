package es.deusto.spq.cliente;

import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Ignore;
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
	
	@Before
	public void inicio(){
		cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario", Genero.MUJER, 48920, "usuario", "usuario");
		ventanaLogin = new VentanaLogin(tiendaGUI, web);
	}
	
	
	@Test
	public void testAceptar() {
//		ventanaLogin.aceptar(tiendaGUI, web);
	}
	
	@Test
	public void testSeleccionidioma() {
//		ventanaLogin.seleccionIdioma();
	}

}
