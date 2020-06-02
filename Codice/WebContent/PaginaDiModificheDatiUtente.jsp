<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Model.UserModelDM"%>
<%@page import=
		" java.util.LinkedList 
		,Model.DriverManagerConnectionPool,Model.ProductFornitoModelDM,java.sql.SQLException,java.util.ArrayList,java.util.Collections,Model.BeanProdottoFornito, Model.DriverManagerConnectionPool
		,Model.ProductFornitoModelDM, com.example.utils.Utils
		, Model.UserModelDM
		, Model.BeanUtente		
"%>    

    
    
    
<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta charset="UTF-8">
	<title>PAGINA_DELL_AREA_UTENTE</title>
	<meta charset="UTF-8">
	<title>Iscriviti</title>
	<link rel="stylesheet" href="css/PaginaDiInscrizione.css" id='css1'>
	<script src="javascript/jquery.js"></script>
	<script type="text/javascript" src="javascript/genericsFunction.js"></script>

</head>

<body>
	
	<%
	HttpSession sessioneCorrente = request.getSession(false);
	if(sessioneCorrente.getAttribute("log") == null )
	{
		out.print("errore : sessione scaduta");
	}
	else
	{	
		
		ServletContext ctx = getServletContext();
		DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
		UserModelDM interfaceDB = new UserModelDM(driver);
		
		
		
		String usernameSession = (String)sessioneCorrente.getAttribute("username");
		if(usernameSession!= null)
		{
			BeanUtente beanUtenteCorrente = interfaceDB.doRetrieveByKey(usernameSession);
			if( beanUtenteCorrente != null)
			{
				String username = beanUtenteCorrente.getUsername();
				String email = beanUtenteCorrente.getEmail();
				String cf = beanUtenteCorrente.getCf();
				String name = beanUtenteCorrente.getName();
				String surname = beanUtenteCorrente.getSurname();
				String password = beanUtenteCorrente.getPassword();
				
		%>
		<div id="panel" align="center">
			<img id="logo" src="immagini/logo2.svg" onclick=""/>
			<div id="titolo">Modifica campi</div>
				<form name='formModificaDatiUtente' action=<%=response.encodeURL("ServletModificheDatiUtente")%>  method='post'>
	 			<div id='regnome'>
	 				<input type="text" class="field" name="nome"
	 				placeholder="Nome"
	 				required 
	 				value='<%= name %>'
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
	 				value='<%= surname %>'
	 				onchange="checkSurname(this.form.cognome)"
	 				onkeyup="checkSurname(this.form.cognome)"
	 				>
	 				<img id='checkTrueSurname' src="icone/iconOk.svg">
	 				<img id='checkFalseSurname' src="icone/iconError.svg">
	 			</div>
	 		 	<div id='regcf'>
	 				<input type="text" class="field" name="cf"
	 				placeholder="Codice Fiscale"
	 				required
	 				value='<%= cf %>'
	 				onchange="checkCF(this.form.cf)"
	 				onkeyup="checkCF(this.form.cf)"
	 				>
	 				<img id='checkTrueCF' src="icone/iconOk.svg">
	 				<img id='checkFalseCF' src="icone/iconError.svg">
	 			</div>
	 			<div id="regemail">
	 				<input type="email" class="field" name="email"
	 				placeholder="Email"
	 				required
	 				value='<%= email %>'
	 				onchange="checkEmail(this.form.email)"
	 				onkeyup="checkEmail(this.form.email)"
	 				>
	 				<img id='checkTrueEmail' src="icone/iconOk.svg">
	 				<img id='checkFalseEmail' src="icone/iconError.svg">
	 			</div>
	 			<div id="reguser" >
					<input type="text" class="field" name="username"
			    	placeholder="Username"
	        		
	        		value='<%= username %>'
	        		disabled
	        		>
	        		
	        		<!-- required -->
					<!-- onchange="checkUsername(this.form.username)"
					onkeyup="checkUsername(this.form.username)" -->
					
					
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
	 			
				<input class="btn2" type='button' value='conferma'
				onmousedown='encodePassword(this.form.password.value , this.form.name)'
				onmouseup='submitANDrestoreFormName(this.form.name)'
				onmouseup='this.form.submit()'
				>
	 				<a href=<%=response.encodeURL("HomePage.jsp") %>>
	 				 	<button id='btnAnn' type="button" class="btn2" value="Annulla">Annulla</button>
	 				</a>
 			</form>
				
			</div>
						
			<%	
			}
			else
			{
				// pagina di errore
				out.print("errore pagina");
			}
			
			
		}
		
		
	}
	%>
	
	

</body>


</html> 
 
 
 
 
 
 
 
 
 
 
 
