<%-- 
    Document   : readequipment
    Created on : Jun 30, 2024, 6:41:55 AM
    Author     : user
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="crops.bean.CropsBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Read Crops</title>
    <link rel="stylesheet" href="style.css"> <!-- Adjust path as necessary -->
    <script>
    // Function to submit the form
    function submitForm() {
        document.getElementById("ReadCrops").submit();
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
        <h1>Read Crops</h1>
    </header>
    <nav>
        <a href="home.html">Home</a>
        <a href="readprofile.jsp">Profile</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="aboutus.html">About Us</a>
        <a href="logout.jsp">Logout</a>
    </nav>
<main>
        <div class="crops-container">
            <h2>Read Crops</h2>
            <form id="ReadCrops" action="ReadCropsServlet" method="get"> <!-- Form for refreshing data -->
            </form>
            <table>
                <tbody>
                    <tr>
                        <th>Crop ID</th>
                        <td>${readcropsBean.cropID}</td>
                    </tr>
                    <tr>
                        <th>Crop Name</th>
                        <td>${readcropsBean.cropName}</td>
                    </tr>
                    <tr>
                        <th>Crop Type</th>
                        <td>${readcropsBean.cropType}</td>
                    </tr>
                    <tr>
                        <th>Planting Date</th>
                        <td>${readcropsBean.plantingDate}</td>
                    </tr>
                    <tr>
                        <th>Harvest Date</th>
                        <td>${readcropsBean.harvestDate}</td>
                    </tr>
                </tbody>
            </table>
             <div class="crop-buttons">
            <button onclick="window.location.href = 'createcrops.jsp'">Create Crops</button>
            <button onclick="window.location.href = 'updatecrops.jsp'">Edit Crops</button>
            <button onclick="window.location.href = 'deletecrops.jsp'">Delete Crops</button>
        </div>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Community Farming Management System. All rights reserved.</p>
    </footer>
</body>
</html>