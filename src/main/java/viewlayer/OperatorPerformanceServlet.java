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
 * from the business layer and forwarding it to the dashboard JSP
 * for display.
 * 
 * Handles HTTP GET requests to fetch the list of {@link OperatorPerformanceDTO}.
 * 
 * @author Khairunnisa Ashri
 */
public class OperatorPerformanceServlet extends HttpServlet {

    /**
     * Handles the HTTP GET request to retrieve operator performance data.
     * Calls business logic to fetch a list of operator performance DTOs,
     * sets it as a request attribute, and forwards the request to the dashboard JSP.
     *
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
            req.getRequestDispatcher("/dashboard.jsp").forward(req, res);
        } catch (RuntimeException e) {
            throw new ServletException("Database error fetching operator performance", e);
        }
    }
}
