/* 
 * Questa funzione serve a controllare la pagina corrente,
 * per permettere la corretta visualizzazione di tutti gli elementi.
 */
function checkPage()
{
	var url = window.location.href;
	var pos = url.substring(url.indexOf('#'));
	$("document").ready(function()
	{	
		var x = document.getElementById("op");
		x.style.display = "none";
		var y = document.getElementById("op2");
		y.style.display = "none";
		var a = document.getElementById("subOp");
		a.style.display = "none";
		var b = document.getElementById("subOp2");
		b.style.display = "none";
		var c = document.getElementById("subOp3");
		c.style.display = "none";
		switch(pos) 
		{
			case '#op':
			{
				x.style.display = "block";
				window.location.href='#op';
				$("#contentOP").empty();
				break;
			}
			case '#op2':
			{
				y.style.display = "block";
				window.location.href='#op2';
				$("#contentOP").empty();
				break;
			}
			case '#subOp':
			{
				x.style.display = "block";
				a.style.display = "block";
				$("#contentOP").empty();
				window.location.href='#subOp';
				break;
			}
			case '#subOp2':
			{
				x.style.display = "block";
				b.style.display = "block";
				$("#contentOP").empty();
				window.location.href='#subOp2';
				break;
			}
			case '#subOp3':
			{
				x.style.display = "block";
				c.style.display = "block";
				$("#contentOP").empty();
				window.location.href='#subOp3';
				break;
			}
			case '#viewAq':
			{
				x.style.display = "block";
				window.location.href='#viewAq';
				sendviewAcquisti();
				break;
			}
			case '#vUtenti':
			{
				x.style.display = "block";
				a.style.display = "block";
				window.location.href='#vUtenti';
				sendviewUser();
				break;
			}
			case '#panelCreateUser':
			{
				x.style.display = "block";
				a.style.display = "block";
				window.location.href='#panelCreateUser';
				createUser();
				break;
			}	
			case '#vProdottiI':
			{
				x.style.display = "block";
				b.style.display = "block";
				window.location.href='#vProdottiI';
				var tmp = 1;
				sendviewProdotto('interno',tmp);
				$("#tipo_prodotto").val('interno');
				break;
			}
			case '#vProdottiF':
			{
				x.style.display = "block";
				b.style.display = "block";
				window.location.href='#vProdottiF';
				var tmp = 1;
				sendviewProdotto('fornito',tmp);
				$("#tipo_prodotto").val('fornito');
				break;
			}
			case '#add_prodotto_interno':
			{
				console.log("sei gay");
				x.style.display = "block";
				b.style.display = "block";
				window.location.href='#add_prodotto_interno';
				$("#tipo_prodotto").val('interno');
				createAddProductInterno();
				break;
			}
			case '#add_product_fornito':
			{
				x.style.display = "block";
				b.style.display = "block";
				window.location.href='#add_product_fornito';
				$("#tipo_prodotto").val('fornito');
				createAddProductFornito();
				break;
			}	
			case '#vStore':
			{
				x.style.display = "block";
				c.style.display = "block";
				window.location.href='#vStore';
				sendviewStore();
				break;
			}
			case '#add_store':
			{
				x.style.display = "block";
				c.style.display = "block";
				window.location.href='#add_store';
				createAddStore();
				break;
			}
			case '#formCSSHomePage':
			{			
				y.style.display = "block";
				createEditCss('HomePage');
				window.location.href='#formCSSHomePage';
				break;
			}
			case '#formCSSPaginaDiLogin':
			{			
				y.style.display = "block";
				createEditCss('PaginaDiLogin');
				window.location.href='#formCSSPaginaDiLogin';
				break;
			}
			case '#formCSSPaginaDiAreaPersonaleUtente':
			{			
				y.style.display = "block";
				createEditCss('PaginaDiAreaPersonaleUtente');
				window.location.href='#formCSSPaginaDiAreaPersonaleUtente';
				break;
			}
			case '#formCSSPaginaDiMoficheDatiUtente':
			{			
				y.style.display = "block";
				createEditCss('PaginaDiMoficheDatiUtente');
				window.location.href='#formCSSPaginaDiMoficheDatiUtente';
				break;
			}
			case '#formCSSPaginaDelProdotto':
			{			
				y.style.display = "block";
				createEditCss('PaginaDelProdotto');
				window.location.href='#formCSSPaginaDelProdotto';
				break;
			}
			case '#formCSSPaginaDiRicercaProdotti':
			{			
				y.style.display = "block";
				createEditCss('PaginaDiRicercaProdotti');
				window.location.href='#formCSSPaginaDiRicercaProdotti';
				break;
			}
			case '#formCSSPaginaDelCarrello':
			{			
				y.style.display = "block";
				createEditCss('PaginaDelCarrello');
				window.location.href='#formCSSPaginaDelCarrello';
				break;
			}
			default:
			{
				$("#contentOP").empty();
				break;
			}
		
		}
	});
}

//Utilities
function logOut()
{
	//Manda richiesta di logOut
	$("document").ready(function()
	{	
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		$.ajaxSetup(
		{ 
			"type": "GET",
			"url": "Logout"+session,
			"success": function(data) 
			{
				window.location.href = "/PROGETTO_TSW_UPDATE/";
			},
			"error": function(data)
			{
				$("body").empty();
				$("body").append(data.responseText);
			}	
		});
		$.ajax({"data":{}});
	});
}
function redirectToHomepage()
{
	var x = $("#url").val();
	var index = x.indexOf(";");
	var session = x.substring(index)
	window.location.href = "/PROGETTO_TSW_UPDATE/HomePage.jsp"+session;
}

function insertOpDB()
{
	$("document").ready(function()
	{	
		var y = document.getElementById("op2");
		y.style.display = "none";
		var a = document.getElementById("subOp");
		a.style.display = "none";
		var b = document.getElementById("subOp2");
		b.style.display = "none";
		var c = document.getElementById("subOp3");
		c.style.display = "none";
		var x = document.getElementById("op");
		if (x.style.display == "block") 
		{
			x.style.display = "none";
		} 
		else 
		{
		    x.style.display = "block";
		}
		window.location.href='#op';
	});
	$("#contentOP").empty();
}
function insertmodificaCSS()
{
	$("document").ready(function()
	{	
		var x = document.getElementById("op");
		x.style.display = "none";
		var a = document.getElementById("subOp");
		a.style.display = "none";
		var b = document.getElementById("subOp2");
		b.style.display = "none";
		var c = document.getElementById("subOp3");
		c.style.display = "none";
		var y = document.getElementById("op2");
		if (y.style.display == "block") 
		{
			y.style.display = "none";
		} 
		else 
		{
			y.style.display = "block";
		}
		window.location.href='#op2';
	});
	$("#contentOP").empty();
}

//Create subOpOperation
function subOpUserManager()
{
	$("document").ready(function()
	{	
		var b = document.getElementById("subOp2");
		b.style.display = "none";
		var c = document.getElementById("subOp3");
		c.style.display = "none";
		var y = document.getElementById("subOp");
		if (y.style.display === "none") 
		{
			y.style.display = "block";
		} 
		else 
		{
			y.style.display = "none";
		}
		$("#contentOP").empty();
		window.location.href='#subOp';
	});
}
function subOpProdottiManager()
{
	$("document").ready(function()
	{	
		var b = document.getElementById("subOp");
		b.style.display = "none";
		var c = document.getElementById("subOp3");
		c.style.display = "none";
		var y = document.getElementById("subOp2");
		if (y.style.display === "none") 
		{
			y.style.display = "block";
		} 
		else 
		{
			y.style.display = "none";
		}
		window.location.href='#subOp2';
	});
	$("#contentOP").empty();
}
function subOpStoreManager()
{
	$("document").ready(function()
	{	
		var b = document.getElementById("subOp");
		b.style.display = "none";
		var c = document.getElementById("subOp2");
		c.style.display = "none";
		var y = document.getElementById("subOp3");
		if (y.style.display === "none") 
		{
			y.style.display = "block";
		} 
		else 
		{
			y.style.display = "none";
		}
		window.location.href='#subOp3';
	});
	$("#contentOP").empty();
}

