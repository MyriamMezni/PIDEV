/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ben younes
 */
public class JavaMailUtil {
    
    public static void SendMail(String dest,String mdp){
        String SMTP_SERVER = "smtp-relay.sendinblue.com";
        String USERNAME = "mohamednaimbenyounes@gmail.com";
        String PASSWORD = "SnfAkKZ4t6YMsICj";

        String EMAIL_FROM = "Kiddy@gmail.com";
        String EMAIL_TO = dest;
        String EMAIL_TO_CC = "";

        String EMAIL_SUBJECT = "Recuperation de mot de passe";
        String EMAIL_TEXT = "<h1>Bonjour</h1><br><h3>Il parait que vous avez oublie votre mot de passe</h3><p>Voici votre mot de passe actuel:<b>"+mdp+"</b></p><p>Veulliez le changer le plus tot possible</p>";
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
		
			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

			// cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
            msg.setSubject(EMAIL_SUBJECT);
			
			// content 
            msg.setText(EMAIL_TEXT);
			
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        /*Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smt.port","587");
        String myEmail="mohamednaimbenyounes@gmail.com";
        String password="";
        Session session=Session.get*/
        
        
    }
    
    
    public static void SendMail2(String dest,String mdp){
        String SMTP_SERVER = "smtp-relay.sendinblue.com";
        String USERNAME = "mohamednaimbenyounes@gmail.com";
        String PASSWORD = "SnfAkKZ4t6YMsICj";

        String EMAIL_FROM = "Kiddy@gmail.com";
        String EMAIL_TO = dest;
        String EMAIL_TO_CC = "";

        String EMAIL_SUBJECT = "Rapport";
        String EMAIL_TEXT = mdp;
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
		
			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

			// cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
            msg.setSubject(EMAIL_SUBJECT);
			
			// content 
            msg.setText(EMAIL_TEXT);
			
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        /*Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smt.port","587");
        String myEmail="mohamednaimbenyounes@gmail.com";
        String password="";
        Session session=Session.get*/
        
        
    }
    
}
