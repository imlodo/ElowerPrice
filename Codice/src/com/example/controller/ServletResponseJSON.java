package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Model.BeanProdottoFornito;
import Model.DriverManagerConnectionPool;
import Model.ProductFornitoModelDM;

@WebServlet("/ServletResponseJSON")
public class ServletResponseJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletResponseJSON() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		response.setContentType("application/json");

		JSONObject jsonProduct = new JSONObject();
		//json.put("info", o1);
		
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver");
		try
		{
			//String prod_name = request.getParameter("prodottoPerNome");
//			System.out.println("Prodotto->"+prod_name);
			ProductFornitoModelDM manager = new ProductFornitoModelDM(driver);
			ArrayList<BeanProdottoFornito> prodotti = (ArrayList<BeanProdottoFornito>) manager.doRetrieveAll("prodotto_name");
			String prodottoJson = "";
			for(BeanProdottoFornito p : prodotti)
			{
				prodottoJson= p.getProductName();
				jsonProduct.put(prodottoJson, prodottoJson);
					
			}
		
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e){}
		
		String result = jsonProduct.toString();
		response.getOutputStream().print(result);
		//response.getWriter().print(result);
//		response.getOutputStream().print("'["+jsonProduct.toString()+"]'");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}