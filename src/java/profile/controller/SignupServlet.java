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

import profile.bean.ProfileBean;
import util.DBConnection;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("name");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/farmersignup.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        ProfileBean signupBean = new ProfileBean();
        signupBean.setUsername(username);
        signupBean.setPassword(password);
        signupBean.setName(fullname);
        signupBean.setEmail(email);
        
        String userRegister = registerUser(signupBean);
        
        if ("SUCCESS".equals(userRegister)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            request.getRequestDispatcher("/farmerlogin.jsp").forward(request, response);
        } else {
            request.setAttribute("errMessage", userRegister);
            request.getRequestDispatcher("/farmersignup.jsp").forward(request, response);
        }
    }

    private String registerUser(ProfileBean signupBean) {
        String fullName = signupBean.getName();
        String email = signupBean.getEmail();
        String userName = signupBean.getUsername();
        String password = signupBean.getPassword();
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            con = DBConnection.createConnection();
            
            String queryCheck = "SELECT FARMERUSERNAME FROM FARMER WHERE FARMERUSERNAME = ?";
            preparedStatement = con.prepareStatement(queryCheck);
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return "Username already exists!";
            }
            
            rs.close();
            preparedStatement.close();
            
            String queryMaxId = "SELECT MAX(FARMERID) AS max_id FROM FARMER";
            preparedStatement = con.prepareStatement(queryMaxId);
            rs = preparedStatement.executeQuery();
            String newFarmerId = "FR01"; // Default value if no existing records

            if (rs.next() && rs.getString("max_id") != null) {
                String currentMaxId = rs.getString("max_id");
                int currentIdNum = Integer.parseInt(currentMaxId.substring(2));
                newFarmerId = "FR" + String.format("%02d", currentIdNum + 1);
            }
            
            rs.close();
            preparedStatement.close();
            
            String queryInsert = "INSERT INTO FARMER (FARMERID, FARMERNAME, PASSWORD, EMAIL, FARMERUSERNAME) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(queryInsert);
            preparedStatement.setString(1, newFarmerId);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, userName);
            
          
            
            int i = preparedStatement.executeUpdate();
            
            if (i != 0) { // Just to ensure data has been inserted into the database
                return "SUCCESS";
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "Something went wrong. Please try again.";
    }
}
