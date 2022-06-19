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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    private JFXTextField tbSearch;
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
            btnAdd.setDisable(false);
            btnDelete.setDisable(true);
            btnEdit.setDisable(true);
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

        tbSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!"".equals(newValue)) {
                ObservableList<Product> products = FXCollections.observableArrayList();
                try {
                    products = ProductDAOImplement.getInstance().searchProduct(newValue);
                    Producttb.setItems(products);
                    Producttb.refresh();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    FillData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tbPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!"".equals(newValue)) {
                if (newValue.matches(".*[a-zA-Z].*")) {
                    tbPrice.setText(oldValue);
                    Notifications.create().title("ERROR").text("Price must be number")
                            .showError();

                }

            }
        });

    }

    private void FillData() throws ClassNotFoundException {
        ObservableList<Product> products = ProductDAOImplement.getInstance().getListOfProduct();
        Producttb.setItems(products);
    }

    private void FillDataCombobox() throws ClassNotFoundException {
        ObservableList<ProductType> productTypes = ProductTypeDAOImplement.getInstance().getAllProductType();
        for (ProductType type : productTypes) {
            cbProductType.getItems().add(type.getTypeName());
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            if (!"".equals(tbName.getText()) && !"".equals(tbPrice.getText())
                    && !"".equals(tbUnit.getText()) && !"".equals(tbDes.getText())
                    && !"".equals(tbOrigin.getText()) && !"".equals(cbProductType.getSelectionModel().getSelectedItem())) {
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
            } else if ("".equals(tbName.getText())) {
                Notifications.create().title("WARNING").text("Please enter the name of product.")
                        .showWarning();
            } else if ("".equals(tbPrice.getText())) {
                Notifications.create().title("WARNING").text("Please enter the price of product.")
                        .showWarning();
            } else if ("".equals(tbUnit.getText())) {
                Notifications.create().title("WARNING").text("Please enter the unit of product.")
                        .showWarning();
            } else if ("".equals(tbDes.getText())) {
                Notifications.create().title("WARNING").text("Please enter the description of product.")
                        .showWarning();
            } else if ("".equals(tbOrigin.getText())) {
                Notifications.create().title("WARNING").text("Please enter the origin of product.")
                        .showWarning();
            }
        } else if (event.getSource() == btnDelete) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Are you sure you want to delete this product?");
            if (alert.showAndWait().get() == ButtonType.OK) {

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
            }
        } else if (event.getSource() == btnEdit) {
            if (!"".equals(tbName.getText()) && !"".equals(tbPrice.getText())
                    && !"".equals(tbUnit.getText()) && !"".equals(tbDes.getText())
                    && !"".equals(tbOrigin.getText()) && !"".equals(cbProductType.getSelectionModel().getSelectedItem())) {
                String id = tbID.getText();
                int iID = Integer.parseInt(id);
                String name = tbName.getText();
                Float price = Float.parseFloat(tbPrice.getText());
                String unit = tbUnit.getText();
                String des = tbDes.getText();
                String origin = tbOrigin.getText();
                String type = cbProductType.getSelectionModel().getSelectedItem();

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
            } else if ("".equals(tbName.getText())) {
                Notifications.create().title("WARNING").text("Please enter the name of product.")
                        .showWarning();
            } else if ("".equals(tbPrice.getText())) {
                Notifications.create().title("WARNING").text("Please enter the price of product.")
                        .showWarning();
            } else if ("".equals(tbUnit.getText())) {
                Notifications.create().title("WARNING").text("Please enter the unit of product.")
                        .showWarning();
            } else if ("".equals(tbDes.getText())) {
                Notifications.create().title("WARNING").text("Please enter the description of product.")
                        .showWarning();
            } else if ("".equals(tbOrigin.getText())) {
                Notifications.create().title("WARNING").text("Please enter the origin of product.")
                        .showWarning();
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
