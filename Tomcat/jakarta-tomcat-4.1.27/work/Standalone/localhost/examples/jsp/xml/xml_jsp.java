package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;
import java.util.Date;
import java.util.Locale;
import java.text.*;

public class xml_jsp extends HttpJspBase {


  String getDateTimeStr(Locale l) {
    DateFormat df = SimpleDateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, l);
    return df.format(new Date());
  }

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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>");
      out.write("<head>");
      out.write("<title>");
      out.write("Example JSP in XML format");
      out.write("</title>");
      out.write("</head>");
      out.write("<body>");
      out.write("\nThis is the output of a simple JSP using XML format. ");
      out.write("<br/>");
      out.write("<div>");
      out.write("Use a jsp:scriptlet to loop from 1 to 10: ");
      out.write("</div>");

// Note we need to declare CDATA because we don't escape the less than symbol
  for (int i = 1; i<=10; i++) {
    out.println(i);
    if (i < 10) {
      out.println(", ");
    }
  }
      out.write("\n  <br><br>");
      out.write("<div align=\"left\">");
      out.write("\n  Use a jsp:expression to write the date and time in the browser's locale: ");
      out.print(getDateTimeStr(request.getLocale()));
      out.write("</div>");
      out.write("\n  ");
      out.write("<p>");
      out.write("This sentence is enclosed in a jsp:text element.");
      out.write("</p>");
      out.write("\n");
      out.write("</body>");
      out.write("</html>");
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
