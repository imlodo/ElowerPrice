package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class DriverManagerConnectionPool 
{

	private List<Connection> freeDbConnections;

	static 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	public DriverManagerConnectionPool() 
	{
		freeDbConnections = new LinkedList<Connection>();
	}

	private synchronized Connection createDBConnection() throws SQLException 
	{
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String db = "elowerprice";
		String username = "Utente";
		String password = "Davideiannaccone1.";

		newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ 
				port+"/"+db+"?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", username, password);

		newConnection.setAutoCommit(false);
		return newConnection;
	}	

	public synchronized Connection getConnection() throws SQLException 
	{
		Connection connection;

		if (!freeDbConnections.isEmpty()) 
		{
			//System.out.println("Nuova Connessione");
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try 
			{
				if (connection.isClosed())
					connection = getConnection();
			}
			catch (SQLException e) 
			{
				connection.close();
				connection = getConnection();
			}
		} 
		else 
		{
			connection = createDBConnection();		
		}

		return connection;
	}

	public synchronized void releaseConnection(Connection connection) 
			throws SQLException 
	{
		if(connection != null) freeDbConnections.add(connection);
	}	


}

