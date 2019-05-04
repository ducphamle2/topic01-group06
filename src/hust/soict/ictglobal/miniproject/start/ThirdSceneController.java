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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import static javax.management.Query.value;

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
    private GridPane parentContainer;

    private LoadScene sceneLoader = null;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    @FXML
    private ImageView houseTwo;

    @FXML
    private Label text;

    private boolean flag = false; // use to check mouse clicked. Only listen once for animation
    private boolean flagTwo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        double schoolHeight = oldHeight / motherKid.getFitHeight();
        double mathHeight = oldHeight / house.getFitHeight();
        double latinHeight = oldHeight / grandparents.getFitHeight();
        double kidHeight = oldHeight / couple.getFitHeight();
        double cityHeight = oldHeight / city.getFitHeight();
        double textboxHeight = oldHeight / textbox.getFitHeight();
        double houseTwoHeight = oldHeight / houseTwo.getFitHeight();

        double oldWidth = 1276.0;
        double schoolWidth = oldWidth / motherKid.getFitWidth();
        double mathWidth = oldWidth / house.getFitWidth();
        double latinWidth = oldWidth / grandparents.getFitWidth();
        double kidWidth = oldWidth / couple.getFitWidth();
        double cityWidth = oldWidth / city.getFitWidth();
        double textboxWidth = oldWidth / textbox.getFitWidth();
        double houseTwoWidth = oldWidth / houseTwo.getFitWidth();

        // listen on the changes of the gridpane height and width
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double old = (double) oldValue;

                motherKid.fitHeightProperty().setValue(height / schoolHeight);
                house.fitHeightProperty().setValue(height / mathHeight);
                grandparents.fitHeightProperty().setValue(height / latinHeight);
                couple.fitHeightProperty().setValue(height / kidHeight);
                city.fitHeightProperty().setValue(height / cityHeight);
                textbox.fitHeightProperty().setValue(height / textboxHeight);
                houseTwo.fitHeightProperty().setValue(height / houseTwoHeight);
                
                FontTextAdjustment.adjustFontTextHeight(text, old, height, 30); // adjust font w.r.t our pane
                
                if (height < old - 100 && height < oldHeight - 50) {
                    FontTextAdjustment.adjustFontTextHeight(text, 0, 0, 30);
                }
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double old = (double) oldValue;

                System.out.println("old width: " + old);
                System.out.println("new width: " + width);
                motherKid.fitWidthProperty().setValue(width / schoolWidth);
                house.fitWidthProperty().setValue(width / mathWidth);
                grandparents.fitWidthProperty().setValue(width / latinWidth);
                couple.fitWidthProperty().setValue(width / kidWidth);
                city.fitWidthProperty().setValue(width / cityWidth);
                textbox.fitWidthProperty().setValue(width / textboxWidth);
                houseTwo.fitWidthProperty().setValue(width / houseTwoWidth);
                
                FontTextAdjustment.adjustFontTextWidth(text, old, width, 30);
                
                if (width < old - 100 && width < oldWidth - 50) {
                    FontTextAdjustment.adjustFontTextWidth(text, 0, 0, 30);
                }
            }
        });

        flagTwo = false;
    }

    public void handleMouseClicked(MouseEvent e) {
        if (!flag) {

            // for the woman holding the baby
            FadeTransition fadeTransition = FadedTransition.transition(0, 1, 0);
            fadeTransition.setNode(motherKid);
            fadeTransition.play();

            fadeTransition = FadedTransition.transition(0, 1, 0);
            fadeTransition.setNode(house);
            fadeTransition.play();

            fadeTransition = FadedTransition.transition(1, 0, 1);
            fadeTransition.setNode(houseTwo);
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
