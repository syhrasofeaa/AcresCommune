<%-- 
    Document   : deleteprofile
    Created on : Jun 29, 2024, 11:44:21 PM
    Author     : user
--%>



<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Profile</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <header>
        <h1>Delete Profile</h1>
    </header>
    <nav>
        <a href="home.html">Home</a>
        <a href="readprofile.jsp">Profile</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="aboutus.html">About Us</a>
        <a href="logout.jsp">Logout</a>
    </nav>
    <main>
        <div class="delete-container">
            <h2>Are you sure you want to delete your profile?</h2>
            <form action="DeleteProfileServlet" method="post">
                <button type="submit">Delete Profile</button>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Community Farming Management System. All rights reserved.</p>
    </footer>
</body>
</html>

