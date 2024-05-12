/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

/**
 *
 * @author Admin
 */
import static java.lang.Character.UnicodeBlock.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connection {
    
     private ResultSet rs=null;
    private ResultSetMetaData rsm=null;
    private Statement st=null;
     private Connection con=null;
    private Vector rowdata=null;

    public connection(){
    
if(con==null)
{
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_conventioncenter","root","");
        st=con.createStatement();
    }
    catch(Exception e){
        System.out.println(e);
    }
}
rowdata=new Vector();
    }
    public int putData(String qry) 
    {
        try{
            return st.executeUpdate(qry);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public Vector getData(String qry){
        try{
            rs=st.executeQuery(qry);
            rsm=rs.getMetaData();
            int colCount=rsm.getColumnCount();
            while(rs.next()){
               Vector colVector=new Vector();
               for(int i=0;i<colCount;i++){
                   colVector.add(rs.getString(i+1));
                 }
                 rowdata.add(colVector);
             }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rowdata;
}
    
}
