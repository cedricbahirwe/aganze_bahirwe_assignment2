package com.example.lastdemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet
public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(LoginServlet.class.getName());

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException {

        try {

            HttpSession session = req.getSession();
            Object isMailSent = session.getAttribute("isMailSent");

            if ((isMailSent != null) && (boolean) isMailSent) {
                PrintWriter out = resp.getWriter();
                String email = (String) session.getAttribute("email");
                out.println("<center><h2 style='color:green;'>Email Already Successfully.</h2>");
//                out.println("Please check (<a href=\"mailto:" + email + "\">your inbox</a>) to see your confirmation email.");
                out.println("Please check <a href=\"mailto:" + email +  "\">your inbox</a> to see your confirmation email you .</center>");
            } else {
                resp.sendRedirect(req.getContextPath() + "/form.html");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error happened " + e.getMessage());
        }
    }
}
