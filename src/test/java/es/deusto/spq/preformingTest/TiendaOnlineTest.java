package es.deusto.spq.preformingTest;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.spq.Main;
import es.deusto.spq.cliente.TiendaGUI;
import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Pedido;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class TiendaOnlineTest {
    
    static Logger logger = Logger.getLogger(TiendaGUI.class.getName());
    private HttpServer server;
    Cliente cliente;
    List<Categoria> categorias;
    List<SubCategoria> subCategorias;
    List<Producto> productos;
    
    
    // If you use the EmptyReportModule, the report is not generated
	//@Rule public ContiPerfRule rule = new ContiPerfRule(new EmptyReportModule());
	@Rule 
    public ContiPerfRule rule = new ContiPerfRule();

    @Before 
    public void setUp() {
     // start the server
     server = Main.startServer();
    }
    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
    @Test 
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 20000, average = 3000)
    public void connectionCliente(){
        Client client = ClientBuilder.newClient();
        final WebTarget appTarget = client.target("http://localhost:8080/myapp");
        final WebTarget clientesTarget = appTarget.path("/clientes").path("/"+"usuario@gmail.com").path("/"+ "12345r");
		cliente = clientesTarget.request(MediaType.APPLICATION_JSON).get(Cliente.class);
    }
    
    @Test 
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 20000, average = 3000)
    public void connectionCategoria(){
        Client client = ClientBuilder.newClient();
        final WebTarget appTarget = client.target("http://localhost:8080/myapp");
		final WebTarget categoriasTarget = appTarget.path("/categorias");
		GenericType<List<Categoria>> genericType_categoria = new GenericType<List<Categoria>>() {};
        categorias = categoriasTarget.request(MediaType.APPLICATION_JSON).get(genericType_categoria);
    }
    
    @Test 
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 20000, average = 3000)
    public void connectionSubCategoria(){
        Client client = ClientBuilder.newClient();
        final WebTarget appTarget = client.target("http://localhost:8080/myapp");
        final WebTarget subTarget = appTarget.path("/subcategorias");
		GenericType<List<SubCategoria>> genericType_sub = new GenericType<List<SubCategoria>>() {};
		subCategorias = subTarget.request(MediaType.APPLICATION_JSON).get(genericType_sub);
    }
    
    @Test 
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 20000, average = 3000)
    public void connectionProducto(){
    	Client client = ClientBuilder.newClient();
        final WebTarget appTarget = client.target("http://localhost:8080/myapp");
        final WebTarget prodTarget = appTarget.path("/productos");
		GenericType<List<Producto>> genericType_sub = new GenericType<List<Producto>>() {};
		productos = prodTarget.request(MediaType.APPLICATION_JSON).get(genericType_sub);
        
    }
    
    @Test 
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 20000, average = 3000)
    public void connectionPedido(){
    	Date date = new Date();
        Client client = ClientBuilder.newClient();
        final WebTarget appTarget = client.target("http://localhost:8080/myapp");
        final WebTarget pedidoTarget = appTarget.path("/pedidos");
        Pedido pedido = new Pedido(cliente, date, "en proceso", 10, 1, "A Coruña");
        pedidoTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(pedido, MediaType.APPLICATION_JSON));
        
    }
    
    


}
