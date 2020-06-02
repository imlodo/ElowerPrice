package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.BeanProdottoFornito.opzioniAcquisto;

public class CategoriaProdottoModelDM implements ItemModel<BeanProdottoFornito, String>
{
	private DriverManagerConnectionPool dmcp = null;	
	
	public CategoriaProdottoModelDM(DriverManagerConnectionPool dmcp) 
	{	
		this.dmcp = dmcp;
		System.out.println("DriverManager User Model creation....");
	}

	@Override
	public BeanProdottoFornito doRetrieveByKey(String categorie_name) throws SQLException
	{
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//		String query = "SELECT * FROM APPARTIENE_A WHERE c = ?";
//		BeanProdottoFornito bean;
//		try
//		{
//			connection = dmcp.getConnection();
//			preparedStatement = connection.prepareStatement(query);
//			//riempio il parametro selectSQL = ?
//			preparedStatement.setString(1, prod_name);
//
//			System.out.println("doRetrieveAll: " + preparedStatement.toString());
//			ResultSet rs = preparedStatement.executeQuery();
//
//			bean = new BeanProdottoFornito();
//			while(rs.next())
//			{
//				bean.setProductName(rs.getString("prodotto_name"));
//				bean.setNameStrore(rs.getString("nome_store_r"));
//				bean.setPrezzoIeri(rs.getDouble("prezzo_ieri"));
//				bean.setPrezzoScorsoMese(rs.getDouble("prezzo_scorso_mese"));
//				bean.setPrezzoInizioGiorno(rs.getDouble("prezzo_inizio_giorno"));
//				bean.setPrezzoAttuale(rs.getDouble("prezzo_attuale"));
//				bean.setQuantity(rs.getInt("quantity"));
//				bean.setDisponibile((rs.getBoolean("availability")));
//				bean.setDescrizione((rs.getString("descr_fornitore")));
//				bean.setOpzioneDAcquisto(opzioniAcquisto.valueOf(rs.getString("opzione_acquisto")));
//			}
//
//			String query2 = "SELECT * FROM PRODOTTO WHERE prod_name = ?";
//			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
//			preparedStatement2.setString(1, bean.getProductName());
//			ResultSet rs2 = preparedStatement2.executeQuery();
//
//			while(rs2.next())
//			{
//				bean.setCod_ean(rs2.getString("cod_ean"));
//				bean.setCount_ricerche(rs2.getInt("num_ricerche"));
//				bean.setImageBytes(rs2.getBytes("img_prodotto"));
//			}
//		}
//		finally
//		{
//			try
//			{
//				if(preparedStatement != null)
//					preparedStatement.close();
//			}
//			finally
//			{
//				dmcp.releaseConnection(connection);
//			}
//		}
//		return bean;
		return null;
	}

	@Override
	public Collection<BeanProdottoFornito> doRetrieveAll(String nome_categoria) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<String> nomi = new ArrayList<String>();
		String queryAnnidata = 
							  "SELECT prodotto_name "
							+ "FROM APPARTIENE_A "
							+ "WHERE categoria_name = ? " 
							+ "AND prodotto_name = ANY "
							+ "("
							  + "SELECT prodotto_name "
							  + "FROM PRODOTTO_FORNITO "
							  + "WHERE PRODOTTO_FORNITO.prodotto_name = APPARTIENE_A.prodotto_name"
							+ ")";
		
		ArrayList<BeanProdottoFornito> prodotti = new ArrayList<BeanProdottoFornito>();
		
