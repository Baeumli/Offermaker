/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offermaker.controllers;

import ch.baeumli.offermaker.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class CreateProduct_Controller implements Initializable {

    @FXML private AnchorPane paneRoot;
    @FXML
    private TextField txtBrand;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtPrice;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }    


    @FXML
    private void btnSubmitPressed(ActionEvent event) {
          if (txtBrand.getText().equals("") || txtProductName.getText().equals("") || txtPrice.getText().equals("")) {
            System.out.println("Empty Fields!");
        } else {
            String brand = txtBrand.getText();
            String productName = txtProductName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            
            Database db = Database.getInstance();
            db.establishConnection(Login_Controller.getUsername(), Login_Controller.getPassword());
            db.addProduct(brand, productName, price);
            db.closeConnection();
              
            Stage stage = (Stage) paneRoot.getScene().getWindow();
            stage.close();
        }
    }

}
