<%-- 
    Document   : readtask
    Created on : Jun 30, 2024, 12:51:53 AM
    Author     : user
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="tasks.bean.ReadTaskBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Read Tasks</title>
    <link rel="stylesheet" href="style.css"> <!-- Adjust path as necessary -->
    <script>
    // Function to submit the form
    function submitForm() {
        document.getElementById("ReadTask").submit();
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
        <h1>Read Task</h1>
    </header>
    <nav>
        <a href="home.html">Home</a>
        <a href="readprofile.jsp">Profile</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="aboutus.html">About Us</a>
        <a href="logout.jsp">Logout</a>
    </nav>
     <main>
        <div class="task-container">
            <h1>Read Profile</h1>
            <form id="ReadTask" action="ReadTaskServlet" method="get"> <!-- Form for refreshing data -->
            </form>
            <table>
                <tbody>
                    <tr>
                        <th>Task ID</th>
                        <td>${readtaskBean.taskID}</td>
                    </tr>
                    <tr>
                        <th>Task Name</th>
                        <td>${readtaskBean.taskName}</td>
                    </tr>
                    <tr>
                        <th>Description</th>
                        <td>${readtaskBean.taskDesc}</td>
                    </tr>
                    <tr>
                        <th>Due Date</th>
                        <td>${readtaskBean.dueDate}</td>
                    </tr>
                    <tr>
                        <th>Status</th>
                        <td>${readtaskBean.taskStatus}</td>
                    </tr>
                </tbody>
            </table>
            <div class="task-buttons">
            <button onclick="window.location.href = 'createtask.jsp'">Create Task</button>
            <button onclick="window.location.href = 'updatetask.jsp'">Edit Task</button>
            <button onclick="window.location.href = 'deletetask.jsp'">Delete Task</button> <%--servlet je tapi nanti display ayat dah takda data, ada jsp tapi sikit je hujung2 boleh buat--%>
        </div>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Community Farming Management System. All rights reserved.</p>
    </footer>
</body>
</html>

