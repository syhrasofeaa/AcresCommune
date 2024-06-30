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

@WebServlet(name = "ReadTaskServlet", urlPatterns = {"/ReadTaskServlet"})
public class ReadTaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String farmerUsername = (String) session.getAttribute("username");

        if (farmerUsername != null) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DBConnection.createConnection(); // Assuming DBConnection class is implemented

                String readQuery = "SELECT T.TASKID, T.TASKNAME, T.TASKDESC, T.DUEDATE, T.STATUS " +
                                   "FROM TASK T INNER JOIN FARMER FR ON T.FARMERID = FR.FARMERID WHERE FR.FARMERUSERNAME = ?";
                
                stmt = conn.prepareStatement(readQuery);
                stmt.setString(1, farmerUsername);

                rs = stmt.executeQuery();
                
                TaskBean readtaskBean = new TaskBean();
                if (rs.next()) {
                    
                    readtaskBean.setTaskID(rs.getString("TASKID"));
                    readtaskBean.setTaskName(rs.getString("TASKNAME"));
                    readtaskBean.setTaskDesc(rs.getString("TASKDESC"));
                    readtaskBean.setDueDate(rs.getString("DUEDATE"));
                    readtaskBean.setTaskStatus(rs.getString("STATUS"));
                    
                }

                request.setAttribute("readtaskBean", readtaskBean);
                request.getRequestDispatcher("readtask.jsp").forward(request, response);

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
            // Redirect to login page if farmerId parameter is missing
            request.setAttribute("errorMessage", "You must be logged in to view tasks.");
            response.sendRedirect("farmerlogin.jsp");
        }
    }
}
