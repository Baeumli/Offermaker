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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class CreatePerson_Controller implements Initializable {

    @FXML private TextField txtFirstname;
    @FXML private TextField txtLastname;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextField txtCompany;
    @FXML private ComboBox<String> cboxSex;
    @FXML private AnchorPane paneRoot;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtZip;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cboxSex.getItems().addAll("M", "F");
    }    

    @FXML
    private void btnSubmitClicked(ActionEvent event) {

        if (cboxSex.getSelectionModel().getSelectedItem().equals("") || txtFirstname.getText().equals("") || txtLastname.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Il a des champs vides!");
            alert.show();
        } else {
            String sex = cboxSex.getSelectionModel().getSelectedItem();
            String firstname = txtFirstname.getText();
            String lastname = txtLastname.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String company = txtCompany.getText();
            String street = txtStreet.getText();
            String city = txtCity.getText();
            String zip = txtZip.getText();
            
            Database db = Database.getInstance();
            db.establishConnection(Login_Controller.getUsername(), Login_Controller.getPassword());
            db.addPerson(sex, firstname, lastname, email, phone, company, street, city, zip);
            db.closeConnection();
              
            Stage stage = (Stage) paneRoot.getScene().getWindow();
            stage.close();
        }
    }   

}
