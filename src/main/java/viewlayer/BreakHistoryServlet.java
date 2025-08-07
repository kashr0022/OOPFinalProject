package viewlayer;

import dataaccesslayer.PTFMSDao;
import dataaccesslayer.PTFMSDaoImpl;
import transferobjects.reports.BreakLogDTO;
import transferobjects.staff.StaffDTO;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servlet to display break history and allow staff to submit break actions.
 * 
 * Supports role-based access:
 * - TransitManager can view any staff's break logs (using ?staffID= param),
 * - Other users can view only their own break logs.
 * 
 * Handles GET to show break logs and POST to record new break actions.
 * 
 * Author: Khairunnisa Ashri
 */
public class BreakHistoryServlet extends HttpServlet {

    /** DAO for database access related to staff and break logs */
    private final PTFMSDao ptfmsDao = new PTFMSDaoImpl();

    /**
     * Handles HTTP GET requests to display break history.
     * Allows TransitManager to specify staffID to view logs, 
     * other users can only view their own logs.
     * 
     * @param req  HttpServletRequest object containing client request
     * @param res  HttpServletResponse object for sending response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login first.");
            return;
        }

        Integer loggedStaffId = (Integer) session.getAttribute("loggedStaffId");
        String userRole = (String) session.getAttribute("userRole");

        if (loggedStaffId == null || userRole == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session missing staff info, please login.");
            return;
        }

        int staffID;
        String staffIDStr = req.getParameter("staffID");

        if ("TransitManager".equalsIgnoreCase(userRole)) {
            // TransitManager can view any staff break logs
            if (staffIDStr == null || staffIDStr.isEmpty()) {
                staffID = loggedStaffId; // default
            } else {
                try {
                    staffID = Integer.parseInt(staffIDStr);
                } catch (NumberFormatException e) {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid staff ID format.");
                    return;
                }
            }
        } else {
            // other users can only view their own logs
            staffID = loggedStaffId;
        }

        StaffDTO staff = ptfmsDao.getStaffByID(staffID);
        if (staff == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "Staff member not found.");
            return;
        }

        List<BreakLogDTO> breakLogs = ptfmsDao.getBreakLogsByStaffID(staffID);

        req.setAttribute("staffID", staffID);
        req.setAttribute("staffName", staff.getFirstName() + " " + staff.getLastName());
        req.setAttribute("breakLogs", breakLogs);

        req.getRequestDispatcher("/WEB-INF/views/dashboards/BreakHistory.jsp").forward(req, res);
    }

    /**
     * Handles HTTP POST requests to record a new break action for the logged-in user.
     * Expects an "action" parameter describing the break action.
     * 
     * @param req  HttpServletRequest object containing client request
     * @param res  HttpServletResponse object for sending response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No active session, please login.");
            return;
        }

        Integer staffID = (Integer) session.getAttribute("loggedStaffId");
        if (staffID == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session missing staff info, please login.");
            return;
        }

        String action = req.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action parameter.");
            return;
        }

        BreakLogDTO newLog = new BreakLogDTO();
        newLog.setStaffID(staffID);
        newLog.setAction(action);
        newLog.setTimestamp(LocalDateTime.now());

        ptfmsDao.insertBreakLog(newLog);

        // back to the logged-in user's break history
        res.sendRedirect(req.getContextPath() + "/breakHistory");
    }
}
