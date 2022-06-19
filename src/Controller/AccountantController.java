/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.PayslipDAOImplement;
import DAO.Implement.ReceiptsDAOImplement;
import Model.Payslip;
import Model.Receipts;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.text.Text;

/**
 *
 * @author DELL
 */
public class AccountantController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private TableView<Receipts> Receiptstb;
    @FXML
    private TableColumn<Receipts, Integer> receiptIdCol;
    @FXML
    private TableColumn<Receipts, Integer> employeeIdCol;
    @FXML
    private TableColumn<Receipts, String> contentCol;
    @FXML
    private TableColumn<Receipts, Float> totalPayCol;
    @FXML
    private TableColumn<Receipts, String> statusCol;
    @FXML
    private TableColumn<Receipts, String> createDateCol;

    @FXML
    private TableView<Payslip> Paysliptb;
    @FXML
    private TableColumn<Payslip, Integer> _receiptIdCol;
    @FXML
    private TableColumn<Payslip, Integer> _employeeIdCol;
    @FXML
    private TableColumn<Payslip, String> _contentCol;
    @FXML
    private TableColumn<Payslip, Float> _totalPayCol;
    @FXML
    private TableColumn<Payslip, String> _statusCol;
    @FXML
    private TableColumn<Payslip, String> _createDateCol;
    @FXML
    private JFXButton btnReceipt;
    @FXML
    private JFXButton btnPayslip;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //column of receipt table
        receiptIdCol.setCellValueFactory(new PropertyValueFactory("Receipt_id"));
        employeeIdCol.setCellValueFactory(new PropertyValueFactory("Employee_id"));
        contentCol.setCellValueFactory(new PropertyValueFactory("Content"));
        totalPayCol.setCellValueFactory(new PropertyValueFactory("TotalPay"));
        statusCol.setCellValueFactory(new PropertyValueFactory("Status"));
        createDateCol.setCellValueFactory(new PropertyValueFactory("CreateDate"));

        //column of payslip table
        _receiptIdCol.setCellValueFactory(new PropertyValueFactory("Payslip_id"));
        _employeeIdCol.setCellValueFactory(new PropertyValueFactory("Employee_id"));
        _contentCol.setCellValueFactory(new PropertyValueFactory("Content"));
        _totalPayCol.setCellValueFactory(new PropertyValueFactory("TotalPay"));
        _statusCol.setCellValueFactory(new PropertyValueFactory("Status"));
        _createDateCol.setCellValueFactory(new PropertyValueFactory("CreateDate"));

//        Paysliptb.disableProperty().setValue(Boolean.TRUE);
        Paysliptb.visibleProperty().setValue(Boolean.FALSE);
        try {
            FillDataReceipt();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnReceipt) {
            try {
                FillDataReceipt();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            Paysliptb.disableProperty().setValue(Boolean.TRUE);
            Paysliptb.visibleProperty().setValue(Boolean.FALSE);
//            Receiptstb.disableProperty().setValue(Boolean.FALSE);
            Receiptstb.visibleProperty().setValue(Boolean.TRUE);
        } else if (event.getSource() == btnPayslip) {
//            Paysliptb.disableProperty().setValue(Boolean.FALSE);
            try {
                FillDataPayslip();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Paysliptb.visibleProperty().setValue(Boolean.TRUE);
//            Receiptstb.disableProperty().setValue(Boolean.TRUE);
            Receiptstb.visibleProperty().setValue(Boolean.FALSE);
        }
    }

    private void FillDataReceipt() throws ClassNotFoundException {
        ObservableList<Receipts> receipts = ReceiptsDAOImplement.getInstance().getAllReceipts();
        Receiptstb.setItems(receipts);
    }

    private void FillDataPayslip() throws ClassNotFoundException {
        ObservableList<Payslip> payslips = PayslipDAOImplement.getInstance().getAllPayslip();
        Paysliptb.setItems(payslips);
    }

}
