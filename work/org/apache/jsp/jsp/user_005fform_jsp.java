/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.15
 * Generated at: 2018-12-31 07:21:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class user_005fform_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(7);
    _jspx_dependants.put("/WEB-INF/lib/struts-tiles-1.3.10.jar", Long.valueOf(1448261664000L));
    _jspx_dependants.put("jar:file:/home/yongki/Documents/Untitled%20Folder/PerpusLucky/web/WEB-INF/lib/struts-taglib-1.3.10.jar!/META-INF/tld/struts-bean.tld", Long.valueOf(1227893410000L));
    _jspx_dependants.put("jar:file:/home/yongki/Documents/Untitled%20Folder/PerpusLucky/web/WEB-INF/lib/struts-taglib-1.3.10.jar!/META-INF/tld/struts-logic.tld", Long.valueOf(1227893410000L));
    _jspx_dependants.put("jar:file:/home/yongki/Documents/Untitled%20Folder/PerpusLucky/web/WEB-INF/lib/struts-tiles-1.3.10.jar!/META-INF/tld/struts-tiles.tld", Long.valueOf(1227893438000L));
    _jspx_dependants.put("/WEB-INF/lib/struts-taglib-1.3.10.jar", Long.valueOf(1448261664000L));
    _jspx_dependants.put("jar:file:/home/yongki/Documents/Untitled%20Folder/PerpusLucky/web/WEB-INF/lib/struts-taglib-1.3.10.jar!/META-INF/tld/struts-html.tld", Long.valueOf(1227893410000L));
    _jspx_dependants.put("jar:file:/home/yongki/Documents/Untitled%20Folder/PerpusLucky/web/WEB-INF/lib/struts-taglib-1.3.10.jar!/META-INF/tld/struts-nested.tld", Long.valueOf(1227893410000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fform_0026_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fstyleClass_005fsize_005fproperty_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fsubmit_0026_005fvalue_005fstyleClass_005fonclick_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fform_0026_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fstyleClass_005fsize_005fproperty_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fsubmit_0026_005fvalue_005fstyleClass_005fonclick_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005fform_0026_005faction.release();
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fstyleClass_005fsize_005fproperty_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005fsubmit_0026_005fvalue_005fstyleClass_005fonclick_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
 response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=Cp1252\"/>\n");
      out.write("<title>Form User </title>\n");
      out.write("   <!--Calendar-->\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"");
      if (_jspx_meth_html_005frewrite_005f0(_jspx_page_context))
        return;
      out.write("\"></script><!--buat perhitungan tanggal-->\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"");
      if (_jspx_meth_html_005frewrite_005f1(_jspx_page_context))
        return;
      out.write("\"></script><!--buat tampilan-->\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"");
      if (_jspx_meth_html_005frewrite_005f2(_jspx_page_context))
        return;
      out.write("\"></script><!--buat calender-->\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      if (_jspx_meth_html_005frewrite_005f3(_jspx_page_context))
        return;
      out.write("\" media=\"screen\"></link>\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"");
      if (_jspx_meth_html_005frewrite_005f4(_jspx_page_context))
        return;
      out.write("\"></script><!--buat AJAX-->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body bgcolor=\"#D3D3D3\">\n");
      out.write("\n");
      out.write("    \n");
      out.write("   <center> \n");
      out.write("   <h1>Form User</h1>\n");
      out.write("   </center>\n");
      out.write("  <tr valign=\"top\"><td>&nbsp;</td></tr>\n");
      out.write("      <tr valign=\"top\"><td></td></tr>\n");
      out.write("<body>\n");
      if (_jspx_meth_html_005fform_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("<script language=\"javascript\">\n");
      out.write("function valid(){\n");
      out.write("\tif(document.forms[0].username.value==\"\"){\n");
      out.write("\t\talert('Masukkan Nama User !!!');\n");
      out.write("\t\treturn false;\n");
      out.write("\t}\n");
      out.write("}\n");
      out.write(" function doBack()\n");
      out.write(" {\t\t\t\n");
      out.write("    \tdocument.forms[0].action = \"");
      if (_jspx_meth_html_005frewrite_005f5(_jspx_page_context))
        return;
      out.write("\";\n");
      out.write("\t\tdocument.forms[0].submit();\n");
      out.write("    }\n");
      out.write("</script>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_html_005frewrite_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:rewrite
    org.apache.struts.taglib.html.RewriteTag _jspx_th_html_005frewrite_005f0 = (org.apache.struts.taglib.html.RewriteTag) _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.get(org.apache.struts.taglib.html.RewriteTag.class);
    boolean _jspx_th_html_005frewrite_005f0_reused = false;
    try {
      _jspx_th_html_005frewrite_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005frewrite_005f0.setParent(null);
      // /jsp/user_form.jsp(15,58) name = page type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005frewrite_005f0.setPage("/js/date.js");
      int _jspx_eval_html_005frewrite_005f0 = _jspx_th_html_005frewrite_005f0.doStartTag();
      if (_jspx_th_html_005frewrite_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f0);
      _jspx_th_html_005frewrite_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005frewrite_005f0, _jsp_getInstanceManager(), _jspx_th_html_005frewrite_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005frewrite_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:rewrite
    org.apache.struts.taglib.html.RewriteTag _jspx_th_html_005frewrite_005f1 = (org.apache.struts.taglib.html.RewriteTag) _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.get(org.apache.struts.taglib.html.RewriteTag.class);
    boolean _jspx_th_html_005frewrite_005f1_reused = false;
    try {
      _jspx_th_html_005frewrite_005f1.setPageContext(_jspx_page_context);
      _jspx_th_html_005frewrite_005f1.setParent(null);
      // /jsp/user_form.jsp(16,58) name = page type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005frewrite_005f1.setPage("/js/hitung.js");
      int _jspx_eval_html_005frewrite_005f1 = _jspx_th_html_005frewrite_005f1.doStartTag();
      if (_jspx_th_html_005frewrite_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f1);
      _jspx_th_html_005frewrite_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005frewrite_005f1, _jsp_getInstanceManager(), _jspx_th_html_005frewrite_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005frewrite_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:rewrite
    org.apache.struts.taglib.html.RewriteTag _jspx_th_html_005frewrite_005f2 = (org.apache.struts.taglib.html.RewriteTag) _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.get(org.apache.struts.taglib.html.RewriteTag.class);
    boolean _jspx_th_html_005frewrite_005f2_reused = false;
    try {
      _jspx_th_html_005frewrite_005f2.setPageContext(_jspx_page_context);
      _jspx_th_html_005frewrite_005f2.setParent(null);
      // /jsp/user_form.jsp(17,58) name = page type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005frewrite_005f2.setPage("/js/dhtmlgoodies_calendar.js");
      int _jspx_eval_html_005frewrite_005f2 = _jspx_th_html_005frewrite_005f2.doStartTag();
      if (_jspx_th_html_005frewrite_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f2);
      _jspx_th_html_005frewrite_005f2_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005frewrite_005f2, _jsp_getInstanceManager(), _jspx_th_html_005frewrite_005f2_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005frewrite_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:rewrite
    org.apache.struts.taglib.html.RewriteTag _jspx_th_html_005frewrite_005f3 = (org.apache.struts.taglib.html.RewriteTag) _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.get(org.apache.struts.taglib.html.RewriteTag.class);
    boolean _jspx_th_html_005frewrite_005f3_reused = false;
    try {
      _jspx_th_html_005frewrite_005f3.setPageContext(_jspx_page_context);
      _jspx_th_html_005frewrite_005f3.setParent(null);
      // /jsp/user_form.jsp(18,45) name = page type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005frewrite_005f3.setPage("/js/dhtmlgoodies_calendar.css");
      int _jspx_eval_html_005frewrite_005f3 = _jspx_th_html_005frewrite_005f3.doStartTag();
      if (_jspx_th_html_005frewrite_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f3);
      _jspx_th_html_005frewrite_005f3_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005frewrite_005f3, _jsp_getInstanceManager(), _jspx_th_html_005frewrite_005f3_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005frewrite_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:rewrite
    org.apache.struts.taglib.html.RewriteTag _jspx_th_html_005frewrite_005f4 = (org.apache.struts.taglib.html.RewriteTag) _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.get(org.apache.struts.taglib.html.RewriteTag.class);
    boolean _jspx_th_html_005frewrite_005f4_reused = false;
    try {
      _jspx_th_html_005frewrite_005f4.setPageContext(_jspx_page_context);
      _jspx_th_html_005frewrite_005f4.setParent(null);
      // /jsp/user_form.jsp(19,58) name = page type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005frewrite_005f4.setPage("/js/Ajax.js");
      int _jspx_eval_html_005frewrite_005f4 = _jspx_th_html_005frewrite_005f4.doStartTag();
      if (_jspx_th_html_005frewrite_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f4);
      _jspx_th_html_005frewrite_005f4_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005frewrite_005f4, _jsp_getInstanceManager(), _jspx_th_html_005frewrite_005f4_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005fform_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:form
    org.apache.struts.taglib.html.FormTag _jspx_th_html_005fform_005f0 = (org.apache.struts.taglib.html.FormTag) _005fjspx_005ftagPool_005fhtml_005fform_0026_005faction.get(org.apache.struts.taglib.html.FormTag.class);
    boolean _jspx_th_html_005fform_005f0_reused = false;
    try {
      _jspx_th_html_005fform_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005fform_005f0.setParent(null);
      // /jsp/user_form.jsp(33,0) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005fform_005f0.setAction("/user/save");
      int _jspx_eval_html_005fform_005f0 = _jspx_th_html_005fform_005f0.doStartTag();
      if (_jspx_eval_html_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write('\n');
          out.write(' ');
          if (_jspx_meth_html_005fhidden_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
            return true;
          out.write("\n");
          out.write(" <br/>\n");
          out.write("        <center>                    \n");
          out.write("                            <table width=\"50%\" border=\"3\" cellspacing=\"0\" cellpadding=\"0\">\n");
          out.write("                            \n");
          out.write("\t\t\t\t\t\t\t<tr>\n");
          out.write("\t\t\t\t\t\t\t\t<td align=\"left\">Nama</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td align=\"left\">: &nbsp;</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td align=\"left\">");
          if (_jspx_meth_html_005ftext_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
            return true;
          out.write("\n");
          out.write("\t\t\t\t\t\t\t\t</td>\n");
          out.write("\t\t\t\t\t\t\t</tr>\n");
          out.write("\t\t\t\t\t\t\t<tr>\n");
          out.write("\t\t\t\t\t\t\t\t<td align=\"left\">Password</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td align=\"left\">: &nbsp;</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td align=\"left\">");
          if (_jspx_meth_html_005ftext_005f1(_jspx_th_html_005fform_005f0, _jspx_page_context))
            return true;
          out.write("\n");
          out.write("\t\t\t\t\t\t\t\t</td>\n");
          out.write("\t\t\t\t\t\t\t</tr>\n");
          out.write("\t\t\t\t\n");
          out.write("\t\t\t\t\t\t</table>\n");
          out.write("\t\t\t\t\t<br>\n");
          out.write("\t\t\t\t\t<input class = \"button\" type=\"button\" onclick=\"doBack()\" value=\"Kembali\" />\n");
          out.write("\t\t\t\t\t");
          if (_jspx_meth_html_005fsubmit_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
            return true;
          out.write("\n");
          out.write("\t\t\t\t</center>\n");
          out.write("\t\t\t<br>\n");
          out.write("\t\t\t");
          int evalDoAfterBody = _jspx_th_html_005fform_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_html_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005fform_0026_005faction.reuse(_jspx_th_html_005fform_005f0);
      _jspx_th_html_005fform_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005fform_005f0, _jsp_getInstanceManager(), _jspx_th_html_005fform_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005fhidden_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_html_005fhidden_005f0 = (org.apache.struts.taglib.html.HiddenTag) _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    boolean _jspx_th_html_005fhidden_005f0_reused = false;
    try {
      _jspx_th_html_005fhidden_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005fhidden_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
      // /jsp/user_form.jsp(34,1) name = property type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005fhidden_005f0.setProperty("id");
      int _jspx_eval_html_005fhidden_005f0 = _jspx_th_html_005fhidden_005f0.doStartTag();
      if (_jspx_th_html_005fhidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f0);
      _jspx_th_html_005fhidden_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005fhidden_005f0, _jsp_getInstanceManager(), _jspx_th_html_005fhidden_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005ftext_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:text
    org.apache.struts.taglib.html.TextTag _jspx_th_html_005ftext_005f0 = (org.apache.struts.taglib.html.TextTag) _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fstyleClass_005fsize_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.html.TextTag.class);
    boolean _jspx_th_html_005ftext_005f0_reused = false;
    try {
      _jspx_th_html_005ftext_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005ftext_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
      // /jsp/user_form.jsp(42,25) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005ftext_005f0.setName("UserForm");
      // /jsp/user_form.jsp(42,25) name = property type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005ftext_005f0.setProperty("username");
      // /jsp/user_form.jsp(42,25) name = size type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005ftext_005f0.setSize("32");
      // /jsp/user_form.jsp(42,25) name = styleClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005ftext_005f0.setStyleClass("textbox");
      int _jspx_eval_html_005ftext_005f0 = _jspx_th_html_005ftext_005f0.doStartTag();
      if (_jspx_th_html_005ftext_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fstyleClass_005fsize_005fproperty_005fname_005fnobody.reuse(_jspx_th_html_005ftext_005f0);
      _jspx_th_html_005ftext_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005ftext_005f0, _jsp_getInstanceManager(), _jspx_th_html_005ftext_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005ftext_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:text
    org.apache.struts.taglib.html.TextTag _jspx_th_html_005ftext_005f1 = (org.apache.struts.taglib.html.TextTag) _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fstyleClass_005fsize_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.html.TextTag.class);
    boolean _jspx_th_html_005ftext_005f1_reused = false;
    try {
      _jspx_th_html_005ftext_005f1.setPageContext(_jspx_page_context);
      _jspx_th_html_005ftext_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
      // /jsp/user_form.jsp(48,25) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005ftext_005f1.setName("UserForm");
      // /jsp/user_form.jsp(48,25) name = property type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005ftext_005f1.setProperty("password");
      // /jsp/user_form.jsp(48,25) name = size type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005ftext_005f1.setSize("32");
      // /jsp/user_form.jsp(48,25) name = styleClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005ftext_005f1.setStyleClass("textbox");
      int _jspx_eval_html_005ftext_005f1 = _jspx_th_html_005ftext_005f1.doStartTag();
      if (_jspx_th_html_005ftext_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fstyleClass_005fsize_005fproperty_005fname_005fnobody.reuse(_jspx_th_html_005ftext_005f1);
      _jspx_th_html_005ftext_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005ftext_005f1, _jsp_getInstanceManager(), _jspx_th_html_005ftext_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005fsubmit_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_html_005fsubmit_005f0 = (org.apache.struts.taglib.html.SubmitTag) _005fjspx_005ftagPool_005fhtml_005fsubmit_0026_005fvalue_005fstyleClass_005fonclick_005fnobody.get(org.apache.struts.taglib.html.SubmitTag.class);
    boolean _jspx_th_html_005fsubmit_005f0_reused = false;
    try {
      _jspx_th_html_005fsubmit_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005fsubmit_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
      // /jsp/user_form.jsp(55,5) name = styleClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005fsubmit_005f0.setStyleClass("button");
      // /jsp/user_form.jsp(55,5) name = onclick type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005fsubmit_005f0.setOnclick("return valid();");
      // /jsp/user_form.jsp(55,5) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005fsubmit_005f0.setValue("Simpan");
      int _jspx_eval_html_005fsubmit_005f0 = _jspx_th_html_005fsubmit_005f0.doStartTag();
      if (_jspx_th_html_005fsubmit_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005fsubmit_0026_005fvalue_005fstyleClass_005fonclick_005fnobody.reuse(_jspx_th_html_005fsubmit_005f0);
      _jspx_th_html_005fsubmit_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005fsubmit_005f0, _jsp_getInstanceManager(), _jspx_th_html_005fsubmit_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_html_005frewrite_005f5(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  html:rewrite
    org.apache.struts.taglib.html.RewriteTag _jspx_th_html_005frewrite_005f5 = (org.apache.struts.taglib.html.RewriteTag) _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.get(org.apache.struts.taglib.html.RewriteTag.class);
    boolean _jspx_th_html_005frewrite_005f5_reused = false;
    try {
      _jspx_th_html_005frewrite_005f5.setPageContext(_jspx_page_context);
      _jspx_th_html_005frewrite_005f5.setParent(null);
      // /jsp/user_form.jsp(70,33) name = page type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005frewrite_005f5.setPage("/user/list.do");
      int _jspx_eval_html_005frewrite_005f5 = _jspx_th_html_005frewrite_005f5.doStartTag();
      if (_jspx_th_html_005frewrite_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fhtml_005frewrite_0026_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f5);
      _jspx_th_html_005frewrite_005f5_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_html_005frewrite_005f5, _jsp_getInstanceManager(), _jspx_th_html_005frewrite_005f5_reused);
    }
    return false;
  }
}
