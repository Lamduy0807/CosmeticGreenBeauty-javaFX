/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.EmployeeDAOImplement;
import Model.Employee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
 * @author DELL
 */
public class EmployeeController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private JFXTextField tbID;
    @FXML
    private JFXTextField tbName;
    @FXML
    private JFXTextField tbCitizenID;
    @FXML
    private JFXTextField tbAddress;
    @FXML
    private JFXTextField tbPhone;
    @FXML
    private JFXTextField tbEmail;
    @FXML
    private JFXTextField tbPosition;
    @FXML
    private JFXTextField tbUsername;
    @FXML
    private JFXTextField tbPassword;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField tbSearch;
    @FXML
    private TableView<Employee> Employeetb;
    @FXML
    private TableColumn<Employee, Integer> idCol;
    @FXML
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, String> citizenCol;
    @FXML
    private TableColumn<Employee, String> addressCol;
    @FXML
    private TableColumn<Employee, String> phoneCol;
    @FXML
    private TableColumn<Employee, String> emailCol;
    @FXML
    private TableColumn<Employee, String> positionCol;
    @FXML
    private TableColumn<Employee, String> usernameCol;
    @FXML
    private TableColumn<Employee, String> passwordCol;

    String LOWER_CASE = "abcdefghijklmnopqursuvwxyz";
    String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String NUMBERS = "123456789";
    String SPECIALS = "!@£$%^&*()#€";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idCol.setCellValueFactory(new PropertyValueFactory("Employee_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory("EmployName"));
        citizenCol.setCellValueFactory(new PropertyValueFactory("Citizen_id"));
        addressCol.setCellValueFactory(new PropertyValueFactory("Address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("PhoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        positionCol.setCellValueFactory(new PropertyValueFactory("Position"));
        usernameCol.setCellValueFactory(new PropertyValueFactory("Username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory("Password"));

        try {
            FillData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Employeetb.getSelectionModel().selectedItemProperty().addListener(
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
                ObservableList<Employee> employees = FXCollections.observableArrayList();
                try {
                    employees = EmployeeDAOImplement.getInstance().searchEmployee(newValue);
                    Employeetb.setItems(employees);
                    Employeetb.refresh();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    FillData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
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

        tbCitizenID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[0-9]+$") && !"".equals(newValue)) {
                tbCitizenID.setText(oldValue);
                Notifications.create().title("ERROR").text("CitizenID must be number")
                        .showError();
            }
        });
    }

    private void FillData() throws ClassNotFoundException {
        ObservableList<Employee> employees = EmployeeDAOImplement.getInstance().getAllUser();
        Employeetb.setItems(employees);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            if (!"".equals(tbName.getText()) && !"".equals(tbCitizenID.getText())
                    && !"".equals(tbAddress.getText()) && !"".equals(tbPhone.getText())
                    && !"".equals(tbEmail.getText()) && !"".equals(tbPosition.getText())) {
                String name = tbName.getText();
                String citizenId = tbCitizenID.getText();
                String address = tbAddress.getText();
                String phoneNum = tbPhone.getText();
                String email = tbEmail.getText();
                String position = tbPosition.getText();
                String _pass = GeneratePassword(true, true, true, false, 6);
                String username = email;
                String password = citizenId;
                Employee employee = new Employee(name, citizenId, address, phoneNum, email, position, username, password);
                try {
                    if (EmployeeDAOImplement.getInstance().addEmployee(employee) == 1) {
                        ClearData();
                        FillData();
                        Employeetb.refresh();
                        Notifications.create().title("Success").text("Add new Employee sucessfully!!")
                                .showInformation();
                        btnAdd.setDisable(true);
                        btnDelete.setDisable(true);
                        btnEdit.setDisable(true);
                    } else {
                        Notifications.create().title("ERROR").text("There is some errors occurred! Please check again")
                                .showError();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("".equals(tbName.getText())) {
                Notifications.create().title("WARNING").text("Please enter the name of employee.")
                        .showWarning();
            } else if ("".equals(tbCitizenID.getText())) {
                Notifications.create().title("WARNING").text("Please enter the citizenID of employee.")
                        .showWarning();
            } else if ("".equals(tbAddress.getText())) {
                Notifications.create().title("WARNING").text("Please enter the address of employee.")
                        .showWarning();
            } else if ("".equals(tbPhone.getText())) {
                Notifications.create().title("WARNING").text("Please enter the phone number of employee.")
                        .showWarning();
            } else if ("".equals(tbEmail.getText())) {
                Notifications.create().title("WARNING").text("Please enter the email of employee.")
                        .showWarning();
            } else if ("".equals(tbPosition.getText())) {
                Notifications.create().title("WARNING").text("Please enter the position of employee.")
                        .showWarning();
            }
        } else if (event.getSource() == btnDelete) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Are you sure you want to delete this employee?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                String id = tbID.getText();
                int iID = Integer.parseInt(id);
                try {
                    if (EmployeeDAOImplement.getInstance().deleteEmployee(iID) == 1) {
                        ClearData();
                        FillData();
                        Employeetb.refresh();
                        Notifications.create().title("Success").text("Delete Employee sucessfully!!")
                                .showInformation();
                        btnAdd.setDisable(true);
                        btnDelete.setDisable(true);
                        btnEdit.setDisable(true);
                    } else {
                        Notifications.create().title("ERROR").text("There is some errors occurred! Please check again")
                                .showError();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (event.getSource() == btnEdit) {
            if (!"".equals(tbName.getText()) && !"".equals(tbCitizenID.getText())
                    && !"".equals(tbAddress.getText()) && !"".equals(tbPhone.getText())
                    && !"".equals(tbEmail.getText()) && !"".equals(tbPosition.getText())) {
                String id = tbID.getText();
                int iID = Integer.parseInt(id);
                String name = tbName.getText();
                String citizenId = tbCitizenID.getText();
                String address = tbAddress.getText();
                String phoneNum = tbPhone.getText();
                String email = tbEmail.getText();
                String position = tbPosition.getText();

                String username = tbUsername.getText();
                String password = tbPassword.getText();
                Employee employee = new Employee(iID, name, citizenId, address, phoneNum, email, position, username, password);
                try {
                    if (EmployeeDAOImplement.getInstance().updateEmployee(employee) == 1) {
                        ClearData();
                        FillData();
                        Employeetb.refresh();
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
                    Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if ("".equals(tbName.getText())) {
                Notifications.create().title("WARNING").text("Please enter the name of employee.")
                        .showWarning();
            } else if ("".equals(tbCitizenID.getText())) {
                Notifications.create().title("WARNING").text("Please enter the citizenID of employee.")
                        .showWarning();
            } else if ("".equals(tbAddress.getText())) {
                Notifications.create().title("WARNING").text("Please enter the address of employee.")
                        .showWarning();
            } else if ("".equals(tbPhone.getText())) {
                Notifications.create().title("WARNING").text("Please enter the phone number of employee.")
                        .showWarning();
            } else if ("".equals(tbEmail.getText())) {
                Notifications.create().title("WARNING").text("Please enter the email of employee.")
                        .showWarning();
            } else if ("".equals(tbPosition.getText())) {
                Notifications.create().title("WARNING").text("Please enter the position of employee.")
                        .showWarning();
            }
        }
    }

    private void selectItem(Employee e) {

        tbID.setText(String.valueOf(e.getEmployee_id()));
        tbName.setText(e.getEmployName());
        tbCitizenID.setText(e.getCitizen_id());
        tbAddress.setText(e.getAddress());
        tbPhone.setText(e.getPhoneNumber());
        tbEmail.setText(e.getEmail());
        tbPosition.setText(e.getPosition());
        tbUsername.setText(e.getUsername());
        tbPassword.setText(e.getPassword());
    }

    public void ClearData() {
        tbID.setText("");
        tbName.setText("");
        tbCitizenID.setText("");
        tbAddress.setText("");
        tbPhone.setText("");
        tbEmail.setText("");
        tbPosition.setText("");
        tbUsername.setText("");
        tbPassword.setText("");
    }

    public String GeneratePassword(Boolean useLowercase, Boolean useUppercase, Boolean useNumbers, Boolean useSpecial,
            int passwordSize) {
        char[] _password = new char[passwordSize];
        String charSet = "";
        Random _random = new Random();
        int counter;

        if (useLowercase) {
            charSet += LOWER_CASE;
        }

        if (useUppercase) {
            charSet += UPPER_CASE;
        }

        if (useNumbers) {
            charSet += NUMBERS;
        }

        if (useSpecial) {
            charSet += SPECIALS;
        }

        for (counter = 0; counter < passwordSize; counter++) {
            _password[counter] = charSet.charAt(_random.nextInt((charSet.length() - 1)));
        }

        System.out.print(_password);

        return _password.toString();
    }

}
