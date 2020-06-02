<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="/PROGETTO_TSW_UPDATE/javascript/jquery.js"></script>
</head>
<body>
	<script>
	
	</script>
	<div id ="richiesta">
	<form action="/PROGETTO_TSW_UPDATE/<%=response.encodeURL("ServletViewOperation")%>" method="POST">
		<input type="text" id="op" name="operation" value="Utenti">
	</form>
	<button onclick="myFunction()">Click Me</button>
	</div>
	
	<div id="viewTable">
	</div>
	
</body>
</html>