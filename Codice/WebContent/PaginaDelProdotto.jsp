<%@page import="Model.BeanRecensioneProdotto"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>

<%@page import="Model.BeanProdottoInterno"%>
<%@page import=
		" java.util.LinkedList
		, Model.BeanCategoria
		, Model.BeanSpecificaTecnica 
		,Model.DriverManagerConnectionPool,Model.ProductFornitoModelDM,java.sql.SQLException,java.util.ArrayList,java.util.Collections,Model.BeanProdottoFornito, Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM, com.example.utils.Utils
		, Model.BeanProdottoFornito.opzioniAcquisto	
		,Model.ProductInternoModelDM
"%>
<%@page import="java.util.Base64, javax.servlet.ServletOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
<!DOCTYPE html>
<html>

<head>
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta charset="UTF-8">
	<title>Pagina del prodotto</title>
	<link rel="stylesheet" href="css/PaginaDelProdotto.css">
	<link rel="stylesheet" type="text/css" href="css/Header.css">
	<link rel="stylesheet" type="text/css" href="css/Footer.css">
	<link rel='stylesheet' type='text/css' href='css/Navigation.css'>
</head>

<body>
		
	<jsp:include page="Navigation.jsp"></jsp:include>
	
	<%
	ArrayList<BeanRecensioneProdotto> recensioni = (ArrayList<BeanRecensioneProdotto>) request.getAttribute("recensioni");
	BeanProdottoFornito prodottoDaMostrare = null;
	
	prodottoDaMostrare = (BeanProdottoFornito)request.getAttribute("prodottoTrovato");
	
	String errore = (String)request.getAttribute("errore");
	if( prodottoDaMostrare != null ) 
	{
		
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
		
		Base64.Encoder encoder = Base64.getEncoder(); // Encoding string  
		String str = encoder.encodeToString(imageBytes);
		out.print
		(
			"<div class='img_info_left'>"
				+"<img id='img_prod'src='data:image/png;base64,"
				+ str + "'"
				+"/>"
				+"<div class='info_left'>"
				
						
		);
		
		
		
		
		
		out.print("<div><span class='info_prod_prec'>Nome prodotto: </span><span class='info_prod'>"+productName+"</span></div>");out.print("<br>");
		
		if(cod_ean != null)
		{
			out.print("<div><span class='info_prod_prec'>Codice ean: </span><span  class='info_prod'>"+cod_ean+"</span></div>");out.print("<br>");
		}
				
		if(disponibile == true)
		{
			out.print("<div><span class='info_prod_prec'>Disponibilità: </span><span  class='info_prod'>Prodotto disponibile</span></div>");
		}
		else
		{
			out.print("<div><span class='info_prod_prec'>Disponibilità: </span><span  class='info_prod'>Prodotto non disponibile</span></div>");
		}
		out.print("<br>");
			
		out.print("<div><span class='info_prod_prec'>Nome store: </span><span  class='info_prod'>"+nameStore+"</span></div>");out.print("<br>");
		out.print("<div><span class='info_prod_prec'>Opzione di acquisto: </span><span  class='info_prod'>"+opzioneDAcquisto.name()+"</span></div>");out.print("<br>");
		out.print("<div><span class='info_prod_prec'>Prezzo inizio del giorno: </span><span  class='info_prod'>€"+prezzoInizioGiorno+"</span></div>");out.print("<br>");
		out.print("<div><span class='info_prod_prec'>Prezzo attuale: </span><span  class='info_prod'>€"+prezzoAttuale+"</span></div>");out.print("<br>"); 
		out.print("<div><span class='info_prod_prec'>Prezzo ieri: </span><span  class='info_prod'>€"+prezzoIeri+"</span></div>");out.print("<br>"); 
		out.print("<div><span class='info_prod_prec'>Prezzo scorso mese: </span><span  class='info_prod'>€"+prezzoScorsoMese+"</span></div>");out.print("<br>");
		out.print("<div><span class='info_prod_prec'>N° di prodotti disponibili: </span><span  class='info_prod'>"+quantity+"</span></div>");out.print("<br>");
		
		out.print("</div>");
		
		out.print("</div>");
		
		out.print("<div style='margin-bottom: -0.5%;'>");
		out.print("<div class='info_bottom'>");
		String categorieDaStampare = "";
		if(categorie!= null)
		{
			
			for(BeanCategoria c : categorie)
				categorieDaStampare += c.getNomeCategoria() + " ";
			
			out.print("<div class='info_prod_prec'>Categorie: </div><div class='info_prod'>"+categorieDaStampare+" </div>");
		}
		out.print("<br>");
		out.print("<div><span class='info_prod_prec'>Descrizione prodotto: </span><span class='info_prod'>"+descrizione+"</span></div>");out.print("<br>");
		
		out.print("</div>");
		
		out.print("</div>");
		
		if(recensioni != null && recensioni.size() > 0)
		{
			out.print("<div class='info_bottom' style='margin-top:1%;margin-bottom:-0.5%'>");
			out.print("<div class='recensioni'><span class='info_prod_prec'>Recensioni</span>");
			for(BeanRecensioneProdotto r : recensioni)
			{
				String username_utente = r.getUtente_username();
				String nome = r.getProdotto_name();
				String commento = r.getCommento();
				int num_stell = r.getNum_stelle();
				out.print("<div class='username_utente'><span class='info_prod_prec'>Autore: <span class='info_prod'>"+username_utente+"</span></div>");
				out.print("<div class='recensione_name'><span class='info_prod_prec'>Nome prodotto: <span class='info_prod'>"+nome+"</span></div>");
				out.print("<div class='commento'><span class='info_prod_prec'>Commento: <textarea disabled class='textCommento'>"+commento+"</textarea></div>");
				out.print("<div class='numeroStelle'><span class='info_prod_prec'>Numero stelle: <span class='info_prod'>"+num_stell+"</span></div>");
			}
		out.print("</div>");
		out.print("</div>");
		}
	}
		
	
	if(prodottoDaMostrare == null)
	{
		BeanProdottoInterno prodottoInterno = null;
		prodottoInterno = (BeanProdottoInterno) request.getAttribute("prodottoInternoTrovato");
		
		errore = (String)request.getAttribute("errore");
		
		if( prodottoInterno != null ) 
		{
				
				
					int i = 0;
					BeanProdottoInterno prodottoInternoDaMostrare = prodottoInterno;
					String productName = prodottoInternoDaMostrare.getProductName();
					String cod_ean = prodottoInternoDaMostrare.getCod_ean();
					byte[] imageBytes = prodottoInternoDaMostrare.getImageBytes();
					ArrayList<BeanCategoria> categorie = prodottoInternoDaMostrare.getCategorie();
					ArrayList<BeanSpecificaTecnica> specifiche = prodottoInternoDaMostrare.getSpecifiche();
					boolean disponibile = prodottoInternoDaMostrare.isAvailability();
					String descrizione = prodottoInternoDaMostrare.getDescr();
					opzioniAcquisto opzioneDAcquisto = prodottoInternoDaMostrare.getOpzione_acquisto();
					double prezzoAttuale = prodottoInternoDaMostrare.getPrezzo();
					int quantity = prodottoInternoDaMostrare.getQuantity();
					
					Base64.Encoder encoder = Base64.getEncoder(); 
					String str = encoder.encodeToString(imageBytes);

				 out.print
					(
						"<div class='img_info_left'>"
							+"<img id='img_prod'src='data:image/png;base64,"
							+ str + "'"
							//+" width='700px' height='700px' style='float: left;'/>"
							+"/>"
							+"<div class='info_left'>"
							
									
					);
					
					
					
					
					
					out.print("<span class='info_prod_prec'>Nome prodotto: </span><span class='info_prod'>"+productName+"</span>");out.print("<br>");
					
					if(cod_ean != null)
					{
						out.print("<span class='info_prod_prec'>Codice ean: </span><span  class='info_prod'>"+cod_ean+"</span>");out.print("<br>");
					}
					
					//stampa le categorie
					
					if(disponibile == true)
					{
						out.print("<span class='info_prod_prec'>Disponibilità: </span><span  class='info_prod'>Prodotto disponibile</span>");
					}
					else
					{
						out.print("<span class='info_prod_prec'>Disponibilità: </span><span  class='info_prod'>Prodotto non disponibile</span>");
					}
					out.print("<br>");
					
					out.print("<span class='info_prod_prec'>Opzione di acquisto: </span><span  class='info_prod'>"+opzioneDAcquisto.name()+"</span>");out.print("<br>");
					
					out.print("<span class='info_prod_prec'>Prezzo: </span><span  class='info_prod'>€"+prezzoAttuale+"</span>");out.print("<br>");
					
					out.print("<span class='info_prod_prec'>N° di prodotti disponibili: </span><span  class='info_prod'>"+quantity+"</span>");out.print("<br>");
					
					if(quantity > 0)
					{
						out.print
					 	(
					 	"<br>"
					 	+"<form name='formCarrello' action='"+response.encodeURL("ServletAggiungiAlCarrello")+"' method='get'>"
					 	+"<input type='text' name='prodottoDaAggiungereAlCarrello' value='"+productName+"'"+"style='display:none;'>"
					 	+"<input type='button' value='aggiungi al carrello'  onclick='this.form.submit();'>"
					 	+"</form>"
					 	);
					}
					
					out.print("</div>");
					
					out.print("</div>");
					
					out.print("<div style='margin-bottom: -0.5%;'>");
					out.print("<div class='info_bottom'>");
					String categorieDaStampare = "";
					if(categorie!= null)
					{
						for(BeanCategoria c : categorie)
							categorieDaStampare += c.getNomeCategoria() + " ";
						
						if(!categorieDaStampare.equals(""))
							out.print("<div class='info_prod_prec'>Categorie: </div><div class='info_prod'>"+categorieDaStampare+" </div>");
						else
							out.print("<div class='info_prod_prec'>Il prodotto non apparitiene a nessuna catagoria</div>");
						
					}
					out.print("<br>");
					out.print("<span class='info_prod_prec'>Descrizione prodotto: </span><span class='info_prod'>"+descrizione+"</span>");out.print("<br>");
					
					out.print("</div>");
					
					out.print("</div>");
								
				
			
		}
		else
		{
			out.print("<h1> prodotto  non trovato </h1>"); 
		}
		
		
		
	}
		
	
	%>	
	
	<br>
	
	<jsp:include page="Footer.jsp"></jsp:include>


</body>

</html>