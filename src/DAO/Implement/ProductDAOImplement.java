/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.ProductDAO;
import Db.DbConnection;
import Model.Product;
import javafx.collections.ObservableList;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
/**
 *
 * @author Admin
 */
public class ProductDAOImplement implements ProductDAO {

  private static ProductDAO instance;
   private Connection connection;
   private static final Logger LOG = Logger.getLogger(ProductDAO.class.getName());
   private ProductDAOImplement() throws ClassNotFoundException{
       connection = DbConnection.getConnect();
   }
   public static ProductDAO getInstance() throws ClassNotFoundException{
       if(instance == null)
       {
           instance = new ProductDAOImplement();
       }
       return instance;
   }
   private Product setDataIntoResultSet(ResultSet r) throws SQLException{
      String id = r.getString(1);
      int iId = Integer.parseInt(id);
      
      String name = r.getString(2);
      float Price = r.getFloat(3);
      String des = r.getString(4);
      String ori = r.getString(5);
      String unit = r.getString(6);
      String type = r.getString(7);
      return new Product(iId, name, Price, unit, des, ori, type);
   }
    @Override
    public ObservableList<Product> getListOfProduct() {
        String sql = "select Product_id, ProductName, Price, Description, Origin, Unit, TypeName from Product , ProductType where Product.ProductType = ProductType.ProductType_id";
        ObservableList<Product> products = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    Product product = setDataIntoResultSet(resultSet);
                    products.add(product);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return products;
    }

    @Override
    public ObservableList<Product> searchProduct(String search) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "select Product_id, ProductName, Price, Description, Origin, Unit, TypeName from Product , ProductType where Product.ProductType = ProductType.ProductType_id and (Product_id like '" + search + "%' or ProductName like N'" + search + "%')";
        ObservableList<Product> products = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    Product product = setDataIntoResultSet(resultSet);
                    products.add(product);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return products;
    }
    
}
