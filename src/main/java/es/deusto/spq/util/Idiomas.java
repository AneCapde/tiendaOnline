package es.deusto.spq.util;

import es.deusto.spq.cliente.TiendaGUI;

public enum Idiomas {
    Ingles, Español;

    public static String seleccionarPalabra(String palabra) {
        if(Idiomas.Español.equals(TiendaGUI.idioma)){
            return PrepararDatos.getInstance().getPalabraEspanyol(palabra);
        }else{
            return PrepararDatos.getInstance().getPalabraIngles(palabra);
        }
    }
}