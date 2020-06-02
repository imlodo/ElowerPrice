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
import Model.DriverManagerConnectionPool;
import Model.ProductFornitoModelDM;

/**
 * Servlet implementation class ServletCercaProdotto
 */
@WebServlet("/ServletCercaProdotto")
public class ServletCercaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver");
		try
		{
			String prod_name = (String) request.getAttribute("prodottoPerNome");
			if(prod_name == null)
				prod_name = request.getParameter("prodottoPerNome");
			
			ProductFornitoModelDM manager = new ProductFornitoModelDM(driver);
			ArrayList<BeanProdottoFornito> prodotti = (ArrayList<BeanProdottoFornito>) manager.doRetrieveAll("prodotto_name");
			ArrayList<BeanProdottoFornito> prodotti_filtrati = new ArrayList<BeanProdottoFornito>();
			for(BeanProdottoFornito p : prodotti)
			{
				if(p.getProductName().equalsIgnoreCase(prod_name))
					prodotti_filtrati.add(p);
			}
			request.setAttribute("prodottiTrovati", prodotti_filtrati);
		
			String current = (String) request.getAttribute("currentPage");
			if(current == null)
				current = request.getParameter("currentPage");

			if(current == null)
				request.setAttribute("pagina", "1");
			else
				request.setAttribute("pagina", current);
			
			String url = "PaginaDiRicercaProdotti.jsp";
			url = response.encodeRedirectURL(url);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} 
		catch (SQLException e)
		{
			request.setAttribute("errori", e.getMessage());
			String url = "PaginaDiRicercaProdotti.jsp";
			response.encodeRedirectURL(url);
			url = response.encodeRedirectURL(url);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
