$(window).ready(function(){
	var url = "http://"+urlLocation.host+"/"+urlLocation.pathname.split("/")[1]+"/servlet";
    $.ajax({
		url: url,
		type:'POST',
		data: {action:25},
		success: function(request) {
			var user = JSON.parse(request).user;

			var urlLocation = new URL(window.location.href);
			var params = new URLSearchParams(urlLocation.search);
			if(params.get("ac") == 1){
				criaModalAgd();
			}
			
            $(".agenda").click(function () {
            	criaModalAgd();
            });
            function criaModalAgd(){
        	 	var modal = $("<div />", { class: 'modal' });
                var mdlClose = $("<span />", { class: 'close' });
                mdlClose.html("&times;");
                var mdlContent = $("<div />", { class: 'modal-content' });
                var mdlProdContent = $("<div />", { class: 'form-content' });
                var mdlFooter = $("<div />", { class: 'modal-footer' });
                var btAgenda =  $("<button />", { class: 'btn btn-danger', form:'form-md' });
                btAgenda.text("Agendar");

                var form = $("<form />", {action:"",method: "POST",id:'form-md'});
                var title = $("<input />", { placeholder: 'Titulo', required: true, name: "titulo", type: 'text' });
                var desc = $("<textarea />", { placeholder: 'Descrição', required: true, name: "descricao"});
                var data = $("<input />", {required: true, type: 'date', name:"data", min:new Date().toISOString().split("T")[0] });
                var h = new Date().toLocaleTimeString().split(':');
                var hora = $("<input />", {required: true, type: 'time', name:"horario"});
                mdlProdContent.append(form);
                form.append(title,desc,data,hora);
                
                var valida = {// DEFINE OS REQUISITOS DO INPUT
                				titulo : {
		            				required : true,
		            				maxlength : 50,
		            				minlength : 2
		            			},
		            			descricao : {
		            				required : true,
		            				maxlength : 300,
		            				minlength : 2
		            			},
		            			data : {
		            				required : true,
		            				min: new Date().toISOString().split("T")[0]
		            			},
		            			horario : {
		            				required : true
		            			}
		            		};
                
                $(data).on("input", function(){ // VALIDA SE O DATA SELECIONADO É HOJE 
                	if($(this).val() == new Date().toISOString().split("T")[0]){ // SE FOR HOJE HORARIO NÃO PODE SER MENOR QUE A HORA ATUAL
                		$("input[name='horario']").attr('min',h[0]+":"+h[1]);
                		valida.horario.min  = h[0]+":"+h[1];
                	} else {
                		$("input[name='horario']").removeAttr('min');
                		delete valida.horario.min;
                	}
                });
                
            	$(form).validate({
            		errorPlacement : function(label, element) {
            			label.addClass('spanError');
            			label.insertAfter(element);
            		},
            		wrapper : 'em',
            		rules : valida,
            		messages : {
            			titulo : {
            				required : "*Obrigatório"
	            		},
	            		descricao : {
	            			required : "*Obrigatório"
	            		},
	            		telefone : {
	            			required : "*Obrigatório"
	            		},
	            		data : {
	            			required : "*Obrigatório",
	            			min: "Digite uma data valida"
	            		},
	            		horario : {
	            			required : "*Obrigatório",
	            			min:"Digite um horário valido."
	            		}
            		}
            	});


                $(form).submit(function(e){
                    e.preventDefault();
                	if($(this).valid()){
	                	$.post(url,
                			{action:9, cnpjDest: $(".letter-perfil").attr("data-user"), titulo:title.val(), descricao:desc.val(), data:data.val(), horario:hora.val()},
                			function(data){
		                		var json = JSON.parse(data);
	                            alert(json.cod, 'Sucesso ao criar o evento!');
		                		if(json.cod == 101)
		                            modal.remove();
		                	}
	                	);
                	}
       			});

                $("body").append(modal);
                modal.append(mdlContent);
                mdlContent.append(mdlClose, mdlProdContent, mdlFooter);
                mdlFooter.append(btAgenda);

                $(".modal .close").click(function () {
                    modal.remove();
                });
                window.onclick = function (event) {
                    if (event.target == document.querySelector(".modal"))
                        modal.remove();
                };
            }
			
            $(".modal-prod").click(function () {
	                var modal = $("<div />", { class: 'modal' });
	                var mdlClose = $("<span />", { class: 'close' });
	                mdlClose.html("&times;");
	                var mdlContent = $("<div />", { class: 'modal-content ped' });
	                var mdlProdContent = $("<div />", { class: 'prod-content' });
	                var mdlFooter = $("<div />", { class: 'modal-footer' });
	                var total = $("<div />", { class: 'total' });
	                total.text("TOTAL R$ ");
	                var span = $("<span />");
	                span.text("0");
	                var btCompra =  $("<button />", { class: 'btn btn-primary' });
	                btCompra.text("Comprar");
	
	                $(btCompra).click(function(e){
	                    e.preventDefault();
	                	var data= "?action=17&cnpj="+$(".letter-perfil").attr("data-user");
	                	for (var i = 0; i < $(".modal .card-product.active").length;i++){
	                		data += "&id="+$($(".modal .card-product.active")[i]).attr("data-id")+"&qnt="+$($(".modal .card-product.active")[i]).find("input[name='qnt']").val();
	                	}
	                	
	                	$.post(url+data, function(request) {
		                		var json = JSON.parse(request);
	                            alert(json.cod, 'Pedido realizado com sucesso!');
		                		if(json.cod == 101)
		                            modal.remove();
            				}
            			);
           			});

	                $("body").append(modal);
	                modal.append(mdlContent);
	                mdlContent.append(mdlClose, mdlProdContent, mdlFooter);
	                total.append(span);
	                mdlFooter.append(total,btCompra);
	                $.ajax({
            			url: url,
            			type:'POST',
            			data: {action:16,cnpj:$(".letter-perfil").attr("data-user")},
            			success: function(request) {
        					var prod = JSON.parse(request);
			                for (var i = 0; i < prod.length; i++) {
			                    var card = $("<div />", { class: 'card-product' });
			                    card.attr("data-id", prod[i].id);
			                    var img = $("<div />", { class: 'img-product', style: "background-image:url('http://localhost:8080/WebServlet/svProdutos?&codigo="+prod[i].id+"')" });
			                    var body = $("<div />", { class: 'product-body' });
			                    var name = $("<span />", { class: 'product-name' });
			                    name.text(prod[i].nome);
			                    var desc = $("<span />", { class: 'product-desc' });
			                    desc.text(prod[i].descricao);
			                    var val = $("<span />", { class: 'product-value' });
			                    val.text("R$" + prod[i].preco.toFixed(2));
			                    mdlProdContent.append(card);
			                    card.append(img);
			                    card.append(body);
			                    body.append(name, desc, val);
			
			                    $(card).click(function(e){
			                        console.log(e.target);
			                        if(!$(this).hasClass("active") && e.target != this.querySelector(".cancel")){
			                            var btCancel = $("<button />", { class: 'cancel' });
			                            btCancel.html("&times;");
			                            var qnt = $("<div />", { class: 'group-qnt' });
			                            $(this).addClass("active");
			                            var inp = $("<input />", { class:'qnt', name:"qnt" });
			                            inp.val(1);
			                            $(this).prepend(btCancel);
			                            $(this).append(qnt);
			                            $(qnt).text("x");
			                            var mais = $("<button />", { class: 'mais' });
			                            mais.text("+");
			                            var menos = $("<button />", { class: 'menos' });
			                            menos.text("-");
			                            $(qnt).append(menos);
			                            $(qnt).prepend(mais,inp);
			
			                            $(mais).click(function(){
			                                $(this).parent().find("input[name='qnt']").val(parseInt($(this).parent().find("input[name='qnt']").val())+1);
			                                calc();
			                            });
			                            $(menos).click(function(){
			                                if(parseInt($(this).parent().find("input[name='qnt']").val()) > 1){
			                                    $(this).parent().find("input[name='qnt']").val(parseInt($(this).parent().find("input[name='qnt']").val())-1);
			                                    calc();
			                                }
			                            });
			                            calc();
			                        } else if(e.target == this.querySelector(".cancel")){
			                            $(this).find(".group-qnt").remove();
			                            $(this).removeClass("active");
			                            $(this).find(".cancel").remove();
			                            calc();
			                        }
			                        function calc(){
			                            var total = 0;
			                            for(var i = 0; i < $(".card-product.active").length;i++){
			                                var card = $(".card-product.active")[i];
			                                var preco = parseFloat($(card).find(".product-value").text().replace("R$",""));
			                                var qnt = parseFloat($(card).find("input[name='qnt']").val());
			                                total += preco * qnt;
			                            }
			                            $(".modal-footer .total span").text(total.toFixed(2));
			                        }
			                    });
			                }
        				}
	                });

	                $(".modal .close").click(function () {
	                    modal.remove();
	                });
	                window.onclick = function (event) {
	                    if (event.target == document.querySelector(".modal"))
	                        modal.remove();
	                };
                });
			}
    	});
        function alert(codigo, msgSuccess){
        	if($('.notify').length <= 0){
            	var cod = new Map();
            	cod.set(100,'Parâmetros fora de escopo.');
            	cod.set(101, msgSuccess);
            	cod.set(102,'Impossivel de realizar o cadastro.');
            	cod.set(103,'Erro ao cadastrar. Houve um problema interno.');
            	cod.set(300,'Erro ao cadastrar. Houve um problema interno.');
            	
            	var notify = $("<div />", { class:'notify' });
            	
            	if(codigo == 101){
                	var divIcon = $("<div />", { class: 'rounded-notify success'});
                	var icon = $("<i />", {class: 'fas fa-check'});
            	} else if(codigo == 100 || codigo == 102){
                	var divIcon = $("<div />", { class: 'rounded-notify warning'});
                	var icon = $("<i />", {class: 'fas fa-exclamation'});
            	} else {
                	var divIcon = $("<div />", { class: 'rounded-notify error'});
                	var icon = $("<i />", {class: 'fas fa-times'});
            	}
                var content = $("<div />", {class:'content-notify'});
                var description = $("<p />");
               	description.text(cod.get(codigo));
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
});