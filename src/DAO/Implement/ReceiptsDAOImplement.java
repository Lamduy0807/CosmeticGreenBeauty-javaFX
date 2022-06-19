/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.ReceiptsDAO;
import Db.DbConnection;
import Model.Receipts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class ReceiptsDAOImplement implements ReceiptsDAO {

    private static ReceiptsDAO instance;
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(ReceiptsDAO.class.getName());

    private ReceiptsDAOImplement() throws ClassNotFoundException {
        connection = DbConnection.getConnect();
    }

    public static ReceiptsDAO getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new ReceiptsDAOImplement();
        }
        return instance;
    }

    private Receipts setDataIntoResultSet(ResultSet r) throws SQLException {

        int receiptId = Integer.parseInt(r.getString(1));
        int employeeId = Integer.parseInt(r.getString(2));
        String content = r.getString(3);
        float totalPay = Float.parseFloat(r.getString(4));
        String status = r.getString(5);
        String createDate = r.getString(6);

        return new Receipts(receiptId, employeeId, content, totalPay, status, createDate);
    }

    @Override
    public ObservableList<Receipts> getAllReceipts() {
        String query = "SELECT Receipt_id, Employee_id, Content, TotalPay, Status, CreateDate FROM Receipt";
        ObservableList<Receipts> receipts = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Receipts receipt = setDataIntoResultSet(resultSet);
                receipts.add(receipt);
            }

            preparedStatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(ReceiptsDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return receipts;
    }
    @Override
    public int addReceipts(Receipts receipt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
