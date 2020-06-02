package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class IndirizzoUtenteModelDM implements ItemModel<BeanIndirizzoUtente, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public IndirizzoUtenteModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		System.out.println("DriverManager IndirizzoStore Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanIndirizzoUtente doRetrieveByKey(String cap) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL =
						"SELECT * "  
						+"FROM INDIRIZZO_UTENTE " 
						+"WHERE cap_utente = ?;";
		BeanIndirizzoUtente bean;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, cap);

			//System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			bean = new BeanIndirizzoUtente();
			
			while(rs.next())
			{
				bean.setCap_utente(cap);
				bean.setNumero_civico_utente(rs.getInt("numero_civico_utente"));
				bean.setVia_utente(rs.getString("via_utente"));
			}
			CityModelDM managerCity = new CityModelDM(dmcp);
			BeanCity city = managerCity.doRetrieveByKey(cap);
			bean.setCity(city);
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
	public Collection<BeanIndirizzoUtente> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanIndirizzoUtente> indirizzi = new ArrayList<BeanIndirizzoUtente>();
		String selectSQL = "SELECT * FROM INIDRIZZO_UTENTE";

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
				BeanIndirizzoUtente bean = new BeanIndirizzoUtente();
				bean.setCap_utente(rs.getString("cap_utente"));
				bean.setNumero_civico_utente(rs.getInt("numero_civico_utente"));
				bean.setVia_utente(rs.getString("via_utente"));
				CityModelDM managerCity = new CityModelDM(dmcp);
				BeanCity city = managerCity.doRetrieveByKey(rs.getString("cap_store"));
				bean.setCity(city);
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
	public void doSave(BeanIndirizzoUtente indirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO INDIRIZZO_UTENTE "+ 
				" (cap_utente, via_utente, numero_civico_utente) " +
				" VALUES(?,?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, indirizzo.getCity().getCap());
			preparedStatement.setString(2, indirizzo.getVia_utente());
			preparedStatement.setInt(3, indirizzo.getNumero_civico_utente());

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
	public void doUpdate(BeanIndirizzoUtente indirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE INDIRIZZO_UTENTE SET " + 
				"via_utente = ?, numero_civico_utente = ?" +
				" WHERE cap_utente = ?";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, indirizzo.getVia_utente());
			preparedStatement.setInt(2, indirizzo.getNumero_civico_utente());
			preparedStatement.setString(3, indirizzo.getCity().getCap());

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
	public boolean doDelete(BeanIndirizzoUtente indirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM INDIRIZZO_STORE WHERE cap_utente = ? ";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, indirizzo.getCity().getCap());
			
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
