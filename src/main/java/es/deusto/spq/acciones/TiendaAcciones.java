package es.deusto.spq.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import es.deusto.spq.cliente.TiendaGUI;
import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Colores;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Tallas;
import es.deusto.spq.util.Idiomas;
import es.deusto.spq.util.PrepararDatos;

public class TiendaAcciones implements ITiendaAcciones{

	private static ITiendaAcciones instance;

	public static ITiendaAcciones getInstance() {
		if (instance == null) {
			instance = new TiendaAcciones();
		}		
		return instance;
	}
	
	@Override
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
	@Override
	public ArrayList<SubCategoria> rellenarSubcategorias(Categoria categoriaSeleccionada, List<SubCategoria> subCategorias){
    	System.out.println(subCategorias);
    	System.out.println(categoriaSeleccionada);
    	
		ArrayList<SubCategoria> futuroComboBox_Subcategoria = new ArrayList<SubCategoria>();
		for (int i = 0; i < subCategorias.size(); i++) {
    		if (categoriaSeleccionada != null) {			
				if (categoriaSeleccionada.getNombre().equals(subCategorias.get(i).getCategoria().getNombre())) {
					futuroComboBox_Subcategoria.add(subCategorias.get(i));
				}
    		}
		}
		System.out.println(futuroComboBox_Subcategoria);
		return futuroComboBox_Subcategoria;
	}
	@Override
	public void cambiarIdioma(ArrayList<JButton> buttons, ArrayList<JLabel> labels, JComboBox<String> combo) {
		if (combo.getSelectedItem().equals("Ingles") || combo.getSelectedItem().equals("English")){
			TiendaGUI.idioma = Idiomas.Ingles;
		}else{
			TiendaGUI.idioma = Idiomas.Español;
		}
		combo.removeAllItems();
		combo.addItem(Idiomas.seleccionarPalabra("nombre1"));
		combo.addItem(Idiomas.seleccionarPalabra("nombre2"));
		for (String i : PrepararDatos.getInstance().getIdiomaCaract()){
			if (combo.getSelectedItem().equals("English") || combo.getSelectedItem().equals("Ingles")){
				for (JButton b : buttons){
					if (b.getText().equals(PrepararDatos.getInstance().getPalabraEspanyol(i))){
						b.setText(Idiomas.seleccionarPalabra(i));
					}
				}
				for (JLabel l : labels){
					if (l.getText().equals(PrepararDatos.getInstance().getPalabraEspanyol(i))){
						l.setText(Idiomas.seleccionarPalabra(i));
					}
				}
			} else{
				for (JButton b : buttons){
					if (b.getText().equals(PrepararDatos.getInstance().getPalabraIngles(i))){
						b.setText(Idiomas.seleccionarPalabra(i));
					}
				}
				for (JLabel l :labels){
					if (l.getText().equals(PrepararDatos.getInstance().getPalabraIngles(i))){
						l.setText(Idiomas.seleccionarPalabra(i));
					}
				}
			}
		}
	}
//	public void LoginOrLogout(Cliente cliente, JFrame esto, WebTarget appTarget, JPanel contentPane) {
//		if (cliente == null) {
//			esto.setEnabled(false);
//			VentanaLogin ventanaLogin = new VentanaLogin(esto, appTarget);
//			ventanaLogin.setVisible(true);
//			contentPane.setEnabled(false);
//		}else{
//			esto.dispose();
//		}
//		//acciones.LoginOrLogout(cliente, (JFrame)esto, appTarget, contentPane);
//	}
}
