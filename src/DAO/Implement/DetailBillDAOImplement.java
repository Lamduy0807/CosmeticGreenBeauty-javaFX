/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.DetailBillDAO;
import Db.DbConnection;
import Model.DetailBill;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class DetailBillDAOImplement implements DetailBillDAO {

    private static DetailBillDAO instance;
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(DetailBillDAO.class.getName());

    private DetailBillDAOImplement() throws ClassNotFoundException {
        connection = DbConnection.getConnect();
    }

    public static DetailBillDAO getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DetailBillDAOImplement();
        }
        return instance;
    }

    @Override
    public boolean addDetailBill(DetailBill detailBill, int id) {
        String query = "INSERT INTO DetailBill (Bill_id, Product_id, Quantity, PresentPrice) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, detailBill.getProduct_id());
            preparedStatement.setInt(3, detailBill.getQuantity());
            preparedStatement.setFloat(4, detailBill.getPresentPrice());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.print("Error when inserting!!");
        }
        return false;

    }

}
