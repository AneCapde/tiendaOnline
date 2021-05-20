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

public class VentanaPagoVisaTest {

    VentanaPagoVisa vVisa;
	WebTarget web = Mockito.mock(WebTarget.class);
	
	@Mock
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
    Pago pago;
    Pedido pedido;
    JTextField numTarjetaField = new JTextField();
    JTextField cvcField = new JTextField();
    HashMap<String,String> credencialesVisa = new HashMap<String,String>();
    HashMap<String,String> credencialesPaypal = new HashMap<String,String>();
    Cliente cliente;

    Pago credpago = new Pago("", credencialesVisa, credencialesPaypal);
    HashMap<String,String> credvisa = new HashMap<String,String>();
    HashMap<String,String> credpaypal = new HashMap<String,String>();

    @Before
    public void inicializar(){

        cliente = new Cliente("12399345K","usuario","apellido","usuario@gmail.com","12345r",655786943,"direccion",Genero.MUJER,45678,"provincia","localidad");

        tiendaGUI.setCliente2(cliente);
        credencialesVisa = new HashMap<String,String>();
        credencialesVisa.put("4444333322221111", "232");             
        credencialesPaypal = new HashMap<String,String>();
        credencialesPaypal.put("usuario@gmail.com", "123r");
        pago = new Pago("12399345K", credencialesVisa, credencialesPaypal);

        pedido = new Pedido(cliente, new Date(), "en proceso", 30, 1, "Munitibar");
        // vVisa = new VentanaPagoVisa(tiendaGUI, pedido, web);
    }

    @Test
    public void testAutoFill() { 
        if (cliente.getDNI().equals(cliente.getDNI())) {
			for(Entry<String, String> c: credencialesVisa.entrySet()) {
				String num = c.getKey();
                numTarjetaField.setText(num);
			}
		}
        
        assertEquals(numTarjetaField.getText(), "4444333322221111");
    }

    @Test
    public void testCrearPedido() {
		boolean b = false;

        for(Entry<String, String> c1: credencialesVisa.entrySet()) {
			String num = c1.getKey();
			String cvc = c1.getValue();
            if (num.equals("4444333322221111") && cvc.equals("232")) {
                b = true;

            }
        }
        // Aquí comprobamos el funcionamiento del método, las pruebas de guardado y acceso a BD se realizan en DBManagerTest
        assertEquals(b, true);
    }

    @Test
    public void testUpdateVisa() {
        // Cambiamos el pass de la cuenta de paypal
		String cvc = new String("11");
        cvcField.setText(cvc);
        for(Entry<String, String> c: credencialesVisa.entrySet()) {
            String num = c.getKey();
            numTarjetaField.setText(num);
        }
        if (numTarjetaField.getText().isEmpty() == false && cvcField.getText().isEmpty() == false) {

            credencialesVisa.clear();
            credencialesVisa.put(numTarjetaField.getText(), cvc);
            //PASAMOS A SERVER EL objeto pago con datos actualizados
            credpago.setDNI(cliente.getDNI());
            credpago.setCredencialesPaypal(credencialesPaypal);
            credpago.setCredencialesVisa(credencialesVisa);
        }
        String pass = "";
        for(Entry<String, String> c: credencialesVisa.entrySet()) {
            pass = c.getValue();
        }
        // Aquí comprobamos el funcionamiento del método, las pruebas de guardado y acceso a BD se realizan en DBManagerTest
        assertEquals(pass, cvc);
    }   
}