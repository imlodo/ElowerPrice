<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script>

 	$(function() {
    $( "#testo" ).autocomplete({
    	//maxShowItems: 4,
        source: ajaxCall()
    });
 	});

	function ajaxCall()
	{
		var arr = new Array();
		$.getJSON("ServletResponseJSON",
		function(json) 
		{
			/* var objects = JSON.parse(json);  */
			var value = "";   		
			var i = 0;
			for(var key in json) 
			{
				value = json[key];
				arr[i] = value;
				i++;
			}
				/* document.getElementById("prova").innerHTML = value; */
			});
			return arr;        
	}
</script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div id='navigation'>
	<div id='logo'>
		<a href=<%=response.encodeURL("HomePage.jsp")%>><img class='fixLogo' src="immagini/logo3.svg"></a>
	</div>
	<%
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver"); 
		boolean isAdmin = Utils.checkAdminSession(request.getSession(),driver,request.getCookies());
	%>
	<%
		if(isAdmin == true)
		{
	%>
		<div id='searchBarAdmin'>
			<form name="formCercaPerNomeProdotto" action='<%=response.encodeURL("ServletCercaProdotto") %>'>
				<input type="text" id='testo' name='prodottoPerNome' class='textSearch' placeholder="Cerca prodotto per nome">
				<button type="submit"><i class="fa fa-search"></i></button>
			</form>
		</div>
		<div id='optionMenuAdmin'>
		<div class='elMenuAdmin'>
           <a href=<%=response.encodeURL("PaginaDeiProdottiInterni.jsp") %> >
				<img class='image' src="icone/iconINostriProdotti.svg">
				<label class='labelMenu'>I nostri prodotti</label>
			</a>
        </div>
	<%
		}
		else
		{
	%>
        <div id='searchBar'>
			<form name="formCercaPerNomeProdotto" action=<%=response.encodeURL("ServletCercaProdotto") %>>
				<input type="text" id='testo' name='prodottoPerNome' class='textSearch' placeholder="Cerca prodotto per nome">
				<button type="submit"><i class="fa fa-search"></i></button>
			</form>
		</div>
		<div id='optionMenu'>
			<div class='elMenu'>
           		<a href=<%=response.encodeURL("PaginaDeiProdottiInterni.jsp") %> >
					<img class='image' src="icone/iconINostriProdotti.svg">
					<label class='labelMenu'>I nostri prodotti</label>
				</a>
        	</div>
      <% 
      	}
      %>  
      <%
			HttpSession sessionCorr = request.getSession(false);
			if (sessionCorr.getAttribute("log") == null)
			{
				out.print
				(
					"<div class='elMenu'><a href='"+response.encodeURL("PaginaDiLogin.jsp")+"'>"+
					"<img class='image' src='icone/iconAccedi.svg'><label class='labelMenu'>Accedi</label></a></div>"+ 
					"<div class='elMenu'><a href='"+response.encodeURL("PaginaDiIscrizione.jsp")+"'>"+
					"<img class='image' src='icone/iconIscriviti.svg'><label class='labelMenu'>Iscriviti</label></a></div>"
				);
		%>
				<div class='elMenu'><a href=<%=response.encodeURL("PaginaDelCarrello.jsp") %>><img class='image' src='icone/iconCarrello.svg'><label class='labelMenu'>Carrello</label></a></div>	
		<% 
			}
			else
			{
				if(isAdmin == true)
				{
					out.print("<div class='elMenuAdmin'>"+
								"<a href='"+response.encodeURL("Logout")+"'>"+
								"<img class='image' src='icone/iconLogOutHomePage.svg'>"+
				 				"<label class='labelMenu'>Esci</label></a>"+
						 		"</div>"
							+"<div class='elMenuAdmin'><a href='"+response.encodeURL("PaginaDellAreaUtente.jsp")+"'><img class='image' src='icone/iconUserArea.svg'><label class='labelMenu'>Area personale</label></a></div>"
							+"<div class='elMenuAdmin'>"+
							"<a href='"+response.encodeURL("PaginaDellAdmin.jsp")+"'>"+
							"<img class='image' src='icone/iconAdminArea.svg'>"+
			 				"<label class='labelMenu'>Area Admin</label></a>"+
					 		"</div>"
					);
		%>
					<div class='elMenuAdmin'><a href=<%=response.encodeURL("PaginaDelCarrello.jsp") %>><img class='image' src='icone/iconCarrello.svg'><label class='labelMenu'>Carrello</label></a></div>			
		<%
				}
				else
				{
					out.print("<div class='elMenu'>"+
							"<a href='"+response.encodeURL("Logout")+"'>"+
							"<img class='image' src='icone/iconLogOutHomePage.svg'>"+
				 			"<label class='labelMenu'>Esci</label></a>"+
						 "</div>"
							+"<div class='elMenu'><a href='"+response.encodeURL("PaginaDellAreaUtente.jsp")+"'><img class='image' src='icone/iconUserArea.svg'><label class='labelMenu'>Area personale</label></a></div>"
					);
		%>    
       				<div class='elMenu'><a href=<%=response.encodeURL("PaginaDelCarrello.jsp") %>><img class='image' src='icone/iconCarrello.svg'><label class='labelMenu'>Carrello</label></a></div>
    	<% 		} 
    		}
    	%>
    	</div>
    </div>
</div>
