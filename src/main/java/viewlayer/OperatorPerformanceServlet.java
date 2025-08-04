/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewlayer;

import dataaccesslayer.OperatorPerformanceDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.reports.OperatorPerformanceDTO;

/**
 *
 * @author A
 */
public class OperatorPerformanceServlet extends HttpServlet {
    private OperatorPerformanceDao dao = new OperatorPerformanceDao();
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<OperatorPerformanceDTO> performanceList = dao.getOperatorPerformance();
            req.setAttribute("performanceList", performanceList);
            req.getRequestDispatcher("/dashboard.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException("Database error fetching operator performance", e);
        }
    }
}
