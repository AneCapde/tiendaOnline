package es.deusto.spq.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Colores;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Tallas;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TiendaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Client client;
	private JPanel contentPane;
	private JTextField txtBuscador;
	private DefaultListModel<Producto> model = new DefaultListModel<>();
	private DefaultListModel<Pedido> model2 = new DefaultListModel<>();
	private JList<Producto> listaElementos;
	private JComboBox<Colores> comboBox_colores;
	private JComboBox<Tallas> comboBoxTalla;
	private JComboBox<Marca> comboBoxMarca;
	private JTextArea textArea;
	ImageIcon imagen1, imagen2;
	Icon icono1, icono2;
	
	private Categoria categoriaSeleccionada;
	private String textoBuscador;
	private SubCategoria subCategoriaSeleccionada;
	private Marca marcaSeleccionada;
	private Tallas tallaSeleccionada;
	private Colores colorSelecionado;
	private Producto productoSeleccionado;
	private static Cliente cliente;
	private List<Producto> productos;
	private List<Pedido> pedidos = new ArrayList<>(); 
	private static List<Producto> productos_deseados = new ArrayList<Producto>();
	public static List<Producto> productos_cesta = new ArrayList<Producto>();
	private static JButton botonLogin;
    private static JButton btnDeseado;
	private static JButton botonComprar;
	private static JButton botonAnyadir;

	public TiendaGUI() {
		client = ClientBuilder.newClient();

        final WebTarget appTarget = client.target("http://localhost:8080/myapp");
		final WebTarget categoriasTarget = appTarget.path("/categorias");
		final WebTarget marcasTarget = appTarget.path("/marcas");
		final WebTarget subTarget = appTarget.path("/subcategorias");
		final WebTarget pedidoTarget= appTarget.path("/pedidos");
		// final WebTarget pagoTarget= appTarget.path("/pagos");
		final WebTarget productosTarget = appTarget.path("/productos");

		final TiendaGUI esto = this;
		
		GenericType<List<Producto>> genericType_productos = new GenericType<List<Producto>>() {};
        productos = productosTarget.request(MediaType.APPLICATION_JSON).get(genericType_productos);
		for (int i = 0; i < productos.size(); i++) {
			model.addElement(productos.get(i));
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 872, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//#################################################################################################
		Panel panel = new Panel();
		panel.setBounds(10, 10, 216, 601);
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
		botonHistorial.setBounds(10, 419, 194, 30);
		panel.add(botonHistorial);
		
		botonHistorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!TiendaGUI.getCliente().equals(null)) {
						esto.setEnabled(false);
						updateUserList(appTarget);
						HistorialGUI historial= new HistorialGUI(esto, pedidos, appTarget);
						historial.setVisible(true);
						contentPane.setEnabled(false);
						System.out.println("holaa");
						dispose();
					}else {
						botonHistorial.setEnabled(false);
					}
				}
				catch(NullPointerException nl) {
					JOptionPane.showMessageDialog(null, "Ningun Usuario ha Iniciado Sesion", "Validar Credenciales", JOptionPane.INFORMATION_MESSAGE);
				}
			}			
		});
		
		//#################################################################################################
		JButton btnCesta = new JButton("Cesta");
		btnCesta.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnCesta.setBounds(10, 460, 194, 30);
		panel.add(btnCesta);
		btnCesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!TiendaGUI.getCliente().equals(null)) {
						esto.setEnabled(false);
						updateUserList(appTarget);
						CestaGUI cestaGUI = new CestaGUI(esto, productos_cesta, appTarget);
						cestaGUI.setVisible(true);
						contentPane.setEnabled(false);
						dispose();
					}else {
						btnCesta.setEnabled(false);
					}
				}
				catch(NullPointerException nl) {
					JOptionPane.showMessageDialog(null, "Ningun Usuario ha Iniciado Sesion", "Validar Credenciales", JOptionPane.INFORMATION_MESSAGE);
				}	
			}
		});
		
		//#################################################################################################
		comboBox_colores = new JComboBox<Colores>();
		comboBox_colores.addItem(null);
		comboBox_colores.setEnabled(false);
		for (Colores col : Colores.values()) {
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
		
		//Cada vez que se cambia una categoría hay que eliminar todas las subactegorias y meter nuevas eb el comboBox
		GenericType<List<SubCategoria>> genericType_sub = new GenericType<List<SubCategoria>>() {};
        List<SubCategoria> subCategorias = subTarget.request(MediaType.APPLICATION_JSON).get(genericType_sub);
		comboBox_Categoria.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	comboBox_Subcategoria.removeAllItems();
		    	comboBox_Subcategoria.addItem(null);
		    	categoriaSeleccionada = (Categoria) comboBox_Categoria.getSelectedItem();
		    	for (int i = 0; i < subCategorias.size(); i++) {
		    		if (categoriaSeleccionada != null) {			
						if (categoriaSeleccionada.getNombre().equals(subCategorias.get(i).getCategoria().getNombre())) {
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
		JLabel lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setBounds(507, 361, 162, 14);
		lblCaracteristicas.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		contentPane.add(lblCaracteristicas);
		
		//#################################################################################################
		botonAnyadir = new JButton("Añadir A la Cesta");
		botonAnyadir.setBounds(507, 310, 229, 41);
		botonAnyadir.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		contentPane.add(botonAnyadir);
		botonAnyadir.setVisible(false);
		botonAnyadir.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				productoSeleccionado =  listaElementos.getSelectedValue();
				if (!productos_cesta.contains(productoSeleccionado)){
					productos_cesta.add(productoSeleccionado);
				}
			}
		});
		
		//#################################################################################################
		botonLogin = new JButton("Log in");
		botonLogin.setBounds(518, 10, 122, 23);
		contentPane.add(botonLogin);
		botonLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				if (cliente == null) {
					esto.setEnabled(false);
					VentanaLogin ventanaLogin = new VentanaLogin(esto, appTarget);
					ventanaLogin.setVisible(true);
					contentPane.setEnabled(false);
				}else{
					cliente = null;
					dispose();
				}
			}
		});
		
		//#################################################################################################
		JButton botonSignIn = new JButton("Sign in");
		botonSignIn.setBounds(709, 10, 122, 23);
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
		botonComprar = new JButton("COMPRAR");
		botonComprar.setBounds(507, 470, 229, 41);
		botonComprar.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		contentPane.add(botonComprar);
		botonComprar.setVisible(false);
		botonComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				productoSeleccionado = listaElementos.getSelectedValue();
				List<Producto> productoPedido = new ArrayList<>();
				Pedido pedido = new Pedido(TiendaGUI.getCliente(), date, "en proceso", productoSeleccionado.getPrecio(),
						1);
				productoPedido.add(productoSeleccionado);
				pedido.setProducto((ArrayList<Producto>) productoPedido);
				pedidoTarget.request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
			}
		});
		
		//#################################################################################################
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 10, 252, 500);
		contentPane.add(scrollPane);
		
		//#################################################################################################
		JPanel imagePlacehold = new JPanel();
		imagePlacehold.setBounds(507, 40, 339, 260);
		imagePlacehold.setBackground(Color.WHITE);
		contentPane.add(imagePlacehold);
		
		//#############################################################
		textArea = new JTextArea();
		textArea.setBounds(507, 379, 339, 80);
		contentPane.add(textArea);
		
		//#############################################################
		btnDeseado = new JButton();
		btnDeseado.setBounds(798, 470, 48, 41);
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/corazon-blanco.png"));
		ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(btnDeseado.getWidth(), btnDeseado.getHeight(),Image.SCALE_DEFAULT));
		btnDeseado.setIcon(icono_2);
		btnDeseado.updateUI();
		contentPane.add(btnDeseado);
		btnDeseado.setVisible(false);
		btnDeseado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try{
				if(productoSeleccionado != null) {
					btnDeseado.setVisible(true);
					boolean incluido = false;
					Producto producto = null;
					for (Producto p : productos_deseados){
						if (p.getNombre().equals(productoSeleccionado.getNombre())){
							incluido = true;
							producto = p;
							ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/corazon-blanco.png"));
							ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(btnDeseado.getWidth(), btnDeseado.getHeight(),Image.SCALE_DEFAULT));
							btnDeseado.setIcon(icono_2);
							btnDeseado.updateUI();
						}
					}
					productos_deseados.remove(producto);
					if (!incluido){
						productos_deseados.add(productoSeleccionado);
						ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/corazon-rojo.png"));
						ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(btnDeseado.getWidth(), btnDeseado.getHeight(),Image.SCALE_DEFAULT));
						btnDeseado.setIcon(icono_2);
						btnDeseado.updateUI();
					}

				}
				}catch(NullPointerException nl4){
					return;
				}


			}
		});
		
		//#################################################################################################
		listaElementos = new JList<Producto>(model);
		scrollPane.setViewportView(listaElementos);
		//Coge los elementos de la clase seleccionada y los pone en la Jlist.
		listaElementos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					if (!TiendaGUI.getCliente().equals(null)) {
						btnDeseado.setVisible(true);
						botonComprar.setVisible(true);
						botonAnyadir.setVisible(true);
						productoSeleccionado = listaElementos.getSelectedValue();
						boolean incluido = false;
						for (Producto p : productos_deseados){
							try{
							if (p.getNombre().equals(productoSeleccionado.getNombre())){
								incluido = true;
								ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/corazon-rojo.png"));
 								ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(btnDeseado.getWidth(), btnDeseado.getHeight(),Image.SCALE_DEFAULT));
 								btnDeseado.setIcon(icono_2);
								btnDeseado.updateUI();
							}
							}catch(NullPointerException nl3){
								return;
							}
						}
						if (!incluido){
							ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/corazon-blanco.png"));
	 						ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(btnDeseado.getWidth(), btnDeseado.getHeight(),Image.SCALE_DEFAULT));
	 						btnDeseado.setIcon(icono_2);
	 						btnDeseado.updateUI();
						}
						textArea.setText(null);
						if (productoSeleccionado != null) {
							imagePlacehold.removeAll();
							ImageIcon icono_3 = new ImageIcon(getClass().getResource("/"+ productoSeleccionado.getImagen()));
							ImageIcon icono_4 = new ImageIcon(icono_3.getImage().getScaledInstance(imagePlacehold.getWidth(), imagePlacehold.getHeight(),Image.SCALE_DEFAULT));
							JLabel label = new JLabel(icono_4);
							imagePlacehold.add(label);
							imagePlacehold.revalidate();
					
								textArea.append("- NOMBRE: " + productoSeleccionado.nombre + "\n");
								textArea.append("- DESCRIPCIÓN: " + productoSeleccionado.descripcion + "\n");
								textArea.append("- PRECIO: " + productoSeleccionado.precio + "\n");
								textArea.append("- CATEGORÍA: " + productoSeleccionado.getSubcategoria().getCategoria().getNombre() + "\n");
								textArea.append("    SUBCATEGORÍA: " + productoSeleccionado.getSubcategoria().getNombre() + "\n");
							}
					}
				}
				catch(NullPointerException nl) {
					imagePlacehold.removeAll();
                    productoSeleccionado = listaElementos.getSelectedValue();
                    
                    try {
                        if (!productoSeleccionado.equals(null)){
                            textArea.setText(null);
                            ImageIcon icono_3 = new ImageIcon(getClass().getResource("/"+ productoSeleccionado.getImagen()));
                            ImageIcon icono_4 = new ImageIcon(icono_3.getImage().getScaledInstance(imagePlacehold.getWidth(), imagePlacehold.getHeight(),Image.SCALE_DEFAULT));
                            JLabel label = new JLabel(icono_4);
                            imagePlacehold.add(label);
                            imagePlacehold.revalidate();
                            
                            textArea.append("- NOMBRE: " + productoSeleccionado.nombre + "\n");
                            textArea.append("- DESCRIPCIÓN: " + productoSeleccionado.descripcion + "\n");
                            textArea.append("- PRECIO: " + productoSeleccionado.precio + "\n");
                            textArea.append("- CATEGORÍA: " + productoSeleccionado.getSubcategoria().getCategoria().getNombre() + "\n");
                            textArea.append("    SUBCATEGORÍA: " + productoSeleccionado.getSubcategoria().getNombre() + "\n");
                        }

                    }
                    catch(NullPointerException nl1) {
                        return;
                    }
				}
		}
	});
		
		//######################################################################
		comboBoxTalla = new JComboBox<Tallas>();
		comboBoxTalla.addItem(null);
		comboBoxTalla.setEnabled(false);
		for (Tallas tall : Tallas.values()) {
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

		//Cada vez que se le da a buscar se mira todos los combobox y el apartado de busqueda y se mira si los elementos en la lista tienen
		//esa característica
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
								&& ((colorSelecionado == null || productos.get(i).getTallas_colores().containsKey(colorSelecionado))  &&  (tallaSeleccionada == null || productos.get(i).getTallas_colores().containsValue(tallaSeleccionada)))
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
		
		JButton botonListaDeseados = new JButton("ListaDeseados");
		botonListaDeseados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!TiendaGUI.getCliente().equals(null)) {
						esto.setEnabled(false);
						updateUserList(appTarget);
						ListaDeseadosGUI listaDeseados= new ListaDeseadosGUI(esto, appTarget, esto);
						listaDeseados.setVisible(true);
						dispose();
					}else {
						botonListaDeseados.setEnabled(false);
					}
				}
				catch(NullPointerException nl) {
					JOptionPane.showMessageDialog(null, "Ningun Usuario ha Iniciado Sesion", "Validar Credenciales", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		botonListaDeseados.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		botonListaDeseados.setBounds(10, 378, 194, 30);
		panel.add(botonListaDeseados);
	
	}
	public static void updateUserList(final WebTarget appTarget){
		TiendaGUI.getCliente().setProductosDeseados((ArrayList<Producto>) productos_deseados);
		final WebTarget clientesTarget = appTarget.path("/clientes/update");
		clientesTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(TiendaGUI.getCliente(), MediaType.APPLICATION_JSON));
	}
	public static void updateUser(final WebTarget appTarget){
		final WebTarget clientesTarget = appTarget.path("/clientes").path("/"+TiendaGUI.getCliente().getEmail()).path("/"+ TiendaGUI.getCliente().getPassword());
		Cliente cliente = clientesTarget.request(MediaType.APPLICATION_JSON).get(Cliente.class);
		TiendaGUI.setCliente(cliente);
	}
	public static void main(String[] args) {
        TiendaGUI tiendaGUI = new TiendaGUI();
		tiendaGUI.setVisible(true);
    }
	public static void setCliente(Cliente cliente) {
		TiendaGUI.cliente = cliente;
		cargarLista();
		botonLogin.setText("Log out");
		botonLogin.updateUI();
	}
	private static void cargarLista() {
		productos_deseados.removeAll(productos_deseados);
		for (Producto p :TiendaGUI.getCliente().getProductosDeseados()){
			productos_deseados.add(p);
		}
	}
	public static Cliente getCliente() {
		return TiendaGUI.cliente;
	}

    public static void setButtons() {
        btnDeseado.setVisible(true);
		botonComprar.setVisible(true);
		botonAnyadir.setVisible(true);
    }
}