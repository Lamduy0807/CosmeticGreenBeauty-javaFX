/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.UserDAOImplement;
import Holder.UserHolder;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
/**
 *
 * @author Duy
 * this file is a controller for Login.fxml
 */
public class LoginController implements Initializable{

    public TextField txtUsername;
    public PasswordField txtPassword;
    
    @FXML
    public Button buttonLogin;
    @FXML
    private ImageView btnClose;
   
    public void Login(ActionEvent e) throws IOException, SQLException, ClassNotFoundException
    {
        String Username = txtUsername.getText();
        String PW = txtPassword.getText();
        //check Password and Username null or not
        if("".equals(PW)||"".equals(Username))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Username or password can not be blank!");
            alert.show();
        }
        else{
        //check for Username and Password is the same in Database or not
        User user = new User(Username,PW);
            if(user.CheckAccount())
        {
            User user2 = UserDAOImplement.getInstance().getUserInformationByUsername(user.getsUsername());
            UserHolder holder = UserHolder.getInstance();
            holder.setUser(user2);
            
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/View/MainMenu.fxml"));
            Parent pa = null;
            pa = load.load();
            Scene scene = new Scene(pa);
            stage.setScene(scene);
            stage.centerOnScreen();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Wrong username or password");
            alert.show();
        }
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnClose.setOnMouseClicked(e->handleClose());
        buttonLogin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                try {
                    Login(t);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
    }
    //handle for close event
    public void handleClose(){
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("Exit");
       alert.setContentText("Do you want to logout?");
       if(alert.showAndWait().get()==ButtonType.OK)
       {
           Platform.exit();
       }
    }
}
