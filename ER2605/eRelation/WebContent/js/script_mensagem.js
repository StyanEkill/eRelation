$(window).ready(function(){
	var url = "http://"+urlLocation.host+"/"+urlLocation.pathname.split("/")[1]+"/servlet";
    $.ajax({
		url: url,
		type:'POST',
		data: {action:25},
		success: function(request) {
			var sender = $(".chat-about").attr("data-user");
			var system = JSON.parse(request);
			var user = system.user;
			var ip = system.ip;
			var port = system.port;
			/*user.replace(/[./-]/g, "")*/
			connect('ws://'+ip+':'+port+'/WebServlet/socket?user='+user);
			
			// VERIFICA SE A PAGINA FOI ABERTA COM UMA CNPJ ESPECIFICA
			if (sender == "") {
			    $(".chatbox").css("display", "none");
			    var wait = $("<div />", { class: 'wait'});
			    $(".content-msn").append(wait);
			} else {
			    var json = {
			            action: 22,
			            cnpj_dest: sender
			    };
				conexao(log, json);
			}
			
			// BOTÃO ANDROID
			$(".toggle-users").click(function () {
			    $(".side-mensagens").toggleClass("active");
			});
			
			// FUNCAO QUE CRIA A CONEXÃO
			function connect(host) {
			    // CRIA A CONEXÃO
			    if ('WebSocket' in window)
			        websocket = new WebSocket(host);
			    else if ('MozWebSocket' in window)
			        websocket = new MozWebSocket(host);
			    else {
			        console.log('Navegador sem suporte para o WebSocket3.');
			        return;
			    }
			
			    // CHAMA AS FUNÇÕES A CADA MUDANÇA NO WEBSOCKET
			    websocket.onopen = onOpen;
			    websocket.onclose = onClose;
			    websocket.onmessage = onMessage;
			    websocket.onerror = onError;
			}
			
			// FUNÇÃO COM A CONEXÃO ABERTA
			function onOpen(event) {
				// FUNÇÃO COM O SCROLL ATIVO
				var oldScrollTop;
			  	$(".logs").scroll(function() {
			  	// SE ESTIVER NO FIM DA PAGINA E TIVER MENSAGENS QUE O USUARIO NÃO VIU CHAMA O UPDATE
					if($(".logs").scrollTop() >= $(".logs").prop("scrollHeight") - $(".logs").height() - $($('.talk').last()).prop("scrollHeight")){
			   			$("#bottom").remove();
		   			// SE ESTIVER ROLANDO PRA CIMA ADICIONA O BOTÃO DE ROLAR PARA BAIXO
					} else if (oldScrollTop > $(this).scrollTop() && $("#bottom").length == 0) {
				    	var bt = $("<button />", { id: 'bottom'});
				    	$(".logs").append(bt);
						var i = $("<i />", { class: 'fas fa-angle-double-down'});
				      	bt.append(i);
				      	
				      	bt.click(function(){
				          	if($('.view').length > 0){
						  		$('.logs').animate({scrollTop: (($('.logs').height()+$('.logs').prop("scrollHeight")) - $('.view').prop("scrollHeight"))}, 500);
				          	} else if($('.talk').length > 0){
				          		console.log("talk");
				          		$('.logs').animate({scrollTop: $('.logs').prop("scrollHeight")}, 500);
				          	}
				      	});
			        }
					oldScrollTop = $(this).scrollTop();
			 	});
			  	
			  	// BUSCA O STATUS DE CADA USUARIO DA LISTA AO ABRIR A CONEXÃO
			    for (var i = 0; i < $(".list-user .user").length; i++) {
			        var status = {
			            action: "search_status",
			            userFrom: $(".list-user .user")[i].getAttribute("data-user")
			        };
			        websocket.send(JSON.stringify(status));
			    }
				
			    alterUser($(".user"));
			
				// FUNÇÃO QUE ENVIA MENSAGEM AO CLICKAR NO SUBMIT
			    $("#submit").click(armazenaMsg);
			}
			function alterUser(element){
				// TROCA USUARIO AO CLICKAR NO USUARIO DO SIDEMESSAGE
			    element.click(function () {
			        $(".talk,.separator-day").remove();
			        if ($(".user").hasClass("active") && $(this).index() == $(".user.active").index()) {
			            $(".user.active").removeClass("active");
			            $(".chatbox").css("display", "none");
			            $(".chat-about").attr("data-user", "");
			            var wait = $("<div />", {class: 'wait'});
			            $(".content-msn").append(wait);
			        } else {
				        $(this).insertBefore('.user:first-of-type');
				        
			            $(".chatbox").css("display", "flex");
			            $(".chat-header .img-user").css("background-image", "url(http://"+ip+":"+port+"/WebServlet/svEmpresa?cnpj=" + $(this).attr("data-user") + ")");
			            $(".user.active").removeClass("active");
			            $(this).addClass("active");
			            $(".wait").remove();
			
			            sender = $(this).attr("data-user");
			            $(".chat-about").attr("data-user", sender);
			            var name = $(this).find(".about .name").text();
			            var status = $(this).find(".about .status").text();
			
			            $(".chat-with").text("Conversando com " + name);
			            $(".chat-about .status").text(status);
			            $(".chat-about .status").addClass(status);
			            
			            var json = {
			                action: 22,
			                cnpj_dest: sender
			            };
			        	conexao(log, json);
			        	
			        	$(this).find(".number-message").remove();
			        }
			    });
			}
			
			function armazenaMsg(){
			    var message = $("textarea").val();
			    $("#message").val("");
			    $("#message").css("height", "30px");
			    $("textarea").focus();
				var d = new Date();
			    if (message != "") {
			        var json = {
			            action: 20,
			            cnpj_dest: sender,
			            cnpj_origem: user,
			            message: message,
			            data: d.toLocaleDateString(),
			            horario: d.toLocaleTimeString([],{hour: '2-digit', minute:'2-digit'})
			        };
			
			        var lastTalk = $(".talk").get(-1);
			        var lastTalkDay = $(lastTalk).parent().find(".day");
			        
			        if(lastTalkDay.text() != d.toLocaleDateString()) {
			            var div = $("<div />", {class: 'separator-day'});
			            var span = $("<span />", {class: 'day'});
			            span.html(json.data);
			   			$(".logs").append(div);
			   			div.append(span);
			   		}
			        $.ajax({
						url: url,
						type:'POST',
						data: json,
						success: function(request) {
							if(JSON.parse(request).cod == 101){
								json.action = "submit_message";
								lastMessage(json, user);
				                websocket.send(JSON.stringify(json));
							}
						}
					});
			    } else
			        alert("Digite alguma mensagem.");
			}
			
			// FUNÇÃO QUE ESCREVE NO PROMPT AO DAR ERRO
			function onMessage(message) {
			    var ms = message.data;
			    var j = JSON.parse(ms);
			    
			    switch (j.action) {
			        case "recive_message":
	            		lastMessage(j, user);
			            break;
			        case "send_status":
			            for (var i = 0; i < $(".list-user .user").length; i++) {
			                if (j.cnpj_origem == $(".list-user .user")[i].getAttribute("data-user")) {
			                    $(".list-user .user")[i].querySelector(".status").classList.remove($(".list-user .user")[i].querySelector(".status").classList[1]);
			                    $(".list-user .user")[i].querySelector(".status").classList.add(j.message.toLowerCase());
			                    $(".list-user .user")[i].querySelector(".status").innerText = j.message;
			                }
			            }
			
			            if (j.cnpj_origem == $(".chat-about").attr("data-user")) {
			                document.querySelector(".chat-about .status").classList.remove(document.querySelector(".chat-about .status").classList[1]);
			                document.querySelector(".chat-about .status").classList.add(j.message.toLowerCase());
			                document.querySelector(".chat-about .status").innerText = j.message;
			            }
			            break;
		            case "recive_view":
		            	if(j.cnpj_origem == $(".chat-about").attr("data-user")){
		            		console.log("rebeci");
				            for (var i = 0; i < $(".self .fa-check").length; i++) {
				            	$($(".self .fa-check")[i]).addClass('fa-check-double');
				            	$($(".self .fa-check")[i]).removeClass('fa-check');
				            }
		            	}
		            	
		            break;
		            
		            case "caixa_entrada":
		            	spawnNotification(j.title,j.message,j.href,{cnpjDest:j.cnpj_origem});
		            	break;
			        default:
			            console.log(j.message);
			            break;
			    }
			}
			
			// FUNÇÃO QUE ADICIONA UMA MENSAGEM NO LOG
			function addMessage(log, user) {
			    var lastTalk = $(".talk").get(-1);
			    var lastTalkDay = $(lastTalk).parent().find(".day");
			    
			    var p = $("<p />");
			    p.html(log.message.replace(/\n/g, "<br>"));
			    
			    var time = $("<span />", { class: 'msg-time'});
			    time.html(log.horario);
			    
			    var info = $("<div/>", {class: "msg-info"});
			    info.append(time);
				
			    var who;
			   	if(log.cnpj_origem == user){
			   		who = "self";
			   		if(log.status){
					    var view = $("<i />", { class: 'fas fa-check-double'});
					} else {
					    var view = $("<i />", { class: 'fas fa-check'});
					}
					info.append(view);
			   	}else{
			   		who = "partner";
			   	}
		    	var user = $("<div />", {class: "talk " + who});
		        user.append(p, info);
		        user.attr("id-msg",log.id);
		        if($($(".separator-day").get(-1)).find(".day").text() == log.data)
		        	$($(".separator-day").get(-1)).append(user);
	        	else {
	        		var div = $("<div />", {class: 'separator-day'});
		            var span = $("<span />", {class: 'day'});
		            span.html(log.data);
		   			$(".logs").append(div);
		   			div.append(span, user);
	        	}
	        		
			}
			
			// ADICIONA ULTIMA MENSAGEM HA LISTA DE USUARIOS
			function lastMessage(json, user) {
			    var searchUser = false;
			    // SE ENVIAR MENSAGEM
			    if (json.cnpj_origem == user) {
			        addMessage(json, user);
			        for (var i = 0; i < $(".list-user .user").length; i++) {
			            if ($(".chat-about").attr("data-user") == $(".list-user .user")[i].getAttribute("data-user")) {
			                $(".list-user .user")[i].querySelector(".last-message").innerText = "Você: " + json.message;
			                $(".list-user .user")[i].querySelector(".last-hour").innerText = json.horario;
			                $('.logs').animate({scrollTop: $('.logs').prop("scrollHeight")}, 500);
					        
			                if($(".view").length > 0){
			                	$(".view").remove();
			                }
			                break;
			            }
			        }
			    // SE RECEBER MENSAGEM E O LOG ESTIVER FECHADO
			    } else {
			    	// BUSCA SE QUEM MANDOU MENSAGEM JA HAVIA ENVIADO
			        for (var i = 0; i < $(".list-user .user").length; i++) {
			            if (json.cnpj_origem == $(".list-user .user")[i].getAttribute("data-user")) {
			                $(".list-user .user")[i].querySelector(".last-message").innerText = json.message;
			                $(".list-user .user")[i].querySelector(".last-hour").innerText = json.horario;
			                
			                if($(".list-user .user")[i].querySelector(".number-message") == null && json.cnpj_origem != $(".chat-about").attr("data-user")){
			                	var numberMsg =  $("<span />", {class: 'number-message'});
			                	numberMsg.html(1);
			                	$($(".list-user .user")[i].querySelector(".about .status")).after(numberMsg);
			            	} else if(json.cnpj_origem != $(".chat-about").attr("data-user")) {
			            		$($(".list-user .user")[i]).insertAfter('.user.active')
			                	$(".list-user .user")[i].querySelector(".number-message").innerText = parseInt($(".list-user .user")[i].querySelector(".number-message").innerText)+1;
			            	}
			                searchUser = true;
			                break;
			            }
			        }
			        
			        // SE RECEBEU A MENSAGEM E NÃO ESTIVER NA LISTA DE USUARIOS
			        if (searchUser == false) {
			            var user = $("<div />", {class: 'user','data-user': json.cnpj_origem});
			            var name = $("<div />", {class: 'name'});
			            name.text(json.empresa);
			            
			            var about = $("<div />", {class: 'about'});
			            var img = $("<div />", {class: 'img-user', style: 'background-image:url(http://'+ip+':'+port+'/WebServlet/svEmpresa?cnpj=' + json.cnpj_origem + ');' });
			            var lastHour = $("<span />", {class: 'last-hour'});
			            lastHour.text(json.horario);
			            var status = $("<span />", { class: 'status online'});
			            status.text("Online");
			            var lastMessage = $("<span />", {class: 'last-message'});
			            lastMessage.text(json.message);
			        	var numberMsg = $("<span />", {class: 'number-message'});
			        	numberMsg.text(1);
			            
			            $(".list-user").append(user);
			            user.append(img, about);
			            about.append(name, lastHour, status, numberMsg, lastMessage);
			            
		                user.insertAfter('.user.active');
		                
		                alterUser(user);

			        // SE RECEBEU A MENSAGEM E O CHATBOX QUE ESTA ABERTO FOR QUEM ENVIOU A MENSAGEM
			        } else if (searchUser == true && json.cnpj_origem == $(".chat-about").attr("data-user")){
			            addMessage(json, user);
			    		conexao(updateMsg,{action: 21, cnpj_dest:$(".chat-about").attr("data-user")});
			    		if($("#bottom").length == 0){
					  		$('.logs').animate({scrollTop: $(".logs").prop("scrollHeight") - $(".logs").height()}, 500);
			        	} else if($("#bottom .new-msg").length == 0) {
			    			var new_msg = $("<span />", { class: 'new-msg'});
			    			new_msg.text(1);
			    			$("#bottom").append(new_msg);
			    		} else {
			    			$("#bottom .new-msg").text(parseInt($("#bottom .new-msg").text())+1);
			    		}
			        }
			    }
			}
			
			function conexao(funcao, data){
				$.ajax({
					url: url,
					type:'POST',
					data: data,
					success: function(request) {
						funcao(JSON.parse(request));
					}
				});
			}
			// FUNÇÃO QUE CARREGA AS MENSAGENS NO .LOG
			function log(json){
				if(!!json.lidas){
			    	for (var i = 0; i < json.lidas.length; i++){
			            var div = $("<div />", {class: 'separator-day'});
			            var span = $("<span />", {class: 'day'});
			            span.html(json.lidas[i].dia);
			   			$(".logs").append(div);
			   			div.append(span);
			       		for (var j = 0; j < json.lidas[i].log.length; j++){
			         		addMessage(json.lidas[i].log[j], user);
			       		}
			        }
				}
				if(!!json.nao_lidas && json.nao_lidas.length > 0){
					var view = $("<div/>", {class: "view"});
					var spanView = $("<span/>");
					spanView.text(json.nao_lidas.length+" Mensagens não lidas");
					$($(".separator-day").get(-1)).append(view);
					view.append(spanView);
					for (var i = 0; i < json.nao_lidas.length; i++){
			     		addMessage(json.nao_lidas[i], user);
			        }
		    		conexao(updateMsg, {action: 21, cnpj_dest:$(".chat-about").attr("data-user")});
				}
			  	if($('.view').length > 0){
			  		$('.logs').animate({scrollTop: (($(".logs").height()+$('.logs').prop("scrollHeight")) - $('.view').prop("scrollHeight"))}, 500);
			  	} else if($('.separator-day').length > 0){
			  		$('.logs').animate({scrollTop: $('.logs').prop("scrollHeight")}, 500);
			  	}
			}
			
			function updateMsg(){
				var jsonView = {
    				action: "send_view",
    				cnpj_origem: user,
    				userFrom: $(".chat-about").attr("data-user")
				}
				websocket.send(JSON.stringify(jsonView));
			}
			
			$("#mySidebar button").click(function () {
			    websocket.close();
			});
			
			// FUNÇÃO QUE FECHA A CONEXÃO
			function onClose(event) {
				if(event.code == 1001 && Boolean(event.reason)) {
					criaModalDisconnect(event);
				} else if(event.code != 1001 && event.code != 1000) {
					criaModalDisconnect(event);
				}
			}
			// FUNÇÃO QUE ESCREVE NO PROMPT AO DAR ERRO
			function onError(event) {
			    console.log('WebSocket error (' + event.data + ').');
			}
			
			$("input[name='search']").on("keyup", function() {
			    var value = $(this).val().toLowerCase().normalize("NFD");
			    $(".list-user .user").filter(function() {
			      $(this).toggle($(this).text().toLowerCase().normalize("NFD").indexOf(value) > -1)
			    });
			  });
			function spawnNotification(title, body, pathname, data) {
	    		var opcoes = {
	    			opt: {
    			    	body: body,
    			    	icon: "icons/logo.png"
	    			},
	    			title: title,
	    			pathname: pathname,
	    			data: data
	    		}
	    		if(opcoes.pathname != "mensagem.jsp"){
		    		var n = new Notification(opcoes.title, opcoes.opt);
		    		setTimeout(function(){n.close()},5000);
		    		n.addEventListener("click", function() {
		    			n.close();
		    			window.focus();
		    			if(!!opcoes.pathname){
		    			   	window.location.href = "http://localhost:8081/eRelation/"+opcoes.pathname+"?cnpjDest="+data.cnpjDest;
		    			}
		    		});
	    		}
	    	}
			
		}
	});
});