/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.ArrayList;
import models.User;

/**
 *
 * @author 851649
 */
public class UserService {
    /*
    need five methods:
        -get a user
        -get all users
        -insert a new user
        -update a user
        -delete a user
    */
    
    public ArrayList<User> getAll(){
    UserDB udb = new UserDB();
    ArrayList<User> userList = udb.getAll();
    return userList;
    }
    
    public User getUser(String email){
    UserDB udb = new UserDB();
    User tempUser = udb.getUser(email);
    return tempUser;
    }
    
    public void addUser(User user){
    UserDB udb = new UserDB();
    udb.addUser(user);
    }
    
    public void updateUser(User user){
    UserDB udb = new UserDB();
    udb.updateUser(user);
    }
    
    public void deleteUser(User user){
    UserDB udb = new UserDB();
    udb.deleteUser(user);
    }
}
