/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.55
 * Generated at: 2020-07-14 11:46:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.JSONString;
import org.json.JSONStringer;
import org.json.JSONArray;
import model.Conexao;
import org.json.JSONObject;

public final class solicitacoes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("org.json.JSONString");
    _jspx_imports_classes.add("model.Conexao");
    _jspx_imports_classes.add("org.json.JSONObject");
    _jspx_imports_classes.add("org.json.JSONStringer");
    _jspx_imports_classes.add("org.json.JSONArray");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"pt\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=0.5\">\r\n");
      out.write("<title>AGENDA</title>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"https://kit.fontawesome.com/5000ee8fc6.js\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("<link\r\n");
      out.write("\thref=\"https://fonts.googleapis.com/css?family=Fjalla+One&display=swap\"\r\n");
      out.write("\trel=\"stylesheet\">\r\n");
      out.write("\t<script charset=\"UTF-8\" src=\"js/script.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t");

	HttpSession hs = request.getSession();
	if(hs.getAttribute("cnpj") != null){
		Conexao conection = new Conexao();
		JSONObject obj = new JSONObject();
		obj.put("cnpj", hs.getAttribute("cnpj"));
		String result = conection.conexao(obj, 2,"svMensagem");
		JSONObject obVerify = new JSONObject(result);
	
      out.write("\r\n");
      out.write("\t<div class=\"pos-f-t \">\r\n");
      out.write("\t\t<div class=\"collapse\" id=\"navbarToggleExternalContent\">\r\n");
      out.write("\t\t\t<div class=\"bg-dark p-2\">\r\n");
      out.write("\t\t\t\t<ul class=\"navbar-nav\">\r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item pt-lg-0 pt-3\"><a\r\n");
      out.write("\t\t\t\t\t\tclass=\"nav-link text-white mr-3\" href=\"login.jsp\">Login</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<nav class=\"navbar navbar-dark\">\r\n");
      out.write("\t\t\t<button id=\"hamb\">\r\n");
      out.write("\t\t\t\t<span class=\"hamburguer\"></span>\r\n");
      out.write("\t\t\t\t");

					if (obVerify.getBoolean("verify")) {
						out.println("<span class=\"new\"></span>");
					}
				
      out.write("\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t\t<div id=\"mySidebar\" class=\"sidebar\">\r\n");
      out.write("\t\t\t\t<button class=\"close-side\">&times;</button>\r\n");
      out.write("\t\t\t\t<a href=\"home.jsp\"> <i class=\"fas fa-home\"></i>\r\n");
      out.write("\t\t\t\t\tFEED\r\n");
      out.write("\t\t\t\t</a> <a href=\"perfil.jsp\"> <i class=\"fas fa-user\"></i> PERFIL\r\n");
      out.write("\t\t\t\t</a> <a href=\"socios.jsp\"> <i class=\"fas fa-user-friends\"></i>\r\n");
      out.write("\t\t\t\t\tSOCIOS\r\n");
      out.write("\t\t\t\t</a> <a href=\"solicitacoes.jsp\" class=\"active\"> <i class=\"fas fa-address-book\"></i>\r\n");
      out.write("\t\t\t\t\tSOLICITAÇÕES\r\n");
      out.write("\t\t\t\t</a> <a href=\"mensagem.jsp\"> <i class=\"fas fa-comments\"></i>\r\n");
      out.write("\t\t\t\t\tMENSAGENS ");
 if (obVerify.getBoolean("verify")) { out.println("<span class=\"new\"></span>"); } 
      out.write("\r\n");
      out.write("\t\t\t\t</a> <a href=\"agenda.jsp\"> <i class=\"fas fa-calendar-alt\"></i>\r\n");
      out.write("\t\t\t\t\tAGENDA\r\n");
      out.write("\t\t\t\t</a> <a href=\"meusprodutos.jsp\"> <i class=\"fas fa-box\"></i> MEUS\r\n");
      out.write("\t\t\t\t\tPRODUTOS\r\n");
      out.write("\t\t\t\t</a> <a href=\"pedidos.jsp\"> <i class=\"fas fa-truck\"></i> PEDIDOS\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<form method=\"POST\" action=\"servlet\">\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"action\" value=\"3\">\r\n");
      out.write("\t\t\t\t\t<button>\r\n");
      out.write("\t\t\t\t\t\t<i class=\"fas fa-sign-out-alt\"></i> SAIR\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"logo jusify-content-center\">\r\n");
      out.write("\t\t\t\t<a class=\"navbar-brand\" href=\"index.jsp\">\r\n");
      out.write("\t\t\t\t\t<h1>\r\n");
      out.write("\t\t\t\t\t\t<img class=\"nav-item\" src=\"icons/logo.png\" height=\"45px\">eRelation\r\n");
      out.write("\t\t\t\t\t</h1>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"color: white; font-size: 42px; margin-right: 20px\">\r\n");
      out.write("\t\t\t\t");

					conection = new Conexao();
					result = conection.conexao(obj, 18, "svEmpresa");
					obVerify = new JSONObject(result);
					if (obVerify.getBoolean("verify")) {
						out.println("<span class=\"new\"></span>");
					}
				
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"caixaentrada.jsp\" style=\"color: white;\"><i\r\n");
      out.write("\t\t\t\t\tclass=\"fas fa-envelope\"></i></a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</nav>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");

	obj.put("cnpj_origem", hs.getAttribute("cnpj"));
	conection = new Conexao();
	
	result = conection.conexao(obj,12,"svEmpresa");
	
	if(!result.isEmpty() && !result.equals("[]")){
		JSONArray arr = new JSONArray(result);
	
      out.write("\r\n");
      out.write("\t<div id=\"solicitacoes\" class=\"col-lg-8 col-10 container-fluid\">\r\n");
      out.write("        <div class=\"separator\">\r\n");
      out.write("            <h5>SOLICITAÇÕES</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("\t\t");

			for (int i = 0; i < arr.length(); i++) {
				JSONObject ob = arr.getJSONObject(i);
				obj = new JSONObject();
				if(ob.getString("cnpj_origem").equals(hs.getAttribute("cnpj"))){
					//ENVIADAS POR MIM
					
					obj.put("cnpj", ob.getString("cnpj_dest"));
					result = conection.conexao(obj, 3, "svEmpresa");
					JSONObject objs = new JSONObject(result);
					System.out.println(result);
			
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div class=\"card\">\r\n");
      out.write("\t            <div class=\"img col-12 col-lg-5\" style=\"background-image: url('http://");
      out.print(conection.getIp());
      out.write(':');
      out.print(conection.getPort());
      out.write("/WebServlet/svEmpresa?cnpj=");
      out.print(ob.getString("cnpj_dest"));
      out.write("');\">\r\n");
      out.write("\t            </div>\r\n");
      out.write("\t\t\t\t<div class=\"card-body\">\r\n");
      out.write("\t\t\t\t\t<p>Enviada para: ");
      out.print( objs.getString("empresa") );
      out.write("</p>\r\n");
      out.write("\t\t\t\t\t<div class=\"group\">\r\n");
      out.write("\t\t\t\t\t\t<form method=\"POST\" action=\"servlet\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"action\" value=\"7\" type=\"hidden\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"cnpjDest\" value=\"");
      out.print(ob.getString("cnpj_dest"));
      out.write("\" type=\"hidden\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-danger\">Cancelar solicitação</button>\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t\t<form method=\"POST\" action=\"perfilempresa.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"cnpj\" value=\"");
      out.print(ob.getString("cnpj_dest"));
      out.write("\" type=\"hidden\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-primary\">Vizualizar perfil</button>\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t");
} else {
			obj.put("cnpj", ob.getString("cnpj_origem"));
			result = conection.conexao(obj, 3, "svEmpresa");
			JSONObject objs = new JSONObject(result);
		
      out.write("\r\n");
      out.write("\t\t\t<div class=\"card\">\r\n");
      out.write("\t            <div class=\"img col-12 col-lg-5\" style=\"background-image: url('http://");
      out.print(conection.getIp());
      out.write(':');
      out.print(conection.getPort());
      out.write("/WebServlet/svEmpresa?cnpj=");
      out.print(ob.getString("cnpj_origem"));
      out.write("');\">\r\n");
      out.write("\t            </div>\r\n");
      out.write("\t\t\t\t<div class=\"card-body\">\r\n");
      out.write("\t\t\t\t\t<p>Recida por: ");
      out.print( objs.getString("empresa") );
      out.write("</p>\r\n");
      out.write("\t\t\t\t\t<div class=\"group\">\r\n");
      out.write("\t\t\t\t\t\t<form method=\"POST\" action=\"servlet\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"action\" value=\"8\" type=\"hidden\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"cnpjDest\" value=\"");
      out.print(ob.getString("cnpj_origem"));
      out.write("\" type=\"hidden\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-success\">Aceitar solicitação</button>\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t\t<form method=\"POST\" action=\"servlet\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"action\" value=\"7\" type=\"hidden\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"cnpjDest\" value=\"");
      out.print(ob.getString("cnpj_origem"));
      out.write("\" type=\"hidden\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-danger\">Recusar solicitação</button>\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t");
		}
			}
		} else {
			
      out.write("\r\n");
      out.write("\t\t\t<div class=\"empty\">\r\n");
      out.write("\t\t\t\t<img src=\"img/empty2.png\" class=\"col-6\">\r\n");
      out.write("\t\t\t\t<div class=\"empty-body\">\r\n");
      out.write("\t\t\t\t\t<h1>Ops!</h1>\r\n");
      out.write("\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\tParece que você não possui nenhuma solicitação nova, volte mais tarde.\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t<span class=\"obs\">\r\n");
      out.write("\t\t\t\t\t\tObs.: Novas solicitações aparecerão aqui, novos parceiros significam novas propostas.<br>Então explore!\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t<a href=\"home.jsp\" class=\"empty-button\">Explorar</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t");
}
	
	} else {
		response.sendRedirect("index.jsp?e=1002");
	}
	
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
