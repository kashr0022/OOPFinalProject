<%-- 
    Document   : AddStaff
    Created on : Jul 31, 2025, 12:13:07 a.m.
    Author     : A
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Add Author</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align:center;">Add Author</h1>
            <!-- form for inputting author details -->
            <form action="${pageContext.request.contextPath}/controller?action=addForm" method="POST">
                <input type="hidden" name="action" value="add">
                <label>First Name:
                    <input type="text" name="firstName" required>
                </label><br>
                <label>Last Name:
                    <input type="text" name="lastName" required>
                </label>
                <br>
                <button type="submit" name="action" value="add">Add Author</button>
                <div style="text-align:left; margin-bottom:1rem;">
                    <a href="${pageContext.request.contextPath}"
                       class="btn">Back to Home</a>
                </div>
                <p>Program by: Khairunnisa Ashri (041164870) / CST8288 012 Assignment 2</p>
            </form>
        </div>
    </div>
</body>
</html>
