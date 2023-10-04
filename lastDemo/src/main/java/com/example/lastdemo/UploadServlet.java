package com.example.lastdemo;

import com.example.lastdemo.model.EmailInfo;
import com.example.lastdemo.service.EmailService;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve student details from the form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Prepare email content
        String emailContent = "New student admission:\n";
        emailContent += "First Name: " + firstName + "\n";
        emailContent += "Last Name: " + lastName + "\n";

        // Process uploaded files
        Part passportPicturePart = request.getPart("passportPicture");
        Part certificatesPart = request.getPart("certificates");

        // Get file names
//        String passportPictureFileName = getFileName(passportPicturePart);
//        String certificatesFileName = getFileName(certificatesPart);

        // Save uploaded files to a directory on the server
        String uploadPath = "your_upload_directory_path"; // Replace with the actual directory path
//        String passportPictureFilePath = uploadPath + File.separator + passportPictureFileName;
//        String certificatesFilePath = uploadPath + File.separator + certificatesFileName;

        // Save the uploaded files to the server
//        passportPicturePart.write(passportPictureFilePath);
//        certificatesPart.write(certificatesFilePath);

        // Send email with attachments
//        EmailService.sendEmail(
//                "cedbahirwe@gmail.com",
//                "New Student Admission",
//                emailContent,
//                "passportPictureFilePath",
//                "certificatesFilePath");

        EmailInfo emailInfo = new EmailInfo(
                firstName,
                lastName,
                "bahirwe.aganze@auca.ac.rw",
                "servlet2023",
                "cedbahirwe@gmail.com",
                "New Student Admission",
                "No mode Info Added");
        EmailService emailService = new EmailService();
        emailService.sendMail(response, emailInfo);
    }

}