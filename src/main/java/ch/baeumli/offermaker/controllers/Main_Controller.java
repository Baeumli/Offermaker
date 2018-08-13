package ch.baeumli.offermaker.controllers;

import ch.baeumli.offermaker.Database;
import ch.baeumli.offermaker.Person;
import ch.baeumli.offermaker.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main_Controller implements Initializable {
    
    @FXML private AnchorPane rootPane;
    @FXML private WebView webView;
    @FXML private Button btnPerson;
    @FXML private Button btnProduct;
    @FXML private Button btnSavePdf;
    @FXML private Label lblFirstname;
    @FXML private Label lblEmail;
    @FXML private Label lblLastname;
    @FXML private Label lblSex;
    @FXML private Label lblPhone;
    @FXML private Label lblCompany;
    @FXML private Label lblProductName;
    @FXML private Label lblPrice;
    @FXML private Label lblBrand;
    @FXML private Label lblConnection;

    private Person selectedPerson;
    private Product selectedProduct;
    private String content; 
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");  
    LocalDateTime now = LocalDateTime.now();  
    String[] arr = Login_Controller.getUsername().split("\\.");
    String firstnameUser = capitalize(arr[0]);
    String lastnameUser = capitalize(arr[1]);
    String companyUser = "Kanti Baden";
    String streetUser = "Seminarstrasse 3";
    String cityUser = "Baden";
    String zipUser = "5400";
    String sex = "";
    String shippingTiming = "";
    int amount = 0;
    int daysToPay = 0;
    int discount = 0;
    int discountAmount = 0;
    String paymentMethod = "";
    String shippingMethod = "";

    private final Person tempPerson = new Person("M", "John", "Smith", "j.d@gmail.com", "000-000-124", "Google Inc.", "Road Alley 9", "New York", "73823", 1);
    private final Product tempProduct = new Product("MSI", "GTX 1080", 1, 1);
    
    @FXML
    private Label lblGreeting;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtDaysToPay;
    @FXML
    private TextField txtDiscount;
    @FXML
    private TextField txtDiscountAmount;
    @FXML
    private CheckBox checkboxDiscount;
    @FXML
    private ChoiceBox<String> listboxPayment;
    @FXML
    private RadioButton radioShippingBefore;
    @FXML
    private RadioButton radioShippingAfter;
    @FXML
    private ChoiceBox<String> listboxShipping;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        if (Database.getCn() != null) {

            lblConnection.setText("Connected");
            lblConnection.setStyle("-fx-text-fill: green");

            lblGreeting.setText(firstnameUser + " " + lastnameUser);

        } else {
            lblConnection.setText("not Connected");
            lblConnection.setStyle("-fx-text-fill: red");
        }
        
        listboxShipping.getItems().addAll("Camion", "Enlèvement");
        listboxPayment.getItems().addAll("Carte Postal", "Facture");
        listboxShipping.getSelectionModel().selectFirst();
        listboxPayment.getSelectionModel().selectFirst();

        txtDiscount.setDisable(true);
        txtDiscountAmount.setDisable(true);
        selectedPerson = tempPerson;
        selectedProduct = tempProduct;
        updatePreview();
    }    

    @FXML
    private void btnPersonPressed(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Person_View.fxml"));
            rootPane = (AnchorPane) loader.load();
            Person_Controller personController = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(rootPane);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Add a new Person");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
            if (personController.getSelectedPerson() != null) {
                selectedPerson = personController.getSelectedPerson();
                lblSex.setText(selectedPerson.getSex());
                lblFirstname.setText(selectedPerson.getFirstname());
                lblLastname.setText(selectedPerson.getLastname());
                lblEmail.setText(selectedPerson.getEmail());
                lblPhone.setText(selectedPerson.getPhone());
                lblCompany.setText(selectedPerson.getCompany());
            }
                updatePreview();
            
        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void updatePreview() {

        String salutation;
        switch (selectedPerson.getSex()) {
            case "M":
                salutation = "Cher Monsieur " + selectedPerson.getLastname();
                sex = "Monsieur";
                break;
            case "F":
                salutation = "Chère Madame " + selectedPerson.getLastname();
                sex = "Madame";
                break;
            default:
                salutation = "Chères Dames et Messieurs";
                sex = "Mesdames, Messieurs";
                break;
        }
        

        content = "<!DOCTYPE html><html><head> <meta charset=\"utf-8\"/> "
                + "<title>Vertretung während Reise – 1. Dezember 2012</title>"
                + "</head>"
                + "<body> <header> "
                + "<address> " + companyUser + "<br/> " + streetUser + " <br/>" + zipUser + " " + cityUser + "<br/>"
                + " </address> <br/> "
                + "<address> " + selectedPerson.getCompany() + "<br/>" + selectedPerson.getFirstname() + " " + selectedPerson.getLastname() + "<br/> " + selectedPerson.getStreet() + "<br/>" + selectedPerson.getZip() + " " + selectedPerson.getCity() + "<br/> "
                + "</address>"
                + " </header> "
                + "<p>" + cityUser + ", " + dtf.format(now) + "</p>"
                + "<h1> Offre </h1>"
                + " <div> <p>" + salutation + ", </p>"
                + "<p>Nous avons bien reçu votre commande du 30 mai et nous vous en remercions vivement.</p>"
                + "<p>Nous vous proposons " + amount + " " + selectedProduct.getBrand() + " " + selectedProduct.getName() + " au prix de " + selectedProduct.getPrice() + " CHF par pièce. "
                + ""
                + "<p>Nous vous demandons de faire le paiement dans les " + daysToPay + " jours " + paymentMethod + "</p>"
                + "<p>" + shippingMethod + shippingTiming + "</p>"
                + "<p>En vous remerciant d'avance de votre commande, nous vous prions d'agréer, " + sex + ", nos distinguées.</p>"
                + "</div>"
                + "<p> Cordialement, <br/> "
                + "<br/>" + firstnameUser + " " + lastnameUser + "</p>"
                + "</body></html>";
 
        WebEngine engine = webView.getEngine();
        engine.loadContent(content);
    }
    
    
    @FXML
    private void btnProductPressed(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Product_View.fxml"));
            rootPane = (AnchorPane) loader.load();
            Product_Controller productController = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(rootPane);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Add a new Product");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
            selectedProduct = productController.getSelectedProduct();
            lblBrand.setText(selectedProduct.getBrand());
            lblProductName.setText(selectedProduct.getName());
            lblPrice.setText(Double.toString(selectedProduct.getPrice()));

        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSavePdfPressed(ActionEvent event) {
        
        final Document doc = new Document();
        
        FileChooser fc = new FileChooser();
        fc.setTitle("Save to...");
        fc.setInitialFileName("Offer.pdf");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = fc.showSaveDialog(null);
        if (file != null) {
            try {
                PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream(file));
                doc.open();
                //Add pdf content here
                XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

                worker.parseXHtml(pdfWriter, doc, new StringReader(content));

                doc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String capitalize(final String line) {
        try {
            return Character.toUpperCase(line.charAt(0)) + line.substring(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @FXML
    private void btnApplyPressed(ActionEvent event) {
                
        try {
            if (txtAmount.getText() != null || txtDiscount.getText() != null || txtDiscountAmount.getText() != null || txtDaysToPay.getText() != null) {
                amount = Integer.parseInt(txtAmount.getText());
                discount = Integer.parseInt(txtDiscount.getText());
                discountAmount = Integer.parseInt(txtDiscountAmount.getText());
                daysToPay = Integer.parseInt(txtDaysToPay.getText());
            } else {
                System.out.println("Empty Fields!");
            }

            paymentMethod = listboxPayment.getValue();
            shippingMethod = listboxShipping.getValue();
            
            
            switch (listboxPayment.getValue()) {
                    case "Carte Postal":
                    paymentMethod = "à notre compte de chèque postal.";
                    break;
                    case "Facture":
                    paymentMethod = "sur facture.";
                    break;
                default:
                    throw new AssertionError();
            }
     
            switch (listboxShipping.getValue()) {
                case "Camion":
                    shippingMethod = "Nous allons faire la livraison par camion";
                    break;
                case "Enlèvement":
                    shippingMethod = "Vous pouvez récupérer la commande dans notre entrepôt";
                    break;
                default:
                    throw new AssertionError();
            }
               
            addDiscount();
            updatePreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String addDiscount() {
        if (true) {
            
        }
        String discountText = "De plus, nous vous offre une remise spéciale de " + discount + "% pour toute commande supérieure à " + discountAmount + " CHF. </p>";
        return "";   
    }

    @FXML
    private void checkboxDiscountPressed(ActionEvent event) {

        if (checkboxDiscount.isSelected()) {
            txtDiscount.setDisable(false);
            txtDiscountAmount.setDisable(false);
        } else {
            txtDiscount.setDisable(true);
            txtDiscountAmount.setDisable(true);
        }
    }

    @FXML
    private void radioShippingBeforePressed(ActionEvent event) {

        shippingTiming = " aussi vite que possible.";
    }

    @FXML
    private void radioShippingAfterPressed(ActionEvent event) {

        shippingTiming = " après la réception de votre paiement.";
    }

}
