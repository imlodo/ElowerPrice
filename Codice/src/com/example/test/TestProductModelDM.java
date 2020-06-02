package com.example.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import Model.BeanCategoria;
import Model.BeanProdottoFornito;
import Model.BeanProdottoFornito.opzioniAcquisto;
import Model.BeanSpecificaTecnica;
import Model.DriverManagerConnectionPool;
import Model.ProductFornitoModelDM;

public class TestProductModelDM
{
	private static byte[] getImages()
	{
		BufferedImage bImage;
		byte image[] = null;
		try
		{
			//mia path
//			String path = "C:\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PROGETTO_TSW_UPDATE\\immagini\\notImage.png";
			String path = "/home/black/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PROGETTO_TSW_UPDATE/immagini/notImage.png";
			//Per mettere la tua vai su form1.html e invia il form su eclipse ti verr� stamapata la path nella console di eclipse, commenti la mia e metti la tua. Cosi trovi le immagini nel database.
			bImage = ImageIO.read(new File(path));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "png", bos );
			image = bos.toByteArray();
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
			System.err.println(e.getCause().getMessage());
		}
		return image;
	}

	public static void main(String[] args)
	{
		DriverManagerConnectionPool driver = new DriverManagerConnectionPool();
		ProductFornitoModelDM manager = new ProductFornitoModelDM(driver);

//		/* Test DoUpdate */
//		BeanProdottoFornito nuovo = new BeanProdottoFornito();
//		ArrayList<BeanCategoria> categorie = new ArrayList<BeanCategoria>();
//		BeanCategoria categoria = new BeanCategoria();
//		categoria.setNomeCategoria("tablet");
//		BeanCategoria categoria2 = new BeanCategoria();
//		categoria2.setNomeCategoria("smartphone");
//		categorie.add(categoria);
//		categorie.add(categoria2);
//		ArrayList<BeanSpecificaTecnica> specifiche = new ArrayList<BeanSpecificaTecnica>();
//		BeanSpecificaTecnica specifica1 = new BeanSpecificaTecnica();
//		specifica1.setNomeSpecifica("peso");
//		specifica1.setDescrizioneSpecifica("400 g");
//		BeanSpecificaTecnica specifica2 = new BeanSpecificaTecnica();
//		specifica2.setNomeSpecifica("dimensioni");
//		specifica2.setDescrizioneSpecifica("10x15x20");
//		specifiche.add(specifica1);
//		specifiche.add(specifica2);
//
//		nuovo.setProductName("Tablet Fire HD 8");
//		nuovo.setCod_ean("7895437878332");
//		nuovo.setCount_ricerche(0);
//		nuovo.setImageBytes(getImages());
//		nuovo.setNameStore("Expert");
//		nuovo.setPrezzoIeri(159.99);
//		nuovo.setPrezzoScorsoMese(70.99);
//		nuovo.setPrezzoInizioGiorno(70.99);
//		nuovo.setPrezzoAttuale(50.99);
//		nuovo.setQuantity(50);
//		nuovo.setDisponibile(true);
//		nuovo.setDescrizione("Tablet creato da Antonello Bello che diventa uno smartphone");
//		nuovo.setOpzioneDAcquisto(opzioniAcquisto.RITIRO_IN_SEDE);
//		nuovo.setCategorie(categorie);
//		nuovo.setSpecifiche(specifiche);
//		nuovo.setCosti_spedizione(30.59);
//		nuovo.setLink_prodotto_store("https://google.com/");
//		try
//		{
//			manager.doUpdate(nuovo);
//		} 
//		catch (SQLException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		/* Test DoDelete */
//		BeanProdottoFornito b = new BeanProdottoFornito();
//		b.setNameStore("Trony");
//		b.setProductName("Tablet Antonello Bello Smartphone");
//		try
//		{
//			if(manager.doDelete(b) == true)
//				System.out.println("Cancellato");
//			else
//				System.out.println("Non cancellato");
//		} 
//		catch (SQLException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		/* Test DoSave */
//		BeanProdottoFornito nuovo = new BeanProdottoFornito();
//		ArrayList<BeanCategoria> categorie = new ArrayList<BeanCategoria>();
//		BeanCategoria categoria = new BeanCategoria();
//		categoria.setNomeCategoria("tablet");
//		BeanCategoria categoria2 = new BeanCategoria();
//		categoria2.setNomeCategoria("smartphone");
//		categorie.add(categoria);
//		categorie.add(categoria2);
//		ArrayList<BeanSpecificaTecnica> specifiche = new ArrayList<BeanSpecificaTecnica>();
//		BeanSpecificaTecnica specifica1 = new BeanSpecificaTecnica();
//		specifica1.setNomeSpecifica("peso");
//		specifica1.setDescrizioneSpecifica("400 g");
//		BeanSpecificaTecnica specifica2 = new BeanSpecificaTecnica();
//		specifica2.setNomeSpecifica("dimensioni");
//		specifica2.setDescrizioneSpecifica("10x15x20");
//		specifiche.add(specifica1);
//		specifiche.add(specifica2);
//
//		nuovo.setProductName("Tablet Antonello Bello Smartphone");
//		nuovo.setCod_ean("7895437878332");
//		nuovo.setCount_ricerche(0);
//		nuovo.setImageBytes(getImages());
//		nuovo.setNameStore("Trony");
//		nuovo.setPrezzoIeri(100.99);
//		nuovo.setPrezzoScorsoMese(70.99);
//		nuovo.setPrezzoInizioGiorno(70.99);
//		nuovo.setPrezzoAttuale(50.99);
//		nuovo.setQuantity(50);
//		nuovo.setDisponibile(true);
//		nuovo.setDescrizione("Tablet creato da Antonello Bello che diventa uno smartphone");
//		nuovo.setOpzioneDAcquisto(opzioniAcquisto.RITIRO_IN_SEDE);
//		nuovo.setCategorie(categorie);
//		nuovo.setSpecifiche(specifiche);
//		nuovo.setCosti_spedizione(30.99);
//		nuovo.setLink_prodotto_store("http://antonellobello.com/");
//		try
//		{
//			manager.doSave(nuovo);
//		} 
//		catch (SQLException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/* Test DoRetriveByKey */
//				BeanProdottoFornito p;
//				try
//				{
//					p = manager.doRetrieveByKey("Adidas Superstar 80s W Scarpe da Donna. Sneakers|Trony");
//					
//					System.out.println(p.getProductName());
//					System.out.println(p.getCod_ean());
//					System.out.println(p.getCount_ricerche());
//					System.out.println(p.isDisponibile());
//					System.out.println(p.getDescrizione());
//					System.out.println(p.getNameStore());
//					System.out.println(p.getOpzioneDAcquisto());
//					System.out.println(p.getPrezzoAttuale());
//					System.out.println(p.getPrezzoIeri());
//					System.out.println(p.getPrezzoInizioGiorno());
//					System.out.println(p.getPrezzoScorsoMese());
//					System.out.println(p.getQuantity());
//					System.out.println(p.getCosti_spedizione());
//					System.out.println(p.getLink_prodotto_store());
//		
//					for(BeanCategoria d : p.getCategorie())
//						System.out.println(d.getNomeCategoria());
//		
//					for(BeanSpecificaTecnica st : p.getSpecifiche())
//						System.out.println(st.getNomeSpecifica() + ": " + st.getDescrizioneSpecifica());
//					
//					System.out.println("--------------------------------------------");
//		
//				} 
//				catch (SQLException e)
//				{
//					e.printStackTrace();
//				}

		/* Test doRetriveByAll */
				ArrayList<BeanProdottoFornito> collection;
				
					try
					{
						collection = (ArrayList<BeanProdottoFornito>) manager.doRetrieveAll("nome_store_r");
//						collection = (ArrayList<BeanProdottoFornito>) manager.doRetrieveAll("prezzo_attuale");
		//				LinkedList<BeanProdottoFornito> l = (LinkedList<BeanProdottoFornito>) manager.doRetrieveAll("prodotto_name");
//						Object a[];
//						l.toArray(a);
//						ArrayList<BeanProdottoFornito> al ;
		//				al.sort(c);
		//				Arrays.asList(a);
		
						Collections.sort( collection , BeanProdottoFornito.Comparators.prezzoAttuale);
						
		//				BufferedImage bImage;
		//				byte image[] = null;
		//				try
		//				{
		//					//Questa � la mia Antonello
		////					String path = "C:\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PROGETTO_TSW_UPDATE\\immagini\\notImage.png";
		//					String path = "/home/black/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PROGETTO_TSW_UPDATE/immagini/notImage.png";
		//					//Per mettere la tua vai su form1.html e invia il form su eclipse ti verr� stamapata la path nella console di eclipse, commenti la mia e metti la tua. Cosi trovi le immagini nel database.
		//					bImage = ImageIO.read(new File(path));
		//					ByteArrayOutputStream bos = new ByteArrayOutputStream();
		//					ImageIO.write(bImage, "png", bos );
		//					image = bos.toByteArray();
		//				} 
		//				catch (IOException e)
		//				{
		//					System.err.println(e.getMessage());
		//					System.err.println(e.getCause().getMessage());
		//				}
						for(BeanProdottoFornito p: collection)
						{
							p.setImageBytes(getImages());
							manager.doUpdate(p);
						}
								
						
						for(int i = 0; i < collection.size() ; i++ )
						{
							System.out.println(collection.get(i).getProductName());
		//					System.out.println(p.getCod_ean());
		//					System.out.println(p.getCount_ricerche());
		//					System.out.println(p.isDisponibile());
		//					System.out.println(p.getDescrizione());
		//					System.out.println(p.getNameStore());
		//					System.out.println(p.getOpzioneDAcquisto());
							System.out.println(collection.get(i).getPrezzoAttuale());
		//					System.out.println(p.getPrezzoIeri());
		//					System.out.println(p.getPrezzoInizioGiorno());
		//					System.out.println(p.getPrezzoScorsoMese());
		//					System.out.println(p.getQuantity());
							
							for(BeanCategoria d : collection.get(i).getCategorie())
								System.out.println(d.getNomeCategoria());
				
							if((collection.get(i).getSpecifiche().size()) > 0)
							{
								String specifica = collection.get(i).getSpecifiche().get(0).getNomeSpecifica();
								if(specifica != null);
								{
									System.out.println(specifica + ": " + collection.get(i).getSpecifiche().get(0).getDescrizioneSpecifica() );
								}
							}
							System.out.println("--------------------------------------------");
						}
		//				for(BeanProdottoFornito p : collection)
		//				{
		//					System.out.println(p.getProductName());
		//					System.out.println(p.getCod_ean());
		//					System.out.println(p.getCount_ricerche());
		//					System.out.println(p.isDisponibile());
		//					System.out.println(p.getDescrizione());
		//					System.out.println(p.getNameStore());
		//					System.out.println(p.getOpzioneDAcquisto());
		//					System.out.println(p.getPrezzoAttuale());
		//					System.out.println(p.getPrezzoIeri());
		//					System.out.println(p.getPrezzoInizioGiorno());
		//					System.out.println(p.getPrezzoScorsoMese());
		//					System.out.println(p.getQuantity());
		//					System.out.println("--------------------------------------------");
		//				}
						
						
		//				BeanProdottoFornito prod = new BeanProdottoFornito();
		//				prod.setProductName("AntonelloBello");
		//				prod.setQuantity(5);
		//				prod.setPrezzoScorsoMese(12.50);
		//				prod.setPrezzoInizioGiorno(13.50);
		//				prod.setPrezzoIeri(10.99);
		//				prod.setPrezzoAttuale(13.99);
		//				prod.setOpzioneDAcquisto(opzioniAcquisto.CONSEGNA_DOMICILIO);
		//				prod.setNameStrore("Trony");
		//				prod.setImageBytes(null);
		//				prod.setDisponibile(false);
		//				prod.setDescrizione("Antonello gay");
		//				prod.setCount_ricerche(0);
		//				prod.setCod_ean("2398200420");
		//				prod.setQuantity(15);
		//				manager.doSave(prod);
						
					} 
					catch (SQLException e)
					{
						e.printStackTrace();
					}
			


	}

}




