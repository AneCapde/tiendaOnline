package es.deusto.spq.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.WebTarget;

public class ComentariosGUI extends JFrame{
	
	private JButton botonEnviar;
	private JButton botonCancelar;
	private JPanel pCentral;
	private JTextField comentario;
	private JPanel contentPane;
	
	public ComentariosGUI(final JFrame ventanaPadre, WebTarget appTarget) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 872, 560);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		botonEnviar = new JButton("Enviar");
		botonEnviar.setBounds(469, 11, 109, 29);
		contentPane.add(botonEnviar);
		
		botonEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(469, 50, 109, 29);
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
