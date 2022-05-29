/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.ProductDAOImplement;
import DAO.Implement.ProductTypeDAOImplement;
import Model.Product;
import Model.ProductType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;
import com.jfoenix.controls.JFXComboBox;

/**
 *
 * @author DELL
 */
public class ProductController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private JFXTextField tbID;
    @FXML
    private JFXTextField tbName;
    @FXML
    private JFXTextField tbPrice;
    @FXML
    private JFXTextField tbUnit;
    @FXML
    private JFXTextField tbDes;
    @FXML
    private JFXTextField tbOrigin;

    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<Product> Producttb;
    @FXML
    private TableColumn<Product, Integer> idCol;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, Float> priceCol;
    @FXML
    private TableColumn<Product, String> unitCol;
    @FXML
    private TableColumn<Product, String> desCol;
    @FXML
    private TableColumn<Product, String> oriCol;
    @FXML
    private TableColumn<Product, String> typeCol;
    
    @FXML
    private JFXComboBox<String> cbProductType;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idCol.setCellValueFactory(new PropertyValueFactory("Product_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory("ProductName"));
        priceCol.setCellValueFactory(new PropertyValueFactory("Price"));
        unitCol.setCellValueFactory(new PropertyValueFactory("Unit"));
        desCol.setCellValueFactory(new PropertyValueFactory("Description"));
        oriCol.setCellValueFactory(new PropertyValueFactory("Original"));
        typeCol.setCellValueFactory(new PropertyValueFactory("ProductType"));

        try {
            FillData();
            FillDataCombobox();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Producttb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectItem(newValue);
                        btnAdd.setDisable(true);
                        btnDelete.setDisable(false);
                        btnEdit.setDisable(false);
                    }
                });
    }

    private void FillData() throws ClassNotFoundException {
        ObservableList<Product> products = ProductDAOImplement.getInstance().getListOfProduct();
        Producttb.setItems(products);
    }
    
    private void FillDataCombobox() throws ClassNotFoundException{
        ObservableList<ProductType> productTypes = ProductTypeDAOImplement.getInstance().getAllProductType();
        for(ProductType type: productTypes)
        {
            cbProductType.getItems().add(type.getTypeName());
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            if (!"".equals(tbName.getText()) && !"".equals(tbPrice.getText()) && !"".equals(tbUnit.getText()) && !"".equals(tbDes.getText()) && !"".equals(tbOrigin.getText()) && !"".equals(cbProductType.getSelectionModel().getSelectedItem())) {
                String name = tbName.getText();
                float price = Float.parseFloat(tbPrice.getText());
                String unit = tbUnit.getText();
                String des = tbDes.getText();
                String origin = tbOrigin.getText();
               
                String type = cbProductType.getSelectionModel().getSelectedItem();
               
                Product product = new Product(name, price, unit, des, origin, type);
                try {
                    if (ProductDAOImplement.getInstance().addProduct(product) == 1) {
                        ClearData();
                        FillData();
                        Producttb.refresh();
                        Notifications.create().title("Success").text("Add new Product sucessfully!!")
                                .showInformation();
                        btnAdd.setDisable(true);
                        btnDelete.setDisable(true);
                        btnEdit.setDisable(true);
                    } else {
                        Notifications.create().title("ERROR").text("There is some errors occurred! Please check again")
                                .showError();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (event.getSource() == btnDelete) {
            String id = tbID.getText();
            int iID = Integer.parseInt(id);
            try {
                if (ProductDAOImplement.getInstance().deleteProduct(iID) == 1) {
                    ClearData();
                    FillData();
                    Producttb.refresh();
                   
                    Notifications.create().title("Success").text("Delete Product sucessfully!!")
                            .showInformation();
                    btnAdd.setDisable(true);
                    btnDelete.setDisable(true);
                    btnEdit.setDisable(true);
                } else {
                    Notifications.create().title("ERROR").text("There is some errors occurred! Please check again")
                            .showError();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event.getSource() == btnEdit) {
            String id = tbID.getText();
            int iID = Integer.parseInt(id);
            String name = tbName.getText();
            Float price = Float.parseFloat(tbPrice.getText());
            String unit = tbUnit.getText();
            String des = tbDes.getText();
            String origin = tbOrigin.getText();
            String type = cbProductType.getSelectionModel().getSelectedItem();
            
            System.out.println("type: "+type);

            Product product = new Product(iID, name, price, unit, des, origin, type);
            try {
                if (ProductDAOImplement.getInstance().updateProduct(product) == 1) {
                    ClearData();
                    FillData();
                    Producttb.refresh();
                    Notifications.create().title("Success").text("Edit employee sucessfully!!")
                            .showInformation();
                    btnAdd.setDisable(true);
                    btnDelete.setDisable(true);
                    btnEdit.setDisable(true);
                } else {
                    Notifications.create().title("ERROR").text("There is some errors occurred! Please check again")
                            .showError();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void selectItem(Product p) {

        tbID.setText(String.valueOf(p.getProduct_id()));
        tbName.setText(p.getProductName());
        tbPrice.setText(String.valueOf(p.getPrice()));
        tbUnit.setText(p.getUnit());
        tbDes.setText(p.getDescription());
        tbOrigin.setText(p.getOriginal());
        cbProductType.setValue(p.getProductType());
        
    }

    public void ClearData() {
        tbID.setText("");
        tbName.setText("");
        tbPrice.setText("");
        tbUnit.setText("");
        tbDes.setText("");
        tbOrigin.setText("");
        

    }

}
