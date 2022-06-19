/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.PayslipDAO;
import DAO.ReceiptsDAO;
import Db.DbConnection;
import Model.Payslip;
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
public class PayslipDAOImplement implements PayslipDAO {

    private static PayslipDAO instance;
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(PayslipDAO.class.getName());

    private PayslipDAOImplement() throws ClassNotFoundException {
        connection = DbConnection.getConnect();
    }

    public static PayslipDAO getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new PayslipDAOImplement();
        }
        return instance;
    }

    private Payslip setDataIntoResultSet(ResultSet r) throws SQLException {

        int receiptId = Integer.parseInt(r.getString(1));
        int employeeId = Integer.parseInt(r.getString(2));
        String content = r.getString(3);
        float totalPay = Float.parseFloat(r.getString(4));
        String status = r.getString(5);
        String createDate = r.getString(6);

        return new Payslip(receiptId, employeeId, content, totalPay, status, createDate);
    }

    @Override
    public ObservableList<Payslip> getAllPayslip() {
        String query = "SELECT Payslip_id, Employee_id, Content, TotalPay, Status, CreateDate FROM Payslip";
        ObservableList<Payslip> payslips = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Payslip payslip = setDataIntoResultSet(resultSet);
                payslips.add(payslip);
            }

            preparedStatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(PayslipDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payslips;
    }

    @Override
    public int addPayslip(Payslip payslip) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
