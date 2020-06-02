<%@page import="Model.BeanStore"%>
<%@page import="Model.StoreModelDM"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page
	import=" java.util.LinkedList 
		,Model.DriverManagerConnectionPool,Model.ProductFornitoModelDM,java.sql.SQLException,
		java.util.ArrayList,java.util.Collections,Model.BeanProdottoFornito, 
		Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM,com.example.utils.Utils"%>

<%
	String error = (String) request.getAttribute("error");	
	ArrayList<BeanProdottoFornito> prodotti = null;
	int size = 0;
	int num_pagine = 0;
	int pag_gen= 1;
	if(error == null)
	{
		prodotti = (ArrayList<BeanProdottoFornito>) request.getAttribute("prodottiTrovati");
		size = prodotti.size();
		num_pagine = size / 5;
		if((size % 5)>0)
			num_pagine+=1;
	}
	String paginaPassata = (String)request.getAttribute("pagina");
	int paginaCorrente = 1;
	if(paginaPassata != null)
	{
		paginaCorrente = Integer.parseInt(paginaPassata);
		int divisione = paginaCorrente / 10;
		int resto = paginaCorrente % 10;
		if(divisione > 0)
		{
			if(resto > 0)
			{
				pag_gen = ((divisione) * 10) + 1;
			}
			else
			{
				pag_gen = paginaCorrente-9;
			}
		}
	}
	String pageCercata = request.getParameter("prodottoPerNome");
	ServletContext ctx = getServletContext();
	DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver");
	StoreModelDM storeModel = new StoreModelDM(driver); 
%>
<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="UTF-8"/>
<title>CERCA_PRODOTTI_ELOWER_PRICE</title>
<link rel="stylesheet" href="css/PaginaDiRicercaProdotti.css"/>
<link rel='stylesheet' type='text/css' href='css/Navigation.css'>
<link rel="stylesheet" type="text/css" href="css/Footer.css"/>
<script type="text/javascript" src="javascript/paginaDiRicercaProdottiFunction.js"></script>
<script src="javascript/jquery.js"></script>
<style>
	#page<%=paginaCorrente%>
	{
		background-color: #2d3a4e;
    	border: 3px solid #F44336;
    	border-radius: 15px;
    	font-size: 17px;
    	color: white;
	}
</style>
	<% if(prodotti != null)
		{
			if(prodotti.size() == 0)
			{
	%>
				<style>
					footer
					{
						position: fixed;
   						left: 0;
   						bottom: 0;
   						width: 100%;
   						height: auto;
					}
				</style>
	<%			
			}
		}
	%>
</head>

