package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class UserModelDM implements ItemModel<BeanUtente, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public UserModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		//System.out.println("DriverManager User Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanUtente doRetrieveByKey(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL =
						"SELECT * "  
						+"FROM UTENTE " 
						+"WHERE username = ? ;";
		BeanUtente bean;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, username);

			//System.out.println("doRetrieveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			bean = new BeanUtente();
			
			while(rs.next())
			{
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));
				bean.setCf(rs.getString("cf"));
				bean.setName(rs.getString("nome"));
				bean.setSurname(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setType(Integer.parseInt(rs.getString("tipo")));
			}
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				dmcp.releaseConnection(connection);
			}
		}
		return bean;
	}
	

	@Override
	public Collection<BeanUtente> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanUtente> utenti = new ArrayList<BeanUtente>();
		String selectSQL = "SELECT * FROM UTENTE";

		if(order != null && !order.equals(""))
		{
			selectSQL += " ORDER BY " + order;
		}

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			//System.out.println("doRetrieveAll: " + preparedStatement.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				BeanUtente bean = new BeanUtente();

				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));
				bean.setCf(rs.getString("cf"));
				bean.setName(rs.getString("nome"));
				bean.setSurname(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setType(Integer.parseInt(rs.getString("tipo")));

				utenti.add(bean);
			}
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				dmcp.releaseConnection(connection);
			}
		}

		return utenti;
	}

	@Override
	public void doSave(BeanUtente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO UTENTE " + 
				" (username, email, cf, nome, cognome, password, tipo) " +
				" VALUES(?,?,?,?,?,?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getEmail());
			preparedStatement.setString(3, utente.getCf());
			preparedStatement.setString(4, utente.getName());
			preparedStatement.setString(5, utente.getSurname());
			preparedStatement.setString(6, utente.getPassword());
			preparedStatement.setString(7, String.valueOf(utente.getType()));

			//System.out.println("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			//Solo in questo momento la modifica viene resa visibile a tutti
			connection.commit();
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				dmcp.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doUpdate(BeanUtente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE UTENTE SET " + 
				" email = ?, cf = ?, nome = ?, cognome = ?, password = ?, tipo = ? " +
				" WHERE username = ?";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getCf());
			preparedStatement.setString(3, utente.getName());
			preparedStatement.setString(4, utente.getSurname());
			preparedStatement.setString(5, utente.getPassword());
			preparedStatement.setString(6, String.valueOf(utente.getType()));
			preparedStatement.setString(7, utente.getUsername());

			//System.out.println("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			//Solo in questo momento la modifica viene resa visibile a tutti
			connection.commit();
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				dmcp.releaseConnection(connection);
			}
		}
	}

	@Override
	public boolean doDelete(BeanUtente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM UTENTE WHERE username = ?";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, utente.getUsername());
			
			//System.out.println("doUpdate: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				dmcp.releaseConnection(connection);
			}
		}
		return (result == 0 ? false : true);
	}

}
