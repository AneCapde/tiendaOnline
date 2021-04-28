// package es.deusto.spq.cliente;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.verify;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map.Entry;

// import javax.swing.JTextField;
// import javax.ws.rs.client.WebTarget;

// import org.junit.Before;
// import org.junit.Test;
// import org.mockito.Mockito;

// import es.deusto.spq.models.Cliente;
// import es.deusto.spq.models.Pago;
// import es.deusto.spq.models.Pedido;
// import es.deusto.spq.models.Cliente.Genero;

// public class VentanaPagoVisaTest {

//     VentanaPagoVisa vVisa;
// 	WebTarget web = Mockito.mock(WebTarget.class);
// 	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
//     Pago pago;
//     Pedido pedido;
//     JTextField numTarjetaField;
//     HashMap<String,String> credencialesVisa;
//     HashMap<String,String> credencialesPaypal;
//     Cliente cliente;
//     HashMap<String,String> cv2;


//     @Before
//     public void inicializar(){
        
//         cliente = new Cliente("12399345K", "usuario", "apellido apellido ", "usuario@gmail.com", "12345r", 655786943,
//         "direccion", Genero.MUJER, 45678, "provincia", "localidad");        
//         credencialesVisa = new HashMap<String,String>();
//         credencialesVisa.put("4444333322221111", "232");             
//         credencialesPaypal = new HashMap<String,String>();
//         credencialesPaypal.put("usuario@gmail.com", "123r");
//         pago = new Pago(cliente.getDNI(), credencialesVisa, credencialesPaypal);

//         pedido = new Pedido(cliente, new Date(), "en proceso", 30, 1);

//         vVisa = new VentanaPagoVisa(tiendaGUI, pedido, web);
//     }
    
//     @Test
//     public void testAutoFill() { 
//         TiendaGUI.getCliente().setDNI("12399345K");
//         String numTarjeta = "";

//         if (TiendaGUI.getCliente().getDNI().equals(pedido.getCliente().getDNI())) {
// 			for(Entry<String, String> c: credencialesVisa.entrySet()) {
// 				numTarjeta = c.getKey();
//                 numTarjetaField.setText(numTarjeta);
// 			}
// 		}
//         assertEquals(numTarjetaField.getText(), "4444333322221111");
//     }

//     @Test
//     public void testCrearPedido() {
// 		VentanaPagoVisa vs = Mockito.mock(VentanaPagoVisa.class);
// 		verify(vs).crearPedido(pedido,credencialesVisa,web);
//     }

//     @Test
//     public void testUpdateVisa() {
// 		VentanaPagoVisa vs = Mockito.mock(VentanaPagoVisa.class);
// 		verify(vs).updateVisa(credencialesPaypal,credencialesVisa,pago,web);
//     }
// }
