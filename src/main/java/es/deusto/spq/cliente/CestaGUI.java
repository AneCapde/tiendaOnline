package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class CestaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public CestaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
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
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner.setBounds(361, 377, 40, 29);
		contentPane.add(spinner);
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCantidad.setBounds(269, 377, 134, 22);
		contentPane.add(lblCantidad);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(new Color(128, 0, 0));
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(466, 377, 90, 29);
		contentPane.add(btnEliminar);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPrecio.setBounds(10, 433, 81, 27);
		contentPane.add(lblPrecio);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		textField.setText("0");
		textField.setEditable(false);
		textField.setBounds(88, 431, 145, 30);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
