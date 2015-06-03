package br.com.thiagoft.exemplojdbccrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.thiagoft.exemplojdbccrud.entity.User;

public class UserDAO {
	
	public List<User> list() {
        Connection conection = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> resultList = new ArrayList<User>();
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT ID,");
        sql.append("NOME");
        sql.append("FROM USER ");
        
        try {
            ps = conection.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                do {
                	resultList.add(new User(rs.getLong("ID"),rs.getString("NAME")));
                } while (rs.next());
            }
            rs.getStatement().close();
            return resultList;
        } catch (Exception e) {
            System.out.println("Erro ao listar os Usuários");
            ConnectionManager.closeConnection();
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro ao fechar o ResultSet ou o PrepareStatement.");
            }
        }

    }
	
	public void save(User user) {
		Connection connection = ConnectionManager.getConnection();
        PreparedStatement ps = null;
		
        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO USER (ID,NAME) VALUES (?,?)");
        
        try {
	        ps = connection.prepareStatement(sql.toString());
	        ps.setLong(1, user.getId());
	        ps.setString(2, user.getName());
	        ps.executeUpdate();
        } catch (SQLException e) {
        	System.out.println("Erro ao Inserir o usuário: "+e.getMessage());
        }
        
	}
	
}
