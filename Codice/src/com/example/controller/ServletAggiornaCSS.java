package com.example.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.utils.Utils;

import Model.DriverManagerConnectionPool;

@WebServlet("/ServletAggiornaCSS")
public class ServletAggiornaCSS extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String readFile(String filename)
   	{
	String path ="C:\\Users\\ROVER\\Dropbox\\PROGETTOTSW\\PROGETTO_UPDATE\\WebContent\\css";
//		String path ="/home/black/Dropbox/PROGETTOTSW/PROGETTO_UPDATE/WebContent/css";
//		String path ="C:\\Users\\drift\\Dropbox\\PROGETTOTSW\\PROGETTO_UPDATE\\WebContent\\css";
		File file = new File(path, filename + ".css"); 
   	  	String str = "";
   	  
   	  try
   	  {
   	    BufferedReader reader = new BufferedReader(new FileReader(file));
   	    String line;
   	    while ((line = reader.readLine()) != null)
   	    {
   	    	str+=line+"\n";
   	    }
   	    reader.close();
   	    return str;
   	  }
   	  catch (Exception e)
   	  {
   	    System.err.format("Exception occurred trying to read '%s'.", filename);
   	    e.printStackTrace();
   	    return null;
   	  }
   	}    
	private void saveFile(String filename, String saveString)
    { 	
    	String path ="C:\\Users\\ROVER\\Dropbox\\PROGETTOTSW\\PROGETTO_UPDATE\\WebContent\\css";
//    	String path ="/home/black/Dropbox/PROGETTOTSW/PROGETTO_UPDATE/WebContent/css";
//    	String path ="C:\\Users\\drift\\Dropbox\\PROGETTOTSW\\PROGETTO_UPDATE\\WebContent\\css";
    	File file = new File(path, filename + ".css");  
    	 
    	try
    	{
    		PrintWriter writer1 =null; 
			if(file.exists())
			{
					writer1 = new PrintWriter(file);
					synchronized (writer1)
					{
						writer1.write(saveString);                                                   
						writer1.flush();  
						writer1.close();
						System.err.println("File scritto con successo ");
					}
			}
			else
			{
				System.out.println("File non esistente " + path);
			}
    	}
    	catch(IOException err)
    	{
    		System.err.println(err.getMessage());
    	}
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver");
		try
		{
			if(Utils.checkAdminSession(request.getSession(),driver,request.getCookies()) == false)
			{
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String op = request.getParameter("cssOP");
		String fileName = request.getParameter("file_name");
		if(op.equals("save"))
		{
			String newCss = request.getParameter("css_modificato");
			if(newCss != null)
			{
				saveFile(fileName, newCss);
				request.setAttribute("esito", "save");
			}
		}
		if(op.equals("read"))
		{
			String records = readFile(fileName);
			request.setAttribute("css",records);	
		}
		String url="";
		url = "PaginaDellAdmin.jsp";
		url = response.encodeRedirectURL(url);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
