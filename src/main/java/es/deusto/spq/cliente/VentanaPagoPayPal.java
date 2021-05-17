package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.client.Entity;

import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;

public class VentanaPagoPayPal extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pCentral;
    private JPanel pSuperior;
    private JPanel pInferior;
    private JTextField accountField;
	private JPasswordField passwordField;
    private JLabel account;
    private JLabel password;
    private JLabel lTexto;
    private JButton bAceptar;
	private JButton bCredenciales; //Aceptar creación de nuevas credenciales
    private JButton bCrearCuenta;

	// LA VENTANA PADRE DEBE SER LA TiendaGUI   +   SE RECIBE EL PEDIDO, QUE SOLO SE COMPLETARÁ AL COMPLETAR EL PAGO
    public VentanaPagoPayPal(final JFrame ventanaPadre, Pedido pedido, WebTarget appTarget) {
        
        //REFERENCIA A LAS CREDENCIALES, NO A LOS PAGOS
        final WebTarget paypalTarget = appTarget.path("/pagos/paypal/").path(TiendaGUI.getCliente().getDNI());
		GenericType<HashMap<String,String>> genericType_paypal = new GenericType<HashMap<String,String>>() {};
		HashMap<String,String> credencialespaypal = paypalTarget.request(MediaType.APPLICATION_JSON).get(genericType_paypal);

		final WebTarget visaTarget = appTarget.path("/pagos/visa/").path(TiendaGUI.getCliente().getDNI());
		GenericType<HashMap<String,String>> genericType_visa = new GenericType<HashMap<String,String>>() {};
		HashMap<String,String> credencialesvisa = visaTarget.request(MediaType.APPLICATION_JSON).get(genericType_visa);

		final WebTarget pagoTarget = appTarget.path("/pagos/pago/").path(TiendaGUI.getCliente().getDNI());
		GenericType<Pago> genericType_pago = new GenericType<Pago>() {};
		Pago credencialespago = pagoTarget.request(MediaType.APPLICATION_JSON).get(genericType_pago);

		//UN PEDIDO SE CREARÁ SOLO CUANDO SE HAYA COMPROBADO SU PAGO
		final WebTarget pedidoTarget = appTarget.path("/pedidos");
		final WebTarget updateTarget = appTarget.path("/pagos/update");

   
		System.out.println(credencialespaypal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
	    setSize(499, 336);
	    setLocation(400, 150);
	    setTitle("Pago con PayPal");

        pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(null);

		pSuperior = new JPanel();
		pSuperior.setEnabled(false);
		pSuperior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pSuperior,BorderLayout.NORTH);
        lTexto = new JLabel("Paga con tu cuenta Paypal");
		lTexto.setForeground(Color.BLACK);
		lTexto.setFont(new Font("Times New Roman", Font.BOLD, 36));
		pSuperior.add(lTexto);
		
        pInferior = new JPanel();
		pInferior.setEnabled(false);
		pInferior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pInferior,BorderLayout.SOUTH);
		
		account = new JLabel("Cuenta(email):");
        account.setToolTipText("");
		account.setBounds(22, 40, 170, 35);
		pCentral.add(account);
		account.setForeground(Color.BLACK);
		account.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		account.setPreferredSize(new Dimension(150, 27));
		
		password = new JLabel("Contraseña:");
        password.setToolTipText("");
		password.setBounds(22, 117, 170, 23);
		pCentral.add(password);
		password.setForeground(Color.BLACK);
		password.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		password.setPreferredSize(new Dimension(150, 27));
		
        passwordField = new JPasswordField();
		passwordField.setBounds(170, 115, 250, 35);
		pCentral.add(passwordField);

		bAceptar = new JButton("Aceptar");
		bAceptar.setBounds((this.getWidth()/100)*5 + 70, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
		pInferior.add(bAceptar);
		bAceptar.setVisible(false);
		bAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (crearPedido(pedido, credencialespaypal, pedidoTarget)) {
//					ventanaPadre.setEnabled(true);
//					ventanaPadre.setVisible(true);
					TiendaGUI.setButtons();
					dispose();
				}
			}
		});

		bCredenciales = new JButton("Crear");
		bCredenciales.setBounds((this.getWidth()/100)*5 + 70, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
		pInferior.add(bCredenciales);
		bCredenciales.setVisible(true);
		bCredenciales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updatePaypal(credencialespaypal, credencialesvisa, credencialespago, updateTarget);
			}
		});

		//SI EL CLIENTE TIENE PAYPAL REGISTRADO, SE MUESTRA EMAIL AUTOMATICAMENTE
		accountField = new JTextField();
		accountField.setBounds(170, 38, 250, 35);
		pCentral.add(accountField);
		autoFill(pedido, credencialespaypal);

		bCrearCuenta = new JButton("Cambiar cuenta");
		bCrearCuenta.setBounds((this.getWidth()/100)*5 - 100, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
		pInferior.add(bCrearCuenta);
		bCrearCuenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				bAceptar.setEnabled(false);
				bAceptar.setVisible(false);
				bCredenciales.setEnabled(true);
				bCredenciales.setVisible(true);
			}
		});	
    }


	
	/** Método que rellena automáticamente el email de paypal si el usuario tiene una cuenta asociada
	 * @param pedido pedido que el cliente busca comprar
	 * @param credencialespaypal Credenciales paypal del cliente
	 */
	public void autoFill(Pedido pedido, HashMap<String, String> credencialespaypal) {
		if (TiendaGUI.getCliente().getDNI().equals(pedido.getCliente().getDNI())) {
			for(Entry<String, String> c: credencialespaypal.entrySet()) {
				String email = c.getKey();
				accountField.setText(email);
				bAceptar.setVisible(true);
				bCredenciales.setVisible(false);
			}
		}
	}


	
	/** Método que crea el pedido una vez se ha verificado y completado el pago del mismo
	 * @param pedido pedido que el cliente busca comprar
	 * @param credencialespaypal Credenciales paypal del cliente
	 * @param pedidoTarget WebTarget para poder guardar el pedido en BD
	 * @return boolean
	 */
	public boolean crearPedido(Pedido pedido, HashMap<String, String> credencialespaypal, final WebTarget pedidoTarget) {
		boolean b = false;

		for(Entry<String, String> c1: credencialespaypal.entrySet()) {
			String email = c1.getKey();
			String password = c1.getValue();
			System.out.println(email + " " + password);
			String pass = new String(passwordField.getPassword());
			System.out.println(accountField.getText() + " " + pass);
			if (email.equals(accountField.getText()) && password.equals(pass)) {
			
				pedidoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
				JOptionPane.showMessageDialog(null, "Pago completado. Compra realizada", "Se te redirigirá al inicio", JOptionPane.INFORMATION_MESSAGE);
				b = true;
			} else {
				JOptionPane.showMessageDialog(null, "Error. Credenciales incorrectas", "Vuelve a intentarlo", JOptionPane.INFORMATION_MESSAGE);

			}
		}
		return b;
	}
	
	
	/** Método que sirve para actualizar la cuenta de paypal asociada al cliente
	 * @param credencialespaypal Credenciales paypal del cliente
	 * @param credencialesvisa Credenciales visa del cliente
	 * @param credencialespago Objeto 'Pago' que se actualiza
	 * @param updateTarget WebTarget para poder actualizar el metodo de pago en BD
	 */
	public void updatePaypal(HashMap<String, String> credencialespaypal, HashMap<String, String> credencialesvisa,
	Pago credencialespago, final WebTarget updateTarget) {
		String pass = new String(passwordField.getPassword());
		if (accountField.getText().isEmpty() == false && pass.isEmpty() == false) {
			
			credencialespaypal.clear();
			credencialespaypal.put(accountField.getText(), pass);

			//PASAMOS A SERVER EL objeto pago con datos actualizados
			credencialespago.setDNI(TiendaGUI.getCliente().getDNI());
			credencialespago.setCredencialesPaypal(credencialespaypal);
			credencialespago.setCredencialesVisa(credencialesvisa);
			updateTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(credencialespago, MediaType.APPLICATION_JSON));

			//Boton aceptar
			bCredenciales.setEnabled(false);
			bCredenciales.setVisible(false);
			bAceptar.setEnabled(true);
			bAceptar.setVisible(true);

			revalidate();
			JOptionPane.showMessageDialog(null, "Credenciales actualizadas", "Completado con éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Rellena todos los campos", "Incompleto", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}