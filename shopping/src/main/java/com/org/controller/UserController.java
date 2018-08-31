package com.org.controller;

import com.org.model.User;
import com.org.service.UserService;
import com.org.util.DateUtil;
import com.org.util.SendMail;
import com.org.util.util;
import java.net.URLEncoder;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * *
 * Controller Class for User Object Modifications
 *
 * @author glodeveloper
 *
 */
@Controller
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);
    private static final String sysadminemailid = util.getProperites("sysadmin.username");
    private static final String profileexpire = util.getProperites("profileexpiredays");
    private static final String serverimageslocation = util.getProperites("serverimageslocation");

    @Autowired
    UserService service;

    /**
     * users profile page redirection...
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(ModelMap model) {
        log.info("users profile page redirection...");
        model.addAttribute("method", "view");
        List<User> users = service.findallUsers();
        model.addAttribute("users", users);
        //model.addAttribute("Permissions", permisssions);
        return "users";
    }

    /**
     * user profile edit page redirection
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edituser/{id}", method = RequestMethod.GET)
    public String edituser(@PathVariable String emailID, ModelMap model) {
        log.debug("Request for modification of user " + emailID);
        model.addAttribute("method", "edit");
        if (emailID.equals("0")) {//sysadmin
            log.info("loading sysadmin profile information...");
            User user = new User();
            user.setId(0);
            user.setEmailID(sysadminemailid);
            user.setAddress("address");
            user.setPhone("0");
            user.setFirstname("sysadmin");
            user.setLastname("admin");
            user.setRoleID("admin");
            user.setIsactive("yes");
            user.setImageid("sysadmin.png");
            model.addAttribute("user", user);
        } else {
            log.info("loading " + emailID + " information...");
            User user = service.findById(emailID);
            model.addAttribute("user", user);
        }
//        model.addAttribute("disabledfield", disabled);
        return "users";
    }

    /**
     * Edit User Profile
     *
     * @param model
     * @param id
     * @param image
     * @param userID
     * @param emailID
     * @param phone
     * @param firstname
     * @param lastname
     * @param address
     * @param roleID
     * @param isactive
     * @return
     */
    @RequestMapping(value = "/editprofile/{id}", method = RequestMethod.POST)
    public String editprofile(ModelMap model, @PathVariable int id, @RequestParam("image") CommonsMultipartFile image,
            @RequestParam("userID") int userID, @RequestParam("emailID") String emailID, @RequestParam("phone") String phone,
            @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("address") String address,
            @RequestParam("roleID") String roleID, @RequestParam("isactive") String isactive) {
        log.info("EDITING PROFILE " + id);
        model.addAttribute("method", "view");
        String message = null;
        if (id == 0) {
            //message = image == null ? "Nothing to update" : util.saveimageinfolder(image, "C:\\xampp\\htdocs\\images\\sysadmin.png") ? "Successfully updated" : "unable to update...";
            message = "ERROR you cannot change sysadmin values";
        }
        model.addAttribute("message", message);
        return "users";
    }

    /**
     * Create user form page redirection
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createuserpage(ModelMap model) {
        log.info("Create new user page redirection...");
        model.addAttribute("method", "create");
        model.addAttribute("userid", "auto generate");
        return "users";
    }

    /**
     * Create new User
     *
     * @param model
     * @param image
     * @param password
     * @param emailID
     * @param phone
     * @param firstname
     * @param lastname
     * @param address
     * @param roleID
     * @param isactive
     * @return
     */
    @RequestMapping(value = "/createprofile", method = RequestMethod.POST)
    public String createprofile(ModelMap model, @RequestParam("image") CommonsMultipartFile image,
            @RequestParam("password") String password, @RequestParam("emailID") String emailID, @RequestParam("phone") String phone,
            @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("address") String address,
            @RequestParam("roleID") String roleID, @RequestParam("isactive") String isactive) {
        log.info("Creating new user profile...");
        model.addAttribute("method", "view");
        if (!service.isUseremailIDUnique(emailID)) {
            log.error("EmailID already Exists");
            model.addAttribute("type", "error");
            model.addAttribute("message", "Unable to create User. EmailID already exists");
            return "users";
        }
        if (emailID.equalsIgnoreCase(sysadminemailid)) {
            log.error("Incorrect emailID...");
            model.addAttribute("type", "error");
            model.addAttribute("message", "Unable to create user. Incorrect emailID given");
            return "users";
        }
        String imageid = image.isEmpty() ? "sysadmin.png" : image.getOriginalFilename();
        int expiredays = 90;
        try {
            expiredays = Integer.parseInt(profileexpire);
        } catch (java.lang.NumberFormatException e) {
            e.printStackTrace();
            model.addAttribute("type", "info");
            model.addAttribute("message", "Application properties file is not configured properly. using default values, see log file for more...");
            log.fatal("Application properties file is not configured properly. using default value..." + e.getMessage());
        }
        User user = new User();
        user.setAddress(address);
        user.setCreationtime(DateUtil.getEpoch());
        user.setExpiretime(DateUtil.getmidnightepoch(DateUtil.getEpoch()) + (86400 * expiredays));
        user.setEmailID(emailID);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setImageid(imageid);
        user.setIsactive(isactive);
        user.setLastlogin(0);
        user.setLastloginip("0");
        user.setPhone(phone);
        user.setRoleID(roleID);
        user.setPassword(password);
        service.SaveUser(user);
        model.addAttribute("type", "success");
        model.addAttribute("users", service.findallUsers());
        model.addAttribute("message", "Profile " + firstname + " created successfully..");
        String verifycode = util.encryptString(emailID.trim());
        String link = "localhost:8084/shopping/activateaccount?emailID=" + emailID + "&code=" + URLEncoder.encode(verifycode);
        SendMail se = new SendMail(emailID, "welocme@glovision.co", "WELCOME", firstname, link);
        se.start();
        if (!imageid.equals("sysadmin.png")) {
            util.saveimageinfolder(image, serverimageslocation + image.getOriginalFilename());
        }
        return "users";
    }

    /**
     * Activate User Account by validating verification code sent to email link
     *
     * @param model
     * @param emailID
     * @param enc encrypted verification code
     * @return
     */
    @RequestMapping(value = "/activateaccount", method = RequestMethod.GET)
    public String activateaccount(ModelMap model, @RequestParam("emailID") String emailID, @RequestParam("code") String enc) {
        log.info("activateaccountclicked " + emailID);
        String verifycode = util.decyptString(enc);
        User user = service.findById(emailID);
        if (user == null) {
            log.info("Invalid request");
            model.addAttribute("message", "Invalid request");
            return "activateaccount";
        }
        if (!verifycode.equals(emailID)) {
            log.info("Invalid link Please click on correct link..");
            model.addAttribute("message", "Invalid link Please click on correct link..");
            return "activateaccount";
        }
        if (user.getIsactive().equalsIgnoreCase("yes")) {
            log.info("account alreay activated");
            model.addAttribute("message", "Link is invalid account is already activated..");
            return "activateaccount";
        }
        user.setIsactive("yes");
        service.updateUser(user);
        log.info("account successfully activated..");
        model.addAttribute("message", "Account Succesfully activated you can login to your account");
        return "activateaccount";
    }

  
    /**
     * Delete user with respective ID
     * @param model
     * @param emailID
     * @return 
     */
    @RequestMapping(value = "/delete/{emailID}", method = RequestMethod.GET)
    public String deleteuser(ModelMap model, @PathVariable String emailID) {
        log.info("User requested delete user...");
        model.addAttribute("method", "view");
        User user = service.findById(emailID);
        model.addAttribute("type", "error");
        if (user == null) {
            log.error("Unable to find requested  emailID..");
            model.addAttribute("message", "Oops, Something went wrong. Unable to find User ");
            return "users";
        }
        log.warn("deleting userID... " + emailID);
        service.deleteUser(user.getEmailID());
        model.addAttribute("message", "User deleted successfully..");
        return "users";
    }
}
