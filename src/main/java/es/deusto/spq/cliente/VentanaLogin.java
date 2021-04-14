package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class VentanaLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pCentral;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JTextField emailTextField;
	private JPasswordField password;
	private JButton bAceptar;
	private JButton bCancelar;
	private JLabel lTexto;
	private static JPasswordField passwordField;
	
	public VentanaLogin(final JFrame ventanaPadre, WebTarget appTarget) {
		
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
		
		password = new JPasswordField(50);
		password.setBackground(Color.WHITE);
		password.setPreferredSize(new Dimension(50, 35));
		
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

		passwordField = new JPasswordField();
		passwordField.setBounds(114, 129, 444, 35);
		pCentral.add(passwordField);

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

				final WebTarget clientesTarget = appTarget.path("/clientes").path("/"+emailTextField.getText()).path("/"+ new String(passwordField.getPassword()));
				String correcto = clientesTarget.request(MediaType.TEXT_PLAIN).get(String.class);
			
				if (correcto.equals("true")) {
					System.out.println("Credenciales correctas");
					TiendaGUI.setCliente(null);//deberia recibir desde el server el cliente concreto TODO
					ventanaPadre.setEnabled(true);
					dispose();
				} else {
					System.out.println("Credenciales incorrectas");		
				}
			}
		
			});
	    
	    bCancelar = new JButton("Cancelar");
	    bCancelar.setBounds((this.getWidth()/100)*16, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
	    pInferior.add(bCancelar);
	    
	    bCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaPadre.setEnabled(true);
				dispose();
			}
		});
	    
	    addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	ventanaPadre.setEnabled(true);
            }
        }); 
		
	}
	
	//Metodo para comprobar email.
	protected static boolean validarEmail(String email) {

		boolean valido = false;
		Pattern patronEmail = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mEmail = patronEmail.matcher(email.toLowerCase());
		if (mEmail.matches()){
			valido = true;
			System.out.println("El email es correcto");
		}else {
			System.out.println("El email introducido no es correcto");
		}
		return valido;
	}

	
}