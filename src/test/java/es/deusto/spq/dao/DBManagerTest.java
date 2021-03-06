package es.deusto.spq.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Main;
import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Cliente.Genero;

public class DBManagerTest {

	private HttpServer server;
    
	@Before 
    public void setUp() {
     // start the server
     server = Main.startServer();
     
    }
	
    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

	@Test
	public void testStoreObjectInDB(){
        Cliente cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario", Genero.MUJER, 48920, "usuario", "usuario");
        DBManager.getInstance().storeObjectInDB(cliente);
        assertEquals(cliente.toString(), DBManager.getInstance().getCliente(cliente.getDNI()).toString());
		DBManager.getInstance().deleteObjectFromDB(cliente);
	}

	@Test
	public void testStore() {
		Categoria cat = new Categoria("categoria", "categoria");
		Marca marca = new Marca("marca1", "marca1");
		SubCategoria subCategoria = new SubCategoria("Subcategoria1", "Subcategoria1", null);
		Producto p1 = new Producto("producto1", "producto1", 1, 1, "producto1", null, null);
		Cliente cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario", Genero.MUJER, 48920, "usuario", "usuario");

		Pedido ped1 = new Pedido(null, new Date(), "en proceso", 10, 1, "barcelona");
		HashMap<String,String> paypal = new HashMap<String,String>();
		paypal.put("usuario@gmail.com", "1234");
		HashMap<String,String> visa = new HashMap<String,String>();
		visa.put("4444333322221111", "1234");
		Pago pago1 = new Pago("12132", visa, paypal);

		DBManager.getInstance().store(cliente);
		assertEquals(cliente.toString(), DBManager.getInstance().getClientes().get(0).toString());
		DBManager.getInstance().deleteObjectFromDB(cliente);

		DBManager.getInstance().store(p1);
		assertEquals(p1.toString(), DBManager.getInstance().getProductos().get(0).toString());
		assertEquals(p1.toString(), DBManager.getInstance().getProducto(p1.getNombre()).toString());
		DBManager.getInstance().deleteObjectFromDB(p1);

		DBManager.getInstance().store(marca);
		assertEquals(marca.toString(), DBManager.getInstance().getMarcas().get(0).toString());
		DBManager.getInstance().deleteObjectFromDB(marca);

		DBManager.getInstance().store(subCategoria);
		assertEquals(subCategoria.toString(), DBManager.getInstance().getSubcategorias().get(0).toString());
		DBManager.getInstance().deleteObjectFromDB(subCategoria);

		DBManager.getInstance().store(cat);
		assertEquals(cat.toString(), DBManager.getInstance().getCategorias().get(0).toString());
		DBManager.getInstance().deleteObjectFromDB(cat);
		
		DBManager.getInstance().store(ped1);
		assertEquals(ped1.toString(), DBManager.getInstance().getPedidos().get(0).toString());
		assertEquals(ped1.getCantidad(), DBManager.getInstance().getPedido(ped1.getFecha()).getCantidad());
		DBManager.getInstance().deleteObjectFromDB(ped1);
		
		
		DBManager.getInstance().store(pago1);
		System.out.println();
		assertEquals(pago1.getDNI().toString(), DBManager.getInstance().getPago(cliente).getDNI().toString());
		assertEquals(pago1.getCredencialesPaypal().toString(), DBManager.getInstance().getPaypal(cliente).toString());
		assertEquals(pago1.getCredencialesVisa().toString(), DBManager.getInstance().getVisa(cliente).toString());
		DBManager.getInstance().deleteObjectFromDB(pago1);

	}

	@Test
	public void testUpdate() {
		Producto p1 = new Producto("producto1", "producto1", 1, 1, "producto1", null, null);
		Cliente cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario",
				Genero.MUJER, 48920, "usuario", "usuario");

		Pedido ped1 = new Pedido(null, new Date(), "en proceso", 10, 1, "barcelona");
		HashMap<String, String> paypal = new HashMap<String, String>();
		paypal.put("usuario@gmail.com", "1234");
		HashMap<String, String> visa = new HashMap<String, String>();
		visa.put("4444333322221111", "1234");
		Pago pago1 = new Pago("12132", visa, paypal);
		DBManager.getInstance().deleteCliente(cliente);
		
		DBManager.getInstance().updateCliente(cliente);
		assertEquals(cliente.toString(), DBManager.getInstance().getClientes().get(0).toString());
		DBManager.getInstance().deleteCliente(cliente);
		
		
		DBManager.getInstance().updatePedido(ped1);
		assertEquals(ped1.toString(), DBManager.getInstance().getPedidos().get(0).toString());
		DBManager.getInstance().deleteObjectFromDB(ped1);

		DBManager.getInstance().updateProducto(p1);
		assertEquals(p1.toString(), DBManager.getInstance().getProductos().get(0).toString());
		DBManager.getInstance().deleteObjectFromDB(p1);
		
