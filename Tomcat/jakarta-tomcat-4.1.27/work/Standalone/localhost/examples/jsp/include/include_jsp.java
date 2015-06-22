package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class include_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  static {
    _jspx_includes = new java.util.Vector(1);
    _jspx_includes.add("/jsp/include/foo.jsp");
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
      			null, true, 5120, false);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n");
      out.write("<!--\r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n\r\n");
      out.write("<body bgcolor=\"white\">\r\n\r\n");
      out.write("<font color=\"red\">\r\n\r\n");
      out.write("\r\n\r\n");
      out.write("<p>In place evaluation of another JSP which gives you the current time:\r\n\r\n");
      out.write("<!--\r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n\r\n");
      out.write("<body bgcolor=\"white\">\r\n");
      out.write("<font color=\"red\">\r\n\r\n");
      out.print( System.currentTimeMillis() );
      out.write("\r\n");
      out.write("\r\n\r\n");
      out.write("<p> ");
      JspRuntimeLibrary.include(request, response, "/jsp/include/foo.html", out, true);
      out.write(" by including the output of another JSP:\r\n\r\n");
      JspRuntimeLibrary.include(request, response, "foo.jsp", out, true);
      out.write("\r\n\r\n:-) \r\n\r\n");
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
