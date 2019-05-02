/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.utils;

import hust.soict.ictglobal.miniproject.start.StartBtnWindowController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
            parentContainer.getScene().setRoot(secondView);
            //curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(StartBtnWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is used to load a different scene depending on the 
     * scene input
     * @param scene - input scene we want to load
     * @param parentContainer - pane that controls the current method that calls this
     */
    public void loadScene(String scene, GridPane parentContainer) {
        Parent secondView;
        try {
            System.out.println("Start of try");
            secondView = (GridPane) FXMLLoader.load(getClass().getResource(scene)); // load new pane
            if (secondView == null) {
                System.out.println("Second view is null due to getResource wrong input: " + scene);
            }
            parentContainer.getScene().setRoot(secondView);
            //curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(StartBtnWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
