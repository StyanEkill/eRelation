/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.55
 * Generated at: 2020-07-14 11:41:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.JSONObject;
import org.json.JSONArray;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("org.json.JSONObject");
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=0.5\">\r\n");
      out.write("    <title>Index</title>\r\n");
      out.write("    \r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/jquery.js\"></script>\r\n");
      out.write("    <script src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css?family=Fjalla+One&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("    <script src=\"https://kit.fontawesome.com/5000ee8fc6.js\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("    <script src=\"js/script.js\" charset=\"utf-8\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body id=\"index\">\r\n");
      out.write("\t");

		HttpSession hs = request.getSession();
		if(hs.getAttribute("cnpj") == null){
	
      out.write("\r\n");
      out.write("\t<div class=\"pos-f-t \">\r\n");
      out.write("\t\t<div class=\"collapse\" id=\"navbarToggleExternalContent\">\r\n");
      out.write("\t\t\t<div class=\"bg-dark p-2\">\r\n");
      out.write("\t\t\t\t<ul class=\"navbar-nav\">\r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item pt-lg-0 pt-3\"><a class=\"nav-link text-white mr-3\" href=\"login.jsp\">Login</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<nav class=\"navbar navbar-dark\">\r\n");
      out.write("\t\t\t<button id=\"hamb\">\r\n");
      out.write("\t\t\t\t<span class=\"hamburguer\"></span>\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t\t<div id=\"mySidebar\" class=\"sidebar\">\r\n");
      out.write("\t\t\t\t<button class=\"close-side\">&times;</button>\r\n");
      out.write("\t\t\t\t<a href=\"index.jsp\" class=\"active\"><i class=\"fas fa-home\"></i> HOME</a> <a\r\n");
      out.write("\t\t\t\t\thref=\"login.jsp\"><i class=\"fas fa-house-user\"></i> LOGIN</a> <a\r\n");
      out.write("\t\t\t\t\thref=\"cadastro.jsp\"><i class=\"fas fa-user-plus\"></i>\r\n");
      out.write("\t\t\t\t\tCADASTRO</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"logo\">\r\n");
      out.write("\t\t\t\t<a class=\"navbar-brand\" href=\"index.html\">\r\n");
      out.write("\t\t\t\t\t<h1>\r\n");
      out.write("\t\t\t\t\t\t<img class=\"nav-item\" src=\"icons/logo.png\" width=\"45px\">\r\n");
      out.write("\t\t\t\t\t\teRelations\r\n");
      out.write("\t\t\t\t\t</h1>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"color: white; font-size: 42px; margin-right: 20px\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</nav>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"fundo\">\r\n");
      out.write("\r\n");
      out.write("        <div class=\"text-center text-white legenda\">\r\n");
      out.write("            <h1>Começe</h1>\r\n");
      out.write("            <h4>Inicie já o seu marketing empresarial</h4><br>\r\n");
      out.write("            <a type=\"button\" class=\"btn btn-info\" href=\"login.html\"><i class=\"fas fa-arrow-right\"></i></a>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"fusion1 row m-0\">\r\n");
      out.write("            <img class=\"img-responsive\" src=\"img/indexx.png\">\r\n");
      out.write("            <div class=\"text-center z1\">\r\n");
      out.write("            <h2 class=\"color1\"><i>Serviços</i></h2>\r\n");
      out.write("            <h4>Procure por empresas que possam<br>oferecer recursos e serviços</h4>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"fusion2 row m-0\">\r\n");
      out.write("            <div class=\"text-center z2\">\r\n");
      out.write("                <h2 class=\"color2\"><i>Negocios</i></h2>\r\n");
      out.write("                <h4 class=\"text-white\">Venda seu produto e agende reuniões<br>com seus parceiros empresariais</h4>\r\n");
      out.write("            </div>\r\n");
      out.write("            <img class=\"img-responsive\" src=\"img/icon.png\">\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"info row m-0\">\r\n");
      out.write("        <div class=\"card\">\r\n");
      out.write("            <div class=\"card-header\">\r\n");
      out.write("                <i class=\"fas fa-handshake\"></i>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <h5 class=\"card-title\">Feche parcerias</h5>\r\n");
      out.write("                <p class=\"card-text\">eRelation oferece um sistema de parceiros para um primeiro contato com outras empresas</p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"card\">\r\n");
      out.write("            <div class=\"card-header\">\r\n");
      out.write("                <i class=\"fas fa-calendar-alt\"></i>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <h5 class=\"card-title\">Eventos</h5>\r\n");
      out.write("                <p class=\"card-text\">Agende reuniões ou encontros quando você quiser.</p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"card\">\r\n");
      out.write("            <div class=\"card-header\">\r\n");
      out.write("                <i class=\"fas fa-truck\"></i>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <h5 class=\"card-title\">Pedidos</h5>\r\n");
      out.write("                <p class=\"card-text\">Realize e receba pedidos de seus parceros ou afiliados.</p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"card\">\r\n");
      out.write("            <div class=\"card-header\">\r\n");
      out.write("                <i class=\"fas fa-truck-loading\"></i>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <h5 class=\"card-title\">Entregas</h5>\r\n");
      out.write("                <p class=\"card-text\">eRealtion contém um sistema de entrega completo e seguro.</p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"card\">\r\n");
      out.write("            <div class=\"card-header\">\r\n");
      out.write("                <i class=\"fas fa-boxes\"></i>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <h5 class=\"card-title\">Produtos</h5>\r\n");
      out.write("                <p class=\"card-text\">Cadastre seus produtos oferecidos.</p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"card\">\r\n");
      out.write("            <div class=\"card-header\">\r\n");
      out.write("                <i class=\"fas fa-bell\"></i>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <h5 class=\"card-title\">Notificação</h5>\r\n");
      out.write("                <p class=\"card-text\">Receba notificações e fique por dentro do que acontece com sua empresa.</p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"footer\">\r\n");
      out.write("        <h3>Acesse também</h3>\r\n");
      out.write("        <div class=\"contatos\">\r\n");
      out.write("            <a href=\"#\"><i class=\"fab fa-facebook-f\"></i></a>\r\n");
      out.write("            <a href=\"#\"><i class=\"fab fa-twitter\"></i></a>\r\n");
      out.write("            <a href=\"#\"><i class=\"fab fa-android\"></i></a>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"sub-footer\">\r\n");
      out.write("        <h6>Copyright 2020 - eRelations - All Rights Reserved</h6>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\t");

	} else {
		response.sendRedirect("home.jsp");
	}
	
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
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
