<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import=
		"java.util.LinkedList 
		,Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM
		,java.sql.SQLException
		,java.util.ArrayList
		,java.util.Collections
		,Model.BeanProdottoFornito
		,Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM
		
		,Model.BeanCategoria
		,Model.BeanSpecificaTecnica 
		,Model.ProductFornitoModelDM
		,java.sql.SQLException,java.util.ArrayList
		,java.util.Collections
		,Model.BeanProdottoFornito
		,Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM
		,com.example.utils.Utils
		,Model.BeanProdottoFornito.opzioniAcquisto	
		
		,java.io.IOException
		,java.sql.SQLException
		,java.util.Base64
		,java.util.Date
		,java.time.LocalDate
		"
%>

<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Base64"%>

<%@page
	import=" java.util.LinkedList 
		,Model.DriverManagerConnectionPool,Model.ProductFornitoModelDM,java.sql.SQLException,
		java.util.ArrayList,java.util.Collections,Model.BeanProdottoFornito, 
		Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM,com.example.utils.Utils
		,Model.BeanCategoria
		,Model.BeanProdottoFornito
		,Model.CategoriaProdottoModelDM
		,Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM
		
		
		
		"%>


 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title> ELOWER_PRICE</title>
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<link href="css/HomePage.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/Header.css">
		<link rel="stylesheet" type="text/css" href="css/Footer.css">
		<link rel='stylesheet' type='text/css' href='css/Navigation.css'>
		<link rel="stylesheet" type="text/css" href="css/PaginaDiRicercaPerCategoriaProdotti.css">
		
		
