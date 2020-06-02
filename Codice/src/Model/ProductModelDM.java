package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ProductModelDM implements ItemModel<Prodotto, String>
{
	private DriverManagerConnectionPool dmcp = null;
	
	public ProductModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		//System.out.println("DriverManager Product Interno Model creation....");
	}
	
	@Override
	public Prodotto doRetrieveByKey(String item_value) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL =
				"SELECT * FROM PRODOTTO WHERE prod_name = ? ";
		
		Prodotto bean = null;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, item_value);
			//System.out.println("doRetrieveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				bean = new Prodotto();
				bean.setProductName(rs.getString("prod_name"));
				bean.setCount_ricerche(rs.getInt("num_ricerche"));
				bean.setImageBytes(rs.getBytes("img_prodotto"));

				String query3 = "SELECT * FROM APPARTIENE_A WHERE prodotto_name = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
				preparedStatement2.setString(1, bean.getProductName());
				ResultSet rs3 = preparedStatement2.executeQuery();
				ArrayList<BeanCategoria> categorie = new ArrayList<BeanCategoria>();
				while(rs3.next())
				{
					BeanCategoria bean2 = new BeanCategoria();
					bean2.setNomeCategoria(rs3.getString("categoria_name"));
					categorie.add(bean2);
				}	
				bean.setCategorie(categorie);

				String query4 = "SELECT * FROM POSSIEDE WHERE prodotto_name = ?";
				preparedStatement2 = connection.prepareStatement(query4);
				preparedStatement2.setString(1, bean.getProductName());
				ResultSet rs2 = preparedStatement2.executeQuery();
				ArrayList<BeanSpecificaTecnica> specifiche = new ArrayList<BeanSpecificaTecnica>();
				while(rs2.next())
				{
					BeanSpecificaTecnica bean3 = new BeanSpecificaTecnica();
					bean3.setNomeSpecifica(rs2.getString("specifica_name"));
					bean3.setDescrizioneSpecifica(rs2.getString("desc_specifica"));
					specifiche.add(bean3);
				}	
				bean.setSpecifiche(specifiche);
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
	public Collection<Prodotto> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
		String selectSQL = "SELECT * FROM PRODOTTO";

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
				Prodotto bean = new BeanProdottoInterno();
				bean.setProductName(rs.getString("prod_name"));
				bean.setCount_ricerche(rs.getInt("num_ricerche"));
				bean.setImageBytes(rs.getBytes("img_prodotto"));
				
				String query3 = "SELECT * FROM APPARTIENE_A WHERE prodotto_name = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
				preparedStatement2.setString(1, bean.getProductName());
				ResultSet rs3 = preparedStatement2.executeQuery();

				ArrayList<BeanCategoria> categorie = new ArrayList<BeanCategoria>();
				while(rs3.next())
				{
					BeanCategoria bean2 = new BeanCategoria();
					bean2.setNomeCategoria(rs3.getString("categoria_name"));
					categorie.add(bean2);
				}	
				bean.setCategorie(categorie);

				String query4 = "SELECT * FROM POSSIEDE WHERE prodotto_name = ?";
				preparedStatement2 = connection.prepareStatement(query4);
				preparedStatement2.setString(1, bean.getProductName());
				ResultSet rs2 = preparedStatement2.executeQuery();
				ArrayList<BeanSpecificaTecnica> specifiche = new ArrayList<BeanSpecificaTecnica>();
				while(rs2.next())
				{
					BeanSpecificaTecnica bean3 = new BeanSpecificaTecnica();
					bean3.setNomeSpecifica(rs2.getString("specifica_name"));
					bean3.setDescrizioneSpecifica(rs2.getString("desc_specifica"));
					specifiche.add(bean3);
				}	
				bean.setSpecifiche(specifiche);
				prodotti.add(bean);
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

		return prodotti;
	}

	@Override
	public void doSave(Prodotto product) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try
		{
			connection = dmcp.getConnection();

			String insertSQL = 
					"INSERT INTO PRODOTTO " + 
							" ("
							  + "prod_name, "
							  + "num_ricerche, "
							  + "img_prodotto "
							  + ") " +
							" VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getCount_ricerche());
			preparedStatement.setBytes(3, product.getImageBytes());
			preparedStatement.executeUpdate();
			connection.commit();
			
			ArrayList<BeanCategoria> categorie = product.getCategorie();
			for(BeanCategoria b : categorie)
			{
				String insertRel = "INSERT INTO APPARTIENE_A"
						  +" (prodotto_name, "
						  + "categoria_name) " +
						  " VALUES(?,?)";
				PreparedStatement preparedStatement2 = connection.prepareStatement(insertRel);
				preparedStatement2.setString(1, product.getProductName());
				preparedStatement2.setString(2, b.getNomeCategoria());
				preparedStatement2.executeUpdate();
				connection.commit();
			}
			ArrayList<BeanSpecificaTecnica> specifiche = product.getSpecifiche();
			for(BeanSpecificaTecnica s : specifiche)
			{
				String insertRel2 = "INSERT INTO POSSIEDE"
						  +" (prodotto_name, specifica_name, desc_specifica)"+
						  " VALUES(?,?,?)";
				PreparedStatement preparedStatement3 = connection.prepareStatement(insertRel2);
				preparedStatement3.setString(1, product.getProductName());
				preparedStatement3.setString(2, s.getNomeSpecifica());
				preparedStatement3.setString(3, s.getDescrizioneSpecifica());
				preparedStatement3.executeUpdate();
			}
				
			
			//Solo in questo momento la modifica viene resa visibile a tutti
			connection.commit();
			//System.out.println("doSave: " + preparedStatement.toString());

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
	public void doUpdate(Prodotto product) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doDelete(Prodotto product) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

}
