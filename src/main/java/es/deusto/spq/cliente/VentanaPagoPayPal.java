package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.ws.rs.client.WebTarget;

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
	private JButton bCancelar;
    private JButton bCrearCuenta; //AÑADIMOS POSIBILIDAD DE METER NUEVA CUENTA DE PAYPAL?

    public VentanaPagoPayPal(final JFrame ventanaPadre, WebTarget appTarget) {
        
        // final WebTarget pagosTarget = appTarget.path("/pagos");

        // this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setResizable(false);
		// setSize(599, 336);
		// setLocation(400, 150);
		// setTitle("Pago con PayPal");
     
    } 

	public VentanaPagoPayPal() {
   
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
		
        accountField = new JTextField();
		accountField.setBounds(170, 38, 250, 35);
		pCentral.add(accountField);

        passwordField = new JPasswordField();
		passwordField.setBounds(170, 115, 250, 35);
		pCentral.add(passwordField);

        bAceptar = new JButton("Aceptar");
		bAceptar.setBounds((this.getWidth()/100)*5, (this.getHeight()/18)*8, (this.getWidth()/35)*10, (this.getHeight()/18)*3);
	    pInferior.add(bAceptar);
	    bAceptar.addActionListener(new ActionListener() {
	    
			@Override
			public void actionPerformed(ActionEvent e) {
				
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