/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments.controller;

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


/**
 *
 * @author user
 */
@WebServlet(name = "CreateEquipmentServlet", urlPatterns = {"/CreateEquipmentServlet"})
public class CreateEquipmentServlet extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String farmerId = (String) session.getAttribute("username"); // Assuming "username" holds the farmerId
        String farmId = (String) session.getAttribute("farmId"); // Assuming "farmId" holds the farmId

        if (farmerId != null && farmId != null) {
            String EquipmentID = request.getParameter("equipmentId");
            String EquipmentName = request.getParameter("equipmentName");
            String EquipmentCondition = request.getParameter("equipmentCondition");
            String UsedDate = request.getParameter("usedDate");

            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = DBConnection.createConnection(); // Replace with your DB connection method

                String insertQuery = "INSERT INTO EQUIPMENT (EQUIPMENTID, EQUIPMENTNAME, EQUIPMENTCONDITION, USEDDATE, FARMERID, FARMID) VALUES (?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertQuery);

                stmt.setString(1, EquipmentID);
                stmt.setString(2, EquipmentName);
                stmt.setString(3, EquipmentCondition);
                stmt.setString(4, UsedDate);
                stmt.setString(5, farmerId);
                stmt.setString(6, farmId);

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
