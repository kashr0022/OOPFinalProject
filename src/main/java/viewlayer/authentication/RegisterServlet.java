package viewlayer.authentication;

import dataaccesslayer.DaoImplementation;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import model.Role;
import static model.Role.OPERATOR;
import model.Staff;
import transferobjects.staff.OperatorDTO;
import transferobjects.staff.TransitManagerDTO;

/**
 * Description: AddTitleServlet to add new title
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/authentication/Register.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String role = req.getParameter("role");

        if (firstName == null || firstName.trim().isEmpty()
                || lastName == null || lastName.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || role == null || role.trim().isEmpty()) {

            req.setAttribute("error", "All fields are required and cannot be empty.");
            req.getRequestDispatcher("/WEB-INF/views/authentication/Register.jsp").forward(req, res);
            return;
        }

//        try {
//            int edition = Integer.parseInt(editionStr);
//
//            if (Role == OPERATOR){
//                OperatorDTO operator = new OperatorDTO();
//            operator.setFirstName(firstName);
//            operator.setLastName(lastName);
//            operator.setEmail(email);
//            operator.setRole(role);
//
//            }
//            
//            CredentialsDTO creds = new CredentialsDTO();
//            creds.setUsername("cst8288");
//            creds.setPassword("cst8288");
//
//            TitlesDaoImpl dao = new TitlesDaoImpl(creds);
//            dao.addTitle(t);
//
//            res.sendRedirect(req.getContextPath() + "/controller?action=getAllTitles");
//
//        } catch (Exception e) {
//            req.setAttribute("error", "Failed to add title: " + e.getMessage());
//            req.getRequestDispatcher("/WEB-INF/views/titles/AddTitle.jsp").forward(req, res);
//        }
    }
}