package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BeanProdottoFornito;
import Model.BeanRecensioneProdotto;
import Model.DriverManagerConnectionPool;
import Model.ProductFornitoModelDM;
import Model.RecensioniProdottiModelDM;

/**
 * Servlet implementation class ServletMostraProdotto
 */
@WebServlet("/ServletMostraProdotto")
public class ServletMostraProdotto extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
       
    public ServletMostraProdotto() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String nomeProdotto = request.getParameter("prodNameAndStoreName");
		//System.out.println("nome prodotto passato in cerca : "+nomeProdotto);
		
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
		ProductFornitoModelDM interfaceDB = new ProductFornitoModelDM(driver);
		RecensioniProdottiModelDM recensioni = new RecensioniProdottiModelDM(driver);
		
		try 
		{
			BeanProdottoFornito prodottoTrovato = (BeanProdottoFornito)interfaceDB.doRetrieveByKey(nomeProdotto);
			ArrayList<BeanRecensioneProdotto> recensioniProdotto = (ArrayList<BeanRecensioneProdotto>) recensioni.doRetrieveAll("prodotto_name");
			if(recensioniProdotto != null)
			{
				int index = nomeProdotto.indexOf("|");
				String prod_name = nomeProdotto.substring(0, index);
				recensioniProdotto.removeIf(s -> !s.getProdotto_name().contains(prod_name));
				request.setAttribute("recensioni", recensioniProdotto);
			}
			if(prodottoTrovato!=null)
			{
				request.setAttribute("prodottoTrovato", prodottoTrovato);
				String url = "/PaginaDelProdotto.jsp"; // pagina di redirect
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);

			}
			else
			{
				String url = "/PaginaDelProdotto.jsp"; // pagina di redirect
				request.setAttribute("errore", "1");
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
