/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments.controller;

import equipments.bean.UpdateEquipmentBean;
import util.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UpdateEquipmentServlet", urlPatterns = {"/UpdateEquipmentServlet"})
public class UpdateEquipmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the username from session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DBConnection.createConnection(); // Assuming DBConnection class is implemented

                String readQuery = "SELECT * FROM FARMER WHERE FARMERUSERNAME = ?"; /**/
                stmt = conn.prepareStatement(readQuery);
                stmt.setString(1, username);

                rs = stmt.executeQuery();

                // Initialize a ReadProfileBean object to hold the data
                UpdateEquipmentBean updateequipmentBean = new UpdateEquipmentBean();

                if (rs.next()) {
                    // Set data to the ReadProfileBean object
                    updateequipmentBean.setEquipmentID(rs.getString("EQUIPMENTID"));
                    updateequipmentBean.setEquipmentName(rs.getString("EQUIPMENTNAME"));
                    updateequipmentBean.setEquipmentCondition(rs.getString("EQUIPMENTCONDIITION"));
                    updateequipmentBean.setUsedDate(rs.getString("USEDDATE"));
                }

                // Set the profileBean as an attribute
                request.setAttribute("updateequipmentBean", updateequipmentBean);

                // Forward the request to readprofile.jsp
                request.getRequestDispatcher("updateequipment.jsp").forward(request, response);

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
        String username = (String) session.getAttribute("username");
        
        if (username != null) {
            String farmerID = request.getParameter("farmerID");
            String name = request.getParameter("fullName");
            String password = request.getParameter("Password");
            String email = request.getParameter("Email");
            String userName = request.getParameter("Username"); // Readonly
            
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = DBConnection.createConnection(); // Assuming DBConnection class is implemented

                String updateQuery = "UPDATE FARMER SET FARMERNAME = ?, PASSWORD = ?, EMAIL = ?, FARMERUSERNAME = ? WHERE FARMERID = ?"; /**/
                stmt = conn.prepareStatement(updateQuery);
                
                stmt.setString(1, name);
                stmt.setString(2, password);
                stmt.setString(3, email);
                stmt.setString(4, userName);

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
