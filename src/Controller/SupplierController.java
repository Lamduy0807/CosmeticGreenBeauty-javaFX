/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.SupplierDAOImplement;
import Model.Supplier;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Duy this file is responsible as a controller for supplier.fxml in
 * folder View
 */
public class SupplierController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private JFXTextField tbID;

    @FXML
    private JFXTextField tbName;

    @FXML
    private JFXTextField tbEmail;

    @FXML
    private JFXTextField tbAddress;

    @FXML
    private JFXTextField tbPhone;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<Supplier> Suppliertb;

    @FXML
    private TableColumn<Supplier, Integer> pIdCol;

    @FXML
    private TableColumn<Supplier, String> pNameCol;

    @FXML
    private TableColumn<Supplier, String> pEmailCol;

    @FXML
    private TableColumn<Supplier, String> pAddressCol;

    @FXML
    private TableColumn<Supplier, String> pPhoneCol;

    //handle event for each button
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            if (!"".equals(tbName.getText()) && !"".equals(tbEmail.getText())
                    && !"".equals(tbAddress.getText()) && !"".equals(tbPhone.getText())) {
                String email = tbEmail.getText();
                String name = tbName.getText();
                String add = tbAddress.getText();
                String phone = tbPhone.getText();
                //Get datafrom textboxes and convert into a Model
                Supplier suplier = new Supplier(name, email, phone, add);
                try {
                    //Successfully add a new supplier
                    if (SupplierDAOImplement.getInstance().AddSupplier(suplier)) {
                        ClearData();
                        FillData();
                        Suppliertb.refresh();
                        Notifications.create().title("Success").text("Add new supplier sucessfully!!")
                                .showInformation();
                        btnAdd.setDisable(true);
                        btnDelete.setDisable(true);
                        btnEdit.setDisable(true);
                    } else {
                        Notifications.create().title("ERROR").text("There is some errors occurred! Please check again")
                                .showError();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("".equals(tbName.getText())) {
                Notifications.create().title("WARNING").text("Please enter the name of supplier.")
                        .showWarning();
            } else if ("".equals(tbEmail.getText())) {
                Notifications.create().title("WARNING").text("Please enter the email of supplier.")
                        .showWarning();
            } else if ("".equals(tbAddress.getText())) {
                Notifications.create().title("WARNING").text("Please enter the address of supplier.")
                        .showWarning();
            } else if ("".equals(tbPhone.getText())) {
                Notifications.create().title("WARNING").text("Please enter the phone number of supplier.")
                        .showWarning();
            }
        } else if (event.getSource() == btnDelete) {
            //Delete a supplier
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Are you sure you want to delete this supplier?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                String id = tbID.getText();
                int iID = Integer.parseInt(id);
                try {
                    if (SupplierDAOImplement.getInstance().DeleteSupplier(iID)) {
                        ClearData();
                        FillData();
                        Suppliertb.refresh();
                        Notifications.create().title("Success").text("Delete supplier sucessfully!!")
                                .showInformation();
                        btnAdd.setDisable(true);
                        btnDelete.setDisable(true);
                        btnEdit.setDisable(true);
                    } else {
                        Notifications.create().title("ERROR").text("There is some errors occurred! Please check again")
                                .showError();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (event.getSource() == btnEdit) {
            //Edit a supplier information
            String id = tbID.getText();
            int iID = Integer.parseInt(id);
            String email = tbEmail.getText();
            String name = tbName.getText();
            String add = tbAddress.getText();
            String phone = tbPhone.getText();
            Supplier suplier = new Supplier(iID, name, email, phone, add);
            try {
                if (SupplierDAOImplement.getInstance().EditSupplier(suplier)) {
                    ClearData();
                    FillData();
                    Suppliertb.refresh();
                    Notifications.create().title("Success").text("Edit supplier sucessfully!!")
                            .showInformation();
                    btnAdd.setDisable(true);
                    btnDelete.setDisable(true);
                    btnEdit.setDisable(true);
                } else {
                    Notifications.create().title("ERROR").text("There is some errors occurred! Please check again")
                            .showError();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //fill data in to supplier table
    private void FillData() throws ClassNotFoundException {
        ObservableList<Supplier> suppliers = SupplierDAOImplement.getInstance().getListOfSuplier();
        Suppliertb.setItems(suppliers);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set value for column in Suppliertb
        pIdCol.setCellValueFactory(new PropertyValueFactory<>("Suplier_Id"));
        pNameCol.setCellValueFactory(new PropertyValueFactory<>("SuplierName"));
        pEmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        pAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        pPhoneCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        try {
            //Fill supplier's data into Suppliertb
            FillData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Handel when click on a row in Suppliertb
        Suppliertb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectItem(newValue);
                        btnAdd.setDisable(true);
                        btnDelete.setDisable(false);
                        btnEdit.setDisable(false);
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

    //handle event when click into a row in suplier table
    private void selectItem(Supplier supplier) {

        tbID.setText(String.valueOf(supplier.getSuplier_Id()));
        tbName.setText(supplier.getSuplierName());
        tbEmail.setText(supplier.getEmail());
        tbAddress.setText(supplier.getAddress());
        tbPhone.setText(supplier.getPhoneNumber());
    }

    //handle for clear data in textfield
    public void ClearData() {
        tbID.setText("");
        tbName.setText("");
        tbEmail.setText("");
        tbAddress.setText("");
        tbPhone.setText("");
    }
}