//send async request
function sendCreateUser()
{
	$(document).ready(function () 
	{
		// Get form
		var form = $('#reg')[0];
		// Create an FormData object 
		var data = new FormData(form);
		// disabled the submit button
		$("#btnConf").prop("disabled", true);

		$.ajax({
			"type": "POST",
			"enctype": 'multipart/form-data',
			"url": "ServletIscrizione",
			"data": data,
			"processData": false,
			"contentType": false,
			"success": function (data) 
			{
				var success =  $($.parseHTML(data)).filter("#success");
				if(success.length == 1)
				{ 
//					$("#contentOp").empty();
//					$("#contentOp").append(data);
					$("#error").empty();
					$("#error").css("padding", "15");
					$("#error").css("background-color", "rgb(0, 148, 68)");
					$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
					$("#closeError").css("filter", "hue-rotate(125deg)");
					$("#error").append("<label>"+data+"</label><br>");
					$("#normSend").prop("disabled", false);
					$("#btnConf").prop("disabled", false);
				}
				else
				{
					$("#error").empty();
				    $("#error").css("padding", "0");
					$("#error").append(data);
					$("#error").css("padding", "15");
					$("#error").prepend("<label id='lab_err'>Correggi gli errori per continuare:</label><br>");
					$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
					$("#btnConf").prop("disabled", false);
				}
			}
		});
		window.location.href='#panelCreateUser';
	});
}
function readCss(nomeFileCSS)
{
	var x = $("#url").val();
	var index = x.indexOf(";");
	var session = x.substring(index);
	var url = "ServletAggiornaCSS" + session;
	$("document").ready(function()
	{	
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": url,
			"success": function(data) 
			{
				var success =  $($.parseHTML(data)).filter("#cssView"); 
				if(success.length != 1)
				{
					
					success = null; 
				}
				if(data.error) {
				      console.log("ERROR: "+data.error);
				}
				createViewCSS(success, nomeFileCSS);
			},
			"error": function(data)
			{
				$("body").empty();
				$("body").append(data.responseText);
			}
		});
		$.ajax({"data":{"cssOP":"read", "file_name":nomeFileCSS}});
		window.location.href='#formCSS'+nomeFileCSS;
	});
	
}
function saveCss(nomeFileCSS, data2)
{
//	alert(data2.value);
	$("document").ready(function()
	{	
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		var url = "ServletAggiornaCSS" + session;
		
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": url,
			"success": function(data) 
			{
				var success =  $($.parseHTML(data)).filter("#esito");
				if(success.length != 1)
				{
					success = null; 
				}
				readCss(nomeFileCSS);
//				for(k in success)
				alert($(success).text());
			},
			"error": function(data)
			{
				$("body").empty();
				$("body").append(data.responseText);
			}
		});
		$.ajax({"data":{"cssOP":"save", "file_name":nomeFileCSS, "css_modificato":data2.value}});
		window.location.href='#formCSS'+nomeFileCSS;
	});
}
function sendviewUser()
{	
	var op = "Utenti";
	$("document").ready(function()
	{	
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		$.ajaxSetup(
		{ 			
			"type": "POST",
			"url": "ServletViewOperation"+session,
			"success": function(json) 
			{	
				if(json != null)
				{
					createViewUtenti(json);
				}
				else
				{
					$("#PanelHomePage").empty();
					$("#PanelHomePage").html("<h1>Errore</h1>")
				}
			},
			"error":function(json)
			{
				$("body").empty();
				$("body").append(json.responseText);
			}
		});
		$.ajax({"data": 
		{
			"operation": op
		}
		});
	});
	window.location.href="#vUtenti";
}
function sendviewProdotto(x, page)
{	
	if(x == 'interno')
	{
		tipo = 'interno';
	}
	else if(x == 'fornito')
	{
		tipo = 'fornito';
	}
	else
	{
		var tipo = $("#tipo_prodotto").val();
		if(tipo === undefined)
		{
			tipo = 'interno';
		}
	}

	$("document").ready(function()
	{
		if(page === undefined)
		{
			page = 1;
		}
		var op = "Prodotti";
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": "ServletViewOperation"+session,
			"success": function(json) 
			{	
				if(json != null)
				{
					if(tipo == 'interno')
					{
						createviewProdottiInterni(json,page);
					}
					else
					{
						createviewProdottiForniti(json,page);
					}
				}
				else
				{
					$("#PanelHomePage").empty();
					$("#PanelHomePage").html("<h1>Errore</h1>")
				}
			},
			"error":function(json)
			{
					$("body").empty();
					$("body").append(json.responseText);
			}
		});
		$.ajax({"data": 
		{
			"operation": op,
			"tipo_prodotto": tipo,
			"num_page": page
		}
		});
	}); 
}
function sendviewStore()
{
	$("document").ready(function()
	{	
		var op = "Store";
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": "ServletViewOperation"+session,
			"success": function(json) 
			{	
				if(json != null)
				{
					createViewStore(json);
				}
				else
				{
					$("#PanelHomePage").empty();
					$("#PanelHomePage").html("<h1>Errore</h1>")
				}
			},
			"error":function(json)
			{
				$("body").empty();
				$("body").append(json.responseText);
			}
		});
		$.ajax({"data": 
		{
			"operation": op
		}
		});
	}); 
}
function sendviewAcquisti()
{
	var op = "Acquisti";
	$("document").ready(function()
	{	
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": "ServletViewOperation"+session,
			"success": function(json) 
			{	
				if(json != null)
				{
					createViewAcquisti(json);
				}
				else
				{
					$("#PanelHomePage").empty();
					$("#PanelHomePage").html("<h1>Errore</h1>")
				}
			},
			"error":function(json)
			{
				$("body").empty();
				$("body").append(json.responseText);
			}
		});
		$.ajax({"data": 
		{
			"operation": op
		}
		});
	}); 
}
function sendAddProduct()
{
	$("document").ready(function()
	{
		var tipo = $("#tipo_prodotto").val();
		if(tipo === undefined)
		{
			tipo = 'interno';
		}
			
		if(tipo =='interno')
		{
			createAddProductInterno();
		}
		else
		{
			createAddProductFornito();
		}
	});
}

