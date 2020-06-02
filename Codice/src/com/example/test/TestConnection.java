package com.example.test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import Model.DriverManagerConnectionPool;


public class TestConnection
{
	public static void main(String[] args)
	{
		try
		{
			DriverManagerConnectionPool driver = new DriverManagerConnectionPool(); 
			Connection connection = driver.getConnection();
			String sql = 
					"SELECT COUNT(*)"  
					+"FROM UTENTE " 
					+"WHERE username = ?;";
			// interfaccia PreparedStatement
			PreparedStatement ps = connection.prepareStatement(sql);
			// associamo ai parametri i valori
			ps.setString(1, "admin");
			// eseguiamo la query
			ResultSet rs = ps.executeQuery();
			int ris = -1;
			while(rs.next())
			{
				 ris = rs.getInt("COUNT(*)");
			}
			
			System.out.println(""+ris+"");
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}
