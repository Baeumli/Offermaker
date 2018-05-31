/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven.Controllers;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class ToolbarFile_Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void btnPersonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Person_View.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add a new Person");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(ToolbarFile_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @FXML
    void btnDateClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Date_View.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Specify a Date");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(ToolbarFile_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    void btnProductClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Product_View.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add a new Product");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(ToolbarFile_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    void btnSaleClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Sale_View.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add a Discount");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(ToolbarFile_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    void btnPaymentClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Payment_View.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add a Payment option");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(ToolbarFile_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    void btnShippingClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Shipping_View.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add a Shipping method");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(ToolbarFile_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
