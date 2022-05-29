/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Product;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface ProductDAO {
    ObservableList<Product> getListOfProduct();
    ObservableList<Product> searchProduct(String search);
    Product getById(int product_id);
    int addProduct(Product product);
    int updateProduct(Product product);
    int deleteProduct(int product_id);
}
