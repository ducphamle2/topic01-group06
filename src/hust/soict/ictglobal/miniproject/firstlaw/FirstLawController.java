/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.firstlaw;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class FirstLawController implements Initializable {
    @FXML
    private AnchorPane scene;

    @FXML
    private Button startBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void openNewWindow() {
        Stage stage = new Stage();
        // create a new window using FirstLaw gui
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FirstLaw.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("First Law");
            stage.initModality(Modality.APPLICATION_MODAL); // prevent from using the main windows
            stage.show();
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openDemo(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FirstLawDemo.fxml"));
            scene.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
