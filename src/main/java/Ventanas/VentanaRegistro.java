package Ventanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dao.DBManager;
import models.Cliente;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombre;
	private JTextField apellidos;
	private JTextField email;
	private JPasswordField passwordField;
	private JTextField telefono;
	private JTextField dni;
	private JTextField direccion;
	private JTextField cod_postal;
	private JTextField localidad;
	private JComboBox<String> provincia;
	JRadioButton[] rdbtnArray = new JRadioButton[3];
	Cliente.Genero[] generos = { Cliente.Genero.HOMBRE, Cliente.Genero.MUJER, Cliente.Genero.NO_BINARIO };
	
	String[] provincias = { "A Coruña", "Alava", "Albacete", "Alicante", "Almeria", "Asturias", "Avila", "Badajoz", "Baleares",
			"Barcelona", "Burgos", "Caceres", "Cadiz", "Cantabria", "Catellon", "Ceuta", "Ciudad Real", "Cordoba", "Cuenca",
			"Gerona", "Granada", "Guadalajara", "Guipuzkoa", "Huelva", "Huesca", "Jaen", "La rioja", "Leon", "Lleida", "Lugo", "Madrid",
			"Malaga", "Melilla", "Murcia", "Navarra", "Ourense", "Palencia", "Pontevedra, Salamanca", "Segovia", "Sevilla", "Soria",
			"Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza"};
	
