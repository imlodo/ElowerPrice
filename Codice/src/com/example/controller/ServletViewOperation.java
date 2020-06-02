package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.AcquistiModelDM;
import Model.BeanAcquisti;
import Model.BeanProdottoFornito;
import Model.BeanProdottoInterno;
import Model.BeanStore;
import Model.BeanUtente;
import Model.DriverManagerConnectionPool;
import Model.ProductFornitoModelDM;
import Model.ProductInternoModelDM;
import Model.StoreModelDM;
import Model.UserModelDM;

@WebServlet("/ServletViewOperation")
public class ServletViewOperation extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		if(session != null)
		{	ServletContext ctx = getServletContext();
			DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver");
			UserModelDM manager = new UserModelDM(driver);
			String username = "";
			boolean trovato = false;
			//Cerco nei cookie
			Cookie[] cookies = request.getCookies();
			if(cookies != null)
			{
				for(Cookie cookie : cookies)
				{
					if(trovato == false)
					{
						if(cookie.getName().equals("user"))
						{
							username = cookie.getValue();
							trovato = true;
						}
					}
					else break;
				}
			}
			//Cerco nella sessione
			if(trovato == false)
			{
				username = (String) session.getAttribute("username");
				if(username != null)
					trovato = true;
			}
			if(trovato == true)
			{
				try
				{
					BeanUtente utente = manager.doRetrieveByKey(username);
					if(utente.getType() == 0)
					{
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						return;
					}
					else
					{
						String op = request.getParameter("operation");
						if(op != null)
						{
							switch(op)
							{
							case "Utenti": 
							{
								try
								{
									ArrayList<BeanUtente> utenti = (ArrayList<BeanUtente>) manager.doRetrieveAll("username");
//									String pagina = (String) request.getAttribute("page");
									JSONArray arr = new JSONArray();
									for(BeanUtente b: utenti)
									{
										JSONObject obj = new JSONObject();
										try
										{
											obj.put("username", b.getUsername());
											obj.put("email", b.getEmail());
											obj.put("cf", b.getCf());
											obj.put("name", b.getName());
											obj.put("surname", b.getSurname());
											obj.put("tipo", b.getType());
											arr.put(obj);
										} 
										catch (JSONException e)
										{
											e.printStackTrace();
										}
									
									}
									PrintWriter out = response.getWriter();
									response.setHeader("Content-Type", "application/json");
									out.print(arr);
									out.flush();
								} 
								catch (SQLException e)
								{
									e.printStackTrace();
								}
							}
							break;
							case "Prodotti" : 
							{
								String tipo_prodotto = request.getParameter("tipo_prodotto");
								String num_pag = request.getParameter("num_page");
								if(tipo_prodotto != null)
								{
									switch(tipo_prodotto)
									{
										case "interno" :
										{
											ProductInternoModelDM managerPI = new ProductInternoModelDM(driver);
											ArrayList<BeanProdottoInterno> prodotti;
											try
											{
												prodotti = (ArrayList<BeanProdottoInterno>) managerPI.doRetrieveAll("prodotto_name");
												//String pagina = (String) request.getAttribute("page");
												JSONArray arr = new JSONArray();
												for(int i = Integer.parseInt(num_pag); i < prodotti.size(); i++)
												{
													if(i >= (Integer.parseInt(num_pag)+5) || i < 1)
														break;
													BeanProdottoInterno b = prodotti.get(i);
													JSONObject obj = new JSONObject();
													obj.put("image", b.getImageBytes());
													obj.put("name", b.getProductName());
													obj.put("prezzo", b.getPrezzo());
													obj.put("quantity", b.getQuantity());
													obj.put("availability", b.isAvailability());
													obj.put("descrizione", b.getDescr());
													obj.put("opzione_acquisto", b.getOpzione_acquisto().name());
													obj.put("cod_ean", b.getCod_ean());
													arr.put(obj);
												}
												PrintWriter out = response.getWriter();
												response.setHeader("Content-Type", "application/json");
												out.print(arr);
												out.flush();
											} 
											catch (SQLException | JSONException e)
											{
												e.printStackTrace();
											}
											break;
										}
										case "fornito" :
										{
											ProductFornitoModelDM managerPF = new ProductFornitoModelDM(driver);
											ArrayList<BeanProdottoFornito> prodotti = (ArrayList<BeanProdottoFornito>) managerPF.doRetrieveAll("prodotto_name");
											//String pagina = (String) request.getAttribute("page");
											JSONArray arr = new JSONArray();
											try
											{
												for(int i = Integer.parseInt(num_pag); i < prodotti.size(); i++)
												{
													if(i >= (Integer.parseInt(num_pag)+5) || i < 1)
														break;
													BeanProdottoFornito b = prodotti.get(i);
												JSONObject obj = new JSONObject();
												obj.put("image", b.getImageBytes());
												obj.put("name", b.getProductName());
												obj.put("prezzo_ieri", b.getPrezzoIeri());
												obj.put("prezzo_scorso_mese", b.getPrezzoScorsoMese());
												obj.put("prezzo_inizio_giorno", b.getPrezzoInizioGiorno());
												obj.put("prezzo_attuale", b.getPrezzoAttuale());
												obj.put("quantity", b.getQuantity());
												obj.put("availability", b.isDisponibile());
												obj.put("descrizione", b.getDescrizione());
												obj.put("opzione_acquisto", b.getOpzioneDAcquisto().name());
												obj.put("cod_ean", b.getCod_ean());
												obj.put("costo_spe", b.getCosti_spedizione());
												obj.put("link", b.getLink_prodotto_store());
												arr.put(obj);
												}
												PrintWriter out = response.getWriter();
												response.setHeader("Content-Type", "application/json");
												out.print(arr);
												out.flush();
											}
											catch (JSONException e)
											{
												e.printStackTrace();
											}
											break;
										}
									}
								}
								else
								{
									response.setStatus(HttpServletResponse.SC_NOT_FOUND);
									return;
								}
							}	
							break;
							case "Store" : 
							{
								StoreModelDM managerST = new StoreModelDM(driver);
								ArrayList<BeanStore> stores = (ArrayList<BeanStore>) managerST.doRetrieveAll("nome_store");
								//String pagina = (String) request.getAttribute("page");
								JSONArray arr = new JSONArray();
								try
								{
									for(BeanStore b: stores)
									{
										JSONObject obj = new JSONObject();
										obj.put("logo", b.getImageBytes());
										obj.put("nome_store", b.getNome_store());
										obj.put("p_iva", b.getP_iva());
										obj.put("n_visite", b.getN_visite());
										obj.put("via", b.getIndirizzo_store().getVia_store());
										obj.put("n_civico", b.getIndirizzo_store().getNumero_civico_store());
										obj.put("cap", b.getIndirizzo_store().getCity().getCap());
										obj.put("city", b.getIndirizzo_store().getCity().getCity());
										obj.put("prov", b.getIndirizzo_store().getCity().getProv());
										arr.put(obj);
									}
									PrintWriter out = response.getWriter();
									response.setHeader("Content-Type", "application/json");
									out.print(arr);
									out.flush();
								}
								catch (JSONException e)
								{
									e.printStackTrace();
								}
								break;
							}
							case "Acquisti" :
							{
								AcquistiModelDM managerAcquisti = new AcquistiModelDM(driver);
								ArrayList<BeanAcquisti> acquisti = (ArrayList<BeanAcquisti>) managerAcquisti.doRetrieveAll("utente_username");
								JSONArray arr = new JSONArray();
								try
								{
									for(BeanAcquisti b: acquisti)
									{
										JSONObject obj = new JSONObject();
										obj.put("username_utente", b.getUsername_utente());
										obj.put("prodotto_name", b.getProdotto_name());
										obj.put("prezzo_acquisto", b.getPrezzo_acquisto());
										obj.put("opzione_acquisto", b.getOpzione().name());
										arr.put(obj);
									}
									PrintWriter out = response.getWriter();
									response.setHeader("Content-Type", "application/json");
									out.print(arr);
									out.flush();
								}
								catch (JSONException e)
								{
									e.printStackTrace();
								}
								break;
							}
							default : break;
							}
						}	
					}
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		}
		else
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
//		//Encode url
//		String encodedURL = response.encodeRedirectURL("testing/test1.jsp");
//		RequestDispatcher dispatcher = request.getRequestDispatcher(encodedURL);
//		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
