package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class CityModelDM implements ItemModel<BeanCity, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public CityModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		System.out.println("DriverManager City Model creation....");
	}
	/* Restituisce il BeanUtente se l'username corrisponde, altrimenti restituisce null */
	@Override
	public BeanCity doRetrieveByKey(String cap) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL =
						"SELECT * "  
						+"FROM CITY " 
						+"WHERE cap = ?;";
		BeanCity bean = null;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, cap);

			System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				bean = new BeanCity();
				bean.setCap(rs.getString("cap"));
				bean.setCity(rs.getString("city"));
				bean.setProv(rs.getString("prov"));
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
	public Collection<BeanCity> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BeanCity> cityes = new ArrayList<BeanCity>();
		String selectSQL = "SELECT * FROM CITY";

		if(order != null && !order.equals(""))
		{
			selectSQL += " ORDER BY " + order;
		}

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("doRetrieveAll: " + preparedStatement.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				BeanCity bean = new BeanCity();
				bean.setCap(rs.getString("cap"));
				bean.setCity(rs.getString("city"));
				bean.setProv(rs.getString("prov"));
				cityes.add(bean);
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

		return cityes;
	}

	@Override
	public void doSave(BeanCity city) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertSQL = "INSERT INTO CITY " + 
				" (cap, city, prov) " +
				" VALUES(?,?,?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, city.getCap());
			preparedStatement.setString(2, city.getCity());
			preparedStatement.setString(3, city.getProv());

			System.out.println("doSave: " + preparedStatement.toString());
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
	public void doUpdate(BeanCity city) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE CITY SET " + 
				" city = ?, prov = ?" +
				" WHERE cap = ?";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, city.getCity());
			preparedStatement.setString(2, city.getProv());
			preparedStatement.setString(3, city.getCap());

			System.out.println("doUpdate: " + preparedStatement.toString());
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
	public boolean doDelete(BeanCity city) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM CITY WHERE cap = ?";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, city.getCap());
			
			System.out.println("doUpdate: " + preparedStatement.toString());
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
