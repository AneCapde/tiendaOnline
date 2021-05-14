package es.deusto.spq.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Cliente.Genero;

public class DBManagerTest {
    
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
<<<<<<< HEAD
		Pedido ped1 = new Pedido(null, new Date(), "en proceso", 10, 1, "barcelona");
=======
		Pedido ped1 = new Pedido(null, new Date(), "en proceso", 10, 1, "");
>>>>>>> branch 'master' of https://github.com/AneCapde/tiendaOnline.git
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
<<<<<<< HEAD
		Pedido ped1 = new Pedido(null, new Date(), "en proceso", 10, 1, "barcelona");
=======
		Pedido ped1 = new Pedido(null, new Date(), "en proceso", 10, 1, "");
>>>>>>> branch 'master' of https://github.com/AneCapde/tiendaOnline.git
		HashMap<String, String> paypal = new HashMap<String, String>();
		paypal.put("usuario@gmail.com", "1234");
		HashMap<String, String> visa = new HashMap<String, String>();
		visa.put("4444333322221111", "1234");
		Pago pago1 = new Pago("12132", visa, paypal);
		
		DBManager.getInstance().updateCliente(cliente);
		DBManager.getInstance().deleteCliente(cliente);
		
		DBManager.getInstance().updatePedido(ped1);
		DBManager.getInstance().deleteObjectFromDB(ped1);

		DBManager.getInstance().updateProducto(p1);
		DBManager.getInstance().deleteObjectFromDB(p1);
		
		DBManager.getInstance().updatePago(pago1);
		DBManager.getInstance().deleteObjectFromDB(pago1);
	}
}