package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;
import num.NumberGuessBean;

public class numguess_jsp extends HttpJspBase {


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

      out.write("<!--\r\n  Copyright (c) 1999 The Apache Software Foundation.  All rights \r\n  reserved.\r\n\r\n  Number Guess Game\r\n  Written by Jason Hunter, CTO, K&A Software\r\n  http://www.servlets.com\r\n-->\r\n\r\n");
      out.write("\r\n\r\n");
      num.NumberGuessBean numguess = null;
      synchronized (session) {
        numguess = (num.NumberGuessBean) pageContext.getAttribute("numguess", PageContext.SESSION_SCOPE);
        if (numguess == null){
          try {
            numguess = (num.NumberGuessBean) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "num.NumberGuessBean");
          } catch (ClassNotFoundException exc) {
            throw new InstantiationException(exc.getMessage());
          } catch (Exception exc) {
            throw new ServletException("Cannot create bean of class " + "num.NumberGuessBean", exc);
          }
          pageContext.setAttribute("numguess", numguess, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\r\n");
      JspRuntimeLibrary.introspect(pageContext.findAttribute("numguess"), request);
      out.write("\r\n\r\n");
      out.write("<html>\r\n");
      out.write("<head>");
      out.write("<title>Number Guess");
      out.write("</title>");
      out.write("</head>\r\n");
      out.write("<body bgcolor=\"white\">\r\n");
      out.write("<font size=4>\r\n\r\n");
 if (numguess.getSuccess()) { 
      out.write("\r\n\r\n  Congratulations!  You got it.\r\n  And after just ");
      out.print( numguess.getNumGuesses() );
      out.write(" tries.");
      out.write("<p>\r\n\r\n  ");
 numguess.reset(); 
      out.write("\r\n\r\n  Care to ");
      out.write("<a href=\"numguess.jsp\">try again");
      out.write("</a>?\r\n\r\n");
 } else if (numguess.getNumGuesses() == 0) { 
      out.write("\r\n\r\n  Welcome to the Number Guess game.");
      out.write("<p>\r\n\r\n  I'm thinking of a number between 1 and 100.");
      out.write("<p>\r\n\r\n  ");
      out.write("<form method=get>\r\n  What's your guess? ");
      out.write("<input type=text name=guess>\r\n  ");
      out.write("<input type=submit value=\"Submit\">\r\n  ");
      out.write("</form>\r\n\r\n");
 } else { 
      out.write("\r\n\r\n  Good guess, but nope.  Try ");
      out.write("<b>");
      out.print( numguess.getHint() );
      out.write("</b>.\r\n\r\n  You have made ");
      out.print( numguess.getNumGuesses() );
      out.write(" guesses.");
      out.write("<p>\r\n\r\n  I'm thinking of a number between 1 and 100.");
      out.write("<p>\r\n\r\n  ");
      out.write("<form method=get>\r\n  What's your guess? ");
      out.write("<input type=text name=guess>\r\n  ");
      out.write("<input type=submit value=\"Submit\">\r\n  ");
      out.write("</form>\r\n\r\n");
 } 
      out.write("\r\n\r\n");
      out.write("</font>\r\n");
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
