package jdbc.modelo;

import java.sql.Date;

public class Reserva {
	
	private Integer id;
	
	private Date fechaEntrada;
	
	private Date fechaSalida;
	
	private Integer valor;
	
	private String formaPago;
	
	public Reserva(Date fechaEntrada, Date fechaSalida, Integer valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, Integer valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	 @Override
	    public String toString() {
	        return String.format(
	                "{ id: %d, fecha de entrada: %s, fecha de salida: %s, forma de pago: %s }",
	                this.id, this.fechaEntrada, this.fechaSalida, this.formaPago);
	    }
}
