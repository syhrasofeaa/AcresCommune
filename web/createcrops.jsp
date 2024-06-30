<%-- 
    Document   : createcrops
    Created on : Jun 30, 2024, 7:34:21 AM
    Author     : user
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Crop</title>
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
    <h1>Create Crop</h1>
    <form action="CreateTaskServlet" method="post">
        <table>
            <tbody>
                <tr>
                    <td><label for=cropId">Crop ID:</label></td>
                    <td><input type="text" id="cropId" name="cropId" required></td>
                </tr>
                <tr>
                    <td><label for="cropName">Crop Name:</label></td>
                    <td><input type="text" id="cropName" name="cropName" required></td>
                </tr>
                <tr>
                    <td><label for="cropType">Crop Type</label></td>
                    <td><textarea id="cropType" name="cropType" rows="4" cols="50" required></textarea></td>
                </tr>
                <tr>
                    <td><label for="plantingDate">Due Date:</label></td>
                    <td><input type="date" id="plantingDate" name="plantingDate" required></td>
                </tr>
                <tr>
                    <td><label for=harvestDate">Due Date:</label></td>
                    <td><input type="date" id="harvestDate" name="harvestDate" required></td>
                </tr>
                <tr>
                    <td><input type="hidden" id="farmerId" name="farmerId" value="<%= session.getAttribute("username") %>"></td>
                    <!-- Assuming "username" is the attribute storing the logged-in user's farmer ID -->
                </tr>
                <tr>
                    <td><input type="hidden" id="farmId" name="farmId" value="<%= session.getAttribute("farmId") %>"></td>
                    <!-- Assuming "farmId" is the attribute storing the farm ID -->
                </tr>
            </tbody>
        </table>
        <button type="submit">Create Crops</button>
    </form>
    </main>
</body>
</html>

