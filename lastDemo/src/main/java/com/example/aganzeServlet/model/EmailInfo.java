package com.example.aganzeServlet.model;

public class EmailInfo {

    private String firstName;
    private String lastName;

    private String senderEmail;

    private String password;

    private String recipientEmail;

    private String subject;

    String emailBody;

    public EmailInfo(String firstName, String lastName, String senderEmail, String password, String recipientEmail, String subject, String emailBody) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.senderEmail = senderEmail;
        this.password = password;
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.emailBody = emailBody;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFullText() {
        return "Thanks for Submitting your application" + "\n\n" + "Full Name: " +  getFullName() + "\n" + "Email: " + senderEmail + "\n\n" + emailBody;
    }

    public String getSubject() {
        return subject;
    }
}
