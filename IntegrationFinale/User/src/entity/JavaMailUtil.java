/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HEDI MSELMI
 */
public class JavaMailUtil {
    public static void sendMail(String recepient){
        Properties properties=new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail="mohamedmselmi407@gmail.com";
        String password="50400131";
        
        Session session= Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }           
            
});
        try { 
            Message message= prepareMessage(session,myAccountEmail,recepient);
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
            
      
        
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) throws MessagingException {
        Message message; //To change body of generated methods, choose Tools | Templates.
        message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Service Inscription");
            message.setText("votre inscription à été effectuer avec succées");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
