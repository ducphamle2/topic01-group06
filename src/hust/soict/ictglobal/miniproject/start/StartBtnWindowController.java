/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class StartBtnWindowController {

    public void openNewWindow() throws IOException {
        Stage stage = new Stage();
        // create a new window using FirstLaw gui
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StartBtnWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
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
