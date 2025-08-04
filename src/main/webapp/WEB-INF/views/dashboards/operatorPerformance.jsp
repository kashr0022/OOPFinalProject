<%-- 
    Document   : operatorPerformance
    Created on : Aug 4, 2025, 1:39:42 a.m.
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, transferobjects.staff.OperatorPerformanceDTO" %>
<html>
<head>
    <title>Operator Performance</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h2>Operator Efficiency (Hours Worked)</h2>
    <canvas id="performanceChart" width="800" height="400"></canvas>

    <%
        List<OperatorPerformanceDTO> performanceList = (List<OperatorPerformanceDTO>) request.getAttribute("performanceList");
    %>

    <script>
        const labels = [<%
            for (int i = 0; i < performanceList.size(); i++) {
                out.print("\"" + performanceList.get(i).getOperatorName() + "\"");
                if (i < performanceList.size() - 1) out.print(", ");
            }
        %>];

        const hoursData = [<%
            for (int i = 0; i < performanceList.size(); i++) {
                out.print(performanceList.get(i).getHoursWorked());
                if (i < performanceList.size() - 1) out.print(", ");
            }
        %>];

        new Chart(document.getElementById("performanceChart"), {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Hours Worked',
                    data: hoursData,
                    backgroundColor: 'rgba(255, 159, 64, 0.6)'
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>

