package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;

import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;

public class VentanaPagoPayPal extends JFrame {

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
	private String numVisa = null;
	private String cvcVisa = null;

	// LA VENTANA PADRE DEBE SER LA TiendaGUI   +   SE RECIBE EL PEDIDO, QUE SOLO SE COMPLETARÁ AL COMPLETAR EL PAGO
    public VentanaPagoPayPal(final JFrame ventanaPadre, Pedido pedido, WebTarget appTarget) {
        
        //REFERENCIA A LAS CREDENCIALES, NO A LOS PAGOS
        final WebTarget pagoTarget = appTarget.path("/pagos");
		//UN PEDIDO SE CREARÁ SOLO CUANDO SE HAYA COMPROBADO SU PAGO
		final WebTarget pedidoTarget = appTarget.path("/pedidos");

		GenericType<List<Pago>> genericType_pago = new GenericType<List<Pago>>() {};
		List<Pago> credenciales = pagoTarget.request(MediaType.APPLICATION_JSON).get(genericType_pago);
   
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
		
		//SI EL CLIENTE TIENE PAYPAL REGISTRADO, SE MUESTRA EMAIL AUTOMATICAMENTE
        accountField = new JTextField();
		accountField.setBounds(170, 38, 250, 35);
		pCentral.add(accountField);

		//SI EL CLIENTE NO TIENE REGISTRADO, SE CREA BOTON PARA REGISTRAR Y COMPRAR

        passwordField = new JPasswordField();
		passwordField.setBounds(170, 115, 250, 35);
		pCentral.add(passwordField);

		bAceptar = new JButton("Aceptar");
		bAceptar.setBounds((this.getWidth()/100)*5 + 70, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
		pInferior.add(bAceptar);
		bAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Pago pago : credenciales) {
					if (pago.getCliente().getDNI().equals(TiendaGUI.getCliente().getDNI()) && pago.getCredencialesVisa().isEmpty() == false) {
						//EL CLIENTE TIENE VISA ASOCIADA
						accountField.setText(pago.getNumVisa(pago.getCredencialesVisa()));
						numVisa = pago.getNumVisa(pago.getCredencialesVisa());
						cvcVisa = pago.getCredencialesVisa().values().toString();
					}
				}
				if (numVisa.equals(accountField.toString()) && cvcVisa.equals(passwordField.toString())) {
					
					pedidoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
					JOptionPane.showMessageDialog(null, "Pago completado. Compra realizada", "Se te redirigirá al inicio", JOptionPane.INFORMATION_MESSAGE);
					
					ventanaPadre.setEnabled(true);
					TiendaGUI.setButtons();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Error. Credenciales incorrectas", "Vuelve a intentarlo", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});

		bCredenciales = new JButton("Crear");
		bCredenciales.setBounds((this.getWidth()/100)*5 + 70, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
		pInferior.add(bCredenciales);
		bCredenciales.setVisible(false);
		bCredenciales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accountField.toString().isEmpty() == false && passwordField.toString().isEmpty() == false) {
					
					HashMap<String,String> cv = new HashMap<String,String>();
					cv.put(accountField.toString(), passwordField.toString());
					credenciales.get(0).setCredencialesVisa(cv);
					//UPDATE BD
					pagoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(credenciales.get(0), MediaType.APPLICATION_JSON));

					//Boton aceptar
					bCredenciales.setEnabled(false);
					bCredenciales.setVisible(false);
					bAceptar.setEnabled(true);
					bAceptar.setVisible(true);

					accountField.setText(credenciales.get(0).getNumVisa(credenciales.get(0).getCredencialesVisa()));
					revalidate();
					JOptionPane.showMessageDialog(null, "Credenciales actualizadas", "Completado con éxito", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Rellena todos los campos", "Incompleto", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

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

    public static void main(String[] args) {
//		BD.initData();
		// try { // Cambiamos el look and feel (se tiene que hacer antes de crear la GUI
		// 	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		// } catch (Exception e) {
		// } // Si Nimbus no está disponible, se usa el l&f por defecto
		VentanaPagoPayPal v = new VentanaPagoPayPal();
		v.setVisible(true);
	}
}