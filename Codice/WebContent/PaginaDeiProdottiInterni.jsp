<%@page import="Model.ProductInternoModelDM"%>
<%@page import="Model.BeanProdottoInterno"%>
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
		,Model.ProductInternoModelDM
		,com.example.utils.Utils
		,Model.BeanProdottoFornito.opzioniAcquisto	
		
		,java.io.IOException
		,java.sql.SQLException
		,java.util.Base64
		"
		
		%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
		<title> ELOWER_PRICE</title>
		<link href="css/HomePage.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/Footer.css">
		<link rel="stylesheet" type="text/css" href="css/PaginaDeiProdottiInterni.css">
		<link rel='stylesheet' type='text/css' href='css/Navigation.css'>
</head>
</head>
<body>
	<jsp:include page="Navigation.jsp"></jsp:include>


		
	
	<div id="prodottiInterni">
	<h1> I nostri prodotti : </h1>
	
		<br>
	
		<%
			
		ArrayList<BeanProdottoInterno> listaProdottiInterni = (ArrayList<BeanProdottoInterno>)request.getAttribute("listaProdottiInterni");
		
		int intSizeListTot = 0;
		
		if( listaProdottiInterni == null)
		{
			ServletContext ctx = getServletContext();
 			DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
 			ProductInternoModelDM interfaceDB = new ProductInternoModelDM(driver);
 			listaProdottiInterni = (ArrayList<BeanProdottoInterno>) interfaceDB.doRetrieveAll("prodotto_name");
 			intSizeListTot = listaProdottiInterni.size();
 			
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
			
		
	

		int NelementiDaMostr = 3;
		
		%>
		
				
			
 			
		<%		
			int sizeList = listaProdottiInterni.size();
		 	
 				int i=0;				
				
				BeanProdottoInterno prodottoDaMostrare = null;
				String productName = "";
				String cod_ean = "";
				byte[] imageBytes = null;
				ArrayList<BeanCategoria> categorie = null;
				ArrayList<BeanSpecificaTecnica> specifiche = null;
				boolean disponibile = false;
				String descrizione = "";
				opzioniAcquisto opzioneDAcquisto = null;
				double prezzoAttuale = 0;
				int quantity = 0;

				String categorieDaStampare = "";
	
				Base64.Encoder encoder = Base64.getEncoder();  
				String str;
				
				
		
		%>
				
		
		<div class='prod_view'>
		
		<%
		
		String view = "";
		
		try
		{
			prodottoDaMostrare = listaProdottiInterni.get(i);
		}
		catch(Exception e )
		{
			prodottoDaMostrare = null;
		}
		if(prodottoDaMostrare!= null)
		{
		productName = prodottoDaMostrare.getProductName();
		cod_ean = prodottoDaMostrare.getCod_ean();
		imageBytes = prodottoDaMostrare.getImageBytes();
		categorie = prodottoDaMostrare.getCategorie();
		specifiche = prodottoDaMostrare.getSpecifiche();
		disponibile = prodottoDaMostrare.isAvailability();
		descrizione = prodottoDaMostrare.getDescr();
		opzioneDAcquisto = prodottoDaMostrare.getOpzione_acquisto();
		prezzoAttuale = prodottoDaMostrare.getPrezzo();
		quantity = prodottoDaMostrare.getQuantity();
		encoder = Base64.getEncoder();  
		str = encoder.encodeToString(imageBytes);
		
		
		
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
						
						+"</div>" 
						
						

					+"<div class = info_prezzi>"
						+"<p class='prezzo_attuale'><b>Prezzo:</b>"+prezzoAttuale+"</p>"
				
					);
										
					if(prodottoDaMostrare.isAvailability() == true)
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
					
					+"<form name='formVaiAllaPaginaDelProdottoInterno' action='"+response.encodeURL("ServletMostraProdottoInterno")+"' method='get'>"
						+"<input type='text' name='prodName' value='"+productName+"' style='display: none;'>"
						+"<button onclick='this.form.submit();'> Visualizza info </button>"
					+"</form>"
					);
					
					if(quantity > 0)
					{
						out.print
					 	(
					 	
					 	"<form name='formCarrello' action='"+response.encodeURL("ServletAggiungiAlCarrello")+"' method='get' target='_blank'>"
					 	+"<input type='text' name='prodottoDaAggiungereAlCarrello' value='"+productName+"'"+"style='display:none;'>"
					 	+"<button onclick='this.form.submit();'> Aggiungi al carrello</button>"
					 	+"</form>"
					 	);
					}
					out.print("</div>");
					
					
					
					out.print("</div>");
					out.print("</div>");
					
					
					
					%>
				
					<%
					
					start += 1;
					
					i++;
		}
			%>
		 
		</div>
		
		<br>
			
		
		<div class='prod_view'>
		
		<%
		
		view = "";
		
		try
		{
			prodottoDaMostrare = listaProdottiInterni.get(i);
		}
		catch(Exception e )
		{
			prodottoDaMostrare = null;
		}
		if(prodottoDaMostrare!= null)
		{
		productName = prodottoDaMostrare.getProductName();
		cod_ean = prodottoDaMostrare.getCod_ean();
		imageBytes = prodottoDaMostrare.getImageBytes();
		categorie = prodottoDaMostrare.getCategorie();
		specifiche = prodottoDaMostrare.getSpecifiche();
		disponibile = prodottoDaMostrare.isAvailability();
		descrizione = prodottoDaMostrare.getDescr();
		opzioneDAcquisto = prodottoDaMostrare.getOpzione_acquisto();
		prezzoAttuale = prodottoDaMostrare.getPrezzo();
		quantity = prodottoDaMostrare.getQuantity();
		encoder = Base64.getEncoder();  
		str = encoder.encodeToString(imageBytes);
		
		
		
			out.print
					(
						"<div// Encoding string  >"
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
						
						+"</div>" 
						
						

					+"<div class = info_prezzi>"
						+"<p class='prezzo_attuale'><b>Prezzo:</b>"+prezzoAttuale+"</p>"
				
					);
						
					if(prodottoDaMostrare.isAvailability() == true)
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
						
						+"<form name='formVaiAllaPaginaDelProdottoInterno' action='"+response.encodeURL("ServletMostraProdottoInterno")+"' method='get'>"
							+"<input type='text' name='prodName' value='"+productName+"' style='display: none;'>"
							+"<button onclick='this.form.submit();'> Visualizza info </button>"
						+"</form>"
						);
						
						if(quantity > 0)
						{
							out.print
						 	(
						 	
						 	"<form name='formCarrello' action='"+response.encodeURL("ServletAggiungiAlCarrello")+"' method='get' target='_blank'>"
						 	+"<input type='text' name='prodottoDaAggiungereAlCarrello' value='"+productName+"'"+"style='display:none;'>"
						 	+"<button onclick='this.form.submit();'> Aggiungi al carrello</button>"
						 	+"</form>"
						 	);
						}
						out.print("</div>");
						
						
						
					out.print("</div>");
					out.print("</div>");
						
						
						
				%>

				<%	
					start += 1;
					
					i++;
		
		}
			%>
		 
		</div>
		
		
		<br>
		
		<div class='prod_view'>
		
		
		<%
		
		view = "";
		/* i = start; */
		
		try
		{
			prodottoDaMostrare = listaProdottiInterni.get(i);
		}
		catch(Exception e )
		{
			prodottoDaMostrare = null;
		}
		if(prodottoDaMostrare!= null)
		{
		productName = prodottoDaMostrare.getProductName();
		cod_ean = prodottoDaMostrare.getCod_ean();
		imageBytes = prodottoDaMostrare.getImageBytes();
		categorie = prodottoDaMostrare.getCategorie();
		specifiche = prodottoDaMostrare.getSpecifiche();
		disponibile = prodottoDaMostrare.isAvailability();
		descrizione = prodottoDaMostrare.getDescr();
		opzioneDAcquisto = prodottoDaMostrare.getOpzione_acquisto();
		prezzoAttuale = prodottoDaMostrare.getPrezzo();
		quantity = prodottoDaMostrare.getQuantity();
		encoder = Base64.getEncoder();   
		str = encoder.encodeToString(imageBytes);
		
		
		
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
						
						+"</div>" 
						
						

					+"<div class = info_prezzi>"
						+"<p class='prezzo_attuale'><b>Prezzo:</b>"+prezzoAttuale+"</p>"
				
					);
						
						
					
					if(prodottoDaMostrare.isAvailability() == true)
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
						
						+"<form name='formVaiAllaPaginaDelProdottoInterno' action='"+response.encodeURL("ServletMostraProdottoInterno")+"' method='get'>"
							+"<input type='text' name='prodName' value='"+productName+"' style='display: none;'>"
							+"<button onclick='this.form.submit();'> Visualizza info </button>"
						+"</form>"
						);
						
						if(quantity > 0)
						{
							out.print
						 	(
						 	
						 	"<form name='formCarrello' action='"+response.encodeURL("ServletAggiungiAlCarrello")+"' method='get' target='_blank'>"
						 	+"<input type='text' name='prodottoDaAggiungereAlCarrello' value='"+productName+"'"+"style='display:none;'>"
						 	+"<button onclick='this.form.submit();'> Aggiungi al carrello</button>"
						 	+"</form>"
						 	);
						}
						out.print("</div>");
						
						
						
					out.print("</div>");
					out.print("</div>");
					
					start += 1;
					
					i++;
		}
			%>
		 
		</div>
		
		<div id='button_change_div'>
		
		
		
		<%
		System.out.print("la posStart è : " + posStart);
		if( posStart > 0) { %>
		<form id='before' action='<%=response.encodeURL("ServletAggiornaProdottiInterniBefore") %>' method="get">
			<input type="text" name='posCorr' value=<%= posStart %> style="display: none;">
			<%-- <input type="text" name='Npagina'value=<%= pag %> style="display: none;"> --%>
			<input type="text" name='NelementiDaMostr'value=<%=NelementiDaMostr%> style="display: none;">
			<input type="text" name='sizeList' value=<%= sizeList %> style='display: none;' >
			<input type="text" name='change' value='0' style="display: none;">
			<input id='before_submit'type="submit" value="before">
		</form>
		<% }
		else
		{
		%>
		
		<button id='before'> before</button>
		
		<%} %>
		
		<%if(posStart + NelementiDaMostr < intSizeListTot) 
		{%> 
		<form id='next' action='<%=response.encodeURL("ServletAggiornaProdottiInterniNext") %>' method="get">
			<input type="text" name='posCorr' value=<%= posStart %> style="display: none;">
			<%-- <input type="text" name='Npagina'value=<%= pag %> style="display: none;"> --%>
			<input type="text" name='NelementiDaMostr'value=<%=NelementiDaMostr%> style="display: none;">
			<input type="text" name='sizeList' value=<%= sizeList %> style='display: none;' >
			<input type="text" name='change' value='1'style="display: none;">
			<input id='next_submit' type="submit" value="next">
		</form>
		 <%}
		else
		{
		%>
		
		<button id='next'> next</button>
					
		<%}%> 
			
		
		</div>
		
		
		</div>
		
		
		<jsp:include page="Footer.jsp"></jsp:include>



</body>
</html>



 
 
 