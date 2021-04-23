package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javax.ws.rs.client.WebTarget;

import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;

@SuppressWarnings("serial")
public class HistorialGUI extends JFrame{
	
	private JPanel contentPane;
	private DefaultListModel<Producto> model = new DefaultListModel<>();
	private Pedido pedidoSeleccionado;
	private JList<Pedido> listaElementos;
	private JButton botonDevolver;
	
	
	public HistorialGUI(final JFrame ventanaPadre, List<Pedido> pedidos, WebTarget appTarget) {
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
		
		JLabel lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setBounds(452, 192, 162, 14);
		lblCaracteristicas.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		contentPane.add(lblCaracteristicas);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(452, 220, 382, 180);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		botonDevolver = new JButton("Devolución");
		botonDevolver.setBounds((this.getWidth()/100)*5, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
	    contentPane.add(botonDevolver);
	    
	    //Boton para realizar la devolucion del producto seleccionado (funcionalidad más adelante)
	    botonDevolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
		
		//Para que salgan la informacion (caracteristicas) de cada pedido
//		listaElementos = new JList<Pedido>(model);
//		listaElementos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		scrollPane.setViewportView(listaElementos);
//		listaElementos.addListSelectionListener(new ListSelectionListener() {
//			
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				productoSeleccionado = listaElementos.getSelectedValue();
//				textArea.setText(null);
//				if (productoSeleccionado != null) {
//					textArea.append(productoSeleccionado.getImagen() + "\n");
//					textArea.append("- NOMBRE: " + productoSeleccionado.nombre + "\n");
//					textArea.append("- DESCRIPCIÓN: " + productoSeleccionado.descripcion + "\n");
//					textArea.append("- PRECIO: " + productoSeleccionado.precio + "\n");
//					textArea.append("- CATEGORÍA: " + productoSeleccionado.getSubcategoria().getCategoria().getNombre() + "\n");
//					textArea.append("    SUBCATEGORÍA: " + productoSeleccionado.getSubcategoria().getNombre() + "\n");
//					
//				}
//			}
//		});
		
		
		
		
	}

}
