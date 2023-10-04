package com.example.lastdemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet
public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(LoginServlet.class.getName());

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException {

        String username, password;
        try {

            PrintWriter pr = resp.getWriter();

//            username = req.getParameter("username");
//            password = req.getParameter("password");

//            pr.println("<h2>Your username is: " + "<u>" + username + "</u>" + " and your password: " + "<u>" + password + "</u>" + "</h2>");

            resp.sendRedirect(req.getContextPath() + "/form.html");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error happened " + e.getMessage());
        }
    }
}
