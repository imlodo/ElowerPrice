//package com.example.controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Base64;
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
//import Model.UserModelDM;
//import Model.BeanUtente;
//import Model.DriverManagerConnectionPool;
//
///**
// * Servlet implementation class ServletLogin
// */
//@WebServlet("/ServletLogin")
//public class ServletLogin extends HttpServlet 
//{
//	private static final long serialVersionUID = 1L;
//
//	public static String DecodificatoreBase64(String src )
//	{
//		//src è la sorgente 
//		Base64.Decoder dec = Base64.getDecoder();// decodificatore// creiamo il decodificatore
//		byte rawdecodedString[] = dec.decode(src); // ritorna una array di byte 
//		String decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
//		return decodedString;
//	}
//	
//       
//    public ServletLogin() 
//    {
//        super();
//    }
//
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//    {
//    	
//    	
//    }
//    	    	
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//	
//		/* 
//		per codificare una stringa invece si fa:
//		String stringaDaCodificare = "...";
//		Base64.Encoder enc = Base64.getEncoder();
//		byte encoded[] = enc.encode(stringaDaCodificare.getBytes());
//		*/
//    	
//    	/* per decodificare
//    	String src = request.getParameter("username");
//		String decodificatore = new String( );
//		Base64.Decoder dec = Base64.getDecoder();
//		byte rawdecodedString[] = dec.decode(src);
//		String decodedString = new String(rawdecodedString);
//    	*/
//		
//    	
//    	//response.setContentType("text/html");
//    	String requestUsername = request.getParameter("username");
//		String requestPassword = request.getParameter("password");
//    	if
//    	(requestUsername == null 
//    	|| requestUsername.equals("") 
//    	|| requestPassword == null 
//    	|| requestPassword.equals("")
//    	)
//    	{
//    		// errore nell'inserimento dei dati da parte dell'utente
//    		// mandiamo l'errore alla jsp 
//    		String url = "/PaginaDiLogin.jsp"; // url della jsp
//			request.setAttribute("errore", "1");
//			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//			dispatcher.forward(request, response);
//    	}
//    	else
//    	{
//    		String src = ""; // sorgente 
//			Base64.Decoder dec = Base64.getDecoder();// decodificatore// creiamo il decodificatore
//	
//			
//			String decodedString;
//	
//			//decodifica dell'user name
//			src = requestUsername;
//			byte rawdecodedString[] = dec.decode(src); // ritorna una array di byte 
//			decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
//			
//			String username = new String(decodedString);// facciamo una copia della stringa decodificata
//	
//			//decodifica della password
//			src = requestPassword;
//			rawdecodedString = dec.decode(src); // ritorna una array di byte 
//			decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
//				
//			// facciamo il controllo della stringa passata dalla form 
//			
//			String password = new String(decodedString);// facciamo una copia della stringa decodificata
//						
//			boolean busername, bpassword;
//			
//			busername = ValidationFormForServlet.checkName(username); // valida il form 
//			bpassword = ValidationFormForServlet.checkPassword(password); // valida il form 
//			
//			if(busername == true && bpassword == true) // se il form è valido 
//			{
//			
//				ServletContext ctx = getServletContext();
//				DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
//				UserModelDM interfaceDB = new UserModelDM(driver);
//				
//				BeanUtente beanUtente = new BeanUtente();
//				try 
//				{
//					beanUtente = interfaceDB.doRetrieveByKey(username);
//					if(beanUtente == null)
//					{
//						String url = "/PaginaDiLogin.jsp"; // url della jsp
//						//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
//						request.setAttribute("errore", "1");
//						RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//						dispatcher.forward(request, response);
//					}
//				}
//				catch (SQLException e) 
//				{
//					//e.printStackTrace();
//					String url = "/PaginaDiLogin.jsp"; // url della jsp
//					//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
//					request.setAttribute("errore", "1");
//					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//					dispatcher.forward(request, response);
//				}
//				String usernameDB = beanUtente.getUsername();
//				if(usernameDB == null)
//				{
//					System.out.println("errore nel DB");
//					String url = "/PaginaDiLogin.jsp"; // url della jsp
//					//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
//					request.setAttribute("errore", "1");
//					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//					dispatcher.forward(request, response);
//					
//				}
//				String passwordDB = beanUtente.getPassword(); // la password è in base64
//				if(passwordDB == null)
//				{
//					System.out.println("errore nel DB");
//					String url = "/PaginaDiLogin.jsp"; // url della jsp
//					//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
//					request.setAttribute("errore", "1");
//					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//					dispatcher.forward(request, response);
//					
//				}
//				
//				rawdecodedString = dec.decode(passwordDB); // ritorna una array di byte 
//				decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
//				String passwordDBdecodificata = new String(decodedString);
//				
//				if(usernameDB.equals(username) && passwordDBdecodificata.equals(password) )
//				{
//					// l'utente ha effettuato l'accesso con successo
//					
//					HttpSession nuovaSessioneUtenteLoggato = request.getSession(true);
//					nuovaSessioneUtenteLoggato.setAttribute("log", "1");
//					
//					nuovaSessioneUtenteLoggato.setAttribute("BeanUtente", beanUtente);
//					
//					if(beanUtente.getType() == 1) // vediamo se l'utente è amministratore
//					{
//						
//						// Encode a URL string with the session id appended
//						// to it.
//						String url = "/PaginaDellAdmin.jsp"; // pagina di redirect
//						//response.encodeRedirectURL( url );
//						// Redirect the client to the new URL
//						RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//						dispatcher.forward(request, response);
//						
//					}
//					else
//					{
//						
//						String url = "/HomePage.jsp"; // pagina di redirect
//						response.encodeRedirectURL( url );
//						// Redirect the client to the new URL
//						//response.sendRedirect(url);
//						RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//						dispatcher.forward(request, response);
//
//					}
//					
//				}
//				else
//				{
//					// errore nell'inserimento di username o password
//					//va gestita con javascript
//
//
//					String url = "/PaginaDiLogin.jsp"; // url della jsp
//					//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
//					request.setAttribute("errore", "1");
//					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//					dispatcher.include(request, response);
//					
//				}
//				
//			}
//			else
//			{
//				String url = "/PaginaDiLogin.jsp"; // url della jsp
//				//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
//				request.setAttribute("errore", "1");
//				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//				dispatcher.forward(request, response);
//			
//			}
//		
//    	
//    		}
//			
//		}
//	
//
//}

