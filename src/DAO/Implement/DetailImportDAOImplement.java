/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.DetailImportDAO;
import Db.DbConnection;
import Model.DetailImport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Duy
 * this file is an implement for DetailImportDAO
 */
public class DetailImportDAOImplement implements DetailImportDAO{
    private static DetailImportDAO instance;
   private Connection connection;
   private static final Logger LOG = Logger.getLogger(DetailImportDAO.class.getName());
   private DetailImportDAOImplement() throws ClassNotFoundException{
       connection = DbConnection.getConnect();
   }
   public static DetailImportDAO getInstance() throws ClassNotFoundException{
       if(instance == null)
       {
           instance = new DetailImportDAOImplement();
       }
       return instance;
   }
   //Insert data into DetailImportForm in database
    @Override
    public boolean InsertToDatabase(DetailImport detail, int id) {       
        String sql = "INSERT INTO DetailImportForm (Product_id, ImportForm_id,ImportPrice,Quantity, Total) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;    
        try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, detail.getProduct_id());
      preparedStatement.setInt(2, id);
      preparedStatement.setFloat(3, detail.getImportPrice());
      preparedStatement.setInt(4, detail.getQuantities());
      preparedStatement.setFloat(5, detail.getTotal());
      
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      System.out.print("Error when inserting!!");
    }
    return false;
    }
    
}
