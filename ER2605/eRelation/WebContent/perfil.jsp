<%@page import="org.json.JSONArray"%>
<%@page import="model.Conexao"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.5">
<title>PERFIL</title>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/5000ee8fc6.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap" rel="stylesheet">

<script src="https://js.api.here.com/v3/3.1/mapsjs-core.js" type="text/javascript" charset="utf-8"></script>
<script src="https://js.api.here.com/v3/3.1/mapsjs-service.js" type="text/javascript" charset="utf-8"></script>
<script src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js" type="text/javascript" charset="utf-8"></script>
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
				</a> <a href="perfil.jsp" class="active"> <i class="fas fa-user"></i> PERFIL
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

		obj = new JSONObject(result);
	%>

    <div class="panel">
        <div class="panel-header">
            <div class="img-square-wrapper" style="background-image: url('http://localhost:8080/WebServlet/svEmpresa?cnpj=<%=obj.getString("cnpj")%>');">
            </div>
            <div class="name">
                <h6>Ol�, </h6>
                <h1><%=obj.getString("empresa")%></h1>
            </div>
        </div>

        <div class="letter-row">
            <div class="col-10 col-lg-4">
                <span class="title-section">
                    <h5>INFO</h5>
                </span>
                <div class="letter letter-perfil">
                    <div class="letter-body">
                        <ul>
                            <li>
                                <span class="title"><i class="fas fa-building"></i> </span>
                                <span class="description"><%=obj.getString("empresa")%></span>
                            </li>
                            <li>
                                <span class="title"><i class="fas fa-map"></i> </span>
                                <span class="description"><%=obj.getString("endereco")%></span>
                            </li>
                            <li>
                                <span class="title"><i class="fas fa-phone-alt"></i> </span>
                                <span class="description"><%=obj.getString("telefone")%></span>
                            </li>
                            <li>
                                <span class="title"><i class="fas fa-industry"></i> </span>
                                <span class="description"><%=obj.getString("tipo")%></span>
                            </li>
                        </ul>
                    </div>
                    <div class="letter-footer">
                        <span class="title">DESCRI��O </span>
                        <p class="description">
                            <%	
								if (obj.has("descricao")) {
									if(!obj.getString("descricao").equals(""))
										out.println(obj.getString("descricao") + "<br>");
									else
										out.println("N�o h� nenhuma descri��o para a empresa<br>");
								} else {
									out.println("N�o h� nenhuma descri��o para a empresa<br>");
								}
							%>
                        </p><a href="updateempresa.jsp" class="edit-emp"><i class="fas fa-pencil-alt" aria-hidden="true"></i></a>
                    </div>
                </div>
            </div>

            <div class="col-10 col-lg-6 mt-lg-0 mt-5">
                <span class="title-section">
                    <h5>LOCALIZA��O</h5>
                </span>
                <div class="letter mapa">

                </div>
            </div>
        </div>
        <div class="letter-row">
            <div class="col-10 col-lg-4">
                <span class="title-section">
                    <h5>DETALHES</h5>
                </span>
				<%
				conection = new Conexao();
				
				result = conection.conexao(obj, 6, "svEmpresa");
				JSONObject resumo = new JSONObject(result);
				%>
                <div class="letter details">
                    <div class="letter-body">
                        <ul>
                            <li>
                                <span class="description">Quantidade de solicita��es</span>
                                <span class="val"><%=resumo.getInt("qnt_solicitacoes") %></span>
                            </li>
                            <li>
                                <span class="description">Quantidade de parceiros</span>
                                <span class="val"><%=resumo.getInt("qnt_socios") %></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="letter details">
                    <div class="letter-body">
                        <ul>
                            <li>
                                <span class="description">Pedidos que devemos entregar</span>
                                <span class="val"><%=resumo.getInt("pedidos_pendentes") %></span>
                            </li>
                            <li>
                                <span class="description">Pedidos que entregamos</span>
                                <span class="val"><%=resumo.getInt("pedidos_realizados") %></span>
                            </li>
                            <li>
                                <span class="description">Total de pedidos recebidos</span>
                                <span class="val"><%=resumo.getInt("total_pedidos") %></span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-10 col-lg-6 mt-lg-0 mt-5">
                <span class="title-section">
                    <h5>PRODUTOS</h5>
                </span>
                    <%
						conection = new Conexao();
				
						result = conection.conexao(obj, 4, "svProdutos");
				if(!result.isEmpty()){
						JSONArray arr = new JSONArray(result);
						if(arr.length() > 0){
							
					%>
                <div class="carousel slide" id="carousel-prod">
                    <div class="carousel-inner">
						<%
						int resto = 0;
						if(arr.length()%3 > 0){
							resto = 1;
						}
						for (int i = 0; i < (arr.length()/3)+resto; i++) {
						%>
						
	                        <div class="carousel-item <%if(i == 0){out.println("active");}%>">
	                            <div class="d-center">
						<%
						int linha = 3;
						if(arr.length()%3 > 0)
							linha = arr.length()%3;
						
						for (int j = (i*3); j < linha; j++) {
							JSONObject ob = arr.getJSONObject(j);
						%>
                                <div class="card-product">
                                    <div class="img-product" style="background-image: url('http://localhost:8080/WebServlet/svProdutos?codigo=<%=ob.getInt("id") %>');">
                                    </div>
                                    <div class="product-body">
                                        <span class="product-name"><%= ob.getString("nome")%></span>
                                        <span class="product-value">R$<%=ob.getFloat("preco")%></span>
                                    </div>
                                </div>
                               
                            	<%} %>
                            </div>
                        </div>
						<% } %>
                    </div>

                    <a class="carousel-control-prev" href="#carousel-prod" role="button" data-slide="prev">
                        <i style="font-size: 1rem; color:black" class="fas fa-arrow-left"></i>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carousel-prod" role="button" data-slide="next">
                        <i style="font-size: 1rem; color:black" class="fas fa-arrow-right"></i>
                        <span class="sr-only">NEXT</span>
                    </a>
                </div>
                <a href="meusprodutos.jsp" class="modal-prod"><i class="fas fa-th" aria-hidden="true"></i></a>
                <%} else {
                	out.println("A empresa n�o tem nenhum produto cadastrado");
                	}
                } else {
                	out.println("A empresa n�o tem nenhum produto cadastrado");
                	}%>
            </div>
        </div>
	</div>
	<script>
	    var platform = new H.service.Platform({
	        'apikey': '6syyeIWKtBAd73kpa0tjpBZ1x3NEc0eJVXiIa7eshbI'
	    });
	
	    var defaultLayers = platform.createDefaultLayers();
	
	    // Instantiate (and display) a map object:
	    var map = new H.Map(document.querySelector('.mapa'),
	        defaultLayers.vector.normal.map, {
	        zoom: 16
	    });
	
	    var mapEvents = new H.mapevents.MapEvents(map);
	
	    var behavior = new H.mapevents.Behavior(mapEvents);
	
	    var geocodingParams = {
	        searchText: '<%=obj.getString("endereco")%>'
	    };
	    // Define a callback function to process the geocoding response:
	    var onResult = function (result) {
	        var locations = result.Response.View[0].Result,
	            position,
	            marker;
	        // Add a marker for each location found
	        for (i = 0; i < locations.length; i++) {
	            position = {
	                lat: locations[i].Location.DisplayPosition.Latitude,
	                lng: locations[i].Location.DisplayPosition.Longitude
	            };
	
	            map.setCenter(position);
	            marker = new H.map.Marker(position);
	            map.addObject(marker);
	        }
	    };
	
	    var geocoder = platform.getGeocodingService();
	
	    geocoder.geocode(geocodingParams, onResult, function (e) {
	        alert(e);
	    });
</script>
<%
	} else {
		response.sendRedirect("index.jsp?e=1002");
	}%>
</body>
</html>