package com.example.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.UserModelDM;
import Model.ValidationFormForServlet;
import Model.BeanUtente;
import Model.DriverManagerConnectionPool;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public static String DecodificatoreBase64(String src )
	{
		//src è la sorgente 
		Base64.Decoder dec = Base64.getDecoder();// decodificatore// creiamo il decodificatore
		byte rawdecodedString[] = dec.decode(src); // ritorna una array di byte 
		String decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
		return decodedString;
	}



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	
    	
    }
    	    	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		String src = ""; // sorgente 
		Base64.Decoder dec = Base64.getDecoder();
		String decodedString;
		String requestUsername = request.getParameter("username");
		if(requestUsername == null || requestUsername.equals("")){}
		else
		{
			//decodifica dell'user name
			src = requestUsername;	
			byte rawdecodedString[] = dec.decode(src); // ritorna una array di byte 
			decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
			String username = new String(decodedString);// facciamo una copia della stringa decodificata
		
			//Save username
			String requestCheck = request.getParameter("ricorda");
			synchronized(session) 
			{
				if(requestCheck != null)
				{
					Cookie userName = new Cookie("saveUser", username);
					Cookie check = new Cookie("check", "si");
					//Cookie
					userName.setMaxAge(60 * 60 * 24 * 365 * 10);
					check.setMaxAge(60 * 60 * 24 * 365 * 10);
					response.addCookie(userName);
					response.addCookie(check);
				}
				else
				{
					Cookie userName = new Cookie("saveUser", "");
					Cookie check = new Cookie("check", "");
					//Cookie
					userName.setMaxAge(0);
					check.setMaxAge(0);
					response.addCookie(userName);
					response.addCookie(check);
				}
			}
		}
		String requestPassword = request.getParameter("password");
    	if
    	(  requestUsername == null 
    	|| requestUsername.equals("") 
    	|| requestPassword == null 
    	|| requestPassword.equals("")
    	)
    	{
    		// errore nell'inserimento dei dati da parte dell'utente
    		// mandiamo l'errore alla jsp 
    		String url = "PaginaDiLogin.jsp"; // url della jsp
			request.setAttribute("errore", "1");
			
			url = response.encodeRedirectURL(url);
			// Redirect the client to the new URL
			//response.sendRedirect(url);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			return;
    	}
    	else
    	{
    		src = ""; // sorgente 
			//decodifica dell'user name
			src = requestUsername;
			byte rawdecodedString[] = dec.decode(src); // ritorna una array di byte 
			decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
			String username = new String(decodedString);// facciamo una copia della stringa decodificata
	
			//decodifica della password
			src = requestPassword;
			rawdecodedString = dec.decode(src); // ritorna una array di byte 
			decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
				
			// facciamo il controllo della stringa passata dalla form 
			
			String password = new String(decodedString);// facciamo una copia della stringa decodificata		
			boolean busername, bpassword;
			
			busername = ValidationFormForServlet.checkName(username); // valida il form 
			bpassword = ValidationFormForServlet.checkPassword(password); // valida il form 
			
			if(busername == true && bpassword == true) // se il form è valido 
			{
			
				ServletContext ctx = getServletContext();
				DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
				UserModelDM interfaceDB = new UserModelDM(driver);
				
				BeanUtente beanUtente = new BeanUtente();
				try 
				{
					beanUtente = interfaceDB.doRetrieveByKey(username);
					if(beanUtente == null)
					{
						String url = "PaginaDiLogin.jsp"; // url della jsp
						//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
						request.setAttribute("errore", "1");
						url = response.encodeRedirectURL(url);
						// Redirect the client to the new URL
						//response.sendRedirect(url);
						RequestDispatcher dispatcher = request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
						return;
					}
				}
				catch (SQLException e) 
				{
					//e.printStackTrace();
					String url = "PaginaDiLogin.jsp"; // url della jsp
					//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
					request.setAttribute("errore", "1");
					
					url = response.encodeRedirectURL(url);
					// Redirect the client to the new URL
					//response.sendRedirect(url);
					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward(request, response);
					return;
				}
				String usernameDB = beanUtente.getUsername();
				if(usernameDB == null)
				{
					System.out.println("errore nel DB");
					String url = "PaginaDiLogin.jsp"; // url della jsp
					//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
					request.setAttribute("errore", "1");
					
					url = response.encodeRedirectURL(url);
					// Redirect the client to the new URL
					//response.sendRedirect(url);
					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward(request, response);
					return;
					
				}
				String passwordDB = beanUtente.getPassword(); // la password è in base64
				if(passwordDB == null)
				{					
					System.out.println("errore nel DB");
					String url = "PaginaDiLogin.jsp"; // url della jsp
					//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
					request.setAttribute("errore", "1");
					url = response.encodeRedirectURL(url);
					// Redirect the client to the new URL
					//response.sendRedirect(url);
					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward(request, response);
					return;
				}
				
				rawdecodedString = dec.decode(passwordDB); // ritorna una array di byte 
				decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
				String passwordDBdecodificata = new String(decodedString);
				
				if(usernameDB.equals(username) && passwordDBdecodificata.equals(password) )
				{
					// l'utente ha effettuato l'accesso con successo
					//Sessione
					synchronized(session) 
					{
						session.setAttribute("log", "1");
						session.setAttribute("username", usernameDB);
						//Cookie
						//setting session to expiry in 30 mins
						session.setMaxInactiveInterval(30*60);
						Cookie userName = new Cookie("user", usernameDB);
						response.addCookie(userName);
						
						if(beanUtente.getType() == 1) // vediamo se l'utente amministratore
						{	
							// Encode a URL string with the session id appended to it.
							String url = "PaginaDellAdmin.jsp"; // pagina di redirect
							String encodedURL2 = response.encodeRedirectURL(url);
							// Redirect the client to the new URL
							response.sendRedirect(encodedURL2);
							return;
						}
						else
						{
							//Get the encoded URL string
							String encodedURL = response.encodeRedirectURL("HomePage.jsp");
							response.sendRedirect(encodedURL);
							return;
						}
						
					}
					
				}
				else
				{
					// errore nell'inserimento di username o password
					//va gestita con javascript


					String url = "/PaginaDiLogin.jsp"; // url della jsp
					//String url = "/Z_progettoTSW_localhost_MIO/WebContent/PaginaDiLogin.jsp"; // url della jsp
					request.setAttribute("errore", "1");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
					dispatcher.forward(request, response);
					return;
					
				}
				
			}
			else
			{
				String url = "PaginaDiLogin.jsp"; // url della jsp
				request.setAttribute("errore", "1");
				url = response.encodeRedirectURL(url);
				// Redirect the client to the new URL
				//response.sendRedirect(url);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			
			}
		
    	
    		}
			
		}
	

}
