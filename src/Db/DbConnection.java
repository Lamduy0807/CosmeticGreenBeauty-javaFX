/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DbConnection {

    public static Connection getConnection(String sqlServer, String dbName) throws ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + sqlServer + ":1433;database=" + dbName + ";user=sa;password=sa;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("SQL Ex: " + e.toString());
        }
        return conn;
    }

    public static Connection getConnect() throws ClassNotFoundException {
//        Connection conn = getConnection("DESKTOP-1I3F1F7\\SQLEXPRESS", "ComesticDB");
        Connection conn = getConnection("DESKTOP-6NG98G3\\SQLEXPRESS", "ComesticDB");
        return conn;
    }
}
