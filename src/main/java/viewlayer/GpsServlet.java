
package viewlayer;

import businesslayer.PTFMSBusinessLogic;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.reports.CostReportDTO;
import transferobjects.reports.FuelReportDTO;
import transferobjects.reports.GpsDTO;
import transferobjects.reports.MaintenanceLogDTO;

/**
 *
 * @author Francesca Parent
 */
public class GpsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        try {
            List<GpsDTO> gpsInfo = ptfmsBusinessLogic.getDetailedGps();
            req.setAttribute("gpsInfo", gpsInfo);
            req.getRequestDispatcher("/WEB-INF/views/Gps.jsp").forward(req, res);
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
            try {
            GpsDTO g = new GpsDTO();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
            }
            catch (NumberFormatException n) {
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
            req.getRequestDispatcher("/Dashboard.jsp").forward(req, res);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
            }

        }
}