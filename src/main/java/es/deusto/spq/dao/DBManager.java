package es.deusto.spq.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;

import javax.jdo.Extent;

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
	 public void store(Pago pago) {
	 	DBManager.getInstance().storeObjectInDB(pago);	
	 }
	 /**
     * Devuelve todos los clientes almacenados en la base de datos.
     * 
     */
	 
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

    /**
     * Devuelve todos los pedidos almacenados en la base de datos.
     */
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
    
    /**
     * Devuelve todos los productos almacenados en la base de datos.
     */
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
    
    /**
     * Devuelve todas las categorias almacenadas en la base de datos.
     */
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
    
    /**
     * Devuelve todas las marcas almacenadas en la base de datos.
     */
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
    
    /**
     * Devuelve todas las subcategorias almacenadas en la base de datos.
     */
    @Override
	public ArrayList<SubCategoria> getSubcategorias() {
		ArrayList<SubCategoria> subcategorias = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		try {
			System.out.println("  * Retrieving all the Subcategoria");

			tx.begin();
			Extent<SubCategoria> extent = pm.getExtent(SubCategoria.class, true);
			for (SubCategoria subcategoria : extent) {
				subcategorias.add(subcategoria);
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
		return subcategorias;			
	}
    /**
     * Devuelve el cliente con el DNI introducido.
     * @DNI Dni del cliente que quieres buscar.
     */
	@Override
	public Cliente getCliente(String DNI) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Cliente cliente = null; 
		try {
			System.out.println("  * Querying a Cliente : " + DNI);
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Cliente.class.getName() + " WHERE DNI == '" + DNI + "'");
			query.setUnique(true);
			cliente = (Cliente) query.execute();
			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying a Cliente: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return cliente;
	}
	
	/**
	 * Devuelve los pedidos hechos en la fecha introducida.
	 * @fecha fecha en la que quieres buscar los pedidos.
	 */
	@Override
	public Pedido getPedido(Date fecha) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Pedido pedido = null; 
		try {
			System.out.println("  * Querying a Pedido : " + fecha);
			tx.begin();

			Extent<Pedido> extent = pm.getExtent(Pedido.class, true);
            for (Pedido p : extent) {
                if (p.getFecha().equals(fecha)){
                    pedido = p;
                }
            }

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying Pedido: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return pedido;
	}

	
	/**
	 * Conseguir los productos con el nombre introducido.
	 * @nombre Nombre por el que quieres buscar.
	 */
	@Override
	public Producto getProducto(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Producto producto = null; 
		try {
			System.out.println("  * Querying a Producto : " + nombre);
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Producto.class.getName() + " WHERE nombre == '" + nombre + "'");
			query.setUnique(true);
			producto = (Producto) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying a Producto: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return producto;
	}
	
	/**
	 * Devuelve un HashMap con el email y la contraseña de PayPal
	 * @cliente Cliente por el que quieres buscar
	 */
	
	@Override
	public HashMap<String, String> getPaypal(Cliente cliente) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Pago pago = null; 
		HashMap<String, String> paypal = null;
		try {
			System.out.println("  * Querying Paypal account : " + cliente.getDNI());
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Pago.class.getName() + " WHERE DNI == '" + cliente.getDNI() + "'");
			query.setUnique(true);
			pago = (Pago) query.execute();
			paypal = pago.getCredencialesPaypal();

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying paypal account: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return paypal;
	}

	/**
	 * Devuelve un HashMap con el numero de tarjeta y la contraseña de la visa
	 * @cliente Cliente por el que quieres buscar
	 */
	@Override
	public HashMap<String, String> getVisa(Cliente cliente) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Pago pago = null; 
		HashMap<String, String> visa = null;
		try {
			System.out.println("  * Querying Visa account : " + cliente.getDNI());
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Pago.class.getName() + " WHERE DNI == '" + cliente.getDNI() + "'");
			query.setUnique(true);
			pago = (Pago) query.execute();
			visa = pago.getCredencialesVisa();

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying Visa account: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return visa;
	}

	/**
	 * Devuelve el pago hecho por el cliente introducido.
	 * @cliente Cliente por el que quieres buscar.
	 */
	@Override
	public Pago getPago(Cliente cliente) {
	PersistenceManager pm = pmf.getPersistenceManager();
	pm.getFetchPlan().setMaxFetchDepth(4);
	Transaction tx = pm.currentTransaction();
	Pago pago = null; 
	try {
		System.out.println("  * Querying Pago : " + cliente.getDNI());
		tx.begin();

		Query<?> query = pm.newQuery("SELECT FROM " + Pago.class.getName() + " WHERE DNI == '" + cliente.getDNI() + "'");
		query.setUnique(true);
		pago = (Pago) query.execute();

		tx.commit();
	} catch (Exception ex) {
		System.out.println(" $ Error querying pago: " + ex.getMessage());
	} finally {
		if (tx != null && tx.isActive()) {
			tx.rollback();
		}
		pm.close();
	}
	return pago;
	}
	
	/**
	 * Busca los elementos más pedidos y los devuelve en un arraylist.
	 */
	@Override
	public ArrayList<Producto> getMasComprados() {
		
		ArrayList<Producto> productos = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		
		try {
			
			tx.begin();
			Query<?> query = pm.newQuery("javax.jdo.query.SQL", "SELECT NOMBRE FROM producto LEFT OUTER JOIN pedido_productopedido ON producto.PRODUCTO_ID" + 
										 " = pedido_productopedido.PRODUCTO_ID_EID GROUP BY producto.NOMBRE order by count(PEDIDO_ID_OID) desc ;");

			Producto prod = null;
			@SuppressWarnings("rawtypes")
			List productosBusque = (List) query.execute();
			
			for (int i = 0; i < productosBusque.size(); i++) {
				Query<?> query2 = pm.newQuery("SELECT FROM "+ Producto.class.getName() + " WHERE nombre =='" + productosBusque.get(i) + "'");
				query2.setUnique(true);
				prod = (Producto) query2.execute();
				productos.add(prod);
				System.out.println(prod.toStringDebug());
			}
//			
			System.out.println(productos);
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
    
	/**
	 * Actualiza el cliente en la base de datos.
	 * @cliente Cliente que quieres actualizar con los nuevos datos.
	 */
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
    /**
	 * Actualiza el producto en la base de datos.
	 * @producto Producto que quieres actualizar con los nuevos datos.
	 */
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
    
    /**
	 * Actualiza el pedido en la base de datos.
	 * @pedido Pedido que quieres actualizar con los nuevos datos.
	 */
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
    
    /**
	 * Actualiza el pago en la base de datos.
	 * @pago Pago que quieres actualizar con los nuevos datos.
	 */
	 @Override
	 public void updatePago(Pago pago) {
	 	PersistenceManager pm = pmf.getPersistenceManager();
	 	Transaction tx = pm.currentTransaction();
	 	try {
	 		tx.begin();
	 		pm.makePersistent(pago);
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
	 
    /**
	 * Borra un cliente en la base de datos.
	 * @cliente Cliente que quieres borrar.
	 */
    @Override
	public void deleteCliente(Cliente cliente) {
    	System.out.println("- Cleaning the Cliente from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Cliente.class.getName() + " WHERE DNI == '" + cliente.DNI + "'");
			System.out.println(" * '" + query.deletePersistentAll() + "' cliente deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying a Cliente: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}

	public void deleteObjectFromDB(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println(" * Delete an object: " + object);
			
			pm.deletePersistent(object);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error deleting an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
}