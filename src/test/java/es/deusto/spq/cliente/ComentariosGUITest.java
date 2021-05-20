package es.deusto.spq.cliente;

import java.util.ArrayList;

import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Tallas;
import es.deusto.spq.models.Colores;

@RunWith(MockitoJUnitRunner.class)
public class ComentariosGUITest {

	ComentariosGUI comentariosGUI;
	@Mock
	WebTarget web = Mockito.mock(WebTarget.class);
	
	@Mock
	TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);
	Producto p1;
	
	ArrayList<Colores> colores;
	ArrayList<Tallas> tallas;
	
	/**
	 * Este método se ejecuta antes de que se ejecute cualquier método de prueba JUnit: se anota con la anotación Before.
	*/
	@Before
	public void inicio(){
		colores = new ArrayList<>();
		colores.add(Colores.AMARILLO);
		tallas = new ArrayList<>();
		tallas.add(Tallas.L);
		ArrayList<String> coment = new ArrayList<>();
		p1 = new Producto("producto1", "producto1", 1, 1, "producto1", 
				new SubCategoria("Subcategoria1", "Subcategoria1", new Categoria("categoria1", "categoria1")), new Marca("marca1", "marca1"), colores, tallas);
		coment.add("Comentario1");
		p1.setComentarios(coment);
		comentariosGUI = new ComentariosGUI(tiendaGUI, p1, web);
	}
	
	
	@Test
	public void testCancelar() {
		comentariosGUI.botonCancelar(tiendaGUI);
	}
	
}