//Implementation subOperation
function createUser()
{
	$("#contentOP").empty();
	$("#contentOP").html
	("<div id='error'>"+
	"</div>"+
	"<div id='panelCreateUser' align='center'>"+
		"<div id='titolo'>Aggiungi Nuovo Utente</div>"+
 		"<form id='reg'>"+
 			"<div id='regnome'>"+
 				"<label>Nome: </label>"+
 				"<input type='text' class='field' name='nome'"+
 				"placeholder='Michele'"+
 				"required" +
 				"onchange='checkName(this.form.nome)'"+
 				"onkeyup='checkName(this.form.nome)'"+
 				">"+
 				"<img id='checkTrueName' src='icone/iconOk.svg'>"+
 				"<img id='checkFalseName' src='icone/iconError.svg'>"+
 			"</div>"+
 			"<div id='regcognome'>"+
 				"<label>Cognome: </label>"+
 				"<input type='text' class='field' name='cognome'"+
 				"placeholder='Risi'"+
 				"required"+
 				"onchange='checkSurname(this.form.cognome)'"+
 				"onkeyup='checkSurname(this.form.cognome)'"+
 				">"+
 				"<img id='checkTrueSurname' src='icone/iconOk.svg'>"+
 				"<img id='checkFalseSurname' src='icone/iconError.svg'>"+
 			"</div>"+
 		 	"<div id='regcf'>"+
 		 		"<label>CF: </label>"+
 				"<input type='text' class='field' name='codf'"+
 				"placeholder='CTLMDL05D42G273B'"+
 				"required"+
 				"onchange='checkCF(this.form.codf)'"+
 				"onkeyup='checkCF(this.form.codf)'"+
 				">"+
 				"<img id='checkTrueCF' src='icone/iconOk.svg'>"+
 				"<img id='checkFalseCF' src='icone/iconError.svg'>"+
 			"</div>"+
 			"<div id='regemail'>"+
 				"<label>Email </label>"+
 				"<input type='email' class='field' name='email'"+
 				"placeholder='michelerisi1@gmail.com'"+
 				"required"+
 				"onchange='checkEmail(this.form.email)'"+
 				"onkeyup='checkEmail(this.form.email)'"+
 				">"+
 				"<img id='checkTrueEmail' src='icone/iconOk.svg'>"+
 				"<img id='checkFalseEmail' src='icone/iconError.svg'>"+
 			"</div>"+
 			"<div id='reguser' >"+
 				"<label>Username: </label>"+
 				"<input type='text' class='field' name='username'"+
		    	"placeholder='bellissimo' autocomplete='off'"+
        		"required"+
				"onchange='checkUsername(this.form.username)'"+
				"onkeyup='checkUsername(this.form.username)'"+
				">"+
				"<img id='checkTrueUsername' src='icone/iconOk.svg'>"+
				"<img id='checkFalseUsername' src='icone/iconError.svg'>"+
			"</div>"+
 		
 			"<div id='regpw'>"+
 				"<label>Password: </label>"+
 				"<input type='password' class='field' name='password'"+
 				"placeholder='gallinamatta01' autocomplete='off'"+
 				"autocomplete='false'"+
 				"required"+
 				"onchange='checkPassword(this.form.password)'"+
 				"onkeyup='checkPassword(this.form.password)'"+
 				">"+
 				"<img id='checkTruePassword' src='icone/iconOk.svg'>"+
 				"<img id='checkFalsePassword' src='icone/iconError.svg'>"+
 			"</div>"+
 		
 			"<div id='regconfpw'>"+
 				"<label>Conferma Password: </label>"+
 				"<input type='password' class='field' name='password2'"+
 				"placeholder='gallinamatta01'"+
 				"autocomplete='false'"+
 				"required"+
 				"onchange='checkPasswordConf(this.form.password2)'"+
 				"onkeyup='checkPasswordConf(this.form.password2)'"+
 				">"+
 				"<img id='checkTruePassword2' src='icone/iconOk.svg'>"+
 				"<img id='checkFalsePassword2' src='icone/iconError.svg'>"+
 			"</div>"+
 			"<button id='btnConf' class='btn' type='button' value='Conferma' onclick='validateForm(this.form)'>"+
 				"Conferma"+
 			"</button>"+
 				 	"<button id='btnAnn' type='button' onclick='location.reload(true)' class='btn2' value='Annulla'>Annulla</button>"+
 		"</form>"+
	"</div>");
	window.location.href='#panelCreateUser';
}
function createEditCss(nomeFileCSS)
{	
	$("#contentOP").empty();
	$("#contentOP").html
	("<div id='PanelHomePage'>" +
		"<form id='modCSS'></form>"+
	"</div>");
	readCss(nomeFileCSS);
}
function createViewCSS(data, nomeFileCSS)
{
	if(data != null)
	{
		$("#PanelHomePage").html("<form id='formCSS"+nomeFileCSS+"' class='formCSS'></form>")
		$("#formCSS"+nomeFileCSS).append(data);
		$("#formCSS"+nomeFileCSS).prepend("<label class='l' for='cssView'>Modifica "+nomeFileCSS+".css</label>");
		var str = "<button id='saveCSS' "+"onclick=saveCss("+"'"+nomeFileCSS+"'"+","+'cssView'+")"+">Salva</button>"+
		  "<button id='annullaCSS' "+"onclick=readCss("+"'"+nomeFileCSS+"'"+")"+">Annulla</button>";
		$("#PanelHomePage").append(str);
	}
	else
	{
		$("#esit").empty();
		$("#PanelHomePage").append(
				"<label id='esit'>Errore: Non sono riuscito a leggere il file.</label>");
	}
	window.location.href='#formCSS'+nomeFileCSS;
}
function createViewUtenti(json)
{
	$("#contentOP").empty();
	$("#contentOP").html(
		"<label class='l2'>Visualizza Utenti</label>"+
		"<div id='vUtenti' class='cont_table'>"+
		"<table id='table'>"+
			"<tr>"+
				"<th>"+
					"<label>Username</label>"+
				"</th>"+
				"<th>"+
					"<label>Email</label>"+
				"</th>"+
				"<th>"+
					"<label>CF</label>"+
				"</th>"+
				"<th>"+
					"<label>Name</label>"+
				"</th>"+
				"<th>"+
					"<label>Surname</label>"+
				"</th>"+
				"<th>"+
					"<label>Type</label>"+
				"</th>"+
			"</tr>"+
			"</table>" +
			"</div>");
	for(var i = 0; i < json.length; i++)
	{
		$("#table").append(
			"<tr>"+
				"<td>"+json[i].username+"</td>"+
				"<td>"+json[i].email+"</td>"+
				"<td>"+json[i].cf+"</td>"+
				"<td>"+json[i].name+"</td>"+
				"<td>"+json[i].surname+"</td>"+
				"<td>"+json[i].tipo+"</td>"+
			"</tr>");
	}
	window.location.href='#vUtenti';
}
function createviewProdotto()
{
	$("#contentOP").empty();
	$("#contentOP").html(
			"<label id='label_tp'>Tipo prodotto: " +
			"<select id='tipo_prodotto' name='tipo_prodotto' onchange='sendviewProdotto()'>" +
				"<option value='interno'>Interno</option>" +
				"<option value='fornito'>Fornito</option>" +
			"</select>" +
			"</label>" +
			"</div>");
}
function createviewProdottiInterni(json, page)
{
	if(json != null)
	{	
			interni = 'interni';
			$("#contentOP").empty();
			$("#contentOP").html(
				"<input type='text' name='num_page' value='"+page+"' hidden>"+
				"<label id='label_tp'>Tipo prodotto: " +
				"<select id='tipo_prodotto' name='tipo_prodotto' onchange='sendviewProdotto()'>" +
				"<option value='interno'>Interno</option>" +
				"<option value='fornito'>Fornito</option>" +
				"</select>" +
				"<button type='button' onclick='decrementPage(interni)'>Prev Page</button>" +
				"<button type='button' onclick='incrementPage(interni)'>Next Page</button>" +
				"</label>" +
				"<label class='l2'>Visualizza Prodotti Interni</label>" +
				"<div id='content_Prodotti'>" +
				"<div id='vProdottiI' class='cont_table'>"+
				"<table id='table' style='width:100%;'>"+
				"<tr>"+
					"<th>"+
						"<label>Image</label>"+
					"</th>"+
					"<th>"+
						"<label>Nome</label>"+
					"</th>"+
					"<th>"+
						"<label>Prezzo</label>"+
					"</th>"+
					"<th>"+
						"<label>Quantity</label>"+
					"</th>"+
					"<th>"+
						"<label>Disponbilità</label>"+
					"</th>"+
					"<th>"+
						"<label>Descrizione</label>"+
					"</th>"+
					"<th>"+
						"<label>Opzione Acquisto</label>"+
					"</th>"+
					"<th>"+
						"<label>Cod Ean</label>"+
					"</th>"+
				"</tr>"+
				"</table>" +
				"</div>" +
				"</div>");
				for(var i = 0; i < json.length; i++)
				{
					var image = btoa(
							  new Uint8Array(json[i].image)
							    .reduce((data, byte) => data + String.fromCharCode(byte), '')
							);
					var cod_ean = json[i].cod_ean;
					if((json[i].cod_ean) === undefined)
					{
						cod_ean = '';
					}
					$("#table").append(
						"<tr>"+
						"<td><img class='image_prodotti' src='data:image/jpg;base64,"+image+"' alt='image' title='image prodotto'></td>"+
						"<td>"+json[i].name+"</td>"+
						"<td>"+json[i].prezzo+"</td>"+
						"<td>"+json[i].quantity+"</td>"+
						"<td>"+json[i].availability+"</td>"+
						"<td>"+json[i].descrizione+"</td>"+
						"<td>"+json[i].opzione_acquisto+"</td>"+						
						"<td>"+cod_ean+"</td>"+
						"</tr>");
				}
				$("#tipo_prodotto").val('interno');
			}
			
	window.location.href='#vProdottiI';
}
function decrementPage(tipo)
{
	$(document).ready(function()
	{
		var page = $("input[name='num_page']");
		var tmp = parseInt(page[0].value,10) - 5;
		if(tmp < 1)
		{
			page[0].value = "1";
		}
		else
		{
			page[0].value = tmp.toString();
		}
		console.log('decrement page '+ page[0].value);
		sendviewProdotto(tipo,parseInt(page[0].value,10));
	});
}
function incrementPage(tipo)
{
	$(document).ready(function()
	{
		var page = $("input[name='num_page']");
		var tmp = parseInt(page[0].value,10) + 5;
		page[0].value = tmp.toString();
		console.log('increment page ' +page[0].value);
		sendviewProdotto(tipo,parseInt(page[0].value,10));
	});
}
function createviewProdottiForniti(json, page)
{
	if(json != null)
	{
		fornito = 'fornito';
		$("#contentOP").empty();
		$("#contentOP").html(
			"<label id='label_tp'>Tipo prodotto: " +
			"<input type='text' name='num_page' value='"+page+"' hidden>"+
			"<select id='tipo_prodotto' name='tipo_prodotto' onchange='sendviewProdotto()'>" +
			"<option value='interno'>Interno</option>" +
			"<option value='fornito'>Fornito</option>" +
			"</select>" +
			"<button type='button' onclick='decrementPage(fornito)'>Prev Page</button>" +
			"<button type='button' onclick='incrementPage(fornito)'>Next Page</button>" +
			"</label>" +
			"<div id='content_Prodotti'>" +
			"</div>"+
			"<label class='l2'>Visualizza Prodotti Forniti</label>" +
			"<div id='content_Prodotti'>" +
			"<div id='vProdottiF' class='cont_table'>"+
			"<table id='table' style='width:100%;'>"+
			"<tr>"+
				"<th>"+
					"<label>Image</label>"+
				"</th>"+
				"<th>"+
					"<label>Nome</label>"+
				"</th>"+
				"<th>"+
					"<label>Prezzo Ieri</label>"+
				"</th>"+
				"<th>"+
					"<label>Prezzo Scorso Mese</label>"+
				"</th>"+
				"<th>"+
					"<label>Prezzo Inizio Giorno</label>"+
				"</th>"+
				"<th>"+
					"<label>Prezzo Attuale</label>"+
				"</th>"+
				"<th>"+
					"<label>Quantity</label>"+
				"</th>"+
				"<th>"+
					"<label>Disponibilità</label>"+
				"</th>"+
				"<th>"+
					"<label>Descrizione</label>"+
				"</th>"+
				"<th>"+
					"<label>Opzioni Acquisto</label>"+
				"</th>"+
				"<th>"+
					"<label>Codice Ean</label>"+
				"</th>"+
				"<th>"+
					"<label>Costo Spedizione</label>"+
				"</th>"+
				"<th>"+
					"<label>Link Store</label>"+
				"</th>"+
			"</tr>"+
		"</table>" +
		"</div>" +
		"</div>");
		for(var i = 0; i < json.length; i++)
		{
			var image = btoa(new Uint8Array(json[i].image).reduce((data, byte) => data + String.fromCharCode(byte), ''));
			var cod_ean = json[i].cod_ean;
			if((json[i].cod_ean) === undefined)
			{
				cod_ean = '';
			}
			$("#table").append(
			"<tr>"+
			"<td><img class='image_prodotti' src='data:image/jpg;base64,"+image+"' alt='image' title='image prodotto'></td>"+
				"<td>"+json[i].name+"</td>"+
				"<td>"+json[i].prezzo_ieri+"</td>"+
				"<td>"+json[i].prezzo_scorso_mese+"</td>"+
				"<td>"+json[i].prezzo_inizio_giorno+"</td>"+
				"<td>"+json[i].prezzo_attuale+"</td>"+
				"<td>"+json[i].quantity+"</td>"+
				"<td>"+json[i].availability+"</td>"+
				"<td>"+json[i].descrizione+"</td>"+
				"<td>"+json[i].opzione_acquisto+"</td>"+
				"<td>"+cod_ean+"</td>"+
				"<td>"+json[i].costo_spe+"</td>"+
				"<td>"+json[i].link+"</td>"+
			"</tr>");
		}
		$("#tipo_prodotto").val('fornito');
	}
	window.location.href='#vProdottiF';
}
function createViewStore(json)
{
	$("#contentOP").empty();
	$("#contentOP").html(
		"<label class='l2'>Visualizza Store</label>" +
		"<div id='vStore' class='cont_table'>"+
		"<table id='table' style='width:100%;'>"+
			"<tr>"+
				"<th>"+
					"<label>Logo</label>"+
				"</th>"+
				"<th>"+
					"<label>Nome</label>"+
				"</th>"+
				"<th>"+
					"<label>PIVA</label>"+
				"</th>"+
				"<th>"+
					"<label>Num_Visite</label>"+
				"</th>"+
				"<th>"+
					"<label>Via</label>"+
				"</th>"+
				"<th>"+
					"<label>N_Civico</label>"+
				"</th>"+
				"<th>"+
					"<label>Cap</label>"+
				"</th>"+
				"<th>"+
				"	<label>Città</label>"+
				"</th>"+
				"<th>"+
				"	<label>Provincia</label>"+
				"</th>"+
			"</tr>"+
			"</table>" +
			"</div>");
	for(var i = 0; i < json.length; i++)
	{
		var image = btoa(
				  new Uint8Array(json[i].logo)
				    .reduce((data, byte) => data + String.fromCharCode(byte), '')
				);
		var cod_ean = json[i].cod_ean;
		if((json[i].cod_ean) === undefined)
		{
			cod_ean = '';
		}
		$("#table").append(
			"<tr>"+
			"<td><img class='image_prodotti' src='data:image/jpg;base64,"+image+"' alt='image' title='image prodotto'></td>"+
				"<td>"+json[i].nome_store+"</td>"+
				"<td>"+json[i].p_iva+"</td>"+
				"<td>"+json[i].n_visite+"</td>"+
				"<td>"+json[i].via+"</td>"+
				"<td>"+json[i].n_civico+"</td>"+
				"<td>"+json[i].cap+"</td>"+
				"<td>"+json[i].city+"</td>"+
				"<td>"+json[i].prov+"</td>"+
			"</tr>");
	}
	window.location.href='#vStore';
}
function createViewAcquisti(json)
{
	var y = document.getElementById("subOp");
	y.style.display = "none";
	var b = document.getElementById("subOp2");
	b.style.display = "none";
	var c = document.getElementById("subOp3");
	c.style.display = "none";
	$("#contentOP").empty();
	$("#contentOP").html(
		"<label class='l2'>Visualizza Acquisti</label>" +
		"<div id='viewAq' class='cont_table'>"+
		"<table id='table' style='width:100%;'>"+
			"<tr>"+
				"<th>"+
					"<label>Username Utente</label>"+
				"</th>"+
				"<th>"+
					"<label>Prodotto Name</label>"+
				"</th>"+
				"<th>"+
					"<label>Prezzo Di Acquisto</label>"+
				"</th>"+
				"<th>"+
					"<label>Tipo Acquisto</label>"+
				"</th>"+
			"</tr>"+
			"</table>" +
			"</div>");
	for(var i = 0; i < json.length; i++)
	{
		$("#table").append(
			"<tr>"+
				"<td>"+json[i].username_utente+"</td>"+
				"<td>"+json[i].prodotto_name+"</td>"+
				"<td>"+json[i].prezzo_acquisto+"</td>"+
				"<td>"+json[i].opzione_acquisto+"</td>"+
			"</tr>");
	}
	window.location.href='#viewAq';
}
function createAddStore()
{
	
	$(document).ready(function()
	{
		window.location.href='#add_store'
	});
	var x = $("#url").val();
	var index = x.indexOf(";");
	var session = x.substring(index);
	var url = "ServletAddOperation" + session;
	
	$("#contentOP").empty();
	$("#contentOP").html(
		"<div id='add_store'>" +
		"<div id='error'></div>" +
			"<label class='l' for='form_add_store'>Aggiungi Store</label>"+
			"<form id='form_add_store' action='"+url+"' method='post' enctype='multipart/form-data' target='_blank'>" +
				"<div id='formAPContent'>" +
				"<input type='text' name='operation' value='Store' hidden>"+
				"<label>Nome store</label>"+
				"<input class='field' type='text' name='store_name' placeholder='IKEA' required>" +
				"<br>" +
				"<label>Partita Iva</label>"+
				"<input class='field' type='text' name='pIVA' placeholder='1234567890A' required>" +
				"<br>" +
				"<label>Via</label>"+
				"<input class='field' type='text' name='via_store' placeholder='Pinco Pallino' required>" +
				"<br>" +
				"<label>Civico</label>"+
				"<input class='field' type='number' step='1' min='1' name='civico' placeholder='3' required>" +
				"<br>" +
				"<label>Seleziona Cap</label>"+
				"<input class='field' id='capInput' list='selectCap' name='cap' placeholder='84121' autocomplete='off' required>"+
					"<datalist id='selectCap'>"+	
					"</datalist>"+
				"</input>"+
				"<br>"+
				"<label for='image_send'>Carica Logo </label>" +
				"<input class='field' type='file' name='image' id='image_send' accept='.png, .jpg, .jpeg' required/>" +
				"<br>" +
				"<label id='result'></label>"+
				"<input id ='normSend' type='submit'>"+
			"</div>" +
			"</form>" +
		"</div>");
	

	$(document).ready(function () 
	{
		$("#normSend").click(function (event) 
		{
			//stop submit the form, we will post it manually.
			event.preventDefault();
			if(validateFormAddStore(document.getElementById('form_add_store')) == true)
			{
				// Get form
				var form = $('#form_add_store')[0];
				// Create an FormData object 
				var data = new FormData(form);
				// If you want to add an extra field for the FormData
				data.append("CustomField", "This is some extra data, testing");
				// disabled the submit button
				$("#normSend").prop("disabled", true);

				$.ajax({
					"type": "POST",
					"enctype": 'multipart/form-data',
					"url": url,
					"data": data,
					"processData": false,
					"contentType": false,
					"cache": false,
					"success": function (data) 
					{
						if(data == 'Store Aggiunto con successo')
						{
							$("#error").empty();
							$("#error").css("padding", "15");
							$("#error").css("background-color", "rgb(0, 148, 68)");
							$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
							$("#closeError").css("filter", "hue-rotate(125deg)");
							$("#error").append("<label>"+data+"</label><br>");
							$("#normSend").prop("disabled", false);
						}
						else
						{
							$("#error").empty();
							$("#error").css("padding", "15");
							$("#error").css("background-color", "rgba(215, 44, 44, 0.9)");
							$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
							$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
							$("#error").append("<label>"+data+"</label><br>");
							$("#normSend").prop("disabled", false);
						}
					},
					error: function (e) 
					{
						$("#error").empty();
						$("#error").css("padding", "15");
						$("#error").css("background-color", "rgba(215, 44, 44, 0.9)");
						$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
						$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
						$("#error").append("<label>"+e.responseText+"</label><br>");
						$("#normSend").prop("disabled", false);
					}
				});
			}
		});
	});
	
	//Cerca il cap
	$("document").ready(function()
	{	
		$("#capInput").keypress(function() 
		{
			var value = $("#capInput").val();  
			console.log(value);
			var x = $("#url").val();
			var index = x.indexOf(";");
			var session = x.substring(index)
			var op = 'cercaCap';
			$.ajaxSetup(
			{ 
				"type": "POST",
				"url": "ServletSearchDB"+session,
				"success": function(data) 
				{	
					if(data != null)
					{
						$("#capInput").empty();
						$("#capInput").append(data);
					}
				},
				"error": function(data)
				{
					$("body").empty();
					$("body").append(data.responseText);
				}
			});
			$.ajax({
				"data": 
				{
					"operation": op,
					"word": value
				}
			});
		}); 
	});
	
}
function createAddProduct()
{	
	$("#contentOP").empty();
	$("#contentOP").html(
			"<div id='menuV'>" +
			"<div id='error'></div>" +
			"<label>Tipo prodotto " +
			"<select id='tipo_prodotto' name='tipo_prodotto' onchange='sendAddProduct()'>" +
				"<option value='interno'>Interno</option>" +
				"<option value='fornito'>Fornito</option>" +
			"</select>" +
			"</label>" +
			"<button id='buttonProd' type='button' class='selectedCl' onclick='addProduct()'>Nuovo Prodotto</button>" +
			"<button id='buttonStore' type='button' class='selectedCl' onclick='createAddStore()'>Nuovo Store</button>"+
			"</div>");
	$(document).ready(function()
	{
		createAddProductInterno();
	});
}
function createAddProductInterno()
{	
	$(document).ready(function () 
	{	
		$("#contentOP").html(
				"<div id='menuV'>" +
				"<div id='error'></div>" +
				"<label>Tipo prodotto " +
				"<select id='tipo_prodotto' name='tipo_prodotto' onchange='sendAddProduct()'>" +
					"<option value='interno'>Interno</option>" +
					"<option value='fornito'>Fornito</option>" +
				"</select>" +
				"</label>" +
				"<button id='buttonProd' type='button' class='selectedCl' onclick='addProduct()'>Nuovo Prodotto</button>" +
				"<button id='buttonStore' type='button' class='selectedCl' onclick='createAddStore()'>Nuovo Store</button>"+
				"</div>" +
				"<div id='content_Prodotti'>" +
		"<div id='add_prodotto_interno'>" +
			"<label class='l' for='form_add_product'>Aggiungi Prodotto Interno</label>"+
			"<form id='form_add_product'>" +
			"<div id='formAPContent'>"+
				"<input type='text' name='operation' value='Prodotti' hidden>" +
				"<input type='text' name='tipo_prodotto' value='interno' hidden>" +
					"<label>Cerca prodotto </label>"+
					"<input id='productInput' class='field' list='selectProduct' name='product' placeholder='Adidas'>"+
						"<datalist id='selectProduct'>"+
						"</datalist>"+
					"</input>"+
					"<br>" +
					"<label>Prezzo </label>"+
					"<input class='field' type='number' step='0.01' min='0.01' name='prezzo' placeholder='10,50' required>" +
					"<br>" +
					"<label>Quantità </label>"+
					"<input  class='field' type='number' step='1' min='1' name='quantity' placeholder='10' required>" +
					"<br>" +
					"<label class='fix_desc'>Descrizione </label>"+
					"<textarea rows='10' cols='90' class='field' name='descrizione' placeholder='...' required></textarea>" +
					"<br>" +
					"<label>Seleziona opzione d'acquisto </label>"+
					"<select class='field' name='select_opzione' required>" +
						"<option>RITIRO_IN_SEDE</option>" +
						"<option>CONSEGNA_DOMICILIO</option>" +
					"<select>" +
					"<br>" +
					"<label>Codice Ean </label>"+
					"<input class='field' type='text' name='cod_ean' placeholder='AAAAAACCCCCC(opzionale)'>" +
					"<br>" +
					"<label id='result'></label>"+
					"<input id ='normSend' type='submit'>"+
				"</div>" +
				"</form>" +
			"</div>" +
			"</div>");
		$("#tipo_prodotto").val('interno');
		//Invia la richiesta alla servlet
		$("#normSend").click(function(event) 
		{
			var x = $("#url").val();
			var index = x.indexOf(";");
			var session = x.substring(index);
			//stop submit the form, we will post it manually.
			event.preventDefault();
			if(validateFormAddProdI(document.getElementById('form_add_product')) == true)
			{
			// Get form
			var form = $('#form_add_product')[0];
			// Create an FormData object 
			var data = new FormData(form);
			// If you want to add an extra field for the FormData
			data.append("CustomField", "This is some extra data, testing");
			// disabled the submit button
			$("#normSend").prop("disabled", true);
			$.ajax({
			"type": "POST",
			"enctype": 'multipart/form-data',
			"url": "ServletAddOperation"+session,
			"data": data,
			"processData": false,
			"contentType": false,
			"cache": false,
			"success": function (data) 
			{
				if(data == 'prodotto aggiunto con successo')
				{
					$("#error").empty();
					$("#error").css("padding", "15");
					$("#error").css("background-color", "rgb(0, 148, 68)");
					$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
					$("#closeError").css("filter", "hue-rotate(125deg)");
					$("#error").append(data);
					$("#error label").css("width", "100%");
					$("#fix_error_panel label").css("width", "100%");
					$("#error label").css("float", "none");
					$("#normSend").prop("disabled", false);
				}
				else
				{
					$("#error").empty();
					$("#error").css("padding", "15");
					$("#error").css("background-color", "rgba(215, 44, 44, 0.9)");
					$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
					$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
					$("#error").append(data);
					$("#error label").css("width", "100%");
					$("#fix_error_panel label").css("width", "100%");
					$("#error label").css("float", "none");
					$("#normSend").prop("disabled", false);
				}
				$("#normSend").prop("disabled", false);
			},
			"error": function (e) 
			{
				$("#error").empty();
				$("#error").css("padding", "15");
				$("#error").css("background-color", "rgba(215, 44, 44, 0.9)");
				$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
				$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
				$("#error").append("<label>"+e.responseText+"</label><br>");
				$("#error label").css("width", "100%");
				$("#fix_error_panel label").css("width", "100%");
				$("#error label").css("float", "none");
				$("#normSend").prop("disabled", false);
			}
			});
			}
			window.location.href='#add_prodotto_interno';
		});
		
	//Cerca i prodotti
	$("#productInput").keypress(function() 
	{
		var value = $("#productInput").val();  
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		var op = 'cercaProdotti';
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": "ServletSearchDB"+session,
			"success": function(data) 
			{	
				if(data != null)
				{
					$("#productInput").empty();
					$("#productInput").append(data);
				}
			},
			"error": function(data)
			{
				$("body").empty();
				$("body").append(data.responseText);
			}
		});
		$.ajax({
			"data": 
			{
				"operation": op,
				"word": value
			}
		});
	}); 
	window.location.href='#add_prodotto_interno';
});	
}
function addProduct()
{
	var x = $("#url").val();
	var index = x.indexOf(";");
	var session = x.substring(index);
	window.open("PaginaNuovoProdotto.jsp"+session);
	
}
function createAddProductFornito()
{
	$("document").ready(function()
	{
		
	$("#contentOP").html(
			"<div id='menuV'>" +
			"<div id='error'></div>" +
			"<label>Tipo prodotto " +
			"<select id='tipo_prodotto' name='tipo_prodotto' onchange='sendAddProduct()'>" +
				"<option value='interno'>Interno</option>" +
				"<option value='fornito'>Fornito</option>" +
			"</select>" +
			"</label>" +
			"<button id='buttonProd' type='button' class='selectedCl' onclick='addProduct()'>Nuovo Prodotto</button>" +
			"<button id='buttonStore' type='button' class='selectedCl' onclick='createAddStore()'>Nuovo Store</button>"+
			"</div>"+
		"<div id='content_Prodotti'>" +
		"<div id='add_product_fornito'>" +
			"<label class='l' for='form_add_product'>Aggiungi Prodotto Fornito</label>"+
			"<div id='formAPContent'>" +
			"<form id='form_add_product'>"+
				"<input type='text' name='operation' value='Prodotti' hidden>" +
				"<input type='text' name='tipo_prodotto' value='fornito' hidden>" +
				"<label>Seleziona Prodotto</label>"+
				"<input class='field' id='productInput' list='selectProduct' name='product' placeholder='Adidas' required>"+
					"<datalist id='selectProduct'>"+	
					"</datalist>"+
				"</input>"+
				"<br>" +
				"<label>Seleziona Store </label>"+
				"<input class='field' id='storeInput' list='selectStore' name='store' placeholder='Ikea' required>"+
					"<datalist id='selectStore'>"+
					"</datalist>"+
				"</input>"+
				"<br>" +
				"<label>Prezzo Scorso Mese </label>"+
				"<input class='field' type='number' step='0.01' min='0.01' name='prezzo_mese' placeholder='10,50' required>" +
				"<br>" +
				"<label>Prezzo Ieri </label>"+
				"<input class='field' type='number' step='0.01' min='0.01' name='prezzo_ieri' placeholder='10,50' required>" +
				"<br>" +
				"<label>Prezzo Inizio Giorno </label>"+
				"<input class='field' type='number' step='0.01' min='0.01' name='prezzo_inizio_giorno' placeholder='10,50' required>" +
				"<br>" +
				"<label>Prezzo Attuale </label>"+
				"<input class='field' type='number' step='0.01' min='0.01' name='prezzo_attuale' placeholder='10,50' required>" +
				"<br>" +
				"<label>Quantità </label>"+
				"<input class='field' type='number' step='1' min='1' name='quantity' placeholder='100' required>" +
				"<br>" +
				"<label class='fix_desc'>Descrizione </label>"+
				"<textarea rows='10' cols='90' class='field' name='descrizione' placeholder='...' required></textarea>" +
				"<br>" +
				"<label>Opzione Consegna </label>"+
				"<select class='field' name='select_opzione' required>" +
					"<option>RITIRO_IN_SEDE</option>" +
					"<option>CONSEGNA_DOMICILIO</option>" +
				"</select>" +
				"<br>" +
				"<label>Codice Ean </label>"+
				"<input class='field' type='text' name='cod_ean' placeholder='1234567890123' required>" +
				"<br>" +
				"<label>Costo Spedizione </label>"+
				"<input class='field' type='number' step='0.01' min='0.01' name='costo_spedizione' placeholder='9,99' required>" +
				"<br>" +
				"<label>Link Offerta </label>"+
				"<input class='field' type='text' name='link_offerta' placeholder='http://www.ikea.com/product01' required>" +
				"<br>" +
				"<label id='result'></label>" +
				"<input id ='normSend' type='submit'>"+
				"</div>"+
			"</form>" +
		"</div>" +
		"</div>");
	$("#tipo_prodotto").val('fornito');
	
	//Invia la richiesta alla servlet
	$("#normSend").click(function(event) 
	{
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		//stop submit the form, we will post it manually.
		event.preventDefault();
		if(validateFormAddProdF(document.getElementById('form_add_product')) == true)
		{
		// Get form
		var form = $('#form_add_product')[0];
		// Create an FormData object 
		var data = new FormData(form);
		// If you want to add an extra field for the FormData
		data.append("CustomField", "This is some extra data, testing");
		// disabled the submit button
		$("#normSend").prop("disabled", true);
		$.ajax({
		"type": "POST",
		"enctype": 'multipart/form-data',
		"url": "ServletAddOperation"+session,
		"data": data,
		"processData": false,
		"contentType": false,
		"cache": false,
		"success": function (data) 
		{
			if(data == 'prodotto aggiunto con successo')
			{
				$("#error").empty();
				$("#error").css("padding", "15");
				$("#error").css("background-color", "rgb(0, 148, 68)");
				$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
				$("#closeError").css("filter", "hue-rotate(125deg)");
				$("#error").append(data);
				$("#error label").css("width", "100%");
				$("#fix_error_panel label").css("width", "100%");
				$("#error label").css("float", "none");
				$("#normSend").prop("disabled", false);
			}
			else
			{
				$("#error").empty();
				$("#error").css("padding", "15");
				$("#error").css("background-color", "rgba(215, 44, 44, 0.9)");
				$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
				$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
				$("#error").append(data);
				$("#error label").css("width", "100%");
				$("#fix_error_panel label").css("width", "100%");
				$("#error label").css("float", "none");
				$("#normSend").prop("disabled", false);
			}
			$("#normSend").prop("disabled", false);
		},
		"error": function (e) 
		{
			$("#error").empty();
			$("#error").css("padding", "15");
			$("#error").css("background-color", "rgba(215, 44, 44, 0.9)");
			$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
			$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
			$("#error").append("<label>"+e.responseText+"</label><br>");
			$("#error label").css("width", "100%");
			$("#fix_error_panel label").css("width", "100%");
			$("#error label").css("float", "none");
			$("#normSend").prop("disabled", false);
		}
		});
		}
		window.location.href="#add_product_fornito";
	});
	
	//Cerca i prodotti
	$("#productInput").keypress(function() 
	{
		var value = $("#productInput").val();  
		console.log(value);
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index)
		var op = 'cercaProdotti';
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": "ServletSearchDB"+session,
			"success": function(data) 
			{	
				if(data != null)
				{
					$("#productInput").empty();
					$("#productInput").append(data);
				}
			},
			"error": function(data)
			{
				$("body").empty();
				$("body").append(data.responseText);
			}
		});
		$.ajax({
			"data": 
			{
				"operation": op,
				"word": value
			}
		});
	}); 

	//Cerca gli store
	$("#storeInput").keypress(function() 
	{
		var value = $("#storeInput").val();  
		console.log(value);
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index)
		var op = 'cercaStore';
		$.ajaxSetup(
		{ 
			"type": "POST",
			"url": "ServletSearchDB"+session,
			"success": function(data) 
			{	
				if(data != null)
				{
					$("#storeInput").empty();
					$("#storeInput").append(data);
				}
			},
			"error": function(data)
			{
				$("body").empty();
				$("body").append(data.responseText);
			}
		});
		$.ajax({
			"data": 
			{
				"operation": op,
				"word": value
			}
		});
	});
	window.location.href="#add_product_fornito";
});	
	
}

