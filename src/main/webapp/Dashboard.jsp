<%-- 
    Document   : TransitMaintenance
    Created on : Aug 4, 2025, 12:57:28 a.m.
    Author     : A
--%>

<%@page import="dataaccesslayer.FuelReportDao"%>
<%@page import="transferobjects.reports.MaintenanceLogDTO"%>
<%@page import="transferobjects.reports.FuelReportDTO"%>
<%@page import="transferobjects.reports.OperatorPerformanceDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
        <title>Transit Manager Dashboard</title>
    </head>
    <%
        List<MaintenanceLogDTO> logs = (List<MaintenanceLogDTO>) request.getAttribute("maintenanceLogs");
        List<OperatorPerformanceDTO> operatorPerformance = (List<OperatorPerformanceDTO>) request.getAttribute("operatorPerformance");
        List<FuelReportDTO> fuelReports = (List<FuelReportDTO>) request.getAttribute("fuelReports");
        request.setAttribute("fuelReports", fuelReports);

    %>
    <body>
        <div class="container">
            <h1>Transit Manager Dashboard</h1>
            <h2>Operator Performance</h2>
            <table border = "1">
                <tr>
                    <th>Operator Name</th>
                    <th>On-Time Arrival Rate (%)</th>
                    <th>Average Route Duration (min)</th>
                    <th>Total Hours Worked</th>
                </tr>
                <tbody>
                    <%
                        if (operatorPerformance != null) {
                            for (OperatorPerformanceDTO op : operatorPerformance) {
                    %>
                    <tr>
                        <td><%= op.getFirstName() + " " + op.getLastName()%></td>
                       
                        <%--<td><%= String.format("%.2f", op.getOnTimeRate())%></td>--%>
                        <td><%= String.format("%.1f", op.getAvgRouteDuration())%></td>
                        <td><%= String.format("%.1f", op.getTotalHoursWorked())%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>

            <h2>Fuel Report</h2>
            <table border = "1">
                <tr>
                    <th>Vehicle ID</th>
                    <th>Type</th>
                    <th>Fuel/Energy Type</th>
                    <th>Fuel Consumed</th>
                    <th>Distance Traveled</th>
                    <th>Fuel Efficiency</th>
                    <th>Report Date</th>
                </tr>
                <tbody>
                    <%
                        if (fuelReports != null) {
                            for (FuelReportDTO fuel : fuelReports) {
                    %>
                    <tr>
                        <td><%= fuel.getVehicleID()%></td>
                        <td><%= fuel.getVehicleType()%></td>
                        <td><%= fuel.getFuelType()%></td>
                        <td><%= String.format("%.2f", fuel.getFuelConsumed())%></td>
                        <td><%= String.format("%.2f", fuel.getDistanceTraveled())%></td>
                        <td><%= String.format("%.2f", fuel.getFuelEfficiency())%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>

            <h2>Maintenance Alerts</h2>
            <table border = "1">
                <tr>
                    <th>Vehicle ID</th>
                    <th>Type</th>
                    <th>Component</th>
                    <th>Usage Hours</th>
                    <th>Diagnostics</th>
                    <th>Timestamp</th>
                    <th>Action</th>
                </tr>
                <tbody>
                    <%
                        if (logs != null) {
                            for (MaintenanceLogDTO log : logs) {
                    %>
                    <tr>
                        <td><%= log.getLogID()%></td>
                        <td><%= log.getVehicleID()%></td>
                        <td><%= log.getComponentID()%></td>
                        <td><%= log.getUsageAmt()%></td>
                        <td><%= log.getStatus()%></td>
                        <td><%= log.getDate()%></td>
                        <td>
                            <%
                            if ("ALERT".equalsIgnoreCase(log.getStatus())) {%>
                            <a href="scheduleMaintenance?logID=<%= log.getLogID()%>">Schedule</a>
                            %> } else { %>
                            N/A
                            <% }%>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
