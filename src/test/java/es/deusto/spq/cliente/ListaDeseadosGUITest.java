package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;
import org.mockito.*;

import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import org.mockito.Mock;

import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Cliente.Genero;

@RunWith(MockitoJUnitRunner.class)
public class ListaDeseadosGUITest {

	ListaDeseadosGUI listaDeseadosGUI;
	WebTarget web = Mockito.mock(WebTarget.class);
	
	@Mock
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);

	ArrayList<Producto> productos = new ArrayList<Producto>();

	Producto p1, p2;
	Cliente cliente;

	
	@Before
	public void inicio(){
		cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario", Genero.MUJER, 48920, "usuario", "usuario");
		p1 = new Producto("producto1", "producto1", 1, 1, "producto1", 
		new SubCategoria("Subcategoria1", "Subcategoria1", new Categoria("categoria1", "categoria1")), new Marca("marca1", "marca1"));
		productos.add(p1);
		
		try(MockedStatic<TiendaGUI> utilities = Mockito.mockStatic(TiendaGUI.class)){
			utilities.when(TiendaGUI::getCliente).thenReturn(cliente);
//			doReturn(productos).when(TiendaGUI::getCliente).getProductosDeseados();
//			utilities.when(TiendaGUI::getProductosDeseados).thenReturn(productos);
		}
//		when(tiendaGUI.getCliente()).thenReturn(cliente);
//		when(tiendaGUI.getCliente().getProductosDeseados()).thenReturn(productos);
		
//		doReturn(tiendaGUI.getCliente()).when().thenReturn(true);
		listaDeseadosGUI = new ListaDeseadosGUI(tiendaGUI, web);
		listaDeseadosGUI.setProductosDeseados(productos);
	}
	
	@Ignore
	@Test
	public void updateClient() {
		ListaDeseadosGUI cGui = Mockito.mock(ListaDeseadosGUI.class);
		verify(cGui).updateClient(web);
	}
	
	@Test 
	public void eliminar() {
		Producto producto = null;
		for (Producto p : productos){
			if (p1.getNombre().equals(p.getNombre())) {
				producto = p;
			}
		}
		 ListaDeseadosGUI.getProductosDeseados().remove(producto);
		 assertEquals(productos, listaDeseadosGUI.eliminar());
	}
	
	
	@Test
	public void anyadir() {
		p2 = new Producto("producto2", "producto2", 1, 1, "producto2", 
				new SubCategoria("Subcategoria2", "Subcategoria2", new Categoria("categoria2", "categoria2")), new Marca("marca2", "marca2"));
		if(!productos.contains(p2)){
			productos.add(p2);
		}
		assertEquals(productos, listaDeseadosGUI.anyadir());
	}
	
	@Ignore
	@Test
	public void setCliente(){
		
		assertEquals(cliente, ListaDeseadosGUI.setCliente(cliente));
	}
	
	@Ignore
	@Test
	public void anyadirProductosDeseados() {
		ArrayList<Producto> productos1 = new ArrayList<>();
		productos1.add(p1);
		listaDeseadosGUI.setProductosDeseados(productos1);
		ArrayList<Producto> res = ListaDeseadosGUI.getProductosDeseados();
		for(Producto p : res) {
			ListaDeseadosGUI.getProductosDeseados().add(p);
		}
		
		assertEquals(productos1, ListaDeseadosGUI.anyadirProductosDeseados());
	}
	
	@Test
	public void testGetProductosDeseados() {
		ArrayList<Producto> productos1 = new ArrayList<>();
		productos1.add(p1);
		assertEquals(productos1, ListaDeseadosGUI.getProductosDeseados());
	}
	
	
	@Test
	public void testSetProductosDeseados() {
		ArrayList<Producto> productos1 = new ArrayList<>();
		productos1.add(p1);
		listaDeseadosGUI.setProductosDeseados(productos1);
		ArrayList<Producto> res = ListaDeseadosGUI.getProductosDeseados();
		assertEquals(productos1, res);
	}
}
