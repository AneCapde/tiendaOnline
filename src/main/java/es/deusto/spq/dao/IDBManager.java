package es.deusto.spq.dao;

import java.util.ArrayList;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;

public interface IDBManager {
    public void storeObjectInDB(Object object);
	public void store(Cliente cliente);
	public void store(Producto producto);
    public void store(Pedido aeropuerto);
    public void store(Categoria categoria);
    public void store(SubCategoria subCategoria);
    public void store(Marca marca);
    public ArrayList<Cliente> getClientes();
    public ArrayList<Pedido> getPedidos();
    public ArrayList<Producto> getProductos();
    public ArrayList<Marca> getMarcas();
    public ArrayList<Categoria> getCategorias();
    public ArrayList<SubCategoria> getSubcategorias();
    public void updateCliente(Cliente cliente);
    public void updateProducto(Producto producto);
    public void updatePedido(Pedido pedido);
}