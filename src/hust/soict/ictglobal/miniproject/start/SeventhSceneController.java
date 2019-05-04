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

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class SeventhSceneController implements Initializable {

    @FXML
    private GridPane parentContainer;

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
        
        double temp = parentContainer.getBoundsInParent().getHeight();
        if (temp >= 300 && temp <= 550) {
            FontTextAdjustment.adjustFontTextHeight(text, 0, 0, 30);
            
        }
        double temp2 = parentContainer.getBoundsInParent().getWidth();
        if (temp2 >= 300 * 1.56 && temp2 <= 300 * 1.56 + 250) {
            FontTextAdjustment.adjustFontTextWidth(text, 0, 0, 30);
        }
        
        // init height and width of components
        double oldHeight = 716.0;
        double divingHeight = oldHeight / diving.getFitHeight();
        double frictionHeight = oldHeight / friction.getFitHeight();
        double collíionHeight = oldHeight / collision.getFitHeight();

        double oldWidth = 1276.0;
        double divingWidth = oldWidth / diving.getFitWidth();
        double frictionWidth = oldWidth / friction.getFitWidth();
        double collíionWidth = oldWidth / collision.getFitWidth();
        
        // listen on the changes of the gridpane height and width
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;

                diving.fitHeightProperty().setValue(height / divingHeight);
                friction.fitHeightProperty().setValue(height / frictionHeight);
                collision.fitHeightProperty().setValue(height / collíionHeight);
                
                FontTextAdjustment.adjustFontTextHeight(text, _oldHeight, height, 30);
                FontTextAdjustment.adjustFontTextHeight(divingText, _oldHeight, height, 21);
                FontTextAdjustment.adjustFontTextHeight(collisionText, _oldHeight, height, 21);
                
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
                diving.fitWidthProperty().setValue(width / divingWidth);
                friction.fitWidthProperty().setValue(width / frictionWidth);
                collision.fitWidthProperty().setValue(width / collíionWidth);
                
                FontTextAdjustment.adjustFontTextWidth(text, _oldWidth, width, 30);
                FontTextAdjustment.adjustFontTextWidth(divingText, _oldWidth, width, 21);
                FontTextAdjustment.adjustFontTextWidth(collisionText, _oldWidth, width, 21);
                
                if (width < _oldWidth - 100 && width < oldWidth - 50) {
                    FontTextAdjustment.adjustFontTextWidth(text, 0, 0, 30);
                }
            }
        });

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
