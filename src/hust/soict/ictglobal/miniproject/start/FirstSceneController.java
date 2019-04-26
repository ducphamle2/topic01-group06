/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import hust.soict.ictglobal.miniproject.utils.FadedTransition;
import hust.soict.ictglobal.miniproject.utils.FontTextAdjustment;
import hust.soict.ictglobal.miniproject.utils.LoadScene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Duc Pham Le
 */
public class FirstSceneController implements Initializable {

    private boolean flag;
    @FXML
    private GridPane parentContainer;

    @FXML
    private ImageView newton;
    
    @FXML
    private Label text;

    private LoadScene sceneLoader = new LoadScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flag = false;
        
        // set background color to black !!
        parentContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        System.out.println("gird pane: " + parentContainer);
        double oldHeight = 716.0;
        double newtonHeight = oldHeight / newton.getFitHeight();

        double oldWidth = 1276.0;
        double newtonWidth = oldWidth / newton.getFitWidth();

        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;

                newton.fitHeightProperty().setValue(height / newtonHeight);
                
                FontTextAdjustment.adjustFontTextHeight(text, _oldHeight, height, 25);
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                newton.fitWidthProperty().setValue(width / newtonWidth);
                
                FontTextAdjustment.adjustFontTextWidth(text, _oldWidth, width, 25);
            }
        });

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
        sceneLoader.loadScene("SecondScene.fxml", parentContainer);
    }

    public void handleKeyTyped(KeyEvent e) {
        if (!flag) {
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
