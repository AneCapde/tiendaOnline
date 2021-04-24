package es.deusto.spq.models;

import java.util.HashMap;

public class Pago {
    
    private String DNI;
    private HashMap<String,String> credencialesVisa = new HashMap<String,String>();
    private HashMap<String,String> credencialesPaypal = new HashMap<String,String>();

    public Pago(String DNI, HashMap<String,String> credencialesVisa, HashMap<String,String> credencialesPaypal) {
        this.DNI = DNI;
        this.credencialesVisa = credencialesVisa;
        this.credencialesPaypal = credencialesPaypal;
    }

    public String getDNI() {
		return this.DNI;
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
