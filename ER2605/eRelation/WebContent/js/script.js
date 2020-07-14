var carregou = false;
var e = null;
var urlLocation = new URL(window.location.href);
var params = new URLSearchParams(urlLocation.search);
console.log(urlLocation)
$(document).ready(function(){
	/*----------------------------------- CRIA LOADING ----------------------------------- */
	var loader = $("<div />", {class: 'loader'});
	for(var i = 0; i < 3; i++){
	    var span = $("<span />");
	    loader.prepend(span);
	}
	if(carregou == false){
		$("body").append(loader);
	}
	
	if(params.get("e") != null){
		e = params.get("e");
	}
	
	/*----------------------------------- SIDEBAR ----------------------------------- */
	$("#hamb").click(function() {
		document.getElementById("mySidebar").style.width = "250px";
	});
	$(".close-side").click(function() {
		document.getElementById("mySidebar").style.width = "0";
	});
	var url = "http://"+urlLocation.host+"/"+urlLocation.pathname.split("/")[1]+"/servlet";

	if(urlLocation.pathname != "/eRelation/mensagem.jsp"){
		$.ajax({
			url: url,
			type:'POST',
			data: {action:25},
			success: function(request) {
				var system = JSON.parse(request);
				var user = system.user;
				var ip = system.ip;
				var port = system.port;;
				var host = 'ws://'+ip+':'+port+'/WebServlet/socket?user='+user;
				connect(host);
			}
		});
	}
});
window.onload = function(){
	if($(".loader").length >= 1){
		document.querySelector(".loader").remove();
	} else {
		carregou = true;
	}
	if(e != null){
		alert(params.get("e"));
		window.history.pushState("", "", urlLocation.pathname);
	}
}
function connect(host) {
    // CRIA A CONEXÃO
    if ('WebSocket' in window)
        websocket = new WebSocket(host);
    else if ('MozWebSocket' in window)
        websocket = new MozWebSocket(host);
    else {
        console.log('Get a real browser which supports WebSocket3.');
        return;
    }

    // CHAMA AS FUNÇÕES A CADA MUDANÇA NO WEBSOCKET
    websocket.onopen = open;
    websocket.onclose = close;
    websocket.onmessage = message;
    websocket.onerror = error;
}

function open(event){

	Notification.requestPermission();

}

