package viewlayer.authentication;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import businesslayer.PTFMSBusinessLogic;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;

/**
 * Servlet responsible for the Register page. Allows users to interact with a GUI form in order to register a new user/staff with the DB.
 * 
 * @author Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class RegisterServlet extends HttpServlet {

    private boolean registerComplete;

    /**
     * processRequest() holds the main printwriter prints displaying all the GUI to the user. Contains input forms for user to enter information. Messages appear if username during register already exists or firstname/lastname combo exists.
     * @author Lily S.
     * @param req, webapp request
     * @param res, webapp response
     * @throws IOException input-output error
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            out.println("<!DOCTYPE html><html><head>");
            out.println("<title>Register New User</title>");
            out.println("<link rel='stylesheet' href='assets/styles.css'>");
            out.println("</head><body>");
            out.println("<center>");

            // display message if username/first+last exists
            String alreadyExistsMsg = (String) req.getAttribute("alreadyExistsMsg");
            if (alreadyExistsMsg != null) {
                out.println("<center>");
                out.println("<p style='color:red;'>" + alreadyExistsMsg + "</p>");
                out.println("</center>");
                alreadyExistsMsg = null;
            }
            out.println("<div class=\"border-white\">");
//          change GUI display if registration completed
            if (registerComplete) {
                out.println("<p>Register complete, please log in with the username & password.</p>");
                out.println("</form>");
                out.print("<form action=\"controller\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"Return\">Log in</button>");
                out.print("</form>");
                registerComplete = false;
            } else {
                out.println("<h1 class=\"title\">REGISTER</h1>");
                out.println("<h2 class=\"subtitle\">Creating a new user</h2>");

                out.println("<form action='register' method='POST' class='register-form'>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>First Name:</label>");
                out.println("<input type='text' name='firstname' required>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Last Name:</label>");
                out.println("<input type='text' name='lastname' required>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Email:</label>");
                out.println("<input type='email' name='email' required>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Username:</label>");
                out.println("<input type='text' name='username' required>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Password:</label>");
                out.println("<input type='password' name='password' required>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Choose an option:</label>");
                out.println("<select name='roleoptions' required>");
                out.println("<option value='Operator'>Operator</option>");
                out.println("<option value='TransitManager'>Transit Manager</option>");
                out.println("</select>");
                out.println("</div>");

                out.println("<div class='button-con'>");
                out.println("<button type='submit' name='registerCheck' value='RegisterUser'>Register</button>");
                out.println("</form>");
                out.print("<form action=\"controller\" method=\"GET\">");
                out.print("<button type=\"submit\" value=\"Return\">Return</button>");
                out.print("</form>");
                out.println("</div>");
            }

            out.println("</div>");
            out.println("</center>");
            out.println("</body></html>");
        }
    }

    /**
     * commitNewRegister processes the user registration form data by creating StaffDTO and UsersDTO objects and calling business logic to add both staff and user entries to the database. Sets registerComplete flag upon successful completion.
     * @author Lily S.
     * @param req, request object containing registration form data
     * @param res, response object
     */
    protected void commitNewRegister(HttpServletRequest req, HttpServletResponse res) {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        StaffDTO staff = new StaffDTO();
        UsersDTO user = new UsersDTO();

        staff.setFirstName(req.getParameter("firstname"));
        staff.setLastName(req.getParameter("lastname"));
        staff.setEmail(req.getParameter("email"));
        staff.setRole(req.getParameter("roleoptions"));

        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("roleoptions"));

        ptfmsBusinessLogic.addStaffUser(staff, user);

        registerComplete = true;

    }

    /**
     * checkIfExists validates whether the entered username or staff first/last name combination already exists in the database. Sets appropriate error messages based on what conflicts are found.
     * @author Lily S.
     * @param req, request object containing registration form data
     * @param res, response object
     * @return boolean
     */
    protected boolean checkIfExists(HttpServletRequest req, HttpServletResponse res) {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        StaffDTO staff = new StaffDTO();
        UsersDTO user = new UsersDTO();

        staff.setFirstName(req.getParameter("firstname"));
        staff.setLastName(req.getParameter("lastname"));
        staff.setEmail(req.getParameter("email"));
        staff.setRole(req.getParameter("roleoptions"));

        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("roleoptions"));

        boolean staffExists = ptfmsBusinessLogic.checkStaffTaken(staff);
        boolean userExists = ptfmsBusinessLogic.checkUserTaken(user);

        if (staffExists && userExists) {
            req.setAttribute("alreadyExistsMsg", "Staff First/Last & Username (" + req.getParameter("username") + ") already taken.");
            return true;
        } else if (userExists) {
            req.setAttribute("alreadyExistsMsg", "Username (" + req.getParameter("username") + ") already taken.");
            return true;
        } else if (staffExists) {
            req.setAttribute("alreadyExistsMsg", "First/Last name (" + req.getParameter("firstname") + " " + req.getParameter("lastname") + ") already taken.");
            return true;
        } else {
            return false;
        }
    }

    /**
     * doGet, overridden method corresponding to HTTP GET, simply calls processRequest while feeding in parameters HTTPServletRequest req, HttpServletResponse res
     * @author Lily S.
     * @param req, request
     * @param res, response
     * @throws ServletException servlet related errors
     * @throws IOException input-output related errors
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    /**
     * doPost, overridden method corresponding to HTTP POST, handles user registration form submission by checking for existing users/staff and processing registration if valid.
     * @author Lily S.
     * @param req, request
     * @param res, response
     * @throws ServletException servlet related errors
     * @throws IOException input-output related errors
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (!checkIfExists(req, res)) {
            commitNewRegister(req, res);
        }
        processRequest(req, res);
    }
}