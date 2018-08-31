/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Permissions for Specific User 0 means does not have permission 1 means have
 * permission
 *
 * @author glodeveloper
 */
@Entity
@Table(name = "userpermissions")
public class UserPermissions implements Serializable {

    @Id
    @Column(name = "userID")
    private int userID;

    @Column(name = "create")
    private int create;

    @Column(name = "update")
    private int update;

    @Column(name = "delete")
    private int delete;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCreate() {
        return create;
    }

    public void setCreate(int create) {
        this.create = create;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

}
