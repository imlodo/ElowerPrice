<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		,Model.ProductFornitoModelDM,java.sql.SQLException,java.util.ArrayList,java.util.Collections,Model.BeanProdottoFornito, Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM, com.example.utils.Utils
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
		<title> ELOWER_PRICE</title>
		
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<link href="css/testHomePageResponsive.css" rel="stylesheet">
		<link rel='stylesheet' type='text/css' href='css/Navigation.css'>
		<link rel="stylesheet" type="text/css" href="css/Footer.css">
		<link rel='stylesheet' type='text/css' href='css/testHomePageResponsive.css'>
		
		<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
		<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<script type="text/javascript" src="javascript/genericsFunction.js"></script>
		
		
		
	</head>

	
<body onload="init()">
	<jsp:include page="Navigation.jsp"></jsp:include>
	<div>
	<div class="dropdown">
  		<button class="dropbtn">Categorie</button>
  		<div class="dropdown-content">
  		<form name="categoria3" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="tablet"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="tablet">
		</form>
  		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="informatica"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="informatica">
		</form>
  		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="notebook"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="notebook">
		</form>
  		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="console videogiochi"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="console videogiochi">
		</form>
  		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="scarpe"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="scarpe">
		</form>
  		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="smartphone"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="smartphone">
		</form>
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="fotocamere"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="fotocamere">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="t-shirt"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="t-shirt">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="condizionatori"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="condizionatori">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="hard-disk"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="hard-disk">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="monitor"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="monitor">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="router"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="router">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="mouse"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="mouse">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="strumenti a corde"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="strumenti a corde">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="strumenti musicali"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="strumenti musicali">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="felpe"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="felpe">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="cappelli"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="cappelli">
		</form>
