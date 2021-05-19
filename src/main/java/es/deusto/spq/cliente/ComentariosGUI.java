package es.deusto.spq.cliente;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.models.Producto;


public class ComentariosGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton botonEnviar;
	private JButton botonCancelar;
	private JSpinner puntuacion;
	private SpinnerModel modeloSpinner;
	private JLabel intComent;
	private JLabel verComent;
	private JPanel contentPane;
	private JTextField comentario;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> list;
	private ArrayList<String> coment = new ArrayList<>();
	final WebTarget productosTarget;
	
	/**
	 * Ventana en la que se pueden añadir comentarios de un producto y tambien se pueden 
	 * ver los comentarios asociados al producto seleccionado
	 * @param ventanaPadre Ventana anterior, a traves de la cual se a llegado a esta
	 * @param producto Producto del que se quieren ver los comentarios
	 * @param appTarget Objeto para la comunicacion con el server
	 */
	
	public ComentariosGUI(final JFrame ventanaPadre, Producto producto, WebTarget appTarget) {
		
		productosTarget = appTarget.path("/productos/update");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 872, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modeloSpinner = new SpinnerNumberModel(0,0,5,1);
		puntuacion = new JSpinner(modeloSpinner);
		puntuacion.setBounds(65,90,300,29);
		puntuacion.setPreferredSize(new Dimension(550, 35));
		contentPane.add(puntuacion);
		
		intComent = new JLabel("Añadir comentario");
		intComent.setBounds(120,11,200,29);
		intComent.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		contentPane.add(intComent);
		
		comentario = new JTextField();
		comentario.setBounds(65,150,300,250);
		contentPane.add(comentario);
		
		coment = producto.getComentarios();
		
		
		for (int i = 0; i < coment.size(); i++) {
			model.addElement(coment.get(i)); 
		}
		
		//JList para los comentarios
		list = new JList<>(model);			
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(500, 90, 236, 320);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		contentPane.add(list);
		
		verComent = new JLabel("Comentarios del Producto");
		verComent.setBounds(500,11,200,29);
		verComent.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		contentPane.add(verComent);
		
		botonEnviar = new JButton("Enviar");
		botonEnviar.setBounds(300, 450, 117, 29);
		contentPane.add(botonEnviar);
		
		botonEnviar.addActionListener(new ActionListener() {
			
			//añadir comentarios a la BD
			@Override
			public void actionPerformed(ActionEvent e) {
				String c = comentario.getText();
				coment.add(c);
				producto.setComentarios(coment);
				System.out.println(coment);
				productosTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(producto, MediaType.APPLICATION_JSON));
				JOptionPane.showMessageDialog(null, "Comentario enviado correctamente");
				comentario.setText(null);
			}
			
		});
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(500, 450, 117, 29);
		contentPane.add(botonCancelar);
		
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TiendaGUI tienda = new TiendaGUI();
				tienda.setVisible(true);
				TiendaGUI.setCliente(TiendaGUI.getCliente());
				dispose();
				
			}
			
		});
		
		
		
	}

}
