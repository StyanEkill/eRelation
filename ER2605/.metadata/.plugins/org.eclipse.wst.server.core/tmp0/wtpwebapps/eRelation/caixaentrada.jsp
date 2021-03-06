<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="model.Conexao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.5">
    <title>Caixa de entrada</title>
    
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/5000ee8fc6.js" crossorigin="anonymous"></script>
    <script charset="UTF-8" src="js/script.js"></script>
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
					SOLICITA��ES
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
				<a href="caixaentrada.jsp" style="color: white;"><i
					class="fas fa-envelope"></i></a>
			</div>
		</nav>
	</div>

	<%
	conection = new Conexao();
	obj.put("cnpj", hs.getAttribute("cnpj"));
	result = conection.conexao(obj, 20, "svEmpresa");
	
	SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfHorario = new SimpleDateFormat("HH:mm");
	
	if(!result.isEmpty() && !result.equals("[]")){
		JSONArray arr = new JSONArray(result);
		for (int i = 0; i < arr.length(); i++) {
			JSONObject ob = arr.getJSONObject(i);
			Date data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ob.getString("data")+" "+ob.getString("horario"));
	 %>
    <div class="notify-entrada">
		<span class="notfy-date">
			<%=sdfData.format(data)%> - 
			<%=sdfHorario.format(data)%>
		</span>
		<p><%=ob.getString("mensagem")%></p>
	</div>
	<%
		}
	} else {
		%>
		<div class="empty">
			<img src="img/empty.png" class="col-6">
			<div class="empty-body">
				<h1>Ops!</h1>
				<p>
					Parece que sua caixa de entrada esta vazia.
				</p>
				<span class="obs">
					Obs.: A caixa de entrada servir� para lhe manter atualizado sobre novas a��es, por isso � de extrema dar uma olhadinha por aqui.
				</span>
			</div>
		</div>
	<%}
	} else {
		response.sendRedirect("index.jsp?e=1002");
	}%>
</body>
</html>