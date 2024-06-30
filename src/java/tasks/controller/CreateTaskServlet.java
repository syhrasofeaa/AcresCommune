/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
package tasks.controller;

import util.DBConnection;
import tasks.bean.TaskBean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CreateTaskServlet", urlPatterns = {"/CreateTaskServlet"})
public class CreateTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String farmerUsername = (String) session.getAttribute("username");

        if (farmerUsername != null) {
            String taskName = request.getParameter("taskName");
            String taskDesc = request.getParameter("taskDesc");
            String dueDate = request.getParameter("dueDate");
            String status = request.getParameter("status");

            TaskBean createtaskBean = new TaskBean();
            createtaskBean.setTaskName(taskName);
            createtaskBean.setTaskDesc(taskDesc);
            createtaskBean.setDueDate(dueDate);
            createtaskBean.setTaskStatus(status);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.createConnection(); // Replace with your DB connection method

            // Query to fetch FARMERID and FARMID based on farmerUsername
            String query = "SELECT FR.FARMERID " +
                           "FROM TASK T " +
                           "INNER JOIN FARM F ON T.TASKID = F.TASKID " +
                           "INNER JOIN FARMER FR ON T.FARMERID = FR.FARMERID " +
                           "WHERE FR.FARMERUSERNAME = ?";
            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, farmerUsername);
            rs = stmt.executeQuery();

            String farmerId = null;

            if (rs.next()) {
                farmerId = rs.getString("FARMERID");
            } else {
                // Handle case where farmerUsername does not exist or is not associated with a farm
                request.setAttribute("errorMessage", "Could not find farmer details.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
                
            }
            
            String queryMaxId = "SELECT MAX(TASKID) AS max_id FROM TASK";
            stmt = conn.prepareStatement(queryMaxId);
            rs = stmt.executeQuery();
            String newTaskId = "T01"; // Default value if no existing records

            if (rs.next() && rs.getString("max_id") != null) {
                String currentMaxId = rs.getString("max_id");
                int currentIdNum = Integer.parseInt(currentMaxId.substring(2));
                newTaskId = "T" + String.format("%02d", currentIdNum + 1);
            }

            // Insert task into TASK table
            String insertQuery = "INSERT INTO TASK (TASKID, TASKNAME, TASKDESC, DUEDATE, STATUS, FARMERID) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertQuery);

            stmt.setString(1, newTaskId);
            stmt.setString(2, createtaskBean.getTaskName());
            stmt.setString(3, createtaskBean.getTaskDesc());
            stmt.setString(4, createtaskBean.getDueDate());
            stmt.setString(5, createtaskBean.getTaskStatus());
            stmt.setString(6, farmerId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
            // Task creation successful
            request.setAttribute("successMessage", "Task created successfully.");
                    request.getRequestDispatcher("home.html").forward(request, response);
        } else {
            // Task creation failed
            request.setAttribute("errorMessage", "Failed to create task.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        } catch (SQLException e) {
        e.printStackTrace();
        request.setAttribute("errorMessage", "Database error occurred.");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} else {
            // Redirect to login page if session attributes are not valid
            request.setAttribute("errorMessage", "You must be logged in to create a task.");
            response.sendRedirect("farmerlogin.jsp");
        }
}
}
