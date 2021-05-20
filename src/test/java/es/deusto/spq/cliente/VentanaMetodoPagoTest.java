package es.deusto.spq.cliente;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;

@RunWith(MockitoJUnitRunner.class)
public class VentanaMetodoPagoTest {

	VentanaMetodoPago ventanaMetodoPago;
	WebTarget web = Mockito.mock(WebTarget.class);
	
	@Mock
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	Producto p1;
	Pedido ped1;

	/**
	 * Este método se ejecuta antes de que se ejecute cualquier método de prueba JUnit: se anota con la anotación Before.
	*/
	@Before
	public void inicio(){
		
		p1 = new Producto("producto1", "producto1", 1, 1, "producto1",
				new SubCategoria("Subcategoria1", "Subcategoria1", new Categoria("categoria1", "categoria1")),
				new Marca("marca1", "marca1"));
		productos.add(p1);
		ped1 = new Pedido(null, new Date(), "en proceso", 10, 1, "barcelona");
		ventanaMetodoPago = new VentanaMetodoPago(tiendaGUI, ped1, web);
	}
	
	@Ignore
	@Test
	public void testPaypal() {
		ventanaMetodoPago.paypal(tiendaGUI, ped1, web);
	}
	
	@Ignore
	@Test
	public void testVisa() {
		ventanaMetodoPago.visa(tiendaGUI, ped1, web);
	}
}
