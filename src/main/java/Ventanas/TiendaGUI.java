package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import java.awt.CardLayout;
import javax.swing.JDesktopPane;
import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Canvas;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Categoria;
import models.Producto;
import models.SubCategoria;
import models.Tallas;
import models.Colores;
import models.Marca;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.List;
import java.awt.Point;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class TiendaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscador;
	private DefaultListModel<Producto> model = new DefaultListModel<>();
	private Colores col;
	private Tallas tall;
	private JList<Producto> listaElementos;
	private JComboBox<Colores> comboBox_colores;
	private JComboBox<Tallas> comboBoxTalla;
	private JComboBox<Marca> comboBoxMarca;
	private JTextArea textArea;
	
//	private ActionListener actualizador;
	private Categoria categoriaSeleccionada;
	private String textoBuscador;
	private SubCategoria subCategoriaSeleccionada;
	private Marca marcaSeleccionada;
	private Tallas tallaSeleccionada;
	private Colores colorSelecionado;
	private Producto productoSeleccionado;

	
	
//	public static void main(String[] args) {
//		ArrayList<Producto> productosTest = new ArrayList<Producto>();
//		ArrayList<Categoria> categoriasTest = new ArrayList<Categoria>();
//		ArrayList<SubCategoria> subCategoriasTest = new ArrayList<SubCategoria>();
//		ArrayList<Marca> marcasTest = new ArrayList<Marca>();
//		
//		
//		Categoria c1 = new Categoria("comida", "comiiiida");
//		Categoria c2 = new Categoria("ropa", "ropaaaa");
//		
//		SubCategoria sc1 = new SubCategoria("tuber", "tuberculo", c1);
//		SubCategoria sc2 = new SubCategoria("baquero", "muuuu", c2);
//		
//		Marca ma1 = new Marca("Pats", "ico");
//		Marca ma2 = new Marca("Levis", "sa");
//		
//		Producto pr1 = new Producto("Patata", "descripcion", 1 , 20, "no", sc1, ma1);
//		Producto pr2 = new Producto("pantalon", "descripcion", 0 , 1, "imagen", sc2, ma2);
//		
//		productosTest.add(pr1);
//		productosTest.add(pr2);
//		categoriasTest.add(c1);
//		categoriasTest.add(c2);
//		subCategoriasTest.add(sc1);
//		subCategoriasTest.add(sc2);
//		marcasTest.add(ma1);
//		marcasTest.add(ma2);
//		
//		TiendaGUI test = new TiendaGUI(productosTest, categoriasTest, subCategoriasTest, marcasTest);
//		test.setVisible(true);
//	}
	
	public TiendaGUI(final ArrayList<Producto> productos, ArrayList<Categoria> categorias, final ArrayList<SubCategoria> subCategorias, ArrayList<Marca> marcas) {
		
		final TiendaGUI esto = this;
		
		for (int i = 0; i < productos.size(); i++) {
			model.addElement(productos.get(i));
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//#################################################################################################
		Panel panel = new Panel();
		panel.setBounds(10, 10, 216, 501);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//#################################################################################################
		txtBuscador = new JTextField();
		txtBuscador.setToolTipText("Buscar");
		txtBuscador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtBuscador.setBounds(10, 11, 124, 38);
		panel.add(txtBuscador);
		txtBuscador.setColumns(10);
		
		
		
		//#################################################################################################
		JButton botonHistorial = new JButton("Historial");
		botonHistorial.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		botonHistorial.setBounds(10, 385, 194, 47);
		panel.add(botonHistorial);
		
		//#################################################################################################
		JButton btnNewButton_2 = new JButton("Cesta");
		btnNewButton_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(10, 443, 194, 47);
		panel.add(btnNewButton_2);
		
		
		//#################################################################################################
		comboBox_colores = new JComboBox<Colores>();
		comboBox_colores.addItem(null);
		for (Colores col : col.values()) {
			comboBox_colores.addItem(col);
		}
		comboBox_colores.setBounds(10, 193, 196, 30);
		panel.add(comboBox_colores);
		
		//#################################################################################################
		JLabel lblColores = new JLabel("Colores");
		lblColores.setBounds(10, 179, 196, 14);
		panel.add(lblColores);
		
		
		//#################################################################################################
		final JComboBox<SubCategoria> comboBox_Subcategoria = new JComboBox<SubCategoria>();
		comboBox_Subcategoria.setBounds(10, 138, 196, 30);
		panel.add(comboBox_Subcategoria);
		
		//#################################################################################################
		final JComboBox<Categoria> comboBox_Categoria = new JComboBox<Categoria>();
		comboBox_Categoria.addItem(null);
		for (Categoria categoria : categorias) {
			comboBox_Categoria.addItem(categoria);
		}
		
		
		comboBox_Categoria.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	comboBox_Subcategoria.removeAllItems();
		    	comboBox_Subcategoria.addItem(null);
		    	categoriaSeleccionada = (Categoria) comboBox_Categoria.getSelectedItem();
		    	for (int i = 0; i < subCategorias.size(); i++) {
					System.out.println(categoriaSeleccionada.getNombre());
					System.out.println(subCategorias.get(i).getCategoria().getNombre());
					System.out.println(categoriaSeleccionada.getNombre().equals(subCategorias.get(i).getCategoria().getNombre()));
		
					if (categoriaSeleccionada.getNombre().equals(subCategorias.get(i).getCategoria().getNombre())) {
						System.out.println("Meto:" + subCategorias.get(i));
						comboBox_Subcategoria.addItem(subCategorias.get(i));
					}
					
				}
		    	
		    }
		});
		
		
		comboBox_Categoria.setBounds(10, 79, 196, 30);
		panel.add(comboBox_Categoria);
		
				
		//#################################################################################################
		JLabel lblSubcategora = new JLabel("Subcategoría");
		lblSubcategora.setBounds(10, 122, 143, 14);
		panel.add(lblSubcategora);
		
		//#################################################################################################
		JLabel lblCategora = new JLabel("Categoría");
		lblCategora.setBounds(10, 62, 153, 14);
		panel.add(lblCategora);
		
		//#################################################################################################
		Panel imagePlacehold = new Panel();
		imagePlacehold.setBounds(507, 40, 309, 315);
		imagePlacehold.setBackground(Color.WHITE);
		contentPane.add(imagePlacehold);
		
		//#################################################################################################
		JLabel lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setBounds(507, 361, 122, 14);
		lblCaracteristicas.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		contentPane.add(lblCaracteristicas);
		
		//#################################################################################################
		JButton botonComprar = new JButton("COMPRAR");
		botonComprar.setBounds(507, 470, 309, 41);
		botonComprar.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		contentPane.add(botonComprar);
		
		//#################################################################################################
		JButton botonLogin = new JButton("Log in");
		botonLogin.setBounds(518, 10, 122, 23);
		botonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				esto.setEnabled(false);
				VentanaLogin ventanaLogin = new VentanaLogin(esto);
				ventanaLogin.setVisible(true);
				contentPane.setEnabled(false);
				
			}
		});
		contentPane.add(botonLogin);
		
		//#################################################################################################
		JButton botonSignIn = new JButton("Sign in");
		botonSignIn.setBounds(679, 10, 122, 23);
		contentPane.add(botonSignIn);
		botonSignIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				esto.setEnabled(false);
				VentanaRegistro ventanaRegistro = new VentanaRegistro(esto);
				ventanaRegistro.setVisible(true);
				contentPane.setEnabled(false);
			}
		});
		
		//#################################################################################################
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 10, 252, 500);
		contentPane.add(scrollPane);
		//#############################################################
		textArea = new JTextArea();
		

		textArea.setBounds(507, 379, 309, 80);
		contentPane.add(textArea);


		//#################################################################################################
		listaElementos = new JList<Producto>(model);
		scrollPane.setViewportView(listaElementos);
		listaElementos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				productoSeleccionado = listaElementos.getSelectedValue();
				textArea.setText(null);;
				textArea.append("- NOMBRE: " + productoSeleccionado.nombre + "\n");
				textArea.append("- DESCRIPCIÓN: " + productoSeleccionado.descripcion + "\n");
				textArea.append("- PRECIO: " + productoSeleccionado.precio + "\n");
				textArea.append("- CATEGORÍA: " + productoSeleccionado.getSubcategoria().getCategoria().getNombre() + "\n");
				textArea.append("    SUBCATEGORÍA: " + productoSeleccionado.getSubcategoria().getNombre() + "\n");
				
				
