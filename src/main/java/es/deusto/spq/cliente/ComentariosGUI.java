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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.ws.rs.client.WebTarget;

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
	private JPanel pCentral,pInferior,pSuperior,p1,p2;
	private JTextField comentario;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> list;
	private ArrayList<String> coment;
	
	public ComentariosGUI(final JFrame ventanaPadre, Producto producto, WebTarget appTarget) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 872, 560);
		
		pCentral = new JPanel();
		getContentPane().add(pCentral,BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout(1,2));
		
		pInferior = new JPanel();
		getContentPane().add(pInferior,BorderLayout.SOUTH);
		
		pSuperior = new JPanel();
		pSuperior.setLayout(new GridLayout(1,2));
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		modeloSpinner = new SpinnerNumberModel(0,0,5,1);
		puntuacion = new JSpinner(modeloSpinner);
		puntuacion.setPreferredSize(new Dimension(550, 35));
		pCentral.add(puntuacion);
		
		intComent = new JLabel("Añadir comentario");
		intComent.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		p1.add(intComent);
		
		comentario = new JTextField();
		comentario.setBounds(260, 119,159, 16);
		pCentral.add(comentario);
		
		if(coment != null) {
			coment = producto.getComentarios();
		
		
			for (int i = 0; i < coment.size(); i++) {
				model.addElement(coment.get(i)); 
			}
			
			//JList para los comentarios
			list = new JList<>(model);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(list);
			pCentral.add(scrollPane);
		}
			
		verComent = new JLabel("Comentarios del Producto");
		intComent.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		p2.add(verComent);
		
		botonEnviar = new JButton("Enviar");
		botonEnviar.setBounds(469, 11, 109, 29);
		pInferior.add(botonEnviar);
		
		botonEnviar.addActionListener(new ActionListener() {
			
			//añadir comentarios a la BD
			@Override
			public void actionPerformed(ActionEvent e) {
				//no tengo que crear una nueva lista, coger la de la BD y añadirlo a esa
				coment = new ArrayList<String>();
				String c = comentario.getText();
				coment.add(c);
				producto.setComentarios(coment);	
				System.out.println(coment);
			}
			
		});
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(469, 50, 109, 29);
		pInferior.add(botonCancelar);
		
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