</head>
</head>
<body>

	<jsp:include page="Navigation.jsp"></jsp:include>


		
	
	<div id="prodottiInterni">
	

	
		<%
		
		int intSizeListTot = 0;
					
		ArrayList<BeanProdottoFornito> listaProdottiPerCategoria = (ArrayList<BeanProdottoFornito>)request.getAttribute("prodottiPerCategoria");
		
		String categoria = "";
		
		categoria = request.getParameter("categoria");
		if( (categoria == null) || (categoria.equals("")))
			categoria = (String)request.getAttribute("categoria");
		if( (categoria == null) || (categoria.equals("")))
		{
			// errore 
			out.print("errore nella ricerca della caterogoria");
		}
			
			
		
		if( listaProdottiPerCategoria == null)
		{
			 			
 			ServletContext ctx = getServletContext();
			DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
			CategoriaProdottoModelDM interfaceDB = new CategoriaProdottoModelDM(driver);
			listaProdottiPerCategoria = (ArrayList<BeanProdottoFornito>) interfaceDB.doRetrieveAll(categoria);
 			
			intSizeListTot = listaProdottiPerCategoria.size();
 			
		}
		
		
		String Npaginacor = (String) request.getAttribute("Npagina");
		String posCorr = (String) request.getAttribute("posCorr");
		String errore = (String) request.getAttribute("stop");
		
		
		String sizeListTot = (String)request.getAttribute("sizeList");
		
		
		
		if( sizeListTot != null)
			intSizeListTot = Integer.parseInt(sizeListTot);
		

		int posStart = 0;
		int start = 0;
		if(posCorr != null)
		{
			start = Integer.parseInt(posCorr);
			posStart = start;
		}
		int pag = 1;
		if(Npaginacor!=null)
			pag = Integer.parseInt(Npaginacor);
		
		int stop = 0;
		if(errore!=null)
			stop= Integer.parseInt(errore);

		int NelementiDaMostr = 3; // dipende dalla media query
		
		%>
		
		<h1> Prodotti categoria : <%= categoria%></h1>
	
		<br>
				
			
 			
		<%	
			
			int sizeList = listaProdottiPerCategoria.size();
		 	
			int i=0;
			
			
		%>
		
		
		<%! String view = ""; %>
		
		
		<div class='prod_view'>
		<%
		
		sizeList = listaProdottiPerCategoria.size();
		i = 0;
		
		BeanProdottoFornito prodottoDaMostrare = null;
		try
		{
			prodottoDaMostrare = listaProdottiPerCategoria.get(i);
		}
		catch(Exception e )
		{
			prodottoDaMostrare = null;
		}
		if(prodottoDaMostrare!= null)
		{		
		prodottoDaMostrare = listaProdottiPerCategoria.get(i);
		String productName = prodottoDaMostrare.getProductName();
		String cod_ean = prodottoDaMostrare.getCod_ean();
		//int count_ricerche = prodottoDaMostrare
		byte[] imageBytes = prodottoDaMostrare.getImageBytes();
		ArrayList<BeanCategoria> categorie = prodottoDaMostrare.getCategorie();
		ArrayList<BeanSpecificaTecnica> specifiche = prodottoDaMostrare.getSpecifiche();
		boolean disponibile = prodottoDaMostrare.isDisponibile();
		String descrizione = prodottoDaMostrare.getDescrizione();
		String nameStore = prodottoDaMostrare.getNameStore();
		opzioniAcquisto opzioneDAcquisto = prodottoDaMostrare.getOpzioneDAcquisto();
		double prezzoAttuale = prodottoDaMostrare.getPrezzoAttuale();
		double prezzoIeri = prodottoDaMostrare.getPrezzoIeri();
		double prezzoInizioGiorno = prodottoDaMostrare.getPrezzoInizioGiorno();
		double prezzoScorsoMese = prodottoDaMostrare.getPrezzoScorsoMese();
		int quantity = prodottoDaMostrare.getQuantity();
		
		//stampiamo l'immagine del prodotto
		Base64.Encoder encoder = Base64.getEncoder(); // Encoding string  
		String str = encoder.encodeToString(imageBytes);
		view = str;
		
		out.print
					(
						"<div>"
						+"<div class='prod_img'>"
							+"<img src='data:image/png;base64,"
			 				+ view + "'"
			 				+" width='200' height='200'/>"
						+"</div>"
							 					
						+"<div class='size_40'>"
							+"<div class='prod_name'>"
								+"<b>Nome: "+ productName +" </b>"
							+"</div>"
							+"<div class='desc_store'>"
								+"<b> Descrizione:"+ descrizione +"</b>" 
							+"</div>"
						+"</div>"
						
						+"<div class='size_20'>"
							+"<div class='nome_store'>"
								+"<b>Nome Store:"+ nameStore +"</b>"
							+"</div>"

						+"</div>"
						
						

					+"<div class = info_prezzi>"
						+"<p class='prezzo_attuale'><b>Prezzo:</b>"+prezzoAttuale+"</p>"
				
					);
						
						
					double costo_spedizione = prodottoDaMostrare.getCosti_spedizione();
					double prezzo_totale = 0;
					if(costo_spedizione > 0)
					{
						
						out.print("<p class='spedizione'>");
						out.print
						(
							"<label id ='costo'><span>Spese Spedizione:</span>"+costo_spedizione+"</label>"
						);
						out.print("</p>");
					
						prezzo_totale= prodottoDaMostrare.getPrezzoAttuale() + costo_spedizione;
						prezzo_totale = BigDecimal.valueOf(prezzo_totale).setScale(2, RoundingMode.HALF_UP).doubleValue();
					}
					else
					{
						out.print("<p class='spedizione'>");
						out.print("<label id ='free'><span>Spezione gratuita</span></label>");
						out.print("</p>");
					
						prezzo_totale= prodottoDaMostrare.getPrezzoAttuale();
					}
					
					
					
					out.print("<p class='prezzo_totale'><span>Prezzo_Totale:</span>"+prezzo_totale+"</p>");
					if(prodottoDaMostrare.isDisponibile() == true)
					{
				
						out.print("<span class='disp'>Disponibilità:</span> <label class='disponibile'></label>");
				
					}
					else
					{
				
						out.print("<span class='disp'>Disponibilità:</span> <label class='non_disponibile'></label>");
					}
					
					 out.print
					(
						"</div>"
					);
					
					out.print
					(
						"<div class='prod_action'>"				
							+"<form action='"+response.encodeURL("ServletMostraProdotto")+"' method='get' target='_blank'>"
								+"<input type='text' name='prodNameAndStoreName' value='"+ productName+"|"+prodottoDaMostrare.getNameStore()+"' style='display:none;'>"
								+"<button onclick ='this.form.submit()'>Visualizza Info</button>"
							+"</form>"
							+"<form action='"+response.encodeURL("ServletRedirect")+"' method='POST' target='_blank'>"
								+"<input type='text' name='link' value='"+ prodottoDaMostrare.getLink_prodotto_store()+"' hidden='hidden'>"
								+"<button onclick='this.form.submit()'>Visualizza Offerta</button>"
							+"</form>"
						+"</div>"
					);
						
					out.print("</div>");
					out.print("</div>");
					
					start += 1;
					
					i++;
		}
					%>
		
		</div>
		
		<br>
		
		
		<div class='prod_view'>
		<%
		
		sizeList = listaProdottiPerCategoria.size();
				
		String view = "";
				
		
		try
		{
			prodottoDaMostrare = listaProdottiPerCategoria.get(i);
		}
		catch(Exception e )
		{
			prodottoDaMostrare = null;
		}
		if(prodottoDaMostrare!= null)
		{
		
			prodottoDaMostrare = listaProdottiPerCategoria.get(i);
			String productName = prodottoDaMostrare.getProductName();
			String cod_ean = prodottoDaMostrare.getCod_ean();
			//int count_ricerche = prodottoDaMostrare
			byte[] imageBytes = prodottoDaMostrare.getImageBytes();
			ArrayList<BeanCategoria> categorie = prodottoDaMostrare.getCategorie();
			ArrayList<BeanSpecificaTecnica> specifiche = prodottoDaMostrare.getSpecifiche();
			boolean disponibile = prodottoDaMostrare.isDisponibile();
			String descrizione = prodottoDaMostrare.getDescrizione();
			String nameStore = prodottoDaMostrare.getNameStore();
			opzioniAcquisto opzioneDAcquisto = prodottoDaMostrare.getOpzioneDAcquisto();
			double prezzoAttuale = prodottoDaMostrare.getPrezzoAttuale();
			double prezzoIeri = prodottoDaMostrare.getPrezzoIeri();
			double prezzoInizioGiorno = prodottoDaMostrare.getPrezzoInizioGiorno();
			double prezzoScorsoMese = prodottoDaMostrare.getPrezzoScorsoMese();
			int quantity = prodottoDaMostrare.getQuantity();
			
			//stampiamo l'immagine del prodotto
			Base64.Encoder encoder = Base64.getEncoder(); // Encoding string  
			String str = encoder.encodeToString(imageBytes);
			view = str;
		
		
		out.print
					(
						"<div>"
						+"<div class='prod_img'>"
							+"<img src='data:image/png;base64,"
			 				+ view + "'"
			 				+" width='200' height='200'/>"
						+"</div>"
							 					
						+"<div class='size_40'>"
							+"<div class='prod_name'>"
								+"<b>Nome: "+ productName +" </b>"
							+"</div>"
							+"<div class='desc_store'>"
								+"<b> Descrizione:"+ descrizione +"</b>" 
							+"</div>"
						+"</div>"
						
						+"<div class='size_20'>"
							+"<div class='nome_store'>"
								+"<b>Nome Store:"+ nameStore +"</b>"
							+"</div>"
							
						+"</div>"
						
						

					+"<div class = info_prezzi>"
						+"<p class='prezzo_attuale'><b>Prezzo:</b>"+prezzoAttuale+"</p>"
				
					);
						
						
					double costo_spedizione = prodottoDaMostrare.getCosti_spedizione();
					double prezzo_totale = 0;
					if(costo_spedizione > 0)
					{
						
						out.print("<p class='spedizione'>");
						out.print
						(
							"<label id ='costo'><span>Spese Spedizione:</span>"+costo_spedizione+"</label>"
						);
						out.print("</p>");
					
						prezzo_totale= prodottoDaMostrare.getPrezzoAttuale() + costo_spedizione;
						prezzo_totale = BigDecimal.valueOf(prezzo_totale).setScale(2, RoundingMode.HALF_UP).doubleValue();
					}
					else
					{
						out.print("<p class='spedizione'>");
						out.print("<label id ='free'><span>Spezione gratuita</span></label>");
						out.print("</p>");
					
						prezzo_totale= prodottoDaMostrare.getPrezzoAttuale();
					}
					
					
					
					out.print("<p class='prezzo_totale'><span>Prezzo_Totale:</span>"+prezzo_totale+"</p>");
					if(prodottoDaMostrare.isDisponibile() == true)
					{
				
						out.print("<span class='disp'>Disponibilità:</span> <label class='disponibile'></label>");
				
					}
					else
					{
				
						out.print("<span class='disp'>Disponibilità:</span> <label class='non_disponibile'></label>");
					}
					
					 out.print
					(
						"</div>"
					);
					
					 out.print
						(
							"<div class='prod_action'>"				
								+"<form action='"+response.encodeURL("ServletMostraProdotto")+"' method='get' target='_blank'>"
									+"<input type='text' name='prodNameAndStoreName' value='"+ productName+"|"+prodottoDaMostrare.getNameStore()+"' style='display:none;'>"
									+"<button onclick ='this.form.submit()'>Visualizza Info</button>"
								+"</form>"
								+"<form action='"+response.encodeURL("ServletRedirect")+"' method='POST' target='_blank'>"
									+"<input type='text' name='link' value='"+ prodottoDaMostrare.getLink_prodotto_store()+"' hidden='hidden'>"
									+"<button onclick='this.form.submit()'>Visualizza Offerta</button>"
								+"</form>"
							+"</div>"
						);
						
					out.print("</div>");
					out.print("</div>");
					
					start += 1;
					
					i++;
			}
					%>
		
		</div>
		
		
		
		<div class='prod_view'>
		<%
		
		sizeList = listaProdottiPerCategoria.size();
				
		view = "";
				
		try
		{
			prodottoDaMostrare = listaProdottiPerCategoria.get(i);
		}
		catch(Exception e )
		{
			prodottoDaMostrare = null;
		}
		if(prodottoDaMostrare!= null)
		{
		
			prodottoDaMostrare = listaProdottiPerCategoria.get(i);
			String productName = prodottoDaMostrare.getProductName();
			String cod_ean = prodottoDaMostrare.getCod_ean();
			//int count_ricerche = prodottoDaMostrare
			byte[] imageBytes = prodottoDaMostrare.getImageBytes();
			ArrayList<BeanCategoria> categorie = prodottoDaMostrare.getCategorie();
			ArrayList<BeanSpecificaTecnica> specifiche = prodottoDaMostrare.getSpecifiche();
			boolean disponibile = prodottoDaMostrare.isDisponibile();
			String descrizione = prodottoDaMostrare.getDescrizione();
			String nameStore = prodottoDaMostrare.getNameStore();
			opzioniAcquisto opzioneDAcquisto = prodottoDaMostrare.getOpzioneDAcquisto();
			double prezzoAttuale = prodottoDaMostrare.getPrezzoAttuale();
			double prezzoIeri = prodottoDaMostrare.getPrezzoIeri();
			double prezzoInizioGiorno = prodottoDaMostrare.getPrezzoInizioGiorno();
			double prezzoScorsoMese = prodottoDaMostrare.getPrezzoScorsoMese();
			int quantity = prodottoDaMostrare.getQuantity();
			
			//stampiamo l'immagine del prodotto
			Base64.Encoder encoder = Base64.getEncoder(); // Encoding string  
			String str = encoder.encodeToString(imageBytes);
			view = str;
		
		
		out.print
					(
						"<div>"
						+"<div class='prod_img'>"
							+"<img src='data:image/png;base64,"
			 				+ str + "'"
			 				+" width='200' height='200'/>"
						+"</div>"
							 					
						+"<div class='size_40'>"
							+"<div class='prod_name'>"
								+"<b>Nome: "+ productName +" </b>"
							+"</div>"
							+"<div class='desc_store'>"
								+"<b> Descrizione:"+ descrizione +"</b>" 
							+"</div>"
						+"</div>"
						
						+"<div class='size_20'>"
							+"<div class='nome_store'>"
								+"<b>Nome Store:"+ nameStore +"</b>"
							+"</div>"
		
						+"</div>"
						
						

					+"<div class = info_prezzi>"
						+"<p class='prezzo_attuale'><b>Prezzo:</b>"+prezzoAttuale+"</p>"
				
					);
						
						
					double costo_spedizione = prodottoDaMostrare.getCosti_spedizione();
					double prezzo_totale = 0;
					if(costo_spedizione > 0)
					{
						
						out.print("<p class='spedizione'>");
						out.print
						(
							"<label id ='costo'><span>Spese Spedizione:</span>"+costo_spedizione+"</label>"
						);
						out.print("</p>");
					
						prezzo_totale= prodottoDaMostrare.getPrezzoAttuale() + costo_spedizione;
						prezzo_totale = BigDecimal.valueOf(prezzo_totale).setScale(2, RoundingMode.HALF_UP).doubleValue();
					}
					else
					{
						out.print("<p class='spedizione'>");
						out.print("<label id ='free'><span>Spezione gratuita</span></label>");
						out.print("</p>");
					
						prezzo_totale= prodottoDaMostrare.getPrezzoAttuale();
					}
					
					
					
					out.print("<p class='prezzo_totale'><span>Prezzo_Totale:</span>"+prezzo_totale+"</p>");
					if(prodottoDaMostrare.isDisponibile() == true)
					{
				
						out.print("<span class='disp'>Disponibilità:</span> <label class='disponibile'></label>");
				
					}
					else
					{
				
						out.print("<span class='disp'>Disponibilità:</span> <label class='non_disponibile'></label>");
					}
					
					 out.print
					(
						"</div>"
					);
					
					 out.print
						(
							"<div class='prod_action'>"				
								+"<form action='"+response.encodeURL("ServletMostraProdotto")+"' method='get' target='_blank'>"
									+"<input type='text' name='prodNameAndStoreName' value='"+ productName+"|"+prodottoDaMostrare.getNameStore()+"' style='display:none;'>"
									+"<button onclick ='this.form.submit()'>Visualizza Info</button>"
								+"</form>"
								+"<form action='"+response.encodeURL("ServletRedirect")+"' method='POST' target='_blank'>"
									+"<input type='text' name='link' value='"+ prodottoDaMostrare.getLink_prodotto_store()+"' hidden='hidden'>"
									+"<button onclick='this.form.submit()'>Visualizza Offerta</button>"
								+"</form>"
							+"</div>"
						);
						
					out.print("</div>");
					out.print("</div>");
					
					start += 1;
					
					i++;
			}
					%>
		
		</div>
		
				
		
		</div>
				
		
		<div id='button_change_div'>
		
		<%
		System.out.print("la posStart è : " + posStart);
		if( posStart > 0) { %>
		<form id='before' action='<%=response.encodeURL("ServletAggiornaProdottiPerCategoriaBefore") %>' method="get">
			<input type="text" name='posCorr' value=<%= posStart %> style="display: none;">
			<%-- <input type="text" name='Npagina'value=<%= pag %> style="display: none;"> --%>
			<input type="text" name='NelementiDaMostr'value=<%=NelementiDaMostr%> style="display: none;">
			<input type="text" name='sizeList' value=<%= sizeList %> style='display: none;' >
			<input type="text" name='change' value='0' style="display: none;">
			
			<input type="text" name='categoria' value='<%= categoria %>'style="display: none;">
			
			<input id='before_submit'type="submit" value="before">
		</form>
		
		<% }
		else
		{
		%>
		
		<button id='before'> before</button>
		
		<%} %>
		
		<% if(posStart + NelementiDaMostr < intSizeListTot){ %>
		<form id='next' action='<%=response.encodeURL("ServletAggiornaProdottiPerCategoriaNext") %>' method="get">
			<input type="text" name='posCorr' value=<%= posStart %> style="display: none;">
			<%-- <input type="text" name='Npagina'value=<%= pag %> style="display: none;"> --%>
			<input type="text" name='NelementiDaMostr'value=<%=NelementiDaMostr%> style="display: none;">
			<input type="text" name='sizeList' value=<%= sizeList %> style='display: none;' >
			<input type="text" name='change' value='1'style="display: none;">
			
			<input type="text" name='categoria' value='<%= categoria %>'style="display: none;">
			
			<input id='next_submit' type="submit" value="next">
		</form>
		<%}
		else
		{
		%>
		
		<button id='next'> next</button>
					
		<%}%> 
		
		</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>



</body>
</html>



 
 
 








