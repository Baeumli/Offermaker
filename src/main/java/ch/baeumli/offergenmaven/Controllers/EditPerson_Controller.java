/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven.Controllers;

import ch.baeumli.offergenmaven.Database;
import ch.baeumli.offergenmaven.Person;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class EditPerson_Controller implements Initializable {

    @FXML private AnchorPane paneRoot;

    @FXML private TextField txtFirstname;

    @FXML private TextField txtLastname;

    @FXML private TextField txtEmail;

    @FXML private TextField txtPhone;

    @FXML private TextField txtCompany;

    @FXML private ComboBox<String> cboxSex;

    private int id;
    private String sex;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String company;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboxSex.getItems().addAll("M", "F");
        
    }

    
    void fillFields(int id, String sex, String firstname, String lastname, String email, String phone, String company) {
        this.id = id;
        cboxSex.setValue(sex);
        txtFirstname.setText(firstname);
        txtLastname.setText(lastname);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtCompany.setText(company);
    }
    

    @FXML
    void btnSubmitClicked(ActionEvent event) {
        if (cboxSex.getSelectionModel().getSelectedItem().equals("") || txtFirstname.getText().equals("") || txtLastname.getText().equals("")) {
            System.out.println("Empty Fields!");
        } else {
            sex = cboxSex.getSelectionModel().getSelectedItem();
            firstname = txtFirstname.getText();
            lastname = txtLastname.getText();
            email = txtEmail.getText();
            phone = txtPhone.getText();
            company = txtCompany.getText();
            
            Database db = Database.getInstance();
            db.establishConnection();
            db.editPerson(sex, firstname, lastname, email, phone, company, id);
            db.closeConnection();
              
            Stage stage = (Stage) paneRoot.getScene().getWindow();
            stage.close();
        }
    }
}
