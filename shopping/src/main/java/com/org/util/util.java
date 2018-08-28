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
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Common utility classes
 *
 * @author VINAY
 *
 */
public class util {

    private static Logger log = Logger.getLogger(util.class);
    private static final String key = util.getProperites("ssl.key");
    private static final String initvector = util.getProperites("ssl.initvector");

    /**
     * Encrypt the given String into 126 bit SSL Encryption
     *
     *  * @author vinay
     * @param req
     * @return encrypted string, null if exception raises
     */
    public static String encryptString(String req) { 
        return encrypt(key, initvector, req);
    }

    /**
     * Decrypt the given String into 126 bit SSL Encryption
     *
     *  * @author vinay
     * @param req
     * @return encrypted string, null if exception raises
     */
    public static String decyptString(String req) {
        return decrypt(key, initvector, req);

    }

    /**
     * encryption
     *
     * @param key
     * @param initVector
     * @param value
     *  * @author vinay
     * @return
     */
    private static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * *
     * decryption
     *
     * @param key
     * @param initVector
     * @param encrypted
     *  * @author vinay
     * @return
     */
    private static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Loads application properties for object
     *
     * @param key
     * @return value
     */
    public static String getProperites(String key) {
        try {
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
     *
     * @param image
     * @param filelocation
     * @return
     */
    public static boolean saveimageinfolder(CommonsMultipartFile image, String filelocation) {
        try {
            log.info("saving image in folder...");
            File file = new File(filelocation);
            if (file.exists()) {
                file.delete();
            }
            byte[] bytes = image.getBytes();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filelocation));
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            log.error("unable to save image in folder " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("starting..");
        
    }
}
