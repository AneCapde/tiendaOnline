package com.example.mainCliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import Ventanas.TiendaGUI;
import dao.DBManager;
import models.Categoria;
import models.Marca;
import models.Producto;
import models.SubCategoria;

public class MainCliente {
	
	public static void main(String[] args) {
		ArrayList<Marca> listaMarcas = DBManager.getInstance().getMarcas();
//		limpiadorTemporal(listaMarcas);
		ArrayList<Producto> listaProductos = DBManager.getInstance().getProductos();
		ArrayList<Categoria> listaCategoria = DBManager.getInstance().getCategorias();
		System.out.println(listaCategoria);
		ArrayList<SubCategoria> listaSubcategorias = DBManager.getInstance().getSubcategorias();
		System.out.println(listaSubcategorias.get(1).toStringDebug() + "Hooooooolaaaaaaa");
		TiendaGUI tienda = new TiendaGUI(listaProductos, listaCategoria, listaSubcategorias, listaMarcas);
		tienda.setVisible(true);
	}
	
//	public static ArrayList limpiadorTemporal(ArrayList aLimpiar) {
//		ArrayList alim = aLimpiar;
//		for (Object object : aLimpiar) {
//			int num = alim.size()-1;
//			System.out.println(aLimpiar.get(num));
//			System.out.println((aLimpiar.get(num) == null) + "iguales");
//			System.out.println(Objects.isNull(aLimpiar.get(num)) + " isNull");
//			System.out.println((aLimpiar.get(num) == "null") + " parentesis");
//			
//			if (alim.get(num) == "null") {
//				System.out.println("test");
//				alim.remove(num);
//			}
//			
//		}
//		System.out.println(alim);
//		return null;
		
//	}
}
