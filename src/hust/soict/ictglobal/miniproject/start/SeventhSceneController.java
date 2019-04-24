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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class SeventhSceneController implements Initializable {

    @FXML
    private StackPane parentContainer;

    private LoadScene sceneLoader = null;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    @FXML
    private Label text;

    @FXML
    private Label divingText;

    @FXML
    private Label collisionText;

    @FXML
    private ImageView diving;

    @FXML
    private ImageView friction;

    @FXML
    private ImageView collision;

    private boolean flag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        flag = false;
        diving.setOpacity(0);
        friction.setOpacity(0);
        collision.setOpacity(0);
        divingText.setOpacity(0);
        collisionText.setOpacity(0);
        text.setOpacity(0);

        // transition for text
        FadeTransition fadeTransition = FadedTransition.transition(0, 0, 1);
        fadeTransition.setNode(text);
        fadeTransition.play();

        // transition for diving & diving text
        fadeTransition = FadedTransition.transition(2, 0, 1);
        fadeTransition.setNode(diving);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(2, 0, 1);
        fadeTransition.setNode(divingText);
        fadeTransition.play();

        // transition for collision & collision text
        fadeTransition = FadedTransition.transition(4, 0, 1);
        fadeTransition.setNode(collision);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(4, 0, 1);
        fadeTransition.setNode(collisionText);
        fadeTransition.play();

        // transition for friction
        fadeTransition = FadedTransition.transition(6, 0, 1);
        fadeTransition.setNode(friction);
        fadeTransition.play();
    }

    public void handleBtnClick(ActionEvent e) {
        if (!flag) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            if (e.getSource().equals(nextButton)) {
                flag = true;
                fadeTransition.play(); // play transition
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FinalScene.fxml", parentContainer);
                });
            } else if (e.getSource().equals(prevButton)) {
                flag = true;
                fadeTransition.play(); // play transition
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SixthScene.fxml", parentContainer);
                });
            } else {
                System.out.println("Error in button clicked");
            }
        }
    }

    public void handleKeyTyped(KeyEvent e) {
        if (!flag) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            // go back to prev scene
            if (e.getCode() == KeyCode.LEFT) {
                flag = true;
                System.out.println("Left arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SixthScene.fxml", parentContainer);
                });
            }
            // go forward
            if (e.getCode() == KeyCode.RIGHT) {
                flag = true;
                System.out.println("right arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FinalScene.fxml", parentContainer);
                });
            }
            fadeTransition.play(); // play transition
        }
    }
}
