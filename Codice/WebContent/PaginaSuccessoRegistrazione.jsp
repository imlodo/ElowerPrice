<%@page import="java.util.Base64"%>
<%@page import="Model.BeanUtente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BeanUtente bean = (BeanUtente) request.getAttribute("user");
	if(bean == null)
	{
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return;
	}
	Base64.Decoder dec = Base64.getDecoder();// decodificatore// creiamo il decodificatore
	byte rawdecodedString[] = dec.decode(bean.getPassword()); // ritorna una array di byte 
	String decodedString = new String(rawdecodedString); // trasformiamo da array di byte in String
	int length = decodedString.length();
	String password = "";
	for(int i = 0; i < length; i++)
		password += "*";
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/PaginaSuccessoRegistrazione.css">
<title>Account registrato con successo</title>
</head>
<body>
	<h1 id="success">Account registrato con successo!</h1>
	<table>
		<caption>Riepilogo dati registrazione</caption>
		<tr>
			<th>Username</th>
			<th>Email</th>
			<th>Codice Fiscale</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Password</th>
		</tr>
		<tr>
			<td><%=bean.getUsername() %></td>
			<td><%=bean.getEmail() %></td>
			<td><%=bean.getCf() %></td>
			<td><%=bean.getName() %></td>
			<td><%=bean.getSurname() %></td>
			<td><%=password %></td>
		</tr>
	</table>
	<p><a href=<%=response.encodeURL("HomePage.jsp")%>>Torna alla HomePage</a></p>
</body>
</html>