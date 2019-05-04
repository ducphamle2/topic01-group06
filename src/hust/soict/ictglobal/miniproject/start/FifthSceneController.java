/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import hust.soict.ictglobal.miniproject.utils.FadedTransition;
import hust.soict.ictglobal.miniproject.utils.FontTextAdjustment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class FifthSceneController implements Initializable {

    @FXML
    private GridPane parentContainer;

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

        double temp = parentContainer.getBoundsInParent().getHeight();
        if (temp >= 600 && temp <= 700) {
            FontTextAdjustment.adjustFontTextHeight(text, 0, 0, 30);
        }
        double temp2 = parentContainer.getBoundsInParent().getWidth();
        if (temp2 >= (1276 / 716) * 600 && temp2 <= (1276 / 716) * 600 + 100) {
            FontTextAdjustment.adjustFontTextWidth(text, 0, 0, 30);
        }

        // init height and width of components
        double oldHeight = 716.0;
        double aTestHeight = oldHeight / ATest.getFitHeight();
        double aTestTwoHeight = oldHeight / ATestTwo.getFitHeight();
        double cloudHeight = oldHeight / cloud.getFitHeight();
        double cloutTwoHeight = oldHeight / cloudTwo.getFitHeight();
        double cloudThreeHeight = oldHeight / cloudThree.getFitHeight();
        double studentHeight = oldHeight / student.getFitHeight();
        double schoolHeight = oldHeight / school.getFitHeight();

        double oldWidth = 1276.0;
        double ATestWidth = oldWidth / ATest.getFitWidth();
        double ATestTwoWidth = oldWidth / ATestTwo.getFitWidth();
        double cloudWidth = oldWidth / cloud.getFitWidth();
        double cloudTwoWidth = oldWidth / cloudTwo.getFitWidth();
        double cloudThreeWidth = oldWidth / cloudThree.getFitWidth();
        double studentWidth = oldWidth / student.getFitWidth();
        double schoolWidth = oldWidth / school.getFitWidth();

        // listen on the changes of the gridpane height and width
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;

                ATest.fitHeightProperty().setValue(height / aTestHeight);
                ATestTwo.fitHeightProperty().setValue(height / aTestTwoHeight);
                cloud.fitHeightProperty().setValue(height / cloudHeight);
                cloudTwo.fitHeightProperty().setValue(height / cloutTwoHeight);
                cloudThree.fitHeightProperty().setValue(height / cloudThreeHeight);
                student.fitHeightProperty().setValue(height / studentHeight);
                school.fitHeightProperty().setValue(height / schoolHeight);
                //text.setPrefHeight(height / (oldHeight / textHeight));

                FontTextAdjustment.adjustFontTextHeight(text, _oldHeight, height, 36);
                
                if (height < _oldHeight - 100 && height < oldHeight - 50) {
                    FontTextAdjustment.adjustFontTextHeight(text, 0, 0, 30);
                }
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                ATest.fitWidthProperty().setValue(width / ATestWidth);
                ATestTwo.fitWidthProperty().setValue(width / ATestTwoWidth);
                cloud.fitWidthProperty().setValue(width / cloudWidth);
                cloudTwo.fitWidthProperty().setValue(width / cloudTwoWidth);
                cloudThree.fitWidthProperty().setValue(width / cloudThreeWidth);
                student.fitWidthProperty().setValue(width / studentWidth);
                school.fitWidthProperty().setValue(width / schoolWidth);
                //text.setPrefWidth(width / (oldWidth / textWidth));
                FontTextAdjustment.adjustFontTextWidth(text, _oldWidth, width, 36);
                
                if (width < _oldWidth - 100 && width < oldWidth - 50) {
                    FontTextAdjustment.adjustFontTextWidth(text, 0, 0, 30);
                }
            }
        });

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
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
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
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
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
