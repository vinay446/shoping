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

    @Column(name = "view")
    private int view;

    @Column(name = "create")
    private int create;

    @Column(name = "update")
    private int update;

    @Column(name = "delete")
    private int delete;

    public void setallpermissions(boolean flag) {
        if (flag) {
            this.view = 1;
            this.create = 1;
            this.update = 1;
            this.delete = 1;
            return;
        }
        this.view = 0;
        this.create = 0;
        this.update = 0;
        this.delete = 0;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
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
