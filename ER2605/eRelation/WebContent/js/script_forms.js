
$(window).ready(function(){
	// script q atualiza a imagem
	if($('input[type="file"]').length > 0){
		document.querySelector('input[type="file"]').addEventListener('change', function() {
			if (this.files && this.files[0]) {
				var img = document.querySelector('#img-select');
				img.src = URL.createObjectURL(this.files[0]);
			}
		});
	}
	
	$("input[name='cnpj']").mask("00.000.000/0000-00");
	$("input[name='telefone']").mask("(00)00000-0000");
	$("input[name='cep']").mask("00000-000");

	$("form").validate({
		errorPlacement : function(label, element) {
			label.addClass('spanError');
			label.insertAfter(element);
		},
		wrapper : 'em',
		rules : {
			cnpj : {
				required : true,
				maxlength : 18,
				minlength : 18
			},
			nome_empresa : {
				required : true,
				maxlength : 150,
				minlength : 2
			},
			telefone : {
				required : true
			},
			rua : {
				required : true,
				minlength : 2
			},
			numero : {
				required : true
			},
			bairro : {
				required : true,
				minlength : 2
			},
			estado : {
				required : true
			},
			cidade : {
				required : true,
				minlength : 2
			},
			telefone : {
				required : true,
			},
			senha : {
				required : true,
				maxlength : 10,
				minlength : 6
			},
			confirmar_senha : {
				required : true,
				equalTo : '#senha',
				maxlength : 10,
				minlength : 6
			},
			descricao : {
				maxlength : 100
			}
		},
		messages : {
		cnpj : {
			required : "*Obrigatório"
		},
		nome_empresa : {
			required : "*Obrigatório"
		},
		telefone : {
			required : "*Obrigatório"
		},
		rua : {
			required : "*Obrigatório"
		},
		numero : {
			required : "*Obrigatório"
		},
		bairro : {
			required : "*Obrigatório"
		},
		estado : {
			required : "*Obrigatório"
		},
		cidade : {
			required : "*Obrigatório"
		},
		telefone : {
			required : "*Obrigatório"
		},
		cep : {
			required : "*Obrigatório"
		},
		senha : {
			required : "*Obrigatório",
			maxlength : "A senha deve conter no máximo 10 dígitos",
			minlength : "A senha deve conter no minimo 6 dígitos"
		},
			confirmar_senha : {
				required : "*Obrigatório",
				equalTo : "Senhas não são correspondentes"
			}
		}
	});

});