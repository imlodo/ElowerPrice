package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class ServletLogout extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public ServletLogout() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		//invalidate the session if exists
		HttpSession session = request.getSession(false);
		synchronized(session) 
		{
			//response.setContentType("text/html");
			Cookie[] cookies = request.getCookies();
			if(cookies != null)
			{
				for(Cookie cookie : cookies)
				{
					if(cookie.getName().equals("JSESSIONID"))
					{
						System.out.println("JSESSIONID="+cookie.getValue());
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
			System.out.println("User="+session.getAttribute("user"));
			if(session != null)
			{
				session.invalidate();
			}
			//no encoding because we have invalidated the session
			response.sendRedirect("HomePage.jsp");
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
