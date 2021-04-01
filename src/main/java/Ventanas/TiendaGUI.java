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

import models.Categoria;
import models.Producto;
import models.SubCategoria;
import models.Colores;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.List;
import java.awt.Point;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class TiendaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscador;
	private DefaultListModel<Producto> model = new DefaultListModel<>();
	private Colores col;
	private ActionListener actualizador;
	private Categoria categoriaSeleccionada;
	
//	public static void main(String[] args) {
//		ArrayList<Producto> productosTest = new ArrayList<Producto>();
//		ArrayList<Categoria> categoriasTest = new ArrayList<Categoria>();
//		ArrayList<SubCategoria> subCategoriasTest = new ArrayList<SubCategoria>();
//		
//		Producto pr1 = new Producto("Patata", "descripcion", 1 , 20, "no");
//		Producto pr2 = new Producto("pantalon", "descripcion", 0 , 1, "imagen");
//		
//		Categoria c1 = new Categoria("comida", "comiiiida");
//		Categoria c2 = new Categoria("ropa", "ropaaaa");
//		
//		SubCategoria sc1 = new SubCategoria("tuber", "tuberculo", c1);
//		SubCategoria sc2 = new SubCategoria("baquero", "muuuu", c2);
//						
//		productosTest.add(pr1);
//		productosTest.add(pr2);
//		categoriasTest.add(c1);
//		categoriasTest.add(c2);
//		subCategoriasTest.add(sc1);
//		subCategoriasTest.add(sc2);
//		
//		TiendaGUI test = new TiendaGUI(productosTest, categoriasTest, subCategoriasTest);
//		test.setVisible(true);
//	}
	
	public TiendaGUI(ArrayList<Producto> productos, ArrayList<Categoria> categorias, final ArrayList<SubCategoria> subCategorias) {
		for (int i = 0; i < productos.size(); i++) {
			model.addElement(productos.get(i));
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		botonBuscar.setToolTipText("Buscar");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		botonBuscar.setBounds(136, 11, 70, 38);
		panel.add(botonBuscar);
		
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
		JComboBox<Colores> comboBox_colores = new JComboBox<Colores>();
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
		
					if (categoriaSeleccionada.getNombre() == subCategorias.get(i).getCategoria().getNombre()) {
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
//		ScrollPane caracteristicasPane = new ScrollPane();
//		caracteristicasPane.setScrollPosition(new Point(0, 0));
//		caracteristicasPane.setBackground(Color.WHITE);
//		caracteristicasPane.setBounds(507, 381, 309, 83);
//		contentPane.add(caracteristicasPane);
//		
		//#################################################################################################
		JButton botonComprar = new JButton("COMPRAR");
		botonComprar.setBounds(507, 470, 309, 41);
		botonComprar.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		contentPane.add(botonComprar);
		
		//#################################################################################################
		JButton botonLogin = new JButton("Log in");
		botonLogin.setBounds(518, 10, 122, 23);
		contentPane.add(botonLogin);
		
		//#################################################################################################
		JButton botonSignIn = new JButton("Sign in");
		botonSignIn.setBounds(679, 10, 122, 23);
		contentPane.add(botonSignIn);
		
		//#################################################################################################
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 10, 252, 500);
		contentPane.add(scrollPane);
		
		//#################################################################################################
		JList<Producto> list = new JList<Producto>(model);
		scrollPane.setViewportView(list);
	}
}
