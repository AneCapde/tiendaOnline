package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;

@RunWith(MockitoJUnitRunner.class)
public class VentanaLugarEntregaGUITest {
	
	VentanaLugarEntregaGUI ventanaLugarEntregaGUI;
	WebTarget web = Mockito.mock(WebTarget.class);
	WebTarget pedidoTarget = Mockito.mock(WebTarget.class);
	
	@Mock
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	Producto p1;
	int precio;

	/**
	 * Este método se ejecuta antes de que se ejecute cualquier método de prueba JUnit: se anota con la anotación Before.
	*/
	@Before
	public void inicio(){
		
		p1 = new Producto("producto1", "producto1", 1, 1, "producto1",
				new SubCategoria("Subcategoria1", "Subcategoria1", new Categoria("categoria1", "categoria1")),
				new Marca("marca1", "marca1"));
		productos.add(p1);
		precio = 1;
		System.out.println("Prueba test Ventana Lugar: " + productos);
		ventanaLugarEntregaGUI = new VentanaLugarEntregaGUI(tiendaGUI, productos, web,precio);
	}
	
	/**
	 * Este metodo comprueba que el metodo cancelar de la Ventana Lugar Entrega GUI funcione adecuadamente
	 */
	@Test 
	public void testCancelar() {
		ventanaLugarEntregaGUI.Cancelar(tiendaGUI);
	}
	
	/**
	 * Este metodo comprueba que el metodo validar de la Ventana Lugar Entrega GUI funcione adecuadamente cuando entra en el primer if
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testValidarCorreos()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidar("12333333a","Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(true, result);	
	}
	
	/**
	 *  Este metodo comprueba que el metodo validar de la Ventana Lugar Entrega GUI funcione adecuadamente cuando entra en el segundo if
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testValidarDomicilio()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidar2("12333333a","Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(true, result);	
	}
	
	/**
	 *  Este metodo comprueba que el metodo validar de la Ventana Lugar Entrega GUI funcione adecuadamente cuando no se a introduccido ningun dato
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testValidarNada()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidarNada("12333333a","Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(false, result);	
	}
	
	/**
	 *  Este metodo comprueba que el metodo validar de la Ventana Lugar Entrega GUI funcione adecuadamente cuando no se introducce la provincia
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testValidaSinProvincia()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidar("12333333a", null);
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(false, result);	
	}
	
	/**
	 * Este metodo comprueba que el metodo validar de la Ventana Lugar Entrega GUI funcione adecuadamente cuando no se introducce la direccion
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testValidarSinDireccion()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidar2(null,"Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(false, result);	
	}	
	
	/**
	 * Metodo que comprueba que el metodo Correos de la Ventana lugar Entrega GUI funciona adecuadamente
	 */
	@Test 
	public void testCorreos() {
		ventanaLugarEntregaGUI.correos();
	}
	
	/**
	 * Metodo que comprueba que el metodo Domicilio de la Ventana lugar Entrega GUI funciona adecuadamente
	 */
	@Test 
	public void testDomicilio() {
		ventanaLugarEntregaGUI.domicilio();
	}
	
	/**
	 * Metodo que comprueba ue el metodo Acepatr de la Ventana Lugar Entrega GUI funciona adecuadamente
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * 
	 */
	@Test
	public void testAceptar() throws NoSuchMethodException, SecurityException{
		ventanaLugarEntregaGUI.aceptar(tiendaGUI,productos,web, pedidoTarget, precio);
		ventanaLugarEntregaGUI.datosValidar2("12333333a","Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
	}
	
//	@Test
//	public void testAceptar2() {
//		ventanaLugarEntregaGUI.aceptar(tiendaGUI,productos,web, pedidoTarget, precio);
//		ventanaLugarEntregaGUI.datosValidar2("12333333a","Albacete");
//		ventanaLugarEntregaGUI.aceptar2();
//	}
//	
//	@Test
//	public void testAceptarFallo(){
//		ventanaLugarEntregaGUI.datosValidarNada("12333333a","Albacete");
//	}
}
