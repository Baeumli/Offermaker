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
import ch.baeumli.offergenmaven.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class Person_Controller implements Initializable {
    
    @FXML private ComboBox cboxSex;
    @FXML private Button btnExit;
    @FXML private TextField txtFirstname;
    @FXML private TextField txtLastname;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextField txtCompany;
    @FXML private AnchorPane panePerson;
    @FXML private TableView tblPerson;
    @FXML private TableColumn colSex;
    @FXML private TableColumn colFirstname;
    @FXML private TableColumn colLastname;
    @FXML private Button btnCreatePerson;
    @FXML private TableColumn colEmail;
    @FXML private TableColumn colPhone;
    @FXML private TableColumn colCompany;
    
    private String sex;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String company;
    private ObservableList<Person> persons = FXCollections.observableArrayList();
    
    void btnExitClick(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnCreatePersonClick(ActionEvent event) {

        if (cboxSex.getSelectionModel().getSelectedItem().toString().equals("") || txtFirstname.getText().equals("") || txtLastname.getText().equals("")) {
            System.out.println("Empty Fields!");
        } else {
            sex = cboxSex.getSelectionModel().getSelectedItem().toString();
            firstname = txtFirstname.getText();
            lastname = txtLastname.getText();
            email = txtEmail.getText();
            phone = txtPhone.getText();
            company = txtCompany.getText();
            Database db = Database.getInstance();
            db.establishConnection();
            db.addPerson(sex, firstname, lastname, email, phone, company);
            persons = db.getPersons();
            db.closeConnection();
            
            this.fillTable();
        }
    }
    
    private void fillTable(){
        //Fill Tableview with data
        colSex.setCellValueFactory(new PropertyValueFactory("sex"));
        colFirstname.setCellValueFactory(new PropertyValueFactory("firstname"));
        colLastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        colCompany.setCellValueFactory(new PropertyValueFactory("company"));   
        
        tblPerson.setItems(persons);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cboxSex.getItems().addAll('M', 'F');
        Database db = Database.getInstance();
        db.establishConnection();
        persons = db.getPersons();
        db.closeConnection();
        this.fillTable();
    }
}
