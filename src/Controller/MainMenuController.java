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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 *
 * @author Admin
 */
public class MainMenuController implements Initializable{
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
    private void setUi(String location) throws IOException {
        Context.getChildren().clear();
        Context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/View/" + location + ".fxml")));
    }
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
        
        
        new FadeTransition(Duration.millis(3000),Context).play();

    }
//    public void btnHome() throws IOException {
//        setUi("Home");
//        buttonHome.getStyleClass().add("active");
//        
//        buttonSale.getStyleClass().remove("active");
//        buttonProduct.getStyleClass().remove("active");
//        buttonEmployee.getStyleClass().remove("active");
//        buttonSuplier.getStyleClass().remove("active");
//        buttonImport.getStyleClass().remove("active");
//        buttonExport.getStyleClass().remove("active");
//        buttonAccountant.getStyleClass().remove("active");
//        buttonReport.getStyleClass().remove("active");
//        
//        new FadeTransition(Duration.millis(3000),Context).play();
//    }
//    public void btnImport() throws IOException {
//        setUi("Import");
//        buttonImport.getStyleClass().add("active");
//        
//        buttonSale.getStyleClass().remove("active");
//        buttonProduct.getStyleClass().remove("active");
//        buttonEmployee.getStyleClass().remove("active");
//        buttonSuplier.getStyleClass().remove("active");
//        buttonHome.getStyleClass().remove("active");
//        buttonExport.getStyleClass().remove("active");
//        buttonAccountant.getStyleClass().remove("active");
//        buttonReport.getStyleClass().remove("active");
//        
//        new FadeTransition(Duration.millis(3000),Context).play();
//    }
    public void btnClick(String name) throws IOException{
        switch(name)
        {
            case "Home":{
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
                break;
            }
            case "Sale":{
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
                break;
            }
            case "Product":{
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
                break;
            }
            case "Employee":{
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
                break;
            }
            case "Suplier":{
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
                break;
            }
            
            case "Import":
            {
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
                break;
            }
            case "Export":{
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
                break;
            }
            case "Accountant":{
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
                break;
            }
            case "Report":{
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
                break;
            }
            default:
                break;
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            DashBoardOnAction();

            buttonImport.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Import");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            buttonHome.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Home");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            buttonSale.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Sale");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            buttonProduct.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Product");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            buttonEmployee.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Employee");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            buttonSuplier.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Suplier");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            buttonExport.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Export");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            buttonAccountant.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Accountant");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            buttonReport.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
                @Override
                public void handle(javafx.scene.input.MouseEvent t) {
                    try {
                        btnClick("Report");
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            new FadeTransition(Duration.millis(3000),Context).play();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
