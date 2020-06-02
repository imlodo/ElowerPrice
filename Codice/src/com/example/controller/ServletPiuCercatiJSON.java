package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Model.BeanProdottoFornito;

/**
 * Servlet implementation class ServletPiuCercatiJSON
 */
@WebServlet("/ServletPiuCercatiJSON")
public class ServletPiuCercatiJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPiuCercatiJSON() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("application/json");

		JSONObject jsonProduct = new JSONObject();
		
		
		ArrayList<BeanProdottoFornito> listaPiuCercati = null;
		ServletContext ctxPiuCercati = getServletContext();
		listaPiuCercati = (ArrayList<BeanProdottoFornito>)ctxPiuCercati.getAttribute("listaPiuCercati");
		if(listaPiuCercati==null)
			return;
		
		for(BeanProdottoFornito pp : listaPiuCercati)
		{
			System.out.println("55555555555555"+pp.getProductName()+"\n");
		}
		
		
//		if(listaDeiRibassiDelGiorno == null)
//			return;//errore 
		
		String ogg = "ogg";
		int i = 0;
		String prodName = "";
		String prezzoIeri = "";
		String prezzoOggi = "";
		String imgProdotto = "";
		Base64.Encoder encoder = Base64.getEncoder(); // Encoding string  
		String storeName ="";
		
		ArrayList<String> strArr = null;
		for(i = 0; i <  listaPiuCercati.size(); i++)
		{
			
			strArr = new ArrayList<String>();
			
			BeanProdottoFornito p = listaPiuCercati.get(i);
			ogg = "ogg"+Integer.toString(i);
			prodName = p.getProductName();
			storeName = p.getNameStore();
			prezzoIeri = Double.toString( p.getPrezzoIeri() );
			prezzoOggi = Double.toString( p.getPrezzoAttuale() );
			imgProdotto = encoder.encodeToString( p.getImageBytes());
			strArr.add(prodName);
			strArr.add(storeName);
			strArr.add(prezzoOggi);
			strArr.add(prezzoIeri);
			strArr.add(imgProdotto);
			
			try {
				jsonProduct.put(ogg, strArr);
				//System.out.println("......................... "+ jsonProduct.toString());
				strArr.removeAll(strArr);
				strArr.clear();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
//		try {
//			jsonProduct.put("sizeList", listaDeiRibassiDelGiorno.size() );
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e){}
		//System.out.println("\n//////////......................... "+ jsonProduct.toString());
		String result = jsonProduct.toString();
		response.getOutputStream().print(result);
		//response.getWriter().print(result);
//		response.getOutputStream().print("'["+jsonProduct.toString()+"]'");
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
