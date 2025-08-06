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

public class ComponentMaintenanceServlet extends HttpServlet {
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
            out.println("<h2 class=\"subtitle\">Component Status Overview</h2>");

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

    protected String getStatusColor(double hoursUsed) {
        if (hoursUsed > 1100) {
            return "red";
        } else if (hoursUsed > 800) {
            return "yellow";
        } else {
            return "green";
        }
    }

    protected String getStatusText(double hoursUsed) {
        if (hoursUsed > 1100) {
            return "CRITICAL";
        } else if (hoursUsed > 800) {
            return "WARNING";
        } else {
            return "FUNCTIONAL";
        }
    }

    protected int getAlertCounter() {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        return ptfmsBusinessLogic.getMaintenanceAlertCount();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
