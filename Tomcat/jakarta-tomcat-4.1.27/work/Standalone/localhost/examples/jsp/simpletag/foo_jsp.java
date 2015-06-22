package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class foo_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_eg_foo_att3_att2_att1;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_eg_log;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_eg_log_toBrowser;

  public foo_jsp() {
    _jspx_tagPool_eg_foo_att3_att2_att1 = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_eg_log = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_eg_log_toBrowser = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_eg_foo_att3_att2_att1.release();
    _jspx_tagPool_eg_log.release();
    _jspx_tagPool_eg_log_toBrowser.release();
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
      out.write("<!--\r\n  Copyright (c) 1999-2001 The Apache Software Foundation.  All rights \r\n  reserved.\r\n-->\r\n");
      out.write("<body>\r\n");
      out.write("\r\n\r\nRadio stations that rock:\r\n\r\n");
      out.write("<ul>\r\n");
      /* ----  eg:foo ---- */
      examples.FooTag _jspx_th_eg_foo_0 = (examples.FooTag) _jspx_tagPool_eg_foo_att3_att2_att1.get(examples.FooTag.class);
      _jspx_th_eg_foo_0.setPageContext(pageContext);
      _jspx_th_eg_foo_0.setParent(null);
      _jspx_th_eg_foo_0.setAtt1("98.5");
      _jspx_th_eg_foo_0.setAtt2("92.3");
      _jspx_th_eg_foo_0.setAtt3("107.7");
      int _jspx_eval_eg_foo_0 = _jspx_th_eg_foo_0.doStartTag();
      if (_jspx_eval_eg_foo_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        String member = null;
        if (_jspx_eval_eg_foo_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
          out = _bc;
          _jspx_th_eg_foo_0.setBodyContent(_bc);
          _jspx_th_eg_foo_0.doInitBody();
        }
        member = (String) pageContext.findAttribute("member");
        do {
          out.write("\r\n");
          out.write("<li>");
          out.print( member );
          out.write("</li>\r\n");
          int evalDoAfterBody = _jspx_th_eg_foo_0.doAfterBody();
          member = (String) pageContext.findAttribute("member");
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_eg_foo_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = pageContext.popBody();
      }
      if (_jspx_th_eg_foo_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_eg_foo_att3_att2_att1.reuse(_jspx_th_eg_foo_0);
      out.write("\r\n");
      out.write("</ul>\r\n\r\n");
      if (_jspx_meth_eg_log_0(pageContext))
        return;
      out.write("\r\n\r\n");
      if (_jspx_meth_eg_log_1(pageContext))
        return;
      out.write("\r\n\r\n");
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

  private boolean _jspx_meth_eg_log_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  eg:log ---- */
    examples.LogTag _jspx_th_eg_log_0 = (examples.LogTag) _jspx_tagPool_eg_log.get(examples.LogTag.class);
    _jspx_th_eg_log_0.setPageContext(pageContext);
    _jspx_th_eg_log_0.setParent(null);
    int _jspx_eval_eg_log_0 = _jspx_th_eg_log_0.doStartTag();
    if (_jspx_eval_eg_log_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_eg_log_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        out = _bc;
        _jspx_th_eg_log_0.setBodyContent(_bc);
        _jspx_th_eg_log_0.doInitBody();
      }
      do {
        out.write("\r\nDid you see me on the stderr window?\r\n");
        int evalDoAfterBody = _jspx_th_eg_log_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_eg_log_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
    }
    if (_jspx_th_eg_log_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_eg_log.reuse(_jspx_th_eg_log_0);
    return false;
  }

  private boolean _jspx_meth_eg_log_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  eg:log ---- */
    examples.LogTag _jspx_th_eg_log_1 = (examples.LogTag) _jspx_tagPool_eg_log_toBrowser.get(examples.LogTag.class);
    _jspx_th_eg_log_1.setPageContext(pageContext);
    _jspx_th_eg_log_1.setParent(null);
    _jspx_th_eg_log_1.setToBrowser("true");
    int _jspx_eval_eg_log_1 = _jspx_th_eg_log_1.doStartTag();
    if (_jspx_eval_eg_log_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_eg_log_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
        out = _bc;
        _jspx_th_eg_log_1.setBodyContent(_bc);
        _jspx_th_eg_log_1.doInitBody();
      }
      do {
        out.write("\r\nDid you see me on the browser window as well?\r\n");
        int evalDoAfterBody = _jspx_th_eg_log_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_eg_log_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = pageContext.popBody();
    }
    if (_jspx_th_eg_log_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_eg_log_toBrowser.reuse(_jspx_th_eg_log_1);
    return false;
  }
}
