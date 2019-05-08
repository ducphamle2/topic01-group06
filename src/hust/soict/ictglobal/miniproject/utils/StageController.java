/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Duc Pham Le
 */
public class StageController {
    
    /**
     * This method is used to open a new stage.
     * @param setResizable - if true then the stage is resizable.
     * @param stageName - the name of the stage
     * @param fxmlName - the location of the fxml file
     * @throws IOException 
     */
    public void openNewWindow(boolean setResizable, String stageName, String fxmlName) throws IOException {
        Stage stage = new Stage();
        // create a new window using FirstLaw gui
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // temp fixed min size of the stage
            stage.setMinWidth((1276 * 500)/ 716);
            stage.setMinHeight(500);
            //stage.setMaximized(true);
            stage.setResizable(setResizable);
            stage.initModality(Modality.APPLICATION_MODAL); // prevent from using the main windows
            stage.setTitle(stageName);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
