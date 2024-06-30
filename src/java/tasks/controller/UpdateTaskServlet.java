/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks.controller;

import tasks.bean.TaskBean;
import util.DBConnection;

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


/**
 *
 * @author user
 */
@WebServlet(name = "UpdateTaskServlet", urlPatterns = {"/UpdateTaskServlet"})
public class UpdateTaskServlet extends HttpServlet {
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String farmerUsername = (String) session.getAttribute("username");
        
        if (farmerUsername != null) {
            String taskID = request.getParameter("taskID");
            String taskName = request.getParameter("taskName");
            String taskDesc = request.getParameter("taskDesc");
            String dueDate = request.getParameter("dueDate");
            String status = request.getParameter("status");
            
            TaskBean updatetaskBean = new TaskBean();
            updatetaskBean.setTaskID(taskID);
            updatetaskBean.setTaskName(taskName);
            updatetaskBean.setTaskDesc(taskDesc);
            updatetaskBean.setDueDate(dueDate);
            updatetaskBean.setTaskStatus(status);
            
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
            conn = DBConnection.createConnection(); // Replace with your DB connection method

            // Query to fetch FARMERID and FARMID based on farmerUsername
            String query = "SELECT FR.FARMERID, T.TASKID " +
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

                String updateQuery = "UPDATE TASK SET TASKNAME = ?, TASKDESC = ?, DUEDATE = ?, STATUS = ? WHERE TASKID = ? AND FARMERID = ?";
                stmt = conn.prepareStatement(updateQuery);
               
                stmt.setString(1, updatetaskBean.getTaskName());
                stmt.setString(2, updatetaskBean.getTaskDesc());
                stmt.setString(3, updatetaskBean.getDueDate());
                stmt.setString(4, updatetaskBean.getTaskStatus());
                stmt.setString(5, updatetaskBean.getTaskID());
                stmt.setString(6, farmerId);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Update successful
                    request.setAttribute("successMessage", "Task updated successfully.");
                    request.getRequestDispatcher("readtask.jsp").forward(request, response);
                } else {
                    // Update failed
                    request.setAttribute("errorMessage", "Failed to update task.");
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
            // Redirect to login page if username parameter is missing
            request.setAttribute("errorMessage", "You must be logged in to update task.");
            response.sendRedirect("farmerlogin.jsp");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String farmerUsername = (String) session.getAttribute("username");
        
        if (farmerUsername != null) {
            String taskID = request.getParameter("taskID");
            
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DBConnection.createConnection();

                String query = "SELECT T.TASKID, T.TASKNAME, T.TASKDESC, T.DUEDATE, T.STATUS " +
                               "FROM TASK T " +
                               "INNER JOIN FARMER FR ON T.FARMERID = FR.FARMERID " +
                               "WHERE FR.FARMERUSERNAME = ? AND T.TASKID = ?";
                
                stmt = conn.prepareStatement(query);
                stmt.setString(1, farmerUsername);
                stmt.setString(2, taskID);
                rs = stmt.executeQuery();

                TaskBean viewtaskBean = new TaskBean();

                if (rs.next()) {
                    viewtaskBean.setTaskID(rs.getString("TASKID"));
                    viewtaskBean.setTaskName(rs.getString("TASKNAME"));
                    viewtaskBean.setTaskDesc(rs.getString("TASKDESC"));
                    viewtaskBean.setDueDate(rs.getString("DUEDATE"));
                    viewtaskBean.setTaskStatus(rs.getString("STATUS"));
                }

                request.setAttribute("viewtaskBean", viewtaskBean);
                request.getRequestDispatcher("updatetask.jsp").forward(request, response);

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
            // Redirect to login page if username parameter is missing
            request.setAttribute("errorMessage", "You must be logged in to view task.");
            response.sendRedirect("farmerlogin.jsp");
        }
    }

}
