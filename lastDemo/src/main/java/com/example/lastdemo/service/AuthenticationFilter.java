package com.example.lastdemo.service;

import javax.mail.Session;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationFilter implements Filter {
    Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());

    final static String _email = "bahirwe.aganze@auca.ac.rw";
    final static String _password = "servlet2023";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String email, password;
        try {

            email = servletRequest.getParameter("email");
            password = servletRequest.getParameter("password");

            if (email.equals(_email) && password.equals(_password)) {
                servletResponse.setContentType("text/html");
                logger.log(Level.INFO, "You have authenticated");

                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                String errorMessage = "UnAuthorized: Please use the provided credentials.";
                httpResponse.getWriter().write(errorMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "An error happened " + e.getMessage());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }


//    public void doGet(HttpServletRequest req,
//                      HttpServletResponse resp) throws ServletException, IOException {
//
//        String username, password;
//        try {
//
//            PrintWriter pr = resp.getWriter();
//
//            username = req.getParameter("username");
//            password = req.getParameter("password");
//            pr.println("Your username is " + username + " and password: " + password);
//        } catch (IOException e) {
//            logger.log(Level.SEVERE, "An error happened " + e.getMessage());
//        }
//    }
}