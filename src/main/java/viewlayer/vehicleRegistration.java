package viewlayer;

import businesslayer.PTFMSBusinessLogic;
import transferobjects.vehicles.VehicleDTO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class vehicleRegistration extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            out.println("<!DOCTYPE html><html><head>");
            out.println("<title>Register New Vehicle</title>");
            out.println("<link rel='stylesheet' href='assets/styles.css'>");
            out.println("</head><body>");
            out.println("<center>");

            String registrationSuccessMsg = (String) req.getAttribute("registrationSuccessMsg");


            if (registrationSuccessMsg != null) {
                out.println("<center>");
                out.println("<p style='color:green;'>" + registrationSuccessMsg + "</p>");
                out.println("</center>");
            }
            out.println("<div class=\"border-white\">");
            out.println("<h1 class=\"title\">VEHICLE REGISTRATION</h1>");
            out.println("<h2 class=\"subtitle\">Adding a new vehicle:</h2>");

            out.println("<form action='vehicleregistration' method='POST' class='register-form'>");

            out.println("<div class='form-con'>");
            out.println("<label class='form-label'>Vehicle Identifier:</label>");
            out.println("<input type='text' name='vehiclenumber' placeholder='e.g., DB001, DET333' required>");
            out.println("</div>");

            out.println("<div class='form-con'>");
            out.println("<label class='form-label'>Vehicle Type:</label>");
            out.println("<select name='vehicletype' required>");
            out.println("<option value=''>Select Vehicle Type</option>");
            out.println("<option value='DieselBus'>Diesel Bus</option>");
            out.println("<option value='DieselElectricTrain'>Diesel-Electric Train</option>");
            out.println("<option value='ElectricLightRail'>Electric Light Rail</option>");
            out.println("</select>");
            out.println("</div>");

            out.println("<div class='form-con'>");
            out.println("<label class='form-label'>Consumption Rate:</label>");
            out.println("<input type='number' name='consumptionrate' min='0' required>");
            out.println("</div>");

            out.println("<div class='form-con'>");
            out.println("<label class='form-label'>Consumption Unit:</label>");
            out.println("<select name='consumptionunit' required>");
            out.println("<option value=''>Select Fuel Unit</option>");
            out.println("<option value='mpg'>Miles per Gallon (mpg)</option>");
            out.println("<option value='L/km'>Liters per Kilometer (L/km)</option>");
            out.println("<option value='kWh/hr'>Kilowatt Hours per Hour (kWh/hr)</option>");
            out.println("</select>");
            out.println("</div>");

            out.println("<div class='form-con'>");
            out.println("<label class='form-label'>Maximum Passengers:</label>");
            out.println("<input type='number' name='maxpassengers' min='1' required>");
            out.println("</div>");

            out.println("<div class='form-con'>");
            out.println("<label class='form-label'>Active Route:</label>");
            out.println("<input type='text' name='activeroute' required>");
            out.println("</div>");

            out.println("<div class='button-con'>");
            out.println("<button type='submit' name='registerCheck' value='RegisterVehicle'>Register</button>");
            out.println("</form>");
            out.print("<form action=\"controller\" method=\"GET\">");
            out.print("<button type=\"submit\" value=\"Return\">Return</button>");
            out.print("</form>");
            out.println("</div>");
            out.println("</div>");

            out.println("</center>");
            out.println("</body></html>");
        }


    }
    protected void registerVehicle(HttpServletRequest request, HttpServletResponse response) {
        String vehicleNumber = request.getParameter("vehiclenumber");
        String vehicleType = request.getParameter("vehicletype");
        double consumptionRate =  Double.parseDouble(request.getParameter("consumptionrate"));
        String consumptionUnit = request.getParameter("consumptionunit");
        int  maxPassengers = Integer.parseInt(request.getParameter("maxpassengers"));
        String activeRoute = request.getParameter("activeroute");

        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setVehicleNumber(vehicleNumber);
        vehicleDTO.setVehicleType(vehicleType);
        vehicleDTO.setConsumptionRate(consumptionRate);
        vehicleDTO.setConsumptionUnit(consumptionUnit);
        vehicleDTO.setMaxPassengers(maxPassengers);
        vehicleDTO.setActiveRoute(activeRoute);

        PTFMSBusinessLogic ptfmsBusinessLogic = new PTFMSBusinessLogic();
        ptfmsBusinessLogic.registerVehicle(vehicleDTO);
        request.setAttribute("registrationSuccessMsg", "Registration of " + vehicleNumber + " successful.");

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        registerVehicle(request, response);
        processRequest(request, response);
    }
}
