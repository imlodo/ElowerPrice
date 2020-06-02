/**
Add plus categoria
 */
$(document).ready(function()
{
    var max_fields = 5;
    var wrapper = $("#categ");
    var add_button = $(".add_categoria");

    var x = 1;
    $(add_button).click(function(e)
    {
        e.preventDefault();
        if (x < max_fields)
        {
            x++;
            $(wrapper).append(
            "<div id='categ'>" +
            	"<label>Seleziona Categoria+</label>" +
            	"<input class='field' id='"+x+"categ' list='selectCategoria"+x+"' name='categorie[]' placeholder='Felpe' required>" +
            		"<datalist id='selectCategoria"+x+"'>"+
            		"</datalist>"+
            	"<button type='button' class='delete'><span style='font-size:16px; font-weight:bold;'>- </span></button>" +
            "</div>"); //add input box

            var y = $("#url").val();
        	var index = y.indexOf(";");
        	var session = y.substring(index)
        	var op = 'cercaCategoria';

        	$("#"+x+"categ").keypress(function()
        	{
        		var value = $("#"+x+"categ").val();
        		console.log(value);
        		$.ajaxSetup(
        		{
        			"type": "POST",
        			"url": "ServletSearchDB"+session,
        			"success": function(data)
        			{
        				if(data != null)
        				{
        					$("#"+x+"categ").empty();
        					$("#"+x+"categ").append(data);
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
        			"word": value,
        			"num": x,
        		}
        	});
        	});
        }
        else
        {
            alert('Limite raggiunto, max 5!');
        }
    });

    $(wrapper).on("click", ".delete", function(e) {
        e.preventDefault();
        $(this).parent('div').remove();
        x--;
    })
});

/*Add plus specifica tecnica*/
$(document).ready(function()
{
	var max_fields = 7;
	var wrapper = $("#specifiche");
	var add_button = $(".add_specifiche");

	var x = 1;
	$(add_button).click(function(e)
	{
		e.preventDefault();
		if (x < max_fields)
		{
			x++;
			$(wrapper).append(
			"<div>" +
				"<label>Seleziona Specifica</label>" +
				"<input class='field' id='"+x+"spec' type='text' list='selectSpecifica"+x+"' name='nomeSpecifiche[]' placeholder='Fodera' autocomplete='off' required>"+
				"<datalist id='selectSpecifica"+x+"'>"+
				"</datalist> "+
				"<button type='button' class='delete'><span style='font-size:16px; font-weight:bold;'>- </span></button>" +
		         "<br> " +
		         "<label>Descrizione specifica</label>" +
		         "<textarea class='field' name='descrSpecifiche[]' placeholder='Descrizione Specifica'></textarea>"+
		     "</div>"); //add input box

			var y = $("#url").val();
        	var index = y.indexOf(";");
        	var session = y.substring(index)
        	var op = 'cercaNomeSpecifica';

        	$("#"+x+"spec").keypress(function()
        	{
        		var value = $("#"+x+"spec").val();
        		console.log(value);
        		$.ajaxSetup(
        		{
        			"type": "POST",
        			"url": "ServletSearchDB"+session,
        			"success": function(data)
        			{
        				if(data != null)
        				{
        					$("#"+x+"spec").empty();
        					$("#"+x+"spec").append(data);
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
        			"word": value,
        			"num": x,
        		}
        	});
        	});
		}
		else
		{
		    alert('Limite raggiunto, max 7!');
		}
	});

	$(wrapper).on("click", ".delete", function(e)
	{
		e.preventDefault();
		$(this).parent('div').remove();
		x--;
	});
});

//Cerca la categoria
$("document").ready(function()
{
	var x = $("#url").val();
	var index = x.indexOf(";");
	var session = x.substring(index)
	var op = 'cercaCategoria';
	$("#1categ").keypress(function()
	{
		var value = $("#1categ").val();
		$.ajaxSetup(
		{
			"type": "POST",
			"url": "ServletSearchDB"+session,
			"success": function(data)
			{
				if(data != null)
				{
					$("#1categ").empty();
					$("#1categ").append(data);
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
				"word": value,
				"num": "1",
			}
		});
	});
});

//Cerca la specifica
$("document").ready(function()
{
	var x = $("#url").val();
	var index = x.indexOf(";");
	var session = x.substring(index)
	var op = 'cercaNomeSpecifica';
	$("#1spec").keypress(function()
	{
		var value = $("#1spec").val();
		$.ajaxSetup(
		{
			"type": "POST",
			"url": "ServletSearchDB"+session,
			"success": function(data)
			{
				if(data != null)
				{
					$("#1spec").empty();
					$("#1spec").append(data);
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
				"word": value,
				"num": "1",
			}
		});
	});
});

//Send Add
//Invia la richiesta alla servlet
$("document").ready(function()
{
	$("#normSend").click(function(event)
	{
		var x = $("#url").val();
		var index = x.indexOf(";");
		var session = x.substring(index);
		//stop submit the form, we will post it manually.
		event.preventDefault();
		if(validateFormAddProdotto(document.getElementById('form_add_product')) == true)
		{
		// Get form
		var form = $('#form_add_product')[0];
		// Create an FormData object
		var data = new FormData(form);
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
				$("#error").append(e.responseText);
				$("#error label").css("width", "100%");
				$("#fix_error_panel label").css("width", "100%");
				$("#error label").css("float", "none");
				$("#normSend").prop("disabled", false);
			}
		});
		}
	});
});

function validateFormAddProdotto(x)
{
	$("#error").empty();
	boolNP = validateProdotto(x.prod_name);
	boolC = true;
	var data = document.getElementsByName("categorie[]");
	for(i = 0; i<data.length; i++)
	{
		if(validateCategoria(data[i]) == true && boolC == true)
			boolC = true;
		else
		{
			boolC = false;
			break;
		}
	}
	var data2 = document.getElementsByName("nomeSpecifiche[]");
	BoolS = true;
	for(i = 0; i<data2.length; i++)
	{
		if(validateSpecificaName(data2[i]) == true && BoolS == true)
			boolS = true;
		else
		{
			boolS = false;
			break;
		}
	}
	var data3 = document.getElementsByName("descrSpecifiche[]");
	BoolDS = true;
	for(i = 0; i<data3.length; i++)
	{
		if(validateSpecificaDesc(data3[i]) == true && BoolDS == true)
			BoolDS = true;
		else
		{
			BoolDS = false;
			break;
		}
	}
	boolL = validateLogo(x.image);
	if(boolNP==true && boolC==true && BoolS==true &&
			BoolDS==true && boolL==true)
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
function validateSpecificaName(el)
{
	var str = /^[A-Za-z]+$/;
	if(el.value.match(str))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Nome specifica errato</label><br>");
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
function validateSpecificaDesc(el)
{
	var str = /^[A-Za-z0-9 .()!,-]+$/i;
	if(el.value.match(str))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Descrizione specifica errata</label><br>");
	});
	return false;
}
function validateCategoria(el)
{
	var str = /^[A-Za-z]+$/i;
	if(el.value.match(str))
	{
		return true;
	}
	$(document).ready(function()
	{
		$("#error").append("<label>Nome categoria errato</label><br>");
	});
	return false;
}
