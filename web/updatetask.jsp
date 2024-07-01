<%-- 
    Document   : updatetask
    Created on : Jun 30, 2024, 9:29:58 AM
    Author     : user
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="tasks.bean.TaskBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acres Commune</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Amatic+SC:wght@400;700&family=Lato:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
    <header>
        <h1>Dashboard</h1>
    </header>
    <nav>
        <span style="cursor:pointer" onclick="openNav()">&#9776; More</span>
        <a href="home.html">Home</a>
        <a href="readprofile.jsp">Profile</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="aboutus.html">About Us</a>
        <a href="logout.jsp">Logout</a>
    </nav>
    <div id="mySidenav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="readtask.jsp">Tasks</a>
        <a href="readequipment.jsp">Equipments</a>
        <a href="readcrops.jsp">Crops</a>
    </div>
</head>
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
 <main>
    <div class="update-container">
        

        <h1>Update Task</h1>
        <%
            TaskBean readtaskBean = (TaskBean) request.getAttribute("readtaskBean");
            if (readtaskBean != null) {
        %>
        <form id="ReadTask" action="ReadTaskServlet" method="get"></form>
        <form action="UpdateTaskServlet" method="post">
            <input type="hidden" name="taskID" value="<%= readtaskBean.getTaskID() %>">
            <table>
                <tbody>
                    <tr>
                        <td><label for="taskID">Task ID:</label></td>
                        <td><input type="text" id="taskID" name="taskID" value="<%= readtaskBean.getTaskID() %>" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="taskName">Task Name:</label></td>
                        <td><input type="text" id="taskName" name="taskName" value="<%= readtaskBean.getTaskName() %>" required></td>
                    </tr>
                    <tr>
                        <td><label for="taskDesc">Task Description:</label></td>
                        <td><textarea id="taskDesc" name="taskDesc" rows="4" cols="50" required><%= readtaskBean.getTaskDesc() %></textarea></td>
                    </tr>
                    <tr>
                        <td><label for="dueDate">Due Date:</label></td>
                        <td><input type="date" id="dueDate" name="dueDate" value="<%= readtaskBean.getDueDate() %>" required></td>
                    </tr>
                    <tr>
                        <td><label for="taskStatus">Status:</label></td>
                        <td>
                            <select id="taskStatus" name="taskStatus" required>
                                <option value="Pending" <%= "Pending".equals(readtaskBean.getTaskStatus()) ? "selected" : "" %>>Pending</option>
                                <option value="Completed" <%= "Completed".equals(readtaskBean.getTaskStatus()) ? "selected" : "" %>>Completed</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="update-buttons">
                <button type="submit">Update Task</button>
                <button type="button" onclick="window.location.href = 'createtask.jsp'">Create Task</button>
                <button type="button" onclick="window.location.href = 'readtask.jsp'">View Task</button>
                <button type="button" onclick="window.location.href = 'deletetask.jsp'">Delete Task</button>
            </div>
        </form>
        
        <%
            } else {
        %>
        <p>Information not found.</p>
        <%
            }
        %>
      
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