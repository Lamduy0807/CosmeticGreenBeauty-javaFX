/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.DetailExportDAOImplement;
import DAO.Implement.ExportDAOImplement;
import DAO.Implement.ProductDAOImplement;
import Holder.UserHolder;
import Model.DetailExport;
import Model.Export;
import Model.Product;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
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
 * @author Duy this file is a controller for Export.fxml file
 */
public class ExportController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private TableView<DetailExport> DetailExporttb;

    @FXML
    private TableColumn<DetailExport, Integer> dpProIdCol;

    @FXML
    private TableColumn<DetailExport, String> dpProNameCol;

    @FXML
    private TableColumn<DetailExport, Float> dpExportPriceCol;

    @FXML
    private TableColumn<DetailExport, Integer> dpQuanCol;

    @FXML
    private TableColumn<DetailExport, Float> dpTotalCol;

    @FXML
    private TableView<Product> Producttb;

    @FXML
    private TableColumn<Product, Integer> pIdCol;
    @FXML
    private TableColumn<Product, String> pNameCol;
    @FXML
    private TableColumn<Product, Float> pPriceCol;
    @FXML
    private TableColumn<Product, String> pUnitCol;
    @FXML
    private TableColumn<Product, String> pDescriptionCol;
    @FXML
    private TableColumn<Product, String> pOriginalCol;
    @FXML
    private TableColumn<Product, String> pTypeCol;

    @FXML
    private JFXTextField tbProductID;

    @FXML
    private JFXTextField tbExportPrice;

    @FXML
    private JFXTextField tbProductName;

    @FXML
    private JFXTextField tbQuantities;

    @FXML
    private JFXTextField tbPrice;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private Text txtTotal;

    @FXML
    private JFXTextField tbExportReason;

    @FXML
    private JFXTextField tbSearch;
    private ObservableList<DetailExport> detailexports = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Set value for ProductTable
        pIdCol.setCellValueFactory(new PropertyValueFactory<>("Product_id"));
        pNameCol.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        pPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        pUnitCol.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        pDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        pOriginalCol.setCellValueFactory(new PropertyValueFactory<>("Original"));
        pTypeCol.setCellValueFactory(new PropertyValueFactory<>("ProductType"));

        //Set title for Detail Table
        dpProIdCol.setCellValueFactory(new PropertyValueFactory<>("Product_id"));
        dpProNameCol.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        dpExportPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        dpQuanCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        dpTotalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));

        try {
            //Fill Data to the Table
            FillData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Get the data from Producttb's row and convert into text
        Producttb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!Bindings.isEmpty(Producttb.getItems()).get()) {
                        selectItem(newValue);
                        btnAdd.setDisable(false);
                        btnDelete.setDisable(true);
                        btnEdit.setDisable(true);
                        btnCancel.setDisable(false);
                    }

                });
        //Get the data from DetailExporttb's row and convert into text
        DetailExporttb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectItemExported(newValue);
                    btnAdd.setDisable(true);
                    btnDelete.setDisable(false);
                    btnEdit.setDisable(false);
                });
        //Handle when tbSearch change
        tbSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("textfield changed from " + oldValue + " to " + newValue);
            if (!"".equals(newValue)) {
                //Get the data of Product from Database when tbSearch Change
                ObservableList<Product> products = FXCollections.observableArrayList();
                try {
                    products = ProductDAOImplement.getInstance().searchProduct(newValue);
                    Producttb.setItems(products);
                    Producttb.refresh();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    FillData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //Check wheather tbQuantities contains value different with numbers or not (using RE)
        tbQuantities.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[0-9]+$") && !"".equals(newValue)) {
                tbQuantities.setText(oldValue);
                Notifications.create().title("ERROR").text("Quantities must be number")
                        .showError();
            }
        });
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        btnCancel.setDisable(true);
        btnCreate.setDisable(true);
    }

    //handle Click event for each button
    @Override
    public void handle(ActionEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (event.getSource() == btnAdd) {
            Product Pro = new Product();

            int id = Integer.parseInt(tbProductID.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float exportprice = Float.parseFloat(tbExportPrice.getText());
            float price = Float.parseFloat(tbPrice.getText());

            try {
                Pro = ProductDAOImplement.getInstance().getById(id);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ExportController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Check for the quantities in Database is enough for exporting?
            if (Pro.getQuantities() < quan) {
                Notifications.create().title("ERROR").text("The quantites of this product in houseware is not enough!!!")
                        .showError();
            } else {
                //Add value to DetailExporttb
                String name = tbProductName.getText();

                float tem = 0;

                boolean isFound = false;
                for (DetailExport DT : detailexports) {
                    if (DT.getProduct_id() == id) {
                        //Check if DetailExporttb's already had that product or not
                        int temp = DT.getQuantity();
                        DT.setQuantity(quan + temp);
                        float temp2 = DT.getTotal();
                        tem = temp2;
                        DT.setTotal(price + temp2);

                        isFound = true;
                    }
                }

                if (!isFound) {
                    DetailExport di = new DetailExport(id, 0, name, exportprice, quan, price);
                    detailexports.add(di);
                    DetailExporttb.setItems(detailexports);
                } else {
                    DetailExporttb.refresh();
                }
                handelCalculateTotal();

                float total = Float.parseFloat(txtTotal.getText());
                txtTotal.setText(Float.toString(total + tem));
                btnCreate.setDisable(false);
                btnCancel.setDisable(false);
                btnAdd.setDisable(true);
                btnEdit.setDisable(true);
                btnDelete.setDisable(true);
                ClearData();
            }
        } else if (event.getSource() == btnEdit) {
            //Get value from texts
            int id = Integer.parseInt(tbProductID.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float price = Float.parseFloat(tbPrice.getText());
            float temp = 0;
            for (DetailExport DT : detailexports) {
                if (DT.getProduct_id() == id) {
                    //Set update value to the product 
                    temp = DT.getTotal();
                    DT.setQuantity(quan);
                    DT.setTotal(price);
                }
            }

            DetailExporttb.refresh();
            float total = Float.parseFloat(txtTotal.getText());
            txtTotal.setText(Float.toString(total - temp + price));
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            ClearData();
        } else if (event.getSource() == btnCancel) {
            ClearData();
            tbExportReason.setText("");
            txtTotal.setText(Float.toString(0));
            detailexports.clear();
            DetailExporttb.refresh();
            btnAdd.setDisable(true);
            btnDelete.setDisable(true);
            btnEdit.setDisable(true);
            btnCancel.setDisable(true);
            btnCreate.setDisable(true);

        } else if (event.getSource() == btnDelete) {
            //Delete a value in DetailExporttb
            int id = Integer.parseInt(tbProductID.getText());
            float temp = 0;
            int index = 0;
            for (DetailExport DT : detailexports) {
                if (DT.getProduct_id() == id) {
                    temp = DT.getTotal();
                    break;
                }
                index++;
            }
            detailexports.remove(index);
            DetailExporttb.refresh();
            float total = Float.parseFloat(txtTotal.getText());
            txtTotal.setText(Float.toString(total - temp));
            btnDelete.setDisable(true);
            btnEdit.setDisable(true);
            if (detailexports.isEmpty()) {
                btnCreate.setDisable(true);
            }
            ClearData();
        } else if (event.getSource() == btnCreate) {
            //Create a Export form
            if ("".equals(tbExportReason.getText())) {
                Notifications.create().title("ERROR").text("You must fill Export reason!!!")
                        .showError();
            } else {
                UserHolder holder = UserHolder.getInstance();
                User u = holder.getUser();
                //Get the employee's ID through UserHolder
                int Id = u.getiID();
                String reason = tbExportReason.getText();
                float total = Float.parseFloat(txtTotal.getText());
                Export im = new Export(Id, reason, total);
                try {
                    String id = ExportDAOImplement.getInstance().InsertNewExport(im);
                    if (!"".equals(id)) {
                        int ID = Integer.parseInt(id);
                        boolean flag = true;
                        for (DetailExport DT : detailexports) {
                            //Insert Data to DetailExportForm
                            DetailExportDAOImplement.getInstance().InsertToDatabase(DT, ID);
                            //Substract quantitties in Product's quantitites
                            flag = ProductDAOImplement.getInstance().UpdateQuantities(DT.getProduct_id(), DT.getQuantity() * (-1));
                        }
                        System.out.print(flag ? "success" : "fail");
                        if (flag) {
                            Notifications.create().title("Information").text("Create successfully!!")
                                    .showInformation();
                            ClearData();
                            txtTotal.setText(Float.toString(0));
                            tbExportReason.setText("");
                            detailexports.clear();
                            DetailExporttb.refresh();
                            btnAdd.setDisable(true);
                            btnDelete.setDisable(true);
                            btnEdit.setDisable(true);
                            btnCancel.setDisable(true);
                            btnCreate.setDisable(true);
                        } else {
                            Notifications.create().title("ERROR").text("There were some errors. Please check again!!")
                                    .showError();
                        }
                    } else {
                        Notifications.create().title("ERROR").text("There were some errors. Please check again!!")
                                .showError();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //handle select a row in product table
    private void selectItem(Product product) {
        tbProductID.setText(String.valueOf(product.getProduct_id()));
        tbProductName.setText(product.getProductName());
        tbExportPrice.setText(String.valueOf(product.getPrice()));
    }

    //handle select a row in detailt table
    private void selectItemExported(DetailExport DT) {
        if (!detailexports.isEmpty()) {
            tbProductID.setText(String.valueOf(DT.getProduct_id()));
            tbProductName.setText(DT.getProductName());
            tbExportPrice.setText(String.valueOf(DT.getPrice()));
            tbQuantities.setText(String.valueOf(DT.getQuantity()));
            tbPrice.setText(String.valueOf(DT.getTotal()));
        }
    }

    //Fill data from datbase into Product table
    private void FillData() throws ClassNotFoundException {
        ObservableList<Product> products = ProductDAOImplement.getInstance().getListOfProduct();
        Producttb.setItems(products);
    }

    //calculate price when textbox quantities changed
    public void handleCalculatePrice() {
        if (!"".equals(tbQuantities.getText()) && !"".equals(tbExportPrice.getText())) {
            float importprice = Float.parseFloat(tbExportPrice.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float result = importprice * quan;
            tbPrice.setText(Float.toString(result));
        }
    }

    //Calculate total proce for all items added 
    public void handelCalculateTotal() {
        float total = 0;
        for (DetailExport di : detailexports) {
            total = total + di.getPrice();
        }

        txtTotal.setText(Float.toString(total));
    }

    //Clear data
    public void ClearData() {
        tbProductID.setText("");
        tbExportPrice.setText("");
        tbProductName.setText("");
        tbQuantities.setText("");
        tbPrice.setText("");
    }
}
