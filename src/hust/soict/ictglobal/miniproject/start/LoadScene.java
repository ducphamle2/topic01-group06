/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Duc Pham Le
 */

public class LoadScene {
    
    /**
     * This method is used to load a different scene depending on the 
     * scene input
     * @param scene - input scene we want to load
     * @param parentContainer - pane that controls the current method that calls this
     */
    public void loadScene(String scene, StackPane parentContainer) {
        Parent secondView;
        try {
            System.out.println("Start of try");
            secondView = (StackPane) FXMLLoader.load(getClass().getResource(scene)); // load new pane
            if (secondView == null) {
                System.out.println("Second view is null due to getResource wrong input: " + scene);
            }
            Scene newScene = new Scene(secondView); // setup the scene
            System.out.println("After newScene");
            Stage curStage = (Stage) parentContainer.getScene().getWindow(); // get the current stage and set our new scene
            System.out.println("After curStage");
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(StartBtnWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
