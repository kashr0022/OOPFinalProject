/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewlayer;

import dataaccesslayer.FuelReportDao;
import dataaccesslayer.MaintenanceLogDao;
import dataaccesslayer.OperatorPerformanceDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.reports.FuelReportDTO;
import transferobjects.reports.MaintenanceLogDTO;

/**
 *
 * @author A
 */
public class DashboardServlet extends HttpServlet {
    private FuelReportDao fuelDao = new FuelReportDao();
    private MaintenanceLogDao maintenanceDao = new MaintenanceLogDao();
    private OperatorPerformanceDao performanceDao = new OperatorPerformanceDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<FuelReportDTO> fuelReports = fuelDao.getFuelReport();
            List<MaintenanceLogDTO> maintenanceLogs = maintenanceDao.getAllLogs();
            List<transferobjects.reports.OperatorPerformanceDTO> operatorPerformance = performanceDao.getOperatorPerformance();

            req.setAttribute("fuelReports", fuelReports);
            req.setAttribute("maintenanceLogs", maintenanceLogs);
            req.setAttribute("operatorPerformance", operatorPerformance);
            req.getRequestDispatcher("/Dashboard.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException("Error loading dashboard data", e);
        }
    }
}

