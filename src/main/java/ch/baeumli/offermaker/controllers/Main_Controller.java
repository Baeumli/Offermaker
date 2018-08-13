package ch.baeumli.offermaker.controllers;

import ch.baeumli.offermaker.Database;
import ch.baeumli.offermaker.Person;
import ch.baeumli.offermaker.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
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
import javafx.scene.control.Label;
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
    @FXML private Button btnShipping;
    @FXML private Button btnDate;
    @FXML private Button btnPayment;
    @FXML private Button btnDiscount;
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
            
    private final Person tempPerson = new Person("M", "John", "Smith", "j.d@gmail.com", "000-000-124", "Google Inc.", "Road Alley 9", "New York", "73823", 1);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (Database.getCn() != null) {
            lblConnection.setText("Connected");
        } else {
            lblConnection.setText("not Connected");
        }
        
        selectedPerson = tempPerson;
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
            selectedPerson = personController.getSelectedPerson();
            lblSex.setText(selectedPerson.getSex());
            lblFirstname.setText(selectedPerson.getFirstname());
            lblLastname.setText(selectedPerson.getLastname());
            lblEmail.setText(selectedPerson.getEmail());
            lblPhone.setText(selectedPerson.getPhone());
            lblCompany.setText(selectedPerson.getCompany());
            updatePreview();
            
        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void updatePreview() {
        
        String salutation;
        switch (selectedPerson.getSex()) {
            case "M":
                salutation = "Sehr geehrter Herr " + selectedPerson.getLastname();
                break;
            case "F":
                salutation = "Sehr geehrte Frau " + selectedPerson.getLastname();
                break;
            default:
                salutation = "Sehr geehrte Damen und Herren";
                break;
        }

        content = "<!DOCTYPE html><html><head> <meta charset=\"utf-8\"/> "
                + "<title>Vertretung während Reise – 1. Dezember 2012</title>"
                + "</head>"
                + "<body> <header> "
                + "<address> Firmenname <br/> Strasse / Nr. <br/> PLZ / Ort <br/>"
                + " </address> <br/> "
                + "<address> " + selectedPerson.getCompany() + "<br/>" + selectedPerson.getFirstname() + " " + selectedPerson.getLastname() + "<br/> " + selectedPerson.getStreet() + "<br/>" + selectedPerson.getZip() + " " + selectedPerson.getCity() + "<br/> "
                + "</address>"
                + " </header> "
                + "<p> Ort, " + dtf.format(now) + "</p>"
                + "<h1> Betreff </h1>"
                + " <div> <p>" + salutation + ", </p>"
                + "<p><br/>Monsieur, Nous avons bien reçu votre commande du 30 mai et nous vous en remercions vivement.</p>"
                + "<p><br/>Nous vous proposons 10 tablettes modèle 0815 au prix de 800 CHF par tablette. "
                + "De plus, nous vous offre une remise spéciale de 5% pour toute commande supérieure à 5000 CHF. </p>"
                + "<p>Nous vous demandons de faire le paiement dans les " + "dTP" + " jours à notre compte de chèque postal.</p>"
                + "<p>Nous allons faire la livraison par camion après la réception de votre paiement.</p>"
                + "<p>En vous remerciant d'avance de votre commande, nous vous prions d'agréer, Monsieur, nos distinguées</p>"
                + "</div>"
                + "<p> Freundliche Grüsse <br/> "
                + "<br/> Name / Position </p>"
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
    private void btnShippingPressed(ActionEvent event) {
    }

    @FXML
    private void btnDatePressed(ActionEvent event) {
        
    }

    @FXML
    private void btnPaymentPressed(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Payment_View.fxml"));
            rootPane = (AnchorPane) loader.load();
            Payment_Controller paymentController = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(rootPane);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Payment");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void btnDiscountPressed(ActionEvent event) {
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
}
