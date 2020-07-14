<!DOCTYPE html>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.5">
    <title>Index</title>
    
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/5000ee8fc6.js" crossorigin="anonymous"></script>
    <script src="js/script.js" charset="utf-8"></script>
</head>

<body id="index">
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
				<a href="index.jsp" class="active"><i class="fas fa-home"></i> HOME</a> <a
					href="login.jsp"><i class="fas fa-house-user"></i> LOGIN</a> <a
					href="cadastro.jsp"><i class="fas fa-user-plus"></i>
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

    <div class="fundo">

        <div class="text-center text-white legenda">
            <h1>Come�e</h1>
            <h4>Inicie j� o seu marketing empresarial</h4><br>
            <a type="button" class="btn btn-info" href="login.html"><i class="fas fa-arrow-right"></i></a>
        </div>

    </div>

    <div class="fusion1 row m-0">
            <img class="img-responsive" src="img/indexx.png">
            <div class="text-center z1">
            <h2 class="color1"><i>Servi�os</i></h2>
            <h4>Procure por empresas que possam<br>oferecer recursos e servi�os</h4>
        </div>
    </div>
    <div class="fusion2 row m-0">
            <div class="text-center z2">
                <h2 class="color2"><i>Negocios</i></h2>
                <h4 class="text-white">Venda seu produto e agende reuni�es<br>com seus parceiros empresariais</h4>
            </div>
            <img class="img-responsive" src="img/icon.png">
    </div>
    <div class="info row m-0">
        <div class="card">
            <div class="card-header">
                <i class="fas fa-handshake"></i>
            </div>
            <div class="card-body">
                <h5 class="card-title">Feche parcerias</h5>
                <p class="card-text">eRelation oferece um sistema de parceiros para um primeiro contato com outras empresas</p>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <i class="fas fa-calendar-alt"></i>
            </div>
            <div class="card-body">
                <h5 class="card-title">Eventos</h5>
                <p class="card-text">Agende reuni�es ou encontros quando voc� quiser.</p>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <i class="fas fa-truck"></i>
            </div>
            <div class="card-body">
                <h5 class="card-title">Pedidos</h5>
                <p class="card-text">Realize e receba pedidos de seus parceros ou afiliados.</p>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <i class="fas fa-truck-loading"></i>
            </div>
            <div class="card-body">
                <h5 class="card-title">Entregas</h5>
                <p class="card-text">eRealtion cont�m um sistema de entrega completo e seguro.</p>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <i class="fas fa-boxes"></i>
            </div>
            <div class="card-body">
                <h5 class="card-title">Produtos</h5>
                <p class="card-text">Cadastre seus produtos oferecidos.</p>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <i class="fas fa-bell"></i>
            </div>
            <div class="card-body">
                <h5 class="card-title">Notifica��o</h5>
                <p class="card-text">Receba notifica��es e fique por dentro do que acontece com sua empresa.</p>
            </div>
        </div>
    </div>

    <div class="footer">
        <h3>Acesse tamb�m</h3>
        <div class="contatos">
            <a href="#"><i class="fab fa-facebook-f"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>
            <a href="#"><i class="fab fa-android"></i></a>
        </div>
        
    </div>
    <div class="sub-footer">
        <h6>Copyright 2020 - eRelations - All Rights Reserved</h6>
    </div>

	<%
	} else {
		response.sendRedirect("home.jsp");
	}
	%>
</body>

</html>