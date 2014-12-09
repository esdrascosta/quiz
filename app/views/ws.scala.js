@(perguntaNickName: String)

var WS = window['MozWebSocket'] ? window['MozWebSocket'] : WebSocket;

var socket = new WS("@routes.RespostaController.webInfo().webSocketURL(request)");

socket.onmessage = function(event){
	var resultado = JSON.parse(event.data);
	drawChart(resultado);
}	

$(document).ready(function () { 
	
		$.ajax({  
           type: 'POST',  
           dataType: 'json',  
           contentType: 'application/json',  
           url: "@routes.RespostaController.respostasJson(perguntaNickName)",  
           data: '{}',  
           success: drawChart 
		});
	
    });
	
function drawChart(resultado) {

   	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Task');
	data.addColumn('number', 'Hours per Day');
	      
	for(var i = 0; i < resultado.length; i++){
	   data.addRow([resultado[i].alternativa,resultado[i].quantidade]);
	}

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
      
    chart.draw(data, {title: 'Resultado da Questão - @perguntaNickName'});
}