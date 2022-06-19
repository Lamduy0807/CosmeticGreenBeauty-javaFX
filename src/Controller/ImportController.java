/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.DetailImportDAOImplement;
import DAO.Implement.ImportDAOImplement;
import DAO.Implement.ProductDAOImplement;
import DAO.Implement.SupplierDAOImplement;
import Holder.UserHolder;
import Model.DetailImport;
import Model.Import;
import Model.Product;
import Model.Supplier;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Duy
 * this file is a controller for Import.fxml
 */
public class ImportController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private TableView<DetailImport> DetailImporttb;
    @FXML
    private TableColumn<DetailImport, Integer> dpProIdCol;
    @FXML
    private TableColumn<DetailImport, String> dpProNameCol;
    @FXML
    private TableColumn<DetailImport, Float> dpImportPriceCol;
    @FXML
    private TableColumn<DetailImport, Integer> dpQuanCol;
    @FXML
    private TableColumn<DetailImport, Float> dpTotalCol;
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
    private JFXTextField tbImportPrice;

    @FXML
    private JFXTextField tbProductName;

    @FXML
    private JFXTextField tbQuantities;

    @FXML
    private JFXTextField tbPrice;
    @FXML
    private JFXTextField tbSearch;

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
    private JFXComboBox<String> cbSupplier;

    @FXML
    private Text txtTotal;

    private ObservableList<DetailImport> detailimports = FXCollections.observableArrayList();

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
        dpImportPriceCol.setCellValueFactory(new PropertyValueFactory<>("ImportPrice"));
        dpQuanCol.setCellValueFactory(new PropertyValueFactory<>("Quantities"));
        dpTotalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));

        try {
            //Fill Data to the Product Table
            FillData();
            FillDataCombobox();
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
        DetailImporttb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectItemImported(newValue);
                    btnAdd.setDisable(true);
                    btnDelete.setDisable(false);
                    btnEdit.setDisable(false);
                });
        //Handle when tbSearch change
        tbSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("textfield changed from " + oldValue + " to " + newValue);
            if (!"".equals(newValue)) {
                ObservableList<Product> products = FXCollections.observableArrayList();
                try {
                    //Get the data of Product from Database when tbSearch Change
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
    //handle select a row in product table
    private void selectItem(Product product) {
        tbProductID.setText(String.valueOf(product.getProduct_id()));
        tbProductName.setText(product.getProductName());
        tbImportPrice.setText(String.valueOf(product.getPrice()));
    }
    //handle select a row in detailt table
    private void selectItemImported(DetailImport DT) {
        if (!detailimports.isEmpty()) {
            tbProductID.setText(String.valueOf(DT.getProduct_id()));
            tbProductName.setText(DT.getProductName());
            tbImportPrice.setText(String.valueOf(DT.getImportPrice()));
            tbQuantities.setText(String.valueOf(DT.getQuantities()));
            tbPrice.setText(String.valueOf(DT.getTotal()));
        }
    }
    //Fill data from datbase into Product table
    private void FillData() throws ClassNotFoundException {
        ObservableList<Product> products = ProductDAOImplement.getInstance().getListOfProduct();
        Producttb.setItems(products);
    }
    //fill data form database into combobox
    private void FillDataCombobox() throws ClassNotFoundException {
        ObservableList<Supplier> suppliers = SupplierDAOImplement.getInstance().getListOfSuplier();
        for (Supplier sup : suppliers) {
            cbSupplier.getItems().add(sup.getSuplierName());
        }
    }
    //calculate price when textbox quantities changed
    public void handleCalculatePrice() {
        if (!"".equals(tbQuantities.getText()) && !"".equals(tbImportPrice.getText())) {
            float importprice = Float.parseFloat(tbImportPrice.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float result = importprice * quan;
            tbPrice.setText(Float.toString(result));
        }
    }
    //Calculate total proce for all items added 
    public void handelCalculateTotal() {
        float total = 0;
        for (DetailImport di : detailimports) {
            total = total + di.getImportPrice();
        }

        txtTotal.setText(Float.toString(total));
    }
    //Clear data in textboxes, text and combobox
    public void ClearData() {
        tbProductID.setText("");
        tbImportPrice.setText("");
        tbProductName.setText("");
        tbQuantities.setText("");
        tbPrice.setText("");
    }
    // handle event for each button
    @Override
    public void handle(ActionEvent event) {

        if (event.getSource() == btnAdd) {
            //Add value to DetailImporttb
            int id = Integer.parseInt(tbProductID.getText());
            String name = tbProductName.getText();
            float importprice = Float.parseFloat(tbImportPrice.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float price = Float.parseFloat(tbPrice.getText());
            float tem = 0;

            boolean isFound = false;
            for (DetailImport DT : detailimports) {
                if (DT.getProduct_id() == id) {
                    //Check if DetailExporttb's already had that product or not
                    int temp = DT.getQuantities();
                    DT.setQuantities(quan + temp);
                    float temp2 = DT.getTotal();
                    tem = temp2;
                    DT.setTotal(price + temp2);

                    isFound = true;
                }
            }

            if (!isFound) {
                DetailImport di = new DetailImport(id, name, 0, importprice, quan, price);
                detailimports.add(di);
                DetailImporttb.setItems(detailimports);
            } else {
                DetailImporttb.refresh();
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
        } else if (event.getSource() == btnEdit) {
            //Get value from texts
            int id = Integer.parseInt(tbProductID.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float price = Float.parseFloat(tbPrice.getText());
            float temp = 0;
            for (DetailImport DT : detailimports) {
                if (DT.getProduct_id() == id) {
                    temp = DT.getTotal();
                    DT.setQuantities(quan);
                    DT.setTotal(price);
                }
            }

            DetailImporttb.refresh();
            float total = Float.parseFloat(txtTotal.getText());
            txtTotal.setText(Float.toString(total - temp + price));
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            ClearData();
        } else if (event.getSource() == btnCancel) {
            ClearData();
            txtTotal.setText(Float.toString(0));
            cbSupplier.getSelectionModel().clearSelection();
            detailimports.clear();
            DetailImporttb.refresh();
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
            for (DetailImport DT : detailimports) {
                if (DT.getProduct_id() == id) {
                    temp = DT.getTotal();
                    break;
                }
                index++;
            }
            detailimports.remove(index);
            DetailImporttb.refresh();
            float total = Float.parseFloat(txtTotal.getText());
            txtTotal.setText(Float.toString(total - temp));
            btnDelete.setDisable(true);
            btnEdit.setDisable(true);
            if (detailimports.isEmpty()) {
                btnCreate.setDisable(true);
            }
            ClearData();
        } else if (event.getSource() == btnCreate) {
            //Create a Import form
            if ("".equals(cbSupplier.getSelectionModel().getSelectedItem())) {
                Notifications.create().title("ERROR").text("You must select supplier!!!")
                        .showError();
            } else {
                UserHolder holder = UserHolder.getInstance();
                //Get the employee's ID through UserHolder
                User u = holder.getUser();
                int Id = u.getiID();
                int supId = 1;
                String supplier = cbSupplier.getSelectionModel().getSelectedItem();
                try {
                    Supplier sup = SupplierDAOImplement.getInstance().getSupplierByName(supplier);
                    supId = sup.getSuplier_Id();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
                }
                float total = Float.parseFloat(txtTotal.getText());
                Import im = new Import(Id, supId, total);
                try {
                    String id = ImportDAOImplement.getInstance().InsertNewImport(im);
                    if (!"".equals(id)) {
                        int ID = Integer.parseInt(id);
                        boolean flag = true;
                        for (DetailImport DT : detailimports) {
                            //Insert Data to DetailImportForm
                            DetailImportDAOImplement.getInstance().InsertToDatabase(DT, ID);
                            //Plus quantitties in Product's quantitites
                            flag = ProductDAOImplement.getInstance().UpdateQuantities(DT.getProduct_id(), DT.getQuantities());
                        }
                        System.out.print(flag ? "success" : "fail");
                        if (flag) {
                            Notifications.create().title("Information").text("Create successfully!!")
                                    .showInformation();
                            ClearData();
                            txtTotal.setText(Float.toString(0));
                            cbSupplier.getSelectionModel().clearSelection();
                            detailimports.clear();
                            DetailImporttb.refresh();
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
}
