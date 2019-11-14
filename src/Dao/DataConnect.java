/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import java.sql.*;
import java.util.*;
public class DataConnect {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/chat?useUnicode=true&characterEncoding=utf-8";
   
   static final String USER = "root";
   static final String PASS = "";
   Connection con = null; 
   Statement stmt = null;
    ResultSet rs=null;
     String sql=null;
    public Connection getConnection(){//连接       
       try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();   
            }catch(Exception e){
            //Handle errors for Class.forName
             e.printStackTrace();
            }
        return con;
    }
       public boolean signs(String id,String pass){
       boolean result= false;
       try{
       con=getConnection();
       stmt=con.createStatement();
       sql="select * from chat where id="+id;
       //System.out.println(sql);
       rs=stmt.executeQuery(sql);
        while(rs.next()){          
        String p=rs.getString("pass");
        String t=rs.getString("id");
        if(p.equals(pass)) result=true;
      }
   }catch(Exception e){       
   }
      return result;
   }
        public boolean logins(String id,String pass){
        boolean re=false;
        try{
            con=getConnection();
            stmt=con.createStatement();
            sql="INSERT INTO `chat` (`id`, `pass`) VALUES ('"+id+"', '"+pass+"')";           
            if(stmt.executeUpdate(sql)!=0) re=true;
        }catch(Exception e){       
        }
        return re;
      }
}
