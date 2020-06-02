<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!--  <script type="text/javascript" src="javascript/genericsFunction.js"></script>-->
<!-- 	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>  -->
	<!-- <script src="javascript/jquery.js" type="text/javascript"></script> -->
	 <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
      <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script>
 	$(function() {
    $( "#testo" ).autocomplete({
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
				if(i == 3)
					break;
				value = json[key];
				arr[i] = value;
				i++;
			}
				/* document.getElementById("prova").innerHTML = value; */
			});
			return arr;        
	}
	
	</script>
	<style>
		ul
		{
			list-style-type:"none";
		}
	</style>
	</head>
<body>
	
	input : <input id="testo" type="text">
	<br>
	<p id="prova">...</p>


</body>
</html>