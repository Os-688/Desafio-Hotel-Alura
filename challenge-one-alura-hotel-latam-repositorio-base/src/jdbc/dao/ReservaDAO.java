package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Huespedes;
import jdbc.modelo.Reserva;

public class ReservaDAO {
	
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Reserva reserva) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO RESERVAS "
                        + "(fechaEntrada, fechaSalida, valor, formaPago)"
                        + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setDate(1, reserva.getFechaEntrada());
                statement.setDate(2, reserva.getFechaSalida());
                statement.setInt(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());

    
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el producto: %s", reserva));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
	
    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int modificar(Date fechaEntrada, Date fechaSalida, Integer id, Integer valor, String formaPago) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE RESERVAS SET "
                    + " FECHAENTRADA = ?, "
                    + " FECHASALIDA = ?,"
                    + " VALOR = ?,"
                    + " FORMAPAGO = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setDate(1, fechaEntrada);
                statement.setDate(2, fechaSalida);
                statement.setInt(3, valor);
                statement.setString(4, formaPago);
                statement.setInt(5, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Reserva> buscar (){
    	List<Reserva> reservas = new ArrayList<Reserva>();
    	try {
    		String sql = "SELECT id, fechaEntrada, fechaSalida, valor, " 
    				+  "formaPago FROM reservas";
    		try(PreparedStatement pstm = con.prepareStatement(sql)){
    			pstm.execute();
    			
    			transformarResultSetEnReserva(reservas, pstm);
    		}
    		return reservas;
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    public List<Reserva> buscaPorId(Integer id) {
        List<Reserva> reservas = new ArrayList<>();

        try {
            String sql = "SELECT id, fechaEntrada, fechaSalida, valor, " 
    				+  "formaPago FROM RESERVAS WHERE ID = ?";
            
            System.out.println(sql);
            
            try(PreparedStatement pstm = con.prepareStatement(sql)){
    			pstm.setInt(1, id);
    			pstm.execute();
    			
    			transformarResultSetEnReserva(reservas, pstm);
    		}
    		return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    
	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()){
			while (rst.next()) {
				Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getInt(4), rst.getString(5));
				reservas.add(reserva);
			}
		}
	}
    
    
}
