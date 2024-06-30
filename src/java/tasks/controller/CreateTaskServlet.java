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
import tasks.bean.CreateTaskBean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String farmerUsername = (String) session.getAttribute("username"); // Assuming "username" holds the farmerId
  
        if (farmerUsername != null) {
            String taskId = request.getParameter("taskId");
            String taskName = request.getParameter("taskName");
            String taskDesc = request.getParameter("taskDesc");
            String dueDate = request.getParameter("dueDate");
            String status = request.getParameter("status");

            Connection conn = null;
            PreparedStatement stmt = null;
            
            CreateTaskBean createtaskBean = new CreateTaskBean();
            createtaskBean.setTaskID(taskId);
            createtaskBean.setTaskName(taskName);
            createtaskBean.setTaskDesc(taskDesc);
            createtaskBean.setDueDate(dueDate);
            createtaskBean.setTaskStatus(status);
        
            try {
                conn = DBConnection.createConnection(); // Replace with your DB connection method

                String insertQuery = "INSERT INTO TASK (TASKID, TASKNAME, TASKDESC, DUEDATE, STATUS, FARMERID, FARMID) " +
                         "SELECT ?, ?, ?, ?, ?, F.FARMERID, F.FARMID " +
                         "FROM FARM F " +
                         "INNER JOIN FARMER FR ON F.FARMERID = FR.FARMERID " +
                         "WHERE F.TASKID = ?";
                stmt = conn.prepareStatement(insertQuery);

                stmt.setString(1, createtaskBean.getTaskID());
                stmt.setString(2, createtaskBean.getTaskName());
                stmt.setString(3, createtaskBean.getTaskDesc());
                stmt.setString(4, createtaskBean.getDueDate());
                stmt.setString(5, createtaskBean.getTaskStatus());
                stmt.setString(6, farmerId);
                stmt.setString(7, farmId);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Task creation successful
                    request.setAttribute("successMessage", "Task created successfully.");
                    request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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