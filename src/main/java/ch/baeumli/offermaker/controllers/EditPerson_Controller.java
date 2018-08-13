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
    private String street;
    private String city;
    private String zip;
    
    
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtZip;

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

    
    void fillFields(int id, String sex, String firstname, String lastname, String email, String phone, String company, String street, String city, String zip) {
        this.id = id;
        cboxSex.setValue(sex);
        txtFirstname.setText(firstname);
        txtLastname.setText(lastname);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtCompany.setText(company);
        txtStreet.setText(street);
        txtCity.setText(city);
        txtZip.setText(zip);
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
            street = txtStreet.getText();
            city = txtCity.getText();
            zip = txtZip.getText();
            
            Database db = Database.getInstance();
            db.establishConnection(Login_Controller.getUsername(), Login_Controller.getPassword());
            db.editPerson(sex, firstname, lastname, email, phone, company, street, city, zip, id);
            db.closeConnection();
              
            Stage stage = (Stage) paneRoot.getScene().getWindow();
            stage.close();
        }
    }
}
