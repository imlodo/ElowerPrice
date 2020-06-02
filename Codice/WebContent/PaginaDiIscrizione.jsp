<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Iscriviti</title>
	<link rel="stylesheet" href="css/PaginaDiInscrizione.css" id='css1'>
	<script src="javascript/jquery.js"></script>
	<script type="text/javascript" src="javascript/genericsFunction.js"></script>

</head>
<body>
	<%
		String error = (String) request.getAttribute("error");
		if(error != null)
		{
	%>
			<h1 id="error_reg"><%=error%></h1>
	<%
		}
	%>
	<input type="text" id="url" value="<%=response.encodeURL("")%>" hidden="true">
	<div id="error">
	</div>
	<div id="panel" align="center">
		<img id="logo" src="immagini/logo2.svg" onclick=""/>
		<div id="titolo">Iscriviti</div>
 		<form id='reg'>
 			<div id='regnome'>
 				<input type="text" class="field" name="nome"
 				placeholder="Nome"
 				required 
 				onchange="checkName(this.form.nome)"
 				onkeyup="checkName(this.form.nome)"
 				>
 				<img id='checkTrueName' src="icone/iconOk.svg">
 				<img id='checkFalseName' src="icone/iconError.svg">
 			</div>
 			<div id='regcognome'>
 				<input type="text" class="field" name="cognome"
 				placeholder="Cognome"
 				required
 				onchange="checkSurname(this.form.cognome)"
 				onkeyup="checkSurname(this.form.cognome)"
 				>
 				<img id='checkTrueSurname' src="icone/iconOk.svg">
 				<img id='checkFalseSurname' src="icone/iconError.svg">
 			</div>
 		 	<div id='regcf'>
 				<input type="text" class="field" name="codf"
 				placeholder="Codice Fiscale"
 				required
 				onchange="checkCF(this.form.codf)"
 				onkeyup="checkCF(this.form.codf)"
 				>
 				<img id='checkTrueCF' src="icone/iconOk.svg">
 				<img id='checkFalseCF' src="icone/iconError.svg">
 			</div>
 			<div id="regemail">
 				<input type="email" class="field" name="email"
 				placeholder="Email"
 				required
 				onchange="checkEmail(this.form.email)"
 				onkeyup="checkEmail(this.form.email)"
 				>
 				<img id='checkTrueEmail' src="icone/iconOk.svg">
 				<img id='checkFalseEmail' src="icone/iconError.svg">
 			</div>
 			<div id="reguser" >
				<input type="text" class="field" name="username"
		    	placeholder="Username"
        		required
				onchange="checkUsername(this.form.username)"
				onkeyup="checkUsername(this.form.username)"
				>
				<img id='checkTrueUsername' src="icone/iconOk.svg">
				<img id='checkFalseUsername' src="icone/iconError.svg">
			</div>
 		
 			<div id='regpw'>
 				<input type="password" class="field" name="password"
 				placeholder="Password"
 				autocomplete="false"
 				required
 				onchange="checkPassword(this.form.password)"
 				onkeyup="checkPassword(this.form.password)" 
 				>
 				<img id='checkTruePassword' src="icone/iconOk.svg">
 				<img id='checkFalsePassword' src="icone/iconError.svg">
 			</div>
 		
 			<div id='regconfpw'>
 				<input type="password" class="field" name="password2"
 				placeholder="Conferma Password"
 				autocomplete="false"
 				required
 				onchange="checkPasswordConf(this.form.password2)"
 				onkeyup="checkPasswordConf(this.form.password2)" 
 				>
 				<img id='checkTruePassword2' src="icone/iconOk.svg">
 				<img id='checkFalsePassword2' src="icone/iconError.svg">
 			</div>
 			<button id='btnConf' class='btn' type="button" value="Conferma" onclick="validateForm(this.form)">
 				Conferma
 			</button>
 				<a href=<%=response.encodeURL("HomePage.jsp") %>>
 				 	<button id='btnAnn' type="button" class="btn2" value="Annulla">Annulla</button>
 				</a>
 		</form>
	</div>
</body>	
</html>