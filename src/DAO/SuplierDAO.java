/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Supplier;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface SuplierDAO {
    ObservableList<Supplier> getListOfSuplier();
    Supplier getSupplierByName(String name);
    boolean AddSupplier(Supplier suplier);
    boolean DeleteSupplier(int id);
    boolean EditSupplier(Supplier supplier);
}
