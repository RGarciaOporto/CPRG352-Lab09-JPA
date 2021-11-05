/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class UserDB {
    /*
    Need methods to:
        -get a single user
        -get all users
        -inserting a user
        -updating a user 
        -deleting a user
    */
    
    public ArrayList<User> getAll(){
        ArrayList<User> userList = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                User tempUser = new User(email, active, firstName, lastName, password, role);
                userList.add(tempUser);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    return userList;
    }
    
    public User getUser(String email){
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        User tempUser = new User();
        String sql = "SELECT * FROM user WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                tempUser = new User(email, active, firstName, lastName, password, role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    return tempUser;
    }
    
    public void addUser(User user){
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String email = user.getEmail();
        int status = user.statusToInt();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        int role = user.getRole();
        
        String sql = "INSERT INTO user (email, active, first_name, last_name,  password , role) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, status);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, password);
            ps.setInt(6, role);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void updateUser(User user){
    ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String email = user.getEmail();
        int status = user.statusToInt();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        int role = user.getRole();
        
        String sql = "UPDATE user SET active = ?, first_name = ?,  last_name = ?, role = ? WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(1, status);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setInt(4, role);
            ps.setString(5, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void deleteUser(User user){
     ConnectionPool cp = ConnectionPool.getInstance();
     Connection con = cp.getConnection();
     PreparedStatement ps = null;
     String email = user.getEmail();
     
     String sql = "DELETE FROM user WHERE email=?";
     
     try{
         ps = con.prepareStatement(sql);
         ps.setString(1, email);
         ps.executeUpdate();
     }  catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
     
}
