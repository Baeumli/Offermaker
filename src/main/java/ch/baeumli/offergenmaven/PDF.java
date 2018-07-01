/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import javafx.stage.FileChooser;

/**
 *
 * @author lbaum
 */
public class PDF {
 
    Document doc = new Document();

    public void createPdf() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Save to...");
        fc.setInitialFileName("Offer.pdf");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = fc.showSaveDialog(null);
        if (file != null) {
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(file));
                doc.open();
                //Add pdf content here
                
                doc.add(new Paragraph("This is a sample text of the offergenmaven project"));
                
                doc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
