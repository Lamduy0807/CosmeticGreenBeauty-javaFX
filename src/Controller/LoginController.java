/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
/**
 *
 * @author Admin
 */
public class LoginController implements Initializable{

    public TextField txtUsername;
    public PasswordField txtPassword;
    
    @FXML
    public Button buttonLogin;
    
    //public Connection con;
    
    public void Login(ActionEvent e) throws IOException, SQLException
    {
        String Username = txtUsername.getText();
        String PW = txtPassword.getText();
        User user = new User(Username,PW);
        if(user.CheckAccount())
        {
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
            System.out.print("PW not corect");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonLogin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                try {
                    Login(t);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
    }   
    
}
