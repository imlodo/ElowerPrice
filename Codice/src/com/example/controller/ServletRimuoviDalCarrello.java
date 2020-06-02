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
import javax.servlet.http.HttpSession;

import Model.BeanProdottoInterno;
import Model.DriverManagerConnectionPool;
import Model.ProductInternoModelDM;

/**
 * Servlet implementation class ServletRimuoviDalCarrello
 */
@WebServlet("/ServletRimuoviDalCarrello")
public class ServletRimuoviDalCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletRimuoviDalCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession sessioneCorrente = request.getSession(false);
		
		if(sessioneCorrente!= null)
		{
		
			ServletContext ctx = getServletContext();
			DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
			ProductInternoModelDM interfaceDB = new ProductInternoModelDM(driver);
			
			String nomeProdottoDaEliminare = (String)request.getParameter("prodName");
			
			BeanProdottoInterno prodottoDaEliminare;
			try
			{
				prodottoDaEliminare = (BeanProdottoInterno)interfaceDB.doRetrieveByKey(nomeProdottoDaEliminare);
			}
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ArrayList<BeanProdottoInterno> listaProdottiNelCarrello = (ArrayList<BeanProdottoInterno>) sessioneCorrente.getAttribute("listaProdottiNelCarrello");
			
			for(int i = 0; i < listaProdottiNelCarrello.size();i++)
			{
				String nomeProdottoCorrente = listaProdottiNelCarrello.get(i).getProductName();
				if(nomeProdottoCorrente.equals(nomeProdottoDaEliminare))
				{
					listaProdottiNelCarrello.remove(i);
					break;
				}
			}
			sessioneCorrente.setAttribute("listaProdottiNelCarrello", listaProdottiNelCarrello);
			
			String url = "/PaginaDelCarrello.jsp"; // url della jsp
			//request.setAttribute("errore", "1");
			
			String encodedURL = response.encodeRedirectURL(url);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(encodedURL);
			dispatcher.forward(request, response);
						
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
