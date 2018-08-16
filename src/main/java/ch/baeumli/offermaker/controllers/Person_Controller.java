/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offermaker.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ch.baeumli.offermaker.Database;
import ch.baeumli.offermaker.Person;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
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
    @FXML private AnchorPane paneRoot;
    @FXML private TableView<Person> tblPerson;
    @FXML private TableColumn<Person, String> colSex;
    @FXML private TableColumn<Person, String> colFirstname;
    @FXML private TableColumn<Person, String> colLastname;
    @FXML private TableColumn<Person, String> colEmail;
    @FXML private TableColumn<Person, String> colPhone;
    @FXML private TableColumn<Person, String> colCompany;
    @FXML private TableColumn<Person, String> colStreet;
    @FXML private TableColumn<Person, String> colCity;
    @FXML private TableColumn<Person, String> colZip;
    @FXML private Button btnSubmit;

    private ObservableList<Person> persons = FXCollections.observableArrayList();
    private Person selectedPerson;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tblPerson.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // TODO
        colSex.setCellValueFactory(new PropertyValueFactory("sex"));
        colFirstname.setCellValueFactory(new PropertyValueFactory("firstname"));
        colLastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        colCompany.setCellValueFactory(new PropertyValueFactory("company"));
        colStreet.setCellValueFactory(new PropertyValueFactory("street"));
        colCity.setCellValueFactory(new PropertyValueFactory("city"));
        colZip.setCellValueFactory(new PropertyValueFactory("zip"));
        updateTable();
    }

    @FXML
    private void btnPersonDialogClicked(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CreatePerson_View.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Ajouter une nouvelle personne");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            updateTable();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("C");
            alert.show();
        }

    }

    @FXML
    private void btnEditPersonClicked(ActionEvent event) {

            if (tblPerson.getSelectionModel().getSelectedItem() != null) {
                try {
                    selectedPerson = tblPerson.getSelectionModel().getSelectedItem();
                                        
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EditPerson_View.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    EditPerson_Controller controller = fxmlLoader.getController();
                    controller.fillFields(selectedPerson.getId(), selectedPerson.getSex(), selectedPerson.getFirstname(), selectedPerson.getLastname(), selectedPerson.getEmail(), selectedPerson.getPhone(), selectedPerson.getCompany(), selectedPerson.getStreet(), selectedPerson.getCity(), selectedPerson.getZip());
                    
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Éditer une personne");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    updateTable();
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Une erreur est survenue!");
                    alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez sélectionner une personne!");
            alert.show();
        }

    }

    @FXML
    private void btnRemovePersonClicked(ActionEvent event) {

        if (tblPerson.getSelectionModel().getSelectedItem() != null) {
            int selectedPersonId;
            selectedPersonId = tblPerson.getSelectionModel().getSelectedItem().getId();
            Database db = Database.getInstance();
            db.establishConnection(Login_Controller.getUsername(), Login_Controller.getPassword());
            db.removePerson(selectedPersonId);
            db.closeConnection();

            updateTable();
        } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setContentText("Veuillez sélectionner une personne!");
                alert.show();
        }
    }

    @FXML
    private void btnSubmitClicked(ActionEvent event) throws IOException {

        if (tblPerson.getSelectionModel().getSelectedItem() != null) {

            selectedPerson = tblPerson.getSelectionModel().getSelectedItem();
            
                        Stage stage = (Stage) paneRoot.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez sélectionner une personne!");
            alert.show();
        }
    }

    private void updateTable() {
        //Fill Tableview with data
        Database db = Database.getInstance();
        db.establishConnection(Login_Controller.getUsername(), Login_Controller.getPassword());
        persons = db.getPersons();
        db.closeConnection();
        tblPerson.setItems(persons);
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    
    
    
    
    
}
