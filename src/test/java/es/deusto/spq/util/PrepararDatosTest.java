package es.deusto.spq.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class PrepararDatosTest{



@Before 
public void setUp() {
}
@Test
public void testPerpararDatosXML(){
    assertEquals("English", PrepararDatos.getInstance().getIngles().get("nombre1"));
}
@Test
public void testGetEspanyol(){
    HashMap<String, String> idioma_esp_1 = new HashMap<>();
    idioma_esp_1.put("nombre1", "Español");
    idioma_esp_1.put("nombre2", "Ingles");
    assertEquals(idioma_esp_1.get("nombre1"), PrepararDatos.getInstance().getEspanyol().get("nombre1"));
}
@Test
public void testGetIngles(){
    HashMap<String, String> idioma_en_1 = new HashMap<>();
    idioma_en_1.put("nombre1", "English");
    idioma_en_1.put("nombre2", "Spanish");
    assertEquals(idioma_en_1.get("nombre1"), PrepararDatos.getInstance().getIngles().get("nombre1"));
}
@Test
public void testGetPalabraEspanyol(){   
    assertEquals("Español", PrepararDatos.getInstance().getPalabraEspanyol("nombre1"));
}
@Test
public void testGetPalabraIngles(){
    assertEquals("English", PrepararDatos.getInstance().getPalabraIngles("nombre1"));
}
}