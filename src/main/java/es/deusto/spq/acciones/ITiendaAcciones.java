package es.deusto.spq.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Colores;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Tallas;

public interface ITiendaAcciones {
    
    public void cambiarIdioma(ArrayList<JButton> buttons, ArrayList<JLabel> labels, JComboBox<String> combo);
    public ArrayList<SubCategoria> rellenarSubcategorias(Categoria categoriaSeleccionada, List<SubCategoria> subCategorias);
    public ArrayList<Producto> buscar(String textoBuscador, Categoria categoriaSeleccionada, SubCategoria subCategoriaSeleccionada,
											 Marca marcaSeleccionada, Colores colorSelecionado, Tallas tallaSeleccionada, List<Producto> productos);
}
