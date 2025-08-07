<%-- 
    Document   : Gps
    Created on : Aug 6, 2025, 2:06:52 PM
    Author     : franm
--%>

<%@page import="transferobjects.reports.GpsDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
        <title>GPS Tracking Page</title>
    </head>
    <%
        List<GpsDTO> gpsInfo = (List<GpsDTO>) request.getAttribute("gpsInfo");
    %>
    <body>
        <nav class="navbar">
            <form action="${pageContext.request.contextPath}/controller" method="GET">
                <button type="submit">Homepage</button>
            </form>
                <form action="${pageContext.request.contextPath}/dashboard" method="GET">
                <button type="submit">Dashboard</button>
            </form>
        </nav>
         <hr class="line-page">
        <div class="container">
            <h2>GPS Tracking</h2>
            <table border = "1">
                <tr>
                    <th>GPS ID</th>
                    <th>Staff ID</th>
                    <th>Vehicle ID</th>
                    <th>Starting Location</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Ending Location</th>
                    <th>Scheduled End Time</th>
                    <th>Notes</th>
                </tr>
                <tbody>
                    <%
    if (gpsInfo != null) {
        for (GpsDTO gps : gpsInfo) {
%>
                    <tr>
                        <td><%= gps.getGpsID() %></td>
                        <td><%= gps.getStaffID() %></td>
                        <td><%= gps.getVehicleID() %></td>
                        <td><%= gps.getStartLocation() %></td>
                        <td><%= gps.getStartTime() %></td>
                        <td><%= gps.getEndTime() %></td>
                        <td><%= gps.getEndLocation() %></td>
                        <td><%= gps.getScheduledEndTime() %></td>
                        <td><%= gps.getNotes() %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
                <div class="border-white">
                    <h1 class="title">Log GPS Times</h1>
                    <h2 class="subtitle">Incorrect formatting will boot you back to the dashboard.</h2>
                    <form name='action' value="GpsServlet" method="post" class='register-form'>
                        <input type="hidden" name="action" value="GpsServlet">
                        <div class='form-con'>
                            <label class='form-label'>Staff Identifier:</label>
                            <input type='text' name='STAFFID' placeholder='Must be an ID that exists'>
                        </div>
                        <div class='form-con'>
                            <label class='form-label'>Vehicle Identifier:</label>
                            <input type='text' name='VEHICLEID' placeholder='Must be an ID that exists'>
                        </div>
                        <div class='form-con'>
                            <label class='form-label'>Starting Location:</label>
                            <input type='text' name='STARTLOCATION'>
                        </div>
                        <div class='form-con'>
                            <label class='form-label'>Start Time:</label>
                            <input type='text' name='STARTTIME' placeholder='Use the format yyyy-MM-dd HH:mm:ss'>
                        </div>
                        <div class='form-con'>
                            <label class='form-label'>End Time:</label>
                            <input type='text' name='ENDTIME' placeholder='Use the format yyyy-MM-dd HH:mm:ss'>
                        </div>
                        <div class='form-con'>
                            <label class='form-label'>Ending Location:</label>
                            <input type='text' name='ENDLOCATION'>
                        </div>
                        <div class='form-con'>
                            <label class='form-label'>Scheduled End Time:</label>
                            <input type='text' name='SCHEDULEDENDTIME' placeholder='Use the format yyyy-MM-dd HH:mm:ss'>
                        </div>
                        <div class='form-con'>
                            <label class='form-label'>Notes:</label>
                            <input type='text' name='NOTES'>
                        </div>
                        <div class='button-con'>
                            <button type='submit' name='gpsdata' method='post'>Submit</button>
                        </div>
                    </form>
                    
                </div>
    </body>
</html>
