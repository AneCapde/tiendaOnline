package es.deusto.spq.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Colores;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Tallas;

public class TiendaAcciones {
	
	public TiendaAcciones() {
	}
	
	
	
	public ArrayList<Producto> buscar(String textoBuscador, Categoria categoriaSeleccionada, SubCategoria subCategoriaSeleccionada,
											 Marca marcaSeleccionada, Colores colorSelecionado, Tallas tallaSeleccionada, List<Producto> productos) {
				
		ArrayList<Producto> model = new ArrayList<Producto>();
		System.out.println(productos);
		for (int i = 0; i < productos.size(); i++) {
//			System.out.println(productos.get(i).getMarca() + "==" + marcaSeleccionada);
			if (productos.get(i).getNombre().toLowerCase().indexOf(textoBuscador.toLowerCase()) == 0) {

				if 		(
						(subCategoriaSeleccionada == null || productos.get(i).getSubcategoria().getNombre().equals(subCategoriaSeleccionada.getNombre()))    
						&& (categoriaSeleccionada == null || productos.get(i).getSubcategoria().getCategoria().toString().equals(categoriaSeleccionada.toString()) )
						&& (marcaSeleccionada == null || productos.get(i).getMarca().getNombre().equals(marcaSeleccionada.getNombre()))
						//&& ((colorSelecionado == null || productos.get(i).getTallas_colores().containsKey(colorSelecionado))  &&  (tallaSeleccionada == null || productos.get(i).getTallas_colores().containsValue(tallaSeleccionada)))
						)
						
				{

					model.add(productos.get(i));
				}
			}

		}
		return model;
	}
	
	public ArrayList<SubCategoria> rellenarSubcategorias(Categoria categoriaSeleccionada, List<SubCategoria> subCategorias){
    	ArrayList<SubCategoria> futuroComboBox_Subcategoria = new ArrayList<SubCategoria>();
		for (int i = 0; i < subCategorias.size(); i++) {
    		if (categoriaSeleccionada != null) {			
				if (categoriaSeleccionada.getNombre().equals(subCategorias.get(i).getCategoria().getNombre())) {
					futuroComboBox_Subcategoria.add(subCategorias.get(i));
				}
    		}
		}
		return futuroComboBox_Subcategoria;
	}
}
