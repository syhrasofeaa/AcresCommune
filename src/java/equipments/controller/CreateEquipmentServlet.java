/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments.controller;

import util.DBConnection;
import equipments.bean.EquipmentBean;

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
@WebServlet(name = "CreateEquipmentServlet", urlPatterns = {"/CreateEquipmentServlet"})
public class CreateEquipmentServlet extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String farmerUsername = (String) session.getAttribute("username"); // Assuming "username" holds the farmerId

        if (farmerUsername != null) {
            String equipmentName = request.getParameter("equipmentName");
            String equipmentCondition = request.getParameter("equipmentCondition");
            String usedDate = request.getParameter("usedDate");
            
            EquipmentBean createequipmentBean = new EquipmentBean();
            createequipmentBean.setEquipmentName(equipmentName);
            createequipmentBean.setEquipmentCondition(equipmentCondition);
            createequipmentBean.setUsedDate(usedDate);

            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DBConnection.createConnection(); // Replace with your DB connection method
                
                String query = "SELECT FR.FARMERID " +
                                "FROM EQUIPMENT E " +
                                "INNER JOIN FARMER FR ON E.FARMERID = FR.FARMERID " +
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
            
            String queryMaxId = "SELECT MAX(EQUIPMENTID) AS max_id FROM EQUIPMENT";
            stmt = conn.prepareStatement(queryMaxId);
            rs = stmt.executeQuery();
            String newEquipmentId = "E01"; // Default value if no existing records

            if (rs.next() && rs.getString("max_id") != null) {
                String currentMaxId = rs.getString("max_id");
                int currentIdNum = Integer.parseInt(currentMaxId.substring(1));
                newEquipmentId = "E" + String.format("%02d", currentIdNum + 1);
            }
            
                String insertQuery = "INSERT INTO EQUIPMENT (EQUIPMENTID, EQUIPMENTNAME, EQUIPMENTCONDITION, USEDDATE, FARMERID) VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertQuery);

                stmt.setString(1, newEquipmentId);
                stmt.setString(2, createequipmentBean.getEquipmentName());
                stmt.setString(3, createequipmentBean.getEquipmentCondition());
                stmt.setString(4, createequipmentBean.getUsedDate());
                stmt.setString(5, farmerId);
                

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Task creation successful
                    request.setAttribute("successMessage", "Equipment created successfully.");
                    request.getRequestDispatcher("home.html").forward(request, response);
                } else {
                    // Task creation failed
                    request.setAttribute("errorMessage", "Failed to create tasks.");
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
            request.setAttribute("errorMessage", "You must be logged in to create an equipment.");
            response.sendRedirect("farmerlogin.jsp");
        }
    }
}
