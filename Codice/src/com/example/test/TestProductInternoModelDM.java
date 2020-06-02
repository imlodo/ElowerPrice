package com.example.test;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.BeanProdottoInterno;
import Model.DriverManagerConnectionPool;
import Model.ProductInternoModelDM;

public class TestProductInternoModelDM
{

	public static void main(String[] args)
	{
		DriverManagerConnectionPool driver = new DriverManagerConnectionPool();
		ProductInternoModelDM manager = new ProductInternoModelDM(driver);
		BeanProdottoInterno bean = new BeanProdottoInterno();
		
		/* TestDoRetriveByKey Prodotto Interno 
			try
			{
				bean = manager.doRetrieveByKey("prodottoInterno");
				System.out.println(bean);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		*/
		
		/* Test doRetriveByAll
			try
			{
				ArrayList<BeanProdottoInterno> prodotti = 
					(ArrayList<BeanProdottoInterno>) manager.doRetrieveAll("prodotto_name");
				for(BeanProdottoInterno p : prodotti)
					System.out.println(p);
			} 
			catch (SQLException e)
			{
			
				e.printStackTrace();
			}
		*/

	}

}
