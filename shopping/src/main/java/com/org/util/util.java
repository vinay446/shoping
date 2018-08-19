package com.org.util;

import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

import com.org.configuration.ApplicationConfiguration;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
     *
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
     *
     * @param model
     * @param request
     */
    public static void getadminlogincookies(ModelMap model, HttpServletRequest request) {
        log.debug("Fetching cookies....");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String cookiename = c.getName();
                if (cookiename.equals("emailID")) {
                    model.addAttribute("emailID", c.getValue());
                } else if (cookiename.equals("password")) {
                    model.addAttribute("password", c.getValue());
                }
            }
        }
    }
    
    /**
     * saves images to folder in server
     * @param image
     * @param filelocation
     * @return 
     */
    public static boolean saveimageinfolder(CommonsMultipartFile image,String filelocation){
        try{
            log.info("saving image in folder...");
            File file = new File(filelocation);
            if(file.exists()){
                file.delete();
            }
            byte [] bytes = image.getBytes();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filelocation));
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        }catch(IOException e){
            log.error("unable to save image in folder "+e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("starting..");
        System.out.println(getProperites("ssl.key"));
    }
}
