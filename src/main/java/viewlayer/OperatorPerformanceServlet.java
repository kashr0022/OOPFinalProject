
package viewlayer;

import businesslayer.PTFMSBusinessLogic;
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
 * @author Khairunnisa Ashri
 */
public class OperatorPerformanceServlet extends HttpServlet {

    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        try {
            List<OperatorPerformanceDTO> performanceList = ptfmsBusinessLogic.getOperatorPerformance();
            req.setAttribute("performanceList", performanceList);
            req.getRequestDispatcher("/dashboard.jsp").forward(req, res);
        } catch (RuntimeException e) {
            throw new ServletException("Database error fetching operator performance", e);
        }
    }
}
