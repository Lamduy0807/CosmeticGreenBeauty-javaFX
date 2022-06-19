/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Holder.UserHolder;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Duy This file is a controller for MainMenu.fxml
 */
public class MainMenuController implements Initializable {

    public AnchorPane Context;
    public HBox buttonHome;
    public HBox buttonSale;
    public HBox buttonProduct;
    public HBox buttonEmployee;
    public HBox buttonSuplier;
    public HBox buttonImport;
    public HBox buttonExport;
    public HBox buttonAccountant;
    public HBox buttonReport;
    public HBox buttonLogout;
    Stage primaryStage;
    @FXML
    private ImageView imgView;

    @FXML
    private Text txtView;
    @FXML
    private ImageView btnClose;

    @FXML
    private ImageView btnMinimize;

    //Set UI for Panel when we click on specific button
    private void setUi(String location) throws IOException {
        Context.getChildren().clear();
        Context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/View/" + location + ".fxml")));
    }

    // Click on Home button
    public void DashBoardOnAction() throws IOException {
        setUi("Home");
        buttonHome.getStyleClass().add("active");

        buttonSale.getStyleClass().remove("active");
        buttonProduct.getStyleClass().remove("active");
        buttonEmployee.getStyleClass().remove("active");
        buttonSuplier.getStyleClass().remove("active");
        buttonImport.getStyleClass().remove("active");
        buttonExport.getStyleClass().remove("active");
        buttonAccountant.getStyleClass().remove("active");
        buttonReport.getStyleClass().remove("active");

        new FadeTransition(Duration.millis(3000), Context).play();

    }

