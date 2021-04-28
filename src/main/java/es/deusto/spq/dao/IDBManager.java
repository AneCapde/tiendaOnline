package es.deusto.spq.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;

public interface IDBManager {
    public void storeObjectInDB(Object object);
	public void store(Cliente cliente);
	public void store(Producto producto);
    public void store(Pedido pedido);
    public void store(Categoria categoria);
    public void store(SubCategoria subCategoria);
    public void store(Marca marca);
    public void store (Pago pago);
    public ArrayList<Cliente> getClientes();
    public ArrayList<Pedido> getPedidos();
    public ArrayList<Producto> getProductos();
    public ArrayList<Marca> getMarcas();
    public ArrayList<Categoria> getCategorias();
    public ArrayList<SubCategoria> getSubcategorias();
    public Cliente getCliente(String DNI);
    public Pedido getPedido(Date fecha);
    public Producto getProducto(String nombre);
    public Pago getPago(Cliente cliente);
    public HashMap<String, String> getPaypal(Cliente cliente);
    public HashMap<String, String> getVisa(Cliente cliente);
    public void updateCliente(Cliente cliente);
    public void updateProducto(Producto producto);
    public void updatePedido(Pedido pedido);
    public void updatePago(Pago pago);
    // public void deletePago(Pago pago);
    public void deleteCliente(Cliente cliente);
    public void deleteObjectFromDB(Object object);
}