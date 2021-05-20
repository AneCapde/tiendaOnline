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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;

public class VentanaPagoVisa extends JFrame{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pCentral;
    private JPanel pSuperior;
    private JPanel pInferior;
    private JTextField numTarjetaField;
    // private JTextField fechaCadField;
	private JPasswordField CVCField;
    private JLabel mumTarjeta;
    // private JLabel fechaCad;
    private JLabel CVC;
    private JLabel lTexto;
    private JButton bAceptar;
	private JButton bCredenciales; //Aceptar creación de nuevas credenciales
    private JButton bCrearCuenta;

    public VentanaPagoVisa(final JFrame ventanaPadre, Pedido pedido, WebTarget appTarget) {
       
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
        lTexto = new JLabel("Añade tu Tarjeta Visa");
		lTexto.setForeground(Color.BLACK);
		lTexto.setFont(new Font("Times New Roman", Font.BOLD, 36));
		pSuperior.add(lTexto);
		
        pInferior = new JPanel();
		pInferior.setEnabled(false);
		pInferior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pInferior,BorderLayout.SOUTH);
		
		mumTarjeta = new JLabel("Cuenta(email):");
        mumTarjeta.setToolTipText("");
		mumTarjeta.setBounds(80, 40, 250, 35);
		pCentral.add(mumTarjeta);
		mumTarjeta.setForeground(Color.BLACK);
		mumTarjeta.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		mumTarjeta.setPreferredSize(new Dimension(150, 20));
		
		CVC = new JLabel("CVC:");
        CVC.setToolTipText("");
		CVC.setBounds(170, 117, 120, 23);
		pCentral.add(CVC);
		CVC.setForeground(Color.BLACK);
		CVC.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		CVC.setPreferredSize(new Dimension(150, 20));

		CVCField = new JPasswordField();
		CVCField.setBounds(220, 115, 80, 35);
		pCentral.add(CVCField);

		bAceptar = new JButton("Aceptar");
		bAceptar.setBounds((this.getWidth()/100)*5 + 70, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
		pInferior.add(bAceptar);
		bAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (crearPedido(pedido, credencialesvisa, pedidoTarget)){ 
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
		bCredenciales.setVisible(false);
		bCredenciales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateVisa(credencialespaypal, credencialesvisa, credencialespago, updateTarget);
			}
		});

		numTarjetaField = new JTextField();
		numTarjetaField.setBounds(220, 38, 200, 35);
		pCentral.add(numTarjetaField);
		autoFill(pedido, credencialesvisa);

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

	
	/** 
	 * Método que rellena automáticamente el número de Visa si el usuario tiene una cuenta asociada
	 * @param pedido pedido que el cliente busca comprar
	 * @param credencialesvisa Credenciales visa del cliente
	 */
	public void autoFill(Pedido pedido, HashMap<String, String> credencialesvisa) {
		if (TiendaGUI.getCliente().getDNI().equals(pedido.getCliente().getDNI())) {
			for(Entry<String, String> c: credencialesvisa.entrySet()) {
				String num = c.getKey();
				numTarjetaField.setText(num);
				bAceptar.setVisible(true);
				bCredenciales.setVisible(false);
			}
		}
	}

	
	/** 
	 * Método que crea el pedido una vez se ha verificado y completado el pago del mismo
	 * @param pedido pedido que el cliente busca comprar
	 * @param credencialesvisa Credenciales visa del cliente
	 * @param pedidoTarget WebTarget para poder guardar el pedido en BD
	 * @return boolean
	 */
	public boolean crearPedido(Pedido pedido, HashMap<String, String> credencialesvisa, final WebTarget pedidoTarget) {
		boolean b = false;
		for(Entry<String, String> c1: credencialesvisa.entrySet()) {
			String email = c1.getKey();
			String password = c1.getValue();
			System.out.println(email + " " + password);
			String pass = new String(CVCField.getPassword());
			System.out.println(numTarjetaField.getText() + " " + pass);
			if (email.equals(numTarjetaField.getText()) && password.equals(pass)) {
			
				pedidoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
				JOptionPane.showMessageDialog(null, "Pago completado. Compra realizada", "Se te redirigirá al inicio", JOptionPane.INFORMATION_MESSAGE);
				b = true;

			} else {
				JOptionPane.showMessageDialog(null, "Error. Credenciales incorrectas", "Vuelve a intentarlo", JOptionPane.INFORMATION_MESSAGE);

			}
		}
		return b;
	}
	
	
	/** 
	 * Método que sirve para actualizar la tarjeta visa asociada a la cuenta del cliente
	 * @param credencialespaypal Credenciales paypal del cliente
	 * @param credencialesvisa Credenciales visa del cliente
	 * @param credencialespago Objeto 'Pago' que se actualiza
	 * @param updateTarget WebTarget para poder actualizar el metodo de pago en BD
	 */
	public void updateVisa(HashMap<String, String> credencialespaypal, HashMap<String, String> credencialesvisa,
	Pago credencialespago, final WebTarget updateTarget) {
		String pass = new String(CVCField.getPassword());
		if (numTarjetaField.getText().isEmpty() == false && pass.isEmpty() == false) {
			
			credencialesvisa.clear();
			credencialesvisa.put(numTarjetaField.getText(), pass);

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