/*Validazione form tramite javascript*/

/*Validazione form Aggiungi Prodotto Interno*/
function validateFormAddProdI(x)
{
	$("#error").empty();
	boolP = validateProdotto(x.product);
	boolPr = validatePrezzo(x.prezzo);
	boolQ = validateQuantity(x.quantity);
	boolD = validateDescrizione(x.descrizione);
	boolO = validateOpzioneAcq(x.select_opzione);
	boolCE = validateCodEan(x.cod_ean);
	if(boolP == true && boolPr == true && boolQ == true
			&& boolD == true && boolO == true && boolCE == true)
	return true;
	
	$("#error").css("padding", "15");
	$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
	$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
	$(document).ready(function()
	{
		$("#error label").css("width", "100%");
		$("#fix_error_panel label").css("width", "100%");
		$("#error label").css("float", "none");
		$("#fix_error_panel label").css("float", "left");
	});
	return false;
}
function validateProdotto(el)
{
	var str = /^[A-Za-z0-9 .()]+$/i;
	if(el.value.match(str))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Nome prodotto errato</label><br>");
	});
	return false;
}
function validatePrezzo(el, value)
{
	var regexp = /^\d+\.{0,1}\d{0,2}$/;
	if(regexp.test(el.value))
		return true;
	$(document).ready(function()
	{
		if(value != null && value != undefined)
		{
			$("#error").append("<label>"+value+" errato</label><br>");
			return false;
		}
		$("#error").append("<label>Prezzo errato</label><br>");
	});
	return false;
}
function validateQuantity(el)
{
	pattern = /^\d+$/;
	if(el.value.match(pattern))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Quantità errata</label><br>");
	});
	return false;
}
function validateDescrizione(el)
{
	var str = /^[A-Za-z0-9 .()!,-]+$/i;
	if(el.value.match(str))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Descrizione errata</label><br>");
	});
	return false;
}
function validateOpzioneAcq(el)
{
	if(el.value == 'CONSEGNA_DOMICILIO' || el.value == 'RITIRO_IN_SEDE')
		return true;
	$(document).ready(function()
	{
		$("#error").append("<label>opzione d'acquisto errata</label><br>");
	});
	return false;
}
function validateCodEan(el)
{
	var str = /^[A-Z0-9]{13}$/i;
	if(el.value.match(str) || el.value == '')
		return true;
	$(document).ready(function()
	{
		$("#error").append("<label>Codice ean errato</label><br>");
	});
	return false;
}

