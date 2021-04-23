package es.deusto.spq.cliente;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.client.WebTarget;

import es.deusto.spq.models.Producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Image;

public class VentanaMetodoPago extends JFrame{

    private JPanel pCentral;
    private JPanel pSuperior;
    private JButton paypal;
    private JButton visa;
    private JLabel lTexto;
    private ImageIcon iIconPaypal, iIconVisa;
    private Icon iPaypal, iVisa;

	// LA VENTANA PADRE DEBE SER LA TiendaGUI   +   SE RECIBE EL PEDIDO, QUE SOLO SE COMPLETARÁ AL COMPLETAR EL PAGO
    public VentanaMetodoPago(final JFrame ventanaPadre, List<Producto> productos, WebTarget appTarget) {
        
        // final WebTarget pedidoTarget = appTarget.path("/pedidos");

     
    } 

    public VentanaMetodoPago() {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
	    setSize(480, 300);
	    setLocation(400, 150);
	    setTitle("Método de pago");

        pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(null);

		pSuperior = new JPanel();
		pSuperior.setEnabled(false);
		pSuperior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pSuperior,BorderLayout.NORTH);
        lTexto = new JLabel("Selecciona el método de pago");
		lTexto.setForeground(Color.BLACK);
		lTexto.setFont(new Font("Times New Roman", Font.BOLD, 30));
		pSuperior.add(lTexto);

        JButton paypal = new JButton();
		paypal.setBounds(40, 40, 170, 100);
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/paypal.png"));
		iIconPaypal = new ImageIcon(icono_1.getImage().getScaledInstance(paypal.getWidth(), paypal.getHeight(),Image.SCALE_FAST));
		paypal.setIcon(iIconPaypal);
		paypal.updateUI();
		pCentral.add(paypal);

        paypal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                setEnabled(false);
				// VentanaPagoPayPal historial= new VentanaPagoPayPal(this, productos, appTarget);
                VentanaPagoPayPal vPaypal= new VentanaPagoPayPal();
				vPaypal.setVisible(true);
				setEnabled(false);
				
			}
		});

        JButton visa = new JButton();
		visa.setBounds(260, 40, 170, 100);
		ImageIcon icono_2 = new ImageIcon(getClass().getResource("/img/visa.png"));
		iIconVisa = new ImageIcon(icono_2.getImage().getScaledInstance(visa.getWidth(), visa.getHeight(),Image.SCALE_FAST));
		visa.setIcon(iIconVisa);
		visa.updateUI();
		pCentral.add(visa);

        visa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEnabled(false);
				// VentanaPagoVisa historial= new VentanaPagoVisa(this, productos, appTarget);
                VentanaPagoVisa vVisa= new VentanaPagoVisa();
				vVisa.setVisible(true);
				setEnabled(false);
			}
		});
    }
    public static void main(String[] args) {
        //		BD.initData();
                // try { // Cambiamos el look and feel (se tiene que hacer antes de crear la GUI
                // 	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                // } catch (Exception e) {
                // } // Si Nimbus no está disponible, se usa el l&f por defecto
                VentanaMetodoPago v = new VentanaMetodoPago();
                v.setVisible(true);
            }
}

    

