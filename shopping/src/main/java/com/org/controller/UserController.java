package com.org.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * Controller Class for User Object Modifications
 * @author glodeveloper
 *
 */
@Controller
public class UserController {
	
	private static Logger log = Logger.getLogger(UserController.class);
	
	/**
	 * users profile page redirection...
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String users(ModelMap model) {
		log.info("users profile page redirection...");
		model.addAttribute("method","view");
		model.addAttribute("users",new String[]{"1","2"});
		return "users";
	}
	
	
	
	@RequestMapping(value="/edituser/{emailID}",method=RequestMethod.GET)
	public String edituser(@PathVariable String emailID,ModelMap model) {
		log.debug("Request for modification of user "+emailID);
		model.addAttribute("method","edit");
		return "users";
	}
}
