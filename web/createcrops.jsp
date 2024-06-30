<%-- 
    Document   : createcrops
    Created on : Jun 30, 2024, 7:34:21 AM
    Author     : user
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="crops.bean.CropsBean" %>
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
    <form action="CreateCropsServlet" method="post">
        <table>
            <tbody>
                <tr>
                    <td><label for="cropName">Crop Name:</label></td>
                    <td><input type="text" id="cropName" name="cropName" required></td>
                </tr>
                <tr>
                    <td><label for="cropType">Crop Type</label></td>
                    <td>
                        <select id="cropType" name="cropType" required>
                            <option value="Grain">Grain</option>
                            <option value="Vegetable">Vegetable</option>
                            <option value="Legume">Legume</option>
                            <option value="Root">Root</option>
                            <option value="Fruit">Fruit</option>
                            <option value="Nuts">Nuts</option>
                            <option value="Seeds">Seeds</option>
                            <option value="Fibers">Fibers</option>
                            <option value="Spices">Spices</option>
                            <option value="Plants">Plants</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="plantingDate">Planting Date:</label></td>
                    <td><input type="date" id="plantingDate" name="plantingDate" required></td>
                </tr>
                <tr>
                    <td><label for=harvestDate">Harvest Date:</label></td>
                    <td><input type="date" id="harvestDate" name="harvestDate" required></td>
                </tr>
            </tbody>
        </table>
        <button type="submit">Create Crops</button>
    </form>
    </main>
</body>
</html>

