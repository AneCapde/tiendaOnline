package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.*;
import junit.textui.TestRunner;
import dao.DBManager;
import models.Cliente;
import models.Cliente.Genero;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


public class PruebaTest {
//    private Client client;
//
//    private Cliente cliente1;
//    private List<Cliente> listaClientes;
//    private List<Cliente> listaClientes2;
//
//
//    @Test 
//    public void testRegistrarClienteBien() {
//
//        listaClientes2 = DBManager.getInstance().getClientes();
//        for (int i = 2; i < listaClientes2.size(); i++) {
//            DBManager.getInstance().updateCliente(listaClientes2.get(i));
//        }
//
//        // Acceso a server
//		client = ClientBuilder.newClient();
//        final WebTarget appTarget = ((javax.ws.rs.client.Client) client).target("http://localhost:8080/myapp");
//        final WebTarget clientesTarget = appTarget.path("/clientes");
//
//        cliente1 = new Cliente("78887877S", "Juan", "Pali Polo", "abcd@gmail.com", "123r", 882888899, "kaltza 12", Genero.HOMBRE, 11334, "Albacete", "Aso");   
//        
//        // Accedemos a la lista de clientes a traves del server
//        GenericType<List<Cliente>> genericType_clientes = new GenericType<List<Cliente>>() {};
//        listaClientes = clientesTarget.request(MediaType.APPLICATION_JSON).get(genericType_clientes);
//        
//        // Añadimos un cliente a BD a traves del server
//        boolean existe = false;
//        for (Cliente cliente : listaClientes) {
//            if (cliente.getNombre().equals(cliente1.getNombre())) {
//                existe = true;
//                break;
//            }
//        }
//        // System.out.println(existe);
//        if (existe == false) {
//            // System.out.println("se añade cliente");
//            clientesTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(cliente1, MediaType.APPLICATION_JSON));
//        } 
//
//        // // Comparamos longitud de la lista con la cantidad de clientes que debería tener 
//        // En este caso 2 (Leire, previamente introducida en BD y Juan)
//        listaClientes2 = clientesTarget.request(MediaType.APPLICATION_JSON).get(genericType_clientes);
//        // System.out.println(listaClientes2.size());
//        assertEquals(listaClientes2.size(), 2);
//
//        // Comparamos el nombre del ultimo cliente registrado con el cliente que acabamos de introducir
//        assertEquals(listaClientes2.get(1).getNombre(), "Juan");
//        // System.out.println(listaClientes2.get(1).getNombre());
//    }

}