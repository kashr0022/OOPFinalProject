package viewlayer;

import businesslayer.PTFMSBusinessLogic;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import transferobjects.reports.CostReportDTO;
import transferobjects.reports.FuelReportDTO;
import transferobjects.reports.MaintenanceLogDTO;
import transferobjects.reports.OperatorPerformanceDTO;

/**
 * Servlet that loads dashboard data and renders the manager dashboard view.
 * 
 * Access control is enforced based on the user role stored in the session:
 * - Only users with role "transitmanager" can access full dashboard with operator performance data.
 * - Other logged-in users receive a limited dashboard view without operator performance data.
 * - Unauthenticated users receive an HTTP 401 Unauthorized response.
 * 
 * Retrieves data via {@link PTFMSBusinessLogic} and forwards to the Dashboard JSP for rendering.
 * 
 * Author: Khairunnisa Ashri
 */
public class DashboardServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests for the dashboard.
     * Checks session and user role, then loads relevant data accordingly.
     * 
     * @param req the HttpServletRequest
     * @param res the HttpServletResponse
     * @throws ServletException if servlet errors occur
     * @throws IOException if I/O errors occur
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userRole") == null) {
            // if no session or not logged in, unauthorized access
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied. Please log in.");
            return;
        }

        String userRole = (String) session.getAttribute("userRole");

        // only allow transit managers full access
        if (!"transitmanager".equalsIgnoreCase(userRole)) {
            // load limited dashboard data for non-transit managers
            try {
                PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();

                List<FuelReportDTO> fuelReports = ptfmsBusinessLogic.getFuelReport();
                List<MaintenanceLogDTO> maintenanceLogs = ptfmsBusinessLogic.getAllLogs();
                List<CostReportDTO> costReports = ptfmsBusinessLogic.getCostReport();

                req.setAttribute("fuelReports", fuelReports);
                req.setAttribute("maintenanceLogs", maintenanceLogs);
                req.setAttribute("fuelAlerts", getFuelAlertCounter());

                // preserve session info for JSP
                req.setAttribute("staffName", session.getAttribute("staffName"));
                req.setAttribute("loggedStaffId", session.getAttribute("loggedStaffId"));
                req.setAttribute("userRole", userRole);

                req.getRequestDispatcher("/WEB-INF/views/dashboards/Dashboard.jsp").forward(req, res);
            } catch (RuntimeException e) {
                throw new ServletException("Error retrieving dashboard data", e);
            }

            return;
        }

        // if user is transit manager, fetch full dashboard data
        try {
            PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();

            List<FuelReportDTO> fuelReports = ptfmsBusinessLogic.getFuelReport();
            List<MaintenanceLogDTO> maintenanceLogs = ptfmsBusinessLogic.getAllLogs();
            List<OperatorPerformanceDTO> operatorPerformance = ptfmsBusinessLogic.getOperatorPerformance();
            List<CostReportDTO> costReports = ptfmsBusinessLogic.getCostReport();

            req.setAttribute("fuelReports", fuelReports);
            req.setAttribute("maintenanceLogs", maintenanceLogs);
            req.setAttribute("operatorPerformance", operatorPerformance);
            req.setAttribute("costReports", costReports);
            req.setAttribute("fuelAlerts", getFuelAlertCounter());

            // preserve session info
            req.setAttribute("staffName", session.getAttribute("staffName"));
            req.setAttribute("loggedStaffId", session.getAttribute("loggedStaffId"));
            req.setAttribute("userRole", userRole);

            req.getRequestDispatcher("/WEB-INF/views/dashboards/Dashboard.jsp").forward(req, res);
        } catch (RuntimeException e) {
            throw new ServletException("Error retrieving dashboard data", e);
        }
    }

    /**
     * Helper method to get the count of fuel alerts.
     * Uses the business logic layer to retrieve the count.
     *
     * @return the number of fuel alerts
     */
    protected int getFuelAlertCounter() {
        PTFMSBusinessLogic logic = new PTFMSBusinessLogic();
        return logic.getFuelAlertCount();
    }

}
