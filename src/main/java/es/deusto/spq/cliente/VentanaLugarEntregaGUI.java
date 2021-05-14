package es.deusto.spq.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class VentanaLugarEntregaGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pCentral;
    private JPanel pSuperior;
    private JLabel lTexto, lblprovincia, lblDireccion;
    private JTextField direccion;
    private JComboBox<String> provincia;
    JRadioButton[] rdbtnArray = new JRadioButton[2];
	JRadioButton rdbtnCorreos;
	JRadioButton rdbtnDomicilio;
	VentanaMetodoPago vmp;
	TiendaGUI ventanaPadre;
	String provinciaSelecionada;
	WebTarget pedidoTarget;
	String direccion1;

    String[] lugares = { "A Coruña", "Albacete", "Alicante", "Almeria", "Avila", "Badajoz", "Bilbao", 
			"Barcelona", "Burgos", "Caceres", "Cadiz", "Catellon", "Ceuta", "Ciudad Real", "Cordoba", "Cuenca",
			"Gerona", "Granada", "Guadalajara", "San Sebastian", "Huelva", "Huesca", "Jaen", "Logroño", "Leon", "Lleida", "Lugo", "Madrid",
			"Malaga", "Mallorca", "Melilla", "Murcia", "Ourense", "Oviedo", "Palencia", "Pamplona", "Pontevedra", "Salamanca", "Santander",
			"Segovia", "Sevilla", "Soria","Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vitoria", "Zamora", "Zaragoza"};
	
	public VentanaLugarEntregaGUI(final JFrame ventanaPadre, ArrayList<Producto> productos, WebTarget appTarget, int precio) {
		pedidoTarget = appTarget.path("/pedidos");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setResizable(false);
	    setSize(480, 300);
	    setLocation(400, 150);
	    setTitle("Lugar de Entrega");
	    
	    pSuperior = new JPanel();
		pSuperior.setEnabled(false);
		pSuperior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pSuperior,BorderLayout.NORTH);
        lTexto = new JLabel("Selecciona un lugar de entrega");
		lTexto.setForeground(Color.BLACK);
		lTexto.setFont(new Font("Times New Roman", Font.BOLD, 30));
		pSuperior.add(lTexto);
	    
	    pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(null);
		
		rdbtnCorreos = new JRadioButton("Correos");
		
		rdbtnCorreos.setBounds(50, 53, 109, 23);
		rdbtnArray[0] = rdbtnCorreos;
		pCentral.add(rdbtnCorreos);
		
		lblprovincia = new JLabel("Selecciona una oficina");
		lblprovincia.setBounds(60, 86, 159, 16);
		
		provincia = new JComboBox<String>();
		provincia.setBounds(60, 113, 159, 20);
		
		for (int i = 0; i < lugares.length; i++) {
			provincia.addItem(lugares[i]);
		}
		pCentral.add(lblprovincia);
		pCentral.add(provincia);
		lblprovincia.setVisible(false);
		provincia.setVisible(false);
		
		rdbtnCorreos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				correos();
			}
		});
		
		lblDireccion = new JLabel("Introduzca la dirección");
		lblDireccion.setBounds(260, 83, 159, 16);
		pCentral.add(lblDireccion);
		direccion = new JTextField();
		direccion.setColumns(10);
		direccion.setBounds(260, 119,159, 16);
		
		rdbtnDomicilio = new JRadioButton("Domicilio");
		rdbtnDomicilio.setBounds(252, 53, 109, 23);
		rdbtnArray[1] = rdbtnDomicilio;
		pCentral.add(rdbtnDomicilio);
		pCentral.add(direccion);
		direccion.setVisible(false);
		lblDireccion.setVisible(false);
		rdbtnDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domicilio();
			}
		});
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnCorreos);
		bg.add(rdbtnDomicilio);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(97, 180, 89, 23);
		pCentral.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(productos);
				aceptar(ventanaPadre, productos, appTarget, pedidoTarget, precio);
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(284, 180, 89, 23);
		pCentral.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancelar(ventanaPadre);
			}
		});
	}
	
	public void Cancelar(JFrame ventanaPadre) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanaPadre.setEnabled(true);
		ventanaPadre.setVisible(true);
		dispose();
	}
	
	private boolean validar() {
		boolean valido = false;
//		try {	
			if (rdbtnCorreos.isSelected()) {
				if (provincia.getSelectedIndex() >= 0) {
					valido = true;
				} else {
					JOptionPane.showMessageDialog(null, "Hay que seleccionar una procincia", "Seleccionar Provincia",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} else if (rdbtnDomicilio.isSelected()) {
				if (!direccion.getText().isEmpty()) {
					valido = true;
				} else {
					JOptionPane.showMessageDialog(null, "No se puede dejar en blanco,hay que añadir la direccion",
							"Validar Direccion", JOptionPane.INFORMATION_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Porfavor selecciona un lugar", "Validar Lugar",
						JOptionPane.INFORMATION_MESSAGE);
			}

//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, "Error", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
//		}
		return valido;
	}
	
	public void datosValidar(String dir,String prov) {
		direccion.setText(dir);
		provincia.setSelectedItem(prov);
		rdbtnCorreos.setSelected(true);		
	}
	
	public void datosValidar2(String dir,String prov) {
		direccion.setText(dir);
		provincia.setSelectedItem(prov);
		rdbtnDomicilio.setSelected(true);		
	}

	public void datosValidarNada(String dir,String prov) {
		direccion.setText(dir);
		provincia.setSelectedItem(prov);		
	}
	
	public void correos() {
		lblprovincia.setVisible(true);
		provincia.setVisible(true);
		direccion.setVisible(false);
		lblDireccion.setVisible(false);
	}
	
	public void domicilio() {
		direccion.setVisible(true);
		lblDireccion.setVisible(true);
		lblprovincia.setVisible(false);
		provincia.setVisible(false);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void aceptar(final JFrame ventanaPadre, ArrayList<Producto> productos, WebTarget appTarget,
			WebTarget pedidoTarget, int precio) {

		if (validar()) {
			Date date = new Date();
//				System.out.println("entra1");
			if (rdbtnArray[0].isSelected()) {
//				System.out.println("entra2 Boton 0");
				provinciaSelecionada = ("Correos " + (String) provincia.getSelectedItem());
//					System.out.println("entra4");
				Pedido pedido = new Pedido(TiendaGUI.getCliente(), date, "en proceso", precio, 1, provinciaSelecionada);
//					System.out.println("Meter pedido en la base de datos: " + pedido);
//					System.out.println("entra5");
//					System.out.println();
//				System.out.println("Productos que se añaden al pedido: " + productos);
				pedido.setProducto(productos);
//				System.out.println("Lista de productos dentro del pedido una vez añadidos : " + pedido);
//				    System.out.println("entra6");
//				pedidoTarget.request(MediaType.APPLICATION_JSON)
//						.post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
//					System.out.println("entra7");
				if(CestaGUI.productos2 != null && CestaGUI.model != null && CestaGUI.list != null) {
					CestaGUI.productos2.remove(CestaGUI.productos2);
					CestaGUI.model.removeAllElements();
					CestaGUI.list.setModel(CestaGUI.model);
				}
				
				ventanaPadre.setEnabled(false);
				ventanaPadre.setVisible(false);
				dispose();
				vmp = new VentanaMetodoPago(ventanaPadre, pedido, appTarget);
				vmp.setVisible(true);

			} else if (rdbtnArray[1].isSelected()) {
//				System.out.println("entra2 Boton 1");
				direccion1 = direccion.getText();
				Pedido pedido = new Pedido(TiendaGUI.getCliente(), date, "en proceso", precio, 1, direccion1);
				pedido.setProducto(productos);
//				System.out.println("Lista de productos dentro del pedido: " + productos);
//				pedidoTarget.request(MediaType.APPLICATION_JSON)
//						.post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
				if(CestaGUI.productos2 != null && CestaGUI.model != null && CestaGUI.list != null) {
					CestaGUI.productos2.remove(CestaGUI.productos2);
					CestaGUI.model.removeAllElements();
					CestaGUI.list.setModel(CestaGUI.model);
				}
				ventanaPadre.setEnabled(false);
				ventanaPadre.setVisible(false);
				dispose();
				vmp = new VentanaMetodoPago(ventanaPadre, pedido, appTarget);
				vmp.setVisible(true);
			}
		}
	}
	public void aceptar1() {
		validar();
		rdbtnCorreos.setSelected(true);	
	}
	
	public void aceptar2() {
		rdbtnDomicilio.setSelected(true);	
	}
}
