

import crops.bean.CropsBean;
import equipments.bean.EquipmentBean;

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

@WebServlet(name = "DashboardServlet", urlPatterns = {"/DashboardServlet"})
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmtProducts = null;
        PreparedStatement stmtCrops = null;
        PreparedStatement stmtEquipment = null;
        ResultSet rsProducts = null;
        ResultSet rsCrops = null;
        ResultSet rsEquipment = null;

        try {
            conn = DBConnection.createConnection(); // Adjust DBConnection to your implementation

            // Query to count total products
            String countProductsQuery = "SELECT COUNT(DISTINCT PRODUCTID) AS totalProducts FROM PRODUCT";
            stmtProducts = conn.prepareStatement(countProductsQuery);
            rsProducts = stmtProducts.executeQuery();
            int totalProducts = 0;
            if (rsProducts.next()) {
                totalProducts = rsProducts.getInt("totalProducts");
            }

            // Query to count total crops
            String countCropsQuery = "SELECT COUNT(DISTINCT CROPID) AS totalCrops FROM CROP";
            stmtCrops = conn.prepareStatement(countCropsQuery);
            rsCrops = stmtCrops.executeQuery();
            int totalCrops = 0;
            if (rsCrops.next()) {
                totalCrops = rsCrops.getInt("totalCrops");
            }

            // Query to count total equipment
            String countEquipmentQuery = "SELECT COUNT(DISTINCT EQUIPMENTID) AS totalEquipment FROM EQUIPMENT";
            stmtEquipment = conn.prepareStatement(countEquipmentQuery);
            rsEquipment = stmtEquipment.executeQuery();
            int totalEquipment = 0;
            if (rsEquipment.next()) {
                totalEquipment = rsEquipment.getInt("totalEquipment");
            }

            // Set attributes in request scope
            request.setAttribute("totalProducts", totalProducts);
            request.setAttribute("totalCrops", totalCrops);
            request.setAttribute("totalEquipment", totalEquipment);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred: " + e.getMessage());
        } finally {
            try {
                if (rsProducts != null) rsProducts.close();
                if (rsCrops != null) rsCrops.close();
                if (rsEquipment != null) rsEquipment.close();
                if (stmtProducts != null) stmtProducts.close();
                if (stmtCrops != null) stmtCrops.close();
                if (stmtEquipment != null) stmtEquipment.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Forward to dashboard.jsp
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}
