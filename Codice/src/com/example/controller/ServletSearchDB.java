package com.example.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.utils.Utils;

import Model.BeanCategoria;
import Model.BeanCity;
import Model.BeanSpecificaTecnica;
import Model.BeanStore;
import Model.CategorieModelDM;
import Model.CityModelDM;
import Model.DriverManagerConnectionPool;
import Model.Prodotto;
import Model.ProductModelDM;
import Model.SpecificheModelDM;
import Model.StoreModelDM;

/**
 * Servlet implementation class ServletSearchDB
 */
@WebServlet("/ServletSearchDB")
public class ServletSearchDB extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

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
			e.printStackTrace();
		}
		synchronized (driver)
		{
			String op = request.getParameter("operation");
			if(op != null)
			{
				switch(op)
				{
					case "cercaProdotti" :
					{
						PrintWriter out = response.getWriter();
						String value = request.getParameter("word").toLowerCase();
						ProductModelDM manager = new ProductModelDM(driver);
						ArrayList<Prodotto> list;
						try
						{
							list = (ArrayList<Prodotto>) manager.doRetrieveAll("prod_name");
							list.removeIf(s->!(s.getProductName().toLowerCase().contains(value)));
							out.print("<datalist id='selectProduct'>");
							int i = 0;
							for(Prodotto b: list)
							{
								if(i > 10)
								break;
								out.append("<option value='"+b.getProductName()+"'>");
								i+=1;
							}
							out.append("</datalist>");
							} 
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						break;
					}
					case "cercaStore" :
					{
						PrintWriter out = response.getWriter();
						String value = request.getParameter("word").toLowerCase();
						StoreModelDM manager = new StoreModelDM(driver);
						ArrayList<BeanStore> list;
						try
						{
							list = (ArrayList<BeanStore>) manager.doRetrieveAll("nome_store");
							list.removeIf(s->!(s.getNome_store().toLowerCase().contains(value)));
							out.print("<datalist id='selectStore'>");
							int i = 0;
							for(BeanStore b: list)
							{
								if(i > 10)
								break;
								out.append("<option value='"+b.getNome_store()+"'>");
								i+=1;
							}
							out.append("</datalist>");
						} 
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						break;
					}
					case "cercaCap" :
					{
						PrintWriter out = response.getWriter();
						String value = request.getParameter("word").toLowerCase();
						CityModelDM manager = new CityModelDM(driver);
						ArrayList<BeanCity> list;
						try
						{
							list = (ArrayList<BeanCity>) manager.doRetrieveAll("cap");
							list.removeIf(s->!(s.getCap().toLowerCase().contains(value)));
							out.print("<datalist id='selectCap'>");
							int i = 0;
							for(BeanCity b: list)
							{
								if(i > 10)
								break;
								out.append("<option value='"+b.getCap()+"'>");
								i+=1;
							}
							out.append("</datalist>");
						} 
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						break;
					}
					case "cercaCategoria":
					{
						PrintWriter out = response.getWriter();
						String value = request.getParameter("word").toLowerCase();
						CategorieModelDM manager = new CategorieModelDM(driver);
						ArrayList<BeanCategoria> list;
						String num = request.getParameter("num");
						try
						{
							list = (ArrayList<BeanCategoria>) manager.doRetrieveAll("nome_categoria");
							list.removeIf(s->!(s.getNomeCategoria().toLowerCase().contains(value)));
							if(num.equals("1"))
								out.print("<datalist id='selectCategoria1'>");
							if(num.equals("2"))
								out.print("<datalist id='selectCategoria2'>");
							if(num.equals("3"))
								out.print("<datalist id='selectCategoria3'>");
							if(num.equals("4"))
								out.print("<datalist id='selectCategoria4'>");
							if(num.equals("5"))
								out.print("<datalist id='selectCategoria5'>");
							int i = 0;
							for(BeanCategoria b: list)
							{
								if(i > 10)
								break;
								out.append("<option value='"+b.getNomeCategoria()+"'>");
								i+=1;
							}
							out.append("</datalist>");
						} 
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						break;
					}
					case "cercaNomeSpecifica":
					{
						PrintWriter out = response.getWriter();
						String value = request.getParameter("word").toLowerCase();
						SpecificheModelDM manager = new SpecificheModelDM(driver);
						ArrayList<BeanSpecificaTecnica> list;
						String num = request.getParameter("num");
						try
						{
							list = (ArrayList<BeanSpecificaTecnica>) manager.doRetrieveAll("nome_specifica");
							list.removeIf(s->!(s.getNomeSpecifica().toLowerCase().contains(value)));
							if(num.equals("1"))
								out.print("<datalist id='selectSpecifica1'>");
							if(num.equals("2"))
								out.print("<datalist id='selectSpecifica2'>");
							if(num.equals("3"))
								out.print("<datalist id='selectSpecifica3'>");
							if(num.equals("4"))
								out.print("<datalist id='selectSpecifica4'>");
							if(num.equals("5"))
								out.print("<datalist id='selectSpecifica5'>");
							if(num.equals("6"))
								out.print("<datalist id='selectSpecifica6'>");
							if(num.equals("7"))
								out.print("<datalist id='selectSpecifica7'>");
							int i = 0;
							for(BeanSpecificaTecnica b: list)
							{
								if(i > 10)
								break;
								out.append("<option value='"+b.getNomeSpecifica()+"'>");
								i+=1;
							}
							out.append("</datalist>");
						} 
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						break;
					}
					default: break;
				}		
			}	
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
