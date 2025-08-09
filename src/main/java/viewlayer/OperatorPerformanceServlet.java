package viewlayer;

import businesslayer.PTFMSBusinessLogic;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.reports.OperatorPerformanceDTO;

/**
 * Servlet responsible for retrieving operator performance data
 * 
 * Handles HTTP GET requests to fetch the list of OperatorPerformanceDTO
 * 
 * @author Khairunnisa Ashri
 * @version 1.0
 * @since JDK 21.0.4
 */
public class OperatorPerformanceServlet extends HttpServlet {

    /**
     * Handles the HTTP GET request to retrieve operator performance data
     *
     * @author Khairunnisa Ashri
     * @param req the HttpServletRequest
     * @param res the HttpServletResponse
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        try {
            List<OperatorPerformanceDTO> performanceList = ptfmsBusinessLogic.getOperatorPerformance();
            req.setAttribute("performanceList", performanceList);
            req.getRequestDispatcher("/WEB-INF/views/dashboards/Dashboard.jsp").forward(req, res);
        } catch (RuntimeException e) {
            throw new ServletException("Database error fetching operator performance", e);
        }
    }
}
