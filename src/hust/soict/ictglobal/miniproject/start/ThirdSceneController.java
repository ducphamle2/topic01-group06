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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class ThirdSceneController implements Initializable {

    @FXML
    private ImageView motherKid;

    @FXML
    private ImageView house;

    @FXML
    private ImageView grandparents;

    @FXML
    private ImageView couple;

    @FXML
    private ImageView city;

    @FXML
    private ImageView textbox;

    @FXML
    private Label text;

    @FXML
    private StackPane parentContainer;

    private LoadScene sceneLoader = null;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    private boolean flag = false; // use to check mouse clicked. Only listen once for animation
    private boolean flagTwo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagTwo = false;
    }

    public void handleMouseClicked(MouseEvent e) {
        if (!flag) {
            // for the house
            Line line = new Line();
            int x = 200;
            int y = 203;
            line.setStartX(200);
            line.setStartY(203);
            line.setEndX(x - 720);
            line.setEndY(y + 150);
            house.setFitHeight(house.getFitHeight() - 110);
            house.setFitWidth(house.getFitWidth() - 110);

            System.out.println("after creating polyline");
            PathTransition transition = new PathTransition();
            transition.setNode(house);
            transition.setDuration(Duration.seconds(2));
            transition.setPath(line);

            // for the woman holding the baby
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0);
            fadeTransition.setNode(motherKid);
            fadeTransition.play();

            //for the grandparents and the kid
            fadeTransition = FadedTransition.transition(2, 0, 1);
            fadeTransition.setNode(grandparents);
            fadeTransition.play();

            //for the couple
            fadeTransition = FadedTransition.transition(2, 0, 1);
            fadeTransition.setNode(couple);
            fadeTransition.play();

            //for the city
            fadeTransition = FadedTransition.transition(2, 0, 1);
            fadeTransition.setNode(city);
            fadeTransition.play();

            //for the textbox
            fadeTransition = FadedTransition.transition(2, 0, 1);
            fadeTransition.setNode(textbox);
            fadeTransition.play();

            //for the text
            fadeTransition = FadedTransition.transition(2, 0, 1);
            fadeTransition.setNode(text);
            fadeTransition.play();

            transition.play();

            flag = true; // does not allow to listen to mouse click again
        }
    }

    public void handleBtnClick(ActionEvent e) {
        if (!flagTwo) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            if (e.getSource().equals(nextButton)) {
                flagTwo = true;
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FourthScene.fxml", parentContainer);
                });
            } else if (e.getSource().equals(prevButton)) {
                flagTwo = true;
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SecondScene.fxml", parentContainer);
                });
            } else {
                System.out.println("Error in button clicked");
            }
            fadeTransition.play(); // play transition
        }
    }

    public void handleKeyTyped(KeyEvent e) {
        if (!flagTwo) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            // go back to prev scene
            if (e.getCode() == KeyCode.LEFT) {
                flagTwo = true;
                fadeTransition.play(); // play transition
                System.out.println("Left arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SecondScene.fxml", parentContainer);
                });
            }
            // go forward
            if (e.getCode() == KeyCode.RIGHT) {
                flagTwo = true;
                fadeTransition.play(); // play transition
                System.out.println("right arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FourthScene.fxml", parentContainer);
                });
            }
        }
    }
}
