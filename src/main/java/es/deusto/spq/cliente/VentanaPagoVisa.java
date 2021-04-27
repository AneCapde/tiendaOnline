package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;

public class VentanaPagoVisa extends JFrame{
    
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
	private String numVisa = null;
	private String cvcVisa = null;

    public VentanaPagoVisa(final JFrame ventanaPadre, Pedido pedido, WebTarget appTarget) {
        //REFERENCIA A LAS CREDENCIALES, NO A LOS PAGOS
        final WebTarget pagoTarget = appTarget.path("/pagos/visa/").path(TiendaGUI.getCliente().getDNI());
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

        numTarjetaField = new JTextField();
		numTarjetaField.setBounds(220, 38, 200, 35);
		pCentral.add(numTarjetaField);

		CVCField = new JPasswordField();
		CVCField.setBounds(220, 115, 80, 35);
		pCentral.add(CVCField);

		bAceptar = new JButton("Aceptar");
		bAceptar.setBounds((this.getWidth()/100)*5 + 70, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
		pInferior.add(bAceptar);
		bAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Pago pago : credenciales) {
					if (pago.getDNI().equals(TiendaGUI.getCliente().getDNI()) && pago.getCredencialesVisa().isEmpty() == false) {
						//EL CLIENTE TIENE VISA ASOCIADA
						numTarjetaField.setText(pago.getNumVisa(pago.getCredencialesVisa()));
						numVisa = pago.getNumVisa(pago.getCredencialesVisa());
						cvcVisa = pago.getCredencialesVisa().values().toString();
					}
				}
				if (numVisa.equals(numTarjetaField.toString()) && cvcVisa.equals(CVCField.toString())) {
					
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
				if (numTarjetaField.toString().isEmpty() == false && CVCField.toString().isEmpty() == false) {
					
					HashMap<String,String> cv = new HashMap<String,String>();
					cv.put(numTarjetaField.toString(), CVCField.toString());
					credenciales.get(0).setCredencialesVisa(cv);
					//UPDATE BD
					pagoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(credenciales.get(0), MediaType.APPLICATION_JSON));

					//Boton aceptar
					bCredenciales.setEnabled(false);
					bCredenciales.setVisible(false);
					bAceptar.setEnabled(true);
					bAceptar.setVisible(true);

					numTarjetaField.setText(credenciales.get(0).getNumVisa(credenciales.get(0).getCredencialesVisa()));
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
}
