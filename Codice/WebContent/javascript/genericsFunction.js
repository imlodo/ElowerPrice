/**
 * le varie funzioni vanno messe nei rispettivi file javascript associati alle relative pagine
 */
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

	// da gestire il fatto del background bianco quando non ci sta nulla nell'input 

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

function sendCreateUser()
{
	$(document).ready(function () 
	{
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index)
//		$("#btnConf").click(function (event) 
//		{
			//stop submit the form, we will post it manually.
//			event.preventDefault();
			// Get form
			var form = $('#reg')[0];
			// Create an FormData object 
			var data = new FormData(form);
			// disabled the submit button
			$("#btnConf").prop("disabled", true);

			$.ajax({
				"type": "POST",
				"enctype": 'multipart/form-data',
				"url": "ServletIscrizione"+session,
				"data": data,
				"processData": false,
				"contentType": false,
				"success": function (data) 
				{
					var success =  $($.parseHTML(data)).filter("#success");
					// data.redirect contains the string URL to redirect to
					if(success.length == 1)
					{
						$('head').find('link#css1').remove();  
					    $("body").empty();
					    $("body").append(data);
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
//		});
	});
}


//variabili globali per l'accesso

var usernameNormale="";
var passwordNormale="";

function encode(username,password)
{
	usernameNormale = username;
	passwordNormale = password;
	if(grecaptcha.getResponse() != '')
	{
		var encodeusername = btoa(username);
		var encodepassword = btoa(password);
		document.form1.username.value = encodeusername;
		document.form1.password.value = encodepassword;
		//document.form1.submit();
		//document.form1.username.value = usernameNormale;	
		//document.form1.password.value = passwordNormale;
	}
}

function submitANDrestoreForm()
{
	if(grecaptcha.getResponse() != '')
	{
		document.form1.submit();
		document.form1.username.value = usernameNormale;	
		document.form1.password.value = passwordNormale;
	}
	else
	{
		$(document).ready(function() 
		{
			$("#error").remove();
			$("#logo").after("<div id='error'>reCaptcha obbligatorio</div>");
			document.form1.username.value = usernameNormale;	
			document.form1.password.value = passwordNormale;
		});
	}
	
}


function encodePassword(password , formName)
{
	
	
	passwordNormale = password;
	var encodepassword = btoa(password);
	document.forms[formName].password.value = encodepassword;
	

}


function submitANDrestoreFormName(formName)
{
	document.forms[formName].submit();	
	document.forms[formName].password.value = passwordNormale;
}


//pagina dell'admin


var stop = 0;
//var h = document.getElementById("iframeAdmin").contentWindow.history

function goBack()
{
	//doucument.contentWindow.history.length;
	if(iframeAdmin.contentWindow.history.length)
	{
		iframeAdmin.contentWindow.history.back();
	}
	
	//h.back();
	if(stop > 1)
	{
		stop--;
		iframeAdmin.contentWindow.history.back();
	}
	else
	{
		location.reload();
	}
}

	
function goForward()
{
	stop++;
	iframeAdmin.contentWindow.history.forward();
	
}

// javascript funzio di modifica pagina 


function selectSectionAdmin()
{
	var listaFunzioni = document.getElemetById("ListaFunzioniAdmin"); // elemento al quale verranno aggiunti i figli
	var liElement = document.createElement("li");
	var spanNode = document.createElement("span"); // creiamo i figli come span
	var contentNode = document.createTextNode("")
	
	liElement.appendChild(spanNode);
	listaFunzioni.appendChild(liElement);
	
}





function ajaxCallJSONPiuCercati()
{
	$.getJSON("ServletAggiornaPiuRicercati",
			{
				  "n_el_da_mostare": document.getElementById("n_el_da_mostare").value
				, "posCorrPiuCercati": document.getElementById("posCorrPiuCercati").value
				
				//,"sizeListPiuCercati":document.getElementById("sizeListPiuCercati").value
//				,"changePiuCercati" : document.getElementById("posCorrPiuCercati")
			},
			function(json) 
			{
				/* var objects = JSON.parse(json);  */
				var value = "";   		
				var i = 0;
				var j = 0;
				var el = 1;
				
				for(var key in json) 
				{
					
//					if(i > json[key].lengt)// >=
//						break;
					
					var prodotto = "prodPiuCercati"+el.toString();
//					$("#"+prodotto).empty();
					
					document.getElementById("prodPiuCercati"+el.toString()).style.display = "inline-block";
					
					console.log(prodotto);
					console.log(json[key][0]);
					var nomeProdotto = json[key][0];
					var nomeStore = json[key][1];
					var prezzoAttuale = json[key][2];
					var prezzoIeri = json[key][3];
					var imgProdotto = json[key][4];
					//$("#"+prodotto).append();
					var divProdN = document.getElementById(prodotto).setAttribute("class" , "w3-content w3-section");
					var img = "imgPiuCercati"+el.toString();
					var buttonimg = "buttonimgPiuCercati"+el.toString();
					
					var prodNameAndStoreName = "prodNameAndStoreNamePiuCercati"+el;
					
					document.getElementById(img).setAttribute("src" ,'data:image/png;base64,'+imgProdotto);
					//remove class and add class for reset 
					document.getElementById(img).classList.remove("class","w3-animate-zoom");
					void document.getElementById(img).offsetWidth;
					document.getElementById(img).setAttribute("class","w3-animate-zoom");
					
					document.getElementById(buttonimg).classList.remove("class","w3-animate-zoom");
					void document.getElementById(buttonimg).offsetWidth;
					document.getElementById(buttonimg).setAttribute("class","w3-animate-zoom");
					
					document.getElementById(img).setAttribute("class","w3-animate-zoom");
					
					document.getElementById(prodNameAndStoreName).value = nomeProdotto+"|"+nomeStore;
					
					var prodProdName = "prodPiuCercatiProdName"+el;
					document.getElementById(prodProdName).innerHTML = nomeProdotto; 
					
					var prodStoreName = "prodPiuCercatiStoreName"+el;
					document.getElementById(prodStoreName).innerHTML = nomeStore;
					
					var prezzo1 = "prezzoIeriPiuCercati"+el;
					document.getElementById(prezzo1).innerHTML = "\u20AC"+prezzoIeri;
					
					var prezzo2 = "prezzoAttualePiuCercati"+el;
					document.getElementById(prezzo2).innerHTML = "\u20AC"+prezzoAttuale;
					
					i++;
					el++;
				}
				
//				var pcor = 	document.getElementById("posCorrPiuCercati").value;
//				document.getElementById("posCorrPiuCercati").value = parseInt(pcor) + 3 ;

//				var pcor = 	document.getElementById("n_el_da_mostare").value;
//				document.getElementById("posCorrPiuCercati").value = parseInt(pcor) ;
				
				
				if(el <= 3)
				{
					for(; el <= 3 ; el++)
					{
						var prodotto = "prodPiuCercati"+el.toString();
						document.getElementById("prodPiuCercati"+el.toString()).style.display = "none";
					}
					
				}


				
				});        




}
























//////////////////





function nextPiuCercati()
{
	var n_el_da_mostare = document.getElementById("n_el_da_mostare").value;
	var posCorrPiuCercati = document.getElementById("posCorrPiuCercati").value;
	var sizeListPiuCercati = document.getElementById("sizeListPiuCercati").value;
//	if( (parseInt(posCorrPiuCercati) + parseInt(n_el_da_mostare) ) >=  (parseInt(sizeListPiuCercati) ) )
//		return;
	if( ( (parseInt(posCorrPiuCercati) + ( parseInt(n_el_da_mostare) ) )  >=  (parseInt(sizeListPiuCercati) ) ) ) 
		return;
	
	//var arr = new Array();
	$.getJSON("ServletNextPiuCercati",
	{
		  "n_el_da_mostare": document.getElementById("n_el_da_mostare").value
		, "posCorrPiuCercati": document.getElementById("posCorrPiuCercati").value
		
		//,"sizeListPiuCercati":document.getElementById("sizeListPiuCercati").value
//		,"changePiuCercati" : document.getElementById("posCorrPiuCercati")
	},
	function(json) 
	{
		/* var objects = JSON.parse(json);  */
		var value = "";   		
		var i = 0;
		var j = 0;
		var el = 1;
		
		var nEL = 0;
		
		for(var key in json) 
		{
			nEL = nEL + 1;
//			if(i > json[key].lengt)// >=
//				break;
			
			var prodotto = "prodPiuCercati"+el.toString();
//			$("#"+prodotto).empty();
			
			console.log(prodotto);
			console.log(json[key][0]);
			var nomeProdotto = json[key][0];
			var nomeStore = json[key][1];
			var prezzoAttuale = json[key][2];
			var prezzoIeri = json[key][3];
			var imgProdotto = json[key][4];
			//$("#"+prodotto).append();
			var divProdN = document.getElementById(prodotto).setAttribute("class" , "w3-content w3-section");
			var img = "imgPiuCercati"+el.toString();
			var buttonimg = "buttonimgPiuCercati"+el.toString();
			
			var prodNameAndStoreName = "prodNameAndStoreNamePiuCercati"+el;
			
			document.getElementById(img).setAttribute("src" ,'data:image/png;base64,'+imgProdotto);
			//remove class and add class for reset 
			document.getElementById(img).classList.remove("class","w3-animate-zoom");
			void document.getElementById(img).offsetWidth;
			document.getElementById(img).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(buttonimg).classList.remove("class","w3-animate-zoom");
			void document.getElementById(buttonimg).offsetWidth;
			document.getElementById(buttonimg).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(img).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(prodNameAndStoreName).value = nomeProdotto+"|"+nomeStore;
			
			var prodProdName = "prodPiuCercatiProdName"+el;
			document.getElementById(prodProdName).innerHTML = nomeProdotto; 
			
			var prodStoreName = "prodPiuCercatiStoreName"+el;
			document.getElementById(prodStoreName).innerHTML = nomeStore;
			
			var prezzo1 = "prezzoIeriPiuCercati"+el;
			document.getElementById(prezzo1).innerHTML = "\u20AC"+prezzoIeri;
			
			var prezzo2 = "prezzoAttualePiuCercati"+el;
			document.getElementById(prezzo2).innerHTML = "\u20AC"+prezzoAttuale;
			
			i++;
			el++;
		}
		
		var pcor = 	document.getElementById("posCorrPiuCercati").value;
		document.getElementById("posCorrPiuCercati").value = parseInt(pcor) + 3 ;
		
		if(el <= 3)
		{
			for(; el <= 3 ; el++)
			{
				//var prodotto = "prodPiuCercati"+nEL.toString();
				document.getElementById("prodPiuCercati"+el.toString()).style.display = "none";
			}
			
		}
		
		});        
	
	// alla fine va fatto lo scorrimento verso sinistra dell'article
	document.getElementById("artPiuCercati").classList.remove("class","w3-animate-right");
	void document.getElementById("artPiuCercati").offsetWidth;
	document.getElementById("artPiuCercati").setAttribute("class","w3-animate-right");
	
	
}






function backPiuCercati()
{
	var n_el_da_mostare = document.getElementById("n_el_da_mostare").value;
	var posCorrPiuCercati = document.getElementById("posCorrPiuCercati").value;
	var sizeListPiuCercati = document.getElementById("sizeListPiuCercati").value;
	if( (parseInt(posCorrPiuCercati) - parseInt(n_el_da_mostare) ) <  0 )
		return;
	
	//var arr = new Array();
	$.getJSON("ServletBackPiuCercati",
	{
		  "n_el_da_mostare": document.getElementById("n_el_da_mostare").value
		, "posCorrPiuCercati": document.getElementById("posCorrPiuCercati").value
		
		//,"sizeListPiuCercati":document.getElementById("sizeListPiuCercati").value
//		,"changePiuCercati" : document.getElementById("posCorrPiuCercati")
	},
	function(json) 
	{
		/* var objects = JSON.parse(json);  */
		var value = "";   		
		var i = 0;
		var j = 0;
		var el = 1;
		
		for(var key in json) 
		{
			
//			if(i > json[key].lengt)// >=
//				break;
			
			var prodotto = "prodPiuCercati"+el.toString();
//			$("#"+prodotto).empty();
			
			document.getElementById("prodPiuCercati"+el.toString()).style.display = "inline-block";
			
			console.log(prodotto);
			console.log(json[key][0]);
			var nomeProdotto = json[key][0];
			var nomeStore = json[key][1];
			var prezzoAttuale = json[key][2];
			var prezzoIeri = json[key][3];
			var imgProdotto = json[key][4];
			//$("#"+prodotto).append();
			var divProdN = document.getElementById(prodotto).setAttribute("class" , "w3-content w3-section");
			var img = "imgPiuCercati"+el.toString();
			var buttonimg = "buttonimgPiuCercati"+el.toString();
			
			var prodNameAndStoreName = "prodNameAndStoreNamePiuCercati"+el;
			
			document.getElementById(img).setAttribute("src" ,'data:image/png;base64,'+imgProdotto);
			//remove class and add class for reset 
			document.getElementById(img).classList.remove("class","w3-animate-zoom");
			void document.getElementById(img).offsetWidth;
			document.getElementById(img).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(buttonimg).classList.remove("class","w3-animate-zoom");
			void document.getElementById(buttonimg).offsetWidth;
			document.getElementById(buttonimg).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(img).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(prodNameAndStoreName).value = nomeProdotto+"|"+nomeStore;
			
			var prodProdName = "prodPiuCercatiProdName"+el;
			document.getElementById(prodProdName).innerHTML = nomeProdotto; 
			
			var prodStoreName = "prodPiuCercatiStoreName"+el;
			document.getElementById(prodStoreName).innerHTML = nomeStore;
			
			var prezzo1 = "prezzoIeriPiuCercati"+el;
			document.getElementById(prezzo1).innerHTML = "\u20AC"+prezzoIeri;
			
			var prezzo2 = "prezzoAttualePiuCercati"+el;
			document.getElementById(prezzo2).innerHTML = "\u20AC"+prezzoAttuale;
			
			i++;
			el++;
		}
		
		var pcor = 	document.getElementById("posCorrPiuCercati").value;
		document.getElementById("posCorrPiuCercati").value = parseInt(pcor) - 3 ;

		
		});        
	
	// alla fine va fatto lo scorrimento verso sinistra dell'article
	
	document.getElementById("artPiuCercati").classList.remove("class","w3-animate-left");
	void document.getElementById("artPiuCercati").offsetWidth;
	document.getElementById("artPiuCercati").setAttribute("class","w3-animate-left");
	
	
}




//stesse funzioni per ribassi del giorno





function ajaxCallJSONRibassi()
{
	$.getJSON("ServletAggiornaRibassi",
			{
				  "n_el_da_mostare": document.getElementById("n_el_da_mostare").value
				, "posCorr": document.getElementById("posCorr").value
				
				//,"sizeListPiuCercati":document.getElementById("sizeListPiuCercati").value
//				,"changePiuCercati" : document.getElementById("posCorrPiuCercati")
			},
			function(json) 
			{
				/* var objects = JSON.parse(json);  */
				var value = "";   		
				var i = 0;
				var j = 0;
				var el = 1;
				
				for(var key in json) 
				{
					
//					if(i > json[key].lengt)// >=
//						break;
					
					var prodotto = "prod"+el.toString();
//					$("#"+prodotto).empty();
					
					document.getElementById("prod"+el.toString()).style.display = "inline-block";
					
					console.log(prodotto);
					console.log(json[key][0]);
					var nomeProdotto = json[key][0];
					var nomeStore = json[key][1];
					var prezzoAttuale = json[key][2];
					var prezzoIeri = json[key][3];
					var imgProdotto = json[key][4];
					//$("#"+prodotto).append();
					var divProdN = document.getElementById(prodotto).setAttribute("class" , "w3-content w3-section");
					var img = "img"+el.toString();
					var buttonimg = "buttonimg"+el.toString();
					
					var prodNameAndStoreName = "prodNameAndStoreName"+el;
					
					document.getElementById(img).setAttribute("src" ,'data:image/png;base64,'+imgProdotto);
					//remove class and add class for reset 
					document.getElementById(img).classList.remove("class","w3-animate-zoom");
					void document.getElementById(img).offsetWidth;
					document.getElementById(img).setAttribute("class","w3-animate-zoom");
					
					document.getElementById(buttonimg).classList.remove("class","w3-animate-zoom");
					void document.getElementById(buttonimg).offsetWidth;
					document.getElementById(buttonimg).setAttribute("class","w3-animate-zoom");
					
					document.getElementById(img).setAttribute("class","w3-animate-zoom");
					
					document.getElementById(prodNameAndStoreName).value = nomeProdotto+"|"+nomeStore;
					
					var prodProdName = "prodProdName"+el;
					document.getElementById(prodProdName).innerHTML = nomeProdotto; 
					
					var prodStoreName = "prodStoreName"+el;
					document.getElementById(prodStoreName).innerHTML = nomeStore;
					
					var prezzo1 = "prezzoIeri"+el;
					document.getElementById(prezzo1).innerHTML = "\u20AC"+prezzoIeri;
					
					var prezzo2 = "prezzoAttuale"+el;
					document.getElementById(prezzo2).innerHTML = "\u20AC"+prezzoAttuale;
					
					i++;
					el++;
				}
				
//				var pcor = 	document.getElementById("n_el_da_mostare").value;
//				document.getElementById("posCorr").value = "0" ;
				
				
				
				if(el <= 3)
				{
					for(; el <= 3 ; el++)
					{
						var prodotto = "prod"+el.toString();
						document.getElementById("prod"+el.toString()).style.display = "none";
					}
					
				}
				
				

				
				});        




}
























//////////////////





function nextRibassi()
{
	var n_el_da_mostare = document.getElementById("n_el_da_mostare").value;
	var posCorr = document.getElementById("posCorr").value;
	var sizeList = document.getElementById("sizeList").value;
	if( (parseInt(posCorr) + parseInt(n_el_da_mostare) ) >=  (parseInt(sizeList) ) )
		return;
	
	//var arr = new Array();
	$.getJSON("ServletNextRibassi",
	{
		  "n_el_da_mostare": document.getElementById("n_el_da_mostare").value
		, "posCorr": document.getElementById("posCorr").value
		
		//,"sizeListPiuCercati":document.getElementById("sizeListPiuCercati").value
//		,"changePiuCercati" : document.getElementById("posCorrPiuCercati")
	},
	function(json) 
	{
		/* var objects = JSON.parse(json);  */
		var value = "";   		
		var i = 0;
		var j = 0;
		var el = 1;
		
		for(var key in json) 
		{
			
//			if(i > json[key].lengt)// >=
//				break;
			
			var prodotto = "prod"+el.toString();
//			$("#"+prodotto).empty();
			
			console.log(prodotto);
			console.log(json[key][0]);
			var nomeProdotto = json[key][0];
			var nomeStore = json[key][1];
			var prezzoAttuale = json[key][2];
			var prezzoIeri = json[key][3];
			var imgProdotto = json[key][4];
			//$("#"+prodotto).append();
			var divProdN = document.getElementById(prodotto).setAttribute("class" , "w3-content w3-section");
			var img = "img"+el.toString();
			var buttonimg = "buttonimg"+el.toString();
			
			var prodNameAndStoreName = "prodNameAndStoreName"+el;
			
			document.getElementById(img).setAttribute("src" ,'data:image/png;base64,'+imgProdotto);
			//remove class and add class for reset 
			document.getElementById(img).classList.remove("class","w3-animate-zoom");
			void document.getElementById(img).offsetWidth;
			document.getElementById(img).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(buttonimg).classList.remove("class","w3-animate-zoom");
			void document.getElementById(buttonimg).offsetWidth;
			document.getElementById(buttonimg).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(img).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(prodNameAndStoreName).value = nomeProdotto+"|"+nomeStore;
			
			var prodProdName = "prodProdName"+el;
			document.getElementById(prodProdName).innerHTML = nomeProdotto; 
			
			var prodStoreName = "prodStoreName"+el;
			document.getElementById(prodStoreName).innerHTML = nomeStore;
			
			var prezzo1 = "prezzoIeri"+el;
			document.getElementById(prezzo1).innerHTML = "\u20AC"+prezzoIeri;
			
			var prezzo2 = "prezzoAttuale"+el;
			document.getElementById(prezzo2).innerHTML = "\u20AC"+prezzoAttuale;
			
			i++;
			el++;
		}
		
		var pcor = 	document.getElementById("posCorr").value;
		document.getElementById("posCorr").value = parseInt(pcor) + 3 ;
		
		
		if(el <= 3)
		{
			for(; el <= 3 ; el++)
			{
				var prodotto = "prod"+el.toString();
				document.getElementById("prod"+el.toString()).style.display = "none";
			}
			
		}
		
		

		
		});        
	
	// alla fine va fatto lo scorrimento verso sinistra dell'article
	document.getElementById("art").classList.remove("class","w3-animate-right");
	void document.getElementById("art").offsetWidth;
	document.getElementById("art").setAttribute("class","w3-animate-right");
	
	
}






function backRibassi()
{
	var n_el_da_mostare = document.getElementById("n_el_da_mostare").value;
	var posCorrPiuCercati = document.getElementById("posCorr").value;
	var sizeListPiuCercati = document.getElementById("sizeList").value;
	if( (parseInt(posCorrPiuCercati) - parseInt(n_el_da_mostare) ) <  0 )
		return;
	
	//var arr = new Array();
	$.getJSON("ServletBackRibassi",
	{
		  "n_el_da_mostare": document.getElementById("n_el_da_mostare").value
		, "posCorr": document.getElementById("posCorr").value
		
		//,"sizeListPiuCercati":document.getElementById("sizeListPiuCercati").value
//		,"changePiuCercati" : document.getElementById("posCorrPiuCercati")
	},
	function(json) 
	{
		/* var objects = JSON.parse(json);  */
		var value = "";   		
		var i = 0;
		var j = 0;
		var el = 1;
		
		for(var key in json) 
		{
			
//			if(i > json[key].lengt)// >=
//				break;
			
			var prodotto = "prod"+el.toString();
//			$("#"+prodotto).empty();
			
			document.getElementById("prod"+el.toString()).style.display = "inline-block";
			
			console.log(prodotto);
			console.log(json[key][0]);
			var nomeProdotto = json[key][0];
			var nomeStore = json[key][1];
			var prezzoAttuale = json[key][2];
			var prezzoIeri = json[key][3];
			var imgProdotto = json[key][4];
			//$("#"+prodotto).append();
			var divProdN = document.getElementById(prodotto).setAttribute("class" , "w3-content w3-section");
			var img = "img"+el.toString();
			var buttonimg = "buttonimg"+el.toString();
			
			var prodNameAndStoreName = "prodNameAndStoreName"+el;
			
			document.getElementById(img).setAttribute("src" ,'data:image/png;base64,'+imgProdotto);
			//remove class and add class for reset 
			document.getElementById(img).classList.remove("class","w3-animate-zoom");
			void document.getElementById(img).offsetWidth;
			document.getElementById(img).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(buttonimg).classList.remove("class","w3-animate-zoom");
			void document.getElementById(buttonimg).offsetWidth;
			document.getElementById(buttonimg).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(img).setAttribute("class","w3-animate-zoom");
			
			document.getElementById(prodNameAndStoreName).value = nomeProdotto+"|"+nomeStore;
			
			var prodProdName = "prodProdName"+el;
			document.getElementById(prodProdName).innerHTML = nomeProdotto; 
			
			var prodStoreName = "prodStoreName"+el;
			document.getElementById(prodStoreName).innerHTML = nomeStore;
			
			var prezzo1 = "prezzoIeri"+el;
			document.getElementById(prezzo1).innerHTML = "\u20AC"+prezzoIeri;
			
			var prezzo2 = "prezzoAttuale"+el;
			document.getElementById(prezzo2).innerHTML = "\u20AC"+prezzoAttuale;
			
			
			
			
			i++;
			el++;
		}
		
		var pcor = 	document.getElementById("posCorr").value;
		document.getElementById("posCorr").value = parseInt(pcor) - 3 ;

		
		});        
	
	// alla fine va fatto lo scorrimento verso sinistra dell'article
	
	document.getElementById("art").classList.remove("class","w3-animate-left");
	void document.getElementById("art").offsetWidth;
	document.getElementById("art").setAttribute("class","w3-animate-left");
	
	
}


function effectsHomePage()
{
	document.getElementById("asideHomePage").setAttribute("class","w3-animate-left");
	document.getElementById("section2").setAttribute("class","w3-animate-right");
	document.getElementById("footer").setAttribute("class","w3-animate-bottom");
	//document.getElementById("log").setAttribute("class","w3-animate-top");
	
}


function init()
{
	effectsHomePage();
	ajaxCallJSONRibassi();
	ajaxCallJSONPiuCercati();
	
	
}

