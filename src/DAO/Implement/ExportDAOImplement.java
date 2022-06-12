/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.ExportDAO;
import Db.DbConnection;
import Model.Export;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Duy
 * this file is an implement for ExportDAO
 */
public class ExportDAOImplement implements ExportDAO{
    private static ExportDAO instance;
   private Connection connection;
   private static final Logger LOG = Logger.getLogger(ExportDAO.class.getName());
   private ExportDAOImplement() throws ClassNotFoundException{
       connection = DbConnection.getConnect();
   }
   public static ExportDAO getInstance() throws ClassNotFoundException{
       if(instance == null)
       {
           instance = new ExportDAOImplement();
       }
       return instance;
   }
   // Insert data to ExportForm in database
    @Override
    public String InsertNewExport(Export impor) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql ="INSERT INTO ExportForm (Employee_id, ExportReason,ExportDate,TotalMoney) VALUES (?, ?, ?, ?)"
                + "Select Scope_Identity()";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String id = "";
    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, impor.getEmployee_id());
      preparedStatement.setString(2, impor.getExportReason());
      preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()) );
      preparedStatement.setFloat(4, impor.getTotalMoney());
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next())
        {
            id = resultSet.getString(1);
        }
    } catch (SQLException e) {
      System.out.print("Error when inserting!!");
    }
    return id;
    }
}
