package hust.soict.ictglobal.miniproject.thirdlaw;

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

public class ThirdLawController implements Initializable{
    
    @FXML
    private GridPane parentContainer;
    
    @FXML
    private ImageView ball;
    
    @FXML
    private Label newtonText;
    
    @FXML
    private Label explainText;
    
    @FXML
    private Line line;

    @FXML
    private void openDemo() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ThirdLawDemo.fxml"));
            parentContainer.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double oldHeight = 716.0;
        double ballHeight = oldHeight / ball.getFitHeight();

        double oldWidth = 1276.0;
        double ballWidth = oldWidth / ball.getFitWidth();
        
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;

                ball.fitHeightProperty().setValue(height / ballHeight);
                
                FontTextAdjustment.adjustFontTextHeight(newtonText, _oldHeight, height, 27);
                FontTextAdjustment.adjustFontTextHeight(explainText, _oldHeight, height, 21);
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                ball.fitWidthProperty().setValue(width / ballWidth);
                line.setScaleX(width);
                
                FontTextAdjustment.adjustFontTextWidth(newtonText, _oldWidth, width, 27);
                FontTextAdjustment.adjustFontTextWidth(explainText, _oldWidth, width, 21);
            }
        });
    }
}
