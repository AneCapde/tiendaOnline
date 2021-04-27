package es.deusto.spq.cliente;

import java.util.HashMap;
import java.util.List;

import es.deusto.spq.models.Producto;

public interface ICesta {

    public int calcularPrecio();
    public void createPedido();
    public List<Producto> getProductos();
    public HashMap<Producto, Integer> getProductosCantidad();

}