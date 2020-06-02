package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.example.utils.Utils;

import Model.BeanCategoria;
import Model.BeanCity;
import Model.BeanIndirizzoStore;
import Model.BeanProdottoFornito;
import Model.BeanProdottoInterno;
import Model.BeanSpecificaTecnica;
import Model.BeanStore;
import Model.CategorieModelDM;
import Model.CityModelDM;
import Model.DriverManagerConnectionPool;
import Model.Prodotto;
import Model.ProductFornitoModelDM;
import Model.ProductInternoModelDM;
import Model.ProductModelDM;
import Model.SpecificheModelDM;
import Model.StoreModelDM;
import Model.BeanProdottoFornito.opzioniAcquisto;

@WebServlet("/ServletAddOperation")
@MultipartConfig()
public class ServletAddOperation extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
		HttpSession session = request.getSession(false);
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver");
		Cookie[] cookies = request.getCookies();
		try
		{
			if(Utils.checkAdminSession(session, driver, cookies) == false)
			{	
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			else
			{
				String op = request.getParameter("operation");
				if(op != null)
				{	switch(op)
					{
					case "Store":
					{
						ArrayList<String> errori = new ArrayList<String>();
						BeanStore store = new BeanStore();
						BeanIndirizzoStore indirizzo = new BeanIndirizzoStore();
						//Nome Store
						String nome_store =  request.getParameter("store_name");
						if(nome_store == null || nome_store.equals(""))
						{
							errori.add("Nome store richiesto");
						}
						//Partita Iva
						String p_iva = request.getParameter("pIVA");
						if(p_iva == null || p_iva.equals(""))
						{
							errori.add("Partita iva richiesta");
						}
						if(p_iva.length() > 11)
						{
							errori.add("Partita iva max 11 cifre");
						}
						//N_visite
						int n_visite = 0;
						//Logo_Store
						Part filePart = request.getPart("image");
						if(filePart.getInputStream().available() == 0)
						{
							errori.add("immagine store obbligatorio");
						}
						else
						{
							if(ImageIO.read(filePart.getInputStream()) == null)
							{
								errori.add("Si prega di inserire soltanto immagini");
							}
							else
							{
								if(filePart.getInputStream().available() > 1000000)
								{
									errori.add("La dimensione del file supera 1MB");
								}
								else
								{
									byte[] image = new byte[filePart.getInputStream().available()];
									filePart.getInputStream().read(image);
									store.setImageBytes(image);
								}
							}
				        }
						//Cap Store
						String cap_store = request.getParameter("cap");
						if(cap_store == null || cap_store.equals(""))
						{
							errori.add("Cap store richiesto");
						}
						else
						{
							if(cap_store.length() > 5)
							{
								errori.add("Il cap deve essere di 5 cifre");
							}
							else
							{
								try
								{
									@SuppressWarnings("unused")
									int testCap = Integer.valueOf(cap_store);
									CityModelDM managerCap = new CityModelDM(driver);
									if(managerCap.doRetrieveByKey(cap_store) == null)
									{
										errori.add("Cap non presente");
									}
								}
								catch(NumberFormatException exception) 
								{
									errori.add("Il cap deve contenere solo numeri");
								}
							}
							
						}
						String via_store = request.getParameter("via_store");
						if(via_store == null || via_store.equals(""))
						{
							errori.add("Via store richiesto");
						}
						String civico_string = request.getParameter("civico");
						if(civico_string == null || civico_string.equals(""))
						{
							errori.add("Civico richiesto richiesto");
						}
						else
						{
							try
							{
								int num_civico = Integer.valueOf(civico_string);
								indirizzo.setNumero_civico_store(num_civico);
							}
							catch(NumberFormatException exception) 
							{
								errori.add("Il civico deve essere numerico");
							}
						}
						if(errori.size() == 0)
						{
							BeanCity city = new BeanCity();
							city.setCap(cap_store);
							indirizzo.setNome_store(nome_store);
							indirizzo.setVia_store(via_store);
							indirizzo.setCity(city);
							store.setNome_store(nome_store);
							store.setP_iva(p_iva);
							store.setN_visite(n_visite);
							store.setIndirizzo_store(indirizzo);
							StoreModelDM manager = new StoreModelDM(driver);
							manager.doSave(store);
							response.getOutputStream().print("Store Aggiunto con successo");
						}
						else
						{
							PrintWriter out = response.getWriter();
							response.setCharacterEncoding("UTF-8");
							for(String err : errori)
							{
								out.append(err+" | ");
							}
						}
						break;
					}
					case "Prodotti":
					{
						String tipo = request.getParameter("tipo_prodotto");
						switch(tipo)
						{
							case "normal":
							{
								Prodotto product = new Prodotto();
								ProductModelDM manager = new ProductModelDM(driver);
								CategorieModelDM managerCat = new CategorieModelDM(driver);
								SpecificheModelDM managerSpec = new SpecificheModelDM(driver);
								ArrayList<BeanCategoria> categorie = new ArrayList<BeanCategoria>();
								ArrayList<BeanSpecificaTecnica> specifiche = new ArrayList<BeanSpecificaTecnica>();
								ArrayList<String> errori = new ArrayList<String>();
								
								//Check prod_name 
								String prod_name = request.getParameter("prod_name");
								if(prod_name == null || prod_name.equals(""))
								{
									errori.add("Nome prodotto obbligatorio");
								}
								else
								{
									if(manager.doRetrieveByKey(prod_name) == null)
									{
										product.setProductName(prod_name);
									}
									else
									{
										errori.add("Prodotto esistente");
									}
								}
								//Categorie e check
								String[] categorieArray = request.getParameterValues("categorie[]");
								for(String line : categorieArray)
								{
									if(managerCat.doRetrieveByKey(line)!=null)
									{
										BeanCategoria bean = new BeanCategoria();
										bean.setNomeCategoria(line);
										categorie.add(bean);
									}
									else
									{
										errori.add("La categoria " + line + " non esiste");
									}
								}
								product.setCategorie(categorie);
								
								//Specifiche e check
								String[] specificheName = request.getParameterValues("nomeSpecifiche[]");
								String[] specificheDescrizioni = request.getParameterValues("descrSpecifiche[]");
								for(int i = 0; i < specificheName.length; i++)
								{
									BeanSpecificaTecnica specifica = new BeanSpecificaTecnica();
									if(specificheName[i] != null && !specificheName[i].equals(""))
									{
										if(managerSpec.doRetrieveByKey(specificheName[i]) == null)
											errori.add("Spiacente il nome della specifica non esiste");
										
										specifica.setNomeSpecifica(specificheName[i]);
										if(specificheDescrizioni.length > i)
										{
											if(specificheDescrizioni[i] != null && 
													!specificheDescrizioni[i].equals(""))
											{
												specifica.setDescrizioneSpecifica(specificheDescrizioni[i]);
											}
											else
											{
												errori.add("le specifiche devono avere una descrizione");
											}
										}
										else
										{
											errori.add("le specifiche non sono congruenti");
										}
										
									}
									else
									{
										errori.add("le specifiche devono avere un nome");
									}
									specifiche.add(specifica);	
								}
								product.setSpecifiche(specifiche);
								
								//Immagine prodotto
								Part filePart = request.getPart("image");
								if(filePart.getInputStream().available() == 0)
								{
									errori.add("immagine prodotto obbligatoria");
								}
								else
								{
									if(ImageIO.read(filePart.getInputStream()) == null)
									{
										errori.add("Si prega di inserire soltanto immagini");
									}
									else
									{
										if(filePart.getInputStream().available() > 1000000)
										{
											errori.add("La dimensione del file supera 1MB");
										}
										else
										{
											byte[] image = new byte[filePart.getInputStream().available()];
											filePart.getInputStream().read(image);
											product.setImageBytes(image);
										}
									}
						        }
								if(errori.size() == 0)
								{
									manager.doSave(product);
									response.getOutputStream().print("Prodotto aggiunto con successo");
								}
								else
								{
									PrintWriter out = response.getWriter();
									response.setCharacterEncoding("UTF-8");
									for(String err : errori)
									{
										out.append("<label>"+err+"</label><br>");
									}
								}
								break;
							}
							
							case "interno":
							{
								ArrayList<String> errori = new ArrayList<String>();
								ProductInternoModelDM manager = new ProductInternoModelDM(driver);
								//Creo il bean
								BeanProdottoInterno bean = new BeanProdottoInterno();
								
								String nome_Prodotto = request.getParameter("product");
								if(nome_Prodotto == null || nome_Prodotto.equals(""))
								{
									errori.add("Nome prodotto necessario");
								}
								else
								{
									ProductModelDM prodotto = new ProductModelDM(driver);

									if(prodotto.doRetrieveByKey(nome_Prodotto) == null)
										errori.add("Prodotto non esistente");
								}

								String prezzo_string = request.getParameter("prezzo");
								if(prezzo_string == null || prezzo_string.equals(""))
								{
									errori.add("Prezzo prodotto necessario");
								}
								else
								{
									try
									{	
										double prezzo = Double.parseDouble(prezzo_string);
										if(prezzo > 9999.99)
										errori.add("Il prezzo deve essere <= a 9999.99");
										else
											bean.setPrezzo(prezzo);
									}
									catch(NumberFormatException exception) 
									{
										errori.add("Il prezzo deve essere numerico");
									}
								}
								
								String quantity_string = request.getParameter("quantity");
								if(quantity_string == null || quantity_string.equals(""))
								{
									errori.add("Quantity prodotto necessario");
								}
								else
								{
									try
									{
										int quantity = Integer.parseInt(quantity_string);
										bean.setQuantity(quantity);
										if(quantity > 0)
											bean.setAvailability(true);
										else
											bean.setAvailability(false);
									}
									catch(NumberFormatException exception) 
									{
										errori.add("La quantity deve essere numerica");
									}
								}
								String descr = request.getParameter("descrizione");
								if(descr == null || descr.equals(""))
								{
									errori.add("Descrizione necessaria");
								}
								String opzione = request.getParameter("select_opzione");
								if(opzione == null || opzione.equals(""))
								{
									errori.add("Opzione d'acquisto necessaria");
								}
								String cod_ean = request.getParameter("cod_ean");
								if(cod_ean == null || cod_ean.equals(""))
								{
									bean.setCod_ean(null);
								}
								else
								{
									errori.add("Cod ean errato");
								}
								if(errori.size() == 0)
								{
									bean.setProductName(nome_Prodotto);
									bean.setDescr(descr);
									bean.setOpzione_acquisto(opzioniAcquisto.valueOf(opzione));
									bean.setCod_ean(cod_ean);
									manager.doSave(bean);
									response.getOutputStream().print("prodotto aggiunto con successo");
								}
								else
								{
									PrintWriter out = response.getWriter();
									response.setCharacterEncoding("UTF-8");
									for(String err : errori)
									{
										out.append("<label>"+err+"</label><br>");
									}
								}
								break;
							}
							case "fornito":
							{
								ArrayList<String> errori = new ArrayList<String>();
								ProductFornitoModelDM manager = new ProductFornitoModelDM(driver);
								BeanProdottoFornito bean = new BeanProdottoFornito();
								
								String nome_Prodotto = request.getParameter("product");
								if(nome_Prodotto == null || nome_Prodotto.equals(""))
								{
									errori.add("Nome prodotto necessario");
								}
								else
								{
									ProductModelDM prodotto = new ProductModelDM(driver);

									if(prodotto.doRetrieveByKey(nome_Prodotto) == null)
										errori.add("Prodotto non esistente");
								}

								String nome_Store = request.getParameter("store");
								if(nome_Store == null || nome_Store.equals(""))
								{
									errori.add("Nome store necessario");
								}
								else
								{
									StoreModelDM store = new StoreModelDM(driver);
									if(store.doRetrieveByKey(nome_Store) == null)
										errori.add("Store non esistente");
								}
								
								String prezzo_m_string = request.getParameter("prezzo_mese");
								String prezzo_i_string = request.getParameter("prezzo_ieri");
								String prezzo_ig_string = request.getParameter("prezzo_inizio_giorno");
								String prezzo_a_string = request.getParameter("prezzo_attuale");
								if(prezzo_m_string == null || prezzo_m_string.equals("") ||
									prezzo_i_string == null || prezzo_i_string.equals("") ||
									 prezzo_ig_string == null || prezzo_ig_string.equals("") ||
									  prezzo_a_string == null || prezzo_a_string.equals(""))
								{
									errori.add("I prezzi devono essere numerici");
								}
								else
								{
									try
									{
										double prezzo_m = Double.parseDouble(prezzo_m_string);
										double prezzo_i = Double.parseDouble(prezzo_i_string);
										double prezzo_ig = Double.parseDouble(prezzo_ig_string);
										double prezzo_a = Double.parseDouble(prezzo_a_string);
										if(prezzo_m > 9999.99 || prezzo_i > 9999.99 || 
											prezzo_ig > 9999.99 || prezzo_a > 9999.99)
										{
											errori.add("I prezzi non devono superare il range di 9999,99");
										}
										else
										{
											bean.setPrezzoScorsoMese(prezzo_m);
											bean.setPrezzoIeri(prezzo_i);
											bean.setPrezzoAttuale(prezzo_a);
											bean.setPrezzoInizioGiorno(prezzo_ig);
										}
									}
									catch(NumberFormatException exception) 
									{
										errori.add("I prezzi devono essere tutti numerici");
									}
								}
								String quantity_string = request.getParameter("quantity");
								if(quantity_string == null || quantity_string.equals(""))
								{
									errori.add("Quantity prodotto necessario");
								}
								else
								{
									try
									{
										int quantity = Integer.parseInt(quantity_string);
										bean.setQuantity(quantity);
										if(quantity > 0)
											bean.setDisponibile(true);
										else
											bean.setDisponibile(false);
									}
									catch(NumberFormatException exception) 
									{
										errori.add("La quantity deve essere numerica");
									}
								}
								String descr = request.getParameter("descrizione");
								if(descr == null || descr.equals(""))
								{
									errori.add("Descrizione necessaria");
								}
								String opzione = request.getParameter("select_opzione");
								if(opzione == null || opzione.equals(""))
								{
									errori.add("Opzione d'acquisto necessaria");
								}
								String cod_ean = request.getParameter("cod_ean");
								if(cod_ean == null || cod_ean.equals(""))
								{
									bean.setCod_ean(null);
								}
								else
								{
									errori.add("Cod ean errato");
								}
								String costo_spedizione_string = request.getParameter("costo_spedizione");
								if(costo_spedizione_string == null || costo_spedizione_string.equals(""))
								{
									errori.add("Costo_spedizione necessario");
								}
								else
								{
									try
									{
										double costo_spedizione = Double.parseDouble(costo_spedizione_string);
										bean.setCosti_spedizione(costo_spedizione);
									}
									catch(NumberFormatException exception) 
									{
										errori.add("Il costo di spedizione deve essere numerico");
									}
								}
								String link_offerta = request.getParameter("link_offerta");
								if(link_offerta == null || link_offerta.equals(""))
								{
									errori.add("Link offerta necessario");
								}
								
								if(errori.size() == 0)
								{
									//Creo il bean per salvarlo
									bean.setProductName(nome_Prodotto);
									bean.setNameStore(nome_Store);
									bean.setDescrizione(descr);
									bean.setOpzioneDAcquisto(opzioniAcquisto.valueOf(opzione));
									bean.setCod_ean(cod_ean);
									bean.setLink_prodotto_store(link_offerta);
									manager.doSave(bean);
									response.getOutputStream().print("prodotto aggiunto con successo");
								}
								else
								{
									PrintWriter out = response.getWriter();
									response.setCharacterEncoding("UTF-8");
									for(String err : errori)
									{
										out.append("<label>"+err+"</label><br>");
									}
								}
								break;
							}
							default :break;
						}

						break;
					}
					default : break;
					}
				}
				else
				{
					response.getOutputStream().print("Non sono in grado di gestire la risposta");
				}
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			response.getOutputStream().print(e.getMessage());
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
