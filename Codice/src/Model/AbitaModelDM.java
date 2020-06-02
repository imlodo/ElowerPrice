package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class AbitaModelDM implements ItemModel<BeanAbita, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public AbitaModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		System.out.println("DriverManager Abita Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanAbita doRetrieveByKey(String utente_username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL =
						"SELECT * "  
						+"FROM ABITA " 
						+"WHERE utente_username = ?;";
		BeanAbita bean;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, utente_username);

			//System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			bean = new BeanAbita();
			
			while(rs.next())
			{
				bean.setUtente_cap(rs.getString("utente_cap"));
				bean.setUtente_username(utente_username);
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
	public Collection<BeanAbita> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanAbita> indirizzi = new ArrayList<BeanAbita>();
		String selectSQL = "SELECT * FROM ABITA";

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
				BeanAbita bean = new BeanAbita();
				bean.setUtente_cap(rs.getString("utente_cap"));
				bean.setUtente_username(rs.getString("utente_username"));
				indirizzi.add(bean);
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

		return indirizzi;
	}

	@Override
	public void doSave(BeanAbita abita) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO ABITA "+ 
				" (utente_cap, utente_username) " +
				" VALUES(?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, abita.getUtente_cap());
			preparedStatement.setString(2, abita.getUtente_username());

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
	public void doUpdate(BeanAbita abita) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE ABITA SET " + 
				"utente_cap = ?" +
				"WHERE utente_username = ?;";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, abita.getUtente_cap());			
			preparedStatement.setString(2, abita.getUtente_username());

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
	public boolean doDelete(BeanAbita abita) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM ABITE WHERE utente_cap = ? AND utente_username = ?;";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, abita.getUtente_cap());
			preparedStatement.setString(2, abita.getUtente_username());
			
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
