package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	 * This method is executed before any JUnit test method is executed: It is annotated with the Before annotation.
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
	
	
	@Test 
	public void testCancelar() {
		ventanaLugarEntregaGUI.Cancelar(tiendaGUI);
	}
	
	@Test
	public void testValidarCorreos()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidar("12333333a","Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(true, result);	
	}
	
	@Test
	public void testValidarDomicilio()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidar2("12333333a","Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(true, result);	
	}
	
	@Test
	public void testValidarNada()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidarNada("12333333a","Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidaSinProvincia()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidar("12333333a", "");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarSinDireccion()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		ventanaLugarEntregaGUI.datosValidar2(null,"Albacete");
		Method method = VentanaLugarEntregaGUI.class.getDeclaredMethod("validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(ventanaLugarEntregaGUI);
		assertEquals(false, result);	
	}	
	
	@Test 
	public void testCorreos() {
		ventanaLugarEntregaGUI.correos();
	}
	
	@Test 
	public void testDomicilio() {
		ventanaLugarEntregaGUI.domicilio();
	}
	
	@Test
	public void testAceptar1() {
		ventanaLugarEntregaGUI.aceptar(tiendaGUI,productos,web, pedidoTarget, precio);
		ventanaLugarEntregaGUI.datosValidar("12333333a","Albacete");
		ventanaLugarEntregaGUI.datosValidar2("12333333a","Albacete");
		ventanaLugarEntregaGUI.aceptar1();
	}
	
	@Test
	public void testAceptar2() {
		ventanaLugarEntregaGUI.aceptar(tiendaGUI,productos,web, pedidoTarget, precio);
		ventanaLugarEntregaGUI.datosValidar2("12333333a","Albacete");
		ventanaLugarEntregaGUI.aceptar2();
	}
	
	@Test
	public void testAceptarFallo(){
		ventanaLugarEntregaGUI.datosValidarNada("12333333a","Albacete");
	}
}
