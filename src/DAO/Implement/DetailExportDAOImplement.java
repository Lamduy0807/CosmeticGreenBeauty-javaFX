/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.DetailExportDAO;
import DAO.DetailImportDAO;
import Db.DbConnection;
import Model.DetailExport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DetailExportDAOImplement implements DetailExportDAO{
    private static DetailExportDAO instance;
   private Connection connection;
   private static final Logger LOG = Logger.getLogger(DetailExportDAO.class.getName());
   private DetailExportDAOImplement() throws ClassNotFoundException{
       connection = DbConnection.getConnect();
   }
   public static DetailExportDAO getInstance() throws ClassNotFoundException{
       if(instance == null)
       {
           instance = new DetailExportDAOImplement();
       }
       return instance;
   }
    @Override
    public boolean InsertToDatabase(DetailExport detail, int id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "INSERT INTO DetailExportForm (Product_id, ExportForm_id,Price,Quantity, Total) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;    
        try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, detail.getProduct_id());
      preparedStatement.setInt(2, id);
      preparedStatement.setFloat(3, detail.getPrice());
      preparedStatement.setInt(4, detail.getQuantity());
      preparedStatement.setFloat(5, detail.getTotal());
      
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      System.out.print("Error when inserting!!");
    }
    return false;
    }
    
}
