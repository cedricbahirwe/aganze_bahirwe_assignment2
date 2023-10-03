import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class  HomeServlet extends HttpServlet {
    final String defaultPassword = "aganze123";

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email, password;

        email = req.getParameter("email");
        password = req.getParameter("password");

        // Email validation using a regular expression
        String emailPattern = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

        if ((email == null && password == null)) {
            // Go to default home page without data
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            if ((email != null) && (!email.matches(emailPattern))) {
                resp.getWriter().println("Please enter a valid email address.");
                return;
            }

            // Set the defaultPassword as request attributes
            req.setAttribute("email", email);

            if (password.equals(defaultPassword)) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                // Set the defaultPassword as request attributes
                req.setAttribute("defaultPassword", defaultPassword);

                // Go to Forget Password
                String redirectURL = req.getContextPath() + "/forgetPassword" +
                        "?email=" + email +
                        "&defaultPassword=" + defaultPassword;
                resp.sendRedirect(redirectURL);
            }
        }
    }
}