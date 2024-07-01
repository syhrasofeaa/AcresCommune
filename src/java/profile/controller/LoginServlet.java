

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author syahira
 */

package profile.controller;

import profile.bean.ProfileBean;
import util.DBConnection;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       
        // Perform authentication
        ProfileBean loginBean = new ProfileBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);
        
        boolean isAuthenticated = authenticateUser(loginBean);
        
        if (isAuthenticated) {
            // Successful login
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("home.html"); // Redirect to home.jsp
        } else {
            // Unsuccessful login
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/farmerlogin.jsp").forward(request, response); // Forward back to login.jsp
        }
    }

    private boolean authenticateUser(ProfileBean loginBean) {
        String userName = loginBean.getUsername();
        String password = loginBean.getPassword();
        
        boolean isAuthenticated = false;

        // JDBC variables
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            
            con = DBConnection.createConnection();
            
            // Prepare the SQL query based on role
            String loginQuery = "SELECT * FROM FARMER WHERE FARMERUSERNAME = ? AND PASSWORD = ?";
            
            statement = con.prepareStatement(loginQuery);
            statement.setString(1, userName);
            statement.setString(2, password);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a matching user was found
            if (resultSet.next()) {
                isAuthenticated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isAuthenticated;
    }
}