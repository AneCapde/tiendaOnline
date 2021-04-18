package es.deusto.spq.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class CestaGUI extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private DefaultListModel<Producto> model = new DefaultListModel<>();
	private HashMap<Producto,Integer> productos_cantidad = new HashMap<>();

	/**
	 * Create the frame.
	 */
	public CestaGUI(List<Producto> productos, final WebTarget appTarget) {

		for (Producto p:productos){
			productos_cantidad.put(p, 1);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		for (int i = 0; i < productos.size(); i++) {
			model.addElement(productos.get(i)); 
		}
	
		JList<Producto> list = new JList<>(model);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 27, 236, 387);
		contentPane.add(list);
		
		JLabel lblCarro = new JLabel("CARRO");
		lblCarro.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblCarro.setBounds(10, 11, 134, 14);
		contentPane.add(lblCarro);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(269, 27, 309, 339);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("COMPRAR");
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnNewButton.setBounds(346, 417, 143, 48);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				for(Producto p : productos_cantidad.keySet()){
					Date date = new Date();
					int precio_pedido = productos_cantidad.get(p)*p.getPrecio();
					Pedido pedido = new Pedido(TiendaGUI.getCliente(), date,"en proceso" , precio_pedido, productos_cantidad.get(p), p);
					appTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
				}
		    	
			}
		});
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(1.0, 1.0, 20.0, 1.0);
		JSpinner spinner = new JSpinner(model1);
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner.setBounds(361, 377, 40, 29);
		contentPane.add(spinner);
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCantidad.setBounds(269, 377, 134, 22);
		contentPane.add(lblCantidad);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(416, 377, 60, 29);
		contentPane.add(btnOk);
		btnOk.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				Producto producto = list.getSelectedValue();
				int cantidad = (Integer) spinner.getValue();
				productos_cantidad.put(producto, cantidad);
				textField.setText(String.valueOf(calcularPrecio()));
			}
		});


		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(486, 377, 90, 29);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				Producto producto = list.getSelectedValue();
				productos_cantidad.remove(producto);
				model.removeElement(producto);
				list.setModel( model);
				textField.setText(String.valueOf(calcularPrecio()));
			}
		});
		JLabel lblPrecio = new JLabel("Precio:");
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

	private int calcularPrecio(){
		int precioTotal = 0;
		for (Producto p : productos_cantidad.keySet()){
			precioTotal += p.getPrecio()*productos_cantidad.get(p);
		}
		return precioTotal;
	}
}
