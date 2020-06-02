package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Model.BeanProdottoFornito;


@WebServlet("/ServletAggiornaProdottiPerCategoria")
public class ServletAggiornaProdottiPerCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAggiornaProdottiPerCategoria() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		String posCorr = request.getParameter("posCorr");
		String Npagina = request.getParameter("Npagina");
		String NelementiDaMostr = request.getParameter("NelementiDaMostr");
		String sizeList = request.getParameter("sizeList");
		String change = request.getParameter("change");
		
		int intposCorr = Integer.parseInt(posCorr);
		int intNpagina = Integer.parseInt(Npagina);
		int intNelementiDaMostr = Integer.parseInt(NelementiDaMostr);
		int intsizeList = Integer.parseInt(sizeList);
		
		int intchange = Integer.parseInt(change);
		
		if( intchange == 1)
		{	
		
			intposCorr += intNelementiDaMostr;
			if(intposCorr > intsizeList)
			{
				request.setAttribute("stop" , "1");		
				request.setAttribute("posCorr", Integer.toString(intposCorr-intNelementiDaMostr));
				request.setAttribute("Npagina", Npagina);
				String url = "PaginaDiRicercaPerCategoriaProdotti.jsp"; // pagina di redirect
				url = response.encodeRedirectURL(url);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
			
			request.setAttribute("posCorr", Integer.toString(intposCorr));
			
			intNpagina += 1;
			request.setAttribute("Npagina", Npagina);
			
			String url = "PaginaDiRicercaPerCategoriaProdotti.jsp"; // pagina di redirect
			url = response.encodeRedirectURL(url);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
		if(intchange == 0 )
		{
			intposCorr -= intNelementiDaMostr;
			if(intposCorr < 0)
			{
				String url = "PaginaDiRicercaPerCategoriaProdotti.jsp"; // pagina di redirect
				url = response.encodeRedirectURL(url);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
			
			request.setAttribute("posCorr", Integer.toString(intposCorr));
			
			intNpagina -= 1;
			request.setAttribute("Npagina", Npagina);
			
			String url = "PaginaDiRicercaPerCategoriaProdotti.jsp";
			url = response.encodeRedirectURL(url);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		}
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
