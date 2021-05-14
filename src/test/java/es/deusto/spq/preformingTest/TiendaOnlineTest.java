package es.deusto.spq.preformingTest;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
import es.deusto.spq.models.Cliente;

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class TiendaOnlineTest {
    
    static Logger logger = Logger.getLogger(TiendaGUI.class.getName());
    private HttpServer server;
    private WebTarget target;

	// If you use the EmptyReportModule, the report is not generated
	//@Rule public ContiPerfRule rule = new ContiPerfRule(new EmptyReportModule());
	@Rule 
    public ContiPerfRule rule = new ContiPerfRule();

    @Before 
    public void setUp() {
     // start the server
     server = Main.startServer();
     // create the client
     Client c = ClientBuilder.newClient();
     target = c.target(Main.BASE_URI);
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
		Cliente cliente = clientesTarget.request(MediaType.APPLICATION_JSON).get(Cliente.class);
    }


}
