<%-- 
    Document   : createtask
    Created on : Jun 30, 2024, 12:00:58 AM
    Author     : user
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="equipments.bean.EquipmentBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Equipment</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Include any necessary CSS or scripts -->
</head>
<body>
    <header>
    <h1>Welcome to the Acres Commune</h1>
</header>
<nav>
    <a href="home.html">Home</a>
    <a href="readprofile.jsp">Profile</a>
    <a href="dashboard.jsp">Dashboard</a>
    <a href="aboutus.html">About Us</a>
    <a href="logout.jsp">Logout</a>
</nav>
    <main>
    <h1>Create Equipment</h1>
    <form action="CreateEquipmentServlet" method="post">
        <table>
            <tbody>
                <tr>
                    <td><label for="equipmentName">Equipment Name:</label></td>
                    <td><input type="text" id="equipmentName" name="equipmentName" required></td>
                </tr>
                <tr>
                    <td><label for="equipmentCondition">Equipment Condition</label></td>
                    <td>
                        <select id="equipmentCondition" name="equipmentCondition" required>
                            <option value="Poor">Poor</option>
                            <option value="Fair">Fair</option>
                            <option value="Good">Good</option>
                            <option value="Excellent">Excellent</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="dueDate">Used Date:</label></td>
                    <td><input type="date" id="usedDate" name="usedDate" required></td>
                </tr>
            </tbody>
        </table>
        <button type="submit">Create Equipment</button>
    </form>
    </main>
</body>
</html>

