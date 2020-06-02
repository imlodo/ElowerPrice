package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.BeanProdottoFornito.opzioniAcquisto;

public class ProductInternoModelDM implements ItemModel<BeanProdottoInterno, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public ProductInternoModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Interno Model creation....");
	}

	@Override
	public BeanProdottoInterno doRetrieveByKey(String prod_name) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL =
				"SELECT * "  
						+"FROM PRODOTTO_INTERNO " 
						+"WHERE prodotto_name = ? ";
		
		BeanProdottoInterno bean = null;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, prod_name);
			//System.out.println("doRetrieveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				bean = new BeanProdottoInterno();
				bean.setProductName(rs.getString("prodotto_name"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setQuantity(rs.getInt("quantity"));
				bean.setAvailability(rs.getBoolean("availability"));
				bean.setDescr(rs.getString("descr"));
				bean.setOpzione_acquisto(opzioniAcquisto.valueOf(rs.getString("opzione_acquisto")));
				bean.setCod_ean(rs.getString("cod_ean"));
				
				String query2 = "SELECT * FROM PRODOTTO WHERE prod_name = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
				preparedStatement2.setString(1, bean.getProductName());
				ResultSet rs2 = preparedStatement2.executeQuery();

				while(rs2.next())
				{
					bean.setCount_ricerche(rs2.getInt("num_ricerche"));
					bean.setImageBytes(rs2.getBytes("img_prodotto"));
				}

				String query3 = "SELECT * FROM APPARTIENE_A WHERE prodotto_name = ?";
				preparedStatement2 = connection.prepareStatement(query3);
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
				rs2 = preparedStatement2.executeQuery();
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
	public Collection<BeanProdottoInterno> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<BeanProdottoInterno> prodotti = new ArrayList<BeanProdottoInterno>();
		String selectSQL = "SELECT * FROM PRODOTTO_INTERNO";

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
				BeanProdottoInterno bean = new BeanProdottoInterno();
				bean.setProductName(rs.getString("prodotto_name"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setQuantity(rs.getInt("quantity"));
				bean.setAvailability(rs.getBoolean("availability"));
				bean.setDescr(rs.getString("descr"));
				bean.setOpzione_acquisto(opzioniAcquisto.valueOf(rs.getString("opzione_acquisto")));
				bean.setCod_ean(rs.getString("cod_ean"));
				
				String query2 = "SELECT * FROM PRODOTTO WHERE prod_name = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
				preparedStatement2.setString(1, bean.getProductName());
				ResultSet rs2 = preparedStatement2.executeQuery();

				while(rs2.next())
				{
					
					bean.setCount_ricerche(rs2.getInt("num_ricerche"));
					byte[] image = rs2.getBytes("img_prodotto");
					bean.setImageBytes(image);
				}


				String query3 = "SELECT * FROM APPARTIENE_A WHERE prodotto_name = ?";
				preparedStatement2 = connection.prepareStatement(query3);
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
				rs2 = preparedStatement2.executeQuery();
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
	public void doSave(BeanProdottoInterno product) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try
		{
			connection = dmcp.getConnection();

			String insertSQL = 
					"INSERT INTO PRODOTTO_INTERNO " + 
							" ("
							  + "prodotto_name, "
							  + "prezzo, "
							  + "quantity, "
							  + "availability, "
							  + "descr, "
							  + "opzione_acquisto, "
							  + "cod_ean "
							  + ") " +
							" VALUES(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setDouble(2, product.getPrezzo());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setBoolean(4, product.isAvailability());
			preparedStatement.setString(5, product.getDescr());
			preparedStatement.setString(6, product.getOpzione_acquisto().name());
			preparedStatement.setString(7, product.getCod_ean());
			preparedStatement.executeUpdate();
			
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
	public void doUpdate(BeanProdottoInterno product) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doDelete(BeanProdottoInterno product) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

}
