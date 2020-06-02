<%@page import="Model.DriverManagerConnectionPool"%>
<%@page import="com.example.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Pannello Amministrazione</title>
	<link rel="stylesheet" href="css/PaginaDellAdmin.css">
	<script src="javascript/jquery.js"></script>
	<script type="text/javascript" src="javascript/adminFunction.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body >
	<script> 
		jQuery(document).ready(function($) 
		{
			checkPage();
			window.onhashchange = checkPage;
		});
	</script>
	<% 
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver");
		if(Utils.checkAdminSession(request.getSession(),driver,request.getCookies()) == false)
		{
			response.sendRedirect(response.encodeURL("HomePage.jsp"));
		}
		String esit = (String) request.getAttribute("esito");
		String cssRead = (String) request.getAttribute("css");
		if(esit != null)
		{
	%>
			<label id="esito"><%=esit%></label>
	<%
		}
		if(cssRead != null)
		{
	%>
			
			<textarea id="cssView" class='field' name="cssP" rows="15"><%=cssRead%></textarea>
	<%
		}
	%>
	<input type="text" id="url" value="<%=response.encodeURL("")%>" hidden="true">
	<div class="logo">
			<a href=<%=response.encodeURL("PaginaDellAdmin.jsp")%>><img id="logo" src="immagini/logo2.svg" onclick=""/></a>
	</div>
	<div class='operationTop'>
		<div class="opTopF" id='opDB' onclick="insertOpDB()">
			<img src='icone/iconDB.svg' class='iconAdmin' />
			<label>Operazioni DB</label>
		</div>
		<div class="opTopF2" onclick="insertmodificaCSS()">
			<img src='icone/iconCSS.svg' class='iconAdmin' />
			<label>Modifica CSS</label>
		</div>
		<div class="opTopF" onclick="redirectToHomepage()" >
			<img src='icone/iconHomePage.svg' class='iconAdmin' />
			<label>Homepage</label>
		</div>
		<div class="opTopF2" onclick="logOut()">
			<img src='icone/iconLogout.svg' class='iconAdmin' />
			<label>Logout</label>
		</div>
	</div>
	<div id='bodyArea'>
	<nav>	
		<div id="op">
			<label class='title'>Menu operazioni DB</label>
			<div class='figlioOP' onclick='subOpUserManager()' 
			onmouseover="document.getElementById('gU').src='icone/iconGestioneUtentiBlack.svg'"
			onmouseout="document.getElementById('gU').src='icone/iconGestioneUtenti.svg'"> 
				<img id='gU' src='icone/iconGestioneUtenti.svg' class='iconAdminF'/>
				<label>Gestione Utenti</label> 
			</div>
			<div class='figlioOP' onclick='subOpProdottiManager()'
			onmouseover="document.getElementById('gP').src='icone/iconGestioneProdottiBlack.svg'"
			onmouseout="document.getElementById('gP').src='icone/iconGestioneProdotti.svg'"> 
		 		<img id='gP' src='icone/iconGestioneProdotti.svg' class='iconAdminF' />
		 		<label>Gestione Prodotti</label> 
		 	</div>
		 	<div class='figlioOP' onclick='subOpStoreManager()'
		 	onmouseover="document.getElementById('gS').src='icone/iconGestioneStoreBlack.svg'"
			onmouseout="document.getElementById('gS').src='icone/iconGestioneStore.svg'"> 
		 		<img id='gS' src='icone/iconGestioneStore.svg' class='iconAdminF' />
		 		<label>Gestione Store</label> 
		 	</div>
		 	<div class='figlioOP' onclick='sendviewAcquisti()'
		 	onmouseover="document.getElementById('gA').src='icone/iconGestioneAcquistiBlack.svg'"
			onmouseout="document.getElementById('gA').src='icone/iconGestioneAcquisti.svg'">  
		 		<img id='gA' src='icone/iconGestioneAcquisti.svg' class='iconAdminF' />
		 		<label>Gestione Acquisti</label>  
		 	</div> 
		</div>
		
		<div id="op2">
			<label class='title'>Menu modifica CSS</label>
			<div class='figlioOP' onclick="createEditCss('HomePage')">
				<label>HomePage</label>
			</div>
			<div class='figlioOP' onclick="createEditCss('PaginaDiLogin')">
				<label>Login</label>
			</div>
			<div class='figlioOP' onclick="createEditCss('PaginaDiAreaPersonaleUtente')">		
				<label>Area Personale Utente</label>
			</div>
			<div class='figlioOP' onclick="createEditCss('PaginaDiMoficheDatiUtente')">		
				<label> Dati Utente </label>
			</div>
			<div class='figlioOP' onclick="createEditCss('PaginaDelProdotto')">		
				<label>Prodotto</label>
			</div>
			<div class='figlioOP' onclick="createEditCss('PaginaDiRicercaProdotti')">
			<label>Ricerca Prodotti</label>
			</div>
			<div class='figlioOP' onclick="createEditCss('PaginaDelCarrello')">
				<label>Carrello</label>
			</div>
		</div>
		
		<div id="subOp">
			<label class='title'>Menu Gestione Utenti</label>
			<div class='figlioSubOP' onclick='sendviewUser()'
			onmouseover="document.getElementById('vU').src='icone/iconViewUserBlack.svg'"
			onmouseout="document.getElementById('vU').src='icone/iconViewUser.svg'">
				<img id='vU' src='icone/iconViewUser.svg' class='iconAdmin' /> 
				<label>Visualizza Utenti</label>  
	 		</div> 
	 		<div class='figlioSubOP' onclick='createUser(), window.location.href="#panelCreateUser"' 
	 		onmouseover="document.getElementById('aU').src='icone/iconAddUserBlack.svg'"
			onmouseout="document.getElementById('aU').src='icone/iconAddUser.svg'">
	 			<img id='aU' src='icone/iconAddUser.svg' class='iconAdmin' />  
				<label>Aggiungi Utente</label> 
			</div> 
		</div>
		
		<div id="subOp2">
			<label class='title'>Menu Gestione Prodotti</label>
			<div class='figlioSubOP' onclick='createviewProdotto(), sendviewProdotto()'
			onmouseover="document.getElementById('vP').src='icone/iconViewProductBlack.svg'"
			onmouseout="document.getElementById('vP').src='icone/iconViewProduct.svg'">
				<img id='vP' src='icone/iconViewProduct.svg' class='iconAdmin' />  
				<label>Visualizza Prodotti</label>  
			</div> 
			<div class='figlioSubOP' onclick='createAddProduct()'
			onmouseover="document.getElementById('aP').src='icone/iconAddProductBlack.svg'"
			onmouseout="document.getElementById('aP').src='icone/iconAddProduct.svg'"> 
				<img id='aP' src='icone/iconAddProduct.svg' class='iconAdmin' />  
				<label>Aggiungi Prodotto</label> 
			</div>  
		</div>
		
		<div id="subOp3">
			<label class='title'>Menu Gestione Store</label>
			<div class='figlioSubOP' onclick='sendviewStore()'
			onmouseover="document.getElementById('vS').src='icone/iconViewBlack.svg'"
			onmouseout="document.getElementById('vS').src='icone/iconView.svg'"> 
				<img id='vS' src='icone/iconView.svg' class='iconAdmin' />  
				<label>Visualizza Stores</label>  
			</div>  
			<div class='figlioSubOP' onclick='createAddStore()'
				onmouseover="document.getElementById('aS').src='icone/iconAddStoreBlack.svg'"
				onmouseout="document.getElementById('aS').src='icone/iconAddStore.svg'">
				<img id='aS' src='icone/iconAddStore.svg' class='iconAdmin' />  
				<label >Aggiungi Store</label> 
			</div>  
		</div>
	</nav>
	<div id="contentOP"></div>
	</div>
</body>
</html>