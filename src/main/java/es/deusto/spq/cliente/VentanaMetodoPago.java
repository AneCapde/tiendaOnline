package es.deusto.spq.cliente;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.ws.rs.client.WebTarget;

import es.deusto.spq.models.Pedido;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

public class VentanaMetodoPago extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pCentral;
    private JPanel pSuperior;
    private JButton paypal;
    private JButton visa;
    private JLabel lTexto;
    private ImageIcon iIconPaypal, iIconVisa;
    // private Icon iPaypal, iVisa;

	// LA VENTANA PADRE DEBE SER LA TiendaGUI   +   SE RECIBE EL PEDIDO, QUE SOLO SE COMPLETARÁ AL COMPLETAR EL PAGO
    public VentanaMetodoPago(final JFrame ventanaPadre, Pedido pedido, WebTarget appTarget) {
        
		final VentanaMetodoPago esto = this;
     
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

        paypal = new JButton();
		paypal.setBounds(40, 40, 170, 100);
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/paypal.png"));
		iIconPaypal = new ImageIcon(icono_1.getImage().getScaledInstance(paypal.getWidth(), paypal.getHeight(),Image.SCALE_FAST));
		paypal.setIcon(iIconPaypal);
		paypal.updateUI();
		pCentral.add(paypal);

        paypal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paypal(ventanaPadre, pedido, appTarget);				
			}
		});

        visa = new JButton();
		visa.setBounds(260, 40, 170, 100);
		ImageIcon icono_2 = new ImageIcon(getClass().getResource("/img/visa.png"));
		iIconVisa = new ImageIcon(icono_2.getImage().getScaledInstance(visa.getWidth(), visa.getHeight(),Image.SCALE_FAST));
		visa.setIcon(iIconVisa);
		visa.updateUI();
		pCentral.add(visa);

        visa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visa(esto, pedido, appTarget);			
			}
		});
	}
    
    public void paypal(JFrame ventanaPadre, Pedido pedido, WebTarget appTarget) {
    	ventanaPadre.setEnabled(true);
		ventanaPadre.setVisible(true);
		dispose();
        VentanaPagoPayPal vPaypal= new VentanaPagoPayPal(ventanaPadre, pedido, appTarget);
		vPaypal.setVisible(true);	
    }
    
    public void visa(JFrame ventanaPadre, Pedido pedido, WebTarget appTarget) {
    	ventanaPadre.setEnabled(true);
		ventanaPadre.setVisible(true);
		dispose();
        VentanaPagoVisa vVisa= new VentanaPagoVisa(ventanaPadre, pedido, appTarget);
		vVisa.setVisible(true);		
    }
}