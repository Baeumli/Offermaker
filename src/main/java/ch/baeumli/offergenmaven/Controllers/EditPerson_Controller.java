/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven.Controllers;

import ch.baeumli.offergenmaven.Database;
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
    
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Person_Controller.fxml"));
            Parent root = (Parent)loader.load();
            Person_Controller controller = (Person_Controller)loader.getController();
            id = controller.getSelectedPersonId();
        } catch (IOException ex) {
            Logger.getLogger(EditPerson_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    void btnSubmitClicked(ActionEvent event) {
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
            db.editPerson(sex, firstname, lastname, email, phone, company, id);
            db.closeConnection();
              
            Stage stage = (Stage) paneRoot.getScene().getWindow();
            stage.close();
        }
    }
}
