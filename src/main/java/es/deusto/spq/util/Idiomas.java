package es.deusto.spq.util;

import es.deusto.spq.cliente.VentanaLogin;

public enum Idiomas {
    Ingles, Español;

    public static String seleccionarPalabra(String palabra) {
        if(Idiomas.Español.equals(VentanaLogin.idioma)){
            return PrepararDatos.getInstance().getPalabraEspanyol(palabra);
        }else{
            return PrepararDatos.getInstance().getPalabraIngles(palabra);
        }
    }
}