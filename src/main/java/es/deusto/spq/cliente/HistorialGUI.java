package es.deusto.spq.cliente;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Pedido;
import es.deusto.spq.util.Idiomas;


public class HistorialGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<Pedido> model = new DefaultListModel<>();
	private Pedido pedidoSeleccionado;
	private JButton botonDevolver;
	private List<Pedido> pedidos = new ArrayList<>();
	private JTextArea textArea;
	private JList<Pedido> list;
	
	/**
	 * Ventana en la que se  muestran los pedidos que realiza un cliente determinado, asi como
	 * las caracteristicas del pedido realizado
	 * @param ventanaPadre Ventana anterior, a traves de la cual se a llegado a esta
	 * @param appTarget Objeto para la comunicacion con el server
	 */
	public HistorialGUI(final JFrame ventanaPadre, WebTarget appTarget) {
		
		final WebTarget pedidoTarget = appTarget.path("/pedidos/").path(TiendaGUI.getCliente().getDNI());
		GenericType<List<Pedido>> genericType_pedido = new GenericType<List<Pedido>>() {};
		pedidos = pedidoTarget.request(MediaType.APPLICATION_JSON).get(genericType_pedido);
		
		final WebTarget updateTarget = appTarget.path("/pedidos/update");

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
		
		JLabel lblCaracteristicas = new JLabel(Idiomas.seleccionarPalabra("caracteristicas"));
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
		
		botonDevolver = new JButton(Idiomas.seleccionarPalabra("devolucion"));
		botonDevolver.setForeground(Color.BLACK);
		botonDevolver.setBackground(new Color(0, 255, 0));
		botonDevolver.setBounds(640, 11, 155, 29);
		contentPane.add(botonDevolver);
	    
	    //Boton para realizar la devolucion del producto seleccionado (funcionalidad m??s adelante)
	    botonDevolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pedidoSeleccionado = list.getSelectedValue();
				if (pedidoSeleccionado != null) {
					// Miramos el d??a del pedido para ver si corresponde a los ultimos 14 dias
					if (((System.currentTimeMillis() - pedidoSeleccionado.getFecha().getTime()) / (1000 * 60 * 60 * 24)) < 14) {
						// Miramos si el pedido est?? devuelto ya o no
						if (pedidoSeleccionado.getEstado().equals("Devuelto")) {
							JOptionPane.showMessageDialog(null, "El pedido seleccionado ya est?? devuleto", "Error en la devoluci??n", JOptionPane.INFORMATION_MESSAGE);
						} else
						//Pasamos el pedido seleccionado para editarlo
						devolverPedido(updateTarget, pedidoSeleccionado, ventanaPadre, appTarget);

					} else {
						JOptionPane.showMessageDialog(null, "Ya ha transcurrido el periodo de devoluci??n de 14 d??as", "Error en la devoluci??n", JOptionPane.INFORMATION_MESSAGE);
					}
				} 
			}
	    });
		
	    JButton btnInicio = new JButton(Idiomas.seleccionarPalabra("volverBoton"));
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
	
	/**
	 * M??todo que permite volver a la ventana principal TiendaGUI
	 */
	
	private void botonInicio() {
		setVisible(false);
		TiendaGUI tienda = new TiendaGUI();
		tienda.setVisible(true);
		TiendaGUI.setCliente(TiendaGUI.getCliente());
		dispose();
		
	}
	
	
	/** 
	 * M??todo que permite devolver un pedido y cambia el estado del mismo a "devuelto"
	 * @param updateTarget 
	 * @param pedido Pedido a actualizar
	 * @param ventanaPadre
	 * @param appTarget
	 */
	private void devolverPedido(WebTarget updateTarget, Pedido pedido, JFrame ventanaPadre, WebTarget appTarget) {
		pedido.setEstado("Devuelto");
		updateTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
		setVisible(false);

		HistorialGUI h = new HistorialGUI(ventanaPadre,appTarget);
		h.setVisible(true);
		JOptionPane.showMessageDialog(null, "Deber??s concretar la entrega del paquete escibiendo al email \n devolucion@tienda.com y se te reembolsar?? el dinero del pedido.", "Devoluci??n completada", JOptionPane.INFORMATION_MESSAGE);

	}
	
	/**
	 * M??todo que permite cargar la lista con la informaci??n del pedido seleccionado en el 
	 * modelo
	 */

	private void cargarLista() {
		pedidoSeleccionado = list.getSelectedValue();
		textArea.setText(null);
		if (pedidoSeleccionado != null) {
			//un pedido puede tener mas de un producto coger la imagen de todos los productos
			//textArea.append(pedidoSeleccionado.getProducto().getImage() + "\n");
			textArea.append("- CLIENTE: " + pedidoSeleccionado.getCliente().DNI + "\n");
			textArea.append("- DESCRIPCI??N: " + pedidoSeleccionado.getFecha() + "\n");
			textArea.append("- PRODUCTO: " + pedidoSeleccionado.getProducto() + "\n");
			textArea.append("- ESTADO: " + pedidoSeleccionado.getEstado() + "\n");
			textArea.append(" -IMPORTE: " + pedidoSeleccionado.getImporte() + "\n");
			textArea.append(" -CANTIDAD: " + pedidoSeleccionado.getCantidad() + "\n");
			textArea.append(" -LUGAR: " + pedidoSeleccionado.getLugar() + "\n");
			
			
		}
		
	}

}
