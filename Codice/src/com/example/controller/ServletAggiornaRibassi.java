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

@WebServlet("/ServletAggiornaRibassi")
public class ServletAggiornaRibassi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAggiornaRibassi() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("application/json");

		JSONObject jsonProduct = new JSONObject();
		String n_el_da_mostare = (String)request.getParameter("n_el_da_mostare");
		int nEl = Integer.parseInt(n_el_da_mostare);
		
		String posCorr = request.getParameter("posCorr");
		int intposCorr = Integer.parseInt(posCorr);
		
		ArrayList<BeanProdottoFornito> listaRibassi = null;
		ServletContext ctxRibassi = getServletContext();
		listaRibassi = (ArrayList<BeanProdottoFornito>)ctxRibassi.getAttribute("listaRibassiCorrenti");
		if(listaRibassi == null)
			System.out.println("LISTA NULL");
		
		
		String ogg = "ogg";
		int i = 0;
		String prodName = "";
		String prezzoIeri = "";
		String prezzoOggi = "";
		String imgProdotto = "";
		Base64.Encoder encoder = Base64.getEncoder();
		String storeName ="";
		
		i = intposCorr;
		int j = 0;
		ArrayList<String> strArr = null;
		for(; (j < nEl) && (i < listaRibassi.size()) ; i++ , j++)
		{
			
			strArr = new ArrayList<String>();
			
			BeanProdottoFornito p = listaRibassi.get(i);
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
				jsonProduct.put(ogg, strArr.toArray());
				strArr.removeAll(strArr);
				strArr.clear();
				
			} catch (JSONException e) {
				System.out.println("LISTA NULL");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			
			
			
		}
		
		String result = jsonProduct.toString();
		response.getWriter().print(result);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
