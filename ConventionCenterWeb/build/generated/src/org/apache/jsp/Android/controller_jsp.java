package org.apache.jsp.Android;

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
    
    ///////////////////////////////////// CUSTOMER REGISTRATION /////////////////////////////////////////////////////////////////
    
     if (key.equals("Customer_registration")) {
        String name = request.getParameter("name").toString().trim();
        String age = request.getParameter("age").toString().trim();
        String aadhaar = request.getParameter("aadhaar").toString().trim();
        String address = request.getParameter("address").toString().trim();
        String phone = request.getParameter("phone").toString().trim();
        String email = request.getParameter("email").toString().trim();
        String password = request.getParameter("password").toString().trim();



        String str = "INSERT INTO tb_customer(c_name,c_age,c_aadhaar,c_address,c_phone,c_email)values('" + name + "','" + age + "','" + aadhaar + "','" + address + "','"+phone+"','" + email + "')";

      String str1 = "INSERT INTO tb_login(reg_id,username,password,usertype,status) VALUES((SELECT MAX(c_id)FROM tb_customer),'" + email + "','" + password + "','customer','1')";

        System.out.println(str + "\n"+str1 );
        
        if (con.putData(str) > 0 && con.putData(str1) > 0) {

             out.println("success");

        } else {
            out.println("failed");
        }

    }
     
      /////////////////////////////////////////////////////// CUSTOMERS LIST /////////////////////////////////////////////////////////////////////////////////////////
  
  
  if (key.equals("admin_view_customers")) {

        String str = "SELECT * FROM tb_customer";
        System.out.println(str);

        String info = "", data = "", respo = "";
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                info += "\nCustomer Name -      " + v.get(1) + "\nAge                       -       " + v.get(2) +"\nAadhaar               -       " + v.get(3)+"\nAddress               -       " + v.get(4)+"\nPhone                  -       " + v.get(5)+"\n" + ":";
                data += v.get(0)+":";
                System.out.println(data + "\n" + info);
            }
            
            respo = data + "#" + info;  
            out.println(info);  
            System.out.println("hahaha "+respo);
        } else {
            out.println("failed");
            System.out.println("failed");
        }

    }
    //////////////////////////////////////////////// HOTEL REGISTRATION /////////////////////////////////////////////////////////
    
    if(key.equals("hotel_registration")){
        String name=request.getParameter("name").trim();
         String rating=request.getParameter("rating").trim();
        String address=request.getParameter("address").trim();
        String phone=request.getParameter("phone").trim();
        String email=request.getParameter("email").trim();
        String password=request.getParameter("password").trim();
        String lat=request.getParameter("lattitude").trim();
        String longi=request.getParameter("longitude").trim();
        
        String qry="insert into tb_hotel(h_name,h_rating,h_place,h_latti,h_longi,h_phone,h_email,h_status)values('"+name+"','"+rating+"','"+address+"','"+lat+"','"+longi+"','"+phone+"','"+email+"','0')";
        System.out.println(qry);
         String qry1 = "INSERT INTO tb_login(reg_id,username,password,usertype,status) VALUES((SELECT MAX(h_id)FROM tb_hotel),'" + email + "','" + password + "','hotel','0')";
         System.out.println(qry1);
           if (con.putData(qry) > 0 && con.putData(qry1)>0) {
                out.print("successful");
            }
         else {
            out.print("failed");
            }
    }
    
 /////////////////////////////////////////////////////// HOTEL REQUEST LIST ///////////////////////////////////////////////////////////////////////////////////////// 
  
  if (key.equals("admin_view_hotel_requests")) {

        String str = "SELECT * FROM tb_hotel where h_status='0'";
        System.out.println(str);

        String info = "", data = "", respo = "";
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                info += "\nHotel Name        -      " + v.get(1) + "\nRating                  -       " + v.get(2) +"\nPlace                   -       " + v.get(3)+"\nPhone                  -       " + v.get(6)+"\n" + ":";
                data += v.get(0)+":";
                System.out.println(data + "\n" + info);
            }
            
            respo = data + "&" + info;  
            out.println(respo);  
            System.out.println("hahaha "+respo);
        } else {
            out.println("failed");
            System.out.println("failed");
        }

    }
   /////////////////////////////////////////// ADMIN APPROVE HOTEL //////////////////////////////////////////////////////////////////////
    
     if (key.equals("admin_approve_hotel")) {
        String hid = request.getParameter("hid");
        String Action = request.getParameter("Action");

        if (Action.equals("Approve")) {

            String qry = "UPDATE `tb_hotel` SET `h_status`='1' WHERE `h_id`='" + hid + "'";
            System.out.println("qry"+qry);
 
            String qry1 = "UPDATE tb_login SET STATUS='1' WHERE usertype='hotel' AND reg_id='"+hid+"'";
           
                System.out.println(qry1);
                if (con.putData(qry) > 0 && con.putData(qry1) > 0) {

                    out.print("success");
                } else {
                    out.print("failed");
                }

            }

         else {
            String qry = "DELETE FROM `tb_hotel` WHERE `h_id`='" +hid +"'";
            System.out.println(qry);
              String qry1 = "DELETE FROM `tb_login` WHERE `reg_id`='" +hid +"' AND usertype='hotel'";
            System.out.println(qry1);
             if (con.putData(qry) > 0 && con.putData(qry1) > 0) {

                out.print("success");
            } else {
                out.print("failed");
            }
        }
     }
     
         //////////////////////////////////////////////// AGENCY REGISTRATION /////////////////////////////////////////////////////////
    
    if(key.equals("agency_registration")){
        String name=request.getParameter("name").trim();
         String lice=request.getParameter("lice").trim();
        String address=request.getParameter("address").trim();
        String phone=request.getParameter("phone").trim();
        String email=request.getParameter("email").trim();
        String password=request.getParameter("password").trim();
        String lat=request.getParameter("lattitude").trim();
        String longi=request.getParameter("longitude").trim();
        
        String qry="insert into tb_agency(a_name,a_lice,a_place,a_latti,a_longi,a_phone,a_email,a_status)values('"+name+"','"+lice+"','"+address+"','"+lat+"','"+longi+"','"+phone+"','"+email+"','0')";
        System.out.println(qry);
         String qry1 = "INSERT INTO tb_login(reg_id,username,password,usertype,status) VALUES((SELECT MAX(a_id)FROM tb_agency),'" + email + "','" + password + "','agency','0')";
         System.out.println(qry1);
           if (con.putData(qry) > 0 && con.putData(qry1)>0) {
                out.print("successful");
            }
         else {
            out.print("failed");
            }
    }
     /////////////////////////////////////////////////////// AGENCY REQUEST LIST ///////////////////////////////////////////////////////////////////////////////////////// 
  
  if (key.equals("admin_view_agency_requests")) {

        String str = "SELECT * FROM tb_agency where a_status='0'";
        System.out.println(str);

        String info = "", data = "", respo = "";
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                info += "\nHotel Name        -      " + v.get(1) + "\nLicence No               -       " + v.get(2) +"\nPlace                   -       " + v.get(3)+"\nPhone                  -       " + v.get(6)+"\n" + ":";
                data += v.get(0)+":";
                System.out.println(data + "\n" + info);
            }
            
            respo = data + "&" + info;  
            out.println(respo);  
            System.out.println("hahaha "+respo);
        } else {
            out.println("failed");
            System.out.println("failed");
        }

    }
   /////////////////////////////////////////// ADMIN APPROVE AGENCY //////////////////////////////////////////////////////////////////////
    
     if (key.equals("admin_approve_agency")) {
        String aid = request.getParameter("aid");
        String Action = request.getParameter("Action");

        if (Action.equals("Approve")) {

            String qry = "UPDATE `tb_agency` SET `a_status`='1' WHERE `a_id`='" + aid + "'";
            System.out.println("qry"+qry);
 
            String qry1 = "UPDATE tb_login SET STATUS='1' WHERE usertype='agency' AND reg_id='"+aid+"'";
           
                System.out.println(qry1);
                if (con.putData(qry) > 0 && con.putData(qry1) > 0) {

                    out.print("success");
                } else {
                    out.print("failed");
                }

            }

         else {
            String qry = "DELETE FROM `tb_agency` WHERE `a_id`='" +aid +"'";
            System.out.println(qry);
              String qry1 = "DELETE FROM `tb_login` WHERE `reg_id`='" +aid +"' AND usertype='agency'";
            System.out.println(qry1);
             if (con.putData(qry) > 0 && con.putData(qry1) > 0) {

                out.print("success");
            } else {
                out.print("failed");
            }
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
