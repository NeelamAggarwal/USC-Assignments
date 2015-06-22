package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class plugin_jsp extends HttpJspBase {


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
      out.write("<!-- \r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n");
      out.write("<title> Plugin example ");
      out.write("</title>\r\n");
      out.write("<body bgcolor=\"white\">\r\n");
      out.write("<h3> Current time is : ");
      out.write("</h3>\r\n");
      out.println("<OBJECT classid=\"clsid:8AD9C840-044E-11D1-B3E9-00805F499D93\" width=\"160\"" + " height=\"150\"" + " codebase=\"http://java.sun.com/products/plugin/1.2.2/jinstall-1_2_2-win.cab#Version=1,2,2,0\">");
      out.println("<PARAM name=\"java_code\" value=\"Clock2.class\">");
      out.println("<PARAM name=\"java_codebase\" value=\"/examples/jsp/plugin/applet\">");
      out.println("<PARAM name=\"type\" value=\"application/x-java-applet;version=1.2\">");
      out.println("<COMMENT>");
      out.println("<EMBED type=\"application/x-java-applet;version=1.2\" width=\"160\"" + " height=\"150\"" + " pluginspage=\"http://java.sun.com/products/plugin/\" java_code=\"Clock2.class\" java_codebase=\"/examples/jsp/plugin/applet\"");
      out.println(">");
      out.println("<NOEMBED>");
      out.println("</COMMENT>");
      out.write("\r\n        Plugin tag OBJECT or EMBED not supported by browser.\r\n    ");
      out.write("\n");
      out.println("</NOEMBED></EMBED>");
      out.println("</OBJECT>");
      out.write("\r\n");
      out.write("<p>\r\n");
      out.write("<h4>\r\n");
      out.write("<font color=red> \r\nThe above applet is loaded using the Java Plugin from a jsp page using the\r\nplugin tag.\r\n");
      out.write("</font>\r\n");
      out.write("</h4>\r\n");
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
