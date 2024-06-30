/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
package profile.controller;

import util.DBConnection;
import profile.bean.ReadProfileBean;

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

@WebServlet(name = "ReadProfileServlet", urlPatterns = {"/ReadProfileServlet"})
public class ReadProfileServlet extends HttpServlet {

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

                String readQuery = "SELECT * FROM FARMER WHERE FARMERUSERNAME = ?";
                stmt = conn.prepareStatement(readQuery);
                stmt.setString(1, username);

                rs = stmt.executeQuery();

                // Initialize a ReadProfileBean object to hold the data
                ReadProfileBean profileBean = new ReadProfileBean();

                if (rs.next()) {
                    // Set data to the ReadProfileBean object
                    profileBean.setFarmerID(rs.getString("FARMERID"));
                    profileBean.setName(rs.getString("FARMERNAME"));
                    profileBean.setPassword(rs.getString("PASSWORD"));
                    profileBean.setEmail(rs.getString("EMAIL"));
                    profileBean.setUsername(rs.getString("FARMERUSERNAME"));
                }

                // Set the profileBean as an attribute
                request.setAttribute("profileBean", profileBean);

                // Forward the request to readprofile.jsp
                request.getRequestDispatcher("readprofile.jsp").forward(request, response);

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