function message(message) {
    var ms = message.data;
    var j = JSON.parse(ms);

    switch (j.action) {
        case "recive_message":
        	spawnNotification(j.empresa,j.message,"mensagem",{cnpjDest:j.cnpj_origem});
        	if($("#hamb .new").length < 0){
	        	var new_msg = $("<span />", {class: 'new'});
	        	$($(".sidebar a")[4]).append(new_msg);
	        	new_msg = $("<span />", {class: 'new'});
	        	$("#hamb").append(new_msg);
        	}
        	break;
        case "caixa_entrada":
        	spawnNotification(j.title,j.message,j.href,{cnpjDest:j.cnpj_origem});
        	if($($(".navbar div")[2]).find(".new").length < 0){
	        	var new_msg = $("<span />", {class: 'new'});
	        	$($(".navbar div")[2]).append(new_msg);
        	}
        	break;
    }
    
	function spawnNotification(title, body, pathname, data) {
		var opcoes = {
			opt: {
		    	body: body,
		    	icon: "icons/bell.png"
			},
			title: title,
			pathname: pathname,
			data: data
		}
		console.log(!!opcoes.pathname);
		console.log(opcoes.pathname);
		var n = new Notification(opcoes.title, opcoes.opt);
		setTimeout(function(){n.close()},5000);
		n.addEventListener("click", function() {
			n.close();
			window.focus();
			var href;
			switch(opcoes.pathname){
				case 'caixa_entrada':
					href = "caixaentrada.jsp";
					break;
				case 'solicitacao':
					href = "solicitacao.jsp";
					break;
				case 'agenda':
					href = "agenda.jsp";
					break;
				case 'mensagem':
					href = "mensagem.jsp?cnpjDest="+opcoes.data.cnpjDest;
					break;
				default:
					href = urlLocation.pathname.split("/")[2];
					break;
			}
			if(!!opcoes.pathname){
			   	window.location.href = "http://localhost:8081/eRelation/"+href;
			}
		});
	}
	
}
function alert(codigo){
	if($('.notify').length <= 0){
    	var cod = new Map();
    	cod.set(120, "Bem Vindo!");
    	cod.set(25, "Até mais");
    	cod.set(100, "Parâmetros fora de escopo.");
    	cod.set(101, "Ação efetuada com sucesso.");
    	cod.set(500, "Impossivel realizar a ação esperada.");
    	cod.set(102, "Impossivel de realizar a ação desejada.");
    	cod.set(103, "Erro ao realizar o evento. Houve um problema inesperado.");
    	cod.set(300, "Usuario ou senha invalida. Digite-os novamente.");
    	cod.set(1001,"Erro ao tentar se conectar com o servlet.");
    	cod.set(1002,"Você foi desconectado ou não tem permissão para acessar a página.");
    	cod.set(500, "Ação desconhecida.");
    	
    	var notify = $("<div />", { class:'notify' });

    	if(codigo == 101){
        	var divIcon = $("<div />", { class: 'rounded-notify success'});
        	var icon = $("<i />", {class: 'fas fa-check'});
    	} else if(codigo == 100 || codigo == 102 || codigo == 1002){
        	var divIcon = $("<div />", { class: 'rounded-notify warning'});
        	var icon = $("<i />", {class: 'fas fa-exclamation'});
    	} else if(codigo == 120 || codigo == 25){
        	var divIcon = $("<div />", { class: 'rounded-notify welcome'});
        	var icon = $("<i />", {class: 'fas fa-smile-wink'});
    	} else {
        	var divIcon = $("<div />", { class: 'rounded-notify error'});
        	var icon = $("<i />", {class: 'fas fa-times'});
    	}
        var content = $("<div />", {class:'content-notify'});
        var description = $("<p />");
       	description.text(cod.get(parseInt(codigo)));
        var span = $("<span />", {class:'cod'});
        span.text( "Cód.: "+codigo);
        $("body").prepend(notify);
        notify.append(divIcon, content);
        divIcon.append(icon);
        content.append(description, span);
        
        $(content).delay(2000).show(1000, function(){
        	setTimeout(function(){
        		$(content).hide(1000, function(){
            		$(notify).animate({bottom:'60px'}, 500).animate({bottom:'-120px'}, 500, function(){
            			$(notify).remove();
            		});
            	});
        	}, 5000);
        });
    } else {
		$('.notify .content-notify').hide(1000, function(){
        	$('.notify').animate({bottom:'60px'}, 500).animate({bottom:'-120px'}, 500, function(){
        		$('.notify').remove();
    		});
    	});
    }
}
function encodeUTF8(string){
	return escape(string);
}
function close(event){
	if(event.code == 1001 && Boolean(event.reason)) {
		criaModalDisconnect(event);
	} else if(event.code != 1001 && event.code != 1000) {
		criaModalDisconnect(event);
	}
}
function criaModalDisconnect(event){
	var modal = $("<div />", { class: 'modal' });
    var mdlContent = $("<div />", { class: 'modal-content md-confirm' });
    var mdlProdContent = $("<div />", { class: 'form-content' });
    mdlProdContent.text("Sua sessão com nosso servidor foi perdida. Deseja tentar reconectar?");
    var span = $("<span/>", {class:"obs pl-5 pr-5"});
    span.html("Obs.: Isso significa que, você não receberá notificações nem trocará mensagens em tempo real.<br>Err.:"+event.code+".  "+event.reason);
    var mdlFooter = $("<div />", { class: 'modal-footer' });
    var btConfirm =  $("<button />", { class: 'btn btn-primary' });
    btConfirm.text("RECONECTAR");
    var btCancel =  $("<button />", { class: 'btn btn-danger' });
    btCancel.text("SEGUIR SEM RECONECTAR");
    var i = $("<i />", { class: 'fas fa-user-slash' });
    
    $(btConfirm).click(function(e){
    	var url = "http://"+urlLocation.host+"/"+urlLocation.pathname.split("/")[1]+"/servlet";
    	$.ajax({
			url: url,
			type:'POST',
			data: {action:25},
			success: function(request) {
				var system = JSON.parse(request);
				var user = system.user;
				var ip = system.ip;
				var port = system.port;;
				var host = 'ws://'+ip+':'+port+'/WebServlet/socket?user='+user;
				connect(host);
				modal.remove();
			}
		});
	});
    
    $("body").append(modal);
    modal.append(mdlContent);
    mdlContent.append(mdlProdContent, mdlFooter);
    mdlProdContent.prepend(i);
    mdlProdContent.append(span);
    mdlFooter.append(btConfirm, btCancel);
    
    $(btCancel).click(function(e){
        modal.remove();
    });
}
function error(event){
	console.log(event);
}