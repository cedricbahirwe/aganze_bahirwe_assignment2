import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ForgetPasswordServlet  extends HttpServlet {
    final String emailKey = "email";
    final String defaultPassKey = "defaultPassword";
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter(emailKey);
        String param2 = req.getParameter(defaultPassKey);

        req.setAttribute(emailKey, param1);
        req.setAttribute(defaultPassKey, param2);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/forgetPassword.jsp");
        requestDispatcher.forward(req, resp);
    }
}

