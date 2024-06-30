/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profile.controller;

import util.DBConnection;

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

@WebServlet(name = "DeleteProfileServlet", urlPatterns = {"/DeleteProfileServlet"})
public class DeleteProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username != null) {
            Connection conn = null;
            PreparedStatement stmt = null;
            
            try {
                conn = DBConnection.createConnection(); // Assuming DBConnection class is implemented
                
                // Delete user's profile data
                String deleteQuery = "DELETE FROM FARMER WHERE FARMERUSERNAME = ?";
                stmt = conn.prepareStatement(deleteQuery);
                stmt.setString(1, username);
                
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    // Deletion successful
                    session.invalidate(); // Invalidate session to logout user
                    response.sendRedirect("farmerlogin.jsp"); // Redirect to login page
                } else {
                    // Deletion failed
                    request.setAttribute("errorMessage", "Failed to delete profile.");
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
            request.setAttribute("errorMessage", "You must be logged in to delete profile.");
            response.sendRedirect("farmerlogin.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handling GET requests, if needed
    }
}
