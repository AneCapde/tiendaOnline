package es.deusto.spq.models;

import java.util.HashMap;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Pago {
    
	@Persistent(defaultFetchGroup = "true")
	private Cliente cliente;
    private HashMap<String,String> credencialesVisa = new HashMap<String,String>();
    private HashMap<String,String> credencialesPaypal = new HashMap<String,String>();

    public Pago(Cliente cliente, HashMap<String,String> credencialesVisa, HashMap<String,String> credencialesPaypal) {
        this.cliente = cliente;
        this.credencialesVisa = credencialesVisa;
        this.credencialesPaypal = credencialesPaypal;
    }

    public Cliente getCliente() {
		return this.cliente;
	}

	public HashMap<String, String> getCredencialesVisa() {
		return credencialesVisa;
	}

	public void setCredencialesVisa(HashMap<String, String> credencialesVisa) {
		this.credencialesVisa = credencialesVisa;
	}

    public HashMap<String, String> getCredencialesPaypal() {
		return credencialesPaypal;
	}

	public void setCredencialesPaypal(HashMap<String, String> credencialesPaypal) {
		this.credencialesPaypal = credencialesPaypal;
	}
}
