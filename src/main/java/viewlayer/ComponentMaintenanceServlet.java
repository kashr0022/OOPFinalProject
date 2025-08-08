package viewlayer;

import businesslayer.PTFMSBusinessLogic;
import transferobjects.reports.ComponentDTO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Component maintenance servlet that displays maintenance alerts and status for
 * all vehicle components. Shows color-coded status indicators and maintenance
 * scheduling options based on user roles.
 *
 * @author: Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class ComponentMaintenanceServlet extends HttpServlet {

    /**
     * processRequest generates the main HTML page displaying component
     * maintenance alerts. Creates a table showing vehicle components with their
     * usage hours, status, and available actions based on user role.
     *
     * @author Lily S.
     * @param req, request
     * @param res, response
     * @throws IOException, input-output related errors
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);
        String userRole = (String) session.getAttribute("userRole");
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        List<ComponentDTO> allComponents = ptfmsBusinessLogic.getAllComponents();

        try (PrintWriter out = res.getWriter()) {

            out.println("<!DOCTYPE html><html><head>");
            out.println("<title>Component Maintenance Alerts</title>");
            out.println("<link rel='stylesheet' href='assets/styles.css'>");
            out.println("</head><body>");
            out.println("<center>");

            out.println("<div class=\"border-white\">");
            out.println("<h1 class=\"title\">MAINTENANCE ALERTS</h1>");
            out.println("<h2 class=\"subtitle\">Component Status Overview of All Vehicles</h2>");

            out.println("<table class='maintenance-con' border='1'>");
            out.println("<tr>");
            out.println("<th>Vehicle</th>");
            out.println("<th>Component Name</th>");
            out.println("<th>Type</th>");
            out.println("<th>Hours Used</th>");
            out.println("<th>Status</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");
// loop thru list of components, created by businesslogic dao method call
            for (ComponentDTO component : allComponents) {
                String statusColor = getStatusColor(component.getHoursUsed());
                String statusText = getStatusText(component.getHoursUsed());

                out.println("<tr>");
                out.println("<td>" + component.getVehicleNum() + "</td>");
                out.println("<td>" + component.getComponentName() + "</td>");
                out.println("<td>" + component.getComponentType() + "</td>");
                out.println("<td>" + component.getHoursUsed() + "</td>");
                out.println("<td>" + "<p style='color: " + statusColor + "; font-weight: bold;'>" + statusText + "</p>" + "</td>");

                if (component.getHoursUsed() > 800 && "transitmanager".equalsIgnoreCase(userRole)) {
                    out.println("<td>");
//                  form with hidden inputs to grab component objects id
                    out.println("<form action='schedulemaintenance' method='GET' style='margin: 0;'>");
                    out.println("<input type='hidden' name='componentid' value='" + component.getComponentId() + "'>");
                    out.println("<input type='hidden' name='vehicleid' value='" + component.getVehicleId() + "'>");
                    out.println("<button type='submit'>Schedule Maintenance</button>");
                    out.println("</form>");
                    out.println("</td>");
                } else if (component.getHoursUsed() > 800 && "operator".equalsIgnoreCase(userRole)) {
                    out.println("<td>");
                    out.println("Maintenance needed. Contact Transit Manager for scheduling.");
                    out.println("</td>");
                } else {
                    out.println("<td>No Maintenance Needed</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("<div class='button-con' style='margin-top: 20px;'>");
            out.println("<form action=\"controller\" method=\"GET\">");
            out.print("<button type=\"submit\" value=\"Return\">Return</button>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='alerts-ctr'>");
            out.println("<p>Component Maintenance Alerts: " + getAlertCounter() + "</p>");
            out.println("</div>");
            out.println("</center>");
            out.println("</body></html>");
        }

    }

    /**
     * getStatusColor determines the appropriate color code for component status
     * display based on hours used thresholds.
     *
     * @author Lily S.
     * @param hoursUsed, total hours the component has been in use
     * @return String, color name for status display (red, yellow, or green)
     */
    protected String getStatusColor(double hoursUsed) {
        if (hoursUsed > 1100) {
            return "red";
        } else if (hoursUsed > 800) {
            return "yellow";
        } else {
            return "green";
        }
    }

    /**
     * getStatusText returns the text description of component status based on
     * hours used thresholds for display purposes.
     *
     * @author Lily S.
     * @param hoursUsed, total hours the component has been in use
     * @return String, status text (CRITICAL, WARNING, or FUNCTIONAL)
     */
    protected String getStatusText(double hoursUsed) {
        if (hoursUsed > 1100) {
            return "CRITICAL";
        } else if (hoursUsed > 800) {
            return "WARNING";
        } else {
            return "FUNCTIONAL";
        }
    }

    /**
     * getAlertCounter retrieves the total count of components requiring
     * maintenance attention from the business logic layer.
     *
     * @author Lily S.
     * @return int, number of components with maintenance alerts
     */
    protected int getAlertCounter() {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        return ptfmsBusinessLogic.getMaintenanceAlertCount();
    }

    /**
     * doGet, overridden method corresponding to HTTP GET, simply calls
     * processRequest while feeding in parameters HTTPServletRequest request
     * (req), HttpServletResponse response (res)
     *
     * @param request, request
     * @param response, response
     * @throws IOException, input-output related errors
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    /**
     * doPost, overridden method corresponding to HTTP POST, simply calls
     * processRequest while feeding in parameters HTTPServletRequest request
     * (req), HttpServletResponse response (res)
     *
     * @param request, request
     * @param response, response
     * @throws IOException, input-output related errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