//				textArea.append();
				
			}
		});
		
		//######################################################################
		comboBoxTalla = new JComboBox<Tallas>();
		comboBoxTalla.addItem(null);
		
		for (Tallas tall : tall.values()) {
			comboBoxTalla.addItem(tall);
		}

		comboBoxTalla.setBounds(10, 251, 196, 30);
		panel.add(comboBoxTalla);
			
		
		//######################################################################
		JLabel lblTalla = new JLabel("Talla");
		lblTalla.setBounds(10, 237, 46, 14);
		panel.add(lblTalla);
		
		//#####################################################################
		comboBoxMarca = new JComboBox<Marca>();
		comboBoxMarca.addItem(null);
		for (Marca marca : marcas) {
			comboBoxMarca.addItem(marca);
		}
		comboBoxMarca.setBounds(10, 310, 196, 30);
		panel.add(comboBoxMarca);
		
		//#####################################################################
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 296, 46, 14);
		panel.add(lblMarca);
		
		//#################################################################################################
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		botonBuscar.setToolTipText("Buscar");


		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textoBuscador = txtBuscador.getText();
				categoriaSeleccionada = (Categoria) comboBox_Categoria.getSelectedItem();
				subCategoriaSeleccionada = (SubCategoria) comboBox_Subcategoria.getSelectedItem();
				marcaSeleccionada = (Marca) comboBoxMarca.getSelectedItem();
				colorSelecionado = (Colores) comboBox_colores.getSelectedItem();
				tallaSeleccionada = (Tallas) comboBoxTalla.getSelectedItem();

				model.removeAllElements();
				System.out.println(productos);
				for (int i = 0; i < productos.size(); i++) {
						System.out.println(productos.get(i).getMarca() + "==" + marcaSeleccionada);
					if (productos.get(i).getNombre().toLowerCase().indexOf(textoBuscador.toLowerCase()) == 0) {
						if ((productos.get(i).getSubcategoria().getCategoria() == categoriaSeleccionada || categoriaSeleccionada == null) 
								&& (productos.get(i).getSubcategoria() == subCategoriaSeleccionada || subCategoriaSeleccionada == null)
								&& (productos.get(i).getMarca() == marcaSeleccionada|| marcaSeleccionada == null)
								//&& (productos.get(i).getTallas_colores())
								)
						{

							model.addElement(productos.get(i));
						}
					}

				}
			}
		});


		botonBuscar.setBounds(136, 11, 70, 38);
		panel.add(botonBuscar);

		

	}
}
