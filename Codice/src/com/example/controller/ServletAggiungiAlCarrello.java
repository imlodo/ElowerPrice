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

@WebServlet("/ServletAggiungiAlCarrello")
public class ServletAggiungiAlCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAggiungiAlCarrello() {
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
			
			String nomeProdottoDaAggiungereAlCarrello = (String)request.getParameter("prodottoDaAggiungereAlCarrello");
			if(nomeProdottoDaAggiungereAlCarrello == null)
			{
				
				//errore prodotto non ci sta nel DB
				String url = "PaginaDelCarrello.jsp"; // url della jsp
				request.setAttribute("errore", "1");
				
				url = response.encodeRedirectURL(url);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
			}
				
			BeanProdottoInterno prodottoDaAggiungereAlCarrello = null;
			try 
			{
				prodottoDaAggiungereAlCarrello = (BeanProdottoInterno) interfaceDB.doRetrieveByKey(nomeProdottoDaAggiungereAlCarrello);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//BeanProdottoInterno prodottoDaAggiungereAlCarrello = (BeanProdottoInterno)sessioneCorrente.getAttribute("prodottoDaAggiungereAlCarrello");
			ArrayList<BeanProdottoInterno> listaProdottiNelCarrello = (ArrayList<BeanProdottoInterno>) sessioneCorrente.getAttribute("listaProdottiNelCarrello");
			if(listaProdottiNelCarrello == null)
			{
				listaProdottiNelCarrello = new ArrayList<BeanProdottoInterno>();
				System.out.println("HO CREATO IL CARRELLO");
			}
			listaProdottiNelCarrello.add(prodottoDaAggiungereAlCarrello);
			sessioneCorrente.setAttribute("listaProdottiNelCarrello", listaProdottiNelCarrello);
			request.setAttribute("prodottoDaAggiungereAlCarrello", prodottoDaAggiungereAlCarrello);

			String url = "PaginaDelCarrello.jsp"; // url della jsp
			url = response.encodeRedirectURL(url);
			response.sendRedirect(url);
		}
		else
		{
			//errore sessione pag
			String url = "/PaginaDelCarrello.jsp"; // url della jsp
			request.setAttribute("errore", "1");
			url = response.encodeRedirectURL(url);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
