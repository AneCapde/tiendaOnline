package es.deusto.spq.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.models.Cliente.Genero;

public class PedidoTest {
	
	Pedido ped;
	Cliente cli;
	Producto pro;
	ArrayList<Producto> arr = new ArrayList<Producto>();
	
	@Before
	public void testPedido() {
		cli = new Cliente("12345F", "NomCliente", "ApCliente", "email", "pass", 9999999,"Direccion", Genero.HOMBRE, 234455, "provincia", "localidad");
		Categoria cat = new Categoria("nombreCat", "descCat");
		SubCategoria subca = new SubCategoria("nombreSub", "descSub", cat);
		Marca marc = new Marca("nombreMarc", "descMarc");
		pro = new Producto("producto", "descripcion", 2, 2, "img", subca, marc);
		arr.add(pro);
		ped = new Pedido(cli, new Date(1619342158), "estado",22 ,2, "barcelona");
	}

	@Test
	public void testGetCliente() {
		Cliente resul = ped.getCliente();
		assertEquals(cli, resul);
	}

	@Test
	public void testSetCliente() {
		ped.setCliente(cli);
		Cliente resul = ped.getCliente();
		assertEquals(cli, resul);
	}

	@Test
	public void testGetFecha() {
		Date resul = ped.getFecha();
		assertEquals(new Date(1619342158), resul);
	}

	@Test
	public void testSetFecha() {
		ped.setFecha(new Date(1619342157));
		Date resul = ped.getFecha();
		assertEquals(new Date(1619342157), resul);
	}

	@Test
	public void testGetEstado() {
		String resul = ped.getEstado();
		assertEquals("estado", resul);
	}

	@Test
	public void testSetEstado() {
		ped.setEstado("estado2");
		String resul = ped.getEstado();
		assertEquals("estado2", resul);
	}

	@Test
	public void testGetImporte() {
		int resul = ped.getImporte();
		assertEquals(22, resul);
	}

	@Test
	public void testSetImporte() {
		ped.setImporte(23);
		int resul = ped.getImporte();
		assertEquals(23, resul);
	}

	@Test
	public void testGetCantidad() {
		int resul = ped.getCantidad();
		assertEquals(2, resul);
	}

	@Test
	public void testSetCantidad() {
		ped.setCantidad(4);
		int resul = ped.getCantidad();
		assertEquals(4, resul);
	}
	
	@Test
	public void testGetLugar() {
		String resul = ped.getLugar();
		assertEquals("barcelona", resul);
	}

	@Test
	public void testSetLugar() {
		ped.setLugar("barcelona");
		String resul = ped.getLugar();
		assertEquals("barcelona", resul);
	}

	@Test
	public void testSetProducto() {
		cli = new Cliente("12345F", "NomCliente", "ApCliente", "email", "pass", 9999999,"Direccion", Genero.HOMBRE, 234455, "provincia", "localidad");
		Categoria cat = new Categoria("nombreCat", "descCat");
		SubCategoria subca = new SubCategoria("nombreSub", "descSub", cat);
		Marca marc = new Marca("nombreMarc", "descMarc");
		Producto pron = new Producto("producto2", "descripcion2", 2, 2, "img", subca, marc);
		ArrayList<Producto> productos = new ArrayList<Producto>();
		productos.add(pron);
		ped.setProducto(productos);
		ArrayList<Producto> resul = ped.getProducto();
		assertEquals(productos, resul);
	}

	@Test
	public void testToString() {
		String resul = ped.toString();
		assertEquals("Pedido [fecha=Mon Jan 19 18:49:02 CET 1970, estado=estado, importe=22, cantidad=2, cliente=NomCliente, producto=[]]", resul);	
	}

}
