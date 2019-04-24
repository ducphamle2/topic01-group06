/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class FourthSceneController implements Initializable {

    @FXML
    private StackPane parentContainer;

    @FXML
    private ImageView latin;

    @FXML
    private ImageView math;

    @FXML
    private ImageView kidStudying;

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
        latin.setOpacity(0);
        school.setOpacity(0);
        math.setOpacity(0);
        text.setOpacity(0);
        
        // TODO

        // transition for kid pic
        Line line = new Line();
        int kidPicX = 270;
        int kidPicY = 120;
        line.setStartX(kidPicX - 700);
        line.setStartY(kidPicY);
        line.setEndX(kidPicX);
        line.setEndY(kidPicY);

        PathTransition transition = new PathTransition();
        transition.setNode(kidStudying);
        transition.setDuration(Duration.seconds(2));
        transition.setPath(line);

        // for other images
        FadeTransition fadeTransition = FadedTransition.transition(3, 0, 1);
        fadeTransition.setNode(school);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(3, 0, 1);
        fadeTransition.setNode(text);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(5, 0, 1);
        fadeTransition.setNode(latin);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(5, 0, 1);
        fadeTransition.setNode(math);
        fadeTransition.play();

        transition.play();
    }

    public void handleBtnClick(ActionEvent e) {
        if (!flag) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            if (e.getSource().equals(nextButton)) {
                flag = true;
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FifthScene.fxml", parentContainer);
                });
            } else if (e.getSource().equals(prevButton)) {
                flag = true;
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("ThirdScene.fxml", parentContainer);
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
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            // go back to prev scene
            if (e.getCode() == KeyCode.LEFT) {
                flag = true;
                fadeTransition.play(); // play transition
                System.out.println("Left arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("ThirdScene.fxml", parentContainer);
                });
            }
            // go forward
            if (e.getCode() == KeyCode.RIGHT) {
                flag = true;
                fadeTransition.play(); // play transition
                System.out.println("right arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FifthScene.fxml", parentContainer);
                });
            }
        }
    }
}
