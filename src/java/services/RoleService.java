/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.ArrayList;
import models.Role;

/**
 *
 * @author 851649
 */
public class RoleService {
    //need one method: getAll()
    public ArrayList<Role> getAll(){
    RoleDB rdb = new RoleDB();
    ArrayList<Role> roleList = rdb.getAll();
    return roleList;
    }
}
