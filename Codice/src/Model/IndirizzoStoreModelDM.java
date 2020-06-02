package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class IndirizzoStoreModelDM implements ItemModel<BeanIndirizzoStore, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public IndirizzoStoreModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		//System.out.println("DriverManager IndirizzoStore Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanIndirizzoStore doRetrieveByKey(String cap_and_store_name) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int index = cap_and_store_name.indexOf("|");
		String store_name = cap_and_store_name.substring(0, index);
		String cap = cap_and_store_name.substring(index+1);
		String selectSQL =
						"SELECT * "  
						+"FROM INDIRIZZO_STORE " 
						+"WHERE cap_store = ? AND nome_store_r = ?;";
		BeanIndirizzoStore bean;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, cap);
			preparedStatement.setString(2, store_name);

			//System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			bean = new BeanIndirizzoStore();
			
			while(rs.next())
			{
				bean.setVia_store(rs.getString("via_store"));
				bean.setNumero_civico_store(rs.getInt("numero_civico_store"));
				bean.setNome_store(rs.getString("nome_store_r"));
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
	public Collection<BeanIndirizzoStore> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanIndirizzoStore> indirizzi = new ArrayList<BeanIndirizzoStore>();
		String selectSQL = "SELECT * FROM INIDRIZZO_STORE";

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
				BeanIndirizzoStore bean = new BeanIndirizzoStore();
				bean.setVia_store(rs.getString("via_store"));
				bean.setNumero_civico_store(rs.getInt("numero_civico_store"));
				bean.setNome_store(rs.getString("nome_store_r"));
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
	public void doSave(BeanIndirizzoStore indirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO INDIRIZZO_STORE "+ 
				" (cap_store, via_store, numero_civico_store, nome_store_r) " +
				" VALUES(?,?,?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, indirizzo.getCity().getCap());
			preparedStatement.setString(2, indirizzo.getVia_store());
			preparedStatement.setInt(3, indirizzo.getNumero_civico_store());
			preparedStatement.setString(4, indirizzo.getNome_store());

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
	public void doUpdate(BeanIndirizzoStore indirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE INDIRIZZO_STORE SET " + 
				"via_store = ?, numero_civico_store = ?" +
				" WHERE cap_store = ? AND nome_store_r = ?";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, indirizzo.getVia_store());
			preparedStatement.setInt(2, indirizzo.getNumero_civico_store());
			preparedStatement.setString(3, indirizzo.getCity().getCap());
			preparedStatement.setString(4, indirizzo.getNome_store());

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
	public boolean doDelete(BeanIndirizzoStore indirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM INDIRIZZO_STORE WHERE cap_store = ? AND nome_store_r = ?";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, indirizzo.getCity().getCap());
			preparedStatement.setString(2, indirizzo.getNome_store());
			
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
