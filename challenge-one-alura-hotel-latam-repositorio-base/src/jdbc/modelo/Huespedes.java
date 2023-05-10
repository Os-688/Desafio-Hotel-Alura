package jdbc.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Huespedes {
	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
	private Date fechaDeNacimiento;
	
	private String nacionalidad;
	
	private String telefono;
	
	private Integer idReserva;
	
	private List<Integer> reservas = new ArrayList<>();
	
	

	public Huespedes(Integer id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.setIdReserva(idReserva);
	}
	
	public Huespedes( String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.setIdReserva(idReserva);
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Integer> getReservas() {
		return reservas;
	}
	
	public void agregarItem(Integer idReserva) {
		reservas.add(idReserva);
	}

	public void setReservas(List<Integer> reservas) {
		this.reservas = reservas;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}
	
	
}
