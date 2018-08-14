package com.org.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.util.util;

/**
 * Common Controller Class for admin and user login
 * 
 * @author VINAY
 *
 */
@Controller
public class LoginController {

	private static Logger log = Logger.getLogger(LoginController.class);

	/**
	 * Admin login details validation
	 * 
	 * @param emailID
	 * @param password
	 * @param rememberme
	 * @param reqpage
	 * @param model
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public String adminlogin(@RequestParam("emailID") String emailID, @RequestParam("password") String password,
			@RequestParam(value = "rememberme", required = false) String rememberme,
			@RequestParam("reqpage") String reqpage, ModelMap model, HttpSession session,
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
				session.setAttribute("username", "sysadmin");
				session.setAttribute("emailID", emailID);
				if (rememberme != null)
					savecookies(response, emailID, password);
				return "dashboard";
			} else {
				log.warn("Sysadmin passsword verification failed..");
				model.addAttribute("message", "Access Denied Incorrect Password..");
				return "adminlogin";
			}
		}
        model.addAttribute("message","Not Supported yet");   
		return "adminlogin";
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

	/***
	 * Logout request
	 * @param msg
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
	public String adminlogout(@RequestParam("msg") String msg, @RequestParam("req") String req, HttpSession session,ModelMap model,HttpServletRequest request) {
		log.info("logout request");
		session.invalidate();
		model.addAttribute("msg",msg);
		model.addAttribute("req",req);
		util.getadminlogincookies(model, request);
		return "adminlogin";
	}
}
