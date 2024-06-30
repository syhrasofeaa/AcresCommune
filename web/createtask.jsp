<%-- 
    Document   : createtask
    Created on : Jun 30, 2024, 12:00:58 AM
    Author     : user
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Tasks</title>
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
    <h1>Create Tasks</h1>
    <form action="CreateTaskServlet" method="post">
        <table>
            <tbody>
                <tr>
                    <td><label for="taskId">Task ID:</label></td>
                    <td><input type="text" id="taskId" name="taskId" required></td>
                </tr>
                <tr>
                    <td><label for="taskName">Task Name:</label></td>
                    <td><input type="text" id="taskName" name="taskName" required></td>
                </tr>
                <tr>
                    <td><label for="taskDesc">Task Description:</label></td>
                    <td><textarea id="taskDesc" name="taskDesc" rows="4" cols="50" required></textarea></td>
                </tr>
                <tr>
                    <td><label for="dueDate">Due Date:</label></td>
                    <td><input type="date" id="dueDate" name="dueDate" required></td>
                </tr>
                <tr>
                    <td><label for="status">Status:</label></td>
                    <td>
                        <select id="status" name="status" required>
                            <option value="Pending">Pending</option>
                            <option value="Completed">Completed</option>
                        </select>
                    </td>
                </tr>
            </tbody>
        </table>
        <button type="submit">Create Task</button>
    </form>
    </main>
</body>
</html>

