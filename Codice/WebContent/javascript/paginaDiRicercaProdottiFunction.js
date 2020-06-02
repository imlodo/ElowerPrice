function changePage(num_page, current_product)
{
//	var newurl = window.location.protocol + "//" + 
//	window.location.host + window.location.pathname + 
//	'?currentPage='+num_page+'&'+'prodottoPerNome='+current_product;
//	window.history.pushState({ path: newurl }, '', newurl);
	
	$("document").ready(function()
	{	
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": "ServletCercaProdotto",
			"success": function(data) 
			{
				$(".button_page").css({"background-color":"white"});
				$("#page"+num_page).css({"background-color":"yellow"});
				
				$(".prodotti").empty();
				var prodotti = $(data).find(".prodotti");
				$(".lista").append(prodotti);
			}
		});
		$.ajax({"data": {"currentPage":""+num_page, "prodottoPerNome":current_product}});
	});
}

function showAlert()
{
	$(document).ready(function()
	{
		$("#alert").remove();
		$("body").prepend("<div id='alert'>" +
				"<img id='closeError' src='icone/closeAlert.svg' title='close' onclick='closeError()'>" +
				"<label>Per visualizzare i link, devi loggarti!</label><br>" +
				"<a href='PaginaDiLogin.jsp'>Clicca qui per andare al login</a></div>");
	});
}

function closeError()
{
	$("#alert").remove();
}
