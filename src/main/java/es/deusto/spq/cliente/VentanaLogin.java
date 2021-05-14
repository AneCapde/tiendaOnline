package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Cliente;
import es.deusto.spq.util.Idiomas;

public class VentanaLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pCentral;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JPanel pIZQ;
	private JPanel pDerecho;
	private JTextField emailTextField;
	private JPasswordField password;
	private JButton bAceptar;
	private JButton bCancelar;
	private JLabel lTexto;
	private static JPasswordField passwordField;
	private JComboBox<String> combo;
	public static Idiomas idioma = Idiomas.Español;
	TiendaGUI ventanaPadre;
	WebTarget appTarget;
	
	public VentanaLogin(final JFrame ventanaPadre, WebTarget appTarget) {

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Properties properties = new Properties();
				try {
					String name = "propiedades.xml";
					properties.loadFromXML(new FileInputStream(name));
				} catch (InvalidPropertiesFormatException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				String user = properties.getProperty("Usuario");
				String pass = properties.getProperty("Contraseña");
				emailTextField.setText(user);
				passwordField.setText(pass);
				
				super.windowOpened(e);
			}
		});
		
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

		pIZQ = new JPanel();
		pIZQ.setBackground(Color.LIGHT_GRAY);

		pDerecho = new JPanel();
		pDerecho.setBackground(Color.LIGHT_GRAY);

		getContentPane().add(pDerecho,BorderLayout.EAST);

		combo = new JComboBox<>();
		combo.addItem(Idiomas.seleccionarPalabra("nombre1"));
		combo.addItem(Idiomas.seleccionarPalabra("nombre2"));
		combo.setBounds(5, 5, 100, 20);
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(combo.getSelectedItem().equals("Ingles"));
				if (combo.getSelectedItem().equals("Ingles") || combo.getSelectedItem().equals("English")){
					VentanaLogin.idioma = Idiomas.Ingles;
					System.out.println(VentanaLogin.idioma);
				}else{
					System.out.println(VentanaLogin.idioma);
					VentanaLogin.idioma = Idiomas.Español;
				}
				combo.removeAllItems();
				combo.addItem(Idiomas.seleccionarPalabra("nombre1"));
				combo.addItem(Idiomas.seleccionarPalabra("nombre2"));
				lTexto.setText(Idiomas.seleccionarPalabra("iniciarsesionPanel"));
				bAceptar.setText(Idiomas.seleccionarPalabra("aceptarBoton"));
				bCancelar.setText(Idiomas.seleccionarPalabra("volverBoton"));
			}
		});
		//pIZQ.add(combo);
		getContentPane().add(pIZQ,BorderLayout.WEST);

		pInferior = new JPanel();
		pInferior.setEnabled(false);
		pInferior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pInferior,BorderLayout.SOUTH);
		
		lTexto = new JLabel(Idiomas.seleccionarPalabra("iniciarsesionPanel"));
		lTexto.setForeground(Color.BLACK);
		lTexto.setFont(new Font("Times New Roman", Font.BOLD, 36));
		pSuperior.add(lTexto);
		
		password = new JPasswordField(50);
		password.setBackground(Color.WHITE);
		password.setPreferredSize(new Dimension(40, 35));
		
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.add(combo);
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
		emailTextField.setPreferredSize(new Dimension(40, 35));

		passwordField = new JPasswordField();
		passwordField.setBounds(114, 129, 444, 35);
		pCentral.add(passwordField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(22, 132, 82, 23);
		pCentral.add(lblPassword);
		lblPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		
		bAceptar = new JButton(Idiomas.seleccionarPalabra("aceptarBoton"));
		bAceptar.setBounds((this.getWidth()/100)*5, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
	    pInferior.add(bAceptar);
		bAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptar(ventanaPadre, appTarget);
			}
		});
	    
	    bCancelar = new JButton(Idiomas.seleccionarPalabra("volverBoton"));
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
	
	public void aceptar(JFrame ventanaPadre, WebTarget appTarget) {
		final WebTarget clientesTarget = appTarget.path("/clientes").path("/"+emailTextField.getText()).path("/"+ new String(passwordField.getPassword()));
		Cliente cliente = clientesTarget.request(MediaType.APPLICATION_JSON).get(Cliente.class);
		
		try {
			if (!cliente.equals(null)) {
				TiendaGUI.setCliente(cliente);
				ventanaPadre.setEnabled(true);
				dispose();
			}
		}
		catch(NullPointerException nl) {
			JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Validar Credenciales", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
