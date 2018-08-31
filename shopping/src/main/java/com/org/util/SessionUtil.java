/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.util;

import com.org.model.UserPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This method is used to inject spring-mvc session dependencies directly by
 * spring controller and in controller methods we can get values directly by
 * using @scope("request") annotation and @Autowire annotation the controller in
 * this approach MUST be request scoped. The default is for Spring to create a
 * global singleton instance of the controller, and this would not work as a
 * singleton is shared by all requests Pros: Clean testable controller as in
 * approach two, with the added benefit of the session now only holds the
 * relevant session data. Cons: A new instance of the controller is created for
 * each request. This is fine if the controller is “small”, but if it is
 * expensive to create (ie the constructor is slow for some reason), scalability
 * would be a problem. Also, this approach is harder to understand because of
 * the request scoped controller.
 *
 * @author glodeveloper
 */
@Component
@Scope("session")
public class SessionUtil {

    private String username;

    private String emailID;

    private UserPermissions permissions;

    public void invalidate() {
        this.username = null;
        this.emailID = null;
        this.permissions = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public UserPermissions getPermissions() {
        return permissions;
    }

    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }
}
