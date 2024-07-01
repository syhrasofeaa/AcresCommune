<%-- 
    Document   : updateprofile
    Created on : Jun 29, 2024, 10:10:11 PM
    Author     : user
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="profile.bean.ProfileBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acres Commune - Update Profile</title>
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
    <main>
        <div class="profile-container">
            <h1>Read Profile</h1>
            <form id="ReadProfile" action="UpdateProfileServlet" method="get"> <!-- Form for refreshing data -->
                <input type="hidden" name="action" value="load">
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
             </form>

         <div class="update-container">    <!-- Form for updating profile data (POST) -->
        <form action="UpdateProfileServlet" method="post">
            
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
            <button type="submit">Update Profile</button>
        </form>
    </div>
        </div>
    </main>

<footer>
    <p>&copy; 2024 Community Farming Management System. All rights reserved.</p>
</footer>
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
    </script>
</body>
</html>