    //Handle click event for each button
    public void btnClick(String name) throws IOException {
        switch (name) {
            case "Home": {
                setUi("Home");
                buttonHome.getStyleClass().add("active");

                buttonSale.getStyleClass().remove("active");
                buttonProduct.getStyleClass().remove("active");
                buttonEmployee.getStyleClass().remove("active");
                buttonSuplier.getStyleClass().remove("active");
                buttonImport.getStyleClass().remove("active");
                buttonExport.getStyleClass().remove("active");
                buttonAccountant.getStyleClass().remove("active");
                buttonReport.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_house_white_24dp.png"));
                txtView.setText("Home");
                break;
            }
            case "Sale": {
                setUi("Sale");
                buttonSale.getStyleClass().add("active");

                buttonHome.getStyleClass().remove("active");
                buttonProduct.getStyleClass().remove("active");
                buttonEmployee.getStyleClass().remove("active");
                buttonSuplier.getStyleClass().remove("active");
                buttonImport.getStyleClass().remove("active");
                buttonExport.getStyleClass().remove("active");
                buttonAccountant.getStyleClass().remove("active");
                buttonReport.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_shopping_bag_white_24dp.png"));
                txtView.setText("Sale");
                break;
            }
            case "Product": {
                setUi("Product");
                buttonProduct.getStyleClass().add("active");

                buttonHome.getStyleClass().remove("active");
                buttonSale.getStyleClass().remove("active");
                buttonEmployee.getStyleClass().remove("active");
                buttonSuplier.getStyleClass().remove("active");
                buttonImport.getStyleClass().remove("active");
                buttonExport.getStyleClass().remove("active");
                buttonAccountant.getStyleClass().remove("active");
                buttonReport.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_local_offer_white_24dp.png"));
                txtView.setText("Product");
                break;
            }
            case "Employee": {
                setUi("Employee");
                buttonEmployee.getStyleClass().add("active");

                buttonHome.getStyleClass().remove("active");
                buttonSale.getStyleClass().remove("active");
                buttonProduct.getStyleClass().remove("active");
                buttonSuplier.getStyleClass().remove("active");
                buttonImport.getStyleClass().remove("active");
                buttonExport.getStyleClass().remove("active");
                buttonAccountant.getStyleClass().remove("active");
                buttonReport.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_people_white_24dp.png"));
                txtView.setText("Employee");
                break;
            }
            case "Suplier": {
                setUi("Suplier");
                buttonSuplier.getStyleClass().add("active");

                buttonHome.getStyleClass().remove("active");
                buttonSale.getStyleClass().remove("active");
                buttonProduct.getStyleClass().remove("active");
                buttonEmployee.getStyleClass().remove("active");
                buttonImport.getStyleClass().remove("active");
                buttonExport.getStyleClass().remove("active");
                buttonAccountant.getStyleClass().remove("active");
                buttonReport.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_gite_white_24dp.png"));
                txtView.setText("Supplier");
                break;
            }

            case "Import": {
                setUi("Import");
                buttonImport.getStyleClass().add("active");

                buttonHome.getStyleClass().remove("active");
                buttonSale.getStyleClass().remove("active");
                buttonProduct.getStyleClass().remove("active");
                buttonEmployee.getStyleClass().remove("active");
                buttonSuplier.getStyleClass().remove("active");
                buttonExport.getStyleClass().remove("active");
                buttonAccountant.getStyleClass().remove("active");
                buttonReport.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_local_shipping_white_24dp.png"));
                txtView.setText("Import");
                break;
            }
            case "Export": {
                setUi("Export");
                buttonExport.getStyleClass().add("active");

                buttonHome.getStyleClass().remove("active");
                buttonSale.getStyleClass().remove("active");
                buttonProduct.getStyleClass().remove("active");
                buttonEmployee.getStyleClass().remove("active");
                buttonSuplier.getStyleClass().remove("active");
                buttonImport.getStyleClass().remove("active");
                buttonAccountant.getStyleClass().remove("active");
                buttonReport.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_lan_white_24dp.png"));
                txtView.setText("Export");
                break;
            }
            case "Accountant": {
                setUi("Accountant");
                buttonAccountant.getStyleClass().add("active");

                buttonHome.getStyleClass().remove("active");
                buttonSale.getStyleClass().remove("active");
                buttonProduct.getStyleClass().remove("active");
                buttonEmployee.getStyleClass().remove("active");
                buttonSuplier.getStyleClass().remove("active");
                buttonImport.getStyleClass().remove("active");
                buttonExport.getStyleClass().remove("active");
                buttonReport.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_calculate_white_24dp.png"));
                txtView.setText("Accountant");
                break;
            }
            case "Report": {
                setUi("Report");
                buttonReport.getStyleClass().add("active");

                buttonHome.getStyleClass().remove("active");
                buttonSale.getStyleClass().remove("active");
                buttonProduct.getStyleClass().remove("active");
                buttonEmployee.getStyleClass().remove("active");
                buttonSuplier.getStyleClass().remove("active");
                buttonImport.getStyleClass().remove("active");
                buttonExport.getStyleClass().remove("active");
                buttonAccountant.getStyleClass().remove("active");
                imgView.setImage(new Image("./img/baseline_analytics_white_24dp.png"));
                txtView.setText("Report");
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnClose.setOnMouseClicked(e -> handleClose());
        btnMinimize.setOnMouseClicked(e -> handleMinimize());
        buttonHome.setDisable(false);
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        //Authentication for specific user
        if ("SalesMan".equals(u.getsPosition())) {

            buttonSale.setDisable(false);
            buttonReport.setDisable(false);
        } else if ("InventoryDepartment".equals(u.getsPosition())) {
            buttonImport.setDisable(false);
            buttonExport.setDisable(false);
            buttonProduct.setDisable(false);
        } else if ("AccountingDepartment".equals(u.getsPosition())) {
            buttonAccountant.setDisable(false);
            buttonReport.setDisable(false);
        } else {
            buttonSale.setDisable(false);
            buttonImport.setDisable(false);
            buttonExport.setDisable(false);
            buttonProduct.setDisable(false);
            buttonAccountant.setDisable(false);
            buttonReport.setDisable(false);
            buttonSuplier.setDisable(false);
            buttonEmployee.setDisable(false);
        }
        try {
            DashBoardOnAction();

            buttonImport.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Import");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonHome.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Home");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonSale.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Sale");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonProduct.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Product");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonEmployee.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Employee");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonSuplier.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Suplier");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonExport.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Export");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonAccountant.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Accountant");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonReport.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Report");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            buttonLogout.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    handleClose();
                }

            });
            new FadeTransition(Duration.millis(3000), Context).play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //handle for Close Program event
    public void handleClose() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Do you want to logout?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    //handle for minimiz event
    public void handleMinimize() {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
}
