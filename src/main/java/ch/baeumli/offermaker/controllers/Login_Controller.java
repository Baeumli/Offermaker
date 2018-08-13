/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offermaker.controllers;

import ch.baeumli.offermaker.Database;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class Login_Controller implements Initializable {

    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUsername;

    private static String username;
    private static String password;
    @FXML
    private AnchorPane rootPane;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSignInPressed(ActionEvent event) {
       username = txtUsername.getText();
       password = txtPassword.getText();
       
       Database db = Database.getInstance();
        if (db.establishConnection(username, password) != null) {
           
                Stage loginStage = (Stage) rootPane.getScene().getWindow();
                loginStage.close();

            try {
               Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main_View.fxml"));
               
               Scene scene = new Scene(root);
               scene.getStylesheets().add("/styles/Styles.css");
               Stage stage = new Stage();
               stage.setTitle("Offermaker - Welcome!");
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(Login_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Offermaker");
            alert.setHeaderText("Wrong Username and Password");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
       
        
        
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    
    
    
}
