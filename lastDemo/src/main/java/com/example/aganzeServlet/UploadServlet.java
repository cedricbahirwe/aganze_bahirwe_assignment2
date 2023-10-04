package com.example.aganzeServlet;

import com.example.aganzeServlet.model.EmailInfo;
import com.example.aganzeServlet.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;

@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve student details from the form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Process uploaded files
        Part passportPicturePart = request.getPart("passportPicture");
        Part certificatesPart = request.getPart("certificates");

        EmailInfo emailInfo = new EmailInfo(
                firstName,
                lastName,
                "bahirwe.aganze@auca.ac.rw",
                "servlet2023",
                "cedbahirwe@gmail.com",
                "New Student Admission",
                "No more Info Added");
        EmailService emailService = new EmailService();

        // Send email with attachments
        emailService.sendMail(
                response,
                emailInfo,
                passportPicturePart,
                certificatesPart);

        // Store state in session
        HttpSession session = request.getSession();
        session.setAttribute("isMailSent", true);
    }
}