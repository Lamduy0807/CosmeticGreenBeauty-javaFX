/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.BillDAOImplement;
import DAO.Implement.DetailBillDAOImplement;
import DAO.Implement.ProductDAOImplement;
import Holder.UserHolder;
import Model.Bill;
import Model.DetailBill;
import Model.Product;
import Model.User;
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
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;

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
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnCreateBill;

    @FXML
    private JFXTextField tbName;
    @FXML
    private JFXTextField tbPhone;

    @FXML
    private JFXTextField tbSearch;
    @FXML
    private Text txtTotal;

    Product temp_product = null;
    DetailBill temp_detailBill = null;

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
            btnCreateBill.setDisable(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Producttb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        temp_product = newValue;
                        btnAdd.setDisable(false);
                    }
                });

        Carttb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        temp_detailBill = newValue;
                        btnDelete.setDisable(false);
                        btnAdd.setDisable(true);
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
                    Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    FillData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Check wheather tbPhone contains value different with numbers or not (using RE)
        tbPhone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[0-9]+$") && !"".equals(newValue)) {
                tbPhone.setText(oldValue);
                Notifications.create().title("ERROR").text("Phone number must be number")
                        .showError();
            }
        });
    }

    //Get all data of product table
    private void FillData() throws ClassNotFoundException {
        ObservableList<Product> products = ProductDAOImplement.getInstance().getListOfProduct();
        Producttb.setItems(products);
    }

    //Calculate total proce for all items added 
    public void handelCalculateTotal() {
        float total = 0;
        for (DetailBill Db : detailBillData) {
            total = total + Db.getPresentPrice() * Db.getQuantity();
        }

        txtTotal.setText(Float.toString(total));
    }

    //Clear data in textboxes, text and combobox
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

            float tem = 0;

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
            handelCalculateTotal();
            btnCreateBill.setDisable(false);

        } else if (event.getSource() == btnDelete) {
            int id = temp_detailBill.getProduct_id();
            int index = 0;
            for (DetailBill db : detailBillData) {
                if (db.getProduct_id() == id) {
                    break;
                }
                index++;
            }
            detailBillData.remove(index);
            Carttb.refresh();

        } else if (event.getSource() == btnCreateBill) {
            if (!"".equals(tbName.getText()) && !"".equals(tbPhone.getText())) {

                UserHolder holder = UserHolder.getInstance();
                User u = holder.getUser();
                int Id = u.getiID();

                float total = Float.parseFloat(txtTotal.getText());
                String customerName = tbName.getText();
                String phoneNum = tbPhone.getText();

                Bill bill = new Bill(Id, customerName, phoneNum, total);
                try {
                    String id = BillDAOImplement.getInstance().createBill(bill);
                    if (!"".equals(id)) {
                        int ID = Integer.parseInt(id);
                        boolean flag = true;
                        for (DetailBill Db : detailBillData) {
                            flag = DetailBillDAOImplement.getInstance().addDetailBill(Db, ID);
                        }
                        System.out.print(flag ? "success" : "fail");
                        if (flag) {
                            Notifications.create().title("Information").text("Create successfully!!")
                                    .showInformation();
                            ClearData();
                            txtTotal.setText(Float.toString(0));

                            detailBillData.clear();
                            Carttb.refresh();
                            btnAdd.setDisable(true);
                            btnDelete.setDisable(true);

                            btnCreateBill.setDisable(true);
                        } else {
                            Notifications.create().title("ERROR").text("There were some errors. Please check again!!")
                                    .showError();
                        }
                    } else {
                        Notifications.create().title("ERROR").text("There were some errors. Please check again!!")
                                .showError();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("".equals(tbName.getText())) {
                Notifications.create().title("WARNING").text("Please enter the name of customer.")
                        .showWarning();

            } else if ("".equals(tbPhone.getText())) {
                Notifications.create().title("WARNING").text("Please enter the phone number of customer.")
                        .showWarning();

            }
        }

    }

}
