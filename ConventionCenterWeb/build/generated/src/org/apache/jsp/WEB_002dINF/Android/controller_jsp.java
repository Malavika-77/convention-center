package org.apache.jsp.WEB_002dINF.Android;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import java.util.Vector;
import DBConnection.connection;

public final class controller_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    connection con=new connection();
    
    String key=request.getParameter("key").trim();
    System.out.println("key = "+key);
    
    //////////////////////////////////////////////// LOGIN ////////////////////////////////////////////////////////////////
    
    if(key.equals("login")){
        
        String info="";
      String email=request.getParameter("email").trim();
      String pass=request.getParameter("password").trim();
      
      String qry="SELECT *FROM tb_login WHERE username='"+email+"' AND password='"+pass+"' AND status='1'";
      System.out.println("qry= "+qry);
      
      Iterator it=con.getData(qry).iterator();
      if(it.hasNext()){
          Vector v=(Vector) it.next();
           info = v.get(1) + ":" + v.get(4);
           out.print(info);
      }else{
          System.out.println("else id=" + info);
          out.print("failed");
      }
       
    }
    
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
