//package com.example.controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import Model.BeanUtente;
//import Model.DriverManagerConnectionPool;
//import Model.UserModelDM;
//
//
//@WebServlet("/ServletModificheDatiUtente")
//public class ServletModificheDatiUtente extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    
//    public ServletModificheDatiUtente() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//		
//
//	}
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//		
//		//prendiamo il bean
//		//
//		HttpSession sessioneCorrente = request.getSession(false);
//		BeanUtente beanUtenteCorrente = (BeanUtente) sessioneCorrente.getAttribute("BeanUtente");
//				
//		String username = request.getParameter("username");
//		String email = request.getParameter("email");
//		String cf = request.getParameter("cf");
//		String name = request.getParameter("nome");
//		String surname = request.getParameter("cognome");
//		
//		String password = request.getParameter("password");
//		//decodifica dell'user name
//			
//		if(password == null)
//		{
//			password = beanUtenteCorrente.getPassword();
//		}
//		
//		beanUtenteCorrente.setName(name);
//		beanUtenteCorrente.setSurname(surname);
//		beanUtenteCorrente.setCf(cf);
//		beanUtenteCorrente.setUsername(username);
//		beanUtenteCorrente.setEmail(email);
//		beanUtenteCorrente.setPassword(password);
//		
//		ServletContext ctx = getServletContext();
//		DriverManagerConnectionPool drive = (DriverManagerConnectionPool)ctx.getAttribute("driver");
//		UserModelDM interfaceDB = new UserModelDM(drive);
//		
//		try 
//		{
//			interfaceDB.doUpdate(beanUtenteCorrente);
//			String url = "/PaginaDiModificheDatiUtente.jsp"; // pagina di redirect
//			//response.encodeRedirectURL( url );
//			// Redirect the client to the new URL
//			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//			dispatcher.forward(request, response);
//		}
//		catch (SQLException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//	
//	}
//
//}






package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BeanUtente;
import Model.DriverManagerConnectionPool;
import Model.UserModelDM;


@WebServlet("/ServletModificheDatiUtente")
@MultipartConfig()
public class ServletModificheDatiUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletModificheDatiUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		//prendiamo il bean
		
		HttpSession sessioneCorrente = request.getSession(false);
		String usernameSession = (String)sessioneCorrente.getAttribute("username");
		if(usernameSession != null)
		{
			ServletContext ctx = getServletContext();
			DriverManagerConnectionPool drive = (DriverManagerConnectionPool)ctx.getAttribute("driver");
			UserModelDM interfaceDB = new UserModelDM(drive);
			BeanUtente beanUtenteCorrente = null;
			try 
			{
				beanUtenteCorrente = interfaceDB.doRetrieveByKey(usernameSession);
				if(beanUtenteCorrente!=null)
				{
					
					String username = request.getParameter("username");
					String email = request.getParameter("email");
					String cf = request.getParameter("cf");
					String name = request.getParameter("nome");
					String surname = request.getParameter("cognome");
					
					String password = request.getParameter("password");
					//decodifica dell'user name
						
					if(password == null || password.equals(""))
					{
						password = beanUtenteCorrente.getPassword();
					}
					else
					{
						beanUtenteCorrente.setPassword(password);
					}
					
					beanUtenteCorrente.setName(name);
					beanUtenteCorrente.setSurname(surname);
					beanUtenteCorrente.setCf(cf);
					beanUtenteCorrente.setUsername(username);
					beanUtenteCorrente.setEmail(email);
					
					try 
					{
						
						interfaceDB.doUpdate(beanUtenteCorrente);
						

						String url = "PaginaDellAreaUtente.jsp"; // pagina di redirect
						url = response.encodeRedirectURL(url);
						RequestDispatcher dispatcher = request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
					}
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			
		}
		//else errore sessione 
				
		
		
		
		
	
	}

}

