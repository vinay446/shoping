/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.service;

import com.org.dao.UserPermissionsDao;
import com.org.model.UserPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author glodeveloper
 */
@Service("UserPermissionService")
@Transactional
public class UserPermissionsServiceImpl implements UserPermissionsService {
    
    @Autowired
    UserPermissionsDao userpermissionsdao;
    
    @Override
    public void saveorupdatepermissions(UserPermissions userpermissions) {
        userpermissionsdao.saveorupdatepermissions(userpermissions);
    }
    
    @Override
    public UserPermissions getpermissionsforuser(int userID) {
        return userpermissionsdao.getpermissionsforuser(userID);
    }
    
    @Override
    public void deletepermissions(int userID) {
        userpermissionsdao.deletepermissions(userID);
    }
    
}
