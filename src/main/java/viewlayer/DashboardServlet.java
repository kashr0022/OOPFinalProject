package viewlayer;

import dataaccesslayer.CostReportDao;
import dataaccesslayer.FuelReportDaoImpl;
import dataaccesslayer.MaintenanceLogDao;
import dataaccesslayer.OperatorPerformanceDao;
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
    private FuelReportDaoImpl fuelDao = new FuelReportDaoImpl();
    private MaintenanceLogDao maintenanceDao = new MaintenanceLogDao();
    private OperatorPerformanceDao performanceDao = new OperatorPerformanceDao();
    private CostReportDao costReportDao = new CostReportDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<FuelReportDTO> fuelReports = fuelDao.getFuelReport();
            List<MaintenanceLogDTO> maintenanceLogs = maintenanceDao.getAllLogs();
            List<transferobjects.reports.OperatorPerformanceDTO> operatorPerformance = performanceDao.getOperatorPerformance();
            List<CostReportDTO> costReports = costReportDao.getCostReport();
            
            req.setAttribute("fuelReports", fuelReports);
            req.setAttribute("maintenanceLogs", maintenanceLogs);
            req.setAttribute("operatorPerformance", operatorPerformance);
            req.setAttribute("costReports", costReports);
            req.getRequestDispatcher("/Dashboard.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException("Error loading dashboard data", e);
        }
    }
}

