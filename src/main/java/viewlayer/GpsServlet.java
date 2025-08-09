package viewlayer;

import businesslayer.PTFMSBusinessLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transferobjects.reports.CostReportDTO;
import transferobjects.reports.FuelReportDTO;
import transferobjects.reports.GpsDTO;
import transferobjects.reports.MaintenanceLogDTO;

/**
 *
 * @author Francesca Parent.
 * The Servlet in charge of the GPS Tracking page.
 */
public class GpsServlet extends HttpServlet {

    /**
     * Writes the HTML code for the webpage. Prevents operators from viewing the page.
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String userRole = (String) session.getAttribute("userRole");

        if ("operator".equalsIgnoreCase(userRole)) {
            try (PrintWriter out = res.getWriter()) {
                out.println("<!DOCTYPE html><html><head>");
                out.println("<title>Register New Vehicle</title>");
                out.println("<link rel='stylesheet' href='assets/styles.css'>");
                out.println("</head><body>");
                out.println("<center>");
                out.println("<div class=\"border-white\">");
                out.println("<h1 class=\"title\">ACCESS DENIED</h1>");
                out.println("<p style='color:red; font-size: 18px;'>Not Authorized</p>");
                out.println("<p>Operators are not allowed to access vehicle registration.</p>");
                out.println("<div class='button-con'>");
                out.println("<form action=\"controller\" method=\"GET\">");
                out.println("<button type=\"submit\" value=\"Return\">Back</button>");
                out.println("</form>");
                out.println("</div>");
                out.println("</center>");
                out.println("</body></html>");
            }
        }

        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        try {
            List<GpsDTO> gpsInfo = ptfmsBusinessLogic.getDetailedGps();
            req.setAttribute("gpsInfo", gpsInfo);
            req.getRequestDispatcher("/WEB-INF/views/Gps.jsp").forward(req, res);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes the HTML for the webpage and obtains stats from the GPS table in
     * the database. Prevents operators from viewing the page. Allows operators
     * to fill in form data, adding a row to the GPS table in the database.
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String userRole = (String) session.getAttribute("userRole");

        if ("operator".equalsIgnoreCase(userRole)) {
            try (PrintWriter out = res.getWriter()) {
                out.println("<!DOCTYPE html><html><head>");
                out.println("<title>Register New Vehicle</title>");
                out.println("<link rel='stylesheet' href='assets/styles.css'>");
                out.println("</head><body>");
                out.println("<center>");
                out.println("<div class=\"border-white\">");
                out.println("<h1 class=\"title\">ACCESS DENIED</h1>");
                out.println("<p style='color:red; font-size: 18px;'>Not Authorized</p>");
                out.println("<p>Operators are not allowed to access vehicle registration.</p>");
                out.println("<div class='button-con'>");
                out.println("<form action=\"controller\" method=\"GET\">");
                out.println("<button type=\"submit\" value=\"Return\">Back</button>");
                out.println("</form>");
                out.println("</div>");
                out.println("</center>");
                out.println("</body></html>");
            }
        }

        try {
            GpsDTO g = new GpsDTO();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            PTFMSBusinessLogic pbl = new PTFMSBusinessLogic();
            int sid = Integer.parseInt(req.getParameter("STAFFID"));
            int vid = Integer.parseInt(req.getParameter("VEHICLEID"));
            String slocation = req.getParameter("STARTLOCATION");
            LocalDateTime stime = LocalDateTime.parse(req.getParameter("STARTTIME"), format);
            LocalDateTime etime = LocalDateTime.parse(req.getParameter("ENDTIME"), format);
            String elocation = req.getParameter("ENDLOCATION");
            LocalDateTime setime = LocalDateTime.parse(req.getParameter("SCHEDULEDENDTIME"), format);
            String notes = req.getParameter("NOTES");

            g.setStaffID(sid);
            g.setVehicleID(vid);
            g.setStartingLocation(slocation);
            g.setStartTime(stime);
            g.setEndTime(etime);
            g.setEndingLocation(elocation);
            g.setScheduledEndTime(setime);
            g.setNotes(notes);

            pbl.registerGps(g);

            try {
                List<GpsDTO> gpsInfo = pbl.getDetailedGps();
                req.setAttribute("gpsInfo", gpsInfo);
                req.getRequestDispatcher("/WEB-INF/views/Gps.jsp").forward(req, res);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } catch (NumberFormatException n) {
            PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
            try {
                List<FuelReportDTO> fuelReports = ptfmsBusinessLogic.getFuelReport();
                List<MaintenanceLogDTO> maintenanceLogs = ptfmsBusinessLogic.getAllLogs();
                List<transferobjects.reports.OperatorPerformanceDTO> operatorPerformance = ptfmsBusinessLogic.getOperatorPerformance();
                List<CostReportDTO> costReports = ptfmsBusinessLogic.getCostReport();

                req.setAttribute("fuelReports", fuelReports);
                req.setAttribute("maintenanceLogs", maintenanceLogs);
                req.setAttribute("operatorPerformance", operatorPerformance);
                req.setAttribute("costReports", costReports);
                req.getRequestDispatcher("/WEB-INF/views/dashboards/Dashboard.jsp").forward(req, res);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
