package es.deusto.spq.cliente;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Producto;

@SuppressWarnings("serial")
public class ListaDeseadosGUI extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Producto> model = new DefaultListModel<>();
	private Producto productoSeleccionado;
	private JList<Producto> listaElementos;

	public ListaDeseadosGUI(final JFrame ventanaPadre, WebTarget appTarget, TiendaGUI tienda) {
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
		lblCaracteristicas.setBounds(464, 322, 162, 14);
		lblCaracteristicas.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		contentPane.add(lblCaracteristicas);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(464, 347, 367, 116);
		contentPane.add(textArea);
		
		JTextArea imagePlacehold = new JTextArea();
		imagePlacehold.setBounds(464, 51, 367, 260);
		imagePlacehold.setBackground(Color.WHITE);
		contentPane.add(imagePlacehold);
		
		System.out.println(tienda.getCliente().getProductosDeseados());
		ArrayList<Producto> productos = tienda.getCliente().getProductosDeseados();
		for (int i = 0; i < productos.size(); i++) {
			model.addElement(productos.get(i));
		}
		
		System.out.println(tienda.getCliente().getProductosDeseados());
		listaElementos = new JList<Producto>(model);
		listaElementos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listaElementos);
		listaElementos.addListSelectionListener(new ListSelectionListener() {
			
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				productoSeleccionado = listaElementos.getSelectedValue();
				textArea.setText(null);
				if (productoSeleccionado != null) {
					imagePlacehold.removeAll();
					ImageIcon icono_3 = new ImageIcon(getClass().getResource("/"+ productoSeleccionado.getImagen()));
					JLabel label = new JLabel(icono_3);
					imagePlacehold.add(label);
					imagePlacehold.revalidate();
					
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
		btnEliminar.setBounds(733, 487, 98, 23);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Se borra de la lista, falta que se borre de la base de datos
				Producto producto = listaElementos.getSelectedValue();
				model.removeElement(producto);
				listaElementos.setModel(model);
				tienda.getCliente().removeProducto(productoSeleccionado); //no se estan borrando los roductos de la base de datos
//				final WebTarget clientesTarget = appTarget.path("/clientes").path("/"+TiendaGUI.getCliente());
//				clientesTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(TiendaGUI.getCliente(), MediaType.APPLICATION_JSON));
			}

		});

		JButton btnInicio = new JButton("INICIO");
		btnInicio.setForeground(Color.BLACK);
		btnInicio.setBackground(new Color(0, 255, 0));
		btnInicio.setBounds(469, 11, 109, 29);
		contentPane.add(btnInicio);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				TiendaGUI tienda = new TiendaGUI();
				tienda.setVisible(true);
				dispose();
			}
		});
		
		JButton botonAnyadir = new JButton("Añadir A la Cesta");
		botonAnyadir.setBounds(605, 11, 212, 29);
		botonAnyadir.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		contentPane.add(botonAnyadir);
		botonAnyadir.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				productoSeleccionado =  listaElementos.getSelectedValue();
				if (!TiendaGUI.productos_cesta.contains(productoSeleccionado)){
					TiendaGUI.productos_cesta.add(productoSeleccionado);
				}
			}
		});
		
		
	}
}
