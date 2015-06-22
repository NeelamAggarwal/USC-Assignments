package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class carts_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  static {
    _jspx_includes = new java.util.Vector(1);
    _jspx_includes.add("/jsp/sessions/carts.html");
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
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n");
      out.write("<!--\r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n\r\n");
      sessions.DummyCart cart = null;
      synchronized (session) {
        cart = (sessions.DummyCart) pageContext.getAttribute("cart", PageContext.SESSION_SCOPE);
        if (cart == null){
          try {
            cart = (sessions.DummyCart) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "sessions.DummyCart");
          } catch (ClassNotFoundException exc) {
            throw new InstantiationException(exc.getMessage());
          } catch (Exception exc) {
            throw new ServletException("Cannot create bean of class " + "sessions.DummyCart", exc);
          }
          pageContext.setAttribute("cart", cart, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\r\n\r\n");
      JspRuntimeLibrary.introspect(pageContext.findAttribute("cart"), request);
      out.write("\r\n");

	cart.processRequest(request);

      out.write("\r\n\r\n\r\n");
      out.write("<FONT size = 5 COLOR=\"#CC0000\">\r\n");
      out.write("<br> You have the following items in your cart:\r\n");
      out.write("<ol>\r\n");
 
	String[] items = cart.getItems();
	for (int i=0; i<items.length; i++) {

      out.write("\r\n");
      out.write("<li> ");
 out.print(util.HTMLFilter.filter(items[i])); 
      out.write(" \r\n");

	}

      out.write("\r\n");
      out.write("</ol>\r\n\r\n");
      out.write("</FONT>\r\n\r\n");
      out.write("<hr>\r\n");
      out.write("<html>\r\n");
      out.write("<!--\r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n\r\n");
      out.write("<head>\r\n    ");
      out.write("<title>carts");
      out.write("</title>\r\n");
      out.write("</head>\r\n\r\n ");
      out.write("<body bgcolor=\"white\">\r\n");
      out.write("<font size = 5 color=\"#CC0000\">\r\n\r\n");
      out.write("<form type=POST action=carts.jsp>\r\n");
      out.write("<BR>\r\nPlease enter item to add or remove:\r\n");
      out.write("<br>\r\nAdd Item:\r\n\r\n");
      out.write("<SELECT NAME=\"item\">\r\n");
      out.write("<OPTION>Beavis & Butt-head Video collection\r\n");
      out.write("<OPTION>X-files movie\r\n");
      out.write("<OPTION>Twin peaks tapes\r\n");
      out.write("<OPTION>NIN CD\r\n");
      out.write("<OPTION>JSP Book\r\n");
      out.write("<OPTION>Concert tickets\r\n");
      out.write("<OPTION>Love life\r\n");
      out.write("<OPTION>Switch blade\r\n");
      out.write("<OPTION>Rex, Rugs & Rock n' Roll\r\n");
      out.write("</SELECT>\r\n\r\n\r\n");
      out.write("<br> ");
      out.write("<br>\r\n");
      out.write("<INPUT TYPE=submit name=\"submit\" value=\"add\">\r\n");
      out.write("<INPUT TYPE=submit name=\"submit\" value=\"remove\">\r\n\r\n");
      out.write("</form>\r\n       \r\n");
      out.write("</FONT>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
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
