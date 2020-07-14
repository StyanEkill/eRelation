<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
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
<title>Pedidos</title>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/5000ee8fc6.js"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link
	href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap"
	rel="stylesheet">
<script src="https://js.api.here.com/v3/3.1/mapsjs-core.js"
	type="text/javascript" charset="utf-8"></script>
<script src="https://js.api.here.com/v3/3.1/mapsjs-service.js"
	type="text/javascript" charset="utf-8"></script>
<script charset="utf-8" src="js/script.js"></script>
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
				</a> <a href="pedidos.jsp" class="active"> <i class="fas fa-truck"></i> PEDIDOS
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
	<div class="content col-xl-10 container-fluid">
		<nav class=" nav nav-tabs d-print-none" id="myTab" role="tablist">
			<a class="nav-link active" id="tab1" data-toggle="tab" href="#pedidos2">
				Pedidos para mim
			</a>
			<a class="nav-link" id="tab2" data-toggle="tab" href="#pedidos1">
				Nossos pedidos
			</a>
		</nav>

		<div class="tab-content" id="myTabContent">
			<div id="pedidos1" class="tab-pane fade show">
				<h2 class="text-center mt-5">NOSSOS PEDIDOS</h2>
				<div class="pedidos">
					<%
					conection = new Conexao();
					result = conection.conexao(obj, 5, "svPedidos");
					NumberFormat formatPreco = new DecimalFormat("0.00");
					JSONArray arr;
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					if(!result.isEmpty() && !result.equals("[]")){
						arr = new JSONArray(result);
						for (int i = 0; i < arr.length(); i++) {
							JSONObject ob = arr.getJSONObject(i);
					%>
			
					<div id="pedidos" class="col-sm-10 col container-fluid">
						<div class="accordion" id="accordionExample">
							<div class="card">
								<div class="card-body titulo" id="headingTwo">
									<div class="card-title">
										<h5><%=ob.getString("fornecedor")%> <img src="icons/seta.png"> <%=ob.getString("cliente")%></h5>
										<ul class="progressbar">
											<li <%if(ob.getInt("status") >= 1) out.println("class=\"active\""); %>>AGUARDANDO DATA DE ENTREGA</li>
											<li <%if(ob.getInt("status") >= 2) out.println("class=\"active\""); %>>PREPARANDO TRANSPORTE</li>
											<li <%if(ob.getInt("status") >= 3) out.println("class=\"active\""); %>>EM TRANSPORTE</li>
											<li <%if(ob.getInt("status") >= 4) out.println("class=\"active\""); %>>ENTREGUE</li>
										</ul>
									</div>
									<div class="content-pedido">
										<ul class="data-pedido">
											<li><h6>Data do pedido:</h6> <%=sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(ob.getString("data_pedido"))) %></li>
									<%
										if (ob.has("data_previsao")) {
									%>
											<li><h6>Data de previsão:</h6> <%=sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(ob.getString("data_previsao"))) %></li>
									<%
										}
									%>
									<%
										if (ob.has("data_final")) {
									%>
											<li><h6>Data de entrega:</h6> <%=sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(ob.getString("data_final"))) %></li>
									<%
										}
									%>
										</ul>
										<p class="card-text">
											<i class="fas fa-map-marker-alt"></i> <%=ob.getString("endereco")%>
										</p>
									<%
										if (ob.getInt("status") == 3) {
									%>
										<form action="servlet" method="POST" style="float: left;margin-right: 15px;">
											<input type="hidden" name="action" value="18">
											<input type="hidden" name="id" value="<%=ob.getInt("id")%>">
											<input type="hidden" name="status" value="<%=ob.getInt("status") + 1%>">
											
											<button class="btn btn-success">DEFINIR COMO ENTREGUE</button>
										</form>
									<%
										}
										if (ob.getInt("status") <= 3) {
									%>
										<form action="servlet" method="POST">
											<input type="hidden" name="action" value="19"> <input
												type="hidden" name="id" value="<%=ob.getInt("id")%>">
											<button class="btn btn-danger" style="">CANCELAR PEDIDO</button>
										</form>
										
									<%
										
									} else {
									%>
										<i class="far fa-check-circle" style="font-size: 1.5rem;color:#009688;" aria-hidden="true"></i> PEDIDO ENTREGUE
									<%
									}
									%>
									</div>
									<button class="btn btn-warning collapsed" type="button" data-toggle="collapse" data-target="#collapseItens<%=ob.getInt("id")%>" aria-expanded="false" aria-controls="collapseItens<%=ob.getInt("id")%>">
										<i class="fas fa-chevron-circle-down" style="font-size: 1.5rem;"></i>
									</button>
								</div>
							</div>
							<div class="collapse multi-collapse" id="collapseItens<%=ob.getInt("id")%>">
								<div class="card-body" style="background-color: white;box-shadow: 0 0 30px 0 rgb(160, 160, 160,0.3);">
									<h4>Itens</h4>
									<ul class="itens">
										<%
										JSONArray array = ob.getJSONArray("itens");
										for (int j = 0; j < array.length(); j++) {
										JSONObject objt = array.getJSONObject(j); %>
										<li>
											<ul class="itens-prod">
												<li><h6>ID:</h6> <%=objt.getInt("id_prod")%></li>
												<li><h6>PRODUTO:</h6> <%=objt.getString("produto")%></li>
												<li><h6>QNT.:</h6> <%=objt.getInt("qnt")%></li>
												<li><h6>VALOR UNITÁRIO:</h6> R$<%=formatPreco.format(objt.getFloat("valor"))%></li>
												<li><h6>SUB-TOTAL:</h6> R$<%=formatPreco.format(objt.getFloat("valor")*objt.getInt("qnt"))%></li>
											</ul>
										</li>
										<% } %>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<%
						}
					} else{ %>
					<div class="empty">
						<img src="img/empty2.png" class="col-6">
						<div class="empty-body">
							<h1>Ops!</h1>
							<p>
								Parece que você não realizou nenhum pedido.
							</p>
							<span class="obs">
								Obs.: Aqui aparecerão os pedidos feitos por sua empresa. Fique atento na caixa de entrada e habilite as notifcações, elas serâo úteis para você acompanhar os status de seu pedido.
							</span>
						</div>
					</div>
				<%} %>
				</div>
			</div>
		
			<div id="pedidos2" class="tab-pane fade show active">
				<h2 class="text-center mt-5">PEDIDOS PARA MIM</h2>
				<div class="pedidos">
					<%
					conection = new Conexao();
					result = conection.conexao(obj, 4, "svPedidos");
					if(!result.isEmpty()){
						arr = new JSONArray(result);
						for (int i = 0; i < arr.length(); i++) {
							JSONObject ob = arr.getJSONObject(i);
					%>
			
					<div id="pedidos" class="col-sm-10 col container-fluid">
						<div class="accordion" id="accordionExample">
							<div class="card">
								<div class="card-body titulo" id="headingTwo">
									<div class="card-title">
										<h5><%=ob.getString("fornecedor")%> <img src="icons/seta.png"> <%=ob.getString("cliente")%></h5>
										<ul class="progressbar">
											<li <%if(ob.getInt("status") >= 1) out.println("class=\"active\""); %>>AGUARDANDO DATA DE ENTREGA</li>
											<li <%if(ob.getInt("status") >= 2) out.println("class=\"active\""); %>>PREPARANDO TRANSPORTE</li>
											<li <%if(ob.getInt("status") >= 3) out.println("class=\"active\""); %>>EM TRANSPORTE</li>
											<li <%if(ob.getInt("status") >= 4) out.println("class=\"active\""); %>>ENTREGUE</li>
										</ul>
									</div>
									<div class="content-pedido">
										<ul class="data-pedido">
											<li><h6>Data do pedido:</h6> <%=sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(ob.getString("data_pedido")))%></li>
										<%
											if (ob.has("data_previsao")) {
										%>
											
											<li><h6>Data de previsão:</h6> <%=sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(ob.getString("data_previsao")))%></li>
										<%
											}
										%>
										<%
											if (ob.has("data_final")) {
										%>
										
											<li><h6>Data de entrega:</h6> <%=sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(ob.getString("data_final")))%></li>
										<%
											}
										%>
										</ul>
										<p class="card-text">
											<i class="fas fa-map-marker-alt"></i> <%=ob.getString("endereco")%>
										</p>
									<%
									String msg = null;
					
									switch (ob.getInt("status")) {
									case 0:
										msg = "DEFINIR DATA DE ENTREGA";
										break;
									case 1:
										msg = "DEFINIR EM PREPARANDO TRANPORTE";
										break;
									case 2:
										msg = "DEFINIR COMO TRANSPORTANDO";
										break;
									case 4: %>
									<i class="far fa-check-circle" style="font-size: 1.5rem;color:#009688;" aria-hidden="true"></i> PEDIDO ENTREGUE
									<%
										break;
									}
									if (ob.getInt("status") < 3) {
									%>
									<form action="servlet" method="POST">
										<input type="hidden" name="action" value="18">
										<input type="hidden" name="id" value="<%=ob.getInt("id")%>">
										<input type="hidden" name="status" value="<%=ob.getInt("status") + 1%>">
										<%
											if (ob.getInt("status") == 0) {
										%>
										<input type="date" name="data_prev" required>
										<%
											}
										%>
										<button class="btn btn-success"><%=msg%></button>
									</form>
									<%
									}
									%>
									</div>
									<button class="btn btn-warning collapsed" type="button" data-toggle="collapse" data-target="#collapseItens<%=ob.getInt("id")%>" aria-expanded="false" aria-controls="collapseItens<%=ob.getInt("id")%>">
										<i class="fas fa-chevron-circle-down" style="font-size: 1.5rem;"></i>
									</button>
								</div>
							</div>
							<div class="collapse multi-collapse" id="collapseItens<%=ob.getInt("id")%>">
								<div class="card-body" style="background-color: white;box-shadow: 0 0 30px 0 rgb(160, 160, 160,0.3);">
									<h4>Itens</h4>
									<ul class="itens">
										<%JSONArray array = ob.getJSONArray("itens");
										for (int j = 0; j < array.length(); j++) {
										JSONObject objt = array.getJSONObject(j); %>
										<li>
											<ul class="itens-prod">
												<li><h6>ID:</h6> <%=objt.getInt("id_prod")%></li>
												<li><h6>PRODUTO:</h6> <%=objt.getString("produto")%></li>
												<li><h6>QNT.:</h6> <%=objt.getInt("qnt")%></li>
												<li><h6>VALOR UNITÁRIO:</h6> R$<%=formatPreco.format(objt.getFloat("valor"))%></li>
												<li><h6>SUB-TOTAL:</h6> R$<%=formatPreco.format(objt.getFloat("valor")*objt.getInt("qnt"))%></li>
											</ul>
										</li>
										<% } %>
									</ul>
								</div>
							</div>
						</div>
					</div>
				<%
						}
					} else{ %>
					<div class="empty">
					<img src="img/empty2.png" class="col-6">
					<div class="empty-body">
						<h1>Ops!</h1>
						<p>
							Parece que você não possui nenhum novo pedido.
						</p>
						<span class="obs">
							Obs.: Aqui aparecerão os pedidos feitos para você. Fique atento na caixa de entrada e habilite as notifcações, elas serâo úteis para você nâo perder nenhum pedido.
						</span>
					</div>
				</div>
			<%} %>
				</div>
			</div>
		</div>
	</div>
	<%
	} else {
		response.sendRedirect("index.jsp?e=1002");
	}%>
</body>
</html>