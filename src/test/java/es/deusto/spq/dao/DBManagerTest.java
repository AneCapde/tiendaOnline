package es.deusto.spq.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Cliente.Genero;

public class DBManagerTest {
    
	@Ignore
	@Test
	public void testStoreObjectInDB(){
        Cliente cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario", Genero.MUJER, 48920, "usuario", "usuario");
        DBManager.getInstance().storeObjectInDB(cliente);
        assertEquals(cliente.toString(), DBManager.getInstance().getCliente(cliente.getDNI()).toString());
		DBManager.getInstance().deleteObjectFromDB(cliente);
	}
	@Ignore
	@Test
	public void testStore() {
		Categoria cat = new Categoria("categoria", "categoria");
		Marca marca = new Marca("marca1", "marca1");
		SubCategoria subCategoria = new SubCategoria("Subcategoria1", "Subcategoria1", null);
		Producto p1 = new Producto("producto1", "producto1", 1, 1, "producto1", null, null);
		Cliente cliente = new Cliente("12132", "usuario", "usuario", "usuario", "usuario", 1213124, "usuario", Genero.MUJER, 48920, "usuario", "usuario");
		Pedido ped1 = new Pedido(null, new Date(), "en proceso", 10, 1);

		DBManager.getInstance().store(ped1);
		assertEquals(ped1.toString(), DBManager.getInstance().getPedidos().get(0).toString());
		DBManager.getInstance().deleteObjectFromDB(ped1);

		DBManager.getInstance().store(cliente);
		assertEquals(cliente.toString(), DBManager.getInstance().getClientes().get(0).toString());
		DBManager.getInstance().deleteObjectFromDB(cliente);

		DBManager.getInstance().store(p1);
		assertEquals(p1.toString(), DBManager.getInstance().getProductos().get(0).toString());
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


	}
}
