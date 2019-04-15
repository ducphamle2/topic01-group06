/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class FifthSceneController implements Initializable {

    @FXML
    private StackPane parentContainer;

    @FXML
    private ImageView ATest;

    @FXML
    private ImageView ATestTwo;

    @FXML
    private ImageView cloud;

    @FXML
    private ImageView cloudTwo;

    @FXML
    private ImageView cloudThree;

    @FXML
    private ImageView student;

    @FXML
    private ImageView school;

    @FXML
    private Label text;

    private LoadScene sceneLoader = null;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    private boolean flag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flag = false;
        ATest.setOpacity(0);
        ATestTwo.setOpacity(0);
        text.setOpacity(0);

        // TODO
        FadeTransition fadeTransition = FadedTransition.transition(1, 0, 1);
        fadeTransition.setNode(text);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(3, 0, 1);
        fadeTransition.setNode(ATest);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(5, 0, 1);
        fadeTransition.setNode(ATestTwo);
        fadeTransition.play();

        // loop animation for first cloud
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setToX(50); // set the destination
        transition.setAutoReverse(true); // this will repeat the transition
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setNode(cloud);
        transition.play();

        // loop animation for 2nd cloud
        transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(3));
        transition.setToX(30); // set the destination
        transition.setAutoReverse(true); // this will repeat the transition
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setNode(cloudTwo);
        transition.play();

        //loop animation for third cloud
        transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(2));
        transition.setToX(-50); // set the destination
        transition.setAutoReverse(true); // this will repeat the transition
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setToX(-50);
        transition.setNode(cloudThree);
        transition.play();

        //transition.play();
    }

    public void handleBtnClick(ActionEvent e) {
        if (!flag) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(1, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            if (e.getSource().equals(nextButton)) {
                flag = true;
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SixthScene.fxml", parentContainer);
                });
            } else if (e.getSource().equals(prevButton)) {
                flag = true;
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FourthScene.fxml", parentContainer);
                });
            } else {
                System.out.println("Error in button clicked");
            }
            fadeTransition.play(); // play transition
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
                fadeTransition.play(); // play transition
                System.out.println("Left arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FourthScene.fxml", parentContainer);
                });
            }
            // go forward
            if (e.getCode() == KeyCode.RIGHT) {
                flag = true;
                fadeTransition.play(); // play transition
                System.out.println("right arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SixthScene.fxml", parentContainer);
                });
            }
        }
    }
}
