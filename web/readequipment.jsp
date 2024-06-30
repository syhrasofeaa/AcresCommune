<%-- 
    Document   : readequipment
    Created on : Jun 30, 2024, 6:41:55 AM
    Author     : user
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="equipments.bean.EquipmentBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Read Equipments</title>
    <link rel="stylesheet" href="style.css"> 
    <script>
    // Function to submit the form
    function submitForm() {
        document.getElementById("ReadEquipment").submit();
    }

    // Check if page is opened for the first time using session storage
    if (sessionStorage.getItem("firstLoad") === null) {
        // Set session storage to mark the page as loaded
        sessionStorage.setItem("firstLoad", "true");

        // Execute when the page is opened for the first time
        window.onload = function() {
            submitForm();
        }; 
    } else if (performance.navigation.type === 1) {
        // Reload the page normally when refreshed
        window.onload = function() {
            submitForm();
        };
    }
</script>
</head>
<body>
    <header>
        <h1>Read Equipments</h1>
    </header>
    <nav>
        <a href="home.html">Home</a>
        <a href="readprofile.jsp">Profile</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="aboutus.html">About Us</a>
        <a href="logout.jsp">Logout</a>
    </nav>
<main>
        <div class="equipment-container">
            <h2>Read Equipments</h2>
            <form id="ReadEquipment" action="ReadEquipmentServlet" method="get"> <!-- Form for refreshing data -->
            </form>
            <table>
                <tbody>
                    <tr>
                        <th>Equipment ID</th>
                        <td>${readequipmentBean.equipmentID}</td>
                    </tr>
                    <tr>
                        <th>Equipment Name</th>
                        <td>${readequipmentBean.equipmentName}</td>
                    </tr>
                    <tr>
                        <th>Equipment Condition</th>
                        <td>${readequipmentBean.equipmentCondition}</td>
                    </tr>
                    <tr>
                        <th>Used Date</th>
                        <td>${readequipmentBean.usedDate}</td>
                    </tr>
                </tbody>
            </table>
             <div class="equipment-buttons">
            <button onclick="window.location.href = 'createequipment.jsp'">Create Equipments</button>
            <button onclick="window.location.href = 'updateequipment.jsp'">Edit Equipments</button>
            <button onclick="window.location.href = 'deleteequipment.jsp'">Delete Equipments</button>
        </div>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Community Farming Management System. All rights reserved.</p>
    </footer>
</body>
</html>