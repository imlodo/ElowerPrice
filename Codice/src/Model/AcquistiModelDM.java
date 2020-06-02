package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.BeanProdottoFornito.opzioniAcquisto;


public class AcquistiModelDM implements ItemModel<BeanAcquisti, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public AcquistiModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		System.out.println("DriverManager Acquisti Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanAcquisti doRetrieveByKey(String username_and_prod_name) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int index = username_and_prod_name.indexOf("|");
		String username = username_and_prod_name.substring(0, index);
		String prod_name = username_and_prod_name.substring(index+1);
		
		String selectSQL =
						"SELECT * "  
						+"FROM ACQUISTA " 
						+"WHERE utente_username = ? AND prodotto_name = ?;";
		BeanAcquisti bean;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, prod_name);

			//System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			bean = new BeanAcquisti();
			
			while(rs.next())
			{
				bean.setUsername_utente(rs.getString("utente_username"));
				bean.setProdotto_name(rs.getString("prodotto_name"));
				bean.setPrezzo_acquisto(rs.getDouble("prezzo_acquisto"));
				bean.setOpzione(opzioniAcquisto.valueOf(rs.getString("tipo_acquisto")));
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
	public Collection<BeanAcquisti> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanAcquisti> acquisti = new ArrayList<BeanAcquisti>();
		String selectSQL = "SELECT * FROM ACQUISTA";

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
				BeanAcquisti bean = new BeanAcquisti();
				bean.setUsername_utente(rs.getString("utente_username"));
				bean.setProdotto_name(rs.getString("prodotto_name"));
				bean.setPrezzo_acquisto(rs.getDouble("prezzo_acquisto"));
				bean.setOpzione(opzioniAcquisto.valueOf(rs.getString("tipo_acquisto")));
				acquisti.add(bean);
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

		return acquisti;
	}

	@Override
	public void doSave(BeanAcquisti acquisto) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO ACQUISTA " + 
				" (utente_username, prodotto_name, prezzo_acquisto, tipo_acquisto) " +
				" VALUES(?,?,?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, acquisto.getUsername_utente());
			preparedStatement.setString(2, acquisto.getProdotto_name());
			preparedStatement.setDouble(3, acquisto.getPrezzo_acquisto());
			preparedStatement.setString(4, acquisto.getOpzione().name());

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
	public void doUpdate(BeanAcquisti acquisto) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE ACQUISTA SET " + 
				" prezzo_acquisto = ?, tipo_acquisto = ?" +
				" WHERE utente_username = ? AND prodotto_name = ?";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setDouble(1, acquisto.getPrezzo_acquisto());
			preparedStatement.setString(2, acquisto.getOpzione().name());
			preparedStatement.setString(3, acquisto.getUsername_utente());
			preparedStatement.setString(4, acquisto.getProdotto_name());

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
	public boolean doDelete(BeanAcquisti acquisto) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE "
						+ "FROM ACQUISTA "
						+ "WHERE utente_username = ? AND prodotto_name = ?";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1,acquisto.getUsername_utente());
			preparedStatement.setString(2,acquisto.getProdotto_name());
			
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
