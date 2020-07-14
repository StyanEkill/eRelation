<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.5">
<title>Cadastro</title>

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.validation.pt-br.js"></script>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap"rel="stylesheet">

<script src="https://kit.fontawesome.com/5000ee8fc6.js" crossorigin="anonymous"></script>
<script src="js/script.js" charset="utf-8"></script>
<script src="js/script_forms.js" charset="utf-8"></script>
</head>

<body>
	<%
		HttpSession hs = request.getSession();
		if(hs.getAttribute("cnpj") == null){
	%>
	<div class="pos-f-t ">
		<div class="collapse" id="navbarToggleExternalContent">
			<div class="bg-dark p-2">
				<ul class="navbar-nav">
					<li class="nav-item pt-lg-0 pt-3"><a class="nav-link text-white mr-3" href="login.jsp">Login</a></li>
				</ul>
			</div>
		</div>

		<nav class="navbar navbar-dark">
			<button id="hamb">
				<span class="hamburguer"></span>
			</button>
			<div id="mySidebar" class="sidebar">
				<button class="close-side">&times;</button>
				<a href="index.jsp"><i class="fas fa-home"></i> HOME</a> <a
					href="login.jsp"><i class="fas fa-house-user"></i> LOGIN</a> <a
					href="cadastro.jsp" class="active"><i class="fas fa-user-plus"></i>
					CADASTRO</a>
			</div>
			<div class="logo">
				<a class="navbar-brand" href="index.html">
					<h1>
						<img class="nav-item" src="icons/logo.png" width="45px">
						eRelations
					</h1>
				</a>
			</div>
			<div style="color: white; font-size: 42px; margin-right: 20px">
			</div>
		</nav>
	</div>


	<div id="login" class="row d-center">
		<div class="col-xl-6 col-12">
			<img class="img-responsive" src="img/cadastro.png">
		</div>
		<div class=" col-xl-6 col-12">
				<div class="text-center mb-4">
					<h2>CADASTRO</h2>
				</div>
				<form method="post" action="servlet" enctype="multipart/form-data">
					<input type="hidden" name="action" value="1">
					<div class="input-group">
						<fieldset>
							<input type="text" class="form-control" name="cnpj" placeholder="CNPJ" required>
						</fieldset>
					</div>
					<div class="input-group">
						<fieldset>
							<input type="text" class="form-control mr-2" name="nome_empresa" placeholder="Nome da empresa">
						</fieldset>
						<fieldset class="col-3">
							<select name="tipo" class="form-control">
								<option value="Comercial">Comercial</option>
								<option value="Industrial">Industrial</option>
								<option value="Rural">Rural</option>
							</select>
						</fieldset>
					</div>
					<div class="input-group">
						<fieldset>
							<input type="text" class="form-control" name="telefone" placeholder="Telefone">
						</fieldset>
					</div>
					<div class="input-group endereco">
						<fieldset>
							<input type="text" class="form-control" name="rua" placeholder="Rua">
						</fieldset>
						
						<fieldset>
							<input type="text" class="form-control" name="bairro" placeholder="Bairro">
						</fieldset>
						
						<fieldset class="col-xl-2 col-3">
							<input type="text" class="form-control" name="numero" placeholder="Número">
						</fieldset>
					</div>
					<div class="input-group endereco">
						<fieldset>
							<input type="text" class="form-control mr-2" name="cidade" placeholder="Cidade">
						</fieldset>
						<fieldset class="col-xl-2 col-3">
							<select name="estado" class="form-control">
								<option disabled>UF</option>
								<option value="AC">AC</option>
								<option value="AL">AL</option>
								<option value="AP">AP</option>
								<option value="AM">AM</option>
								<option value="BA">BA</option>
								<option value="CE">CE</option>
								<option value="DF">DF</option>
								<option value="ES">ES</option>
								<option value="GO">GO</option>
								<option value="MA">MA</option>
								<option value="MT">MT</option>
								<option value="MS">MS</option>
								<option value="MG">MG</option>
								<option value="PA">PA</option>
								<option value="PB">PB</option>
								<option value="PR">PR</option>
								<option value="PE">PE</option>
								<option value="PI">PI</option>
								<option value="RJ">RJ</option>
								<option value="RN">RN</option>
								<option value="RS">RS</option>
								<option value="RO">RO</option>
								<option value="RR">RR</option>
								<option value="SC">SC</option>
								<option value="SP">SP</option>
								<option value="SE">SE</option>
								<option value="TO">TO</option>
							</select>
						</fieldset>
						<fieldset class="col-xl-2 col-3">
							<input type="text" class="form-control" name="cep" placeholder="CEP">
						</fieldset>
					</div>
					<div class="input-group">
						<fieldset>
							<input type="password" class="form-control" name="senha" id="senha" placeholder="Senha">
						</fieldset>
						
						<fieldset>
							<input type="password" class="form-control" name="confirmar_senha" placeholder="Confirme a senha">
						</fieldset>
					</div>
					<div class="input-group">
      					<img name="img" id="img-select" class="rounded-circle" height="100px" width="100px"/>
                    	<label for="foto" id="lb-image"><i class="fas fa-pencil-alt"></i></label>
                    	<input type="file" name="foto" accept=".jpg" id="foto">
                   	</div>
                   	<div class="input-group">
     					<textarea rows="2" name="descricao" class="form-control" placeholder="Descrição. Exp.: Empresa especializada em fabricação de bebidas"></textarea>
                   	</div>
					<button class="btn btn-primary ">ACESSAR</button>
				</form>
			</div>
		</div>
	<%
	} else {
		response.sendRedirect("home.jsp");
	}
	%>
</body>

</html>
