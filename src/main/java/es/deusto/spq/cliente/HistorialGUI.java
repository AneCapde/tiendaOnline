package es.deusto.spq.cliente;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;

@SuppressWarnings("serial")
public class HistorialGUI extends JFrame{
	
	private JPanel contentPane;
	private DefaultListModel<Pedido> model = new DefaultListModel<>();
	private DefaultListModel<Producto> model2 = new DefaultListModel<>();
	private Pedido pedidoSeleccionado;
	private Producto productoSeleccionado;
	private JButton botonDevolver;
	private ArrayList<Producto> productos = new ArrayList<>();
	private List<Pedido> pedidos = new ArrayList<>();
	private JTextArea textArea;
	private JList<Pedido> list;
	
	
	
	public HistorialGUI(final JFrame ventanaPadre, WebTarget appTarget) {
		
		final WebTarget pedidoTarget = appTarget.path("/pedidos/").path(TiendaGUI.getCliente().getDNI());
		GenericType<List<Pedido>> genericType_pedido = new GenericType<List<Pedido>>() {};
		pedidos = pedidoTarget.request(MediaType.APPLICATION_JSON).get(genericType_pedido);
		
		System.out.println(pedidos + "kfjwofjiwqjgiwqjgpi");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 872, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPedidosDeseados = new JLabel("Historial de Pedidos");
		lblPedidosDeseados.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblPedidosDeseados.setBounds(20, 11, 382, 14);
		contentPane.add(lblPedidosDeseados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 37, 400, 457);
		contentPane.add(scrollPane);
		
//		JScrollPane scrollPane2 = new JScrollPane();
//		scrollPane2.setBounds(500, 37, 400, 457);
//		contentPane.add(scrollPane2);
//
//		JPanel imagePlacehold = new JPanel();
//		imagePlacehold.setBounds(464, 51, 367, 260);
//		imagePlacehold.setBackground(Color.WHITE);
//		contentPane.add(imagePlacehold);
		
		JLabel lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setBounds(452, 192, 162, 14);
		lblCaracteristicas.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		contentPane.add(lblCaracteristicas);
		
		textArea = new JTextArea();
		textArea.setBounds(452, 220, 382, 180);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		for (int i = 0; i < pedidos.size(); i++) {
			model.addElement(pedidos.get(i)); 
		}
		
		//JList para los pedidos
		list = new JList<>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		//Para que salgan la informacion (caracteristicas) de cada pedido

		list.addListSelectionListener(new ListSelectionListener() {
		
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				cargarLista();
				
			}

			
		});
		
		//Obtenemos los productos que hay dentro de un pedido para mostrar el articulo junto con la foto
//		for (Pedido pedido : pedidos) {
//			productos = pedido.getProducto();
//		}
//		
//		//JList para los productos que hay dentro de un determinado pedido
//		for (int i = 0; i < productos.size(); i++) {
//			model2.addElement(productos.get(i)); 
//		}
//		
		//JList para los productos
		
//		JList<Producto> list2 = new JList<>(model2);
//		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		scrollPane2.setViewportView(list2);
//		list2.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				productoSeleccionado = list2.getSelectedValue();
//				textArea.setText(null);
//				if (productoSeleccionado != null) {
//					imagePlacehold.removeAll();
//					ImageIcon icono_3 = new ImageIcon(getClass().getResource("/"+ productoSeleccionado.getImagen()));
//					ImageIcon icono_4 = new ImageIcon(icono_3.getImage().getScaledInstance(imagePlacehold.getWidth(), imagePlacehold.getHeight(),Image.SCALE_DEFAULT));
//					JLabel label = new JLabel(icono_4);
//					imagePlacehold.add(label);
//					imagePlacehold.revalidate();
//					
//					textArea.append("- NOMBRE: " + productoSeleccionado.nombre + "\n");
//					textArea.append("- DESCRIPCIÓN: " + productoSeleccionado.descripcion + "\n");
//					textArea.append("- PRECIO: " + productoSeleccionado.precio + "\n");
//					textArea.append("- CATEGORÍA: " + productoSeleccionado.getSubcategoria().getCategoria().getNombre() + "\n");
//					textArea.append("    SUBCATEGORÍA: " + productoSeleccionado.getSubcategoria().getNombre() + "\n");
//					
//				}
//			}
//		});

		
		botonDevolver = new JButton("DEVOLUCIÓN");
		botonDevolver.setForeground(Color.BLACK);
		botonDevolver.setBackground(new Color(0, 255, 0));
		botonDevolver.setBounds(650, 11, 109, 29);
	    contentPane.add(botonDevolver);
	    
	    //Boton para realizar la devolucion del producto seleccionado (funcionalidad más adelante)
	    botonDevolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				pedidoSeleccionado = list.getSelectedValue();
//				if (pedidoSeleccionado != null) {
//				
//			}	 
			}
	    });
		
	    JButton btnInicio = new JButton("INICIO");
		btnInicio.setForeground(Color.BLACK);
		btnInicio.setBackground(new Color(0, 255, 0));
		btnInicio.setBounds(469, 11, 109, 29);
		contentPane.add(btnInicio);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonInicio();
				
			}

			
		});
		
		
		
	}
	
	private void botonInicio() {
		setVisible(false);
		TiendaGUI tienda = new TiendaGUI();
		tienda.setVisible(true);
		TiendaGUI.setCliente(TiendaGUI.getCliente());
		dispose();
		
	}
	
	private void cargarLista() {
		pedidoSeleccionado = list.getSelectedValue();
		textArea.setText(null);
		if (pedidoSeleccionado != null) {
			//un pedido puede tener mas de un producto coger la imagen de todos los productos
			//textArea.append(pedidoSeleccionado.getProducto().getImage() + "\n");
			textArea.append("- CLIENTE: " + pedidoSeleccionado.getCliente().DNI + "\n");
			textArea.append("- DESCRIPCIÓN: " + pedidoSeleccionado.getFecha() + "\n");
			textArea.append("- PRODUCTO: " + pedidoSeleccionado.getProducto() + "\n");
			textArea.append("- ESTADO: " + pedidoSeleccionado.getEstado() + "\n");
			textArea.append(" -IMPORTE: " + pedidoSeleccionado.getImporte() + "\n");
			textArea.append(" -CANTIDAD: " + pedidoSeleccionado.getCantidad() + "\n");
			
			
		}
		
	}

}
