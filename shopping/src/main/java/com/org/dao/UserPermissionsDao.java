/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.dao;

import com.org.model.UserPermissions;

/**
 *
 * @author glodeveloper
 */
public interface UserPermissionsDao {

    /**
     * adds or updates user level permission for userID
     *
     * @param userpermissions
     */
    public void saveorupdatepermissions(UserPermissions userpermissions);



    /**
     * get UserPermissions Object for user
     *
     * @param userID
     * @return
     */
    public UserPermissions getpermissionsforuser(int userID);
    
    /**
     * deletes permissions
     * @param userID 
     */
    public void deletepermissions(int userID);

}
