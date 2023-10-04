package com.example.lastdemo.service;

import com.example.lastdemo.model.EmailInfo;

import java.io.PrintWriter;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class EmailService {
    public void sendMail(HttpServletResponse response, EmailInfo emailInfo) {


        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Configure email sending properties here (SMTP server, username, password, etc.)
        final String senderEmail =  emailInfo.getSenderEmail();
        final String password = emailInfo.getPassword();
        final String recipientEmail = emailInfo.getRecipientEmail();

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        };

        Session session = Session.getInstance(props, authenticator);

        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            message.setSubject(emailInfo.getSubject());

            MimeBodyPart textPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            textPart.setText(emailInfo.getFullText());
            multipart.addBodyPart(textPart);
            message.setContent(multipart);

            BodyPart messageBodyPart = new MimeBodyPart();


            Transport.send(message);

            out.println("<center><h2 style='color:green;'>Email Sent Successfully.</h2>");
            out.println("Thank you " + emailInfo.getFullName() + ", your message has been submitted to us.</center>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
