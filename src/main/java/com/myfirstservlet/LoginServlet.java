package com.myfirstservlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Shruthi"),
                @WebInitParam(name = "password", value = "Shru@1999")
        }
)
public class LoginServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

        //get request parameters for user ID and password
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        // get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        String nameRegex = "^[A-Z][a-z]{2,}";

        // UC4 : Validating password of the user
        String pwdRegex = "^(?=.*[0-9])(?=[^@#$%^&+=]*[@#$%^&+=][^@#$%^&+=]*$)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

        if(userID.equals(user) && userID.matches(nameRegex) && password.equals(pwd) && password.matches(pwdRegex)){
            req.setAttribute("user",user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out  = resp.getWriter();
            out.println("<font color = red> Either username or password is wrong</font>");
            rd.include(req, resp);
        }

    }

}

