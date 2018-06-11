/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven.Controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Baeumli
 */
public class ToolbarSend_Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void btnSendMailClick(ActionEvent event) {
        Desktop desktop = Desktop.getDesktop();
        String msg = "mailto:lbaumgartner@hotmail.de?subject=Test&body=Test%20Message";
        URI uri = URI.create(msg);
        try {
            desktop.mail(uri);
        } catch (IOException ex) {
            Logger.getLogger(ToolbarSend_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}