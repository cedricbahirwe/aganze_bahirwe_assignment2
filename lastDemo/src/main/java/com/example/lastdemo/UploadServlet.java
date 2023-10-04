package com.example.lastdemo;

import com.example.lastdemo.model.EmailInfo;
import com.example.lastdemo.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
    }
}