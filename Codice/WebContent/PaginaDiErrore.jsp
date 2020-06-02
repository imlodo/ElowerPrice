<%@page import="java.util.Stack"%>
<%@ 
page language="java"
	 contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8"
	 isErrorPage="true"
	 %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="UTF-8">
<title> ERROR PAGE</title>

</head>

<body>
	<jsp:include page="Navigation.jsp"></jsp:include>
	<%
	if(exception != null)
	{
		out.print("<img src='immagini/davideIannaccone.jpeg' whidth='500px' height='500px' >");
		
		out.print("<h1>si è verificato l'errore perchè ha programmato questo soggetto..."
				+"ci scusiamo per il problema... licenzieremo al più presto questo cesso schifoso!</h1>");
		
		out.print(
				"<p>"
				+"An exception was raised : "+exception.toString()
				+"</p><br>"
				+"<p>"
				+"Exception message is : "+exception.getMessage()
				+"</p>"
				);
				StackTraceElement[] st = exception.getStackTrace();
				for(StackTraceElement e : st)
				{
					out.println(e.toString());
				}
				
	}

	%>


</body>

</html>