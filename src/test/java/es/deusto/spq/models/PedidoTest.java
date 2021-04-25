package es.deusto.spq.models;

import static org.junit.Assert.*;

import java.util.Date;

import javax.print.attribute.standard.PDLOverrideSupported;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.models.Cliente.Genero;

public class PedidoTest {
	
	Pedido ped;
	Cliente cli;
	Producto pro;
	
	@Before
	public void testPedido() {
		cli = new Cliente("12345F", "NomCliente", "ApCliente", "email", "pass", 9999999,"Direccion", Genero.HOMBRE, 234455, "provincia", "localidad");
		Categoria cat = new Categoria("nombreCat", "descCat");
		SubCategoria subca = new SubCategoria("nombreSub", "descSub", cat);
		Marca marc = new Marca("nombreMarc", "descMarc");
		pro = new Producto("producto", "descripcion", 2, 2, "img", subca, marc);
		ped = new Pedido(cli, new Date(1619342158), "estado",22 ,2);
	}

	@Test
	public void testGetCliente() {
		Cliente resul = ped.getCliente();
		assertEquals(cli, resul);
	}

	@Test
	public void testSetCliente() {
		
		
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
	public void testGetProducto() {
		//Producto resul = ped.getProducto();
		//assertEquals(pro, resul);
	}

	@Test
	public void testSetProducto() {
		cli = new Cliente("12345F", "NomCliente", "ApCliente", "email", "pass", 9999999,"Direccion", Genero.HOMBRE, 234455, "provincia", "localidad");
		Categoria cat = new Categoria("nombreCat", "descCat");
		SubCategoria subca = new SubCategoria("nombreSub", "descSub", cat);
		Marca marc = new Marca("nombreMarc", "descMarc");
		Producto pron = new Producto("producto2", "descripcion2", 2, 2, "img", subca, marc);
		//ped.setProducto(pron);
		//Producto resul = ped.getProducto();
		//assertEquals(pron, resul);
	}

	@Test
	public void testToString() {
		String resul = ped.toString();
		assertEquals("Pedido [fecha=Mon Jan 19 18:49:02 CET 1970, estado=estado, importe=22, cantidad=2, cliente=es.deusto.spq.models.Cliente@5fcfe4b2, producto=producto]", resul);	
	}

}
