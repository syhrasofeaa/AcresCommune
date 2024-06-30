<%-- 
    Document   : FarmerLogin
    Created on : Jun 29, 2024, 11:21:45 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
    <style>
        /* Add your CSS styles here */
        body {
            background-image: url('crops/background.jpg'); /* Replace 'path/to/your/image.jpg' with the path to your image */
            background-size: cover; /* This will cover the entire background */
            background-repeat: no-repeat; /* This will prevent the background from repeating */
            font-family: "Lato", sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-form input[type="text"],
        .login-form input[type="password"] {
            width: 95%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-form input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #0a5a0a;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .login-form input[type="submit"]:hover {
            background-color: #6abf69;
        }
        .login-button {
            width: 100%; /* Adjust width to leave space for scrollbar */
            padding: 10px;
            background-color: #0a5a0a;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 15px;
        }
        .login-button:hover {
            background-color: #6abf69;
        }
        .signup-button {
            width: 100%; /* Adjust width to leave space for scrollbar */
            padding: 10px;
            background-color: #0a5a0a;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 15px;
        }
        .signup-button:hover {
            background-color: #6abf69;
        }
    </style>
    </head>
    <body>
    <div class="login-container">
        <h2>Login</h2>
        
        <form class="login-form" action="LoginServlet" method="POST"> <!-- Adjust action to your Servlet URL -->
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button class="login-button">Log In</button>
        </form>
        <button class="signup-button" onclick="location.href='farmersignup.jsp'">Sign Up</button>
        
    </div>
    </body>
</html>
