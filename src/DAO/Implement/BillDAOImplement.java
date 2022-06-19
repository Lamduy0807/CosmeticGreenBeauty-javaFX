/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.BillDAO;
import Db.DbConnection;
import Model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class BillDAOImplement implements BillDAO {

    private static BillDAO instance;
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(BillDAO.class.getName());

    private BillDAOImplement() throws ClassNotFoundException {
        connection = DbConnection.getConnect();
    }

    public static BillDAO getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new BillDAOImplement();
        }
        return instance;
    }

    //Insert data to Bill table in database
    @Override
    public String createBill(Bill bill) {
        String query = "INSERT INTO Bill (Employee_id, Cus_Name, PhoneNumber, BillValue, DateBill) VALUES (?, ?, ?, ?, ?)"
                + "Select Scope_Identity()";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String id = "";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bill.getEmployee_id());
            preparedStatement.setString(2, bill.getCus_Name());
            preparedStatement.setString(3, bill.getPhoneNumber());
            preparedStatement.setFloat(4, bill.getBillValue());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.print("Error when inserting!!");
        }
        return id;

    }

}
