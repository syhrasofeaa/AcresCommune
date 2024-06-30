<%-- 
    Document   : updatecrops
    Created on : Jun 30, 2024, 9:56:08 AM
    Author     : user
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="crops.bean.CropsBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acres Commune - Update Crops</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
        <div class="crops-container">
            <h1>Update Crops</h1>
            <form id="UpdateCrops" action="UpdateCropsServlet" method="get"> <!-- Form for refreshing data -->
<script>
    // Function to submit the form
    function submitForm() {
        document.getElementById("UpdateCrops").submit();
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
                <input type="hidden" name="action" value="load">
            <table>
                <tbody>
                    <tr>
                        <th>Farmer ID</th>
                        <td>${updatecropsBean.farmerID}</td>
                    </tr>
                    <tr>
                        <th>Crop ID</th>
                        <td>${updatecropsBean.CropID}</td>
                    </tr>
                    <tr>
                        <th>Crop Name</th>
                        <td>${updatecropsBean.CropName}</td>
                    </tr>
                    <tr>
                        <th>Crop Type</th>
                        <td>${updatecropsBean.CropType}</td>
                    </tr>
                    <tr>
                        <th>Planting Date</th>
                        <td>${updatecropsBean.PlantingDate}</td>
                    </tr>
                    <tr>
                        <th>Harvest Date</th>
                        <td>${updatecropsBean.HarvestDate}</td>
                    </tr>
                </tbody>
            </table>
             </form>

                    <div class="update-container">    <!-- Form for updating profile data (POST) -->
        <form id="UpdateProfileForm" action="UpdateTaskServlet" method="post">
            <input type="hidden" name="farmerID" value="${profileBean.farmerID}">
            <table>
                <tbody>
                    <tr>
                        <th>Name</th>
                        <td>
                            <input type="text" name="fullName" value="" required>
                        </td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td>
                            <input type="password" name="Password" value="" required>
                        </td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td>
                            <input type="email" name="Email" value="" required>
                        </td>
                    </tr>
                    <tr>
                        <th>Username</th>
                        <td>
                            <input type="text" name="Username" value="" required>
                        </td>
                    </tr>
                </tbody>
            </table>
            <button type="submit">Update Crops</button>
        </form>
    </div>
        </div>
    </main>

<footer>
    <p>&copy; 2024 Community Farming Management System. All rights reserved.</p>
</footer>
</body>
</html>

