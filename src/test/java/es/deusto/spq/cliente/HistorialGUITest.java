//package es.deusto.spq.cliente;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.ws.rs.client.WebTarget;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import es.deusto.spq.models.Cliente;
//import es.deusto.spq.models.Cliente.Genero;
//import es.deusto.spq.models.Pedido;
//
//@RunWith(MockitoJUnitRunner.class)
//public class HistorialGUITest {
//	
//	HistorialGUI historialGUI;
//	WebTarget web = Mockito.mock(WebTarget.class);
//	
//	@Mock
//	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
//
//	List<Pedido> pedidos = new ArrayList<Pedido>();
//	Pedido pedido;
//	
//	@Before
//	public void inicio() {
//		Cliente cli = new Cliente("12399345K", "usuario", "apellido apellido ", "usuario@gmail.com", "12345r", 655786943,
//				"direccion", Genero.MUJER, 45678, "provincia", "localidad");
//		
//		pedido = new Pedido(cli,new Date(1619342158), "estado",22 ,2,"lugar");
//		pedidos.add(pedido);
//	
//		historialGUI = new HistorialGUI(tiendaGUI,web);
//		
//	}
//	
//	@Test
//	public void testCargarLista() {
//		List<Pedido> pedidos = new ArrayList<>();
//		pedidos.add(pedido);
//		assertEquals(pedidos, historialGUI);
//	}
//	
//
//}