<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="leggins"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="leggins">
		</form>
  		</div>
		</div>
	<div id="asideHomePage">
		
		categorie:
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria3" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="tablet"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="tablet">
		</form>
		</div>
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="informatica"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="informatica">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="notebook"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="notebook">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="console videogiochi"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="console videogiochi">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="scarpe"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="scarpe">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="smartphone"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="smartphone">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="fotocamere"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="fotocamere">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="t-shirt"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="t-shirt">
		</form>
		</div>
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="condizionatori"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="condizionatori">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="hard-disk"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="hard-disk">
		</form>
		</div>
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="monitor"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="monitor">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="router"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="router">
		</form>
		</div>
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="mouse"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="mouse">
		</form>
		</div>
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="strumenti a corde"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="strumenti a corde">
		</form>
		</div>
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="strumenti musicali"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="strumenti musicali">
		</form>
		</div>
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="felpe"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="felpe">
		</form>
		</div>
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="cappelli"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="cappelli">
		</form>
		</div>
		
		
		<div class="itemAside" class="zoom">
		<form name="categoria4" action='<%= response.encodeURL("PaginaDiRicercaPerCategoriaProdotti.jsp") %>' method="get">
			<input type='text' name="categoria" value="leggins"  style="display:none;" >
			<input type="submit" class="buttonNOBorder" value="leggins">
		</form>
		</div>
		
	</div>
	
	</div>
		
	<div id="section2">
		
		<h1> TROVA E CONFRONTA I MIGLIORI PREZZI ONLINE! </h1>
				 	
		<div id="art">
			
		<h2>Ribassi del giorno: </h2>
		<br>
		
		
		<%
		ArrayList<BeanProdottoFornito> listaDeiRibassiDelGiorno = null;
		ServletContext ctxRibassi = getServletContext();
		listaDeiRibassiDelGiorno = (ArrayList<BeanProdottoFornito>)ctxRibassi.getAttribute("listaRibassiCorrenti");
		int NelementiDaMostr = 3; // dipende dalla media query
		int sizeList = 0;
		String start = "";
		if(listaDeiRibassiDelGiorno == null)
		{
			
			listaDeiRibassiDelGiorno = new ArrayList<BeanProdottoFornito>();
			ArrayList<BeanProdottoFornito> collection = null; 
			
			ServletContext ctx = getServletContext();
			DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
			ProductFornitoModelDM manager = new ProductFornitoModelDM(driver);
			
			collection = (ArrayList<BeanProdottoFornito>) manager.doRetrieveAll("prodotto_name");
			listaDeiRibassiDelGiorno = Utils.ribassiDelGiorno(collection);
			Collections.sort( listaDeiRibassiDelGiorno , BeanProdottoFornito.Comparators.prezzoAttuale);
		 		
			ctxRibassi.setAttribute("listaRibassiCorrenti", listaDeiRibassiDelGiorno);
			start = "0";
			
		}
		
		sizeList = listaDeiRibassiDelGiorno.size();
		
		
		
		%>
		
	
		<input type="text" id="sizeList" value ='<%= listaDeiRibassiDelGiorno.size() %>' style="display:none;">
		<input type="text" id='posCorr' value='0' style="display:none;">
		<input type="text" id='n_el_da_mostare' value='3'style="display:none;">
		
		<input type="button" id='before' value='before' onclick="backRibassi()"> 	
		
			
		
		<div id="prod1" class="ProdottiHomePage">
		
		<form class="display_img" name="formVaiAllaPaginaDelProdotto1" action='<%=response.encodeURL("ServletMostraProdotto") %>' method="get">
			<input id ="prodNameAndStoreName1" type="text" style="display:none;" name='prodNameAndStoreName' value=''>
			<button class="display_img" id="buttonimg1"onclick="this.form.submit();" >
				<img id="img1" src=''  /> 
			</button>
		</form>
		
		<div class="info_prod">
			<div>
			<span id=prodProdName1></span><br>
			</div>
			
			<div>
			<span id=prodStoreName1></span><br>
			</div>
			
			<div>
			<span id=prezzoIeri1></span><br>
			</div>
			
			<div>
			<span id=prezzoAttuale1></span><br>
			</div>
		</div>
		
		</div>
		
		<div id="prod2" class="ProdottiHomePage">
		
		<form class="display_img" name="formVaiAllaPaginaDelProdotto1" action='<%=response.encodeURL("ServletMostraProdotto") %>' method="get">
			<input id ="prodNameAndStoreName2" type="text" style="display:none;" name='prodNameAndStoreName' value=''>
			<button class="display_img" id="buttonimg2"onclick="this.form.submit();"> 
				<img id="img2" src=''  width='200px' height='200px'/>
			</button>
		</form>
		
		<div class="info_prod">
			
			<div>
			<span id=prodProdName2></span><br>
			</div>
			
			<div>
			<span id=prodStoreName2></span><br>
			</div>
			
			<div>
			<span id=prezzoIeri2></span><br>
			</div>
			
			<div>
			<span id=prezzoAttuale2></span><br>
			</div>
		</div>
		
		</div>
		
		<div id="prod3" class="ProdottiHomePage">
		
		<form class="display_img" name="formVaiAllaPaginaDelProdotto1" action='<%=response.encodeURL("ServletMostraProdotto") %>' method="get">
			<input id ="prodNameAndStoreName3" type="text" style="display:none;" name='prodNameAndStoreName' value=''>
			<button class="display_img" id="buttonimg3"onclick="this.form.submit();"> 
				<img id="img3" src=''  width='200px' height='200px'/>
			</button>
		</form>
		
		<div class="info_prod">
			<div>
			<span id=prodProdName3></span><br>
			</div>
			
			<div>
			<span id=prodStoreName3></span><br>
			</div>
			
			<div>
			<span id=prezzoIeri3></span><br>
			</div>
			
			<div>
			<span id=prezzoAttuale3></span><br>
			</div>
		</div>
		
		</div>
		
		<input type="button" id='next' value='next' onclick="nextRibassi()" >
		
		
		
		</div>	
		 	
		<br><br>
					
		<div id="artPiuCercati">
		
		<h2> Prodotti più cercati: </h2>
		
		
		<%
		ArrayList<BeanProdottoFornito> listaPiuCercati = null;
		ServletContext ctxPiuCercati = getServletContext();
		listaPiuCercati = (ArrayList<BeanProdottoFornito>)ctxPiuCercati.getAttribute("listaPiuCercati");
		NelementiDaMostr = 3; // dipende dalla media query
		 sizeList = 0;
		
		if(listaPiuCercati == null)
		{
			
			listaPiuCercati = new ArrayList<BeanProdottoFornito>();
			
			ServletContext ctx = getServletContext();
			DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
			ProductFornitoModelDM manager = new ProductFornitoModelDM(driver);
			listaPiuCercati = (ArrayList<BeanProdottoFornito>) manager.doRetrieveAll("prodotto_name");
			
			Collections.sort( listaPiuCercati , BeanProdottoFornito.Comparators.piuCercati);
				
			ctxPiuCercati.setAttribute("listaPiuCercati", listaPiuCercati);
			start = "0";
			
		}
		
		sizeList = listaPiuCercati.size();
		
		
		
		
		%>				
		<input type="text" id="sizeListPiuCercati" value ='<%= listaPiuCercati.size() %>' style="display:none;">
			
		<input type="text" id='posCorrPiuCercati' value='0' style="display:none;">
		
		<input type="button" id='beforePiuCercati' value='before' onclick="backPiuCercati()">
		
		<div id="prodPiuCercati1">
		
		<form class="display_img" name="formVaiAllaPaginaDelProdotto1" action='<%=response.encodeURL("ServletMostraProdotto") %>' method="get">
			<input id ="prodNameAndStoreNamePiuCercati1" type="text" style="display:none;" name='prodNameAndStoreName' value=''>
			<button class="display_img" id="buttonimgPiuCercati1"onclick="this.form.submit();">
				<img id="imgPiuCercati1" src=''  width='200px' height='200px'/>
			</button>
		</form>
		
		<div class="info_prod">
			<div>
			<span id=prodPiuCercatiProdName1></span><br>
			</div>
			
			<div>
			<span id=prodPiuCercatiStoreName1></span><br>
			</div>
			
			<div>
			<span id=prezzoIeriPiuCercati1></span><br>
			</div>
			
			<div>
			<span id=prezzoAttualePiuCercati1></span><br>
			</div>
		</div>
		
		</div>
				
		<div id="prodPiuCercati2">
		
		<form class="display_img" name="formVaiAllaPaginaDelProdotto1" action='<%=response.encodeURL("ServletMostraProdotto") %>' method="get">
			<input id ="prodNameAndStoreNamePiuCercati2" type="text" style="display:none;" name='prodNameAndStoreName' value=''>
			<button class="display_img" id="buttonimgPiuCercati2"onclick="this.form.submit();"> 
				<img id="imgPiuCercati2" src=''  width='200px' height='200px'/>
			</button>
		</form>
		
		<div class="info_prod">
			
			<div>
			<span id=prodPiuCercatiProdName2></span><br>
			</div>
			
			<div>
			<span id=prodPiuCercatiStoreName2></span><br>
			</div>
			
			<div>
			<span id=prezzoIeriPiuCercati2></span><br>
			</div>
			
			<div>
			<span id=prezzoAttualePiuCercati2></span><br>
			</div>
		</div>
		
		</div>
		
		<div id="prodPiuCercati3">
		
		<form class="display_img" name="formVaiAllaPaginaDelProdotto1" action='<%=response.encodeURL("ServletMostraProdotto") %>' method="get">
			<input id ="prodNameAndStoreNamePiuCercati3" type="text" style="display:none;" name='prodNameAndStoreName' value=''>
			<button class="display_img" id="buttonimgPiuCercati3"onclick="this.form.submit();"> 
				<img id="imgPiuCercati3" src=''  width='200px' height='200px'/>
			</button>
		</form>
		<div class="info_prod">
			
			<div>
			<span id=prodPiuCercatiProdName3></span><br>
			</div>
			
			<div>
			<span id=prodPiuCercatiStoreName3></span><br>
			</div>
			
			<div>		
			<span id=prezzoIeriPiuCercati3></span><br>
			</div>
			
			<div>
			<span id=prezzoAttualePiuCercati3></span><br>
			</div>
		</div>
		
		
		</div>
			
		
		<input type="button" id='nextPiuCercati' value='next' onclick="nextPiuCercati()" >	
			
		
		</div>	
			
			
			
				
		</div>
		
		 
		
		<jsp:include page="Footer.jsp"></jsp:include>
				 
		
	<script type="text/javascript">
	window.onload= init;	
	</script>
	
	
	</body>
	
</html>
