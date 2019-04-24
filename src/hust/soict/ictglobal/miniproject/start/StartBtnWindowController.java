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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class StartBtnWindowController implements Initializable {

    @FXML
    private StackPane parentContainer;

    private boolean flag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flag = false;
    }

    public void openNewWindow() throws IOException {
        Stage stage = new Stage();
        // create a new window using FirstLaw gui
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StartBtnWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL); // prevent from using the main windows
            stage.setTitle("Biography of Issac Newton");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleButtonClick(ActionEvent event) { // this catches the event when clicking the next button
        if (!flag) {
            makeFadeOut();
            flag = true;
        }
    }

    private void makeFadeOut() {
        // using fade transition to fade to another scene
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDelay(Duration.seconds(0)); // fade after 1 sec
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(1); // this is opacity, from 1 to 0 means from clearest to disappear
        fadeTransition.setToValue(0);

        // load another scene after finishing fading
        fadeTransition.setOnFinished((ActionEvent event) -> {
            loadNextScene();
        });
        fadeTransition.play();
    }

    // setup for the second scene
    private void loadNextScene() {
        Parent secondView;
        try {
            System.out.println("Start of try");
            secondView = (StackPane) FXMLLoader.load(getClass().getResource("SecondScene.fxml"));
            Scene newScene = new Scene(secondView);
            System.out.println("After newScene");
            Stage curStage = (Stage) parentContainer.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(StartBtnWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleKeyTyped(KeyEvent e) {
        if (!flag) {
            LoadScene sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            // go forward
            if (e.getCode() == KeyCode.RIGHT) {
                flag = true;
                System.out.println("right arrow pressed");
                fadeTransition.play(); // play transition
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SecondScene.fxml", parentContainer);
                });
            }
        }
    }
}
