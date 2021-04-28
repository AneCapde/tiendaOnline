package es.deusto.spq.cliente;

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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Cliente;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	JRadioButton rdbtnHombre;
	JRadioButton rdbtnMujer;
	JRadioButton rdbtnNoBinario;
	Cliente.Genero[] generos = { Cliente.Genero.HOMBRE, Cliente.Genero.MUJER, Cliente.Genero.NO_BINARIO };
	
	String[] provincias = { "A Coruña", "Alava", "Albacete", "Alicante", "Almeria", "Asturias", "Avila", "Badajoz", "Baleares",
			"Barcelona", "Burgos", "Caceres", "Cadiz", "Cantabria", "Catellon", "Ceuta", "Ciudad Real", "Cordoba", "Cuenca",
			"Gerona", "Granada", "Guadalajara", "Guipuzkoa", "Huelva", "Huesca", "Jaen", "La rioja", "Leon", "Lleida", "Lugo", "Madrid",
			"Malaga", "Melilla", "Murcia", "Navarra", "Ourense", "Palencia", "Pontevedra, Salamanca", "Segovia", "Sevilla", "Soria",
			"Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza"};
	
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
	
	// metodo para validar el correo
	protected static boolean nombreCorrecto(String nombre) {

		boolean valido = false;

		Pattern patronNombre = Pattern
				.compile("[a-zA-Z]*\\D{3}");

		Matcher mNombre = patronNombre.matcher(nombre.toLowerCase());
		if (mNombre.matches()) {
			valido = true;
		}
		return valido;
	}
	
	// metodo para validar el DNI
	protected static boolean DNICorrecto(String dni) {

		boolean valido = false;

		Pattern patronDNI = Pattern
				.compile("[0-9]{8}[A-Za-z]{1}");

		Matcher mDNI = patronDNI.matcher(dni.toLowerCase());
		if (mDNI.matches()) {
			valido = true;
		}
		return valido;
	}
	
	
	// metodo para validar el Codigo Postal
	protected static boolean CodPostalCorrecto(String cod_postal) {

		boolean valido = false;

		Pattern patroncod_postal = Pattern
				.compile("[0-9]{5}");

		Matcher mcod_postal = patroncod_postal.matcher(cod_postal.toLowerCase());
		if (mcod_postal.matches()) {
			valido = true;
		}
		return valido;
	}
	
	// metodo para validar el Telefono
	protected static boolean telefonoCorrecto(String telefono) {

		boolean valido = false;

		Pattern patrontelefono = Pattern
				.compile("[0-9]{9}");

		Matcher mtelefono = patrontelefono.matcher(telefono.toLowerCase());
		if (mtelefono.matches()) {
			valido = true;
		}
		return valido;
	}

	// metodo para validar el apellido
	protected static boolean apellidoCorrecto(String apellido) {

		boolean valido = false;

		Pattern patronApellido = Pattern
				.compile("[a-zA-z]+([ '-][a-zA-Z]+)*");

		Matcher mApellido = patronApellido.matcher(apellido.toLowerCase());
		if (mApellido.matches()) {
			valido = true;
		}
		return valido;
	}
	
	// metodo para validar la localidad
		protected static boolean localidadCorrecto(String localidad) {

			boolean valido = false;

			Pattern patronLocalidad = Pattern
					.compile("[a-zA-Z]*\\D{3}");

			Matcher mLocalidad = patronLocalidad.matcher(localidad.toLowerCase());
			if (mLocalidad.matches()) {
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
	
	private boolean Validar() {
		boolean valido = false;
		try {
			if(elEmailCorrecto(email.getText())) {
				if(nombreCorrecto(nombre.getText())) {
					if(apellidoCorrecto(apellidos.getText())) {
						if(DNICorrecto(dni.getText())) {
							if(telefonoCorrecto(telefono.getText())) {
								if(!direccion.getText().isEmpty()) {
									if(CodPostalCorrecto(cod_postal.getText())) {
										if(provincia.getSelectedIndex() != 0) {
											if(localidadCorrecto(localidad.getText())) {
												if(rdbtnHombre.isSelected() || rdbtnMujer.isSelected() || rdbtnNoBinario.isSelected()) {
													valido = true;
												}else {
													JOptionPane.showMessageDialog(null, "Porfavor selecciona el genero", "Validar Genero", JOptionPane.INFORMATION_MESSAGE);
												}
											}else {
												JOptionPane.showMessageDialog(null, "Solo se admite texto", "Validar Localidad", JOptionPane.INFORMATION_MESSAGE);
											}
										}else {
											JOptionPane.showMessageDialog(null, "Hay que seleccionar una procincia", "Seleccionar Provincia", JOptionPane.INFORMATION_MESSAGE);
										}
									}else {
										JOptionPane.showMessageDialog(null, "Solo se admiten 5 numeros", "Validar Codigo Postal", JOptionPane.INFORMATION_MESSAGE);
									}
								}else {
									JOptionPane.showMessageDialog(null, "No se puede dejar en blanco,hay que añadir la direccion", "Validar Direccion", JOptionPane.INFORMATION_MESSAGE);
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "Solo se admiten 9 numeros", "Validar Telefono", JOptionPane.INFORMATION_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Tienen que ser 9 caracteres,8 Numeros y 1 letra ", "Validar DNI", JOptionPane.INFORMATION_MESSAGE);
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Tiene que ser dos apellidos, con solo texto", "Validar Apellidos", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Solo se admite texto", "Validar Nombre", JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Email incorrecto","Validar Email", JOptionPane.INFORMATION_MESSAGE);
				email.requestFocus();
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,"Error", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
		}
		return valido;
	}
	

	public VentanaRegistro(final JFrame ventanaPadre, WebTarget appTarget ) {

		final WebTarget clientesTarget = appTarget.path("/clientes");

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/registro2.png")));
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
		nombre.setBounds(93, 150, 106, 23);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(266, 153, 61, 16);
		getContentPane().add(lblApellidos);
		
		apellidos = new JTextField();
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
		dni.setColumns(10);
		dni.setBounds(93, 191, 106, 23);
		getContentPane().add(dni);
		
		JLabel lbltelefono = new JLabel("Telefono");
		lbltelefono.setBounds(266, 198, 61, 16);
		getContentPane().add(lbltelefono);
		
		telefono = new JTextField();
		telefono.setColumns(10);
		telefono.setBounds(337, 192, 106, 23);
		getContentPane().add(telefono);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(10, 85, 86, 16);
		getContentPane().add(lblemail);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(94, 77, 168, 28);
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
		cod_postal.setColumns(10);
		cod_postal.setBounds(482, 240, 78, 23);
		getContentPane().add(cod_postal);
		
		JLabel lbllocalidad = new JLabel("Localidad");
		lbllocalidad.setBounds(297, 290, 64, 16);
		getContentPane().add(lbllocalidad);
		
		localidad = new JTextField();
		localidad.setColumns(10);
		localidad.setBounds(376, 287, 124, 23);
		getContentPane().add(localidad);
		
		JLabel lblprovincia = new JLabel("Provincia");
		lblprovincia.setBounds(10, 291, 54, 16);
		getContentPane().add(lblprovincia);
		
		provincia = new JComboBox<String>();
		provincia.setBounds(93, 290, 159, 20);
		getContentPane().add(provincia);
		
		for (int i = 0; i < provincias.length; i++) {
			provincia.addItem(provincias[i]);
		}
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(64, 340, 86, 14);
		getContentPane().add(lblGenero);

		 rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(113, 375, 86, 23);
		getContentPane().add(rdbtnHombre);
		rdbtnArray[0] = rdbtnHombre;

		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(249, 375, 78, 23);
		getContentPane().add(rdbtnMujer);
		rdbtnArray[1] = rdbtnMujer;

		rdbtnNoBinario = new JRadioButton("No Binario");
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
						if (Validar()) {
							String dniUsuario = dni.getText();
							String usuarioNick = nombre.getText();
							String apellidosUsuario = apellidos.getText();
							String correo = email.getText();
							String password = new String(passwordField.getPassword());
							Integer telefonoUsuario =  Integer.parseInt(telefono.getText());
							String direccionUsuario = direccion.getText();
							Integer cod_postalUsuario = Integer.parseInt(cod_postal.getText());
							String provinciaSelecionada = (String) provincia.getSelectedItem();
							String localidadUsuario = localidad.getText();
							Cliente.Genero genero = null;
							for (int i = 0; i < rdbtnArray.length; i++) {
								if (rdbtnArray[i].isSelected()) {
									genero = generos[i];
								}
							}
			
							Cliente cliente = new Cliente(dniUsuario, usuarioNick, apellidosUsuario, correo, password,
									telefonoUsuario, direccionUsuario, genero, cod_postalUsuario, provinciaSelecionada,
									localidadUsuario);
			
							clientesTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(cliente, MediaType.APPLICATION_JSON));

							dispose();
							ventanaPadre.setEnabled(true);
						}				

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
	

	public void datosValidar(String em, String nom, String apel, String dn, String tel, String dir, String cod, String prov,
			String loc) {
		email.setText(em);
		nombre.setText(nom);
		apellidos.setText(apel);
		dni.setText(dn);
		telefono.setText(tel);
		direccion.setText(dir);
		cod_postal.setText(cod);
		localidad.setText(loc);
		provincia.setSelectedItem(prov);
		rdbtnHombre.setSelected(true);
		
			
	}
	
	
//	public static void main(String[] args) {
////		BD.initData();
//		try { // Cambiamos el look and feel (se tiene que hacer antes de crear la GUI
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (Exception e) {
//		} // Si Nimbus no está disponible, se usa el l&f por defecto
//		VentanaRegistro v = new VentanaRegistro();
//		v.setVisible(true);
//	}

}