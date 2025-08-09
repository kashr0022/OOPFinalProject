package controller;

import businesslayer.PTFMSBusinessLogic;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Front controller of the web app. Contains log-in screen and navigation to every other page. Acts as a traffic controller.
 * @author: Lily S., Khairunnisa Ashri
 * @version 1.0
 * @since JDK 21.0.4
 */
public class FrontControllerServlet extends HttpServlet {

    /**
     * processRequest holds all the main html being output to the screen via the printwriter. Content such as navigation, log-in inputs are shown.
     * @author Lily S.
     * @author Khairunnisa Ashri
     * @param req, request
     * @param res, response
     * @throws IOException, input-output related errors
     * @version 1.0
     * @since JDK 21.0.4
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        // false to prevent a new session from being created if none exist
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

//          display register button if user is not logged in
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
//          nav section, only display if logged in
            if (loggedIn) {
                out.print("<hr class=\"line\">");
                out.println("<h2 class=\"subtitle\">Navigation</h2>");
                out.print("<div class=\"button-con\">");

                out.print("<form action=\"vehicleregistration\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"Vehicle Registration\">Vehicle Registration</button>");
                out.print("</form>");

                out.print("<form action=\"componentmaintenance\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"Component Maintenance\">Component Maintenance</button>");
                out.print("</form>");

                out.print("<form action=\"breakHistory\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"breakHistory\">Punch Clock</button>");
                out.print("</form>");

                out.print("</div>");

                out.print("<div class=\"button-con\">");
                out.print("<form action=\"dashboard\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"dashboard\">Dashboard</button>");
                out.print("</form>");

                out.print("<form action=\"gps\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"gps\">GPS</button>");
                out.print("</form>");

                out.print("</div>");
                out.println("</center>");
                out.println("</div>");

                // added fuel consumption alerts for managers K.A.
                String userRole = (String) session.getAttribute("userRole");
                if ("transitmanager".equalsIgnoreCase(userRole)) {
                    out.println("<div class='alerts-ctr'>");;

                    PTFMSBusinessLogic logic = new PTFMSBusinessLogic();
                    int fuelAlerts = logic.getFuelAlertCount();
                    out.println("<p>Fuel Alerts: " + fuelAlerts + "</p>");
                    out.println("</div>");
                }

            }
            out.println("</body></html>");
        }
    }

    /**
     * call business logic . checkCred method to validate user's entered credentials with the DB via BusinessLogic -> DAO -> DataSource -> DB chain
     * @author Lily S.
     * @param userIn, passed in username from the input field in processRequest()
     * @param passIn, passed in password from the input field in proccessRequest()
     * @return boolean, status if credentials are valid or not
     */
    protected boolean authenticateAccount(String userIn, String passIn) {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        return ptfmsBusinessLogic.checkCred(userIn, passIn);
    }

    /**
     * doGet, overridden method corresponding to HTTP GET, simply calls processRequest while feeding in parameters HTTPServletRequest request (req), HttpServletResponse response (res)
     * @param req, request
     * @param res, response
     * @throws ServletException, servlet related errors
     * @throws IOException, input-output related errors
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);

    }

    /**
     * doPost, overridden method corresponding to HTTP POST, this holds the majority of the logic behind login check, login validation, logout, and putting user cred + role into sesh storage.
     * @author Lily S.
     * @auhtor Khairunnisa Ashri
     * @param request
     * @param response
     * @throws ServletException, servlet related errors
     * @throws IOException, input-output related errors
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String logInCheck = request.getParameter("logInCheck");
        String action = request.getParameter("action");

        if ("Login".equals(logInCheck)) {
            boolean loginSuccess = authenticateAccount(request.getParameter("username"), request.getParameter("password"));
            if (loginSuccess) {
                PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
                UsersDTO userDTO = ptfmsBusinessLogic.getUserByUsername(request.getParameter("username"));
                StaffDTO staffDTO = ptfmsBusinessLogic.getStaffByUsername(request.getParameter("username"));

                HttpSession session = request.getSession(true);

                // store role and  username in session
                session.setAttribute("userRole", userDTO.getRole());
                session.setAttribute("username", request.getParameter("username"));

                // store staff id and name in session
                if (staffDTO != null) {
                    session.setAttribute("loggedStaffId", staffDTO.getStaffId());
                    session.setAttribute("staffName", staffDTO.getFirstName() + " " + staffDTO.getLastName());
                }
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
