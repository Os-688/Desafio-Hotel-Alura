package jdbc.controller;

import java.sql.Date;
import java.util.List;

import jdbc.dao.HuespedesDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Huespedes;

public class HuespedesController{
	
	private HuespedesDAO huespedes;

	public HuespedesController() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		huespedes = new HuespedesDAO(connectionFactory.recuperaConexion());
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedes.guardar(huespedes);
	}
	
	public int modificar(String nombre, String apellido, Date fechaDeNacimiento, Integer id, String nacionalidad, String telefono, Integer reservas_id) {
		return this.huespedes.modificar(nombre, apellido, fechaDeNacimiento, id, nacionalidad, telefono, reservas_id);
	}
	
	public int eliminar(Integer id) {
		return this.huespedes.eliminar(id);
	}
	
    public List<Huespedes> buscar(){
    	return this.huespedes.buscar();
    }
    
    public List<Huespedes> buscarPorId(Integer id){
    	return this.huespedes.buscaPorId(id);
    }
    
    public List<Huespedes> buscarPorApellido(String Apellido){
    	return this.huespedes.buscaPorApellido(Apellido);
    }
    
    
}
