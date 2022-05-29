/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.DetailBillDAO;
import Db.DbConnection;
import Model.DetailBill;
import Model.Product;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
/**
 *
 * @author DELL
 */
public class DetailBillDAOImplement implements DetailBillDAO{
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
    
//    private DetailBill setDataIntoResultSet(ResultSet r) throws SQLException {
//        
//        int billId = Integer.parseInt(r.getString(1));
//        int productId = Integer.parseInt(r.getString(2));
//        int quan = Integer.parseInt(r.getString(3));
//        float price = Float.parseFloat(r.getString(4));
//        
//        return new DetailBill(productId, quan, price);
//    }
    @Override
    public int addDetailBill(Product p) {
        
         String query = "INSERT INTO DetailBill (Bill_id, Product_id, Quantity, PresentPrice) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,1);
             preparedStatement.setInt(2,1);
              preparedStatement.setInt(3,1);
            preparedStatement.setFloat(4, p.getPrice());
            
            result = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.print("Error when inserting!!");
        }
        return result;
        
    }
    
}
