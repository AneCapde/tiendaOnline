package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import java.awt.CardLayout;
import javax.swing.JDesktopPane;
import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Canvas;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.List;
import java.awt.Point;
import javax.swing.ListSelectionModel;

public class TiendaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtPatata;

	
	public TiendaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setEnabled(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 216, 501);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBackground(Color.WHITE);
		list.setBounds(10, 82, 194, 292);
		panel.add(list);
		
		JLabel lblNewLabel = new JLabel("Tags");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 60, 46, 21);
		panel.add(lblNewLabel);
		
		txtPatata = new JTextField();
		txtPatata.setToolTipText("Buscar");
		txtPatata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPatata.setBounds(10, 11, 124, 38);
		panel.add(txtPatata);
		txtPatata.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnNewButton.setToolTipText("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(136, 11, 70, 38);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Historial");
		btnNewButton_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnNewButton_1.setBounds(10, 385, 194, 47);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cesta");
		btnNewButton_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(10, 443, 194, 47);
		panel.add(btnNewButton_2);
		
		List list_1 = new List();
		list_1.setMultipleSelections(false);
		list_1.setBounds(245, 10, 245, 501);
		contentPane.add(list_1);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(507, 40, 309, 315);
		contentPane.add(panel_1);
		
		JLabel lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblCaracteristicas.setBounds(507, 361, 122, 14);
		contentPane.add(lblCaracteristicas);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setScrollPosition(new Point(0, 0));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(507, 381, 309, 83);
		contentPane.add(scrollPane);
		
		JButton btnNewButton_3 = new JButton("COMPRAR");
		btnNewButton_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnNewButton_3.setBounds(507, 470, 309, 41);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Log in");
		btnNewButton_4.setBounds(518, 10, 122, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Sign in");
		btnNewButton_5.setBounds(679, 10, 122, 23);
		contentPane.add(btnNewButton_5);
	}
}
