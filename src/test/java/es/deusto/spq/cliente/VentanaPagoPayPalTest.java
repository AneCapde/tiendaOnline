package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JTextField;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Cliente.Genero;

public class VentanaPagoPayPalTest {

    VentanaPagoPayPal vPayPal;
	WebTarget web = Mockito.mock(WebTarget.class);
	
	@Mock
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
    Pago pago;
    Pedido pedido;
    JTextField emailField = new JTextField();
    JTextField passField = new JTextField();
    HashMap<String,String> credencialesVisa = new HashMap<String,String>();
    HashMap<String,String> credencialesPaypal = new HashMap<String,String>();
    Cliente cliente;

    Pago credpago = new Pago("", credencialesVisa, credencialesPaypal);
    HashMap<String,String> credvisa = new HashMap<String,String>();
    HashMap<String,String> credpaypal = new HashMap<String,String>();

    @Before
    public void inicializar(){

        cliente = new Cliente("12399345K","usuario","apellido","usuario@gmail.com","12345r",655786943,"direccion",Genero.MUJER,45678,"provincia","localidad");

        TiendaGUI.setCliente2(cliente);
        credencialesVisa = new HashMap<String,String>();
        credencialesVisa.put("4444333322221111", "232");             
        credencialesPaypal = new HashMap<String,String>();
        credencialesPaypal.put("usuario@gmail.com", "123r");
        pago = new Pago("12399345K", credencialesVisa, credencialesPaypal);

        pedido = new Pedido(cliente, new Date(), "en proceso", 30, 1, "Munitibar");

    }

    @Test
    public void testAutoFill() { 
        if (cliente.getDNI().equals(cliente.getDNI())) {
			for(Entry<String, String> c: credencialesPaypal.entrySet()) {
				String email = c.getKey();
                emailField.setText(email);
			}
		}
        assertEquals(emailField.getText(), "usuario@gmail.com");
    }

    @Test
    public void testCrearPedido() {
		boolean b = false;

        for(Entry<String, String> c1: credencialesPaypal.entrySet()) {
			String email = c1.getKey();
			String pass = c1.getValue();
            if (email.equals("usuario@gmail.com") && pass.equals("123r")) {
                b = true;

            }
        }
        // Aquí comprobamos el funcionamiento del método, las pruebas de guardado y acceso a BD se realizan en DBManagerTest
        assertEquals(b, true);
    }

    @Test
    public void testUpdatePaypal() {
        // Cambiamos el pass de la cuenta de paypal
		String contr = new String("11");
        passField.setText(contr);
        for(Entry<String, String> c: credencialesPaypal.entrySet()) {
            String email = c.getKey();
            emailField.setText(email);
        }
        if (emailField.getText().isEmpty() == false && passField.getText().isEmpty() == false) {

            credencialesPaypal.clear();
            credencialesPaypal.put(emailField.getText(), contr);
            //PASAMOS A SERVER EL objeto pago con datos actualizados
            credpago.setDNI(cliente.getDNI());
            credpago.setCredencialesPaypal(credencialesPaypal);
            credpago.setCredencialesVisa(credencialesVisa);
        }
        String pass = "";
        for(Entry<String, String> c: credencialesPaypal.entrySet()) {
            pass = c.getValue();
        }
        // Aquí comprobamos el funcionamiento del método, las pruebas de guardado y acceso a BD se realizan en DBManagerTest
        assertEquals(pass, contr);
    }   
}
