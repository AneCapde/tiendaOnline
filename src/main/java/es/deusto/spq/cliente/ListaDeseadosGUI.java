package es.deusto.spq.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.WebTarget;

//import es.deusto.spq.models.Cliente;
//import es.deusto.spq.models.Producto;

@SuppressWarnings("serial")
public class ListaDeseadosGUI extends JFrame {

//	private Producto productoSeleccionado;
//	private static Cliente cliente;
	private JPanel contentPane;

	public ListaDeseadosGUI(final JFrame ventanaPadre, WebTarget appTarget) {
//		final WebTarget productosDeseadosTarget= appTarget.path("/productosDeseados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

//	public static void main(String[] args) {
//		try { // Cambiamos el look and feel (se tiene que hacer antes de crear la GUI
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (Exception e) {
//		} // Si Nimbus no está disponible, se usa el l&f por defecto
//		ListaDeseadosGUI v = new ListaDeseadosGUI();
//		v.setVisible(true);
//	}
}
