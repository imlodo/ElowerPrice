package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class RecensioniProdottiModelDM implements ItemModel<BeanRecensioneProdotto, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public RecensioniProdottiModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		//System.out.println("DriverManager Recensioni Prodotti Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanRecensioneProdotto doRetrieveByKey(String prodotto_name_and_utente_username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int index = prodotto_name_and_utente_username.indexOf("|");
		String prodotto_name = prodotto_name_and_utente_username.substring(0, index);
		String utente_username = prodotto_name_and_utente_username.substring(index+1);
		String selectSQL =
						"SELECT * "  
						+"FROM RECENSIONE_PRODOTTO " 
						+"WHERE prodotto_name = ? AND utente_username = ?;";
		BeanRecensioneProdotto bean;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, prodotto_name);
			preparedStatement.setString(2, utente_username);

			//System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			bean = new BeanRecensioneProdotto();
			
			while(rs.next())
			{
				bean.setProdotto_name(prodotto_name);
				bean.setUtente_username(utente_username);
				bean.setCommento(rs.getString("commento"));
				bean.setNum_stelle(rs.getInt("num_stelle"));
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
	public Collection<BeanRecensioneProdotto> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanRecensioneProdotto> indirizzi = new ArrayList<BeanRecensioneProdotto>();
		String selectSQL = "SELECT * FROM RECENSIONE_PRODOTTO";

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
				BeanRecensioneProdotto bean = new BeanRecensioneProdotto();
				bean.setProdotto_name(rs.getString("prodotto_name"));
				bean.setUtente_username(rs.getString("utente_username"));
				bean.setCommento(rs.getString("commento"));
				bean.setNum_stelle(rs.getInt("num_stelle"));
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
	public void doSave(BeanRecensioneProdotto recensione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO RECENSIONE_PRODOTTO "+ 
				" (prodotto_name, utente_username, commento, num_stelle) " +
				" VALUES(?,?,?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, recensione.getProdotto_name());
			preparedStatement.setString(2, recensione.getUtente_username());
			preparedStatement.setString(3, recensione.getCommento());
			preparedStatement.setInt(4, recensione.getNum_stelle());

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
	public void doUpdate(BeanRecensioneProdotto recensione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE RECENSIONE_PRODOTTO SET " + 
				"commento = ?, num_stelle = ?" +
				"WHERE prodotto_name = ? AND utente_username = ?;";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, recensione.getCommento());
			preparedStatement.setInt(2, recensione.getNum_stelle());
			preparedStatement.setString(3, recensione.getProdotto_name());
			preparedStatement.setString(4, recensione.getUtente_username());

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
	public boolean doDelete(BeanRecensioneProdotto recensione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM RECENSIONE_PRODOTTO WHERE prodotto_name = ? AND utente_username = ?;";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, recensione.getProdotto_name());
			preparedStatement.setString(2, recensione.getUtente_username());
			
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
