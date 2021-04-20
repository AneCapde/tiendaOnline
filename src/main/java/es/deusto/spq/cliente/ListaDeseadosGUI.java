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
import javax.ws.rs.client.WebTarget;

import es.deusto.spq.models.Producto;

@SuppressWarnings("serial")
public class ListaDeseadosGUI extends JFrame {

//	private Producto productoSeleccionado;
//	private static Cliente cliente;
	private JPanel contentPane;
	private DefaultListModel<Producto> model = new DefaultListModel<>();
	private Producto productoSeleccionado;
	private JList<Producto> listaElementos;

	public ListaDeseadosGUI(final JFrame ventanaPadre, WebTarget appTarget) {
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
		
		JLabel lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setBounds(452, 192, 162, 14);
		lblCaracteristicas.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		contentPane.add(lblCaracteristicas);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(452, 220, 382, 180);
		contentPane.add(textArea);
		
		
		ArrayList<Producto> productos = TiendaGUI.getCliente().getProductosDeseados();
		for (int i = 0; i < productos.size(); i++) {
			model.addElement(productos.get(i));
		}
		
		
		listaElementos = new JList<Producto>(model);
		listaElementos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listaElementos);
		listaElementos.addListSelectionListener(new ListSelectionListener() {
			
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				productoSeleccionado = listaElementos.getSelectedValue();
				textArea.setText(null);
				if (productoSeleccionado != null) {
					textArea.append(productoSeleccionado.getImagen() + "\n");
					textArea.append("- NOMBRE: " + productoSeleccionado.nombre + "\n");
					textArea.append("- DESCRIPCIÓN: " + productoSeleccionado.descripcion + "\n");
					textArea.append("- PRECIO: " + productoSeleccionado.precio + "\n");
					textArea.append("- CATEGORÍA: " + productoSeleccionado.getSubcategoria().getCategoria().getNombre() + "\n");
					textArea.append("    SUBCATEGORÍA: " + productoSeleccionado.getSubcategoria().getNombre() + "\n");
					
				}
			}
		});
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(730, 440, 103, 41);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Se borra de la lista, falta que se borre de la base de datos
				Producto producto = listaElementos.getSelectedValue();
				model.removeElement(producto);
				listaElementos.setModel( model);
//				TiendaGUI.getCliente().removeProducto(productoSeleccionado); //no se estan borrando los roductos de la base de datos
//				final WebTarget clientesTarget = appTarget.path("/clientes");
//				clientesTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(TiendaGUI.getCliente(), MediaType.APPLICATION_JSON));
			}

		});

		JButton btnInicio = new JButton("INICIO");
		btnInicio.setForeground(Color.BLACK);
		btnInicio.setBackground(new Color(0, 255, 0));
		btnInicio.setBounds(498, 28, 116, 36);
		contentPane.add(btnInicio);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				TiendaGUI tienda = new TiendaGUI();
				tienda.setVisible(true);
				contentPane.setEnabled(false);
			}
		});
		
		
	}
}
