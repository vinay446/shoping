package com.org.util;

import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

import com.org.configuration.ApplicationConfiguration;

/**
 * Common utility classes
 * 
 * @author VINAY
 *
 */

public class util {

	private static Logger log = Logger.getLogger(util.class);

	/**
	 * Loads application properties for object
	 * @param key
	 * @return value
	 */
	public static String getProperites(String key) {
		try {
			log.debug("Loading application properties ...");
			ApplicationConfiguration conf = new ApplicationConfiguration();
			Properties prop = conf.getApplicationProperties("application.properties");
	        return prop.getProperty(key);
		} catch (Exception e) {
			log.fatal("Error in loading application properties..." + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Retires cookies stored for login page and adds in modelmap
	 * @param model
	 * @param request
	 */
	public static void getadminlogincookies(ModelMap model,HttpServletRequest request) {
		log.debug("Fetching cookies....");
		Cookie[] cookies = request.getCookies();
		   if(cookies != null){
			   for(Cookie c: cookies){
				   String 	cookiename = c.getName();
				   if(cookiename.equals("emailID")){
					   model.addAttribute("emailID", c.getValue());
				   }else if(cookiename.equals("password")) {
					   model.addAttribute("password",c.getValue());
				   }
			   }
		   }
	}
	
	public static void main(String[] args) {
		System.out.println("starting..");
		System.out.println(getProperites("ssl.key"));
	}
}
