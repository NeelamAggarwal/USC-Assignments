package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class sendmail_jsp extends HttpJspBase {


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
      out.write("<title>Example Mail Sending Form");
      out.write("</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body bgcolor=\"white\">\r\n\r\n");
      out.write("<p>This page will send an electronic mail message via the\r\n");
      out.write("<code>javax.mail.Session");
      out.write("</code> resource factory that is configured into\r\nthe JNDI context for this web application.  Before it can be used\r\nsuccessfully, you must take note of the following:");
      out.write("</p>\r\n");
      out.write("<ul>\r\n");
      out.write("<li>The default configuration assumes that there is an SMTP server running\r\n    on ");
      out.write("<strong>localhost");
      out.write("</strong>.  If this is not the case, edit your\r\n    ");
      out.write("<code>conf/server.xml");
      out.write("</code> file and change the value for the\r\n    ");
      out.write("<code>mail.smtp.host");
      out.write("</code> parameter to the name of a host that provides\r\n    SMTP service for your network.");
      out.write("</li>\r\n");
      out.write("<li>The application logic assumes that no user authentication is required\r\n    by your SMTP server before accepting mail messages to be sent.");
      out.write("</li>\r\n");
      out.write("<li>All of the fields below are required.");
      out.write("</li>\r\n");
      out.write("</ul>\r\n\r\n");
      out.write("<form method=\"POST\" action=\"../../SendMailServlet\">\r\n");
      out.write("<table>\r\n\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<th align=\"center\" colspan=\"2\">\r\n      Enter The Email Message To Be Sent\r\n    ");
      out.write("</th>\r\n  ");
      out.write("</tr>\r\n\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<th align=\"right\">From:");
      out.write("</th>\r\n    ");
      out.write("<td align=\"left\">\r\n      ");
      out.write("<input type=\"text\" name=\"mailfrom\" size=\"60\">\r\n    ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<th align=\"right\">To:");
      out.write("</th>\r\n    ");
      out.write("<td align=\"left\">\r\n      ");
      out.write("<input type=\"text\" name=\"mailto\" size=\"60\">\r\n    ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<th align=\"right\">Subject:");
      out.write("</th>\r\n    ");
      out.write("<td align=\"left\">\r\n      ");
      out.write("<input type=\"text\" name=\"mailsubject\" size=\"60\">\r\n    ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<td colspan=\"2\">\r\n      ");
      out.write("<textarea name=\"mailcontent\" rows=\"10\" cols=\"80\">\r\n      ");
      out.write("</textarea>\r\n    ");
      out.write("</td> \r\n  ");
      out.write("</tr>\r\n\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<td align=\"right\">\r\n      ");
      out.write("<input type=\"submit\" value=\"Send\">\r\n    ");
      out.write("</td>\r\n    ");
      out.write("<td align=\"left\">\r\n      ");
      out.write("<input type=\"reset\" value=\"Reset\">\r\n    ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n\r\n");
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
