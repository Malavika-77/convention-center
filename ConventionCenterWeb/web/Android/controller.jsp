<%-- 
    Document   : controller
    Created on : Dec 17, 2019, 2:14:51 PM
    Author     : user13
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Vector"%>
<%@page import="DBConnection.connection"%>
<%
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
                info += "\nHotel Name        -      " + v.get(1) + "\nLicence No           -       " + v.get(2) +"\nPlace                   -       " + v.get(3)+"\nPhone                  -       " + v.get(6)+"\n" + ":";
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
     
   //////////////////////////// HOTEL ADD PACKAGE ////////////////////////////////////////////////////////////////////////////////
     
     if(key.equals("hotel_add_package")){
         
         String name=request.getParameter("pname");
          String pname=name.toLowerCase();
         
         String h_id=request.getParameter("hid");
         String description=request.getParameter("pdesc");
         String prate=request.getParameter("prate");
      
         
           String qry="SELECT COUNT(*) AS COUNT FROM tb_hotel_package WHERE p_name='"+pname+"' and h_id='"+h_id+"'";
         System.out.println(qry); 
         Iterator it=con.getData(qry).iterator();
         if (it.hasNext()) {
                Vector v = (Vector) it.next();
                String count=v.get(0)+""; 
                int COUNT=Integer.valueOf(count);
                System.out.println(COUNT);
                if(COUNT>0){   
                    out.print("failed");
                }else{
                     String qry1="insert into tb_hotel_package(h_id,p_name,p_desc,p_rate,p_status)values('"+h_id+"','"+pname+"','"+description+"','"+prate+"','1')";
                    System.out.println(qry1);
                     if (con.putData(qry1) > 0) {
                           out.print("success");
                        }else{
                         out.print("failed");
                         } 
                     }
            }  
     }  
     
       /////////////////////////////////////////////////////// HOTEL PACKAGE LIST /////////////////////////////////////////////////////////////////////////////////////////
  
  
  if (key.equals("hotel_view_packages")) {
      
      String h_id=request.getParameter("hid");
        String str = "SELECT * FROM tb_hotel_package where h_id='"+h_id+"' and p_status='1'";
        System.out.println(str);

        String info = "", data = "", respo = "";
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                info += "\nPackage Name -      " + v.get(2) + "\nDescription -  " + v.get(3) +"\nRate               -       " + v.get(4)+"\n" + ":";
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
    //////////////////////////// CENTER ADD FACILITY ////////////////////////////////////////////////////////////////////////////////
     
     if(key.equals("center_add_facility")){
         
         String name=request.getParameter("fname");
          String pname=name.toLowerCase();
         
         String description=request.getParameter("fdesc");
         String prate=request.getParameter("frate");
      
         
           String qry="SELECT COUNT(*) AS COUNT FROM tb_center_facility WHERE fac_name='"+pname+"'";
         System.out.println(qry); 
         Iterator it=con.getData(qry).iterator();
         if (it.hasNext()) {
                Vector v = (Vector) it.next();
                String count=v.get(0)+""; 
                int COUNT=Integer.valueOf(count);
                System.out.println(COUNT);
                if(COUNT>0){   
                    out.print("failed");
                }else{
                     String qry1="insert into tb_center_facility(fac_name,fac_desc,fac_rate,fac_status)values('"+pname+"','"+description+"','"+prate+"','1')";
                    System.out.println(qry1);
                     if (con.putData(qry1) > 0) {
                           out.print("success");
                        }else{
                         out.print("failed");
                         } 
                     }
            }  
     }  
     
    /////////////////////////////////////// AGENCY ADD VEHICLE ////////////////////////////////////////////////////////////////////////////////
     
     if(key.equals("agency_add_vehicle")){
         
          String a_id=request.getParameter("aid");
         String name=request.getParameter("vname");
          String vname=name.toLowerCase();
         
         String seats=request.getParameter("seats");
         String vrate=request.getParameter("vrate");
      
         
           String qry="SELECT COUNT(*) AS COUNT FROM tb_agency_vehicle WHERE vehicle='"+vname+"' and v_status='1'";
         System.out.println(qry); 
         Iterator it=con.getData(qry).iterator();
         if (it.hasNext()) {
                Vector v = (Vector) it.next();
                String count=v.get(0)+""; 
                int COUNT=Integer.valueOf(count);
                System.out.println(COUNT);
                if(COUNT>0){   
                    out.print("failed");
                }else{
                     String qry1="insert into tb_agency_vehicle(a_id,vehicle,seats,rate,v_status)values('"+a_id+"','"+vname+"','"+seats+"','"+vrate+"','1')";
                    System.out.println(qry1);
                     if (con.putData(qry1) > 0) {
                           out.print("success");
                        }else{
                         out.print("failed");
                         } 
                     }
            }  
     }  
     
    /////////////////////////////////////////////////////// AGENCY VEHICLE LIST /////////////////////////////////////////////////////////////////////////////////////////
  
  if (key.equals("agency_view_vehicles")) {
      
      String a_id=request.getParameter("aid");
        String str = "SELECT * FROM tb_agency_vehicle where a_id='"+a_id+"' and v_status='1'";
        System.out.println(str);

        String info = "", data = "", respo = "";
        Iterator itr = con.getData(str).iterator();

        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                info += "\nVehicle         -  " + v.get(2) + "\nSeats            -  " + v.get(3) +"\nRate per km -  " + v.get(4)+"\n" + ":";
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
   /////////////////////////////////////// CUSTOMER VIEW MAP ///////////////////////////////////////////////////////////
   
     if(key.equals("User_view_map")){
         
             String info = "", lat = "", longi = "", data = "",id="";
      String qry = "SELECT *FROM tb_agency";
       System.out.println("qry=" + qry);
        Iterator it = con.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                lat += v.get(4) + "#";
                longi += v.get(5) + "#";
                data += v.get(1)+"#";
                id += v.get(0)+"#";
            }
            info = lat + "&" + longi + "&" + data+ "&" + id;
            System.out.println(info);
            out.print(info);
        } else {
            System.out.println("else id=" + info);
            out.print("failed");
        }
    }
    %>