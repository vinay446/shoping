package com.org.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Loads the application properties into application
 * @author VINAY
 *
 */


public class ApplicationConfiguration {

	private static Logger log = Logger.getLogger(ApplicationConfiguration.class);
	
	/**
	 * Reads properties and values in file to properties object 
	 * @param file name or location of file
	 * @return Properties object or null if file not found
	 * @throws IOException
	 */
	public Properties getApplicationProperties(String file) throws IOException{
		InputStream input =null;
		Properties prop = new Properties();
		try {
			log.info("Fetching Properties from file..");
			input = getClass().getClassLoader().getResourceAsStream(file);
			if(input==null) {
				log.fatal("input null file not found ");
				return null;
			}
			prop.load(input);
			input.close();
		}catch(Exception e) {
			e.printStackTrace();
			log.error("Error in fetching properties from file..."+e.getMessage());
		}finally {
			if(input!=null) {
				input.close();
			}			
		}
		return prop;
	}

    public static void main(String[] args) throws IOException{
    	System.out.println("Starting...");
    	ApplicationConfiguration app = new ApplicationConfiguration();
    	System.out.println(app.getApplicationProperties("application.properties"));
    }
}
