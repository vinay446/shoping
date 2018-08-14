package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.util.util;

/***
 * Common controller class for url requests
 * 
 * @author vinay
 *
 */
@Controller
public class CommonController {

	private static Logger log = Logger.getLogger(CommonController.class);
	
	/**
	 * * Request from web.xml if 404 (page not found) Exception raises
	 * 
	 * @return to custom 404 page other than apache
	 */
	@RequestMapping(value = "/404")
	public String error404() {
		log.warn("User requested a resource which is not available");
		return "404";
	}

	/**
	 * * Request from web.xml if 500 (Internal Server Error) Exception raises
	 * 
	 * @return to custom 404 page other than apache
	 */
	@RequestMapping(value = "/500")
	public String error500() {
		log.fatal("Un handled internal server error occured..");
		return "500";
	}

	/***
	 * login page redirection for admin
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String adminlogin(HttpServletRequest request,HttpSession session,ModelMap model) {
		log.info("admin login page redirection..");
		String username = (String) session.getAttribute("username");
		String msg= request.getParameter("msg");
		String req = request.getParameter("req");
		if(msg!=null) {
			model.addAttribute("msg",msg);
			model.addAttribute("req",req);
		}
		if(username!=null) {
			log.info("Session is active redirection to main page");
			return "redirect:/dashboard";
		}
		util.getadminlogincookies(model, request);
		return "adminlogin";
	}
	
	
	
	/**
	 * dashboard page redirection
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String dashboard(ModelMap model) {
		log.info("dashboard page redirection..");
		return "dashboard";
	}
	
	
	
	
}