/*Validazione form Aggiungi Prodotto Fornito*/
function validateFormAddProdF(x)
{
	$("#error").empty();
	boolP = validateProdotto(x.product);
	boolSt = validateStore(x.store);
	boolPSM = validatePrezzo(x.prezzo_mese,'Prezzo mese');
	boolPI = validatePrezzo(x.prezzo_ieri,'Prezzo ieri');
	boolPIG = validatePrezzo(x.prezzo_inizio_giorno, 'Prezzo inizio giorno');
	boolPA = validatePrezzo(x.prezzo_attuale, 'Prezzo Attuale');
	boolQ = validateQuantity(x.quantity);
	boolD = validateDescrizione(x.descrizione);
	boolAq = validateOpzioneAcq(x.select_opzione);
	boolC = validateCodEan(x.cod_ean);	
	boolCS = validatePrezzo(x.costo_spedizione, 'Costo spedizione');
	boolLink = validateLinkOfferta(x.link_offerta);
	if(boolP==true && boolSt==true && boolPSM==true
		&& boolPI==true && boolPIG==true && boolPA==true
		&& boolQ==true && boolD==true && boolAq==true 
		&& boolC==true && boolCS==true &&boolLink==true )
	return true;
	
	$("#error").css("padding", "15");
	$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
	$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
	$(document).ready(function()
	{
		$("#error label").css("width", "100%");
		$("#fix_error_panel label").css("width", "100%");
		$("#error label").css("float", "none");
		$("#fix_error_panel label").css("float", "left");
	});
	return false;
}
function validateStore(x)
{
	var pattern = /^[A-Za-z]+$/;
	if(x.value.match(pattern))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Nome store errato</label><br>");
	});
	return false;
}
function validateLinkOfferta(x)
{
	pattern = /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/){1}[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
	if(x.value.match(pattern))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>link offerta errato</label><br>");
	});
	return false;
}

