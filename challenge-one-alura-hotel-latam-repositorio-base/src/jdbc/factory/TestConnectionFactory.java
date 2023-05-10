package jdbc.factory;

import java.sql.SQLException;

public class TestConnectionFactory {
	  public static void main(String[] args) throws SQLException {
	        ConnectionFactory factory = new ConnectionFactory();
	        
	        for (int i = 0; i < 20; i++) {
	            factory.recuperaConexion();
	            
	            System.out.println("Abriendo conexión #" + i);
	        }
	    }
}
