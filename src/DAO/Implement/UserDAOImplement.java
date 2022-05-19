/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.UserDAO;
import Db.DbConnection;
import Model.Product;
import Model.User;
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
 * @author Admin
 */
public class UserDAOImplement implements UserDAO{
    private static UserDAO instance;
   private Connection connection;
   private static final Logger LOG = Logger.getLogger(UserDAO.class.getName());
   private UserDAOImplement() throws ClassNotFoundException{
       connection = DbConnection.getConnect();
   }
   public static UserDAO getInstance() throws ClassNotFoundException{
       if(instance == null)
       {
           instance = new UserDAOImplement();
       }
       return instance;
   }
   private User setDataIntoResultSet(ResultSet r) throws SQLException{
      String id = r.getString(1);
      int iId = Integer.parseInt(id);
      
      String name = r.getString(2);
      String citizen = r.getString(3);
      String add = r.getString(4);
      String phone = r.getString(5);
      String email = r.getString(6);
      String position = r.getString(7);
      String username = r.getString(8);
      String pass = r.getString(9);
      return new User(username, pass, position, iId, name, citizen, add, phone, email);
   }
    @Override
    public User getUserInformationByUsername(String un) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "select * from Employee where Username = '"+un+"'";
        User user = new User();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    user = setDataIntoResultSet(resultSet);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return user;
    }
    
}
