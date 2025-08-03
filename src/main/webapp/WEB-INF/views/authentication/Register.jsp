<%-- 
    Document   : Registration
    Created on : Jul 31, 2025, 12:26:22 a.m.
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- form to add author, shows author details -->
<html>
    <head>
        <title>Register</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
    </head>
    <body>
        <center>
        <div class="container">
            <h1 style="text-align:center;">Register </h1>
            <form action="${pageContext.request.contextPath}/controller?action=register" method="POST">
                <input type="hidden" name="action" value="add">
                <label>First Name:
                    <input type="text" name="firstName" required>
                </label><br>
                <label>Last Name:
                    <input type="text" name="lastName" required>
                </label>
                <br>
                <label>Email:
                    <input type="text" name="email" required>
                </label>
                <br>
                <label for="options">Choose an option:</label>
                <select id="options" name="options">
                    <option value="option1">Operator</option>
                    <option value="option2">Transit Manager</option>
                </select>
                <br>
                <button type="submit" name="action" value="add">Register</button>
                <div style="text-align:left; margin-bottom:1rem;">
                    <a href="${pageContext.request.contextPath}"
                       class="btn">Back to Home</a>
                </div>
                <p>blah</p>
            </form>
        </div>
    </div>
        </center>
</body>
</html>
