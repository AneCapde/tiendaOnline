package es.deusto.spq.models;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.models.Cliente.Genero;


public class PagoTest {
    Pago pago;
    HashMap<String,String> credencialesVisa;
    HashMap<String,String> credencialesPaypal;
    Cliente cliente;
    HashMap<String,String> cv2;
    @Before
    public void inicializar(){
        
        cliente = new Cliente("12399345K", "usuario", "apellido apellido ", "usuario@gmail.com", "12345r", 655786943,
        "direccion", Genero.MUJER, 45678, "provincia", "localidad");        
        credencialesVisa = new HashMap<String,String>();
        credencialesVisa.put("4444333322221111", "232");             
        credencialesPaypal = new HashMap<String,String>();
        credencialesPaypal.put("usuario@gmail.com", "123r");
        pago = new Pago(cliente.DNI, credencialesVisa, credencialesPaypal);
    }

    @Test
	public void testGetDNI() {
		String dni = pago.getDNI();
		assertEquals("12399345K", dni);
	}

    @Test
    public void testSetDNI() {
        String dni = new String("12233454F");
        pago.setDNI(dni);
        assertEquals("12233454F", pago.getDNI());
    }

    @Test
    public void testGetCredencialesVisa() {
        HashMap<String,String> cv = pago.getCredencialesVisa();
        assertEquals(credencialesVisa, cv);
    }

    @Test
    public void testSetCredencialesVisa() {
        HashMap<String,String> cv = new HashMap<String,String>();
        cv.put("1111222233334444", "323");
        pago.setCredencialesVisa(cv);
        HashMap<String,String> cv2 = new HashMap<String,String>();
        cv2.put("1111222233334444", "323");
        assertEquals(cv2, pago.getCredencialesVisa());
    }

    @Test
    public void testGetCredencialesPaypal() {
        HashMap<String,String> cp = pago.getCredencialesPaypal();
        assertEquals(credencialesPaypal, cp);
    }

    @Test
    public void testSetCredencialesPaypal() {
        HashMap<String,String> cp = new HashMap<String,String>();
        cp.put("usuario@gmail.com", "123r");
        pago.setCredencialesPaypal(cp);
        HashMap<String,String> cp2  = new HashMap<String,String>();
        cp2.put("usuario@gmail.com", "123r");
        assertEquals(cp2, pago.getCredencialesPaypal());
    }

    @Test
    public void testGetNumeroVisa() {
        assertEquals("4444333322221111", pago.getNumVisa(credencialesVisa));
    }

    @Test 
    public void testGetEmailPaypal() {
        assertEquals("usuario@gmail.com", pago.getEmailPaypal(credencialesPaypal));
    }
}
