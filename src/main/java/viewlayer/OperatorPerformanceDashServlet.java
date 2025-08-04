/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewlayer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataaccesslayer.OperatorDao;
import transferobjects.staff.OperatorPerformanceDTO;

/**
 *
 * @author A
 */
public class OperatorPerformanceDashServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            OperatorDao dao = new OperatorDao();
            List<OperatorPerformanceDTO> performanceList = dao.getOperatorPerformance();
            req.setAttribute("performanceLIst", performanceList);
            req.getRequestDispatcher("WEB-INF/views/dashboard/operatorPerformance.jsp").forward(req, res);
        } catch (SQLException e) {
            e.printStackTrace();
            res.sendError(500, "Database error");
        }
    }
}
