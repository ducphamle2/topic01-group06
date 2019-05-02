/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.firstlaw;

import hust.soict.ictglobal.miniproject.utils.FontTextAdjustment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;

/**
 * FXML Controller class
 *
 * @author Duc Pham Le
 */
public class FirstLawController implements Initializable {
    @FXML
    private GridPane parentContainer;

    @FXML
    private ImageView ball;
    @FXML
    private ImageView ballTwo;
    @FXML
    private Label text;
    @FXML
    private Label textTwo;
    @FXML
    private Shape line;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // init height and width of components
        double oldHeight = 716.0;
        double ballHeight = oldHeight / ball.getFitHeight();
        double ballTwoHeight = oldHeight / ballTwo.getFitHeight();

        double oldWidth = 1276.0;
        double ballWidth = oldWidth / ball.getFitWidth();
        double ballTwoWidth = oldWidth / ballTwo.getFitWidth();
        
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;

                ball.fitHeightProperty().setValue(height / ballHeight);
                ballTwo.fitHeightProperty().setValue(height / ballTwoHeight);
                
                FontTextAdjustment.adjustFontTextHeight(text, _oldHeight, height, 27);
                FontTextAdjustment.adjustFontTextHeight(textTwo, _oldHeight, height, 19);
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                ball.fitWidthProperty().setValue(width / ballWidth);
                ballTwo.fitWidthProperty().setValue(width / ballTwoWidth);
                line.setScaleX(width);
                
                FontTextAdjustment.adjustFontTextWidth(text, _oldWidth, width, 27);
                FontTextAdjustment.adjustFontTextWidth(textTwo, _oldWidth, width, 19);
            }
        });
    }

    @FXML
    private void openDemo(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FirstLawDemo.fxml"));
            parentContainer.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
