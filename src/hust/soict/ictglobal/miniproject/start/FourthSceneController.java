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
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class FourthSceneController implements Initializable {

    @FXML
    private GridPane parentContainer;

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
        System.out.println("parent containner: " + parentContainer);

        // init height and width of components
        double oldHeight = 716.0;
        double schoolHeight = oldHeight / school.getFitHeight();
        double mathHeight = oldHeight / math.getFitHeight();
        double latinHeight = oldHeight / latin.getFitHeight();
        double kidHeight = oldHeight / kidStudying.getFitHeight();
        double textHeight = oldHeight / text.getHeight();

        double oldWidth = 1276.0;
        double schoolWidth = oldWidth / school.getFitWidth();
        double mathWidth = oldWidth / math.getFitWidth();
        double latinWidth = oldWidth / latin.getFitWidth();
        double kidWidth = oldWidth / kidStudying.getFitWidth();
        double textWidth = oldWidth / text.getWidth();
        
        // listen on the changes of the gridpane height and width
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;

                school.fitHeightProperty().setValue(height / schoolHeight);
                math.fitHeightProperty().setValue(height / mathHeight);
                latin.fitHeightProperty().setValue(height / latinHeight);
                kidStudying.fitHeightProperty().setValue(height / kidHeight);
                //text.setPrefHeight(height / (oldHeight / textHeight));
                
                FontTextAdjustment.adjustFontTextHeight(text, _oldHeight, height, 36);
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                school.fitWidthProperty().setValue(width / schoolWidth);
                math.fitWidthProperty().setValue(width / mathWidth);
                latin.fitWidthProperty().setValue(width / latinWidth);
                kidStudying.fitWidthProperty().setValue(width / kidWidth);
                //text.setPrefWidth(width / (oldWidth / textWidth));
                
                FontTextAdjustment.adjustFontTextWidth(text, _oldWidth, width, 36);
            }
        });
        // TODO
        // transition for kid pic
        Line line = new Line();
        int kidPicX = 270;
        int kidPicY = 120;
        line.setStartX(kidPicX - 700);
        line.setStartY(kidPicY);
        line.setEndX(kidPicX + 100);
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
