<%@page import="org.json.JSONArray"%>
<%@page import="model.Conexao"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=0.5">
<title>Home</title>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/5000ee8fc6.js"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link
	href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap"
	rel="stylesheet">
<script charset="UTF-8" src="js/script.js"></script>
<script charset="UTF-8" src="js/script_home.js"></script>
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
				<a href="home.jsp" class="active"> <i class="fas fa-home"></i>
					FEED
				</a> <a href="perfil.jsp"> <i class="fas fa-user"></i> PERFIL
				</a> <a href="socios.jsp"> <i class="fas fa-user-friends"></i>
					SOCIOS
				</a> <a href="solicitacoes.jsp"> <i class="fas fa-address-book"></i>
					SOLICITAÇÕES
				</a> <a href="mensagem.jsp"> <i class="fas fa-comments"></i>
					MENSAGENS <%
 	if (obVerify.getBoolean("verify")) {
 		out.println("<span class=\"new\"></span>");
 	}
 %>
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
	<div id="feed" class="col-sm-11 col-12 container-fluid">
		<div class="top-feed">
			<div class="search">
				<input type="search" placeholder="Search.." name="search">
				<button style="background: transparent; border: none;">
					<i class="fa fa-search"></i>
				</button>
			</div>
			<div class="filter">
				<i class="fa fa-filter"></i> <select name="type">
					<option value="all" selected>TODOS</option>
					<option value="rural">RURAL</option>
					<option value="industrial">INDUSTRIAL</option>
					<option value="comercial">COMERCIAL</option>
				</select>
			</div>
		</div>
	</div>
	<%
	} else {
		response.sendRedirect("index.jsp?e=1002");
	}%>
</body>

</html>