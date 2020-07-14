<%@page import="model.Conexao"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.5">
<title>Home</title>


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
	if(hs.getAttribute("cnpj") != null){
		Conexao conection = new Conexao();
		JSONObject obj = new JSONObject();
		obj.put("cnpj", hs.getAttribute("cnpj"));
		String result = conection.conexao(obj, 2,"svMensagem");
		JSONObject obVerify = new JSONObject(result);
	%>
	<div class="pos-f-t ">
		<div class="collapse" id="navbarToggleExternalContent">
			<div class="bg-dark p-2">
				<ul class="navbar-nav">
					<li class="nav-item pt-lg-0 pt-3"><a
						class="nav-link text-white mr-3" href="login.jsp">Login</a></li>
				</ul>
			</div>
		</div>

		<nav class="navbar navbar-dark">
			<button id="hamb">
				<span class="hamburguer"></span>
				<%
					if (obVerify.getBoolean("verify")) {
						out.println("<span class=\"new\"></span>");
					}
				%>
			</button>
			<div id="mySidebar" class="sidebar">
				<button class="close-side">&times;</button>
				<a href="home.jsp"> <i class="fas fa-home"></i>
					FEED
				</a> <a href="perfil.jsp"> <i class="fas fa-user"></i> PERFIL
				</a> <a href="socios.jsp"> <i class="fas fa-user-friends"></i>
					SOCIOS
				</a> <a href="solicitacoes.jsp"> <i class="fas fa-address-book"></i>
					SOLICITAÇÕES
				</a> <a href="mensagem.jsp"> <i class="fas fa-comments"></i>
					MENSAGENS <% if (obVerify.getBoolean("verify")) { out.println("<span class=\"new\"></span>"); } %>
				</a> <a href="agenda.jsp"> <i class="fas fa-calendar-alt"></i>
					AGENDA
				</a> <a href="meusprodutos.jsp"> <i class="fas fa-box"></i> MEUS
					PRODUTOS
				</a> <a href="pedidos.jsp"> <i class="fas fa-truck"></i> PEDIDOS
				</a>
				<form method="POST" action="servlet">
					<input type="hidden" name="action" value="3">
					<button>
						<i class="fas fa-sign-out-alt"></i> SAIR
					</button>
				</form>
			</div>
			<div class="logo jusify-content-center">
				<a class="navbar-brand" href="index.jsp">
					<h1>
						<img class="nav-item" src="icons/logo.png" height="45px">eRelation
					</h1>
				</a>
			</div>
			<div style="color: white; font-size: 42px; margin-right: 20px">
				<%
					conection = new Conexao();
					result = conection.conexao(obj, 18, "svEmpresa");
					obVerify = new JSONObject(result);
					if (obVerify.getBoolean("verify")) {
						out.println("<span class=\"new\"></span>");
					}
				%>

				<a href="caixaentrada.jsp" style="color: white;"><i
					class="fas fa-envelope"></i></a>
			</div>
		</nav>
	</div>

	<%
	conection = new Conexao();
	result = conection.conexao(obj, 3, "svEmpresa");
	
	JSONObject objs = new JSONObject(result);
	String[] end = objs.getString("endereco").split(", ");
	String[] bairroNum = end[1].split(" - ");
	String[] cidUf = end[2].split(" - ");%>
	<div id="login" class="row d-center">
		<div class=" col-xl-6 col-12">
				<div class="text-center mb-4">
					<h2>ATUALIZAÇÃO</h2>
				</div>
				<form method="post" action="servlet" enctype="multipart/form-data">
					<input type="hidden" name="action" value="4">
					<div class="input-group">
      					<img name="img" id="img-select" class="rounded-circle" height="100px" width="100px" src="http://localhost:8080/WebServlet/svEmpresa?cnpj=<%=objs.getString("cnpj")%>"/>
                    	<label for="foto" id="lb-image"><i class="fas fa-pencil-alt"></i></label>
                    	<input type="file" name="foto" accept=".jpg" id="foto">
                   	</div>
					<div class="input-group">
						<fieldset>
                        	<input type="text" class="form-control mr-2" name="nome_empresa" placeholder="Nome da empresa" value="<%=objs.getString("empresa")%>">
						</fieldset>
						<fieldset class="col-3">
							<select name="tipo" class="form-control">
                            <option  value="Comercial" <%if(objs.getString("tipo").equals("Comercial")) out.println("selected");%>>Comercial</option>
                            <option value="Industrial" <%if(objs.getString("tipo").equals("Industrial")) out.println("selected");%>>Industrial</option>
                            <option value="Rural" <%if(objs.getString("tipo").equals("Rural")) out.println("selected");%>>Rural</option>
							</select>
						</fieldset>
					</div>
					<div class="input-group">
						<fieldset>
							<input type="text" class="form-control" name="telefone" placeholder="Telefone" value="<%=objs.getString("telefone")%>">
						</fieldset>
					</div>
					<div class="input-group endereco">
						<fieldset>
							<input type="text" class="form-control" name="rua" placeholder="Rua" value="<%=end[0]%>">
						</fieldset>
						
						<fieldset>
							<input type="text" class="form-control" name="bairro" placeholder="Bairro" value="<%=bairroNum[0]%>">
						</fieldset>
						
						<fieldset class="col-xl-2 col-3">
							<input type="number" class="form-control" name="numero" placeholder="Número" value="<%=bairroNum[1]%>">
						</fieldset>
					</div>
					<div class="input-group endereco">
						<fieldset>
							<input type="text" class="form-control mr-2" name="cidade" placeholder="Cidade" value="<%=cidUf[0]%>">
						</fieldset>
						<fieldset class="col-xl-2 col-3">
							<select name="estado" class="form-control">
								<option disabled>UF</option>
								<option value="AC" <%if(cidUf[1].equals("AC")) out.println("selected");%>>AC</option>
								<option value="AL" <%if(cidUf[1].equals("AL")) out.println("selected");%>>AL</option>
								<option value="AP" <%if(cidUf[1].equals("AP")) out.println("selected");%>>AP</option>
								<option value="AM" <%if(cidUf[1].equals("AM")) out.println("selected");%>>AM</option>
								<option value="BA" <%if(cidUf[1].equals("BA")) out.println("selected");%>>BA</option>
								<option value="CE" <%if(cidUf[1].equals("CE")) out.println("selected");%>>CE</option>
								<option value="DF" <%if(cidUf[1].equals("DF")) out.println("selected");%>>DF</option>
								<option value="ES" <%if(cidUf[1].equals("ES")) out.println("selected");%>>ES</option>
								<option value="GO" <%if(cidUf[1].equals("GO")) out.println("selected");%>>GO</option>
								<option value="MA" <%if(cidUf[1].equals("MA")) out.println("selected");%>>MA</option>
								<option value="MT" <%if(cidUf[1].equals("MT")) out.println("selected");%>>MT</option>
								<option value="MS" <%if(cidUf[1].equals("MS")) out.println("selected");%>>MS</option>
								<option value="MG" <%if(cidUf[1].equals("MG")) out.println("selected");%>>MG</option>
								<option value="PA" <%if(cidUf[1].equals("PA")) out.println("selected");%>>PA</option>
								<option value="PB" <%if(cidUf[1].equals("PB")) out.println("selected");%>>PB</option>
								<option value="PR" <%if(cidUf[1].equals("PR")) out.println("selected");%>>PR</option>
								<option value="PE" <%if(cidUf[1].equals("PE")) out.println("selected");%>>PE</option>
								<option value="PI" <%if(cidUf[1].equals("PI")) out.println("selected");%>>PI</option>
								<option value="RJ" <%if(cidUf[1].equals("RJ")) out.println("selected");%>>RJ</option>
								<option value="RN" <%if(cidUf[1].equals("RN")) out.println("selected");%>>RN</option>
								<option value="RS" <%if(cidUf[1].equals("RS")) out.println("selected");%>>RS</option>
								<option value="RO" <%if(cidUf[1].equals("RO")) out.println("selected");%>>RO</option>
								<option value="RR" <%if(cidUf[1].equals("RR")) out.println("selected");%>>RR</option>
								<option value="SC" <%if(cidUf[1].equals("SC")) out.println("selected");%>>SC</option>
								<option value="SP" <%if(cidUf[1].equals("SP")) out.println("selected");%>>SP</option>
								<option value="SE" <%if(cidUf[1].equals("SE")) out.println("selected");%>>SE</option>
								<option value="TO" <%if(cidUf[1].equals("TO")) out.println("selected");%>>TO</option>
							</select>
						</fieldset>
						<fieldset class="col-xl-2 col-3">
							<input type="text" class="form-control" name="cep" placeholder="CEP" value="<%=end[3]%>">
						</fieldset>
					</div>
                   	<div class="input-group">
     					<textarea rows="2" name="descricao" class="form-control" placeholder="Descrição. Exp.: Empresa especializada em fabricação de bebidas"><%if(objs.has("descricao")) out.println(objs.getString("descricao"));%></textarea>
                   	</div>
					<button class="btn btn-primary ">CONCLUIR</button>
				</form>
		</div>
		<div class="col-xl-6 col-12">
			<img class="img-responsive" src="img/undraw_forms_78yw.png">
		</div>
	</div>
	<%
	} else {
		response.sendRedirect("index.jsp?e=1002");
	}%>
</body>
</html>