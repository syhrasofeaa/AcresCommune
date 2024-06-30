/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author syahira sofea
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String DB_URL = "jdbc:derby://localhost:1527/AcresCommune;create=true";
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "app";

    
            public static Connection createConnection(){
            Connection con = null;
            String url = "jdbc:derby://localhost:1527/AcresCommune;create=true";
            String username ="app";
            String password = "app";
            
            try{
                try{
                    Class.forName("com.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Printing Connection object "+ con);
            } catch (Exception e){
                
            }
            return con;
        }
}

