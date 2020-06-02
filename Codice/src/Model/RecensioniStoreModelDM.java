package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class RecensioniStoreModelDM implements ItemModel<BeanRecensioneStore, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public RecensioniStoreModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		//System.out.println("DriverManager IndirizzoStore Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanRecensioneStore doRetrieveByKey(String nome_store_and_utente_username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int index = nome_store_and_utente_username.indexOf("|");
		String store_name = nome_store_and_utente_username.substring(0, index);
		String utente_username = nome_store_and_utente_username.substring(index+1);
		String selectSQL =
						"SELECT * "  
						+"FROM RECENSIONE_STORE " 
						+"WHERE nome_store_r = ? AND utente_username = ?;";
		BeanRecensioneStore bean;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, store_name);
			preparedStatement.setString(2, utente_username);

			//System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			bean = new BeanRecensioneStore();
			
			while(rs.next())
			{
				bean.setNome_store_r(store_name);
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
	public Collection<BeanRecensioneStore> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanRecensioneStore> indirizzi = new ArrayList<BeanRecensioneStore>();
		String selectSQL = "SELECT * FROM RECENSIONE_STORE";

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
				BeanRecensioneStore bean = new BeanRecensioneStore();
				bean.setNome_store_r(rs.getString("nome_store_r"));
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
	public void doSave(BeanRecensioneStore recensione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO RECENSIONE_STORE "+ 
				" (nome_store_r, utente_username, commento, num_stelle) " +
				" VALUES(?,?,?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, recensione.getNome_store_r());
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
	public void doUpdate(BeanRecensioneStore recensione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE RECENSIONE_STORE SET " + 
				"commento = ?, num_stelle = ?" +
				"WHERE nome_store_r = ? AND utente_username = ?;";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, recensione.getCommento());
			preparedStatement.setInt(2, recensione.getNum_stelle());
			preparedStatement.setString(3, recensione.getNome_store_r());
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
	public boolean doDelete(BeanRecensioneStore recensione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM RECENSIONE_STORE WHERE nome_store_r = ? AND utente_username = ?;";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, recensione.getNome_store_r());
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
