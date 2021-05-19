package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import es.deusto.spq.Main;
import es.deusto.spq.dao.DBManager;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Pago;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Cliente.Genero;

public class VentanaPagoPayPalTest {

    VentanaPagoPayPal vPayPal;
	WebTarget web = Mockito.mock(WebTarget.class);
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
    Pago pago;
    Pedido pedido;
    JTextField emailField = new JTextField();
    HashMap<String,String> credencialesVisa = new HashMap<String,String>();
    HashMap<String,String> credencialesPaypal = new HashMap<String,String>();
    Cliente cliente = new Cliente("null","null","null","null","null",0,"null",Genero.HOMBRE,0,"null","null");
    HashMap<String,String> cv2;

    WebTarget paypalTarget;
    WebTarget visaTarget;
    WebTarget pagoTarget;
    WebTarget pedidoTarget;
    WebTarget getpedidoTarget;
    WebTarget updateTarget;    
    Pago credpago = new Pago("", credencialesVisa, credencialesPaypal);
    HashMap<String,String> credvisa = new HashMap<String,String>();
    HashMap<String,String> credpaypal = new HashMap<String,String>();
	private HttpServer server;
    WebTarget appTarget;
    
    @Before
    public void inicializar(){

        server = Main.startServer();
        Client client = ClientBuilder.newClient();
        appTarget = client.target("http://localhost:8080/myapp");

        // tiendaGUI = new TiendaGUI();

        cliente.setDNI("12399345K");
        cliente.setNombre("usuario");
        cliente.setApellidos("apellido apellido ");
        cliente.setEmail("usuario@gmail.com");
        cliente.setPassword("12345r");
        cliente.setTelefono(655786943);
        cliente.setDireccion("direccion");
        cliente.setGenero(Genero.MUJER);
        cliente.setCod_postal(45678);
        cliente.setProvincia("provincia");       
        cliente.setLocalidad("localidad"); 
        DBManager.getInstance().store(cliente);

        // tiendaGUI.setCliente2(cliente);
        credencialesVisa = new HashMap<String,String>();
        credencialesVisa.put("4444333322221111", "232");             
        credencialesPaypal = new HashMap<String,String>();
        credencialesPaypal.put("usuario@gmail.com", "123r");
        pago = new Pago("12399345K", credencialesVisa, credencialesPaypal);
        DBManager.getInstance().store(pago);

        pedido = new Pedido(cliente, new Date(), "en proceso", 30, 1, "Munitibar");

    }
    
    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testAutoFill() { 
        if (cliente.getDNI().equals(pedido.getCliente().getDNI())) {
			for(Entry<String, String> c: credencialesPaypal.entrySet()) {
				String email = c.getKey();
                emailField.setText(email);
			}
		}
        assertEquals(emailField.getText(), "usuario@gmail.com");
    }

    @Test
    public void testCrearPedido() {
		// boolean b = false;

        paypalTarget = appTarget.path("/pagos/paypal/").path("12399345K");
        GenericType<HashMap<String,String>> genericType_paypal = new GenericType<HashMap<String,String>>() {};
        HashMap<String,String> credPaypal = paypalTarget.request(MediaType.APPLICATION_JSON).get(genericType_paypal);

        System.out.println(credPaypal);

        for(Entry<String, String> c1: credencialesPaypal.entrySet()) {
			String email = c1.getKey();
			String pass = c1.getValue();
            if (email.equals("usuario@gmail.com") && pass.equals("123r")) {
                pedidoTarget = appTarget.path("/pedidos");
                pedidoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
                DBManager.getInstance().deleteObjectFromDB(pedido);

            }
        }
        // WebTarget getpedidoTarget = appTarget.path("/pedidos12399345K");
        // GenericType<List<Pedido>> genericType_pedido = new GenericType<List<Pedido>>() {};
        // List<Pedido> pedidos = getpedidoTarget.request(MediaType.APPLICATION_JSON).get(genericType_pedido);
       
        // List<Pedido> pedidos = DBManager.getInstance().getPedidos();
        assertEquals(pedido.getCantidad(), 1);

    }

    @Test
    public void testUpdatePaypal() {
        // Cambiamos el pass de la cuenta de paypal
		String contr = new String("11");
        for(Entry<String, String> c: credencialesPaypal.entrySet()) {
            String email = c.getKey();
            emailField.setText(email);
        }
        if (emailField.getText().isEmpty() == false && contr.isEmpty() == false) {

            credencialesPaypal.clear();
            credencialesPaypal.put(emailField.getText(), contr);
            //PASAMOS A SERVER EL objeto pago con datos actualizados
            credpago.setDNI(cliente.getDNI());
            credpago.setCredencialesPaypal(credencialesPaypal);
            credpago.setCredencialesVisa(credencialesVisa);
            updateTarget = appTarget.path("/pagos/update");
            updateTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(credpago, MediaType.APPLICATION_JSON));
        }
        paypalTarget = appTarget.path("/pagos/paypal/").path("12399345K");
        GenericType<HashMap<String,String>> genericType_paypal = new GenericType<HashMap<String,String>>() {};
        HashMap<String,String> paypal = paypalTarget.request(MediaType.APPLICATION_JSON).get(genericType_paypal);
        String pass = "";
        for(Entry<String, String> c: credencialesPaypal.entrySet()) {
            pass = c.getValue();
        }

        assertEquals(pass, contr);
        DBManager.getInstance().deleteObjectFromDB(pedido);

    }   
}
