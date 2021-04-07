package models;

import javax.jdo.annotations.PersistenceCapable;


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
	private Genero genero;
	private int cod_postal;
	private String provincia;
	private String localidad;
	
	public Cliente(String DNI, String nombre,String apellidos, String email, String password,
			int telefono, String direccion, Genero genero, int cod_postal, String provincia, String localidad) {
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
	}	
	
	public String getDNI() {
		return this.DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getCod_postal() {
		return this.cod_postal;
	}

	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", password=" + password + ", telefono=" + telefono + ", direccion=" + direccion + ", genero="
				+ genero + ", cod_postal=" + cod_postal + ", provincia=" + provincia + ", localidad=" + localidad
				+ "]";
	}
		
}
