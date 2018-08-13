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
import ch.baeumli.offermaker.Product;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class Product_Controller implements Initializable {
    @FXML private AnchorPane paneRoot;

    @FXML private Button btnSubmit;

    private ObservableList<Product> products = FXCollections.observableArrayList();
    private Product selectedProduct;
    @FXML
    private TableView<Product> tblProduct;
    @FXML
    private TableColumn<Product, String> colBrand;
    @FXML
    private TableColumn<Product, String> colProductName;
    @FXML
    private TableColumn<Product, Integer> colPrice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tblProduct.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // TODO
        colBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        colProductName.setCellValueFactory(new PropertyValueFactory("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));

        updateTable();
    }

    @FXML
    private void btnSubmitClicked(ActionEvent event) throws IOException {

        if (tblProduct.getSelectionModel().getSelectedItem() != null) {

            selectedProduct = tblProduct.getSelectionModel().getSelectedItem();
            
            
            Stage stage = (Stage) paneRoot.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("no selection");
        }
    }

    private void updateTable() {
        //Fill Tableview with data
        Database db = Database.getInstance();
        db.establishConnection(Login_Controller.getUsername(), Login_Controller.getPassword());
        products = db.getProducts();
        db.closeConnection();
        tblProduct.setItems(products);
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    @FXML
    private void btnAddProductPressed(ActionEvent event) {
                try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CreateProduct_View.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Add a new Product");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            updateTable();
        } catch (IOException ex) {
            Logger.getLogger(Product_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEditProductPressed(ActionEvent event) {
        
            if (tblProduct.getSelectionModel().getSelectedItem() != null) {
                try {
                    selectedProduct = tblProduct.getSelectionModel().getSelectedItem();
                                  
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EditProduct_View.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    EditProduct_Controller controller = fxmlLoader.getController();
                    controller.fillFields(selectedProduct.getId(), selectedProduct.getBrand(), selectedProduct.getName(), selectedProduct.getPrice());
                    
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Edit Product");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    updateTable();
                } catch (IOException ex) {
                    Logger.getLogger(Product_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("No Product selected");
            }

    }

    @FXML
    private void btnRemoveProductPressed(ActionEvent event) {
        if (tblProduct.getSelectionModel().getSelectedItem() != null) {
            int selectedProductId;
            selectedProductId = tblProduct.getSelectionModel().getSelectedItem().getId();
            Database db = Database.getInstance();
            db.establishConnection(Login_Controller.getUsername(), Login_Controller.getPassword());
            db.removeProduct(selectedProductId);
            db.closeConnection();

            updateTable();
        } else {
            System.out.println("No product selected");
        }
    }


    
    
    
    
}
