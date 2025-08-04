package controller;

import businesslayer.PTFMSBusinessLogic;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class FrontControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("username") != null);
        res.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = res.getWriter()) {
            out.println("<!DOCTYPE html><html><head>");
            out.println("<title>Enter PTFMS Credentials</title>");
            out.println("<link rel='stylesheet' href='assets/styles.css'>");
            out.println("</head><body>");
            out.println("<center>");

            String logInFailMsg = (String) req.getAttribute("logInFailMsg");
            String logInSuccessMsg = (String) req.getAttribute("logInSuccessMsg");

            if (logInFailMsg != null) {
                out.println("<center>");
                out.println("<p style='color:red;'>" + logInFailMsg + "</p>");
                out.println("</center>");
            }
            if (logInSuccessMsg != null) {
                out.println("<center>");
                out.println("<p style='color:green;'>" + logInSuccessMsg + "</p>");
                out.println("</center>");
            }

            out.println("<div class=\"con\">");

            if (!loggedIn) {
                out.println("<div class=\"corner-btn\">");
                out.println("<form action=\"register\" method=\"GET\">");
                out.println("<button type=\"submit\" value=\"Register\">Register</button>");
                out.println("</form>");
                out.println("</div>");
            } else {
                out.println("<div class=\"corner-btn\">");
                out.println("<form action='controller' method='POST'>");
                out.println("<input type='hidden' name='action' value='logout'>");
                out.println("<button type=\"submit\">Log out</button>");
                out.println("</form>");
                out.println("</div>");
            }

            out.println("<center class=\"border-white\">");
            out.println("<h1 class=\"title\">PTFMS</h1>");

            if (loggedIn) {
                out.println("<h2 class=\"subtitle it\">Current Account:</h2>");
                String loggedUsername = (String) session.getAttribute("username");
                out.println("<label>Username: <input type='text' value=\"" + loggedUsername + "\" disabled></label><br>");
            } else {
                out.println("<h2 class=\"subtitle it\">Enter Credentials:</h2>");
                out.println("<form action='controller' method='POST'>");
                out.println("<label>Username: <input type='text' name='username' required></label><br>");
                out.println("<label>Password: <input type='password' name='password' required></label><br><br>");
                out.println("<button type='submit' name=\"logInCheck\" value='Login'>Login</button>");
                out.println("</form>");
            }

            out.print("<br>");

            if (loggedIn) {
                out.print("<hr class=\"line\">");
                out.println("<h2 class=\"subtitle\">Navigation</h2>");
                out.print("<div class=\"button-con\">");
                out.print("<form action=\"vehicleregistration\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"Vehicle Registration\">Vehicle Registration</button>");
                out.print("</form>");
                out.print("</div>");

            }

            out.println("</center>");
            out.println("</div>");
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
        String action = request.getParameter("action");

        if ("Login".equals(logInCheck)) {
            boolean loginSuccess = authenticateAccount(request.getParameter("username"), request.getParameter("password"));
            if (loginSuccess) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", request.getParameter("username"));
                request.setAttribute("logInSuccessMsg", "Welcome back " + request.getParameter("username") + ".");
                processRequest(request, response);
            } else {
                request.setAttribute("logInFailMsg", "Invalid Credentials. Please Retry.");
                processRequest(request, response);
            }
        } else if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
//              remove sesh from storage
                session.invalidate();
            }
            processRequest(request, response);
        } else {
            processRequest(request, response);
        }
    }
}