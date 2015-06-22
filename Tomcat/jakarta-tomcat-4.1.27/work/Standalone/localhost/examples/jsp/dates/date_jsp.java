package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class date_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    javax.servlet.jsp.PageContext pageContext = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html;charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n");
      out.write("<!--\r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n\r\n");
      out.write("\r\n\r\n");
      out.write("<body bgcolor=\"white\">\r\n");
      dates.JspCalendar clock = null;
      synchronized (pageContext) {
        clock = (dates.JspCalendar) pageContext.getAttribute("clock", PageContext.PAGE_SCOPE);
        if (clock == null){
          try {
            clock = (dates.JspCalendar) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "dates.JspCalendar");
          } catch (ClassNotFoundException exc) {
            throw new InstantiationException(exc.getMessage());
          } catch (Exception exc) {
            throw new ServletException("Cannot create bean of class " + "dates.JspCalendar", exc);
          }
          pageContext.setAttribute("clock", clock, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\r\n\r\n");
      out.write("<font size=4>\r\n");
      out.write("<ul>\r\n");
      out.write("<li>\tDay of month: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getDayOfMonth())));
      out.write("\r\n");
      out.write("<li>\tYear: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getYear())));
      out.write("\r\n");
      out.write("<li>\tMonth: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getMonth())));
      out.write("\r\n");
      out.write("<li>\tTime: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getTime())));
      out.write("\r\n");
      out.write("<li>\tDate: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getDate())));
      out.write("\r\n");
      out.write("<li>\tDay: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getDay())));
      out.write("\r\n");
      out.write("<li>\tDay Of Year: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getDayOfYear())));
      out.write("\r\n");
      out.write("<li>\tWeek Of Year: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getWeekOfYear())));
      out.write("\r\n");
      out.write("<li>\tera: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getEra())));
      out.write("\r\n");
      out.write("<li>\tDST Offset: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getDSTOffset())));
      out.write("\r\n");
      out.write("<li>\tZone Offset: is  ");
      out.print(JspRuntimeLibrary.toString((((dates.JspCalendar)pageContext.findAttribute("clock")).getZoneOffset())));
      out.write("\r\n");
      out.write("</ul>\r\n");
      out.write("</font>\r\n\r\n");
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
