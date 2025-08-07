package viewlayer;

import dataaccesslayer.PTFMSDao;
import dataaccesslayer.PTFMSDaoImpl;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transferobjects.reports.BreakLogDTO;

/**
 * Servlet to handle break actions submitted by staff.
 * It records break start/end actions to the break log.
 * 
 * Expects a POST request with a parameter "action" indicating the break action.
 * Requires an active session with a loggedStaffId attribute.
 * On success, redirects to break history page for the staff.
 * 
 * @author Khairunnisa Ashri
 */
@WebServlet("/breakAction")
public class BreakActionServlet extends HttpServlet {

    /**
     * Handles POST requests to record a break action for the logged-in staff.
     * 
     * @param request the HttpServletRequest object containing client request
     * @param response the HttpServletResponse object for sending response
     * @throws IOException if an I/O error occurs during request handling
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No active session, please login.");
            return;
        }

        Integer loggedStaffId = (Integer) session.getAttribute("loggedStaffId");
        if (loggedStaffId == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session missing staff info, please login.");
            return;
        }

        String action = request.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or empty action parameter");
            return;
        }

        BreakLogDTO newLog = new BreakLogDTO();
        newLog.setStaffID(loggedStaffId);
        newLog.setAction(action.trim().toUpperCase()); // normalize action to upper case
        newLog.setTimestamp(LocalDateTime.now());

        PTFMSDao dao = new PTFMSDaoImpl();

        try {
            dao.insertBreakLog(newLog);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to record break action");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/breakHistory?staffID=" + loggedStaffId);
    }
}
