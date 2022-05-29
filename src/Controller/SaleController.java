/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.ProductDAOImplement;
import Model.DetailBill;
import Model.DetailImport;
import Model.Product;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author DELL
 */
public class SaleController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private TableView<Product> Producttb;
    @FXML
    private TableColumn<Product, Integer> idCol;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, Float> priceCol;
    @FXML
    private TableColumn<Product, Integer> quantityCol;

    @FXML
    private TableView<DetailBill> Carttb;
    @FXML
    private TableColumn<DetailBill, Integer> idColCart;
    @FXML
    private TableColumn<DetailBill, String> nameColCart;
    @FXML
    private TableColumn<DetailBill, Float> priceColCart;
    @FXML
    private TableColumn<DetailBill, Integer> quantityColCart;

    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField tbName;
    @FXML
    private JFXTextField tbPhone;

    Product temp_product = null;
    DetailBill temp_detailBill=null;

    private ObservableList<DetailBill> detailBillData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory("Product_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory("ProductName"));
        priceCol.setCellValueFactory(new PropertyValueFactory("Price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory("Quantities"));

        idColCart.setCellValueFactory(new PropertyValueFactory("Product_id"));
        nameColCart.setCellValueFactory(new PropertyValueFactory("productName"));
        priceColCart.setCellValueFactory(new PropertyValueFactory("PresentPrice"));
        quantityColCart.setCellValueFactory(new PropertyValueFactory("Quantity"));

        try {
            FillData();
            btnAdd.setDisable(true);
            btnDelete.setDisable(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Producttb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        temp_product = newValue;
                        //selectItem(newValue);
                        btnAdd.setDisable(false);

                        //btnDelete.setDisable();
                        // btnEdit.setDisable(false);
                    }
                });

        Carttb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        temp_detailBill = newValue;
                        
                        btnDelete.setDisable(false);

                        
                    }
                });
    }

    private void FillData() throws ClassNotFoundException {
        ObservableList<Product> products = ProductDAOImplement.getInstance().getListOfProduct();
        Producttb.setItems(products);
    }

    
    public void ClearData() {
        tbName.setText("");
        tbPhone.setText("");
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            int ProductId = temp_product.getProduct_id();
            String name = temp_product.getProductName();
            int quantity = 1;
            float presentPrice = temp_product.getPrice();

            boolean isFound = false;
            for (DetailBill db : detailBillData) {
                if (db.getProduct_id() == ProductId) {
                    int temp = db.getQuantity();
                    db.setQuantity(quantity + temp);
                    isFound = true;
                }
            }

            if (!isFound) {
                DetailBill db = new DetailBill(ProductId, name, quantity, presentPrice);
                detailBillData.add(db);
                Carttb.setItems(detailBillData);
            } else {
                Carttb.refresh();
            }

        } else if (event.getSource() == btnDelete) {
            int id = temp_detailBill.getProduct_id();
           // float temp = 0;
            int index = 0;
            for (DetailBill db : detailBillData) {
                if (db.getProduct_id() == id) {
                    //temp = db.getTotal();
                    break;
                }
                index++;
            }
            detailBillData.remove(index);
            Carttb.refresh();
            
        }
       
    }

}
