package es.deusto.spq.models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.models.Cliente.Genero;
import es.deusto.spq.server.ProductosServer;

public class ClienteTest {
	Cliente cli;
	ArrayList<Producto> prod;
	
	
	@Before
	public void initiali(){
		cli = new Cliente("12345F", "NomCliente", "ApCliente", "email", "pass", 9999999,"Direccion", Genero.HOMBRE, 234455, "provincia", "localidad");
		Categoria cat = new Categoria("nombreCat", "descCat");
		SubCategoria subca = new SubCategoria("nombreSub", "descSub", cat);
		Marca marc = new Marca("nombreMarc", "descMarc");
		Producto pro = new Producto("producto", "descripcion", 2, 2, "img", subca, marc);
		prod = new ArrayList<Producto>();
		prod.add(pro);
		cli.setProductosDeseados(prod);
	}

	@Test
	public void testGetDNI() {
		String resul = cli.getDNI();
		assertEquals("12345F", resul);
	}

	@Test
	public void testSetDNI() {
		cli.setDNI("12345G");
		String resul = cli.getDNI();
		assertEquals("12345G", resul);
	}

	@Test
	public void testGetNombre() {
		String resul = cli.getNombre();
		assertEquals("NomCliente", resul);
	}

	@Test
	public void testSetNombre() {
		cli.setNombre("NomCliente2");
		String resul = cli.getNombre();
		assertEquals("NomCliente2", resul);
	}

	@Test
	public void testGetApellidos() {
		String resul = cli.getApellidos();
		assertEquals("ApCliente", resul);
	}

	@Test
	public void testSetApellidos() {
		cli.setApellidos("ApCliente2");
		String resul = cli.getApellidos();
		assertEquals("ApCliente2", resul);
	}

	@Test
	public void testGetEmail() {
		String resul = cli.getEmail();
		assertEquals("email", resul);
	}

	@Test
	public void testSetEmail() {
		cli.setEmail("email2");
		String resul = cli.getEmail();
		assertEquals("email2", resul);
	}

	@Test
	public void testGetPassword() {
		String resul = cli.getPassword();
		assertEquals("pass", resul);
	}

	@Test
	public void testSetPassword() {
		cli.setPassword("pass2");
		String resul = cli.getPassword();
		assertEquals("pass2", resul);
	}

	@Test
	public void testGetTelefono() {
		int resul = cli.getTelefono();
		assertEquals(9999999, resul);
	}

	@Test
	public void testSetTelefono() {
		cli.setTelefono(99999992);
		int resul = cli.getTelefono();
		assertEquals(99999992, resul);
	}

	@Test
	public void testGetDireccion() {
		String resul = cli.getDireccion();
		assertEquals("Direccion", resul);
	}

	@Test
	public void testSetDireccion() {
		cli.setDireccion("Direccion2");
		String resul = cli.getDireccion();
		assertEquals("Direccion2", resul);
	}

	@Test
	public void testGetGenero() {
		Genero resul = cli.getGenero();
		assertEquals(Genero.HOMBRE, resul);
	}

	@Test
	public void testSetGenero() {
		cli.setGenero(Genero.NO_BINARIO);
		Genero resul = cli.getGenero();
		assertEquals(Genero.NO_BINARIO, resul);
	}

	@Test
	public void testGetCod_postal() {
		int resul = cli.getCod_postal();
		assertEquals(234455, resul);
	}

	@Test
	public void testSetCod_postal() {
		cli.setCod_postal(2344552);
		int resul = cli.getCod_postal();
		assertEquals(2344552, resul);
	}

	@Test
	public void testGetProvincia() {
		String resul = cli.getProvincia();
		assertEquals("provincia", resul);
	}

	@Test
	public void testSetProvincia() {
		cli.setProvincia("provincia2");
		String resul = cli.getProvincia();
		assertEquals("provincia2", resul);
	}

	@Test
	public void testGetLocalidad() {
		String resul = cli.getLocalidad();
		assertEquals("localidad", resul);
	}

	@Test
	public void testSetLocalidad() {
		cli.setLocalidad("localidad2");
		String resul = cli.getLocalidad();
		assertEquals("localidad2", resul);
	}

	@Test
	public void testGetProductosDeseados() {
		ArrayList<Producto> res = cli.getProductosDeseados();
		assertEquals(prod, res);
		
	}

	@Test
	public void testRemoveProducto() {
		
	}

}
