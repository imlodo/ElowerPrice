<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.BeanProdottoInterno"%>
<%@page import="java.util.ArrayList"%>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta charset="UTF-8">
	<title> CARRELLO </title>
	<link rel="stylesheet" href="css/PaginaDelCarrello.css">
</head>

<body>
		
	<a href=<%=response.encodeURL("HomePage.jsp")%>><img src="immagini/logo.svg" onclick="" width="100%" height="200px"/></a>
	
	<br>
	
	<div id="acquisti">
	<h1> Lista dei prodotti: </h1>
	<%
	
	double totale = 0.0;
	
	HttpSession sessioneUtente = request.getSession(false);
	if(sessioneUtente!= null)
	{
		ArrayList<BeanProdottoInterno> listaProdottiNelCarrello = (ArrayList<BeanProdottoInterno>) sessioneUtente.getAttribute("listaProdottiNelCarrello");
		if(listaProdottiNelCarrello==null)
		{
			out.print("non ci sono prodotti nel carrello");
		}
		else
		{
						
				if(listaProdottiNelCarrello.size() > 0)
				{
				out.print("<div class='acquisti'>");
				/* for(int i = 0 ; (i < listaProdottiNelCarrello.size() ) && (aggiunto==false) ; i++) */
				for(int i = 0 ; i < listaProdottiNelCarrello.size() ; i++)
				{
					String productName = listaProdottiNelCarrello.get(i).getProductName();
					double prezzoProdottoInterno = listaProdottiNelCarrello.get(i).getPrezzo();
					totale += prezzoProdottoInterno;
					byte[] imageBytes = listaProdottiNelCarrello.get(i).getImageBytes();
					Base64.Encoder encoder = Base64.getEncoder(); // Encoding string  
					String str = encoder.encodeToString(imageBytes);
					out.print
					(
					
					"<div class='itemAcquisto'>"
						+"<div id='img_prod_carrello'>"
							+"<form id='form_button_img'name='formVaiAllaPaginaDelProdottoInterno' action='"+response.encodeURL("ServletMostraProdottoInterno")+"' method='get' >"
								+"<input type='text' name='prodName' value='"+productName+"' style='display:none;' >"
								+"<button id='button_img' onclick='this.form.submit();'> <img id='img' src='data:image/png;base64,"+ str +"' /> </button>"
							+"</form>"
						+"</div>"
						
						+"<div id='div_info_prod'>"
						
							+"<div id='name_prod_carrello' value=''>"+productName+"</div>"
							+"<div id='prezzo_prod_carrello' >"+"\u20AC"+prezzoProdottoInterno+"</div>"
							
							+"<div id='remove_button'>"
							+"<form name='formRimuviDalCarrello' action='"+response.encodeURL("ServletRimuoviDalCarrello")+"' method='get'>"
								+"<input type='text' name='prodName' value='"+productName+"' style='display:none;' >"
								+"<button class='button'  value='remove' onclick='this.form.submit();' >remove</button>"
							+"</form>"
						+"</div>"
						
						+"</div>"
						
					+"</div>"
					);	
				}
				out.print("</div>");
				
				}
				else
				{
					out.print("NON CI SONO PRODOTTI NEL CARRELLO");
				}
				
		}
	}
	else
	{
		out.print("sessione scaduta");
	}
	
	%>
	
	</div>
	
	
	<div id="barralaterale">
	
	
	<div id="right_content">
	<%DecimalFormat df2 = new DecimalFormat("#.##"); %>
		<h1 style="display:inline;"> Totale : </h1>
		<!-- <br> -->
		<div>
			<span style="font-size: 1.5em;"> &#8364 <%= df2.format(totale) %> </span>
		</div>
		
		<div class="div_barralaterale_resize">
			<a href=<%=response.encodeURL("") %>><button class="button_barralaterale">procedi al pagamento</button></a>
		</div>	
		
		<div class="div_barralaterale_resize">
			<a href=<%=response.encodeURL("PaginaDeiProdottiInterni.jsp") %>><button class="button_barralaterale">continua con gli acquisti</button></a>
		</div>	
		
		<div class="div_barralaterale_resize">
			<a href=<%=response.encodeURL("HomePage.jsp") %>><button class="button_barralaterale">vai alla pagina iniziale</button></a>
		</div>
	</div>
	
	</div>	
	

</body>

</html>