package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.ws.rs.client.WebTarget;


import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;


public class CestaGUITest {

    CestaGUI cestaGUI;
	WebTarget web = Mockito.mock(WebTarget.class);
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);

	List<Producto> productos = new ArrayList<Producto>();

	Producto p1;

	@Before
	public void inio(){
		p1 = new Producto("producto1", "producto1", 1, 1, "producto1", 
		new SubCategoria("Subcategoria1", "Subcategoria1", new Categoria("categoria1", "categoria1")), new Marca("marca1", "marca1"));
		productos.add(p1);
		cestaGUI = new CestaGUI(tiendaGUI, productos, web);
	}
	@Test
	public void testGetProductos() {
		List<Producto> productos1 = new ArrayList<>();
		productos1.add(p1);
		assertEquals(productos1, cestaGUI.getProductos());
	}
	@Test
	public void testGetProductosCantidad(){
		HashMap<Producto,Integer> productosCantidad = new HashMap<>();
		productosCantidad.put(p1, 1);
		assertEquals(productosCantidad, cestaGUI.getProductosCantidad());
	}
	@Test
    public void testCalcularPrecio(){
		Producto p2 = new Producto("producto2", "producto2", 1, 5, "producto2", 
		new SubCategoria("Subcategoria2", "Subcategoria2", new Categoria("categoria2", "categoria2")), new Marca("marca2", "marca2"));
		cestaGUI.getProductosCantidad().put(p2, 1);
		HashMap<Producto,Integer> productosCantidad = new HashMap<>();
		productosCantidad.put(p1, 1);
		productosCantidad.put(p2, 1);
		int precioTotal = 0;
		for (Producto p : productosCantidad.keySet()){
			precioTotal += p.getPrecio()*productosCantidad.get(p);
		}
		assertEquals(precioTotal, cestaGUI.calcularPrecio());
	}
    public void testCreatePedido(){
		CestaGUI cGui = Mockito.mock(CestaGUI.class);
		verify(cGui).createPedido();
	}
}