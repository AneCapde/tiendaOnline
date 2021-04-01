package dao;

import java.util.ArrayList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Extent;

import models.Categoria;
import models.Cliente;
import models.Marca;
import models.Pedido;
import models.Producto;
import models.SubCategoria;

public class DBManager implements IDBManager{
    private static IDBManager instance;
	private PersistenceManagerFactory pmf = null;
	
	public DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");		
	}
	
	public static IDBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}		
		return instance;
	}

    @Override
	public void storeObjectInDB(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("  * Storing an object: " + object);
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error storing an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}
    @Override
	public void store(Cliente cliente) {
		DBManager.getInstance().storeObjectInDB(cliente);	
	}
	@Override
	public void store(Pedido pedido) {
		DBManager.getInstance().storeObjectInDB(pedido);	
	}
    @Override
	public void store(Producto producto) {
		DBManager.getInstance().storeObjectInDB(producto);	
	}
    @Override
	public void store(Categoria categoria) {
		DBManager.getInstance().storeObjectInDB(categoria);	
	}
    @Override
	public void store(SubCategoria subCategoria) {
		DBManager.getInstance().storeObjectInDB(subCategoria);	
	}
    @Override
	public void store(Marca marca) {
		DBManager.getInstance().storeObjectInDB(marca);	
	}

    @Override
	public ArrayList<Cliente> getClientes() {
		ArrayList<Cliente> clientes = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		try {
			System.out.println("  * Retrieving all the Clientes");
			tx.begin();
			Extent<Cliente> extent = pm.getExtent(Cliente.class, true);
			for (Cliente cliente : extent) {
				clientes.add(cliente);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Cliente: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
        }
		return clientes;		
	}

    @Override
	public ArrayList<Pedido> getPedidos() {
		ArrayList<Pedido> pedidos = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		try {
			System.out.println("  * Retrieving all the Pedidos");

			tx.begin();

			Extent<Pedido> extent = pm.getExtent(Pedido.class, true);
			for (Pedido pedido : extent) {
				pedidos.add(pedido);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Pedidos: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
        }
		return pedidos;		
	}
    @Override
	public ArrayList<Producto> getProductos() {
		ArrayList<Producto> productos = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		try {
			System.out.println("  * Retrieving all the Productos");
			tx.begin();
			Extent<Producto> extent = pm.getExtent(Producto.class, true);
			for (Producto producto : extent) {
				productos.add(producto);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Productos: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
        }
		return productos;		
	}
    @Override
	public ArrayList<Categoria> getCategorias() {
		ArrayList<Categoria> categorias = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		try {
			System.out.println("  * Retrieving all the Categorias");

			tx.begin();

			Extent<Categoria> extent = pm.getExtent(Categoria.class, true);
			for (Categoria categoria : extent) {
				categorias.add(categoria);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Categorias: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
        }
		return categorias;		
	}
    @Override
	public ArrayList<Marca> getMarcas() {
		ArrayList<Marca> marcas = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		try {
			System.out.println("  * Retrieving all the Marcas");

			tx.begin();

			Extent<Marca> extent = pm.getExtent(Marca.class, true);
			for (Marca marca : extent) {
				marcas.add(marca);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Marcas: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
        }
		return marcas;		
	}
    @Override
	public ArrayList<SubCategoria> getSubcategorias() {
		ArrayList<SubCategoria> subCategorias = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		try {
			System.out.println("  * Retrieving all the Subcategorias");

			tx.begin();

			Extent<SubCategoria> extent = pm.getExtent(SubCategoria.class, true);
			for (SubCategoria subCategoria : extent) {
				subCategorias.add(subCategoria);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Subcategorias: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
        }
		return subCategorias;		
	}
    @Override
	public void updateCliente(Cliente cliente) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(cliente);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
    @Override
	public void updateProducto(Producto producto) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(producto);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
    @Override
	public void updatePedido(Pedido pedido) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(pedido);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
}