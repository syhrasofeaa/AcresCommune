<%-- 
    Document   : updatetask
    Created on : Jun 30, 2024, 9:29:58 AM
    Author     : user
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="tasks.bean.TaskBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acres Commune - Update Task</title>
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
    <div class="update-container">
            <h1>Update Task</h1>
            <%
                TaskBean view = (TaskBean) request.getAttribute("viewTaskBean");
                if (view != null) {
            %>
            <form action="UpdateTaskServlet" method="post">
         
                <input type="hidden" name="taskID" value="<%=view.getTaskID()%>">
                <table>
                    <tbody>
                        <tr>
                            <td><label for="taskID">Task ID:</label></td>
                            <td><input type="text" id="taskID" name="taskID" value="<%=view.getTaskID()%>" readonly></td>
                        </tr>
                        <tr>
                            <td><label for="taskName">Task Name:</label></td>
                            <td><input type="text" id="taskName" name="taskName" value="<%=view.getTaskID()%>" required></td>
                        </tr>
                        <tr>
                            <td><label for="taskDesc">Task Description:</label></td>
                            <td><textarea id="taskDesc" name="taskDesc" rows="4" cols="50" required><%=view.getTaskDesc()%></textarea></td>
                        </tr>
                        <tr>
                            <td><label for="dueDate">Due Date:</label></td>
                            <td><input type="date" id="dueDate" name="dueDate" value="<%=view.getDueDate()%>" required></td>
                        </tr>
                        <tr>
                            <td><label for="status">Status:</label></td>
                            <td>
                                <select id="status" name="status" required>
                                    <option value="Pending" <%= "Pending".equals(view.getTaskStatus()) ? "selected" : "" %>>Pending</option>
                                     <option value="Completed" <%= "Completed".equals(view.getTaskStatus()) ? "selected" : "" %>>Completed</option>
                                </select>
                            </td>
                        </tr>
                        
                    </tbody>
                    
                </table>
                               
                <div class="update-buttons">
                <button type="submit">Update Task</button>
            <button onclick="window.location.href = 'createtask.jsp'">Create Task</button>
            <button onclick="window.location.href = 'readtask.jsp'">View Task</button>
            <button onclick="window.location.href = 'deletetask.jsp'">Delete Task</button> <%--servlet je tapi nanti display ayat dah takda data, ada jsp tapi sikit je hujung2 boleh buat--%>
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
</body>
</html>