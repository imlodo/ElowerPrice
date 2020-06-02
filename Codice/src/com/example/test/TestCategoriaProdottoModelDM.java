package com.example.test;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.BeanCategoria;
import Model.BeanProdottoFornito;
import Model.BeanSpecificaTecnica;
import Model.CategoriaProdottoModelDM;
import Model.DriverManagerConnectionPool;

public class TestCategoriaProdottoModelDM
{
	public static void main(String Args[])
	{
		DriverManagerConnectionPool driver = new DriverManagerConnectionPool();
		CategoriaProdottoModelDM manager = new CategoriaProdottoModelDM(driver);
		try
		{
			ArrayList<BeanProdottoFornito> prodottiFiltrati = (ArrayList<BeanProdottoFornito>) manager.doRetrieveAll("tablet");
			for(BeanProdottoFornito p : prodottiFiltrati)
			{
				System.out.println("Nome_prodotto :" + p.getProductName());
				System.out.println("Ean : " + p.getCod_ean());
				System.out.println("Numero_ricerche : " + p.getCount_ricerche());
				System.out.println("Disponibilità : " + p.isDisponibile());
				System.out.println("Descrizione : " + p.getDescrizione());
				System.out.println("Name Store: " + p.getNameStore());
				System.out.println("Opzione D'Acquisto : " + p.getOpzioneDAcquisto());
				System.out.println("Costi di spedizione: " + p.getCosti_spedizione());
				System.out.println("Link offerta : " + p.getLink_prodotto_store());				
				System.out.println(p.getPrezzoAttuale());
				System.out.println(p.getPrezzoIeri());
				System.out.println(p.getPrezzoInizioGiorno());
				System.out.println(p.getPrezzoScorsoMese());
				System.out.println(p.getQuantity());

				for(BeanCategoria d : p.getCategorie())
					System.out.println(d.getNomeCategoria());

				for(BeanSpecificaTecnica e : p.getSpecifiche())
				{
					if(e != null);
					{
						System.out.println(e.getNomeSpecifica() + ": " + e.getDescrizioneSpecifica() );
					}
				}
				System.out.println("--------------------------------------------");

			}
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
}
