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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class FinalSceneController implements Initializable {

    @FXML
    private GridPane parentContainer;

    private LoadScene sceneLoader = null;

    @FXML
    private Button prevButton;

    @FXML
    private Label firstText;

    @FXML
    private Label secondText;
    
    @FXML
    private ImageView newton;

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
        
        // set background color to black
        parentContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
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
                
                FontTextAdjustment.adjustFontTextHeight(firstText, _oldHeight, height, 30);
                FontTextAdjustment.adjustFontTextHeight(secondText, _oldHeight, height, 30);
                FontTextAdjustment.adjustFontTextHeight(thirdText, _oldHeight, height, 30);
            }
        });
        
        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                newton.fitWidthProperty().setValue(width / newtonWidth);
                
                FontTextAdjustment.adjustFontTextWidth(firstText, _oldWidth, width, 30);
                FontTextAdjustment.adjustFontTextWidth(secondText, _oldWidth, width, 30);
                FontTextAdjustment.adjustFontTextWidth(thirdText, _oldWidth, width, 30);
            }
        });

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
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
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
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0); // setup transition
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
