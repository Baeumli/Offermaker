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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class Person_Controller implements Initializable {
    
    @FXML private Button btnExit;
    @FXML private AnchorPane panePerson;
    @FXML private TableView<Person> tblPerson;
    @FXML private TableColumn<Person, String> colSex;
    @FXML private TableColumn<Person, String> colFirstname;
    @FXML private TableColumn<Person, String> colLastname;
    @FXML private TableColumn<Person, String> colEmail;
    @FXML private TableColumn<Person, String> colPhone;
    @FXML private TableColumn<Person, String> colCompany;
      
    private ObservableList<Person> persons = FXCollections.observableArrayList();
    private int selectedPersonId;

    public int getSelectedPersonId() {
        return selectedPersonId;
    }
    
    
    void btnExitClick(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void updateTable(){
        //Fill Tableview with data
        Database db = Database.getInstance();
        db.establishConnection();
        persons = db.getPersons();
        db.closeConnection();
        tblPerson.setItems(persons);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colSex.setCellValueFactory(new PropertyValueFactory("sex"));
        colFirstname.setCellValueFactory(new PropertyValueFactory("firstname"));
        colLastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        colCompany.setCellValueFactory(new PropertyValueFactory("company"));   
        
        updateTable();
        
    }

    @FXML
    private void btnPersonDialogClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CreatePerson_View.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Add a new Person");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            updateTable();
            
        } catch (IOException ex) {
            Logger.getLogger(ToolbarFile_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEditPersonClicked(ActionEvent event) {
        
        if (tblPerson.getSelectionModel().getSelectedItem() != null) {
            selectedPersonId = tblPerson.getSelectionModel().getSelectedItem().getId();
        }
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EditPerson_View.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Edit Person");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            updateTable();
            
        } catch (IOException ex) {
            Logger.getLogger(ToolbarFile_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRemovePersonClicked(ActionEvent event) {

        if (tblPerson.getSelectionModel().getSelectedItem() != null) {
            selectedPersonId = tblPerson.getSelectionModel().getSelectedItem().getId();
        }

        Database db = Database.getInstance();
        db.establishConnection();
        db.removePerson(selectedPersonId);
        db.closeConnection();
        
        updateTable();
    }

}
