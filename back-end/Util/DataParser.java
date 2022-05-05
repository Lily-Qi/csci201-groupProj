package Util;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataParser {
 public static User getUser(int ID) throws SQLException {
  User usr = new User(ID);
     String db = "jdbc:mysql://localhost:3306/finalproject";
  String user = Constant.DBUserName;
  String pwd = Constant.DBUserName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pwd);
            String sql = "SELECT * FROM users u WHERE u.userID ="+ID+";";
            String sql2= "SELECT * FROM Projects p, users_has_groups ug WHERE p.groupID = ug.groups_groupID AND ug.users_userID ="+ID+";";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
         usr.setUserName(rs.getString("name"));
         usr.setPassword(rs.getString("password"));
         usr.setUserEmail(rs.getString("email"));
         rs = s.executeQuery(sql2);
         ArrayList<String> p = new ArrayList<String>();
         ArrayList<Integer> pID = new ArrayList<Integer>();
         while(rs.next()) {
          pID.add(rs.getInt("groupID"));
          p.add(rs.getString("title"));
         }
         usr.setProjects(p);
         usr.setProjId(pID);
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        return usr;
 }
 
 public static ArrayList<User> getAllUser() throws SQLException{
  ArrayList<User> alluser = new ArrayList<User>();
     String db = "jdbc:mysql://localhost:3306/finalproject";
  String user = Constant.DBUserName;
  String pwd = Constant.DBUserName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pwd);
            String sql = "SELECT * FROM users u;";
            ArrayList<Integer> userIDs = new ArrayList<Integer>();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()) {
             User usr = new User(rs.getInt("userID"));
             usr.setUserName(rs.getString("name"));
          usr.setPassword(rs.getString("password"));
          usr.setUserEmail(rs.getString("email"));
          alluser.add(usr);
             
            }
            for(User u :alluser) {
             String sql2= "SELECT * FROM Projects p, users_has_groups ug WHERE p.groupID = ug.groups_groupID AND ug.users_userID ="+u.getId()+";";
             ArrayList<String> p = new ArrayList<String>();
             ArrayList<Integer> pID = new ArrayList<Integer>();
             while(rs.next()) {
              pID.add(rs.getInt("groupID"));
              p.add(rs.getString("title"));
             }
             u.setProjects(p);
             u.setProjId(pID);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        return alluser;
  
 }
 
 public static Project getProject(int ID) throws SQLException {
  Project p = new Project();
     String db = "jdbc:mysql://localhost:3306/finalproject";
  String user = Constant.DBUserName;
  String pwd = Constant.DBUserName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pwd);
            String sql = "SELECT * FROM Projects p WHERE p.groupID ="+ID+";";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            p.setId(ID);
         p.setTitle(rs.getString("title"));
         p.setDescription(rs.getString("description"));
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        return p;
 }
 
}