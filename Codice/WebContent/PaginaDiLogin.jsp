<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta charset="UTF-8">
	<title> LOGIN UTENTE</title>
	<link rel="stylesheet" href="css/PaginaDiLogin.css">
	<script src="https://www.google.com/recaptcha/api.js"></script>
	<script src="javascript/jquery.js"></script>
	<script type="text/javascript" src="javascript/genericsFunction.js"></script>
	</head>
	<%
	String usernameSaved = null;
	Cookie[] cookies = request.getCookies();
	boolean trovato = false;
	//Cerco nei cookies
	if(cookies != null)
	{
		for(Cookie cookie : cookies)
		{
			if(trovato == false)
			{
				if(cookie.getName().equals("saveUser"))
				{
					System.out.println(cookie.getValue());
					usernameSaved = cookie.getValue();
					trovato = true;
				}
			}
			else break;
		}
	}
	String checkOption = null;
	trovato = false;
	//Cerco nei cookies
	if(cookies != null)
	{
		for(Cookie cookie : cookies)
		{
			if(trovato == false)
			{
				if(cookie.getName().equals("check"))
				{
					System.out.println(cookie.getValue());
					checkOption = cookie.getValue();
					trovato = true;
				}
			}
			else break;
		}
	}
	%>
<body>
	<div id="login">
	<form name="form1" action=<%=response.encodeURL("ServletLogin") %> method="post">
	<img id="logo" src="immagini/logo2.svg" onclick=""/>
	<%
	String erroreInserimento = "";
	erroreInserimento = (String)request.getAttribute("errore");
	if( (erroreInserimento!=null) && (erroreInserimento.equals("1")) )
	{ 
	%>
		<div id="error">Username o password errata</div>
		<div id="titolo_with_error">Accedi</div>
	<%
		if(usernameSaved != null && !usernameSaved.equals(""))
		{
	%>
			<input type='text' class='textLog' name='username' placeholder="Username" value='<%=usernameSaved%>' autocomplete='off' required>
	<%
		}
		else
		{
	%>
			<input type='text' class='textLog' name='username' placeholder="Username" required autocomplete='off'>
	<% 
		}
	%>
		<br>
		<input type='password' class='textLog' name='password' placeholder="Password" required>
		<br> 
	<% 
	}
	else 
	{
	%>
		<div id="titolo">Accedi</div>
	<%
		if(usernameSaved != null && !usernameSaved.equals(""))
		{
	%>
			<input type='text' class='textLog' name='username' placeholder="Username" value='<%=usernameSaved%>' autocomplete='off' required>
	<%
		}
		else
		{
	%>
			<input type='text' class='textLog' name='username' placeholder="Username" required autocomplete='off'>
	<% 
		}
	%>
		<br>
		<input type='password' class='textLog' name='password' placeholder="Password" required>
		<br> 
	<%
	}
	%>
		<div class='ricordaUser'>
			<label class='titleCheck'>Ricorda username</label>
		<%
		if(checkOption != null && !checkOption.equals(""))
		{
		%>
			<input class='checkbox' type="checkbox" name='ricorda' checked>
		<%
		}
		else
		{
		%>
				<input class='checkbox' type="checkbox" name='ricorda'>
		<%			
		}
		%>
		</div>
		<div class="g-recaptcha" data-sitekey="6LfZA68UAAAAAFCQfl9GVMH9QJkaXYafsMbbbCYs"></div>
		<input id='btLogin' type='button' value='Accedi'
			onclick="encode(document.form1.username.value , document.form1.password.value ),submitANDrestoreForm()">	
		<div class='optionLogin'>
			<a href=''>
				<label class='titleOptionLogin'>Password dimenticata</label>
			</a>
		</div>
		<div class='optionLogin'>
			<a href="<%=response.encodeURL("PaginaDiIscrizione.jsp")%>">
				<label class='titleOptionLogin'>Registrati</label>
			</a>
		</div>
		<div class='optionLogin'>
			<a href="<%=response.encodeURL("HomePage.jsp")%>">
				<label class='titleOptionLogin'>Torna indietro</label>
			</a>
		</div>
		</form>
	</div>

</body>

</html>