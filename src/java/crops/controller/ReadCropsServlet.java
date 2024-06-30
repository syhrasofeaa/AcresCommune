/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crops.controller;

import crops.bean.CropsBean;
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
@WebServlet(name = "ReadCropsServlet", urlPatterns = {"/ReadCropsServlet"})
public class ReadCropsServlet extends HttpServlet {

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

                String readQuery = "SELECT C.CROPID, C.CROPNAME, C.CROPTYPE, C.PLANTINGDATE, C.PLANTINGDATE, C.HARVESTDATE " +
                                   "FROM CROP C INNER JOIN FARMER FR ON C.FARMERID = FR.FARMERID WHERE FARMERUSERNAME = ? ";
                stmt = conn.prepareStatement(readQuery);
                stmt.setString(1, farmerUsername);

                rs = stmt.executeQuery();

                // Initialize a ReadProfileBean object to hold the data
                CropsBean readcropsBean = new CropsBean();

                if (rs.next()) {
                    // Set data to the ReadProfileBean object
                    readcropsBean.setCropID(rs.getString("CROPID"));
                    readcropsBean.setCropName(rs.getString("CROPNAME"));
                    readcropsBean.setCropType(rs.getString("CROPTYPE"));
                    readcropsBean.setPlantingDate(rs.getString("PLANTINGDATE"));
                    readcropsBean.setHarvestDate(rs.getString("HARVESTDATE"));
                }

                // Set the profileBean as an attribute
                request.setAttribute("readcropsBean", readcropsBean);

                // Forward the request to readprofile.jsp
                request.getRequestDispatcher("readcrops.jsp").forward(request, response);

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
