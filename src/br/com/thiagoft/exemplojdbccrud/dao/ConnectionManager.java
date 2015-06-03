package br.com.thiagoft.exemplojdbccrud.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	private static Connection connection = null;
	
	private static final String login = "";
	private static final String url = "";
	private static final String password = "";

    public static Connection getConnection() {
        try {
            if (connection != null) {
                return connection;
            }
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, login, password);
            
            if (connection != null) {
                return connection;
            } else { 
                return null;
            }
        } catch (Exception e) {
        	System.out.println("Erro na comunicação com o banco de dados: "+e.getMessage());
        	return null;
        }
    }

    public static boolean closeConnection() {
        try {
            System.out.println("Fechando Conexão.");
            connection.close();
            connection = null;
            System.out.println("Conexão Fechada.");
            return true;
        } catch (Exception e) {
            connection = null;
            return false;
        }
    }
}
