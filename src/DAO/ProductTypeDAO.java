/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.ProductType;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public interface ProductTypeDAO {
     ObservableList<ProductType> getAllProductType();
     int getTypeIdByName (String name);
}
