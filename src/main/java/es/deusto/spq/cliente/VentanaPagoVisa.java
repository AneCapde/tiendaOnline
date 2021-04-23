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

public class VentanaPagoVisa extends JFrame{
    
    private JPanel pCentral;
    private JPanel pSuperior;
    private JPanel pInferior;
    private JTextField numTarjetaField;
    private JTextField fechaCadField;
	private JPasswordField CVCField;
    private JLabel mumTarjeta;
    private JLabel fechaCad;
    private JLabel CVC;
    private JLabel lTexto;
    private JButton bAceptar;
	private JButton bCancelar;
    private JButton bCrearCuenta; //AÑADIMOS POSIBILIDAD DE METER NUEVA CUENTA DE PAYPAL?

    public VentanaPagoVisa(final JFrame ventanaPadre, WebTarget appTarget) {
        
        // final WebTarget pagosTarget = appTarget.path("/pagos");

        // this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setResizable(false);
		// setSize(599, 336);
		// setLocation(400, 150);
		// setTitle("Pago con PayPal");
     
    } 

	public VentanaPagoVisa() {
   
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
		
		fechaCad = new JLabel("Validez('mm/aa'):");
        fechaCad.setToolTipText("");
		fechaCad.setBounds(22, 117, 250, 23);
		pCentral.add(fechaCad);
		fechaCad.setForeground(Color.BLACK);
		fechaCad.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		fechaCad.setPreferredSize(new Dimension(150, 20));
		
		CVC = new JLabel("CVC:");
        CVC.setToolTipText("");
		CVC.setBounds(330, 117, 120, 23);
		pCentral.add(CVC);
		CVC.setForeground(Color.BLACK);
		CVC.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		CVC.setPreferredSize(new Dimension(150, 20));

        numTarjetaField = new JTextField();
		numTarjetaField.setBounds(220, 38, 200, 35);
		pCentral.add(numTarjetaField);

        fechaCadField = new JTextField();
		fechaCadField.setBounds(190, 115, 80, 35);
		pCentral.add(fechaCadField);

        CVCField = new JPasswordField();
		CVCField.setBounds(380, 115, 80, 35);
		pCentral.add(CVCField);

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
		VentanaPagoVisa v = new VentanaPagoVisa();
		v.setVisible(true);
	}
}
