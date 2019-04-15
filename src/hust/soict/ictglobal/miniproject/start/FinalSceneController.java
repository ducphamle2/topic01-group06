/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class FinalSceneController implements Initializable {

    @FXML
    private StackPane parentContainer;

    private LoadScene sceneLoader = null;

    @FXML
    private Button prevButton;

    @FXML
    private Label firstText;

    @FXML
    private Label secondText;

    @FXML
    private Label thirdText;

    private boolean flag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstText.setOpacity(0);
        flag = false;
        // TODO
        // transition for diving & diving text

        FadeTransition fadeTransition = FadedTransition.transition(1, 0, 1);
        fadeTransition.setNode(firstText);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(7, 1, 0);
        fadeTransition.setNode(firstText);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(8, 0, 1);
        fadeTransition.setNode(secondText);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(14, 1, 0);
        fadeTransition.setNode(secondText);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(15, 0, 1);
        fadeTransition.setNode(thirdText);
        fadeTransition.play();
    }

    public void handleBtnClick(ActionEvent e) {
        if (!flag) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(1, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            if (e.getSource().equals(prevButton)) {
                flag = true;
                fadeTransition.play(); // play transition
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SeventhScene.fxml", parentContainer);
                });
            } else {
                System.out.println("Error in button clicked");
            }
        }
    }

    public void handleKeyTyped(KeyEvent e) {
        if (!flag) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(1, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            // go back to prev scene
            if (e.getCode() == KeyCode.LEFT) {
                flag = true;
                System.out.println("Left arrow pressed");
                fadeTransition.play(); // play transition
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SeventhScene.fxml", parentContainer);
                });
            }
        }
    }
}
