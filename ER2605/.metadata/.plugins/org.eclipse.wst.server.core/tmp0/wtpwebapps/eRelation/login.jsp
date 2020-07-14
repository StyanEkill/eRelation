<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.5">
    <title>Login</title>

    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.mask.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/jquery.validation.pt-br.js"></script>
	
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap" rel="stylesheet">
	
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
					href="login.jsp" class="active"><i class="fas fa-house-user"></i> LOGIN</a> <a
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
    <div id="login" class="row">
        <div class="col-xl-6 col-12"><img class="img-responsive" src="img/login.png"></div>
        <div class="col-xl-6 col-12">
            <div class="pt-5">
                <div class="text-center">
                    <h2>LOGIN</h2>
                </div>
                <form method="post" action="servlet">
                    <input type="hidden" class="form-control" name="action" value="2">
                    <div class="input-group">
                    	<fieldset>
                        	<input type="text" class="form-control" name="cnpj" placeholder="CNPJ">
                        </fieldset>
                    </div>
                    <div class="input-group">
                    	<fieldset>
                        	<input type="password" class="form-control" name="senha" placeholder="Senha" minlength="6">
                        </fieldset>
                    </div>
                    <button class="btn btn-primary">ACESSAR</button>
                </form>
            </div>
        </div>
    </div>
    
	<%
	} else {
		response.sendRedirect("home.jsp");
	}
	%>
</body>

</html>