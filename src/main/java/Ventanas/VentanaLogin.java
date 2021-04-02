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
import javax.swing.DropMode;

public class VentanaLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pCentral;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JTextField emailTextField;
	private JTextField password;
	private JButton bAceptar;
	private JLabel lEmail;
	private JLabel lPassword;
	private JLabel lTexto;
	private HashMap<String, String> hmComprobar;
	private static JPasswordField passwordField;
	
	public VentanaLogin(final JFrame ventanaPadre) {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(599, 336);
		setLocation(400, 150);
		setTitle("Inicio de sesion");
		
		pCentral = new JPanel();

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
		
		password = new JTextField(50);
		password.setBackground(Color.WHITE);
		password.setPreferredSize(new Dimension(50, 35));
//		VentanaLogin.crearBoxLayout(pCentral, "Password:",password);
		
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(null);
		JLabel Email = new JLabel("Email:");
		Email.setToolTipText("");
		Email.setBounds(22, 37, 54, 35);
		pCentral.add(Email);
		Email.setForeground(Color.BLACK);
		Email.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		Email.setPreferredSize(new Dimension(200, 35));
		
		emailTextField = new JTextField(50);
		emailTextField.setBounds(86, 40, 472, 35);
		pCentral.add(emailTextField);
		emailTextField.setBackground(Color.WHITE);
		emailTextField.setPreferredSize(new Dimension(50, 35));
		//		pCentral.add(password);
		//		pCentral.add(email);
				
//				VentanaLogin.crearBoxLayout(pCentral, "Email:", emailTextField);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(114, 129, 444, 35);
				pCentral.add(passwordField);
//				passwordField.setDropMode(DropMode.ON);
				
				JLabel lblPassword = new JLabel("Password:");
				lblPassword.setBounds(22, 132, 82, 23);
				pCentral.add(lblPassword);
				lblPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		
		bAceptar = new JButton("Aceptar");
		bAceptar.setBounds((this.getWidth()/100)*5, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
	    pInferior.add(bAceptar);
	    
	    bAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		
			});
	    
	    addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	ventanaPadre.setEnabled(true);
            }
        });
	    
	    
		
	}
		
//	public static void main(String[] args) {
//		VentanaLogin v = new VentanaLogin();
//		v.setVisible(true);
//	}
}
