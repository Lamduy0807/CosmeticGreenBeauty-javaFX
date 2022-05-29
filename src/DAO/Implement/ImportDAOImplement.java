/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.ImportDAO;
import Model.Import;
import Db.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class ImportDAOImplement implements ImportDAO {

   private static ImportDAO instance;
   private Connection connection;
   private static final Logger LOG = Logger.getLogger(ImportDAO.class.getName());
   private ImportDAOImplement() throws ClassNotFoundException{
       connection = DbConnection.getConnect();
   }
   public static ImportDAO getInstance() throws ClassNotFoundException{
       if(instance == null)
       {
           instance = new ImportDAOImplement();
       }
       return instance;
   }
    @Override
    public String InsertNewImport(Import impor) {
        String sql ="INSERT INTO ImportForm (Employee_id, Suplier_id,FormDate,TotalPrice) VALUES (?, ?, ?, ?)"
                + "Select Scope_Identity()";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String id = "";
    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, impor.getEmployee_id());
      preparedStatement.setInt(2, impor.getSuplier_id());
      preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()) );
      preparedStatement.setFloat(4, impor.getTotalPrice());
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
