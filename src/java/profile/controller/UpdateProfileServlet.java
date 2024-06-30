/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profile.controller;

import util.DBConnection;
import profile.bean.ProfileBean;

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


@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the username from session
        HttpSession session = request.getSession();
        String farmerUsername = (String) session.getAttribute("username");

        if (farmerUsername != null) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DBConnection.createConnection(); // Assuming DBConnection class is implemented

                String readQuery = "SELECT * FROM FARMER WHERE FARMERUSERNAME = ?";
                stmt = conn.prepareStatement(readQuery);
                stmt.setString(1, farmerUsername);

                rs = stmt.executeQuery();

                // Initialize a ReadProfileBean object to hold the data
                ProfileBean readprofileBean = new ProfileBean();

                if (rs.next()) {
                    // Set data to the ReadProfileBean object
                    readprofileBean.setFarmerID(rs.getString("FARMERID"));
                    readprofileBean.setName(rs.getString("FARMERNAME"));
                    readprofileBean.setPassword(rs.getString("PASSWORD"));
                    readprofileBean.setEmail(rs.getString("EMAIL"));
                    readprofileBean.setUsername(rs.getString("FARMERUSERNAME"));
                }

                // Set the profileBean as an attribute
                request.setAttribute("readprofileBean", readprofileBean);

                // Forward the request to readprofile.jsp
                request.getRequestDispatcher("updateprofile.jsp").forward(request, response);

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
            request.setAttribute("errorMessage", "You must be logged in to view profile.");
            response.sendRedirect("farmerlogin.jsp");
        }
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String farmerUsername = (String) session.getAttribute("username");
        
        if (farmerUsername != null) {
            String name = request.getParameter("fullName");
            String password = request.getParameter("Password");
            String email = request.getParameter("Email");
            String userName = request.getParameter("Username"); // Readonly
            
            ProfileBean updateprofileBean = new ProfileBean ();
            updateprofileBean.setName(name);
            updateprofileBean.setPassword(password);
            updateprofileBean.setEmail(email);
            updateprofileBean.setUsername(userName);
            
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            
            try {
                conn = DBConnection.createConnection(); // Assuming DBConnection class is implemented
                
                // Query to fetch FARMERID and FARMID based on farmerUsername
            String query = "SELECT FARMERID FROM FARMER WHERE FARMERUSERNAME = ?";
            
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
            
                String updateQuery = "UPDATE FARMER SET FARMERNAME = ?, PASSWORD = ?, EMAIL = ?, FARMERUSERNAME = ? WHERE FARMERID = ?";
                stmt = conn.prepareStatement(updateQuery);
                
                stmt.setString(1, updateprofileBean.getName());
                stmt.setString(2, updateprofileBean.getPassword());
                stmt.setString(3, updateprofileBean.getEmail());
                stmt.setString(4, updateprofileBean.getUsername());
                stmt.setString(5, farmerId);
            

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Update successful
                    request.setAttribute("successMessage", "Profile updated successfully.");
                    request.getRequestDispatcher("readprofile.jsp").forward(request, response);
                } else {
                    // Update failed
                    request.setAttribute("errorMessage", "Failed to update profile.");
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
            request.setAttribute("errorMessage", "You must be logged in to update profile.");
            response.sendRedirect("farmerlogin.jsp");
        }
    }
    
}  
    

