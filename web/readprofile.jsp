<%-- 
    Document   : profile
    Created on : Jun 29, 2024, 2:43:55 PM
    Author     : user
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="profile.bean.ProfileBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acres Commune - Profile</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script>
    // Function to submit the form
    function submitForm() {
        document.getElementById("ReadProfile").submit();
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
        <div class="profile-container">
            <h1>Read Profile</h1>
            <form id="ReadProfile" action="ReadProfileServlet" method="get"> <!-- Form for refreshing data -->
            </form>
            <table>
                <tbody>
                    <tr>
                        <th>Farmer ID</th>
                        <td>${readprofileBean.farmerID}</td>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <td>${readprofileBean.name}</td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td>${readprofileBean.password}</td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td>${readprofileBean.email}</td>
                    </tr>
                    <tr>
                        <th>Username</th>
                        <td>${readprofileBean.username}</td>
                    </tr>
                </tbody>
            </table>
             <div class="profile-buttons">
            <button onclick="window.location.href = 'updateprofile.jsp'">Edit Profile</button>
            <button onclick="window.location.href = 'deleteprofile.jsp'">Delete Profile</button>
        </div>
        </div>
    </main>

<footer>
    <p>&copy; 2024 Community Farming Management System. All rights reserved.</p>
</footer>
</body>
</html>