//	private AutorizacionController controller;
	private static DBManager instance;
	
		public static DBManager getInstance() {
			if (instance == null) {
				try {
					instance = new DBManager();
				} catch (Exception ex) {
					System.err.println("# Error creating EasyBookingRemoteFacade: " + ex);
				}
			}	
			return instance;
		}
	
	// metodo para validar el correo
	protected static boolean elEmailCorrecto(String email) {

		boolean valido = false;

		Pattern patronEmail = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mEmail = patronEmail.matcher(email.toLowerCase());
		if (mEmail.matches()) {
			valido = true;
		}
		return valido;
	}
	
	// metodo para validar la contraseña
	protected static String elPasswordCorrecto(String password) {
		String resultado = "Muy Buena"; // Resultado de password valido

		int length = 0; // Almacenamos numero de caracteres en el pass
		int numCount = 0; // Variable usada para almacenar numeros en el password
		int capCount = 0; // Variable usada para almacenar mayusculas en el password
		int capSignos = 0; // Variable usada para almacenar los signos
		int Arroba = 0; // solo la arroba -.-!

		for (int x = 0; x < password.length(); x++) {
			if ((password.charAt(x) >= 47 && password.charAt(x) <= 58) // numeros
					|| (password.charAt(x) >= 64 && password.charAt(x) <= 91) // mayusculas
					|| (password.charAt(x) >= 63 && password.charAt(x) <= 65) // Arroba
					|| (password.charAt(x) >= 32 && password.charAt(x) <= 44) // signos
					|| (password.charAt(x) >= 97 && password.charAt(x) <= 122)) { // minusculas

			}
			if ((password.charAt(x) > 63 && password.charAt(x) < 65)) { // Cuenta laS arrobas
				Arroba++;
			}
			if ((password.charAt(x) > 32 && password.charAt(x) < 44)) { // Cuenta la cantidad signos
				capSignos++;
			}
			if ((password.charAt(x) > 47 && password.charAt(x) < 58)) { // Cuenta la cantidad de numero
				numCount++;
			}

			if ((password.charAt(x) > 64 && password.charAt(x) < 91)) { // Cuenta la cantidad de mayuscula
				capCount++;
			}

			length = (x + 1); // Cuenta la longitud del password

		} // Final del bucle

		if (capSignos < 1) { // Revisa la longitud minima de 8 caracteres del password
			resultado = "no tiene caracteres especiales como ( ! # $ % & ' ( ) + - )";
		}
		if (Arroba < 1) { // Revisa la longitud minima de 8 caracteres del password
			resultado = "Coloque un @ para mayor seguridad";
		}
		if (numCount < 1) { // Revisa que el password contenga minimo 1 numero
			resultado = "Medio";
		}

		if (capCount < 1) { // Revisa que el password contenga minimo 1 mayuscula
			resultado = "Facil";
		}

		if (length < 5) { // Revisa la longitud minima de 8 caracteres del password
			resultado = "Inutilizable: no cumple con el mínimo de caracteres!";
		}

		return(resultado);
	}
	
	public VentanaRegistro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/Imagenes_sueltas/registro2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(600, 530);
		setLocation(400, 150);
		setTitle("Registro");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 574, 54);
		getContentPane().add(panel);
		
		JLabel registro = new JLabel("Escribe tus datos de Registro");
		registro.setForeground(Color.BLUE);
		registro.setFont(new Font("Times New Roman", Font.BOLD, 36));
		panel.add(registro);


		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 153, 61, 16);
		getContentPane().add(lblNombre);
		
		nombre = new JTextField();
		nombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//Declaramos una variable y le asignamos un evento
				char car = e.getKeyChar();
				//Condicion que nos permite ingresar datos numericos
				if((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
						&&(car!=(char)KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se admite texto", "Validar texto", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		nombre.setBounds(93, 150, 106, 23);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(266, 153, 61, 16);
		getContentPane().add(lblApellidos);
		
		apellidos = new JTextField();
		apellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//Declaramos una variable y le asignamos un evento
				char car = e.getKeyChar();
				//Condicion que nos permite ingresar datos numericos
				if((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
						&&(car==(char)KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se admite texto", "Validar texto", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		apellidos.setColumns(10);
		apellidos.setBounds(337, 151, 223, 23);
		getContentPane().add(apellidos);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(10, 116, 86, 16);
		getContentPane().add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(93, 114, 106, 21);
		getContentPane().add(passwordField);
		
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				JOptionPane.showMessageDialog(null, elPasswordCorrecto(new String(passwordField.getPassword())), "Validar Contraseña", JOptionPane.INFORMATION_MESSAGE);	
			}
		});
		
		JLabel lbldni= new JLabel("DNI");
		lbldni.setBounds(10, 195, 61, 16);
		getContentPane().add(lbldni);
		
		dni = new JTextField();
		dni.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(dni.getText().length() != 9 ) {
					JOptionPane.showMessageDialog(null, "Tienen que ser 9 caracteres, letra incluida", "Validar DNI", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		dni.setColumns(10);
		dni.setBounds(93, 191, 106, 23);
		getContentPane().add(dni);
		
		JLabel lbltelefono = new JLabel("Telefono");
		lbltelefono.setBounds(266, 198, 61, 16);
		getContentPane().add(lbltelefono);
		
		telefono = new JTextField();
		telefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(telefono.getText().length() != 9 ) {
					JOptionPane.showMessageDialog(null, "Solo se admiten 9 numeros", "Validar Numeros", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		telefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//Declaramos una variable y le asignamos un evento
				char car = e.getKeyChar();
				//Condicion que nos permite ingresar datos numericos
				if((car < '0' || car > '9') 
						&&(car!=(char)KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se admiten numeros", "Validar Numeros", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		telefono.setColumns(10);
		telefono.setBounds(337, 192, 106, 23);
		getContentPane().add(telefono);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(10, 85, 86, 16);
		getContentPane().add(lblemail);
		
		email = new JTextField();
		email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(elEmailCorrecto(email.getText())) {
					
				}else {
					JOptionPane.showMessageDialog(null, "Email incorrecto","Validar Email", JOptionPane.INFORMATION_MESSAGE);
					email.requestFocus();
				}
			}
		});
		email.setColumns(10);
		email.setBounds(94, 82, 168, 23);
		getContentPane().add(email);
		
		JLabel lbldireccion = new JLabel("Direccón");
		lbldireccion.setBounds(10, 243, 61, 16);
		getContentPane().add(lbldireccion);
		
		direccion = new JTextField();
		direccion.setColumns(10);
		direccion.setBounds(93, 240, 268, 23);
		getContentPane().add(direccion);
		
		JLabel lblcod_postal = new JLabel("Código Postal");
		lblcod_postal.setBounds(394, 243, 78, 16);
		getContentPane().add(lblcod_postal);
		
		cod_postal = new JTextField();
		cod_postal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//Declaramos una variable y le asignamos un evento
				char car = e.getKeyChar();
				//Condicion que nos permite ingresar datos numericos
				if((car < '0' || car > '9') 
						&&(car!=(char)KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se admiten numeros", "Validar Numeros", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		cod_postal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(cod_postal.getText().length() != 5 ) {
					JOptionPane.showMessageDialog(null, "Solo se admiten 5 numeros", "Validar Numeros", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		cod_postal.setColumns(10);
		cod_postal.setBounds(482, 240, 78, 23);
		getContentPane().add(cod_postal);
		
		JLabel lbllocalidad = new JLabel("Localidad");
		lbllocalidad.setBounds(297, 290, 64, 16);
		getContentPane().add(lbllocalidad);
		
		localidad = new JTextField();
		localidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//Declaramos una variable y le asignamos un evento
				char car = e.getKeyChar();
				//Condicion que nos permite ingresar datos numericos
				if((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
						&&(car!=(char)KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se admite texto", "Validar texto", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		localidad.setColumns(10);
		localidad.setBounds(376, 287, 124, 23);
		getContentPane().add(localidad);
		
		JLabel lblprovincia = new JLabel("Provincia");
		lblprovincia.setBounds(10, 291, 54, 16);
		getContentPane().add(lblprovincia);
		
		provincia = new JComboBox<String>();
		provincia.setBounds(93, 290, 159, 20);
		getContentPane().add(provincia);
		provincia.addItem(provincias[0]);
		provincia.addItem(provincias[1]);
		provincia.addItem(provincias[2]);
		provincia.addItem(provincias[3]);
		provincia.addItem(provincias[4]);
		provincia.addItem(provincias[5]);
		provincia.addItem(provincias[6]);
		provincia.addItem(provincias[7]);
		provincia.addItem(provincias[8]);
		provincia.addItem(provincias[9]);
		provincia.addItem(provincias[10]);
		provincia.addItem(provincias[11]);
		provincia.addItem(provincias[12]);
		provincia.addItem(provincias[13]);
		provincia.addItem(provincias[14]);
		provincia.addItem(provincias[15]);
		provincia.addItem(provincias[16]);
		provincia.addItem(provincias[17]);
		provincia.addItem(provincias[18]);
		provincia.addItem(provincias[19]);
		provincia.addItem(provincias[20]);
		provincia.addItem(provincias[21]);
		provincia.addItem(provincias[22]);
		provincia.addItem(provincias[23]);
		provincia.addItem(provincias[24]);
		provincia.addItem(provincias[25]);
		provincia.addItem(provincias[26]);
		provincia.addItem(provincias[27]);
		provincia.addItem(provincias[28]);
		provincia.addItem(provincias[29]);
		provincia.addItem(provincias[30]);
		provincia.addItem(provincias[31]);
		provincia.addItem(provincias[32]);
		provincia.addItem(provincias[33]);
		provincia.addItem(provincias[34]);
		provincia.addItem(provincias[35]);
		provincia.addItem(provincias[36]);
		provincia.addItem(provincias[37]);
		provincia.addItem(provincias[38]);
		provincia.addItem(provincias[39]);
		provincia.addItem(provincias[40]);
		provincia.addItem(provincias[41]);
		provincia.addItem(provincias[42]);
		provincia.addItem(provincias[43]);
		provincia.addItem(provincias[44]);
		provincia.addItem(provincias[45]);
		provincia.addItem(provincias[46]);
		provincia.addItem(provincias[47]);
		provincia.addItem(provincias[48]);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(64, 340, 86, 14);
		getContentPane().add(lblGenero);

		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(113, 375, 86, 23);
		getContentPane().add(rdbtnHombre);
		rdbtnArray[0] = rdbtnHombre;

		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(249, 375, 78, 23);
		getContentPane().add(rdbtnMujer);
		rdbtnArray[1] = rdbtnMujer;

		JRadioButton rdbtnNoBinario = new JRadioButton("No Binario");
		rdbtnNoBinario.setBounds(376, 375, 147, 23);
		getContentPane().add(rdbtnNoBinario);
		rdbtnArray[2] = rdbtnNoBinario;

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnHombre);
		bg.add(rdbtnMujer);
		bg.add(rdbtnNoBinario);

		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(145, 429, 117, 29);
		getContentPane().add(btnAceptar);

		// Esto se lanza cuando alguien pulsa el boton de Aceptar entonces guarda los
		// datos en la base de Datos
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread() {
					public void run() {
						String dniUsuario = dni.getText();
						String usuarioNick = nombre.getText();
						String apellidosUsuario = apellidos.getText();
						String correo = email.getText();
						String password = new String(passwordField.getPassword());
						Integer telefonoUsuario =  Integer.parseInt(telefono.getText());
						String direccionUsuario = direccion.getText();
						Cliente.Genero genero = null;
						for (int i = 0; i < rdbtnArray.length; i++) {
							if (rdbtnArray[i].isSelected()) {
								genero = generos[i];
							}
						}
						Integer cod_postalUsuario = Integer.parseInt(cod_postal.getText());
						String provinciaSelecionada = (String) provincia.getSelectedItem();

						String localidadUsuario = localidad.getText();

						Cliente cliente = new Cliente(dniUsuario, usuarioNick,apellidosUsuario,correo, password, telefonoUsuario,direccionUsuario, genero,cod_postalUsuario, provinciaSelecionada, localidadUsuario );
						
						DBManager.getInstance().store(cliente);
//						new VentanaLoggin().setVisible(true);
						dispose();
					}
				};
				t.start();
				System.out.println("Aceptar");
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(337, 429, 117, 29);
		getContentPane().add(btnCancelar);
		

		
		// Esto se lanza cuando alguien pulsa el boton de Cancelar
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});

	}
	
	public static void main(String[] args) {
//		BD.initData();
		try { // Cambiamos el look and feel (se tiene que hacer antes de crear la GUI
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		} // Si Nimbus no está disponible, se usa el l&f por defecto
		VentanaRegistro v = new VentanaRegistro();
		v.setVisible(true);
	}

}
