package es.deusto.spq.models;

import java.util.HashMap;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Pago {
    
	@Persistent(defaultFetchGroup = "true")
	private String DNI;
	@Persistent(defaultFetchGroup = "true")
	@Join
    private HashMap<String,String> credencialesVisa = new HashMap<String,String>();
	@Persistent(defaultFetchGroup = "true")
	@Join
    private HashMap<String,String> credencialesPaypal = new HashMap<String,String>();

    /** 
	* La clase asocia unas credenciales visa y credenciales paypal con cada cliente registrado
	* @param DNI DNI del cliente
	* @param credencialesvisa Credenciales visa del cliente
	* @param credencialespaypal Credenciales paypal del cliente
	*/
	public Pago(String DNI, HashMap<String,String> credencialesVisa, HashMap<String,String> credencialesPaypal) {
        this.DNI = DNI;
        this.credencialesVisa = credencialesVisa;
        this.credencialesPaypal = credencialesPaypal;
    }

    public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
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

	public String getNumVisa(HashMap<String,String> credencialesVisa) {
		String num = null;
		for (String cv : credencialesVisa.keySet()) {
			num = cv;
		}
		return num;
	}

	public String getEmailPaypal(HashMap<String,String> credencialesPaypal) {
		String email = null;
		for (String cp : credencialesPaypal.keySet()) {
			email = cp;
		}
		return email;
	}
}
