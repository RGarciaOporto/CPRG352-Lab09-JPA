/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
    
    public List<User>getAll(){
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    
    try{
        List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
        return users;
    } finally{
        em.close();
    }
    }
    
    public User getUser(String email){
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    try{
        User user = em.find(User.class, email);
        return user;
    } finally{
        em.close();
    }
    }
    
    public void addUser(User user){
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();
    try{
        trans.begin();
        em.persist(user);
        trans.commit();
    }
    finally{
        em.close();
    }
    }
    
    public void updateUser(User user){
     EntityManager em = DBUtil.getEmFactory().createEntityManager();
     EntityTransaction trans = em.getTransaction();
    try{
        trans.begin();
        em.merge(user);
        trans.commit();
    }catch(Exception ex){
        trans.rollback();
    }
    finally{
        em.close();
    }
    }
    
    public void deleteUser(User user){
     EntityManager em = DBUtil.getEmFactory().createEntityManager();  
     EntityTransaction trans = em.getTransaction();
    try{
        trans.begin();
        em.remove(em.merge(user));
        trans.commit();
    }catch(Exception ex){
        trans.rollback();
    }
    finally{
        em.close();
    }
    }
     
}
