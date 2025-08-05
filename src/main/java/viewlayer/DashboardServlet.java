package viewlayer;

import businesslayer.PTFMSBusinessLogic;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.reports.CostReportDTO;
import transferobjects.reports.FuelReportDTO;
import transferobjects.reports.MaintenanceLogDTO;

/**
 *
 * @author Khairunnisa Ashri
 */
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

