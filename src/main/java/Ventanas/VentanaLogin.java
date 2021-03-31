package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import main.main;

//NO ESTA TERMINADA
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
	
	public VentanaLogin() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600, 530);
		setLocation(400, 150);
		setTitle("Inicio de sesion");
		
		pCentral = new JPanel();
		pCentral.setEnabled(false);
		pCentral.setBackground(Color.GRAY);
		getContentPane().add(pCentral,BorderLayout.CENTER);
		
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
		
		lEmail = new JLabel("Email:");
		lEmail.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lEmail.setBounds(10, 433, 81, 27);
		pCentral.add(lEmail);
		
		lPassword = new JLabel("Password:");
		lPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lPassword.setBounds(266, 153, 61, 16);
		pCentral.add(lPassword);
		
		email = new JTextField(50);
		email.setBackground(Color.WHITE);
		email.setBounds(93, 150, 106, 23);
		password = new JTextField(50);
		password.setBackground(Color.WHITE);
		password.setBounds(337, 500, 223, 23);
		pCentral.add(password);
		pCentral.add(email);
		
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
	

	
//	public static void main(String[] args) {
//		VentanaLogin v = new VentanaLogin();
//		v.setVisible(true);
//	}

}
