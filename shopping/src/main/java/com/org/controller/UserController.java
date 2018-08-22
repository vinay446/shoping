package com.org.controller;

import com.org.model.User;
import com.org.service.UserService;
import com.org.util.DateUtil;
import com.org.util.util;
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
    public String edituser(@PathVariable int id, ModelMap model) {
        log.debug("Request for modification of user " + id);
        model.addAttribute("method", "edit");
        List<Integer> disabled = getpermissions(id, "disabledfields");
        if (id == 0) {//sysadmin
            log.info("loading sysadmin profile information...");
            String sysadminemailid = util.getProperites("sysadmin.username");
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
            log.info("loading " + id + " information...");

        }
        model.addAttribute("disabledfield", disabled);
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
        model.addAttribute("userid", "temp");
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
            model.addAttribute("message", "Error, EmailID already exists");
            return "users";
        }
        String imageid = image == null ? "sysadmin.png" : image.getOriginalFilename();
        System.out.println("image name " + imageid);
        int expiredays = 365;
        // try {
        expiredays = Integer.parseInt(util.getProperites("userexpiredays"));
        System.out.println("expired days " + expiredays);
        // } catch (NumberFormatException e) {
        //   e.printStackTrace();
        // log.error("Number format exception raised in settting exipre days taking default value 365 days");
        // }
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
        return "users";
    }

    /**
     * *
     * gets all permissions for user
     *
     * @param userid
     * @param permission
     * @return
     */
    private List<Integer> getpermissions(int userid, String permission) {
        //0 false 1 true
        List<Integer> permissions = new ArrayList<Integer>();
        if (userid == 0) {//sysadmin
            //disabled (uneditable) fields for sysadmin user
            if (permission.equals("disabledfields")) {
                for (int i = 0; i < 9; i++) {
                    //all fields editable permission disabled
                    permissions.add(0);
                }
            } //user modification options for sysadmin user
            else if (permission.equals("usermodifications")) {
                permissions.add(1);//edit
                permissions.add(1);//new
                permissions.add(1);//delete
            }
        }
        return permissions;
    }
}
