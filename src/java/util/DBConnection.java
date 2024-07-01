package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String DB_URL = "jdbc:derby://localhost:1527/AcresCommune;create=true";
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "app";

    
            public static Connection createConnection(){
            Connection con = null;
            
             try {
            Class.forName("org.apache.derby.jdbc.ClientDataSource");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Printing Connection object " + con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return con;
    }
}

