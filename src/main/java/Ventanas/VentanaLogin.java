package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import main.main;

public class VentanaLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pCentral;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JTextField email;
	private JTextField password;
	private JButton bAceptar;
	private JLabel lEmail;
	private JLabel lPassword;
	private JLabel lTexto;
	private HashMap<String, String> hmComprobar;
	private static JPasswordField passwordField;
	
	public VentanaLogin() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(599, 336);
		setLocation(400, 150);
		setTitle("Inicio de sesion");
		
		pCentral = new JPanel();
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));

		pSuperior = new JPanel();
		pSuperior.setEnabled(false);
		pSuperior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pSuperior,BorderLayout.NORTH);
		
		pInferior = new JPanel();
		pInferior.setEnabled(false);
		pInferior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pInferior,BorderLayout.SOUTH);
		
		lTexto = new JLabel("Inicia sesion con tu email");
		lTexto.setForeground(Color.BLACK);
		lTexto.setFont(new Font("Times New Roman", Font.BOLD, 36));
		pSuperior.add(lTexto);
		
		email = new JTextField(50);
		email.setBounds(66, 36, 430, 35);
		email.setBackground(Color.WHITE);
		email.setPreferredSize(new Dimension(50, 35));
		password = new JTextField(50);
		password.setBackground(Color.WHITE);
		password.setPreferredSize(new Dimension(50, 35));
//		pCentral.add(password);
//		pCentral.add(email);
		
		VentanaLogin.crearBoxLayout(pCentral, "Email:", email);
		VentanaLogin.crearBoxLayout(pCentral, "Password:",password);
		
		getContentPane().add(pCentral, BorderLayout.CENTER);
		
		bAceptar = new JButton("Aceptar");
		bAceptar.setBounds((this.getWidth()/100)*5, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
	    pInferior.add(bAceptar);
	    
	    bAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		
			});

		
	}
	
	public static void crearBoxLayout( Container cont, String etiqueta, Component campo) {
		JPanel tempPanel = new JPanel();
		tempPanel.setOpaque(false);
		tempPanel.setLayout(null);
		JLabel l = new JLabel(etiqueta);
		l.setBounds(5, 33, 200, 35);
		l.setForeground(Color.BLACK);
		l.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		l.setPreferredSize(new Dimension(200, 35));
		tempPanel.add( l );
		tempPanel.add( campo );
		cont.add(tempPanel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 141, 406, 35);
		tempPanel.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		lblPassword.setBounds(5, 140, 154, 30);
		tempPanel.add(lblPassword);
	}
		
	public static void main(String[] args) {
		VentanaLogin v = new VentanaLogin();
		v.setVisible(true);
	}
}
