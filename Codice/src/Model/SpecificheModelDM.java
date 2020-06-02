package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class SpecificheModelDM implements ItemModel<BeanSpecificaTecnica, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public SpecificheModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		//System.out.println("DriverManager User Model creation....");
	}

	@Override
	public BeanSpecificaTecnica doRetrieveByKey(String nome_specifica) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM SPECIFICA_TECNICA WHERE nome_specifica = ?";
		BeanSpecificaTecnica bean = null;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(query);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, nome_specifica);

			//System.out.println("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				bean = new BeanSpecificaTecnica();
				bean.setNomeSpecifica(rs.getString("nome_specifica"));
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
	public Collection<BeanSpecificaTecnica> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//		Collection<BeanProdottoFornito> prodotti = new LinkedList<BeanProdottoFornito>();
		ArrayList<BeanSpecificaTecnica> specifiche = new ArrayList<BeanSpecificaTecnica>();
		String selectSQL = "SELECT * FROM SPECIFICA_TECNICA";

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
				BeanSpecificaTecnica bean = new BeanSpecificaTecnica();
				bean.setNomeSpecifica(rs.getString("nome_specifica"));
				specifiche.add(bean);
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

		return specifiche;
	}

	@Override
	public void doSave(BeanSpecificaTecnica specifica) throws SQLException
	{
		
	}

	@Override
	public void doUpdate(BeanSpecificaTecnica specifica) throws SQLException
	{
		
	}

	@Override
	public boolean doDelete(BeanSpecificaTecnica specifica) throws SQLException
	{
		return false;
	}
}
