<%@page import="transferobjects.reports.BreakLogDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    // get staff name and id from request attributes
    String staffName = (String) request.getAttribute("staffName");
    Integer staffID = (Integer) request.getAttribute("staffID");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Break History</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
    </head>
    <body>
        <!-- navigation bar with home button -->
        <nav class="navbar">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <button type="submit">Home</button>
            </form>
        </nav>

        <div class="container">
            <h1>Viewing Operator Break Log </h1>
            <h2>Name: <%= staffName%></h2>
            <h2>Staff ID: <%= staffID%></h2>

            <!-- form for submitting break actions -->
            <form method="post" action="${pageContext.request.contextPath}/breakHistory">
                <input type="hidden" name="staffID" value="<%= staffID%>">
                <button type="submit" name="action" value="CLOCK_IN">Clock In</button>
                <button type="submit" name="action" value="BREAK_START">Start Break</button>
                <button type="submit" name="action" value="BREAK_END">End Break</button>
                <button type="submit" name="action" value="CLOCK_OUT">Clock Out</button>
            </form>
        </div>

        <hr class="line-page">

        <div class="container">
            <h3>Break History</h3>
            <table border="1">
                <tr>
                    <th>Action</th>
                    <th>Timestamp</th>
                </tr>
                <%
                    // retrieve break log list from request attribute
                    List<BreakLogDTO> breakLogs = (List<BreakLogDTO>) request.getAttribute("breakLogs");
                    if (breakLogs != null && !breakLogs.isEmpty()) {
                        for (BreakLogDTO log : breakLogs) {
                %>
                <tr>
                    <td>
                        <%
                            // map action codes to user-friendly display text
                            String action = log.getAction();
                            String displayText;
                            if ("BREAK_START".equals(action)) {
                                displayText = "On Break";
                            } else if ("BREAK_END".equals(action)) {
                                displayText = "Break Ended";
                            } else if ("CLOCK_IN".equals(action)) {
                                displayText = "Clocked In";
                            } else if ("CLOCK_OUT".equals(action)) {
                                displayText = "Clocked Out";
                            } else {
                                displayText = action;
                            }
                        %>
                        <%= displayText%>
                    </td>
                    <!-- display timestamp -->
                    <td><%= log.getTimestamp()%></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="2">no break history found.</td></tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
