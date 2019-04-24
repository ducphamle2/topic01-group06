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
public class SixthSceneController implements Initializable {

    @FXML
    private StackPane parentContainer;

    private LoadScene sceneLoader = null;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    @FXML
    private ImageView calculus;

    @FXML
    private ImageView physics;

    @FXML
    private ImageView book;

    @FXML
    private Label text;

    @FXML
    private Label textTwo;

    @FXML
    private Label calText;

    @FXML
    private Label phyText;

    private boolean flag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        flag = false;
        book.setOpacity(0);
        text.setOpacity(0);
        textTwo.setOpacity(0);
        calculus.setOpacity(0);
        physics.setOpacity(0);
        calText.setOpacity(0);
        phyText.setOpacity(0);

        // transition for text
        FadeTransition fadeTransition = FadedTransition.transition(1, 0, 1);
        fadeTransition.setNode(text);
        fadeTransition.play();

        // transition for calculus
        fadeTransition = FadedTransition.transition(3, 0, 1);
        fadeTransition.setNode(calculus);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(3, 0, 1);
        fadeTransition.setNode(calText);
        fadeTransition.play();

        // transition for two textx of physics and calculus pics
        fadeTransition = FadedTransition.transition(5, 0, 1);
        fadeTransition.setNode(physics);
        fadeTransition.play();

        fadeTransition = FadedTransition.transition(5, 0, 1);
        fadeTransition.setNode(phyText);
        fadeTransition.play();

        // set text opacity to 0 again for another text to show up
        fadeTransition = FadedTransition.transition(7, 1, 0);
        fadeTransition.setNode(text);
        fadeTransition.play();

        // transition for text two
        fadeTransition = FadedTransition.transition(8, 0, 1);
        fadeTransition.setNode(textTwo);
        fadeTransition.play();

        // transition for book
        fadeTransition = FadedTransition.transition(11, 0, 1);
        fadeTransition.setNode(book);
        fadeTransition.play();
    }

    public void handleBtnClick(ActionEvent e) {
        if (!flag) {
            sceneLoader = new LoadScene();
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
            fadeTransition.setNode(parentContainer);
            if (e.getSource().equals(nextButton)) {
                flag = true;
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SeventhScene.fxml", parentContainer);
                });
            } else if (e.getSource().equals(prevButton)) {
                flag = true;
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("FifthScene.fxml", parentContainer);
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
                    sceneLoader.loadScene("FifthScene.fxml", parentContainer);
                });
            }
            // go forward
            if (e.getCode() == KeyCode.RIGHT) {
                flag = true;
                fadeTransition.play(); // play transition
                System.out.println("right arrow pressed");
                fadeTransition.setOnFinished((ActionEvent event) -> {
                    sceneLoader.loadScene("SeventhScene.fxml", parentContainer);
                });
            }
        }
    }
}
