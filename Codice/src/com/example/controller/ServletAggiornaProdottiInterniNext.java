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
import Model.BeanProdottoInterno;
import Model.DriverManagerConnectionPool;
import Model.ProductInternoModelDM;


@WebServlet("/ServletAggiornaProdottiInterniNext")
public class ServletAggiornaProdottiInterniNext extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAggiornaProdottiInterniNext() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String n_el_da_mostare = (String)request.getParameter("NelementiDaMostr");
		int nEl = Integer.parseInt(n_el_da_mostare);
		String posCorr = request.getParameter("posCorr");
		int intposCorr = Integer.parseInt(posCorr);
				
		ArrayList<BeanProdottoInterno> listaProdottiInterni = null;
		
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
		ProductInternoModelDM interfaceDB = new ProductInternoModelDM(driver);
		int sizeList = 0; 
		try 
		{
			listaProdottiInterni = (ArrayList<BeanProdottoInterno>) interfaceDB.doRetrieveAll("prodotto_name");
			sizeList = listaProdottiInterni.size();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		if(intposCorr >= sizeList)
		{
			String url = "PaginaDeiProdottiInterni.jsp";
			url = response.encodeRedirectURL(url);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
		if(listaProdottiInterni == null)
			System.out.println("LISTA NULL");
		
		ArrayList<BeanProdottoInterno> listaProdottiInterniNext = new ArrayList<BeanProdottoInterno>(); 
		
		
		int i = intposCorr+nEl;
		int j = 0;
		
		for(; (j < nEl) && (i < sizeList) ; i++ , j++)
		{
			BeanProdottoInterno p =	listaProdottiInterni.get(i);
			listaProdottiInterniNext.add( p );
		}
				
		request.setAttribute("listaProdottiInterni", listaProdottiInterniNext);
		request.setAttribute("posCorr", Integer.toString(intposCorr+nEl));
		request.setAttribute("sizeList", Integer.toString(sizeList));
		
		String url = "PaginaDeiProdottiInterni.jsp";
		url = response.encodeRedirectURL(url);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
