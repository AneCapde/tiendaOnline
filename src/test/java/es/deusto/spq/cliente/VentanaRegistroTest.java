package es.deusto.spq.cliente;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EmptyStackException;

import javax.swing.JFrame;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;
import org.mockito.Mockito;

public class VentanaRegistroTest {
	WebTarget web = Mockito.mock(WebTarget.class);
	JFrame padre = Mockito.mock(JFrame.class);
	private VentanaRegistro vent = new VentanaRegistro(padre, web); 
	

	
	@Test
	public void comprobarEmailCorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		Method method = VentanaRegistro.class.getDeclaredMethod("elEmailCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "asdasdasd@gmail.com");
		assertEquals(true, result);
	}
	
	@Test
	public void comprobarEmailIncorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		Method method = VentanaRegistro.class.getDeclaredMethod("elEmailCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "asdasdas@gmailcom");
		assertEquals(false, result);
	}
	
	@Test
	public void comprobarNombreCorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("nombreCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "Ane");
		assertEquals(true, result);
	}
	
	@Test
	public void comprobarNombreIncorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("nombreCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "4563");
		assertEquals(false, result);
	}
	
	@Test
	public void comprobarDNICorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("DNICorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "12333333a");
		assertEquals(true, result);

	}
	
	@Test
	public void comprobarDNIIncorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("DNICorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "4511s1163");
		assertEquals(false, result);

	}
	
	@Test
	public void comprobarCodPostCorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("CodPostalCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "12345");
		assertEquals(true, result);
	}
	
	@Test
	public void comprobarCodPostIncorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("CodPostalCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "12s45");
		assertEquals(false, result);
	}
	
	@Test
	public void comprobarTelCorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("telefonoCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "123456789");
		assertEquals(true, result);
	}
	
	@Test
	public void comprobarTelIncorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("telefonoCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "12312312312313123123123123");
		assertEquals(false, result);
	}
	
	@Test
	public void comprobarApellidoCorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("apellidoCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "sdasdasdasda");
		assertEquals(true, result);
	}
	
	@Test
	public void comprobarApellidoIncorrecto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("apellidoCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "asdasd2");
		assertEquals(false, result);
	}
	
	@Test
	public void comprobarLocalidadCorrecta() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("localidadCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "asdasd");
		assertEquals(true, result);
	}
	
	@Test
	public void comprobarLocalidadIncorrecta() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("localidadCorrecto", String.class);
		method.setAccessible(true);
		boolean result = (boolean) method.invoke(vent, "asdasd2");
		assertEquals(false, result);
	}
	
	@Test
	public void comprobarPassIncorrectaNoEspecial() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("elPasswordCorrecto", String.class);
		method.setAccessible(true);
		String result = (String) method.invoke(vent, "@1A|aaAaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertEquals("no tiene caracteres especiales como ( ! # $ % & ' ( ) + - )", result);	
	}
	
	@Test
	public void comprobarPassArroba() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("elPasswordCorrecto", String.class);
		method.setAccessible(true);
		String result = (String) method.invoke(vent, "1A!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertEquals("Coloque un @ para mayor seguridad", result);	
	}


	@Test
	public void comprobarPassMayuscula() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("elPasswordCorrecto", String.class);
		method.setAccessible(true);
		String result = (String) method.invoke(vent, "1@a!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertEquals("Facil", result);	
	}


	@Test
	public void comprobarPassMinimo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("elPasswordCorrecto", String.class);
		method.setAccessible(true);
		String result = (String) method.invoke(vent, "!@A");
		assertEquals("Inutilizable: no cumple con el mínimo de caracteres!", result);	
	}
	@Test
	public void comprobarPassSinNum() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("elPasswordCorrecto", String.class);
		method.setAccessible(true);
		String result = (String) method.invoke(vent, "!@Aaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertEquals("Medio", result);	
	}

	@Test
	public void comprobarPassCorrecta() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = VentanaRegistro.class.getDeclaredMethod("elPasswordCorrecto", String.class);
		method.setAccessible(true);
		String result = (String) method.invoke(vent, "!@A1aaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertEquals("Muy Buena", result);	
	}
	
	@Test
	public void testValidarDatos()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "nom", "apel", "12333333a", "123456789", "dir", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(true, result);	
	}
	
	@Test
	public void testValidarDatosEmailMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em", "nom", "apel", "12333333a", "123456789", "dir", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosDniMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "nom", "apel", "12333a333", "123456789", "dir", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosDirecionMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "nom", "apel", "12333333a", "123456789", "", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosTelefonoMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "nom", "apel", "12333333a", "12345", "dir", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosLocalidadMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "nom", "apel", "12333333a", "123456789", "dir", "12435", "Albacete", "");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosNombreMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "", "apel", "12333333a", "123456789", "dir", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosProvinciaMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "nom", "apel", "12333333a", "123456789", "dir", "12435", "", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosCodPostalMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "nom", "apel", "12333333a", "123456789", "dir", "1243a5", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosApellidosMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar("em@gmail.com", "nom", "apel1", "12333333a", "123456789", "dir", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	@Test
	public void testValidarDatosGeneroMal()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar2("em@gmail.com", "nom", "apel", "12333333a", "123456789", "dir", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		assertEquals(false, result);	
	}
	
	//no salta el catch
	@Test
	public void testValidarDatosCatch()throws EmptyStackException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		vent.datosValidar2("em@gmail.com", "nom", "apel1", "12333333a", "123456789", "dir", "12435", "Albacete", "loc");
		Method method = VentanaRegistro.class.getDeclaredMethod("Validar");
		method.setAccessible(true);
		Boolean result = (Boolean) method.invoke(vent);
		
		assertEquals(false, result);	
	}
	
	
	

}
