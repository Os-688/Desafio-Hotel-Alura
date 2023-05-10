package jdbc.controller;

import java.sql.Date;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Reserva;

public class ReservasController {
	
	private ReservaDAO reserva;
	
    public ReservasController() {
        ConnectionFactory factory = new ConnectionFactory();
        this.reserva = new ReservaDAO(factory.recuperaConexion());
    }
    
    public void guardar(Reserva reserva) {
    	this.reserva.guardar(reserva);
    }
    
    public int modificar(Date fechaEntrada, Date fechaSalida, Integer id, Integer valor, String formaPago) {
    	return this.reserva.modificar(fechaEntrada, fechaSalida, id, valor, formaPago);
    }
    
    public int eliminar(Integer id) {
    	return this.reserva.eliminar(id);
    }
    
    public List<Reserva> buscar(){
    	return this.reserva.buscar();
    }
    
    public List<Reserva> buscaPorId(Integer id){
    	return this.reserva.buscaPorId(id);
    }
    
}
