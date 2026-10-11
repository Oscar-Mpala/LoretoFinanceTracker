package loretofinance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ReportDAO {
    
    public static double getTotalIncome() {
        double total = 0;
        String sql = "SELECT SUM(Amount) AS Total FROM income";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("Total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error calculating income: " + e.getMessage());
        }
        return total;
    }

    public static double getTotalExpense() {
        double total = 0;
        String sql = "SELECT SUM(Amount) AS Total FROM expenses";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("Total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error calculating expenses: " + e.getMessage());
        }
        return total;
    }
}