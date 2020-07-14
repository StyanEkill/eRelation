$(window).ready(function(){
	var url = "http://"+urlLocation.host+"/"+urlLocation.pathname.split("/")[1]+"/servlet";
    
	$(".add").click(function () {
    	criaModal();
    	var action = $("<input/>",{name:'action', value:13, type:"hidden"});
    	$("#form-md").attr("action", "servlet");
    	$("#form-md").append(action);
    });
    
    $(".edit").click(function () {
    	criaModal();

        var produto = $(this).closest(".card-product");
    	var modal = $(".modal");
    	console.log($(this).closest(".card-product"));
		var nome = modal.find("input[name='nome']");
		var descricao = modal.find("textarea[name='descricao']");
		var preco = modal.find("input[name='preco']");
		
		nome.val(produto.find(".product-name").text());
		descricao.val(produto.find(".product-desc").text());
		console.log(parseInt(produto.find(".product-value").text().replace("R$","")));
		preco.val(produto.find(".product-value").text().replace("R$","").replace(",","."));

        var src  = produto.find(".prod-image").attr("src");
    	$("#img-select").attr("src", src);
        var id = produto.attr("data-id");
        
	    $("#form-md").submit(function(e){
	        e.preventDefault();
	        
            var inputFile = $(this).find("input[name='imagem']");
            
        	var fd = new FormData();
        	fd.append('imagem', inputFile.prop('files')[0]);

	    	if($(this).valid()){

	    		$.ajax({
	    			url: url+"?action=14&id="+id+"&nome="+encodeISO(nome.val())+"&descricao="+encodeISO(descricao.val())+"&preco="+ encodeISO(preco.val()),
	    			method:"POST",
	    			data: fd,
        	        contentType: false,
        	        processData: false,
        	        success: function(data){
	              		var json = JSON.parse(data);
	              		if(json.cod == 101){
	                    	modal.remove();
	                    	$($(produto).find(".product-name")).text(nome.val());
	                    	$(produto).find(".product-desc").text(descricao.val());
	                    	$(produto).find(".product-value").text(preco.val());
	                    	produto.find(".prod-image").attr("src", src.split("&timestamp")[0]+"&timestamp="+new Date().getTime());
	              		}
	                    alert(json.cod);
	              	},
	                error: function (exr, sender) {
	                    alert('1001');
	            	}
	        	});
	    	}
		});
    });

    $(".exclui").click(function () {
        var id = $(this).closest(".card-product").attr("data-id");
        var produto =  $(this).closest(".card-product");
        var modal = $("<div />", { class: 'modal' });
        var mdlContent = $("<div />", { class: 'modal-content md-confirm' });
        var mdlProdContent = $("<div />", { class: 'form-content' });
        mdlProdContent.text("Deseja mesmo deletar esse produto?");
        var mdlFooter = $("<div />", { class: 'modal-footer' });
        var btConfirm =  $("<button />", { class: 'btn btn-success col-3' });
        btConfirm.text("Sim");
        var btCancel =  $("<button />", { class: 'btn btn-danger col-3' });
        btCancel.text("Não");
        
        var i = $("<i />", { class: 'fas fa-minus-circle' });
        $(btConfirm).click(function(e){
    		$.ajax({
    			url: url,
    			method:"POST",
    			data: {action: 15, id: id},
    			success: function(data){
              		var json = JSON.parse(data);
              		if(json.cod == 101){
                        modal.remove();
              			produto.remove();
              			if($(".card-product").length == 0){
              				$(".add").remove();
              				$(".array-prod").remove();
              				$(".separator").remove();
              				emptyProdutos();
              				
              			}
              		}
              			
              	}
        	});
		});
        $("body").append(modal);
        modal.append(mdlContent);
        mdlContent.append(mdlProdContent, mdlFooter);
        mdlProdContent.prepend(i);
        mdlFooter.append(btConfirm, btCancel);
        
        window.onclick = function (event) {
            if (event.target == document.querySelector(".modal"))
                modal.remove();
        };
        $(btCancel).click(function(e){
            modal.remove();
        });
    });
    
    function criaModal(){
    	 var modal = $("<div />", { class: 'modal' });
         var mdlClose = $("<span />", { class: 'close' });
         mdlClose.html("&times;");
         var mdlContent = $("<div />", { class: 'modal-content  md-form' });
         var mdlProdContent = $("<div />", { class: 'form-content' });
         var mdlFooter = $("<div />", { class: 'modal-footer' });
         var btCria =  $("<button />", { class: 'btn btn-danger', form:'form-md' });
         btCria.text("Cadastrar");

         var form = $("<form />", {action:"",method: "POST",id:'form-md', enctype:"multipart/form-data"});
         var nome = $("<input />", { placeholder: 'Titulo', required: true, name: "nome", type: 'text' });
         var desc = $("<textarea />", { placeholder: 'Descrição', required: true, name: "descricao"});
         var preco = $("<input />", { placeholder: 'Valor', name: "preco", required: true, type: 'number' });
         var divImg = $("<div/>",{ class:'img-select'});
         var img = $("<img/>",{name: 'img', id:'img-select',class:'rounded-circle', height:"100px", width:"100px"});
         var i = $("<i/>",{class:'fas fa-pencil-alt'});
         var label = $("<label />", {  name: "imagem", for: 'imagem', id:'lb-image' });
         var image = $("<input />", {  name: "imagem", id: "imagem", type: 'file', accept:".jpg"  });
         mdlProdContent.append(form);
         label.append(i);
         divImg.append(img,label);
         form.append(divImg, nome,desc,preco,image);

   		image.change(function() {
   			if (this.files && this.files[0]) {
   				img.attr('src', URL.createObjectURL(this.files[0]));
   			}
   		});
         
     	$(form).validate({
     		errorPlacement : function(label, element) {
     			label.addClass('spanError');
     			label.insertAfter(element);
     		},
     		wrapper : 'em',
     		rules : {
 				nome : {
     				required : true,
     				maxlength : 50,
     				minlength : 2
     			},
     			descricao : {
     				required : true,
     				maxlength : 300,
     				minlength : 2
     			},
     			preco : {
     				required : true
     			}
     		},
     		messages : {
     			nome : {
     				required : "*Obrigatório"
         		},
         		descricao : {
         			required : "*Obrigatório"
         		},
         		preco : {
         			required : "*Obrigatório"
         		}
     		}
     	});


         $("body").append(modal);
         modal.append(mdlContent);
         mdlContent.append(mdlClose, mdlProdContent, mdlFooter);
         mdlFooter.append(btCria);

         $(".modal .close").click(function () {
             modal.remove();
         });
         window.onclick = function (event) {
             if (event.target == document.querySelector(".modal"))
                 modal.remove();
         };
    }
    function emptyProdutos(){
    	 var empty = $("<div />", { class: 'empty' });
         var img = $("<img/>",{src:"img/empty.png",class:'col-6'});
    	 var emptyBody = $("<div />", { class: 'empty-body' });

         var h1 = $("<h1/>");
         h1.text("Ops!");
         var p = $("<p/>");
         p.text("Parece que você não possui nenhum produto cadastrado.");
         
         var btCria =  $("<button />", { class: 'add'});
         btCria.text("Novo produto");
         
         $(btCria).click(function(){
          	criaModal();
        	var action = $("<input/>",{name:'action', value:13, type:"hidden"});
        	$("#form-md").attr("action", "servlet");
        	$("#form-md").append(action);
         });
         var obs = $("<span />", { class: 'obs' });
         obs.text("Obs.: Você pode clicar no botão abaixo para cadastrar seu primeiro produto, mas somentes socios poderão comprá-los.");

         $("body").append(empty);
         $(empty).append(img, emptyBody);
         $(emptyBody).append(h1, p, obs,btCria);
         
    }
    function criaProd(id, nome, desc, val){
 			var array = $("<div />", { class: 'array-prod' });
    	 var card = $("<div />", { class: 'card-product' });
         card.attr("data-id", id);
         var img = $("<div />", { class: 'img-product', style: "background-image:url('http://localhost:8080/WebServlet/svProdutos?&codigo="+prod[i].id+"')" });
         var body = $("<div />", { class: 'product-body' });
         var name = $("<span />", { class: 'product-name' });
         name.text(nome);
         var desc = $("<span />", { class: 'product-desc' });
         desc.text(desc);
         var val = $("<span />", { class: 'product-value' });
         val.text("R$" + val.toFixed(2));
         $("#mydiv").append(array);
         array.append(card);
         card.append(img);
         card.append(body);
         body.append(name, desc, val);
    }
    function encodeISO(string){
    	return unescape(encodeURIComponent(string));
    }
});