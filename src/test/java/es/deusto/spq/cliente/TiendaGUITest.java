package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.*;

import es.deusto.spq.util.Idiomas;

@RunWith(MockitoJUnitRunner.class)
public class TiendaGUITest {

    private TiendaGUI tiendaGUI = Mockito.mock(TiendaGUI.class);

    @Test
    public void testGetJButtons(){
        ArrayList<JButton> buttons = new ArrayList<>();
        assertEquals(buttons, tiendaGUI.getJButtons());
    }
    @Test
    public void testGetJLabels(){
        ArrayList<JLabel> labels = new ArrayList<>();
        assertEquals(labels, tiendaGUI.getJLabels());
    }
    @Test
    public void cambiarIdioma(){
        TiendaGUI.idioma = Idiomas.Español;
        assertEquals(Idiomas.Español, TiendaGUI.idioma);

        JComboBox<String> combo = new JComboBox<>();
        combo.addItem("Ingles");
        combo.setSelectedItem("Ingles");
        
        try(MockedStatic<TiendaGUI> utilities = Mockito.mockStatic(TiendaGUI.class)){
			utilities.when(TiendaGUI::getComboBox).thenReturn(combo);
		}

        tiendaGUI.cambiarIdioma();
        assertEquals(Idiomas.Español, TiendaGUI.idioma);
    }
        
}
