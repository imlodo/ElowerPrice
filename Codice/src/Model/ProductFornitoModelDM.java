package Model;

import java.sql.*;
import java.util.*;

import Model.BeanProdottoFornito.opzioniAcquisto;


public class ProductFornitoModelDM implements ItemModel<BeanProdottoFornito, String>
{
	private DriverManagerConnectionPool dmcp = null;	

	public ProductFornitoModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		//System.out.println("DriverManager Product Fornito Model creation....");
	}

	@Override
	public BeanProdottoFornito doRetrieveByKey(String prod_name_and_store_name) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int index = prod_name_and_store_name.indexOf("|");		
		String prod_name = prod_name_and_store_name.substring(0, index);
		String store_name = prod_name_and_store_name.substring(index+1);
		String selectSQL =
				"SELECT * "  
						+"FROM PRODOTTO_FORNITO " 
						+"WHERE prodotto_name = ? AND nome_store_r = ?";
		BeanProdottoFornito bean = null;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//riempio il parametro selectSQL = ?
			preparedStatement.setString(1, prod_name);
			preparedStatement.setString(2, store_name);
			//System.out.println("doRetrieveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				bean = new BeanProdottoFornito();
				bean.setProductName(rs.getString("prodotto_name"));
				bean.setNameStore(rs.getString("nome_store_r"));
				bean.setPrezzoIeri(rs.getDouble("prezzo_ieri"));
				bean.setPrezzoScorsoMese(rs.getDouble("prezzo_scorso_mese"));
				bean.setPrezzoInizioGiorno(rs.getDouble("prezzo_inizio_giorno"));
				bean.setPrezzoAttuale(rs.getDouble("prezzo_attuale"));
				bean.setQuantity(rs.getInt("quantity"));
				bean.setDisponibile((rs.getBoolean("availability")));
				bean.setDescrizione((rs.getString("descr_fornitore")));
				bean.setOpzioneDAcquisto(opzioniAcquisto.valueOf(rs.getString("opzione_acquisto")));
				bean.setCod_ean(rs.getString("cod_ean"));
				bean.setCosti_spedizione(rs.getDouble("costo_spedizione"));
				bean.setLink_prodotto_store(rs.getString("link_offerta"));
				
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
	public Collection<BeanProdottoFornito> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//		Collection<BeanProdottoFornito> prodotti = new LinkedList<BeanProdottoFornito>();
		ArrayList<BeanProdottoFornito> prodotti = new ArrayList<BeanProdottoFornito>();
		String selectSQL = "SELECT * FROM PRODOTTO_FORNITO";

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
				BeanProdottoFornito bean = new BeanProdottoFornito();
				bean.setProductName(rs.getString("prodotto_name"));
				bean.setNameStore(rs.getString("nome_store_r"));
				bean.setPrezzoIeri(rs.getDouble("prezzo_ieri"));
				bean.setPrezzoScorsoMese(rs.getDouble("prezzo_scorso_mese"));
				bean.setPrezzoInizioGiorno(rs.getDouble("prezzo_inizio_giorno"));
				bean.setPrezzoAttuale(rs.getDouble("prezzo_attuale"));
				bean.setQuantity(rs.getInt("quantity"));
				bean.setDisponibile((rs.getBoolean("availability")));
				bean.setDescrizione((rs.getString("descr_fornitore")));
				bean.setOpzioneDAcquisto(opzioniAcquisto.valueOf(rs.getString("opzione_acquisto")));
				bean.setCod_ean(rs.getString("cod_ean"));
				bean.setCosti_spedizione(rs.getDouble("costo_spedizione"));
				bean.setLink_prodotto_store(rs.getString("link_offerta"));
				
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
	public void doSave(BeanProdottoFornito prodotto) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try
		{
			connection = dmcp.getConnection();

			String insertSQL = 
					"INSERT INTO PRODOTTO_FORNITO " + 
							" ("
							+ "prodotto_name, nome_store_r, prezzo_ieri, "
							+ "prezzo_scorso_mese, prezzo_inizio_giorno, prezzo_attuale, "
							+ "quantity, availability, descr_fornitore, opzione_acquisto, "
							+ "cod_ean, costo_spedizione, link_offerta"
							+ ") " +
							" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prodotto.getProductName());
			preparedStatement.setString(2, prodotto.getNameStore());
			preparedStatement.setDouble(3, prodotto.getPrezzoIeri());
			preparedStatement.setDouble(4, prodotto.getPrezzoScorsoMese());
			preparedStatement.setDouble(5, prodotto.getPrezzoInizioGiorno());
			preparedStatement.setDouble(6, prodotto.getPrezzoAttuale());
			preparedStatement.setInt(7, prodotto.getQuantity());
			preparedStatement.setBoolean(8, prodotto.isDisponibile());
			preparedStatement.setString(9, prodotto.getDescrizione());
			preparedStatement.setString(10, prodotto.getOpzioneDAcquisto().name());
			preparedStatement.setString(11, prodotto.getCod_ean());
			preparedStatement.setDouble(12, prodotto.getCosti_spedizione());
			preparedStatement.setString(13, prodotto.getLink_prodotto_store());
			preparedStatement.executeUpdate();
			//Solo in questo momento la modifica viene resa visibile a tutti
			connection.commit();
			//System.out.println("doSave: " + preparedStatement.toString());

			//			for(BeanCategoria b : prodotto.getCategorie())
			//			{
			//				String query2 = 
			//								"INSERT INTO APPARTIENE_A " + 
			//								" (prodotto_name, categoria_name) " +
			//								" VALUES(?,?)";
			//				preparared2 = connection.prepareStatement(query2);
			//				preparared2.setString(1, prodotto.getProductName());
			//				preparared2.setString(2, b.getNomeCategoria());
			//				preparared2.executeUpdate();
			//			}
			//			
			//			for(BeanSpecificaTecnica st : prodotto.getSpecifiche())
			//			{
			//				String query2 = 
			//								"INSERT INTO POSSIEDE " + 
			//								" (prodotto_name, specifica_name, desc_specifica) " +
			//								" VALUES(?,?,?)";
			//				preparared2 = connection.prepareStatement(query2);
			//				preparared2.setString(1, prodotto.getProductName());
			//				preparared2.setString(2, st.getNomeSpecifica());
			//				preparared2.setString(3, st.getDescrizioneSpecifica());
			//				preparared2.executeUpdate();
			//			}

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
	public void doUpdate(BeanProdottoFornito prodotto) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try
		{
			connection = dmcp.getConnection();

			String insertSQL2 = "UPDATE PRODOTTO SET "+ 
					"img_prodotto = ?" +
					"WHERE prod_name = ?";
			PreparedStatement preparared2 = connection.prepareStatement(insertSQL2);
			preparared2.setBytes(1, prodotto.getImageBytes());
			preparared2.setString(2, prodotto.getProductName());
			preparared2.executeUpdate();

			String updateSQL = "UPDATE PRODOTTO_FORNITO SET " + 
					" prezzo_ieri = ?, "
					+ "prezzo_scorso_mese = ?, prezzo_inizio_giorno = ?, prezzo_attuale = ?,"
					+ " quantity = ?, availability = ?, descr_fornitore = ?, opzione_acquisto = ?, "
					+ "cod_ean = ?, costo_spedizione = ?, link_offerta = ? " +
					" WHERE prodotto_name = ? AND nome_store_r = ?";
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setDouble(1, prodotto.getPrezzoIeri());
			preparedStatement.setDouble(2, prodotto.getPrezzoScorsoMese());
			preparedStatement.setDouble(3, prodotto.getPrezzoInizioGiorno());
			preparedStatement.setDouble(4, prodotto.getPrezzoAttuale());
			preparedStatement.setInt(5, prodotto.getQuantity());
			preparedStatement.setBoolean(6, prodotto.isDisponibile());
			preparedStatement.setString(7, prodotto.getDescrizione());
			preparedStatement.setString(8, prodotto.getOpzioneDAcquisto().name());
			preparedStatement.setString(9, prodotto.getCod_ean());
			preparedStatement.setDouble(10, prodotto.getCosti_spedizione());
			preparedStatement.setString(11, prodotto.getLink_prodotto_store());
			preparedStatement.setString(12, prodotto.getProductName());
			preparedStatement.setString(13, prodotto.getNameStore());
			
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
	public boolean doDelete(BeanProdottoFornito product) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = 
				"DELETE "
						+ "FROM PRODOTTO_FORNITO "
						+ "WHERE prodotto_name = ? AND nome_store_r = ?";
		int result = 0;
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getNameStore());

			//System.out.println("doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
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
		return (result == 0 ? false : true);
	}

}
