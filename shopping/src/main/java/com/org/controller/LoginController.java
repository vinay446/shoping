package com.org.controller;

import com.org.model.User;
import com.org.model.UserPermissions;
import com.org.service.UserPermissionsService;
import com.org.service.UserService;
import com.org.util.DateUtil;
import com.org.util.SessionUtil;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.util.util;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 * Common Controller Class for admin and user login
 *
 * @author VINAY
 *
 */
@Controller
@Scope("request")//This annotation is used to inject session util class dependecies by spring controller
public class LoginController {

    private static Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    UserService service;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SessionUtil sessionutil;

    @Autowired
    private UserPermissionsService permissionsservice;

    /**
     * Admin login details validation
     *
     * @param emailID
     * @param password
     * @param rememberme
     * @param reqpage
     * @param model
     * @param response
     * @return
     */
    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public String adminlogin(@RequestParam("emailID") String emailID, @RequestParam("password") String password,
            @RequestParam(value = "rememberme", required = false) String rememberme,
            @RequestParam("reqpage") String reqpage, ModelMap model,
            HttpServletResponse response) {
        log.debug("admin login validation..");
        String sysadminusername = util.getProperites("sysadmin.username");
        String sysadminpassword = util.getProperites("sysadmin.password");
        if (sysadminusername == null | sysadminpassword == null) {
            log.fatal("Error in getting values from properties file ");
            model.addAttribute("message", "Oops, Something went wrong, Try Again after sometime");
            return "adminlogin";
        }
        if (emailID.equals(sysadminusername)) {
            if (password.equals(sysadminpassword)) {
                log.debug("Loggin in as sysadmin....");
                setsessionutility(emailID, "sysadmin", 0);
                if (rememberme != null) {
                    savecookies(response, emailID, password);
                }
                model.addAttribute("sessionutil", sessionutil);
                return "dashboard";
            } else {
                log.warn("Sysadmin passsword verification failed..");
                model.addAttribute("message", "Access Denied Incorrect Password..");
                return "adminlogin";
            }
        }
        User user = service.findById(emailID);
        if (user == null) {
            model.addAttribute("message", "Invalid UserID");
            return "adminlogin";
        }
        if (user.getIsactive().equalsIgnoreCase("no")) {
            model.addAttribute("message", "User ID is not activated..");
            return "adminlogin";
        }
        if (!user.getRoleID().equalsIgnoreCase("admin")) {
            model.addAttribute("message", "you are not an admin..");
            return "adminlogin";
        }
        if (user.getPassword().equals(password)) {
            setsessionutility(emailID, user.getFirstname(), user.getId());
            if (rememberme != null) {
                savecookies(response, emailID, password);
            }
            user.setLastlogin(DateUtil.getEpoch());
            user.setLastloginip(request.getRemoteAddr());
            return "dashboard";
        } else {
            model.addAttribute("message", "Access Denied, Incorrect Password");
            return "adminlogin";
        }
    }

    /**
     * get permissions for user and set sessionsutil class
     *
     * @param emailID
     * @param username
     * @param userID
     */
    private void setsessionutility(String emailID, String username, int userID) {
        sessionutil.setEmailID(emailID);
        sessionutil.setUsername(username);
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("emailID", emailID);
        if (userID == 0) {
            return;
        }
        UserPermissions permissions = permissionsservice.getpermissionsforuser(userID);
        sessionutil.setPermissions(permissions);
    }

    /**
     * saves username and password in cookies
     *
     * @param response
     * @param emailID
     * @param password
     */
    private void savecookies(HttpServletResponse response, String emailID, String password) {
        Cookie c1 = new Cookie("emailID", emailID);
        Cookie c2 = new Cookie("password", password);
        c1.setMaxAge(60 * 60 * 24 * 365 * 10);
        c2.setMaxAge(60 * 60 * 24 * 365 * 10);
        response.addCookie(c1);
        response.addCookie(c2);
        log.debug("username and passsword saved into cookies");
    }

    /**
     * *
     * Logout request
     *
     * @param msg
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
    public String adminlogout(@RequestParam("msg") String msg, @RequestParam("req") String req, ModelMap model) {
        log.info("logout request");
        request.getSession().invalidate();
        sessionutil.invalidate();
        model.addAttribute("msg", msg);
        model.addAttribute("req", req);
        util.getadminlogincookies(model, request);
        return "adminlogin";
    }
}
