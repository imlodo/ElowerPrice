<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<link rel='stylesheet' type='text/css' href='css/Navigation.css'>
	<link rel="stylesheet" type="text/css" href="css/Footer.css">
	<link rel="stylesheet" type="text/css" href="css/ErrorPage.css">
    <title>Error Page</title>
</head>
<body>
	
	<%-- <jsp:include page="/Footer.jsp"></jsp:include> --%>
	<jsp:include page="/Navigation.jsp"></jsp:include>
<div class='error'><img src='immagini/errorImage.png'></img></div>
  <div class="wrapper">
    <div class="push"></div>
       <jsp:include page="/Footer.jsp"></jsp:include>
  </div>

</body>
</html>