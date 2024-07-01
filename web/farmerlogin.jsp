<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="style.css"> <!-- Link to external CSS -->
    </head>
    <body class="login-page">
    <div class="login-container">
        <h2>Login</h2>
        
        <form class="login-form" action="LoginServlet" method="POST"> <!-- Adjust action to your Servlet URL -->
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
        </form>
        <button class="login-button" onclick="location.href='home.html'">Log In</button>
        <button class="signup-button" onclick="location.href='farmersignup.jsp'">Sign Up</button>
        
    </div>
    </body>
</html>
