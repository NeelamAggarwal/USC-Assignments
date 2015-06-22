package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class err_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  static {
    _jspx_includes = new java.util.Vector(1);
    _jspx_includes.add("/jsp/error/error.html");
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    javax.servlet.jsp.PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html;charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"errorpge.jsp", true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n");
      out.write("<!--\r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n");
      out.write("<body bgcolor=\"lightblue\">\r\n\r\n\t");
      out.write("\r\n\t");
      error.Smart foo = null;
      synchronized (request) {
        foo = (error.Smart) pageContext.getAttribute("foo", PageContext.REQUEST_SCOPE);
        if (foo == null){
          try {
            foo = (error.Smart) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "error.Smart");
          } catch (ClassNotFoundException exc) {
            throw new InstantiationException(exc.getMessage());
          } catch (Exception exc) {
            throw new ServletException("Cannot create bean of class " + "error.Smart", exc);
          }
          pageContext.setAttribute("foo", foo, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n\t");
 
		String name = null;

		if (request.getParameter("name") == null) {
	
      out.write("\r\n\t");
      out.write("<html>\r\n");
      out.write("<!--\r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n\r\n");
      out.write("<body bgcolor=\"white\">\r\n\r\n");
      out.write("<h1> This example uses ");
      out.write("<b>errorpage");
      out.write("</b> directive ");
      out.write("</h1>\r\n");
      out.write("<br>\r\n");
      out.write("<h3> Select my favourite car.");
      out.write("</h3>\r\n");
      out.write("<form method=get action=err.jsp>\r\n");
      out.write("<!-- ");
      out.write("<br> Make a guess: -->\r\n");
      out.write("<SELECT NAME=\"name\" SIZE=5>\r\n");
      out.write("<OPTION VALUE=\"integra\"> Acura Integra ");
      out.write("<BR>\r\n");
      out.write("<OPTION VALUE=\"bmw328i\"> BMW 328I ");
      out.write("<BR>\r\n");
      out.write("<OPTION VALUE=\"z3\"> BMW Z3 ");
      out.write("<BR>\r\n");
      out.write("<OPTION VALUE=\"infiniti\"> InfinitiQ3 ");
      out.write("<BR>\r\n");
      out.write("<OPTION VALUE=\"audi\"> Audi A8 ");
      out.write("<BR>\r\n");
      out.write("</SELECT>\r\n");
      out.write("<br> ");
      out.write("<INPUT TYPE=submit name=submit Value=\"Submit\">\r\n");
      out.write("</form>\r\n\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n\t");

		} else {
		  foo.setName(request.getParameter("name"));
		  if (foo.getName().equalsIgnoreCase("integra"))
		  	name = "acura";
		  if (name.equalsIgnoreCase("acura")) {
	
      out.write("\r\n\r\n\t");
      out.write("<H1> Yes!!! ");
      out.write("<a href=\"http://www.acura.com\">Acura");
      out.write("</a> is my favorite car.\r\n\r\n\t");
 
		  }
		}	
	
      out.write("\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n\r\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }
}
