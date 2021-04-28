package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;
import org.mockito.*;

import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
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

	List<Producto> productos = new ArrayList<Producto>();

	Producto p1;

	@Ignore
	@Before
	public void inicio(){
		Cliente cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario", Genero.MUJER, 48920, "usuario", "usuario");
		p1 = new Producto("producto1", "producto1", 1, 1, "producto1", 
		new SubCategoria("Subcategoria1", "Subcategoria1", new Categoria("categoria1", "categoria1")), new Marca("marca1", "marca1"));
		productos.add(p1);
		
		when(tiendaGUI.getCliente()).thenReturn(cliente);
//		doReturn(true).when(cliente).getNombre();
//		doReturn(tiendaGUI.getCliente()).when().thenReturn(true);
		listaDeseadosGUI = new ListaDeseadosGUI(tiendaGUI, web);
		listaDeseadosGUI.setProductosDeseados(productos);
	}
	
	@Ignore
	@Test
	public void testGetProductosDeseados() {
		List<Producto> productos1 = new ArrayList<>();
		productos1.add(p1);
		assertEquals(productos1, listaDeseadosGUI.getProductosDeseados());
	}
	
	@Ignore
	@Test
	public void testSetProductosDeseados() {
		ArrayList<Producto> productos1 = new ArrayList<>();
		productos1.add(p1);
		TiendaGUI.getCliente().setProductosDeseados(productos1);
		ArrayList<Producto> res = TiendaGUI.getCliente().getProductosDeseados();
		assertEquals(productos1, res);
	}
	
	@Ignore
	@Test
	public void updateClient() {
		ListaDeseadosGUI cGui = Mockito.mock(ListaDeseadosGUI.class);
		verify(cGui).updateClient(web);
	}
	
	@Ignore
	@Test 
	public void eliminar() {
		
	}
	
	@Ignore
	@Test
	public void anyadir() {
		
	}
	
	
}