		try
		{
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(queryAnnidata);
			preparedStatement.setString(1, nome_categoria);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				nomi.add(rs.getString("prodotto_name"));
			System.out.println("doRetrieveAll: " + preparedStatement.toString());
			
			for(String s : nomi)
			{
				String selectSQL = "SELECT * FROM PRODOTTO_FORNITO WHERE prodotto_name = ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, s);
				rs = preparedStatement.executeQuery();
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
					BeanCategoria bean2 = new BeanCategoria();
					while(rs3.next())
					{
						bean2.setNomeCategoria(rs3.getString("categoria_name"));
						categorie.add(bean2);
					}	
					bean.setCategorie(categorie);

					String query4 = "SELECT * FROM POSSIEDE WHERE prodotto_name = ?";
					preparedStatement2 = connection.prepareStatement(query4);
					preparedStatement2.setString(1, bean.getProductName());
					rs2 = preparedStatement2.executeQuery();
					ArrayList<BeanSpecificaTecnica> specifiche = new ArrayList<BeanSpecificaTecnica>();
					BeanSpecificaTecnica bean3 = new BeanSpecificaTecnica();
					while(rs2.next())
					{
						bean3.setNomeSpecifica(rs2.getString("specifica_name"));
						bean3.setDescrizioneSpecifica(rs2.getString("desc_specifica"));
						specifiche.add(bean3);
					}	
					bean.setSpecifiche(specifiche);

					prodotti.add(bean);
				}
				System.out.println("doRetrieveAll: " + preparedStatement.toString());
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
	public void doSave(BeanProdottoFornito categoria) throws SQLException
	{
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//
//		String insertSQL = "INSERT INTO PRODOTTO_FORNITO " + 
//				" (prodotto_name, nome_store_r, prezzo_ieri, prezzo_scorso_mese, prezzo_inizio_giorno, prezzo_attuale, quantity, availability, descr_fornitore, opzione_acquisto) " +
//				" VALUES(?,?,?,?,?,?,?,?,?,?)";
//
//		try
//		{
//			connection = dmcp.getConnection();
//			preparedStatement = connection.prepareStatement(insertSQL);
//
//			String insertSQL2 = "INSERT INTO PRODOTTO"+ 
//					" (prod_name, cod_ean, num_ricerche, img_prodotto) " +
//					"VALUES(?,?,?,?)";
//			PreparedStatement preparared2 = connection.prepareStatement(insertSQL2);
//			preparared2.setString(1, prodotto.getProductName());
//			preparared2.setString(2, prodotto.getCod_ean());
//			preparared2.setInt(3, 0);
//			preparared2.setBytes(4, prodotto.getImageBytes());
//			preparared2.executeUpdate();
//
//
//			//Solo in questo momento la modifica viene resa visibile a tutti
//			connection.commit();
//
//			preparedStatement.setString(1, prodotto.getProductName());
//			preparedStatement.setString(2, prodotto.getNameStore());
//			preparedStatement.setDouble(3, prodotto.getPrezzoIeri());
//			preparedStatement.setDouble(4, prodotto.getPrezzoScorsoMese());
//			preparedStatement.setDouble(5, prodotto.getPrezzoInizioGiorno());
//			preparedStatement.setDouble(6, prodotto.getPrezzoAttuale());
//			preparedStatement.setInt(7, prodotto.getQuantity());
//			preparedStatement.setBoolean(8, prodotto.isDisponibile());
//			preparedStatement.setString(9, prodotto.getDescrizione());
//			preparedStatement.setString(10, prodotto.getOpzioneDAcquisto().name());
//
//			//			PreparedStatement preparedStatement2 = null;
//			//				String query2 = "SELECT * FROM APPARTIENE_A WHERE prodotto_name = ?";
//			//				preparedStatement2 = connection.prepareStatement(query2);
//			//				preparedStatement2.setString(1, bean.getProductName());
//			//				ResultSet rs2 = preparedStatement2.executeQuery();
//			//				
//			//				BeanCategoria[] categorie = new BeanCategoria[10];
//			//				BeanCategoria bean2 = new BeanCategoria();
//			//				int k = 0;
//			//				while(rs2.next())
//			//				{
//			//					bean2.setNomeCategoria(rs2.getString("categoria_name"));
//			//					if(k < 10)
//			//					{
//			//						categorie[k] = bean2;
//			//						k++;
//			//					}
//			//				}	
//			//				bean.setCategorie(categorie);
//
//			//				k = 0;
//			//				String query3 = "SELECT * FROM POSSIEDE WHERE prodotto_name = ?";
//			//				preparedStatement2 = connection.prepareStatement(query3);
//			//				preparedStatement2.setString(1, bean.getProductName());
//			//				rs2 = preparedStatement2.executeQuery();
//			//				BeanSpecificaTecnica[] specifiche = new BeanSpecificaTecnica[20];
//			//				BeanSpecificaTecnica bean3 = new BeanSpecificaTecnica();
//			//				while(rs2.next())
//			//				{
//			//					bean3.setNomeSpecifica(rs2.getString("specifica_name"));
//			//					bean3.setDescrizioneSpecifica(rs2.getString("desc_specifica"));
//			//					if(k < 10)
//			//					{
//			//						specifiche[k] = bean3;
//			//						k++;
//			//					}
//			//				}	
//			//				bean.setSpecifiche(specifiche);
//			System.out.println("doSave: " + preparedStatement.toString());
//			preparedStatement.executeUpdate();
//
//
//		}
//		finally
//		{
//			try
//			{
//				if(preparedStatement != null)
//					preparedStatement.close();
//			}
//			finally
//			{
//				dmcp.releaseConnection(connection);
//			}
//		}
	}

	@Override
	public void doUpdate(BeanProdottoFornito categoria) throws SQLException
	{
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//		String updateSQL = "UPDATE PRODOTTO_FORNITO SET " + 
//				" nome_store_r = ?, prezzo_ieri = ?, "
//				+ "prezzo_scorso_mese = ?, prezzo_inizio_giorno = ?, prezzo_attuale = ?,"
//				+ " quantity = ?, availability = ?, descr_fornitore = ?, opzione_acquisto = ? " +
//				" WHERE prodotto_name = ?";
//
//		try
//		{
//			connection = dmcp.getConnection();
//			preparedStatement = connection.prepareStatement(updateSQL);
//
//			String insertSQL2 = "UPDATE PRODOTTO SET "+ 
//					"img_prodotto = ? " +
//					"WHERE prod_name = ?";
//			PreparedStatement preparared2 = connection.prepareStatement(insertSQL2);
//			preparared2.setBytes(1, prodotto.getImageBytes());
//			preparared2.setString(2, prodotto.getProductName());
//			preparared2.executeUpdate();
//
//			preparedStatement.setString(1, prodotto.getNameStore());
//			preparedStatement.setDouble(2, prodotto.getPrezzoIeri());
//			preparedStatement.setDouble(3, prodotto.getPrezzoScorsoMese());
//			preparedStatement.setDouble(4, prodotto.getPrezzoInizioGiorno());
//			preparedStatement.setDouble(5, prodotto.getPrezzoAttuale());
//			preparedStatement.setInt(6, prodotto.getQuantity());
//			preparedStatement.setBoolean(7, prodotto.isDisponibile());
//			preparedStatement.setString(8, prodotto.getDescrizione());
//			preparedStatement.setString(9, prodotto.getOpzioneDAcquisto().name());
//			preparedStatement.setString(10, prodotto.getProductName());
//
//			System.out.println("doUpdate: " + preparedStatement.toString());
//			preparedStatement.executeUpdate();
//
//			//Solo in questo momento la modifica viene resa visibile a tutti
//			connection.commit();
//		}
//		finally
//		{
//			try
//			{
//				if(preparedStatement != null)
//					preparedStatement.close();
//			}
//			finally
//			{
//				dmcp.releaseConnection(connection);
//			}
//		}
	}

	@Override
	public boolean doDelete(BeanProdottoFornito categoria) throws SQLException
	{
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		String deleteSQL = "DELETE FROM PRODOTTO_FORNITO WHERE prodotto_name = ?";
//		int result = 0;
//		try
//		{
//			connection = dmcp.getConnection();
//			preparedStatement = connection.prepareStatement(deleteSQL);
//			preparedStatement.setString(1, product.getProductName());
//
//			System.out.println("doUpdate: " + preparedStatement.toString());
//			result = preparedStatement.executeUpdate();
//		}
//		finally
//		{
//			try
//			{
//				if(preparedStatement != null)
//					preparedStatement.close();
//			}
//			finally
//			{
//				dmcp.releaseConnection(connection);
//			}
//		}
//		return (result == 0 ? false : true);
		return false;
	}
}