/*Validazione form Aggiungi Store*/
function validateFormAddStore(x)
{
	$("#error").empty();
	$('#result').empty();
	boolNS = validateNomeStore(x.store_name);
	boolPI = validatePIva(x.pIVA);
	boolVia = validateVia(x.via_store);
	boolCiv = validateCivico(x.civico);
	boolCap = validateCap(x.cap);
	boolImg = validateLogo(x.image);
	if(boolNS == true && boolPI == true && boolVia == true
		&& boolCiv == true && boolCap == true && boolImg == true)
	return true;
	
	$("#error").css("padding", "15");
	$("#error").prepend("<label>Correggi gli errori per continuare:</label><br>");
	$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");
	return false;
}
function validateCivico(el)
{
	//only number
	var str = /^(0|[1-9][0-9]*)$/;
	if(el.value.match(str))
		return true;
	
	$(document).ready(function()
	{	
		$("#error").append("<label>Civico errato</label><br>");
	});
	return false;
}
function validateCap(text)
{
	pattern = /^\d{5}$/;
	if(text.value.match(pattern))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Cap errato</label><br>");
	});
	return false;
}
function validateLogo(logo)
{
	pattern=/\.(gif|jpg|jpeg|png)$/i;
	if((pattern).test(logo.value))
		return true;
	$(document).ready(function()
	{
		$("#error").append("<label>Logo mancate o errato</label><br>");
	});
	return false;
}
function validateVia(via)
{
	 var pattern = /^[A-Za-z ]+$/;
	 if(via.value.match(pattern))
	 {
		return true;
	 }
	 $(document).ready(function()
	 {
		$("#error").append("<label>Via errata</label><br>");
	 });
	 return false;
}
function validatePIva(pIva)
{
	var str = /^[A-Z0-9]{11}$/i;
	if(pIva.value.match(str))
		return true;
	$(document).ready(function()
	{
		$("#error").append("<label>Partita iva errata</label><br>");
	});
	return false;
}
function validateNomeStore(nStore)
{
	var pattern = /^[A-Za-z]+$/;
	if(nStore.value.match(pattern))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Nome store errato</label><br>");
	});
	return false;
}
/*Validazione form Crea Utente*/
function closeError()
{
	$("#error").empty();
	$("#error").css("padding","0");
}
function checkUsername(inputText)
{
	var str = /^[a-z0-9]{5,64}$/;
	if(inputText.value.match(str))
	{
		var y = document.getElementById("checkTrueUsername");
		y.style.display = "initial";
		var x = document.getElementById("checkFalseUsername");
		x.style.display = "none";
		$("#reguser").css("position","relative");
		$("#reguser").css("left","14px");
		return true;
	}
	else
	{
		var y = document.getElementById("checkTrueUsername");
		y.style.display = "none";
		var x = document.getElementById("checkFalseUsername");
		x.style.display = "initial";
		$("#reguser").css("position","relative");
		$("#reguser").css("left","14px");
		return false;
	}
}
function checkEmail(inputText)
{
	var size = inputText.value.length;
	if(size > 254)
	{
		var y = document.getElementById("checkTrueEmail");
		y.style.display = "none";
		var x = document.getElementById("checkFalseEmail");
		x.style.display = "initial";
		$("#regemail").css("position","relative");
		$("#regemail").css("left","14px");
		return false;
	}
	else
	{

		var str = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(inputText.value.match(str))
		{
			var y = document.getElementById("checkTrueEmail");
			y.style.display = "initial";
			var x = document.getElementById("checkFalseEmail");
			x.style.display = "none";
			$("#regemail").css("position","relative");
			$("#regemail").css("left","14px");
			return true;	
		}
		else
		{
			var y = document.getElementById("checkTrueEmail");
			y.style.display = "none";
			var x = document.getElementById("checkFalseEmail");
			x.style.display = "initial";
			$("#regemail").css("position","relative");
			$("#regemail").css("left","14px");
			return false;
		}
	}

}
function checkPassword(inputText)
{
	var str = /([\wA-Z\d]*(\w|[A-Z]|\d)[!%]*).{8,64}$/;
	if(inputText.value.match(str))
	{	
		var y = document.getElementById("checkTruePassword");
		y.style.display = "initial";
		var x = document.getElementById("checkFalsePassword");
		x.style.display = "none";
		$("#regpw").css("position","relative");
		$("#regpw").css("left","14px");
		return true;
	}
	else
	{
		var y = document.getElementById("checkTruePassword");
		y.style.display = "none";
		var x = document.getElementById("checkFalsePassword");
		x.style.display = "initial";
		$("#regpw").css("position","relative");
		$("#regpw").css("left","14px");
		return false;
	}
}
function checkPasswordConf(inputText)
{
	var str = /([\wA-Z\d]*(\w|[A-Z]|\d)[!%]*).{8,64}$/;
	if(inputText.value.match(str))
	{	
		var y = document.getElementById("checkTruePassword2");
		y.style.display = "initial";
		var x = document.getElementById("checkFalsePassword2");
		x.style.display = "none";
		$("#regconfpw").css("position","relative");
		$("#regconfpw").css("left","14px");
		return true;
	}
	else
	{
		var y = document.getElementById("checkTruePassword2");
		y.style.display = "none";
		var x = document.getElementById("checkFalsePassword2");
		x.style.display = "initial";
		$("#regconfpw").css("position","relative");
		$("#regconfpw").css("left","14px");
		return false;
	}
}
function checkName(inputText)
{
	var str = /^[A-Za-z]+$/;
	if(inputText.value.match(str))
	{	
		var y = document.getElementById("checkTrueName");
		y.style.display = "initial";
		var x = document.getElementById("checkFalseName");
		x.style.display = "none";
		$("#regnome").css("position","relative");
		$("#regnome").css("left","14px");
		return true;
	}
	else
	{
		var y = document.getElementById("checkTrueName");
		y.style.display = "none";
		var x = document.getElementById("checkFalseName");
		x.style.display = "initial";
		$("#regnome").css("position","relative");
		$("#regnome").css("left","14px");
		return false;
	}
}
function checkSurname(inputText)
{
	var str = /^[A-Za-z]+$/;
	if(inputText.value.match(str))
	{	
		var y = document.getElementById("checkTrueSurname");
		y.style.display = "initial";
		var x = document.getElementById("checkFalseSurname");
		x.style.display = "none";
		$("#regcognome").css("position","relative");
		$("#regcognome").css("left","14px");
		return true;
	}
	else
	{
		var y = document.getElementById("checkTrueSurname");
		y.style.display = "none";
		var x = document.getElementById("checkFalseSurname");
		x.style.display = "initial";
		$("#regcognome").css("position","relative");
		$("#regcognome").css("left","14px");
		return false;
	}
}
function checkCF(inputText)
{
	var str = /^[a-zA-Z]{6}[0-9]{2}[a-zA-Z]{1}[0-9]{2}[a-zA-Z]{1}[a-zA-Z0-9]{3}[a-zA-Z]$/;
	if(inputText.value.match(str))
	{
		var y = document.getElementById("checkTrueCF");
		y.style.display = "initial";
		var x = document.getElementById("checkFalseCF");
		x.style.display = "none";
		$("#regcf").css("position","relative");
		$("#regcf").css("left","14px");
		return true;	
	}
	else
	{
		var y = document.getElementById("checkTrueCF");
		y.style.display = "none";
		var x = document.getElementById("checkFalseCF");
		x.style.display = "initial";
		$("#regcf").css("position","relative");
		$("#regcf").css("left","14px");
		return false;
	}
}
function validateForm(form)
{
	$("#error").empty();
	$("#error").css("padding","0");
	var valid = true;
	var boolU = checkUsername(form.username);
	var boolE = checkEmail(form.email);
	var boolCF = checkCF(form.codf);
	var boolN = checkName(form.nome);
	var boolS = checkSurname(form.cognome);
	var boolP = checkPassword(form.password);
	var boolP2 = checkPasswordConf(form.password2);
	if(boolN==false)
	{
		$("#error").append("<label>Il nome non è corretto, sono consentite solo lettere.</label><br>");
		valid = false;
	}
	if(boolS==false)
	{
		$("#error").append("<label>Il cognome non è corretto, sono consentite solo lettere.</label><br>");
		valid = false;
	}
	if(boolCF==false)
	{
		$("#error").append("<label>Il codice fiscale non è corretto.</label><br>");
		valid = false;
	}
	if(boolE==false)
	{
		$("#error").append("<label>Email non corretta.</label><br>");
		valid = false;
	}
	if(boolU == false)
	{
		/*Setto l'errore*/
		$("#error").append("<label>Username non corretto.</label><br>");
		valid = false;
	}
	var password = form.password;
	var password2 = form.password2;
	if(password.value != password2.value)
	{
		var x = document.getElementById("checkFalsePassword2");
		var y = document.getElementById("checkTruePassword2");
		var z = document.getElementById("checkTruePassword");
		var w = document.getElementById("checkFalsePassword");
		x.style.display = "initial";
		y.style.display = "none";
		w.style.display = "initial";
		z.style.display = "none";
		$("#regpw").css("position","relative");
		$("#regpw").css("left","14px");
		$("#regconfpw").css("position","relative");
		$("#regconfpw").css("left","14px");	
		$("#error").append("<label>Le password non coincidono.</label><br>");
		valid = false;
	}
	else
	{
		if(boolP==false)
		{
			$("#error").append("<label>La password non è corretta.</label><br>");
			valid = false;
		}
		if(boolP2==false)
		{
			$("#error").append("<label>La password di conferma non è corretta.</label><br>");
			valid = false;
		}
	}
	if(valid == true)
	{
		sendCreateUser();
	}
	else
	{
		$("#error").css("padding", "15");
		$("#error").prepend("<label id='lab_err'>Correggi gli errori per continuare:</label><br>");
		$("#error").prepend("<div id='fix_error_panel'><img id='closeError' src='icone/iconClose.svg' title='close' onclick='closeError()'></div>");	
	}
}