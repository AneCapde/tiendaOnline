package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
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
import es.deusto.spq.models.Pedido;

@RunWith(MockitoJUnitRunner.class)
public class HistorialGUITest {
	
	HistorialGUI historialGUI;
	WebTarget web = Mockito.mock(WebTarget.class);
	
	@Mock
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);

	List<Pedido> pedidos = new ArrayList<Pedido>();
	Pedido pedido;
	
	@Ignore
	@Before
	public void inicio() {
		Cliente cli = new Cliente("12399345K", "usuario", "apellido apellido ", "usuario@gmail.com", "12345r", 655786943,
				"direccion", Genero.MUJER, 45678, "provincia", "localidad");
		
		pedido = new Pedido(cli,new Date(1619342158), "estado",22 ,2,"lugar");
		pedidos.add(pedido);
	
		historialGUI = new HistorialGUI(tiendaGUI,web);
		
	}
	
	@Ignore
	@Test
	public void testDevolverPedido() {
		pedido.setEstado("Devuelto");

		// Aquí se prueba el método, el guardado en base de datos se prueba en DBManagerTest
		assertEquals(pedido.getEstado(), "Devuelto");
		if (pedido.getEstado().equals("Devuelto")) {
			JOptionPane.showMessageDialog(null, "Deberás concretar la entrega del paquete escibiendo al email \n devolucion@tienda.com y se te reembolsará el dinero del pedido.", "Devolución completada", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	
	@Ignore
	@Test
	public void testCargarLista() {
		List<Pedido> pedidos = new ArrayList<>();
		pedidos.add(pedido);
		assertEquals(pedidos, historialGUI);
	}
	

}
