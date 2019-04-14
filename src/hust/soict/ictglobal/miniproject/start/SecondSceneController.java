/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class SecondSceneController implements Initializable {

    @FXML
    private StackPane parentContainer;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parentContainer.setOpacity(0);
        makeFadeInTransition();
    }

    private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDelay(Duration.seconds(1));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void handleBtnClick(ActionEvent e) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDelay(Duration.seconds(1));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        if (e.getSource().equals(nextButton)) {
            fadeTransition.setOnFinished((ActionEvent event) -> {
                loadNextScene();
            });
        } else if (e.getSource().equals(prevButton)) {
            fadeTransition.setOnFinished((ActionEvent event) -> {
                loadPrevScene();
            });
        } else {
            System.out.println("Error in button clicked");
        }
        fadeTransition.play();
    }

    private void loadNextScene() {

    }

    private void loadPrevScene() {
        Parent prevView;
        try {
            System.out.println("Start of try");
            prevView = (StackPane) FXMLLoader.load(getClass().getResource("StartBtnWindow.fxml"));
            Scene newScene = new Scene(prevView);
            System.out.println("After newScene");
            Stage curStage = (Stage) parentContainer.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(StartBtnWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
