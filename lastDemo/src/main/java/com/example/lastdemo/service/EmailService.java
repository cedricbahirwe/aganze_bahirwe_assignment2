package com.example.lastdemo.service;

import com.example.lastdemo.model.EmailInfo;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Properties;

public class EmailService {
    public void sendMail(HttpServletResponse response,
                         EmailInfo emailInfo,
                         Part passportPicturePart,
                         Part certificatesPart) throws IOException  {

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.debug", "true");

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

            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set From email field
            message.setFrom(new InternetAddress(senderEmail));
            // Set To email field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            // Set email subject field
            message.setSubject(emailInfo.getSubject());
            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Set the actual message
            messageBodyPart.setText(emailInfo.getFullText());

            // Create an instance of multipart object
            Multipart multipart = new MimeMultipart();
            // Set the first text message part
            multipart.addBodyPart(messageBodyPart);

            if (passportPicturePart != null) {
                // Set the second part, which is the passport attachment (image)
                BodyPart bodyPart = addAttachment(passportPicturePart, "image/jpeg");
                multipart.addBodyPart(bodyPart);
            }

            if (certificatesPart != null) {
                // Set the third part, which is the passport attachment (pdf)
                BodyPart bodyPart = addAttachment(certificatesPart, "application/pdf");
                multipart.addBodyPart(bodyPart);
            }

            message.setContent(multipart);

            // Send the email message
            Transport.send(message);

            out.println("<center><h2 style='color:green;'>Email Sent Successfully.</h2>");
            out.println("Thank you " + emailInfo.getFullName() + ", your message has been submitted to us.</center>");
        } catch ( Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private BodyPart addAttachment(Part attachmentPart, String type) throws IOException, MessagingException {
        String certificatesPartFileName = Paths.get(attachmentPart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = attachmentPart.getInputStream();

        // Add the file attachment
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new ByteArrayDataSource(fileContent, type);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(certificatesPartFileName);

        return messageBodyPart;
    }
}
