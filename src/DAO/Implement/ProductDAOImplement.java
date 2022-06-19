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

public class ProductDAOImplement implements ProductDAO {

    private static ProductDAO instance;
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(ProductDAO.class.getName());

    private ProductDAOImplement() throws ClassNotFoundException {
        connection = DbConnection.getConnect();
    }

    public static ProductDAO getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new ProductDAOImplement();
        }
        return instance;
    }

    private Product setDataIntoResultSet(ResultSet r) throws SQLException {
        String id = r.getString(1);
        int iId = Integer.parseInt(id);
        String name = r.getString(2);
        String Price = r.getString(3);
        float price = Float.parseFloat(Price);
        String des = r.getString(4);
        String ori = r.getString(5);
        String unit = r.getString(6);
        int quan = Integer.parseInt(r.getString(7));
        String type = r.getString(8);
        return new Product(iId, name, price, unit, des, ori, quan, type);
    }

    @Override
    public ObservableList<Product> getListOfProduct() {
        String sql = "SELECT Product_id, ProductName, Price, Description, Origin, Unit, Quantities, TypeName from Product , ProductType where Product.ProductType = ProductType.ProductType_id";
        ObservableList<Product> products = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
        String sql = "select Product_id, ProductName, Price, Description, Origin, Unit,Quantities, TypeName from Product , ProductType where Product.ProductType = ProductType.ProductType_id and (Product_id like '" + search + "%' or ProductName like N'" + search + "%')";
        ObservableList<Product> products = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
    public Product getById(int product_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT Product_id, ProductName, Price, Description, Origin, Unit, Quantities, TypeName from Product , ProductType where Product_id= '"+product_id+"'";
        //ObservableList<Product> products = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = setDataIntoResultSet(resultSet);
                return product;
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int addProduct(Product p) {
        String query = "INSERT INTO Product (ProductName, Price, Description, Origin, Unit, Quantities, ProductType) VALUES (?, ?, ?, ?, ?,0, ?)";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, p.getProductName());
            preparedStatement.setFloat(2, p.getPrice());
            preparedStatement.setString(3, p.getDescription());
            preparedStatement.setString(4, p.getOriginal());
            preparedStatement.setString(5, p.getUnit());
            int iD = 0;
            try {
                iD = ProductTypeDAOImplement.getInstance().getTypeIdByName(p.getProductType());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProductDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }

            preparedStatement.setInt(6, iD);
            result = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.print("Error when inserting!!");
        }
        return result;
    }

    @Override
    public int updateProduct(Product p) {
        String query = "UPDATE Product SET ProductName = ?, Price = ?, Description= ?, Origin= ?, Unit = ?, ProductType=? WHERE Product_id = ?";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, p.getProductName());
            preparedStatement.setFloat(2, p.getPrice());
            preparedStatement.setString(3, p.getDescription());
            preparedStatement.setString(4, p.getOriginal());
            preparedStatement.setString(5, p.getUnit());
            int iD = 0;
            try {
                iD = ProductTypeDAOImplement.getInstance().getTypeIdByName(p.getProductType());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProductDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStatement.setInt(6, iD);
            preparedStatement.setInt(7, p.getProduct_id());

            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.print("Error when inserting!!");
        }
        return result;
    }

    @Override
    public int deleteProduct(int product_id) {
        String query = "DELETE Product WHERE Product_id = '" + product_id + "'";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(query);

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error when inserting!!");
        }
        return result;
    }

    @Override
    public boolean UpdateQuantities(int product_id, int quantities) {
        String sql = "UPDATE Product SET Quantities = Quantities + ? WHERE Product_id = ?";
        PreparedStatement preparedStatement;    
        try {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, quantities);
        preparedStatement.setInt(2, product_id);

        return preparedStatement.executeUpdate()> 0;
        
        } catch (SQLException e) {
            System.out.print("Error when update!! ");
        }
        return true;
    }
}
