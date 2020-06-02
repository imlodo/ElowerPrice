package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class StoreModelDM implements ItemModel<BeanStore, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public StoreModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		//System.out.println("DriverManager Store Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanStore doRetrieveByKey(String nome_store) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL =
						"SELECT * "  
						+"FROM STORE " 
						+"WHERE nome_store = ? ;";
		BeanStore bean = null;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, nome_store);

			//System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				bean = new BeanStore();
				bean.setNome_store(rs.getString("nome_store"));
				bean.setP_iva(rs.getString("p_iva"));
				bean.setN_visite(rs.getInt("num_visite"));
				bean.setImageBytes(rs.getBytes("logo_Store"));
				
				//Ricavo il CAP
				String selectSQL2 = "SELECT * FROM INDIRIZZO_STORE WHERE nome_store_r = ?;";
				PreparedStatement preparedStatement2 = connection.prepareStatement(selectSQL2);
				preparedStatement2.setString(1, bean.getNome_store());
				ResultSet rs2 = preparedStatement2.executeQuery();
				while(rs2.next())
				{
					IndirizzoStoreModelDM managerIndirizzo = new IndirizzoStoreModelDM(dmcp);
					String storeAndCap = bean.getNome_store() +"|"+ rs2.getString("cap_store");
					bean.setIndirizzo_store(managerIndirizzo.doRetrieveByKey(storeAndCap));
				}
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
	public Collection<BeanStore> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanStore> stores = new ArrayList<BeanStore>();
		String selectSQL = "SELECT * FROM STORE";
		String selectSQL2 = "SELECT * FROM INDIRIZZO_STORE WHERE nome_store_r = ?;";
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
				BeanStore bean = new BeanStore();

				bean.setNome_store(rs.getString("nome_store"));
				bean.setP_iva(rs.getString("p_iva"));
				bean.setN_visite(rs.getInt("num_visite"));
				bean.setImageBytes(rs.getBytes("logo_Store"));
				
				//Ricavo il CAP
				PreparedStatement preparedStatement2 = connection.prepareStatement(selectSQL2);
				preparedStatement2.setString(1, bean.getNome_store());
				ResultSet rs2 = preparedStatement2.executeQuery();
				while(rs2.next())
				{
					IndirizzoStoreModelDM managerIndirizzo = new IndirizzoStoreModelDM(dmcp);
					String storeAndCap = bean.getNome_store() +"|"+ rs2.getString("cap_store");
					bean.setIndirizzo_store(managerIndirizzo.doRetrieveByKey(storeAndCap));
				}
				stores.add(bean);
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

		return stores;
	}

	@Override
	public void doSave(BeanStore store) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO STORE " + 
				" (nome_store, p_iva, num_visite, logo_Store) " +
				" VALUES(?,?,?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, store.getNome_store());
			preparedStatement.setString(2, store.getP_iva());
			preparedStatement.setInt(3, store.getN_visite());
			preparedStatement.setBytes(4, store.getImageBytes());

			//System.out.println("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			//Solo in questo momento la modifica viene resa visibile a tutti
			connection.commit();
			IndirizzoStoreModelDM manager2 = new IndirizzoStoreModelDM(dmcp);
			manager2.doSave(store.getIndirizzo_store());
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
	public void doUpdate(BeanStore store) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE STORE SET " + 
				" p_iva = ?, num_visite = ?, logo_Store = ? " +
				" WHERE nome_store = ?;";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, store.getP_iva());
			preparedStatement.setInt(2, store.getN_visite());
			preparedStatement.setBytes(3, store.getImageBytes());
			preparedStatement.setString(4, store.getNome_store());
			
			IndirizzoStoreModelDM manager2 = new IndirizzoStoreModelDM(dmcp);
			manager2.doUpdate(store.getIndirizzo_store());

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
	public boolean doDelete(BeanStore store) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM STORE WHERE nome_store = ?";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, store.getNome_store());
			
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
