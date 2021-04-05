package mainCliente;

import java.util.ArrayList;

import Ventanas.TiendaGUI;
import dao.DBManager;
import models.Categoria;
import models.Marca;
import models.Producto;
import models.SubCategoria;

public class MainCliente {
	
	public static void main(String[] args) {
		ArrayList<Marca> listaMarcas = DBManager.getInstance().getMarcas();
		ArrayList<Producto> listaProductos = DBManager.getInstance().getProductos();
		ArrayList<Categoria> listaCategoria = DBManager.getInstance().getCategorias();
		ArrayList<SubCategoria> listaSubcategorias = DBManager.getInstance().getSubcategorias();
		new TiendaGUI(listaProductos, listaCategoria, listaSubcategorias, listaMarcas);
	}

}
