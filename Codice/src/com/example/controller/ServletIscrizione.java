package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.BeanUtente;
import Model.DriverManagerConnectionPool;
import Model.UserModelDM;
import Model.ValidationFormForServlet;

/**
 * Servlet implementation class ServletIscrizione
 */
@WebServlet("/ServletIscrizione")
@MultipartConfig()
public class ServletIscrizione extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		PrintWriter out = response.getWriter();
		ArrayList<String> errori = new ArrayList<String>();
		BeanUtente bean = new BeanUtente();
		boolean busername = false, bemail = false, bcodiceFiscale = false, 
				bnome = false, bcognome = false, bpassword = false;
		
		//Get request parameter
		String username = request.getParameter("username");
		if(username == null || username.equals(""))
		{
			errori.add("Username mancante.");
		}
		else
		{
			busername = ValidationFormForServlet.checkUsername(username);
			if(busername == true)
				bean.setUsername(username);
			else
				errori.add("Username non valido.");
			
		}
		
		String email = request.getParameter("email");
		if(email == null || email.equals(""))
		{
			errori.add("Email mancante.");
		}
		else
		{
			bemail = ValidationFormForServlet.checkEmail(email);
			if(bemail == true)
				bean.setEmail(email);
			else
				errori.add("Email non valida.");
		}
		
		String cf = request.getParameter("codf");
		if(cf == null || cf.equals(""))
		{
			errori.add("Codice fiscale mancante.");
		}
		else
		{
			bcodiceFiscale = ValidationFormForServlet.checkCodiceFiscale(cf);
			if(bcodiceFiscale == true)
				bean.setCf(cf);
			else
				errori.add("Check digit codice fiscale non valido.");
		}
		
		String name = request.getParameter("nome");
		if(name == null || name.equals(""))
		{
			errori.add("Nome mancante.");
		}
		else
		{
			bnome = ValidationFormForServlet.checkName(name);
			if(bnome == true)
				bean.setName(name);
			else
				errori.add("Nome non valido.");
		}
		
		String surname = request.getParameter("cognome");
		if(surname == null || surname.equals(""))
		{
			errori.add("Cognome mancante.");
		}
		else
		{
			bcognome = ValidationFormForServlet.checkSurname(surname);
			if(bcognome == true)
				bean.setSurname(surname);
			else
			{
				errori.add("Cognome non valido.");
			}
		}
		
		String password = request.getParameter("password");
		if(password == null || password.equals(""))
		{
			errori.add("Password mancante.");
		}
		else
		{
			bpassword = ValidationFormForServlet.checkPassword(password);
			if(bpassword == true)
				bean.setPassword(password);
			else
			{
				errori.add("Password non valida.");
			}
		}
		bean.setType(0);
		
		if(errori.size() == 0)
		{
			Base64.Encoder encoder = Base64.getEncoder();    
	        String str = encoder.encodeToString(bean.getPassword().getBytes());
	        bean.setPassword(str); 
	        
			response.setContentType("text/html");
			ServletContext ctx = this.getServletContext();
			DriverManagerConnectionPool drive = (DriverManagerConnectionPool)ctx.getAttribute("driver");
			UserModelDM manager = new UserModelDM(drive);
			try
			{
				manager.doSave(bean);
					
				String url = "PaginaSuccessoRegistrazione.jsp";
				request.setAttribute("user", bean);
				url = response.encodeRedirectURL(url);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
					
			} 
			catch (SQLException e)
			{
				String url = "PaginaDiIscrizione.jsp";
				errori.add("Errore controlla i campi");
				url = response.encodeRedirectURL(url);
				for(String err : errori)
				{
					out.print("<label>"+err+"</label>");
					System.out.println(err);
				}
			}
		}
		else
		{
			for(String err : errori)
			{
				out.print("<label>"+err+"</label>");
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}