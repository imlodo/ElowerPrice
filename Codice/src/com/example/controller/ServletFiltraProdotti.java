package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BeanProdottoFornito;
import Model.CategoriaProdottoModelDM;
import Model.DriverManagerConnectionPool;

/**
 * Servlet implementation class ServletFiltraProdotti
 */
@WebServlet("/ServletFiltraProdotti")
public class ServletFiltraProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletFiltraProdotti() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		
//		String categoriaProdottoDaCercare = (String) request.getAttribute("categoriapassata");
//		String filtro = (String)request.getParameter("filtro");
		HttpSession sessioneUtente = request.getSession(false);
		
		if(sessioneUtente != null)
		{	
			
			String categoriaProdottoDaCercare = (String) sessioneUtente.getAttribute("categoriapassata");
			String filtro = (String)request.getParameter("filtro");
			
			ServletContext ctx = getServletContext();
			DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
			CategoriaProdottoModelDM interfaceDB = new CategoriaProdottoModelDM(driver);
			
			try
			{
				//String filtro = (String)request.getAttribute("filtro");
				
				System.out.println("//////////////////////////////77 "+categoriaProdottoDaCercare+"  filtro : "+filtro);
							
				
				//ArrayList<BeanProdottoFornito> prodotti = (ArrayList<BeanProdottoFornito>) interfaceDB.doRetrieveAll(categoriaProdottoDaCercare);
				//ArrayList<BeanProdottoFornito> prodottiPercategoria = new ArrayList<BeanProdottoFornito>();
				
				ArrayList<BeanProdottoFornito> prodotti = (ArrayList<BeanProdottoFornito>)sessioneUtente.getAttribute("prodottiPerCategoria");
				
				if(prodotti == null)
					System.out.println("/////////////////////////ARRAYLIST == NULL" );
				for(int i = 0 ; i < prodotti.size();i++)
				{
					System.out.println(prodotti.get(i).getProductName());
				}
				Collections.sort(prodotti, BeanProdottoFornito.Comparators.piuCercati);
				
				request.setAttribute("prodottiPerCategoria", prodotti );
				String url = "PaginaDiRicercaPerCategoriaProdotti.jsp";
				url = response.encodeRedirectURL(url);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				
			} 
			catch (Exception e)
			{
				request.setAttribute("errori", e.getMessage());
				String url = "PaginaDiRicercaPerCategoriaProdotti.jsp";
				url = response.encodeRedirectURL(url);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
			
		}
			
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
