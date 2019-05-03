package hust.soict.ictglobal.miniproject.secondlaw;

import hust.soict.ictglobal.miniproject.utils.FontTextAdjustment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

public class SecondLawController implements Initializable{
    @FXML
    private GridPane parentContainer;
    
    @FXML
    private Label label;
    
    @FXML
    private Label text;
    
    @FXML
    private Line line;
    
    @FXML
    private ImageView image;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init height and width of components
        double oldHeight = 716.0;
        double imageHeight = oldHeight / image.getFitHeight();
        
        double oldWidth = 1276.0;
        double imageWidth = oldWidth / image.getFitWidth();
        
        // listen on the changes of the gridpane height and width
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;

                image.fitHeightProperty().setValue(height / imageHeight);
                //text.setPrefHeight(height / (oldHeight / textHeight));
                
                FontTextAdjustment.adjustFontTextHeight(label, _oldHeight, height, 27);
                FontTextAdjustment.adjustFontTextHeight(text, _oldHeight, height, 21);
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                image.fitWidthProperty().setValue(width / imageWidth);
                
                FontTextAdjustment.adjustFontTextWidth(label, _oldWidth, width, 27);
                FontTextAdjustment.adjustFontTextWidth(text, _oldWidth, width, 21);
                
                line.setScaleX(width);
            }
        });
    }

    @FXML
    private void openDemo() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SecondLawDemo.fxml"));
            parentContainer.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
