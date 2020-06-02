package com.example.listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Model.DriverManagerConnectionPool;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener 
{
    public void contextDestroyed(ServletContextEvent event)  
    { 
    	ServletContext ctx = event.getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
		Connection conn;
		try
		{
			conn = driver.getConnection();
			conn.close();
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
    }

	
    public void contextInitialized(ServletContextEvent event)  
    { 
    	try
		{  
		
    		DriverManagerConnectionPool driver = new DriverManagerConnectionPool(); 
		          
			//storing connection object as an attribute in ServletContext  
			ServletContext ctx = event.getServletContext();  
			ctx.setAttribute("driver", driver);
		
		}
		catch(Exception e)
		{
			
		}
    }
	
}
