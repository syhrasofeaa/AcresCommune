/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments.controller;

import equipments.bean.ReadEquipmentBean;
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
@WebServlet(name = "ReadEquipmentServlet", urlPatterns = {"/ReadEquipmentServlet"})
public class ReadEquipmentServlet extends HttpServlet {

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

                String readQuery = "SELECT E.EQUIPMENTID, E.EQUIPMENTNAME, E.EQUIPMENTCONDITION, E.USEDDATE " +
                                   "FROM EQUIPMENT E INNER JOIN FARMER F ON E.FARMERID = F.FARMERID WHERE FARMERUSERNAME = ? ";
                stmt = conn.prepareStatement(readQuery);
                stmt.setString(1, farmerUsername);

                rs = stmt.executeQuery();

                // Initialize a ReadProfileBean object to hold the data
                ReadEquipmentBean readequipmentBean = new ReadEquipmentBean();

                if (rs.next()) {
                    // Set data to the ReadProfileBean object
                    readequipmentBean.setEquipmentID(rs.getString("EQUIPMENTID"));
                    readequipmentBean.setEquipmentName(rs.getString("EQUIPMENTNAME"));
                    readequipmentBean.setEquipmentCondition(rs.getString("EQUIPMENTCONDITION"));
                    readequipmentBean.setUsedDate(rs.getString("USEDDATE"));
                }

                // Set the profileBean as an attribute
                request.setAttribute("readequipmentBean", readequipmentBean);

                // Forward the request to readprofile.jsp
                request.getRequestDispatcher("readequipment.jsp").forward(request, response);

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
}
