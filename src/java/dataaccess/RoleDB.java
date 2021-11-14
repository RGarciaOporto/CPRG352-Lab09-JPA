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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.Role;
import models.User;

/**
 *
 * @author 851649
 */
public class RoleDB {
    /*
     only need the get all method
    */
    
    public List<Role> getAll(){
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
    
    try{
        List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
        return roles;
    } finally{
        em.close();
    }
    }
}
