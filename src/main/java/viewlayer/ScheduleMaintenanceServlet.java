package viewlayer;

import businesslayer.PTFMSBusinessLogic;
import transferobjects.reports.ComponentDTO;
import transferobjects.reports.GpsDTO;
import transferobjects.reports.MaintenanceLogDTO;
import transferobjects.staff.StaffDTO;
import transferobjects.vehicles.VehicleDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Schedule maintenance servlet that handles the creation and management of new maintenance schedules for vehicle components. Provides role-based access control and form processing for maintenance scheduling.
 * 
 * @author Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class ScheduleMaintenanceServlet extends HttpServlet {

    /**
     * processRequest generates the maintenance scheduling form page with role-based access control. Shows scheduling form for transit managers with component and vehicle details, staff selection, and maintenance parameters.
     * @author Lily S.
     * @param request, request
     * @param response, response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
//      grab user role stored in sesh
        String userRole = (String) session.getAttribute("userRole");

//      get passed in ids from the previous page (depending on what schedule button they clicked) and use businesslogic to return the correct name of the db entry
        int componentId = Integer.parseInt(request.getParameter("componentid"));
        int vehicleId = Integer.parseInt(request.getParameter("vehicleid"));
        String vehicleName = grabVehicleName(vehicleId);
        String componentName = grabComponentName(componentId);
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        List<StaffDTO> allStaff = ptfmsBusinessLogic.getAllStaff();
        List<GpsDTO> allGps = ptfmsBusinessLogic.getAllGps();

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Maintenance Alerts</title>");
            out.println("<link rel='stylesheet' href='assets/styles.css'>");
            out.println("</head><body>");
            out.println("<center>");

            String registrationSuccessMsg = (String) request.getAttribute("registrationSuccessMsg");
            if (registrationSuccessMsg != null) {
                out.println("<center>");
                out.println("<p style='color:green;'>" + registrationSuccessMsg + "</p>");
                out.println("</center>");
            }

            if ("operator".equalsIgnoreCase(userRole)) {
                out.println("<h1 class=\"title\">ACCESS DENIED</h1>");
                out.println("<p style='color:red; font-size: 18px;'>Not Authorized</p>");
                out.println("<p>How did you even get here? Did you type the URL in directly? I admire the effort but operators are not allowed to access maintenance scheduling.</p>");
                out.println("<div class='button-con'>");
                out.println("<form action=\"controller\" method=\"GET\">");
                out.println("<button type=\"submit\" value=\"Return\">Back</button>");
                out.println("</form>");
                out.println("</div>");

            } else if ("transitmanager".equalsIgnoreCase(userRole)) {
                out.println("<div class=\"border-white\">");
                out.println("<h1 class=\"title\">SCHEDULE MAINTENANCE</h1>");
                out.println("<h2 class=\"subtitle\">Scheduling a new maintenance:</h2>");
                out.println("<div class=\"con\">");
                out.println("<p>Vehicle: " + vehicleName + "</p>");
                out.println("<p>Component: " + componentName + "</p>");
                out.println("</div>");

                out.println("<form action='schedulemaintenance' method='POST' class='register-form'>");
                // hidden inputs just to use for later add business logic call
                out.println("<input name='componentid' hidden value='" + componentId + "'>");
                out.println("<input name='vehicleid' hidden value='" + vehicleId + "'>");
                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Assigned Staff:</label>");
                out.println("<select name='staffid' required>");
                out.println("<option value=''>Select Staff Member</option>");
//          loop thru staff list created through getAllStaff() businesslayer dao call
                for (StaffDTO staff : allStaff) {
                    int staffId = staff.getStaffId();
                    String staffName = staff.getFirstName() + " " + staff.getLastName();
                    out.println("<option value='" + staffId + "'>" + staffName + "</option>");
                }
                out.println("</select>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Related GPS Information:</label>");
                out.println("<select name='gpsid' required>");
                out.println("<option value=''>Select Gps Location</option>");
                for (GpsDTO gps : allGps) {
                    int gpsId = gps.getGpsID();
                    String gpsRoute = gps.getStartingLocation() + " to " + gps.getEndingLocation();
                    out.println("<option value='" + gpsId + "'>" + gpsRoute + "</option>");
                }
                out.println("</select>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Hours of Wear:</label>");
                out.println("<input type='number' name='usageamt' min='0' required>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Status:</label>");
                out.println("<select name='status' required>");
                out.println("<option value=''>Select Status</option>");
                out.println("<option value='Pending'>Pending</option>");
                out.println("<option value='In Progress'>In Progress</option>");
                out.println("<option value='Completed'>Completed</option>");
                out.println("</select>");
                out.println("</div>");

                out.println("<div class='form-con'>");
                out.println("<label class='form-label'>Maintenance Notes:</label>");
                out.println("<textarea name='notes' rows='3' cols='50' placeholder='Enter maintenance details'></textarea>");
                out.println("</div>");

                out.println("<div class='button-con'>");
                out.println("<button type='submit' name='commitCheck' value='Commit'>Schedule</button>");
                out.println("</form>");
                out.println("<form action=\"componentmaintenance\" method=\"GET\">");
                out.println("<button type=\"submit\" value=\"return\">Return</button>");
                out.println("</form>");
                out.println("</div>");

                out.println("</div>");
                out.println("</center>");
                out.println("</body></html>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * grabVehicleName retrieves the vehicle number/name from the database using the provided vehicle ID via business logic layer.
     * @author Lily S.
     * @param id, vehicle ID to look up
     * @return String, vehicle number/name from the database
     */
    protected String grabVehicleName(int id) {
        VehicleDTO vehicleDTO =  new VehicleDTO();
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        vehicleDTO = ptfmsBusinessLogic.getVehicleByID(id);
        return vehicleDTO.getVehicleNumber();
    }

    /**
     * grabComponentName retrieves the component name from the database using the provided component ID via business logic layer.
     * @author Lily S.
     * @param id, component ID to look up
     * @return String, component name from the database
     */
    protected String grabComponentName(int id) {
        ComponentDTO componentDTO = new ComponentDTO();
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        componentDTO = ptfmsBusinessLogic.getComponentByID(id);
        return componentDTO.getComponentName();
    }

    /**
     * commitNewMaintenance processes the maintenance scheduling form data by creating a MaintenanceLogDTO object and calling business logic to add the maintenance entry to the database.
     * @author Lily S.
     * @param req, request object containing maintenance form data
     * @param res, response object
     */
    protected void commitNewMaintenance(HttpServletRequest req, HttpServletResponse res) {
        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        MaintenanceLogDTO maintenance = new MaintenanceLogDTO();

        maintenance.setStaffID(Integer.parseInt(req.getParameter("staffid")));
        maintenance.setGpsID(Integer.parseInt(req.getParameter("gpsid")));
        maintenance.setVehicleID(Integer.parseInt(req.getParameter("vehicleid")));
        maintenance.setComponentID(Integer.parseInt(req.getParameter("componentid")));
        maintenance.setUsageAmt(Double.parseDouble(req.getParameter("usageamt")));
        maintenance.setStatus(req.getParameter("status"));
        maintenance.setNotes(req.getParameter("notes"));
        maintenance.setComponentName(grabComponentName(Integer.parseInt(req.getParameter("componentid"))));

        ptfmsBusinessLogic.addMaintenance(maintenance);

        req.setAttribute("registrationSuccessMsg", "New maintenance log successfully added. Please refer to the dashboard to see all scheduling entries.");


    }

    /**
     * doGet, overridden method corresponding to HTTP GET, simply calls processRequest while feeding in parameters HTTPServletRequest req, HttpServletResponse res
     * @author Lily S.
     * @param req, request
     * @param res, response
     * @throws ServletException servlet related errors
     * @throws IOException input-output related errors
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req, res);
    }

    /**
     * doPost, overridden method corresponding to HTTP POST, handles maintenance scheduling form submission by checking for commit action and processing new maintenance entries.
     * @author Lily S.
     * @param req, request
     * @param res, response
     * @throws ServletException servlet related errors
     * @throws IOException input-output related errors
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String commitCheck = req.getParameter("commitCheck");
        if("Commit".equals(commitCheck)) {
            commitNewMaintenance(req, res);
            processRequest(req, res);
        } else {
            processRequest(req, res);
        }


    }

}