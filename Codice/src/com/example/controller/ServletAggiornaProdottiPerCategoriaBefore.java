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
import Model.CategoriaProdottoModelDM;
import Model.DriverManagerConnectionPool;


@WebServlet("/ServletAggiornaProdottiPerCategoriaBefore")
public class ServletAggiornaProdottiPerCategoriaBefore extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public ServletAggiornaProdottiPerCategoriaBefore() 
    {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String n_el_da_mostare = (String)request.getParameter("NelementiDaMostr");
		int nEl = Integer.parseInt(n_el_da_mostare);
		String posCorr = request.getParameter("posCorr");
		int intposCorr = Integer.parseInt(posCorr);
				
		ArrayList<BeanProdottoFornito> listaProdottiPerCategoria = null;
		
		String categoria = "";
		categoria = request.getParameter("categoria");
			
		int sizeList = 0; 
		
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
		CategoriaProdottoModelDM interfaceDB = new CategoriaProdottoModelDM(driver);
		try 
		{
			listaProdottiPerCategoria = (ArrayList<BeanProdottoFornito>) interfaceDB.doRetrieveAll(categoria);
			sizeList = listaProdottiPerCategoria.size();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(intposCorr < 0 )
		{
						
			String url = "PaginaDiRicercaPerCategoriaProdotti.jsp";
			url = response.encodeRedirectURL(url);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		}
		
		if(listaProdottiPerCategoria == null)
			System.out.println("LISTA NULL");
				
		ArrayList<BeanProdottoFornito> listaProdottiInterniNext = new ArrayList<BeanProdottoFornito>(); 
		
		
		int i = intposCorr - nEl;
		int j = 0;
		
		for(; (j < nEl) && (i < sizeList) ; i++ , j++)
		{
			BeanProdottoFornito p =	listaProdottiPerCategoria.get(i);
			listaProdottiInterniNext.add( p );
		}
		
		request.setAttribute("prodottiPerCategoria", listaProdottiInterniNext);
		request.setAttribute("posCorr", Integer.toString(intposCorr-nEl));
		request.setAttribute("sizeList", Integer.toString(sizeList));
		
		String url = "PaginaDiRicercaPerCategoriaProdotti.jsp";
		url = response.encodeRedirectURL(url);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
