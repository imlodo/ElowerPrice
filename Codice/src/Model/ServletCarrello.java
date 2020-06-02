package Model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrello
 */
@WebServlet("/ServletCarrello")
public class ServletCarrello extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
	
    public ServletCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession sessioneCorrente = request.getSession(false);
		if(sessioneCorrente != null)
		{
			BeanProdottoInterno prodottoDaAggiungere = (BeanProdottoInterno) sessioneCorrente.getAttribute("prodottoDaAggingere"); 
			@SuppressWarnings("unchecked")
			ArrayList<BeanProdottoInterno> listaCarrelloUtente = (ArrayList<BeanProdottoInterno>)sessioneCorrente.getAttribute("listaCarrelloUtente");
			listaCarrelloUtente.add(prodottoDaAggiungere);
			sessioneCorrente.removeAttribute("prodottoDaAggingere");
			sessioneCorrente.setAttribute("listaCarrelloUtente", listaCarrelloUtente);
			
			String url = "/PaginaDiRicercaProdotti.jsp";
			response.encodeRedirectURL(url);
			RequestDispatcher rq = request.getRequestDispatcher(url);
			rq.forward(request, response);
			
		}
		else
		{
			// redirect errore 
			
			request.setAttribute("errore", "1");
			String url = "/PaginaDiRicercaProdotti.jsp";
			response.encodeRedirectURL(url);
			RequestDispatcher rq = request.getRequestDispatcher(url);
			rq.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
