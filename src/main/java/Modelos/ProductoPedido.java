package Modelos;

import javax.jdo.annotations.ForeignKey;

public class ProductoPedido {

	@ForeignKey
	private int id_pedido;
	@ForeignKey
	private int id_producto;
	
	public int getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	
	
}
