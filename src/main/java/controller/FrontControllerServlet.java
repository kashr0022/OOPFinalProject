package controller;

import businesslayer.PTFMSBusinessLogic;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class FrontControllerServlet extends HttpServlet {

    private boolean loggedIn;

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            out.println("<!DOCTYPE html><html><head>");
            out.println("<title>Enter PTFMS Credentials</title>");
            out.println("<link rel='stylesheet' href='assets/styles.css'>");
            out.println("</head><body>");
            out.println("<center>");
//          type casting to String because .toString causes null pointer exception
            String logInFailMsg = (String) req.getAttribute("logInFailMsg");
            String logInSuccessMsg = (String) req.getAttribute("logInSuccessMsg");
            if (logInFailMsg != null) {
                out.println("<center>");
                out.println("<p style='color:red;'>" + logInFailMsg + "</p>");
                out.println("</center>");
                logInFailMsg = null;
            } if (logInSuccessMsg != null) {
                out.println("<center>");
                out.println("<p style='color:green;'>" + logInSuccessMsg + "</p>");
                out.println("</center>");
                logInSuccessMsg = null;
            }

            out.println("<center class=\"border-white\">");
            out.println("<h1 class=\"title\">PTFMS</h1>");
            out.println("<h2 class=\"subtitle\">Enter Credentials</h2>");


            out.println("<form action='controller' method='POST'>");
            out.println("<label>Username: <input type='text' name='username' required></label><br>");
            out.println("<label>Password: <input type='password' name='password' required></label><br><br>");
            out.println("<button type='submit' name=\"logInCheck\" value='Login'>Login</button>");
            out.println("</form>");



            out.print("<br>");

            if (loggedIn) {
                out.print("<div class=\"button-con\">");

                out.print("<form action=\"\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"PageOne\">Page One</button>");
                out.print("</form>");


                out.print("</div>");
                loggedIn = false;

            } else {
                out.print("<div class=\"button-con\">");

                out.print("<form action=\"register\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"Register\">Register</button>");
                out.print("</form>");


                out.print("</div>");
            }
            out.println("</center>");
            out.println("</body></html>");
        }
    }

    protected boolean authenticateAccount(String userIn, String passIn) {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        return ptfmsBusinessLogic.checkCred(userIn, passIn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String logInCheck = request.getParameter("logInCheck");

        if ("Login".equals(logInCheck)) {
            boolean loginSuccess = authenticateAccount(request.getParameter("username"), request.getParameter("password"));

            if (loginSuccess) {
                loggedIn = true;
                request.setAttribute("logInSuccessMsg", "Welcome back " + request.getParameter("username") + ".");
                processRequest(request, response);
            } else {
                request.setAttribute("logInFailMsg", "Invalid Credentials. Please Retry.");
                processRequest(request, response);

            }
        } else {

            processRequest(request, response);
        }
    }

}