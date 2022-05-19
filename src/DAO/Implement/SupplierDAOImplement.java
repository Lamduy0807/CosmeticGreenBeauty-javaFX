/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.SuplierDAO;
import Db.DbConnection;
import Model.Supplier;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
/**
 *
 * @author Admin
 */
public class SupplierDAOImplement implements SuplierDAO{

   private static SuplierDAO instance;
   private Connection connection;
   private static final Logger LOG = Logger.getLogger(SuplierDAO.class.getName());
   private SupplierDAOImplement() throws ClassNotFoundException{
       connection = DbConnection.getConnect();
   }
   public static SuplierDAO getInstance() throws ClassNotFoundException{
       if(instance == null)
       {
           instance = new SupplierDAOImplement();
       }
       return instance;
   }
   private Supplier setDataIntoResultSet(ResultSet r) throws SQLException{
      String id = r.getString(1);
      int iId = Integer.parseInt(id);
      String name = r.getString(2);
      String add = r.getString(3);
      String phone = r.getString(4);
      String email = r.getString(5);
      return new Supplier(iId, name, email, phone, add);
   }
    @Override
    public ObservableList<Supplier> getListOfSuplier() {
        String sql = "SELECT Supplier_id, SuplierName, Address, PhoneNumber, Email from Supplier";
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    Supplier supplier = setDataIntoResultSet(resultSet);
                    suppliers.add(supplier);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return suppliers;
    }

    @Override
    public Supplier getSupplierByName(String name) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT Supplier_id, SuplierName, Address, PhoneNumber, Email from Supplier WHERE SuplierName = '"+ name + "'";
        Supplier supplier = new Supplier();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    supplier = setDataIntoResultSet(resultSet);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return supplier;
    }

    @Override
    public boolean AddSupplier(Supplier suplier) {
        String sql = "INSERT INTO Supplier (SuplierName, Address,PhoneNumber, Email) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement;    
        try {
      preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setString(1, suplier.getSuplierName());
      preparedStatement.setString(2, suplier.getAddress());
      preparedStatement.setString(3, suplier.getPhoneNumber());
      preparedStatement.setString(4, suplier.getEmail());
      
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      System.out.print("Error when inserting!!");
    }
    return false;
    }

    @Override
    public boolean DeleteSupplier(int id) {
        String sql = "DELETE Supplier WHERE Supplier_id = '"+ id +"'";
        PreparedStatement preparedStatement;    
        try {
      preparedStatement = connection.prepareStatement(sql);
      
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      System.out.print("Error when inserting!!");
    }
    return false;
    }

    @Override
    public boolean EditSupplier(Supplier supplier) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "UPDATE Supplier SET SuplierName = ?, Address = ?, PhoneNumber= ?, Email = ? WHERE Supplier_id = ?";
        PreparedStatement preparedStatement;    
        try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, supplier.getSuplierName());
      preparedStatement.setString(2, supplier.getAddress());
      preparedStatement.setString(3, supplier.getPhoneNumber());
      preparedStatement.setString(4, supplier.getEmail());
      preparedStatement.setInt(5, supplier.getSuplier_Id());
      
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      System.out.print("Error when inserting!!");
    }
    return false;
    }
    
}
