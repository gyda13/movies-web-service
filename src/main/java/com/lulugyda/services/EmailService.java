package com.lulugyda.services;

import jakarta.inject.Singleton;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Singleton
public class EmailService {

    private final String username = "username"; //  SMTP username
    private final String password = "password"; //  SMTP password
    private final Properties props;

    public EmailService() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "localhost"); // MailHog SMTP host
        props.put("mail.smtp.port", "1025"); // MailHog SMTP port
    }

    public void sendEmail(String to, String subject, String body)  {
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);
        } catch (Exception e){
            throw new RuntimeException("SMTP error");
        }
    }
}
