/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author VINAY
 */
public class Mailer {

    private static final String user = util.getProperites("mailerusername");
    private static final String pass = util.getProperites("mailerpassword");
    private static final String mailsmtpsocketFactoryfallback = util.getProperites("mail.smtp.socketFactory.fallback");
    private static final String mailsmtphost = util.getProperites("mail.smtp.host");
    private static final String mailsmtpsocketFactoryport = util.getProperites("mail.smtp.socketFactory.port");
    private static final String mailsmtpport = util.getProperites("mail.smtp.port");
    private static final String mailsmtpstarttlsenable = util.getProperites("mail.smtp.starttls.enable");
    private static final String mailsmtpauth = util.getProperites("mail.smtp.auth");
    private static final String mailsmtpssltrust = util.getProperites("mail.smtp.ssl.trust");

    public static String send(String to, String from, String sub, String body) {
        String mailStatus = "fail";
        Properties props = new Properties();
        props.put("mail.smtp.socketFactory.fallback", mailsmtpsocketFactoryfallback);
        props.put("mail.smtp.host", mailsmtphost);
        props.put("mail.smtp.socketFactory.port", mailsmtpsocketFactoryport);
        props.put("mail.smtp.port", mailsmtpport);
        props.put("mail.smtp.starttls.enable", mailsmtpstarttlsenable);
        props.put("mail.smtp.auth", mailsmtpauth);
        props.put("mail.smtp.ssl.trust", mailsmtpssltrust);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        //2nd step)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(body);
            message.setContent(body, "text/html; charset=utf-8");
            //3rd step)send message
            Transport.send(message);
            mailStatus = "success";
        } catch (MessagingException e) {
            e.printStackTrace();
            mailStatus = "fail";
            //throw new RuntimeException(e);
        }

        return mailStatus;
    }

}
