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
import javax.ws.rs.client.WebTarget;

import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class VentanaLugarEntregaGUI extends JFrame {

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

	String[] lugares = { "A Coruña", "Albacete", "Alicante", "Almeria", "Avila", "Badajoz", "Bilbao", "Barcelona",
			"Burgos", "Caceres", "Cadiz", "Catellon", "Ceuta", "Ciudad Real", "Cordoba", "Cuenca", "Gerona", "Granada",
			"Guadalajara", "San Sebastian", "Huelva", "Huesca", "Jaen", "Logroño", "Leon", "Lleida", "Lugo", "Madrid",
			"Malaga", "Mallorca", "Melilla", "Murcia", "Ourense", "Oviedo", "Palencia", "Pamplona", "Pontevedra",
			"Salamanca", "Santander", "Segovia", "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia",
			"Valladolid", "Vitoria", "Zamora", "Zaragoza" };

	/**
	 * Ventana donde se decide el lugar de entrega del pedido realizado
	 * @param ventanaPadre Ventana anterior, a traves de la cual se a llegado a esta
	 * @param productos 	Array List de productos que hay en el pedido
	 * @param appTarget Objeto para la comunicacion con el server
	 * @param precio		Precio del pedido
	 */
	public VentanaLugarEntregaGUI(final JFrame ventanaPadre, ArrayList<Producto> productos, WebTarget appTarget,
			int precio) {
		pedidoTarget = appTarget.path("/pedidos");

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(480, 300);
		setLocation(400, 150);
		setTitle("Lugar de Entrega");

		pSuperior = new JPanel();
		pSuperior.setEnabled(false);
		pSuperior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pSuperior, BorderLayout.NORTH);
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
		direccion.setBounds(260, 119, 159, 16);

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
				aceptar(ventanaPadre, productos, appTarget, pedidoTarget, precio);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(284, 180, 89, 23);
		pCentral.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar(ventanaPadre);
			}
		});
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	ventanaPadre.setEnabled(true);
            }
        }); 
	}

	/**
	 * Metodo para cancelar la selccion de lugar realizada
	 * @param ventanaPadre Ventana anterior, a traves de la cual se a llegado a esta
	 */
	public void cancelar(JFrame ventanaPadre) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanaPadre.setEnabled(true);
		ventanaPadre.setVisible(true);
		dispose();
	}

	/**
	 * metodo para validar que los datos introducidos son correctos
	 * @return true si los datos son correctos, false si los datos son incorrectos
	 */
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

	/**
	 * Metodo para validar que los datos son correctos al seleccionar el boton de correos
	 * @param dir direccion establecida por el cliente
	 * @param prov provincia seleccionada por el cliente
	 */
	public void datosValidar(String dir, String prov) {
		direccion.setText(dir);
		provincia.setSelectedItem(prov);
		rdbtnCorreos.setSelected(true);
	}

	/**
	 * Metodo para validar que los datos son correctos al seleccionar el boton de Domicilio
	 * @param dir direccion establecida por el cliente
	 * @param prov provincia seleccionada por el cliente
	 */
	public void datosValidar2(String dir, String prov) {
		direccion.setText(dir);
		provincia.setSelectedItem(prov);
		rdbtnDomicilio.setSelected(true);
	}

	/**
	 * Metodo para validar que que no se a seleccionado ningun boton
	 * @param dir direccion establecida por el cliente
	 * @param prov provincia seleccionada por el cliente
	 */
	public void datosValidarNada(String dir, String prov) {
		direccion.setText(dir);
		provincia.setSelectedItem(prov);
	}

	/**
	 * Metodo para que al seleccioanr el boton de correos se pueda seleccionar una provincia
	 */
	public void correos() {
		lblprovincia.setVisible(true);
		provincia.setVisible(true);
		direccion.setVisible(false);
		lblDireccion.setVisible(false);
	}

	/**
	 * Metodo para que al seleccioanr el boton de Domicilio se pueda introduccir una direccion
	 */
	public void domicilio() {
		direccion.setVisible(true);
		lblDireccion.setVisible(true);
		lblprovincia.setVisible(false);
		provincia.setVisible(false);
	}

	/**
	 * Metodo para al tocar el boton de aceptar y validar que los datos introducidos son correctos se cree el pedido
	 * @param ventanaPadre Ventana anterior, a traves de la cual se a llegado a esta
	 * @param productos Array List de productos que hay en el pedido
	 * @param appTarget Objeto para la comunicacion con el server
	 * @param pedidoTarget Objeto para la comunicacion con el server de un pedido
	 * @param precio precio del pedido
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void aceptar(final JFrame ventanaPadre, ArrayList<Producto> productos, WebTarget appTarget,
			WebTarget pedidoTarget, int precio) {
		Date date = new Date();
		if (validar()) {
			if (rdbtnCorreos.isSelected()) {
				provinciaSelecionada = ("Correos " + (String) provincia.getSelectedItem());
				Pedido pedido = new Pedido(TiendaGUI.getCliente(), date, "en proceso", precio, 1, provinciaSelecionada);
				pedido.setProducto(productos);
				if (productos != null && CestaGUI.model != null && CestaGUI.list != null) {
					System.out.println("entra7");
					productos.remove(productos);
					CestaGUI.model.removeAllElements();
					CestaGUI.list.setModel(CestaGUI.model);
				}

				ventanaPadre.setEnabled(false);
				ventanaPadre.setVisible(false);
				dispose();
				vmp = new VentanaMetodoPago(ventanaPadre, pedido, appTarget);
				vmp.setVisible(true);

			} else if (rdbtnDomicilio.isSelected()) {
				direccion1 = direccion.getText();
				Pedido pedido = new Pedido(TiendaGUI.getCliente(), date, "en proceso", precio, 1, direccion1);
				pedido.setProducto(productos);
				if (productos != null && CestaGUI.model != null && CestaGUI.list != null) {
					productos.remove(productos);
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
}
