<%@ page import="transferobjects.reports.CostReportDTO" %>
<%@ page import="transferobjects.reports.MaintenanceLogDTO" %>
<%@ page import="transferobjects.reports.FuelReportDTO" %>
<%@ page import="transferobjects.reports.OperatorPerformanceDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
        <title>User Dashboard</title>
    </head>
    <body>
        <%
            // get staff name and user role from session if available
            String staffName = (session != null) ? (String) session.getAttribute("staffName") : null;
            String userRole = (session != null) ? (String) session.getAttribute("userRole") : null;

            // retrieve data lists set as request attributes by servlet
            List<MaintenanceLogDTO> logs = (List<MaintenanceLogDTO>) request.getAttribute("maintenanceLogs");
            List<OperatorPerformanceDTO> operatorPerformance = (List<OperatorPerformanceDTO>) request.getAttribute("operatorPerformance");
            List<FuelReportDTO> fuelReports = (List<FuelReportDTO>) request.getAttribute("fuelReports");
            List<CostReportDTO> costReports = (List<CostReportDTO>) request.getAttribute("costReports");

            // define threshold for fuel consumption alerts
            double fuelConsumedThreshold = 100.0;

            // flag for user role check
            boolean isTransitManager = "transitmanager".equalsIgnoreCase(userRole);
        %>

        <!-- navigation bar with home button -->
        <nav class="navbar">
            <form action="${pageContext.request.contextPath}/controller" method="GET">
                <button type="submit">Home</button>
            </form>
        </nav>

        <div class="container">
            <h1>User Dashboard</h1>
            <div class="greeting">
                <!-- greet logged-in user or guest -->
                <h2>Hello, <%= staffName != null ? staffName : "Guest"%>!</h2>
            </div>
        </div>

        <hr class="line-page">
        <%
            // determine if any fuel report exceeds consumption threshold to trigger alert
            boolean alertNeeded = false;
            if (fuelReports != null) {
                for (FuelReportDTO fuel : fuelReports) {
                    if (fuel.getFuelConsumed() > fuelConsumedThreshold) {
                        alertNeeded = true;
                        break;
                    }
                }
            }
        %>

        <!-- operator performance section, visible only to transit managers -->
        <% if (isTransitManager) { %>
        <div class="container">
            <h2>Operator Performance</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Staff ID</th>
                        <th>Operator Name</th>
                        <th>On-Time Arrival Rate (%)</th>
                        <th>Average Route Duration (min)</th>
                        <th>Total Hours Worked</th>
                        <th>Status</th>
                        <th>Break History</th> 
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (operatorPerformance != null) {
                            for (OperatorPerformanceDTO op : operatorPerformance) {
                                // ensure status text is not null, convert to css class friendly format
                                String status = (op.getStatus() != null) ? op.getStatus() : "Unknown";
                                String statusClass = status.replaceAll(" ", "").toLowerCase();
                    %>
                    <tr>
                        <td><%= op.getStaffID()%></td>
                        <td><%= op.getFirstName() + " " + op.getLastName()%></td>
                        <td><%= String.format("%.2f", op.getOnTimeRate())%></td>
                        <td><%= String.format("%.1f", op.getAvgRouteDuration())%></td>
                        <td><%= String.format("%.1f", op.getTotalHoursWorked())%></td>
                        <td class="<%= statusClass%>"><%= status%></td>
                        <td>
                            <!-- button to view break log for operator -->
                            <form action="<%= request.getContextPath()%>/breakHistory" method="get">
                                <input type="hidden" name="staffID" value="<%= op.getStaffID()%>">
                                <button type="submit">View Break Log</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <% } %>

        <!-- fuel report table -->
        <div class="container">
            <h2>Fuel Report</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Vehicle ID</th>
                        <th>Type</th>
                        <th>Fuel/Energy Type</th>
                        <th>Fuel Consumed</th>
                        <th>Distance Traveled</th>
                        <th>Fuel Efficiency</th>
                        <th>Report Date</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (fuelReports != null) {
                            for (FuelReportDTO fuel : fuelReports) {
                                // apply alert-row css if transit manager and fuel consumption high
                                String rowClass = (isTransitManager && fuel.getFuelConsumed() > fuelConsumedThreshold) ? "alert-row" : "";
                    %>
                    <tr class="<%= rowClass%>">
                        <td><%= fuel.getVehicleID()%></td>
                        <td><%= fuel.getVehicleType()%></td>
                        <td><%= fuel.getFuelType()%></td>
                        <td><%= String.format("%.2f", fuel.getFuelConsumed())%></td>
                        <td><%= String.format("%.2f km", fuel.getDistanceTraveled())%></td>
                        <td><%= String.format("%.2f %s", fuel.getFuelEfficiency(), fuel.getFuelEfficiencyUnit())%></td>
                        <td><%= fuel.getDate()%></td>
                    </tr>
                    <%  }
                        } %>
                </tbody>
            </table>
        </div>

        <!-- maintenance log table -->
        <div class="container">
            <h2>Maintenance Log</h2>
            <table border="1">
                <tr>
                    <th>Log ID</th>
                    <th>Vehicle ID</th>
                    <th>Type</th>
                    <th>Component</th>
                    <th>Usage Hours</th>
                    <th>Diagnostics</th>
                    <th>Notes</th>
                    <th>Action</th>
                    <th>Timestamp</th>
                </tr>
                <tbody>
                    <% if (logs != null) {
                            for (MaintenanceLogDTO log : logs) {
                                // apply alert-row css if transit manager and status is 'Schedule'
                                String rowClass = (isTransitManager && "Schedule".equalsIgnoreCase(log.getStatus())) ? "alert-row" : "";
                    %>
                    <tr class="<%= rowClass%>">
                        <td><%= log.getLogID()%></td>
                        <td><%= log.getVehicleID()%></td>
                        <td><%= log.getVehicleType()%></td>
                        <td><%= log.getComponentName()%></td>
                        <td><%= log.getUsageAmt()%></td>
                        <td><%= log.getDiagnostics()%></td>
                        <td><%= log.getNotes()%></td>
                        <td>
                            <% if ("Schedule".equalsIgnoreCase(log.getStatus()) && isTransitManager) { %>
                            <!-- link to component maintenance for scheduled logs -->
                            <a href="componentmaintenance" class="alert-link">Schedule</a>
                            <% } else {%>
                            <%= log.getStatus()%>
                            <% }%>
                        </td>
                        <td><%= log.getDate()%></td>
                    </tr>
                    <%  }
                        } %>
                </tbody>
            </table>
            <br>
            <!-- button to view maintenance alerts -->
            <form action="${pageContext.request.contextPath}/componentmaintenance" method="GET">
                <button type="submit">View Maintenance Alerts</button>
            </form>
        </div>

        <!-- cost report table, shown only to transit managers -->
        <% if (isTransitManager) { %>
        <div class="container">
            <h2>Cost Report</h2>
            <table border="1">
                <tr>
                    <th>Vehicle ID</th>
                    <th>Type</th>
                    <th>Report Type</th>
                    <th>Description</th>
                    <th>Cost</th>
                    <th>Report Date</th>
                </tr>
                <tbody>
                    <% if (costReports != null) {
                            for (CostReportDTO cost : costReports) {%>
                    <tr>
                        <td><%= cost.getVehicleID()%></td>
                        <td><%= cost.getVehicleType()%></td>
                        <td><%= cost.getReportType()%></td>
                        <td><%= cost.getDescription()%></td>
                        <td><%= String.format("$%.2f", cost.getCost())%></td>
                        <td><%= cost.getDate()%></td>
                    </tr>
                    <% }
                        }%>
                </tbody>
            </table>
        </div>
        <% }%>
    </body>
</html>
