<%@page import="com.example.utils.Utils"%>
<%@page import="Model.DriverManagerConnectionPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ServletContext ctx = getServletContext();
	DriverManagerConnectionPool driver = (DriverManagerConnectionPool) ctx.getAttribute("driver");
	if(Utils.checkAdminSession(request.getSession(),driver,request.getCookies()) == false)
	{
		response.sendRedirect(response.encodeURL("HomePage.jsp"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="UTF-8">
<title>Aggiungi un nuovo prodotto</title>
<script src="javascript/jquery.js"></script>
<script type="text/javascript" src="javascript/addProduct.js"></script>
<script type="text/javascript" src="javascript/adminFunction.js"></script>
<link rel='stylesheet' type='text/css' href='css/AddProduct.css'>
<link rel='stylesheet' type='text/css' href='css/PaginaDellAdmin.css'>
</head>
<body>
	<input type="text" id="url" value="<%=response.encodeURL("")%>" hidden="true">
	<div id='add_product'> 
		<div id='error'></div>	
		<div id="content_Prodotti">
		<label class="l" for='form_add_product'>Aggiungi Prodotto</label>
		<form id='form_add_product'>
			<div id="formAPContent">
			<input type='text' name='operation' value='Prodotti' hidden="true"> 
			<input type='text' name='tipo_prodotto' value='normal' hidden="true">
			<label>Nome prodotto</label> 
			<input class='field' type='text' name='prod_name' placeholder='Adidas Felpa' autocomplete='off' required> 
			<br>
    		<div id='categ'>
    		<label>Seleziona Categoria</label>
    		<input  class='field' id='1categ' type='text' list='selectCategoria1' name='categorie[]' placeholder='Felpe' required>
			<datalist id='selectCategoria1'>
			</datalist>
			<button type='button' class="add_categoria"> 
      			<span style="font-size:16px; font-weight:bold;">+ </span>
    		</button>
    		</div>
			<div id='specifiche'>
			<label>Seleziona Specifica</label>
			<input class='field' id='1spec' type='text' list='selectSpecifica1' name='nomeSpecifiche[]' placeholder='Fodera' autocomplete='off' required>
			<datalist id='selectSpecifica1'>
			</datalist>
			<button type='button' class="add_specifiche"> 
      			<span style="font-size:16px; font-weight:bold;">+ </span>
    		</button>
    		<br>
    		<label>Descrizione Specifica</label>
    		<textarea class='field' name="descrSpecifiche[]" placeholder='....'></textarea>
    		</div>
			<label>Carica Logo </label>
			<input  class='field' type='file' name='image' id='image_send' accept='.png, .jpg, .jpeg' required/>
			<br> 
			<input id ='normSend' type='submit'>
			</div>
		</form> 
		</div>
	</div>
</body>
</html>