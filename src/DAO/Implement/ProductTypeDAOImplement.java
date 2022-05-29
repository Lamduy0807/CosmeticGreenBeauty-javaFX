/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.ProductTypeDAO;
import Db.DbConnection;
import Model.ProductType;
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
public class ProductTypeDAOImplement implements ProductTypeDAO {

    private static ProductTypeDAO instance;
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(ProductTypeDAO.class.getName());

    private ProductTypeDAOImplement() throws ClassNotFoundException {
        connection = DbConnection.getConnect();
    }

    public static ProductTypeDAO getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new ProductTypeDAOImplement();
        }
        return instance;
    }

    private ProductType setDataIntoResultSet(ResultSet r) throws SQLException {
        String id = r.getString(1);
        int iId = Integer.parseInt(id);
        String name = r.getString(2);

        return new ProductType(iId, name);
    }

    @Override
    public ObservableList<ProductType> getAllProductType() {
        String query = "SELECT ProductType_id, TypeName FROM ProductType";
        ObservableList<ProductType> productTypes = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductType productType = setDataIntoResultSet(resultSet);
                productTypes.add(productType);
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productTypes;
    }

    @Override
    public int getTypeIdByName(String name) {
        int id = 0;
        String query = "SELECT ProductType_id FROM ProductType where TypeName = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            preparedStatement.close();

        } catch (SQLException ex) {
            System.out.print("Error when inserting!!");
        }

        return id;
    }

}
