package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Ventanas.VentanaRegistro;
import dao.DBManager;
import models.Cliente;
import models.Cliente.Genero;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class PruebaTest {
    private Cliente cliente1;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Cliente> listaClientes2;


    @Before
    public void datosPrueba() {
        Genero g = (Genero) Genero.HOMBRE;
        cliente1 = new Cliente("78887877S", "Juan", "Pali Polo", "abcd@gmail.com", "123r", 882888899, "kaltza 12", g, 11334, "Albacete", "Aso");
        listaClientes = DBManager.getInstance().getClientes();

    }

    @Test
    public void testRegistrarClienteBien() {
        DBManager.getInstance().store(cliente1);
        listaClientes2 = DBManager.getInstance().getClientes();
        assertEquals(listaClientes.size()+1, listaClientes2.size());
    }

    @Test
    public void testClienteYaRegistrado() {
        DBManager.getInstance().store(cliente1);
        listaClientes = DBManager.getInstance().getClientes();
        assertEquals(listaClientes.size(), listaClientes2.size());
    }

}
