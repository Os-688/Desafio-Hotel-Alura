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

public class HuespedesDAO {
	
	private Connection con;
	
	public HuespedesDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huespedes huesped) {
	        try {
	            PreparedStatement statement;
	                statement = con.prepareStatement(
	                        "INSERT INTO HUESPEDES "
	                        + "(nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, reservas_id)"
	                        + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	    
	            try (statement) {
	                statement.setString(1, huesped.getNombre());
	                statement.setString(2, huesped.getApellido());
	                statement.setDate(3, huesped.getFechaDeNacimiento());
	                statement.setString(4, huesped.getNacionalidad());
	                statement.setString(5, huesped.getTelefono());
	                statement.setInt(6, huesped.getIdReserva());

	    
	                statement.execute();
	    
	                final ResultSet resultSet = statement.getGeneratedKeys();
	    
	                try (resultSet) {
	                    while (resultSet.next()) {
	                        huesped.setId(resultSet.getInt(1));
	                        
	                        System.out.println(String.format("Fue insertado el producto: %s", huesped));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	
    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");

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
    
    public int modificar(String nombre, String apellido, Date fechaDeNacimiento, Integer id, String nacionalidad, String telefono, Integer reservas_id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE HUESPEDES SET "
                    + " NOMBRE = ?, "
                    + " APELLIDO = ?,"
                    + " FECHADENACIMIENTO = ?,"
                    + " NACIONALIDAD = ?,"
                    + " TELEFONO = ?,"
                    + " RESERVAS_ID = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setDate(3, fechaDeNacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, reservas_id);
                statement.setInt(7, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Huespedes> buscar (){
    	List<Huespedes> huespedes = new ArrayList<Huespedes>();
    	try {
    		String sql = "SELECT id, nombre, apellido, fechaDeNacimiento, " 
    				+  "nacionalidad, telefono, reservas_id FROM huespedes";
    		try(PreparedStatement pstm = con.prepareStatement(sql)){
    			pstm.execute();
    			
    			transformarResultSetEnHuespedes(huespedes, pstm);
    		}
    		return huespedes;
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    public List<Huespedes> buscaPorId(Integer id) {
        List<Huespedes> huespedes = new ArrayList<>();

        try {
            String sql = "SELECT id, nombre, apellido, fechaDeNacimiento, " 
    				+  "nacionalidad, telefono, reservas_id FROM HUESPEDES WHERE ID = ?";
            
            System.out.println(sql);
            
            try(PreparedStatement pstm = con.prepareStatement(sql)){
    			pstm.setInt(1, id);
    			pstm.execute();
    			
    			transformarResultSetEnHuespedes(huespedes, pstm);
    		}
    		return huespedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huespedes> buscaPorApellido(String Apellido) {
        List<Huespedes> huespedes = new ArrayList<>();

        try {
            String sql = "SELECT id, nombre, apellido, fechaDeNacimiento, " 
    				+  "nacionalidad, telefono, reservas_id FROM HUESPEDES WHERE apellido = ?";
            
            System.out.println(sql);
            
            try(PreparedStatement pstm = con.prepareStatement(sql)){
    			pstm.setString(1, Apellido);
    			pstm.execute();
    			
    			transformarResultSetEnHuespedes(huespedes, pstm);
    		}
    		return huespedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void transformarResultSetEnHuespedes(List<Huespedes> huespedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()){
			while (rst.next()) {
				Huespedes huesped = new Huespedes(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
				huespedes.add(huesped);
			}
		}
	}
}
