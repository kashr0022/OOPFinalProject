<%-- 
    Document   : TransitMaintenance
    Created on : Aug 4, 2025, 12:57:28 a.m.
    Author     : A
--%>

<%@page import="transferobjects.reports.CostReportDTO"%>
<%@page import="dataaccesslayer.PTFMSDao"%>
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
        List<CostReportDTO> costReports = (List<CostReportDTO>) request.getAttribute("costReports");
    %>
    <body>
        <nav class="navbar">
            <form action="${pageContext.request.contextPath}/controller" method="GET">
                <button type="submit">Back</button>
            </form>
        </nav>
        <div class="container">
            <h1>Transit Manager Dashboard</h1>
        </div>
            <hr class="line-page">
        <div class="container">
            <h2>Operator Performance</h2>
            <table border = "1">
                <tr>
                    <th>Staff ID</th>
                    <th>Operator Name</th>
                    <th>On-Time Arrival Rate (%)</th>
                    <th>Average Route Duration (min)</th>
                    <th>Total Hours Worked</th>
                </tr>
                <tbody>
                    <%   if (operatorPerformance != null) {
                            for (OperatorPerformanceDTO op : operatorPerformance) {
                    %>
                    <tr>
                        <td><%= op.getStaffID() %></td>
                        <td><%= op.getFirstName() + " " + op.getLastName()%></td>
                        <td><%= String.format("%.2f", op.getOnTimeRate())%></td>
                        <td><%= String.format("%.1f", op.getAvgRouteDuration())%></td>
                        <td><%= String.format("%.1f", op.getTotalHoursWorked())%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div class="container">
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
                        <td><%= String.format("%.2f km", fuel.getDistanceTraveled())%></td>
                        <td><%= String.format("%.2f %s", fuel.getFuelEfficiency(), fuel.getFuelEfficiencyUnit())%></td>
                        <td><%= fuel.getDate()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div class="container">
            <h2>Maintenance Alerts</h2>
            <table border = "1">
                <tr>
                    <th>Log ID</th>
                    <th>Vehicle ID</th>
                    <th>Type</th>
                    <th>Component</th>
                    <th>Usage Hours</th>
                    <th>Diagnostics</th>
                    <%-- New heading added for Notes column - lily--%>
                    <th>Notes</th>
                    <th>Action</th>
                    <th>Timestamp</th>
                </tr>
                <tbody>
                    <%
                        if (logs != null) {
                            for (MaintenanceLogDTO log : logs) {
                    %>
                    <tr>
                        <td><%= log.getLogID()%></td>
                        <td><%= log.getVehicleID()%></td>
                        <td><%= log.getVehicleType()%></td>
                        <td><%= log.getComponentName()%></td>
                        <td><%= log.getUsageAmt()%></td>
                        <td><%= log.getDiagnostics()%></td>
                        <%-- Another dto get added for Notes column - lily--%>
                        <td><%= log.getNotes() %></td>
                        <td>
                            <% if ("ALERT".equalsIgnoreCase(log.getStatus())) {%>
                            <a href="scheduleMaintenance?logID=<%= log.getLogID()%>">Schedule</a>
                            <% } else {%>
                            <%= log.getStatus()%>
                            <% } %>
                        </td>
                        <td><%= log.getDate()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
                <div class="container">
            <h2>Cost Report</h2>
            <table border = "1">
                <tr>
                    
                    <th>Vehicle ID</th>
                    <th>Type</th>
                    <th>Report Type</th>
                    <th>Description</th>
                    <th>Cost</th>
                    <th>Report Date</th>
                </tr>
                <tbody>
                    <%
                        if (costReports != null) {
                            for (CostReportDTO cost : costReports) {
                    %>
                    <tr>
                        <td><%= cost.getVehicleID()%></td>
                        <td><%= cost.getVehicleType()%></td>
                        <td><%= cost.getReportType()%></td>
                        <td><%= cost.getDescription()%></td>
                        <td><%= String.format("$%.2f", cost.getCost())%></td>
                        <td><%= cost.getDate()%></td>
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
