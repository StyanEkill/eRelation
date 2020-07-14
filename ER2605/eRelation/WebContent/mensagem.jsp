<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="model.Conexao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=0.6">
<title>Mensagens</title>

<script src="js/jquery.js"></script>
<script src=" js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link
	href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/5000ee8fc6.js" crossorigin="anonymous"></script>

	<script charset="utf-8" src="js/script.js"></script>
	<script charset="utf-8" src="js/script_mensagem.js"></script>
</head>

<body>
	<%
	HttpSession hs = request.getSession();
	if(hs.getAttribute("cnpj") != null){
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
			</button>
			<div id="mySidebar" class="sidebar">
				<button class="close-side">&times;</button>
				<a href="home.jsp">
                    <i class="fas fa-home"></i> FEED
                </a>
                <a href="perfil.jsp">
                    <i class="fas fa-user"></i> PERFIL
                </a>
                <a href="socios.jsp">
                    <i class="fas fa-user-friends"></i> SOCIOS
                </a>
                <a href="solicitacoes.jsp">
                    <i class="fas fa-address-book"></i> SOLICITAÇÕES
                </a>
                <a href="mensagem.jsp" class="active">
                    <i class="fas fa-comments"></i> MENSAGENS
                </a>
                <a href="agenda.jsp">
                    <i class="fas fa-calendar-alt"></i> AGENDA
                </a>
                <a href="meusprodutos.jsp">
                    <i class="fas fa-box"></i> MEUS PRODUTOS
                </a>
                <a href="pedidos.jsp">
                    <i class="fas fa-truck"></i> PEDIDOS
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
			<div style="color:white;font-size: 42px;margin-right:20px">
				<%
				Conexao conection = new Conexao();
				JSONObject obj = new JSONObject();
				obj.put("cnpj", hs.getAttribute("cnpj"));
				String result = conection.conexao(obj, 18, "svEmpresa");
				JSONObject obVerify = new JSONObject(result);
				if(obVerify.getBoolean("verify")){ out.println("<span class=\"new\"></span>");
				}%>
				<a href="caixaentrada.jsp" style="color:white;"><i class="fas fa-envelope"></i></a>
			</div>
		</nav>
	</div>
	<div class="content-msn">
		<div class="side-mensagens">
			<button class="toggle-users">
				<i class="fas fa-angle-double-right"></i>
			</button>
			<div class="side-header">
				<div class="search">
					<input type="search" placeholder="Search.." name="search">
					<i class="fa fa-search"></i>
				</div>
			</div>
			<div class="list-user">
				<%
					conection = new Conexao();
					result = conection.conexao(obj, 5, "svMensagem");

					boolean hasCnpj = false;
					String cnpj = "";
					String empresa = "";
					
					if(!result.isEmpty()){
						JSONArray arr = new JSONArray(result);
	
						for (int i = 0; i < arr.length(); i++) {
							JSONObject ob = arr.getJSONObject(i);
							if (ob.getString("cnpj_conversa").equals(request.getParameter("cnpjDest"))) {
								cnpj = ob.getString("cnpj_conversa");
								empresa = ob.getString("empresa");
								hasCnpj = true;
							}
				%>
				<div class="user <%if(ob.getString("cnpj_conversa").equals(request.getParameter("cnpjDest"))){ out.println("active");}%>" data-user="<%=ob.getString("cnpj_conversa")%>">
					<div class="img-user"
						style="background-image:url('http://<%out.print(conection.getIp()+":"+conection.getPort()); %>/WebServlet/svEmpresa?cnpj=<%=ob.getString("cnpj_conversa")%>')"></div>

					<div class="about">
						<div class="name"><%=ob.getString("empresa")%></div>
						<span class="last-hour"><%=ob.getString("horario")%></span>
						<span class="status"></span>
						<% if(ob.getInt("number_message") > 0){ %>
						<span class="number-message"><%=ob.getInt("number_message")%></span>
						<%} %>
						<span class="last-message">
							<%
								if (ob.getString("cnpj").equals(hs.getAttribute("cnpj"))) {
										out.println("Você: " + ob.getString("mensagem"));
									} else {
										out.println(ob.getString("mensagem"));
									}
							%>
						</span>
					</div>
				</div>
				<%
					}
				}
					if (!hasCnpj && request.getParameter("cnpjDest") != null) {
						conection = new Conexao();
						obj = new JSONObject();
						obj.put("cnpj", request.getParameter("cnpjDest"));
						result = conection.conexao(obj, 3, "svEmpresa");

						obj = new JSONObject(result);
						cnpj = obj.getString("cnpj");
						empresa = obj.getString("empresa");
				%>

				<div class="user active" data-user="<%=obj.getString("cnpj")%>">
					<div class="img-user"
						style="background-image:url('http://<%out.print(conection.getIp()+":"+conection.getPort()); %>/WebServlet/svEmpresa?cnpj=<%=obj.getString("cnpj")%>')"></div>

					<div class="about">
						<div class="name"><%=obj.getString("empresa")%></div>
						<span class="last-hour"></span> <span
							class="status"></span> <span class="last-message">
							
						</span>
					</div>
				</div>
				<%
					}
				%>

			</div>
		</div>
		<div class="chatbox">
			<div class="chat-header">
				<div class="img-user"
						<%if(request.getParameter("cnpjDest") != null){ %>
								style="background-image:url('http://<%=conection.getIp()%>:<%=conection.getPort() %>/WebServlet/svEmpresa?cnpj=<%=request.getParameter("cnpjDest")%>');" 
						<%}%>>
				</div>
				<div class="chat-about"
					data-user="<%=cnpj%>">
					
					<div class="chat-with">
						Conversando com
						<%=empresa%></div>
					<span class="status "></span>
				</div>
			</div>
			<div class="logs"></div>
			<div class="chat-footer">
				<div class="message-submit">
					<textarea placeholder="Digite aqui a sua mensagem." id="message"></textarea>
					<button id="submit">
						<i class="fas fa-paper-plane"></i>
					</button>
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