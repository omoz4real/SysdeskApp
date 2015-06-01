/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardinal.sysdesk;

import com.cardinal.entities.Task;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Trishna
 */
@Named(value = "emailAccount")
@SessionScoped
public class emailAccount implements Serializable {

    private Task task;
    @Resource(name = "mail/gmailAccount")
    private Session mailSession;
    private String smtpServe = "smtp.gmail.com";

    /**
     * Creates a new instance of emailAccount
     */
    public emailAccount() {
        task = new Task();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getSmtpServe() {
        return smtpServe;
    }

    public void setSmtpServe(String smtpServe) {
        this.smtpServe = smtpServe;
    }

    public void sendNotification() throws MessagingException {
        System.out.println("This is the task email account " + task.getEmail());
        ResourceBundle bundle = ResourceBundle.getBundle("resources.messages");
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", smtpServe);
        props.put("mail.smtp.auth", "true");
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(task.getClient()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(task.getEmail(),false));
        String subject = bundle.getString("subject");
        String body = bundle.getString("body");
        String messageText = MessageFormat.format(body, task.getContactName(), task.getStartDate(), task.getEndDate());
        message.setSubject(subject);
        message.setText(messageText);
        message.setSentDate(new Date());
        message.saveChanges();
        Transport.send(message);
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentitication() {
            String username = "omoz4real@gmail.com";
            String password = "slimmy123";
            return new PasswordAuthentication(username, password);
        }
    }
}
