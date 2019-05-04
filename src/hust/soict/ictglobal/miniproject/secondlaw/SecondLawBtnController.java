package hust.soict.ictglobal.miniproject.secondlaw;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Duc Pham Le
 */
public class SecondLawBtnController {
    
    public void openNewWindow(String fxmlName) throws IOException {
        Stage stage = new Stage();
        // create a new window using FirstLaw gui
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // temp fixed min size of the stage
            stage.setMinWidth((1276 * 600)/ 716);
            stage.setMinHeight(600);
            //stage.setMaximized(true);
            //stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL); // prevent from using the main windows
            stage.setTitle("Biography of Issac Newton");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
