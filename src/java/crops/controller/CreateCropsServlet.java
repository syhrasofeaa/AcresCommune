/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crops.controller;

import util.DBConnection;
import crops.bean.CropsBean;

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
@WebServlet(name = "CreateCrops", urlPatterns = {"/CreateCrops"})
public class CreateCropsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String farmerUsername = (String) session.getAttribute("username"); // Assuming "username" holds the farmerId
 

        if (farmerUsername != null) {
            String cropsName = request.getParameter("cropName");
            String cropsType = request.getParameter("cropType");
            String plantingDate = request.getParameter("plantingDate");
            String harvestDate = request.getParameter("harvestDate");

            CropsBean createcropsBean = new CropsBean ();
            createcropsBean.setCropName(cropsName);
            createcropsBean.setCropType(cropsType);
            createcropsBean.setPlantingDate(plantingDate);
            createcropsBean.setHarvestDate(harvestDate);
            
            ResultSet rs = null;
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = DBConnection.createConnection(); // Replace with your DB connection method

                String query = "SELECT FR.FARMERID, F.FARMID " +
                                "FROM CROP C " +
                                "INNER JOIN FARMER FR ON C.FARMERID = FR.FARMERID " +
                                "INNER JOIN FARM F ON C.FARMID = F.FARMID " +
                                "WHERE FR.FARMERUSERNAME = ?";
            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, farmerUsername);
            rs = stmt.executeQuery();

            String farmerId = null;
            String farmId = null;

            if (rs.next()) {
                farmerId = rs.getString("FARMERID");
                farmId = rs.getString("FARMID");
            } else {
                // Handle case where farmerUsername does not exist or is not associated with a farm
                request.setAttribute("errorMessage", "Could not find farmer details.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
                
            }
            
            String queryMaxId = "SELECT MAX(CROPID) AS max_id FROM CROP";
            stmt = conn.prepareStatement(queryMaxId);
            rs = stmt.executeQuery();
            String newCropId = "C01"; // Default value if no existing records

            if (rs.next() && rs.getString("max_id") != null) {
                String currentMaxId = rs.getString("max_id");
                int currentIdNum = Integer.parseInt(currentMaxId.substring(1));
                newCropId = "C" + String.format("%02d", currentIdNum + 1);
            }
            
            
                String insertQuery = "INSERT INTO CROP (CROPID, CROPNAME, CROPTYPE, PLANTINGDATE, HARVESTDATE, FARMID, FARMERID) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertQuery);

                stmt.setString(1, newCropId);
                stmt.setString(2, createcropsBean.getCropName());
                stmt.setString(3, createcropsBean.getCropType());
                stmt.setString(4, createcropsBean.getPlantingDate());
                stmt.setString(5, createcropsBean.getHarvestDate());
                stmt.setString(6, farmId);
                stmt.setString(7, farmerId);
                

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Task creation successful
                    request.setAttribute("successMessage", "Crops created successfully.");
                    request.getRequestDispatcher("home.html").forward(request, response);
                } else {
                    // Task creation failed
                    request.setAttribute("errorMessage", "Failed to create a crop.");
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
            request.setAttribute("errorMessage", "You must be logged in to create a crop.");
            response.sendRedirect("farmerlogin.jsp");
        }
    }

}
