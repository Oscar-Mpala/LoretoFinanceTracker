package formstest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {
    // Change "password" to match your local MySQL credentials
    private static final String URL = "jdbc:mysql://localhost:3306/LoretoFinance";
    private static final String USER = "root";
    private static final String PASSWORD = "Nashe16032410!"; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // This loads the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }
}