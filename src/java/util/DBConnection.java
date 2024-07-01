package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
            public static Connection createConnection(){
            Connection con = null;
            String url;
                url = "jdbc:derby://localhost:1527/AcresCommune;create=true";
            String username ="app";
            String password = "app";
            
            try{
                try{
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Printing Connection object "+ con);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                        if (con != null) {
                            try {
                                con.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            return con;
        }
}

