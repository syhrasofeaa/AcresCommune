/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crops.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DBConnection;

/**
 *
 * @author user
 */
@WebServlet(name = "CreateCrops", urlPatterns = {"/CreateCrops"})
public class CreateCropsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String farmerId = (String) session.getAttribute("username"); // Assuming "username" holds the farmerId
        String farmId = (String) session.getAttribute("farmId"); // Assuming "farmId" holds the farmId

        if (farmerId != null && farmId != null) {
            String CropsId = request.getParameter("cropsId");
            String CropsName = request.getParameter("cropsName");
            String CropsType = request.getParameter("cropsType");
            String PlantingDate = request.getParameter("plantingDate");
            String HarvestDate = request.getParameter("harvestDate");

            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = DBConnection.createConnection(); // Replace with your DB connection method

                String insertQuery = "INSERT INTO CROP (CROPID, CROPNAME, CROPTYPE, PLANTINGDATE, HARVESTDATE, FARMERID, FARMID) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertQuery);

                stmt.setString(1, CropsId);
                stmt.setString(2, CropsName);
                stmt.setString(3, CropsType);
                stmt.setString(4, PlantingDate);
                stmt.setString(5, HarvestDate);
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
