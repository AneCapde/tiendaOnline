package es.deusto.spq.cliente;

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

	private List<Producto> productos_deseados = new ArrayList<>();
	private JPanel contentPane;
	public static DefaultListModel<Producto> model = new DefaultListModel<>();
	private Producto productoSeleccionado;
	public static JList<Producto> listaElementos;

	public ListaDeseadosGUI(final JFrame ventanaPadre, WebTarget appTarget, TiendaGUI tienda) {
		productos_deseados.removeAll(productos_deseados);
		model.removeAllElements();

		for (Producto p : TiendaGUI.getCliente().getProductosDeseados()){
			productos_deseados.add(p);
		}
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
		
		JPanel imagePlacehold = new JPanel();
		imagePlacehold.setBounds(464, 51, 367, 260);
		imagePlacehold.setBackground(Color.WHITE);
		contentPane.add(imagePlacehold);
		
		JButton botonAnyadir = new JButton("Añadir A la Cesta");
		botonAnyadir.setBounds(605, 11, 212, 29);
		botonAnyadir.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		contentPane.add(botonAnyadir);
		botonAnyadir.setVisible(false);
		botonAnyadir.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				productoSeleccionado =  listaElementos.getSelectedValue();
				// if (!TiendaGUI.productos_cesta.contains(productoSeleccionado)){
				// 	TiendaGUI.productos_cesta.add(productoSeleccionado);
				// }
			}
		});
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(733, 487, 98, 23);
		contentPane.add(btnEliminar);
		btnEliminar.setVisible(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Producto p1 = listaElementos.getSelectedValue();
				model.removeElement(p1);
				listaElementos.setModel(model);
				Producto producto = null;
				for (Producto p : productos_deseados){
					if (p1.getNombre().equals(p.getNombre())) {
						producto = p;
					}
				}
				productos_deseados.remove(producto);
			}
		});
		
		for (int i = 0; i < productos_deseados.size(); i++) {
			model.addElement(productos_deseados.get(i));
		}
		
		listaElementos = new JList<Producto>(model);
		listaElementos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listaElementos);
		listaElementos.addListSelectionListener(new ListSelectionListener() {
			
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				botonAnyadir.setVisible(true);
				btnEliminar.setVisible(true);
				productoSeleccionado = listaElementos.getSelectedValue();
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
		});

		JButton btnInicio = new JButton("INICIO");
		btnInicio.setForeground(Color.BLACK);
		btnInicio.setBackground(new Color(0, 255, 0));
		btnInicio.setBounds(469, 11, 109, 29);
		contentPane.add(btnInicio);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				updateClient(appTarget);
				TiendaGUI tienda = new TiendaGUI();
				tienda.setVisible(true);
				TiendaGUI.setCliente(TiendaGUI.getCliente());
				dispose();
			}
		});
		
	}
	public void updateClient(final WebTarget appTarget){
		System.out.println(productos_deseados);
		TiendaGUI.getCliente().setProductosDeseados((ArrayList<Producto>) productos_deseados);
		final WebTarget clientesTarget = appTarget.path("/clientes/update");
		clientesTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(TiendaGUI.getCliente(), MediaType.APPLICATION_JSON));
	}
}
