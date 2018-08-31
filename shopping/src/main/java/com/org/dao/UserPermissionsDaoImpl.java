/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.dao;

import com.org.model.UserPermissions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author glodeveloper
 */
@Repository("UserPermissionsDao")
public class UserPermissionsDaoImpl implements UserPermissionsDao {
    
    @Autowired
    protected SessionFactory factory;
    
    private Session getSession() {
        return factory.getCurrentSession();
    }
    
    @Override
    public UserPermissions getpermissionsforuser(int userID) {
        return (UserPermissions) getSession().get(UserPermissions.class, userID);
    }
    
    @Override
    public void deletepermissions(int userID) {        
        getSession().delete(getpermissionsforuser(userID));
    }
    
    @Override
    public void saveorupdatepermissions(UserPermissions userpermissions) {
        getSession().saveOrUpdate(userpermissions);
    }
    
}
