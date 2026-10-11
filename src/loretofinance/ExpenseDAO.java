package loretofinance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ExpenseDAO {
    
    // Method to insert a new expense record
    public static void addExpense(String date, String category, double amount, String description) {
        
        // The SQL query matching Team 2's database structure
        String sql = "INSERT INTO expenses (Date, Category, Amount, Description) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            // Binding the UI variables to the ? placeholders in the SQL query
            pstmt.setString(1, date);
            pstmt.setString(2, category);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, description);
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Expense added successfully!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding expense: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void loadExpenseData(javax.swing.JTable table) {
        String sql = "SELECT * FROM expenses";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = pstmt.executeQuery()) {

            // Get the table model and clear any existing empty rows
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();
            model.setRowCount(0); 

            // Loop through the MySQL results and add them to the Java table
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getDate("Date"),
                    rs.getString("Category"),
                    rs.getDouble("Amount"),
                    rs.getString("Description")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void updateExpense(int id, String date, String category, double amount, String description) {
        String sql = "UPDATE expenses SET Date=?, Category=?, Amount=?, Description=? WHERE ID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, date);
            pstmt.setString(2, category);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, description);
            pstmt.setInt(5, id); // The ID tells MySQL exactly which row to change

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Expense updated successfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating expense: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE ID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting expense: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}