package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategorieModelDM implements ItemModel<BeanCategoria, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public CategorieModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		System.out.println("DriverManager Categoria Model creation....");
	}

	@Override
	public BeanCategoria doRetrieveByKey(String categoria_name) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM CATEGORIA WHERE nome_categoria = ?";
		BeanCategoria bean = null;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(query);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, categoria_name);

			System.out.println("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				bean = new BeanCategoria();
				bean.setNomeCategoria(rs.getString("nome_categoria"));
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
	public Collection<BeanCategoria> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//		Collection<BeanProdottoFornito> prodotti = new LinkedList<BeanProdottoFornito>();
		ArrayList<BeanCategoria> categorie = new ArrayList<BeanCategoria>();
		String selectSQL = "SELECT * FROM CATEGORIA";

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
				BeanCategoria bean = new BeanCategoria();
				bean.setNomeCategoria(rs.getString("nome_categoria"));
				categorie.add(bean);
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

		return categorie;
	}

	@Override
	public void doSave(BeanCategoria categoria) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO CATEGORIA " + 
				" (nome_categoria) " +
				" VALUES(?)";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, categoria.getNomeCategoria());
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
	public void doUpdate(BeanCategoria categoria) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE CATEGORIA SET " + 
				" nome_categoria = ?," +
				" WHERE nome_categoria = ?";

		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, categoria.getNomeCategoria());

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
	public boolean doDelete(BeanCategoria categoria) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM CATEGORIA WHERE nome_categoria = ?";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, categoria.getNomeCategoria());

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
