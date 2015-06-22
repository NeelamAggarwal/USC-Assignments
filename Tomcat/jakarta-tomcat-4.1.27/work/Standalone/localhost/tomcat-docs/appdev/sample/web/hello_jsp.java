package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class hello_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

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
      out.write("<head>\r\n");
      out.write("<title>Sample Application JSP Page");
      out.write("</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body bgcolor=white>\r\n\r\n");
      out.write("<table border=\"0\">\r\n");
      out.write("<tr>\r\n");
      out.write("<td align=center>\r\n");
      out.write("<img src=\"images/tomcat.gif\">\r\n");
      out.write("</td>\r\n");
      out.write("<td>\r\n");
      out.write("<h1>Sample Application JSP Page");
      out.write("</h1>\r\nThis is the output of a JSP page that is part of the Hello, World\r\napplication.  It displays several useful values from the request\r\nwe are currently processing.\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n\r\n");
      out.write("<table border=\"0\" border=\"100%\">\r\n");
      out.write("<tr>\r\n  ");
      out.write("<th align=\"right\">Context Path:");
      out.write("</th>\r\n  ");
      out.write("<td align=\"left\">");
      out.print( request.getContextPath() );
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n  ");
      out.write("<th align=\"right\">Path Information:");
      out.write("</th>\r\n  ");
      out.write("<td align=\"left\">");
      out.print( request.getPathInfo() );
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n  ");
      out.write("<th align=\"right\">Query String:");
      out.write("</th>\r\n  ");
      out.write("<td align=\"left\">");
      out.print( request.getQueryString() );
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n  ");
      out.write("<th align=\"right\">Request Method:");
      out.write("</th>\r\n  ");
      out.write("<td align=\"left\">");
      out.print( request.getMethod() );
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n  ");
      out.write("<th align=\"right\">Servlet Path:");
      out.write("</th>\r\n  ");
      out.write("<td align=\"left\">");
      out.print( request.getServletPath() );
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</body>\r\n");
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
