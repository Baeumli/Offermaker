/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.baeumli.offergenmaven.Database;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class Product_Controller implements Initializable {
    @FXML
    private Button btnExit;   
    @FXML
    private TextField txtBrand;
    @FXML
    private TextField txtProduct;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtAmount;

    private String brand;
    private String product;
    private String price;
    private int amount;

    void btnExitClick(ActionEvent event) {
    Stage stage = (Stage) btnExit.getScene().getWindow();
    stage.close();
    }
    
    @FXML
    void btnCreateProductClick(ActionEvent event) {
        
        brand = txtBrand.getText();
        product = txtProduct.getText();
        price = txtPrice.getText();
        
        Database db = Database.getInstance();
        db.establishConnection();
        db.insertInto("product", "null, " + "'" + brand + "', '" + product + "', '" + price  + "'");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
}