<body>
		<jsp:include page="Navigation.jsp"></jsp:include>
		<input id="current" type="hidden" value="<%=paginaCorrente%>">
		<%
		if(prodotti != null)
		{
			if(prodotti.size() == 0)
			{
				out.print("<h1>Nessun prodotto trovato.</h1>");
			}
			else
			{	
		%> 	
			<div class="lista">
				<div class="result_num">
					<img class='icon' src="icone/iconResultSearch.svg">
					<span class="label"><%=prodotti.size()%> risultati trovati.</span>
				</div>
				<ul class ="prodotti">
					<%
						int pagCount = ((paginaCorrente-1) * 5);
						int size2 = size - pagCount;
						int i = 0;
						for(; size2 > 0 && i < 5;i++,size2--)
						{
							BeanProdottoFornito p = prodotti.get(i+pagCount);
							byte imgImage [] = p.getImageBytes();
							String str="";
							if(imgImage!= null)
							{
								Base64.Encoder encoder = Base64.getEncoder();  
								// Encoding string  
					        	str = encoder.encodeToString(imgImage);
							}
							
							BeanStore store = storeModel.doRetrieveByKey(p.getNameStore());
							imgImage = store.getImageBytes();
							String imageStore="";
							if(imgImage!= null)
							{
								Base64.Encoder encoder = Base64.getEncoder();  
								// Encoding string  
					        	imageStore = encoder.encodeToString(imgImage);
							}
					%>
						<li id="prod<%=i+1%>" class="list_item">
							<div class="prod_img">
								<img src="data:image/png;base64,<%=str%>" width="150" height="150"/>
							</div>
							<div class="size_40">
								<div class="prod_name">
									<b>Nome: </b><%=p.getProductName()%>
								</div>
								<div class="desc_store">
									<b>Descrizione: </b><%=p.getDescrizione() %>
								</div>
							</div>
							<div class="size_20">
								<div class="nome_store">
									<b>Nome Store: </b><%=p.getNameStore() %>
								</div>
								<div class="image_store">
								<%
									if(imageStore.length() > 0)
									{
								%>
										<b>Image Store: </b>
										<img src="data:image/png;base64,<%=imageStore%>" width="150" height="150"/>
								<%
									}
									else
									{
								%>
									<div class = "img_store">
										<b>Image Store: </b>
										<label class='titleImgStore'>Non presente</label>
									</div>
								<%
									}
								%>
								</div>
							</div>
							<div class = info_prezzi>
								<p class="prezzo_attuale"><b>Prezzo: &euro;</b><%=p.getPrezzoAttuale()%></p>
								<p class="spedizione">
									<%
									double costo_spedizione = p.getCosti_spedizione();
									double prezzo_totale = 0;
									if(costo_spedizione > 0)
									{
									%>
										<label id ="costo"><b>Spese Spedizione: </b>&euro;<%=costo_spedizione%></label>
									<%
										prezzo_totale= p.getPrezzoAttuale() + costo_spedizione;
										prezzo_totale = BigDecimal.valueOf(prezzo_totale).setScale(2, RoundingMode.HALF_UP).doubleValue();
									}
									else
									{
									%>
										<label id ="free"><b>Spezione gratuita</b></label>
									<%
										prezzo_totale= p.getPrezzoAttuale();
									}
									%>
								</p>
								<p class="prezzo_totale"><b>Prezzo Totale: &euro;</b><%=prezzo_totale %></p>
								<%
									if(p.isDisponibile() == true)
									{
								%>
										<b class='titleDisp'>Disponibilità:</b> <div class="disponibile"></div>
								<%
									}
									else
									{
								%>
										<b class='titleDisp'>Disponibilità:</b> <div class="non_disponibile"></div>
								<%
									}
								%>
							</div>
							<div class="prod_action">
								<form action='<%= response.encodeURL("ServletMostraProdotto") %>' method="POST" target="_blank">
									<input type='text' name="prodNameAndStoreName" value="<%=pageCercata+"|"+p.getNameStore()%>" style="display:none;">
									<button onclick ="this.form.submit()">Visualizza Info</button>
								</form>
								<% HttpSession sessionCorr = request.getSession(false);
								if(sessionCorr.getAttribute("log") == null)
								{
								%>
								<form action='<%= response.encodeURL("ServletRedirect") %>' method="POST" target="_blank">
									<input type='text' name="link" value='' hidden="hidden">
									<button type='button' onclick="showAlert()">Visualizza Offerta</button>
								</form>
								<%	
								}
								else
								{
								%>
								<form action='<%= response.encodeURL("ServletRedirect") %>' method="POST" target="_blank">
									<input type='text' name="link" value='<%=p.getLink_prodotto_store()%>' hidden="hidden">
									<button onclick="this.form.submit()">Visualizza Offerta</button>
								</form>
							<%	}%>
							</div>
						</li>
				<%	
					}
					
				%>
			</ul>			
			</div>
			<div class="buttons_page">
				<b>Pagine: </b>
			<%
				if(paginaCorrente > 1)
				{
			%>
				<form class="prevPage" action="ServletCercaProdotto" method="post">
					<input type='text' name="currentPage" value="<%=paginaCorrente-1%>" style="display:none;">
					<input type='text' name="prodottoPerNome" value="<%=pageCercata%>" style="display:none;">
					<button class="button_next" onclick="this.form.submit()">Indietro</button> 
				</form>
			<%
				}
			%>
		<% 
					int num_pagine_rimanenti = 0;
					int temp_page_gen = pag_gen+10;
					
					for(; pag_gen < num_pagine+1 && pag_gen < temp_page_gen; pag_gen++)
					{
		%>				
						<form class="FormPage" action="ServletCercaProdotto" method="post">
							<input type='text' name="currentPage" value="<%=pag_gen%>" style="display:none;">
							<input type='text' name="prodottoPerNome" value="<%=pageCercata%>" style="display:none;">
							<button class="button_page" id="page<%=pag_gen %>" onclick="this.form.submit()"><%=pag_gen %></button>
						</form>
		<%
					}
					if(paginaCorrente < num_pagine)
					{	
		%>
					<form class="nextPage" action="ServletCercaProdotto" method="post">
						<input type='text' name="currentPage" value="<%=paginaCorrente+1%>" style="display:none;">
						<input type='text' name="prodottoPerNome" value="<%=pageCercata%>" style="display:none;">
						<button class="button_next" onclick="this.form.submit()">Avanti</button> 
					</form>
		<%			
					} 
		
		%>
			</div>
		<%
					
			}
		}
		else
		{
		%>
		<% 
			out.print("<p>"+error+"</p>"); 
		%>
		<%
		}
		%>
		<jsp:include page="Footer.jsp"></jsp:include>
</body>

</html>