package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;

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
import Model.ProductFornitoModelDM;
import Model.ProductInternoModelDM;

/**
 * Servlet implementation class ServletMostraProdottoInterno
 */
@WebServlet("/ServletMostraProdottoInterno")
public class ServletMostraProdottoInterno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletMostraProdottoInterno() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeProdotto = request.getParameter("prodName");
		
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
		ProductInternoModelDM interfaceDB = new ProductInternoModelDM(driver);
		
		try 
		{
			BeanProdottoInterno prodottoTrovato = (BeanProdottoInterno)interfaceDB.doRetrieveByKey(nomeProdotto);
			if(prodottoTrovato!=null)
			{

				request.setAttribute("prodottoInternoTrovato", prodottoTrovato);
				String url = "/PaginaDelProdotto.jsp"; // pagina di redirect
				
				url = response.encodeRedirectURL(url);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				
			}
			else
			{
				String url = "/PaginaDelProdotto.jsp"; // pagina di redirect
				request.setAttribute("errore", "1");
				url = response.encodeRedirectURL(url);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
