package Modelos;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable(detachable = "true")
public class Cliente {
	

	public enum Genero {
		MUJER,
		HOMBRE,
		NO_BINARIO
	}
	public String DNI;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private int telefono;
	private String direccion;
	private Genero genero ;
	private int cod_postal;
	private String provincia;
	private String localidad;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "cliente", dependentElement = "true")
	@Join
	private ArrayList<Pedido> pedidos = new ArrayList<>(); 
	
	
	
	public Cliente(String DNI, String nombre,String apellidos, String email, String password,
			int telefono, String direccion, Genero genero, int cod_postal, String provincia, String localidad, ArrayList<Pedido> pedidos) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.direccion = direccion;
		this.genero = genero;
		this.cod_postal = cod_postal;
		this.provincia = provincia;
		this.localidad = localidad;
		this.pedidos = pedidos;
	}	
	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getCod_postal() {
		return cod_postal;
	}

	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void removePedidos() {
		for (Pedido r : this.pedidos) {
			this.pedidos.remove(r);
		}
	}
	
	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", password=" + password + ", telefono=" + telefono + ", direccion=" + direccion + ", genero="
				+ genero + ", cod_postal=" + cod_postal + ", provincia=" + provincia + ", localidad=" + localidad
				+ ", pedidos=" + pedidos + "]";
	}
	


	
}