		DBManager.getInstance().updatePago(pago1);
		assertEquals(pago1.getDNI().toString(), DBManager.getInstance().getPago(cliente).getDNI().toString());
		DBManager.getInstance().deleteObjectFromDB(pago1);
	}
	
	@Test
	public void testMasComprado() {
		Producto p1 = new Producto("producto1", "producto1", 1, 1, "producto1", null, null);
		Producto p2 = new Producto("producto2", "producto2", 1, 1, "producto2", null, null);
		Producto p3 = new Producto("producto3", "producto3", 1, 1, "producto3", null, null);
		Producto p4 = new Producto("producto4", "producto4", 1, 1, "producto4", null, null);
		Cliente cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario",
				Genero.MUJER, 48920, "usuario", "usuario");
		
		Pedido ped1 = new Pedido(cliente, new Date(1619342158), "estado",22 ,2, "barcelona");
		Pedido ped2 = new Pedido(cliente, new Date(1619342158), "estado",22 ,2, "barcelona");
		Pedido ped3 = new Pedido(cliente, new Date(1619342158), "estado",22 ,2, "barcelona");
		Pedido ped4 = new Pedido(cliente, new Date(1619342158), "estado",22 ,2, "barcelona");
		Pedido ped5 = new Pedido(cliente, new Date(1619342158), "estado",22 ,2, "barcelona");
		Pedido ped6 = new Pedido(cliente, new Date(1619342158), "estado",22 ,2, "barcelona");
		ArrayList<Producto> list = new ArrayList<Producto>();
		
		DBManager.getInstance().store(p1);
		DBManager.getInstance().store(p2);
		DBManager.getInstance().store(p3);
		DBManager.getInstance().store(p4);
		
		list.add(p2);
		ped1.setProducto(list);
		DBManager.getInstance().store(ped1);
		
		list.clear();
		list.add(p3);
		ped2.setProducto(list);
		ped3.setProducto(list);
		DBManager.getInstance().store(ped2);
		DBManager.getInstance().store(ped3);
		
		list.clear();
		list.add(p4);
		ped4.setProducto(list);
		ped5.setProducto(list);
		ped6.setProducto(list);
		DBManager.getInstance().store(ped4);
		DBManager.getInstance().store(ped5);
		DBManager.getInstance().store(ped6);
		
		ArrayList<Producto> res = DBManager.getInstance().getMasComprados();
		ArrayList<Producto> exp = new ArrayList<Producto>();
		
		exp.add(p4);
		exp.add(p3);
		exp.add(p2);
		exp.add(p1);
		
		assertEquals(exp.toString(), res.toString());
		DBManager.getInstance().deleteObjectFromDB(ped1);
		DBManager.getInstance().deleteObjectFromDB(ped2);
		DBManager.getInstance().deleteObjectFromDB(ped3);
		DBManager.getInstance().deleteObjectFromDB(ped4);
		DBManager.getInstance().deleteObjectFromDB(ped5);
		DBManager.getInstance().deleteObjectFromDB(ped6);
		DBManager.getInstance().deleteObjectFromDB(p1);
		DBManager.getInstance().deleteObjectFromDB(p2);
		DBManager.getInstance().deleteObjectFromDB(p3);
		DBManager.getInstance().deleteObjectFromDB(p4);
		DBManager.getInstance().deleteObjectFromDB(cliente);
	}
	@Test
	public void testServerVentanasPago() {
		// SERVER Y TARGETS
		Client client = ClientBuilder.newClient();
        WebTarget appTarget = client.target("http://localhost:8080/myapp");

		Cliente cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario",
		Genero.MUJER, 48920, "usuario", "usuario");

		HashMap<String,String> paypal = new HashMap<String,String>();
		paypal.put("usuario@gmail.com", "1234");
		HashMap<String,String> visa = new HashMap<String,String>();
		visa.put("4444333322221111", "1234");
		Pago credPago = new Pago("12132", visa, paypal);
		DBManager.getInstance().store(credPago);

		Pedido pedido = new Pedido(cliente, new Date(1619342158), "estado",22 ,2, "Munitibar");

		WebTarget pedidoTarget = appTarget.path("/pedidos");
		WebTarget updateTarget = appTarget.path("/pagos/update");

		//METODO CREAR PEDIDO (Se crea el pedido si las credenciales son correctas)
		pedidoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
		assertEquals(pedido.getFecha(), DBManager.getInstance().getPedido(pedido.getFecha()).getFecha());
		DBManager.getInstance().deleteObjectFromDB(DBManager.getInstance().getPedido(pedido.getFecha()));

		//METODO ACTUALIZAR PEDIDO (Se actualiza el pedido si se cambian credenciales)
		HashMap<String,String> paypal2 = new HashMap<String,String>();
		paypal.put("usuario@gmail.com", "111");
		HashMap<String,String> visa2 = new HashMap<String,String>();
		visa.put("4444333322221111", "111");
		credPago.setCredencialesPaypal(paypal2);
		credPago.setCredencialesVisa(visa2);

		updateTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(credPago, MediaType.APPLICATION_JSON));
		assertEquals(DBManager.getInstance().getPago(cliente).getDNI(), credPago.getDNI());
		DBManager.getInstance().deleteObjectFromDB(credPago);
	}
}