package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.isEmpty()) {
            showHomePage(req, res, null);
            return;
        }

        String targetServlet = null;
        String nextAction = req.getParameter("nextAction");
        switch (action) {
            // authors
            case "":
                targetServlet = "/";
                break;

            default:
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown GET action: " + action);
                return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(targetServlet);
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        String targetServlet = null;

        if (action == null || action.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing POST action");
            return;
        }

        switch (action) {
            // authentication
            case "register":
                targetServlet = "/WEB-INF/views/authentication/Register.jsp";
                break;

            default:
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown POST action: " + action);
                return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(targetServlet);
        dispatcher.forward(req, res);
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse res, String errorMessage)
            throws IOException {

        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            out.println("<!DOCTYPE html><html><head>");
            out.println("<title>Enter PTFMS Credentials</title>");
            out.println("<link rel='stylesheet' href='assets/styles.css'>");
            out.println("</head><body>");
            out.println("<center>");
            out.println("<h1 class=\"title\">PTFMS</h1>");
            out.println("<h2 class=\"subtitle\">Enter Credentials</h2>");

            // login
            out.println("<form action='controller' method='get'>");
            out.println("<label>Username: <input type='text' name='username' required></label><br>");
            out.println("<label>Password: <input type='password' name='password' required></label><br><br>");
            out.println("</form>");
            
            // register
            out.println("<form action='controller' method='post'>");
            out.println("<button type='submit' name='action' value='login'>Login</button>");
            out.println("<button type='submit' name='action' value='register'>Register</button>");
            out.println("<div style='form-row'>");
            out.println("</form>");
            out.println("</center>");
            if (errorMessage != null) {
                out.println("<p style='color:red;'>" + errorMessage + "</p>");
            }
//           
            out.println("</body></html>");
        }
    }
}
