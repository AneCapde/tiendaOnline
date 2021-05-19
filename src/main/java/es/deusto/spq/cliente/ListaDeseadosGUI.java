package es.deusto.spq.cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Producto;
import es.deusto.spq.util.Idiomas;

public class ListaDeseadosGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Producto> productos_deseados = new ArrayList<>();
	public static JPanel contentPane, imagePlacehold;
	public static JTextArea textArea;
	public static DefaultListModel<Producto> model = new DefaultListModel<>();
	public static Producto productoSeleccionado;
	public static JList<Producto> listaElementos;
	public static JButton botonAnyadir, btnEliminar;
	static Cliente cliente;
	final WebTarget clientesTarget;
	
	/** Ventana donde se encuentran todos los articulos que el cliente ha indicado que son sus favoritos
	 * 
	 * @param ventanaPadre Ventana anterior, a traves de la cual se a llegado a esta
	 * @param appTarget Objeto para la comunicacion con el server
	 */
	public ListaDeseadosGUI(final JFrame ventanaPadre, final WebTarget appTarget) {
		clientesTarget = appTarget.path("/clientes/update");
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 872, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductosDeseados = new JLabel("Productos Deseados");
		lblProductosDeseados.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblProductosDeseados.setBounds(20, 11, 382, 14);
		contentPane.add(lblProductosDeseados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 37, 400, 457);
		contentPane.add(scrollPane);
		
		JLabel lblCaracteristicas = new JLabel(Idiomas.seleccionarPalabra("caracteristicas"));
		lblCaracteristicas.setBounds(464, 322, 162, 14);
		lblCaracteristicas.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		contentPane.add(lblCaracteristicas);
		
		textArea = new JTextArea();
		textArea.setBounds(464, 347, 367, 116);
		contentPane.add(textArea);
		
		imagePlacehold = new JPanel();
		imagePlacehold.setBounds(464, 51, 367, 260);
		imagePlacehold.setBackground(Color.WHITE);
		contentPane.add(imagePlacehold);
		
		botonAnyadir = new JButton(Idiomas.seleccionarPalabra("anyadircestaBoton"));
		botonAnyadir.setBounds(605, 11, 212, 29);
		botonAnyadir.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		contentPane.add(botonAnyadir);
		botonAnyadir.setVisible(false);
		botonAnyadir.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	anyadir();
			}
		});
		
	 	btnEliminar = new JButton(Idiomas.seleccionarPalabra("eliminarBoton"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(733, 487, 98, 23);
		contentPane.add(btnEliminar);
		btnEliminar.setVisible(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		
		listaElementos = new JList<Producto>(model);
		listaElementos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listaElementos);
		listaElementos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				productoSeleccionado = listaElementos.getSelectedValue();
				imagenes(productoSeleccionado);
			}
		});

		JButton btnInicio = new JButton(Idiomas.seleccionarPalabra("volverBoton"));
		btnInicio.setForeground(Color.BLACK);
		btnInicio.setBackground(new Color(0, 255, 0));
		btnInicio.setBounds(469, 11, 109, 29);
		contentPane.add(btnInicio);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio(appTarget);
			}
		});	
	}
	
	/**
	 * Metodo para volver a la ventana de inicio de la tienda
	 * @param appTarget Objeto para la comunicacion con el server
	 */
	public void Inicio(final WebTarget appTarget) {
		setVisible(false);
		updateClient(appTarget);
		TiendaGUI tienda = new TiendaGUI();
		tienda.setVisible(true);
		TiendaGUI.setCliente(TiendaGUI.getCliente());
		dispose();
	}
	
	/**
	 * Metodo que estable la conexion con el servidor del suaurio conectado a la Tienda
	 * @param appTarget Objeto para la comunicacion con el server
	 */
	public void updateClient(final WebTarget appTarget){
		System.out.println(productos_deseados);
		TiendaGUI.getCliente().setProductosDeseados((ArrayList<Producto>) productos_deseados);
		clientesTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(TiendaGUI.getCliente(), MediaType.APPLICATION_JSON));
	}
		
	/**
	 * Metodo para eliminar un producto de la lista de deseados
	 * @return devuelve la lista con los productos deseados
	 */
	public ArrayList<Producto> eliminar() {
		Producto p1 = listaElementos.getSelectedValue();
		model.removeElement(p1);
		listaElementos.setModel(ListaDeseadosGUI.model);
		Producto producto = null;
		for (Producto p : productos_deseados){
			if (p1.getNombre().equals(p.getNombre())) {
				producto = p;
			}
		}
		getProductosDeseados().remove(producto);
		return productos_deseados;
	}
	
	/**
	 * Metodo para añadir un producto a el carrito de la cesta
	 * @return devuelve la lista con los productos deseados
	 */
	public ArrayList<Producto> anyadir() {
		productoSeleccionado = listaElementos.getSelectedValue();
		 if (!TiendaGUI.productos_cesta.contains(productoSeleccionado)){
		 	TiendaGUI.productos_cesta.add(productoSeleccionado);
		 }
		return productos_deseados;
	}
	
	/**
	 * Metodo para indicar que cliente hay actualmente en la tienda
	 * @param cliente Objeto cliente
	 * @return devuleve el cliente que esta actualmente logeado en la tienda
	 */
	public static Cliente setCliente(Cliente cliente) {
		ListaDeseadosGUI.cliente = cliente;
		anyadirProductosDeseados();
		return cliente;
	}
	
	/**
	 * Metodo para añadir los productos deseados a esta ventana y hacer que se visualicen
	 * @return devuelve la lista con los productos deseados
	 */
	public static ArrayList<Producto> anyadirProductosDeseados() {
		productos_deseados.removeAll(productos_deseados);
		model.removeAllElements();
		System.out.println("Cliente: " +TiendaGUI.getCliente());
		System.out.println("Productos Deseados: " + TiendaGUI.getCliente().getProductosDeseados());
		for (Producto p : TiendaGUI.getCliente().getProductosDeseados()){
			getProductosDeseados().add(p);
		}
		for (int i = 0; i < productos_deseados.size(); i++) {
			model.addElement(productos_deseados.get(i));
		}
		return productos_deseados;
	}
	
	/**
	 * Metodo que devuleve una lista con los productos deseados
	 * @return devuelve la lista con los productos deseados
	 */
	public static ArrayList<Producto> getProductosDeseados() {
		return productos_deseados;
	}
	
	/**
	 * Establece los productos que se han marcado como deseados
	 * @param productos_deseados lista con los productos deseados
	 */
	public void setProductosDeseados(ArrayList<Producto> productos_deseados) {
		ListaDeseadosGUI.productos_deseados = productos_deseados;
	}
	
	/**
	 * Metodo que al seleccionar un producto muestra su imagen
	 * @param producto ProductoSeleccionado por el usuario
	 */
	public void imagenes(Producto producto) {
		botonAnyadir.setVisible(true);
		btnEliminar.setVisible(true);
		textArea.setText(null);
		if (producto != null) {
			ListaDeseadosGUI.imagePlacehold.removeAll();
			ImageIcon icono_3 = new ImageIcon(getClass().getResource("/"+ productoSeleccionado.getImagen()));
			ImageIcon icono_4 = new ImageIcon(icono_3.getImage().getScaledInstance(imagePlacehold.getWidth(), imagePlacehold.getHeight(),Image.SCALE_DEFAULT));
			JLabel label = new JLabel(icono_4);
			imagePlacehold.add(label);
			imagePlacehold.revalidate();
			
			textArea.append("- NOMBRE: " + producto.nombre + "\n");
			textArea.append("- DESCRIPCIÓN: " + producto.descripcion + "\n");
			textArea.append("- PRECIO: " + producto.precio + "\n");
			textArea.append("- CATEGORÍA: " + producto.getSubcategoria().getCategoria().getNombre() + "\n");
			textArea.append("    SUBCATEGORÍA: " + producto.getSubcategoria().getNombre() + "\n");
			
		}
	}
}
