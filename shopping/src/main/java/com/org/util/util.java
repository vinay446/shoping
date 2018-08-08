package com.org.util;

import java.util.Properties;

import org.apache.log4j.Logger;

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
	
	
	public static void main(String[] args) {
		System.out.println("starting..");
		System.out.println(getProperites("ssl.key"));
	}
}
