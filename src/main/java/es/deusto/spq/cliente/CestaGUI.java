package es.deusto.spq.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.client.WebTarget;

import es.deusto.spq.models.Producto;
import es.deusto.spq.util.Idiomas;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class CestaGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	public static DefaultListModel<Producto> model = new DefaultListModel<>();
	public ArrayList<Producto> productos2 = new ArrayList<>();
	public HashMap<Producto, Integer> productos_cantidad = new HashMap<Producto, Integer>();
	final WebTarget pedidoTarget;
	public static JList<Producto> list;

	/**
	 * Create the frame.
	 */
	public CestaGUI(final JFrame ventanaPadre, ArrayList<Producto> productos, final WebTarget appTarget) {
		
		final CestaGUI esto = this;
		pedidoTarget = appTarget.path("/pedidos");
		
		for (Producto p : productos){
			System.out.println("Lista de la Tienda: "+ p);
			productos2.add(p);
			System.out.println("Lista de la Cesta: "+ productos2);
			model.addElement(p);
			getProductosCantidad().put(p, 1);
			System.out.println("Metodo get productos cantidad: " + getProductosCantidad());
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCarro = new JLabel(Idiomas.seleccionarPalabra("cestaBoton"));
		lblCarro.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCarro.setBounds(10, 11, 134, 14);
		contentPane.add(lblCarro);

		
		JButton btnNewButton = new JButton(Idiomas.seleccionarPalabra("comprarBoton"));
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnNewButton.setBounds(346, 417, 143, 48);
		contentPane.add(btnNewButton);
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener (new ActionListener () {
			@Override
		    public void actionPerformed(ActionEvent e) {
				System.out.println("Boton Aceptar: " +productos2);
				VentanaLugarEntregaGUI vle = new VentanaLugarEntregaGUI(esto, productos2, appTarget, calcularPrecio() );
				vle.setVisible(true);
				esto.setEnabled(false);
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(269, 27, 309, 339);
		contentPane.add(panel);
		
	
		list = new JList<>(model);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 27, 236, 387);
		contentPane.add(list);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnNewButton.setVisible(true);
				panel.removeAll();
				Producto producto = list.getSelectedValue();
				if (producto != null) {
					ImageIcon icono_1 = new ImageIcon(getClass().getResource("/"+ producto.getImagen()));
					ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(),Image.SCALE_DEFAULT));
					JLabel label = new JLabel(icono_2);
					panel.add(label);
					panel.revalidate();
				}
				
			}
		});
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(1.0, 1.0, 20.0, 1.0);
		JSpinner spinner = new JSpinner(model1);
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner.setBounds(361, 377, 40, 29);
		contentPane.add(spinner);
		
		JLabel lblCantidad = new JLabel(Idiomas.seleccionarPalabra(""));
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCantidad.setBounds(269, 377, 134, 22);
		contentPane.add(lblCantidad);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(416, 377, 60, 29);
		contentPane.add(btnOk);
		btnOk.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				Producto producto = list.getSelectedValue();
				int cantidad = Integer.valueOf(String.valueOf(Math.round((double) spinner.getValue())));
				try {
					if (!producto.equals(null)) {
						textField.setText(String.valueOf(calcularPrecio()));
						getProductosCantidad().put(producto, cantidad);
					}
				}
				catch(NullPointerException nl) {
					return;
				}
			}
		});

		JButton btnEliminar = new JButton(Idiomas.seleccionarPalabra("eliminarBoton"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(486, 377, 90, 29);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener (new ActionListener () {
			@Override
		    public void actionPerformed(ActionEvent e) {
				Producto producto = list.getSelectedValue();
				getProductos().remove(producto);
				model.removeElement(producto);
				panel.removeAll();
				list.setModel( model);
				textField.setText(String.valueOf(calcularPrecio()));
			}
		});
		JLabel lblPrecio = new JLabel(Idiomas.seleccionarPalabra("precio"));
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPrecio.setBounds(10, 433, 81, 27);
		contentPane.add(lblPrecio);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		textField.setText(String.valueOf(calcularPrecio()));
		textField.setEditable(false);
		textField.setBounds(88, 431, 145, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
	}
	
	
	/** 
	 * Calcula el precio del pedido mediante la suma de la cantidad de cada producto por su precio
	 * @return int precio total del pedido
	 */
	public int calcularPrecio() {
		int precioTotal = 0;
		for (Producto p : productos_cantidad.keySet()){
			precioTotal += p.getPrecio()*productos_cantidad.get(p);
		}
		return precioTotal;
	}

	
	/** 
	 * @return List<Producto> lista de productos que el usuario a añadido a la cesta
	 */
	public List<Producto> getProductos(){
        return productos2;
	}
	
	
	/** 
	 * @return HashMap<Producto, Integer> cada producto dispone de un int con la cantidad que se desea pedir
	 */
	public HashMap<Producto, Integer> getProductosCantidad() {
		return productos_cantidad;
	}
}
