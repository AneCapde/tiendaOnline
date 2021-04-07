package es.deusto.spq.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import java.awt.Color;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import models.Categoria;
import models.Cliente;
import models.Producto;
import models.SubCategoria;
import models.Tallas;
import models.Colores;
import models.Marca;
import models.Pedido;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TiendaGUI extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Client client;
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
	private Cliente cliente;
	
	
	
	public TiendaGUI() {
		client = ClientBuilder.newClient();

        final WebTarget appTarget = client.target("http://localhost:8080/myapp");
		final WebTarget productosTarget = appTarget.path("/productos");
		final WebTarget categoriasTarget = appTarget.path("/categorias");
		final WebTarget marcasTarget = appTarget.path("/marcas");
		final WebTarget subTarget = appTarget.path("/subcategorias");
		final WebTarget pedidoTarget= appTarget.path("/pedidos");

		final TiendaGUI esto = this;
		
		GenericType<List<Producto>> genericType_productos = new GenericType<List<Producto>>() {};
        List<Producto> productos = productosTarget.request(MediaType.APPLICATION_JSON).get(genericType_productos);
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
		GenericType<List<Categoria>> genericType_categoria = new GenericType<List<Categoria>>() {};
        List<Categoria> categorias = categoriasTarget.request(MediaType.APPLICATION_JSON).get(genericType_categoria);
		for (Categoria categoria : categorias) {
			comboBox_Categoria.addItem(categoria);
		}
		
		
		GenericType<List<SubCategoria>> genericType_sub = new GenericType<List<SubCategoria>>() {};
        List<SubCategoria> subCategorias = subTarget.request(MediaType.APPLICATION_JSON).get(genericType_sub);
		comboBox_Categoria.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	comboBox_Subcategoria.removeAllItems();
		    	comboBox_Subcategoria.addItem(null);
		    	categoriaSeleccionada = (Categoria) comboBox_Categoria.getSelectedItem();
		    	for (int i = 0; i < subCategorias.size(); i++) {
		    		if (categoriaSeleccionada != null) {
		    			System.out.println(categoriaSeleccionada.getNombre());
						System.out.println(subCategorias.get(i).getCategoria().getNombre());
						System.out.println(categoriaSeleccionada.getNombre().equals(subCategorias.get(i).getCategoria().getNombre()));
			
						if (categoriaSeleccionada.getNombre().equals(subCategorias.get(i).getCategoria().getNombre())) {
							System.out.println("Meto:" + subCategorias.get(i));
							comboBox_Subcategoria.addItem(subCategorias.get(i));
						}
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
		botonComprar.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Date date = new Date();
		    	System.out.println(productoSeleccionado);

		    	Pedido pedido = new Pedido(cliente, date,"en proceso" , productoSeleccionado.getPrecio(), 1 , productoSeleccionado);
		    	pedidoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
			}
		});
		
		//#################################################################################################
		JButton botonLogin = new JButton("Log in");
		botonLogin.setBounds(518, 10, 122, 23);
		botonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				esto.setEnabled(false);
				VentanaLogin ventanaLogin = new VentanaLogin(esto, appTarget);
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
				VentanaRegistro ventanaRegistro = new VentanaRegistro(esto, appTarget);
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
				textArea.setText(null);
				if (productoSeleccionado != null) {
					textArea.append("- NOMBRE: " + productoSeleccionado.nombre + "\n");
					textArea.append("- DESCRIPCIÓN: " + productoSeleccionado.descripcion + "\n");
					textArea.append("- PRECIO: " + productoSeleccionado.precio + "\n");
					textArea.append("- CATEGORÍA: " + productoSeleccionado.getSubcategoria().getCategoria().getNombre() + "\n");
					textArea.append("    SUBCATEGORÍA: " + productoSeleccionado.getSubcategoria().getNombre() + "\n");
					
				}
				
				
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
		
		GenericType<List<Marca>> genericType_marca = new GenericType<List<Marca>>() {};
        List<Marca> marcas = marcasTarget.request(MediaType.APPLICATION_JSON).get(genericType_marca);
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
						
						System.out.println(productos.get(i).getSubcategoria().getCategoria() + " =? " + categoriaSeleccionada + (productos.get(i).getSubcategoria().getCategoria() == categoriaSeleccionada));
						System.out.println(productos.get(i).getSubcategoria() + " =? " + subCategoriaSeleccionada + (productos.get(i).getSubcategoria() == subCategoriaSeleccionada));
						System.out.println(productos.get(i).getMarca() + " =? " + marcaSeleccionada + (productos.get(i).getMarca() == marcaSeleccionada));
						System.out.println(productos.get(i).getTallas_colores());
						System.out.println(productos.get(i).getTallas_colores().containsValue(tallaSeleccionada));
						//System.out.println(( colorSelecionado == null || productos.get(i).getTallas_colores().containsKey(colorSelecionado.toString() ) && (tallaSeleccionada == null || productos.get(i).getTallas_colores().containsValue(tallaSeleccionada) ) ));
						//System.out.println(colorSelecionado + " =? " + productos.get(i).getTallas_colores().get(colorSelecionado.toString()) + productos.get(i).getTallas_colores().containsKey(colorSelecionado.toString()));
						System.out.println();
						
						if 		(
								   (categoriaSeleccionada == null || productos.get(i).getSubcategoria().getCategoria().getNombre().equals(categoriaSeleccionada.getNombre())) 
								&& (subCategoriaSeleccionada == null || productos.get(i).getSubcategoria().getNombre().equals(subCategoriaSeleccionada.getNombre()))
								&& (marcaSeleccionada == null || productos.get(i).getMarca().getNombre().equals(marcaSeleccionada.getNombre()))
								&& ((colorSelecionado == null || productos.get(i).getTallas_colores().containsKey(colorSelecionado.toString()))  &&  (tallaSeleccionada == null || productos.get(i).getTallas_colores().containsValue(tallaSeleccionada)))
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
	public static void main(String[] args) {
        TiendaGUI tiendaGUI = new TiendaGUI();
		tiendaGUI.setVisible(true);
    }
}