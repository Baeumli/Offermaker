/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven.Controllers;

import ch.baeumli.offergenmaven.Database;
import ch.baeumli.offergenmaven.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
            System.out.println("Empty Fields!");
        } else {
            String sex = cboxSex.getSelectionModel().getSelectedItem();
            String firstname = txtFirstname.getText();
            String lastname = txtLastname.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String company = txtCompany.getText();
            
            Database db = Database.getInstance();
            db.establishConnection();
            db.addPerson(sex, firstname, lastname, email, phone, company);
            db.closeConnection();
              
            Stage stage = (Stage) paneRoot.getScene().getWindow();
            stage.close();
        }
    }   

}
