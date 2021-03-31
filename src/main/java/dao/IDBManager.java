package dao;

import java.util.ArrayList;

import models.Categoria;
import models.Cliente;
import models.Marca;
import models.Pedido;
import models.Producto;
import models.SubCategoria;

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