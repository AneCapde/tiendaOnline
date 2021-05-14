package es.deusto.spq.util;

import java.util.HashMap;

public interface IPrepararDatos {

    public HashMap<String, String> getEspanyol();
	public HashMap<String, String> getIngles();
	public String getPalabraEspanyol(String palabra);
	public String getPalabraIngles( String palabra);
	public void cargarDatosXML();

}
