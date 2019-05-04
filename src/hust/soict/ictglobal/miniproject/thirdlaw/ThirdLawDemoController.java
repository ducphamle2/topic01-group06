package hust.soict.ictglobal.miniproject.thirdlaw;

import hust.soict.ictglobal.miniproject.utils.FontTextAdjustment;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ThirdLawDemoController implements Initializable {
    @FXML
    private TextField ball1Input;

    @FXML
    private TextField ball2Input;
    
    @FXML
    private GridPane parentContainer;
    
    @FXML
    private Label velocity;
    
    @FXML
    private Text vOne;
    
    @FXML
    private Text vTwo;
    
    @FXML
    private Circle ball;
    
    @FXML
    private Circle ballTwo;
    
    @FXML
    private Label weight;
    
    @FXML
    private Label weightTwo;
    
    @FXML
    private Line line;
    
    @FXML
    private Line lineTwo;
    
    @FXML
    private Line lineThree;
    
    @FXML
    private Line lineFour;
    
    @FXML
    private Line longLine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        // fix the min width of the textfield so that they dont get too small
        ball1Input.setMinWidth(ball1Input.getPrefWidth() / 1.25);
        ball2Input.setMinWidth(ball1Input.getPrefWidth() / 1.25);
        // init height and width of components
        double oldHeight = 716.0;
        double ballHeight = oldHeight / (ball.getRadius());
        
        double oldWidth = 1276.0;
        double ballWidth = oldWidth / (ball.getRadius());
        
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;
                
                ball.setRadius(height / ballHeight);
                ballTwo.setRadius(height / ballHeight);
                line.setStartY(ball.getCenterY() - ball.getRadius()); // the bottom edge of the ball (starting point)
                line.setEndY(ball.getCenterY() + ball.getRadius()); // the top edge of the ball (end point)
                lineTwo.setStartX(ball.getCenterX() - ball.getRadius()); // the left edge of the ball (starting point)
                lineTwo.setEndX(ball.getCenterX() + ball.getRadius()); // the right edge of the ball (end point)
                lineThree.setStartY(ballTwo.getCenterY() - ballTwo.getRadius()); // the left edge of the ball (starting point)
                lineThree.setEndY(ballTwo.getCenterY() + ballTwo.getRadius()); // the right edge of the ball (end point)
                lineFour.setStartX(ballTwo.getCenterX() - ballTwo.getRadius()); // the left edge of the ball (starting point)
                lineFour.setEndX(ballTwo.getCenterX() + ballTwo.getRadius()); // the right edge of the ball (end point)
                
                //ball.fitHeightProperty().setValue(height / schoolHeight);
                
                FontTextAdjustment.adjustFontTextHeight(velocity, _oldHeight, height, 27);
                FontTextAdjustment.adjustFontTextHeight(vOne, _oldHeight, height, 21);
                FontTextAdjustment.adjustFontTextHeight(vTwo, _oldHeight, height, 21);
                FontTextAdjustment.adjustFontTextHeight(weight, _oldHeight, height, 20);
                FontTextAdjustment.adjustFontTextHeight(weightTwo, _oldHeight, height, 20);
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                ball.setRadius(width / ballWidth);
                ballTwo.setRadius(width / ballWidth);
                line.setStartY(ball.getCenterY() - ball.getRadius()); // the bottom edge of the ball (starting point)
                line.setEndY(ball.getCenterY() + ball.getRadius()); // the top edge of the ball (end point)
                lineTwo.setStartX(ball.getCenterX() - ball.getRadius()); // the left edge of the ball (starting point)
                lineTwo.setEndX(ball.getCenterX() + ball.getRadius()); // the right edge of the ball (end point)
                lineThree.setStartY(ballTwo.getCenterY() - ballTwo.getRadius()); // the left edge of the ball (starting point)
                lineThree.setEndY(ballTwo.getCenterY() + ballTwo.getRadius()); // the right edge of the ball (end point)
                lineFour.setStartX(ballTwo.getCenterX() - ballTwo.getRadius()); // the left edge of the ball (starting point)
                lineFour.setEndX(ballTwo.getCenterX() + ballTwo.getRadius()); // the right edge of the ball (end point)
                //school.fitWidthProperty().setValue(width / schoolWidth);
                
                FontTextAdjustment.adjustFontTextWidth(velocity, _oldWidth, width, 27);
                FontTextAdjustment.adjustFontTextWidth(vOne, _oldWidth, width, 21);
                FontTextAdjustment.adjustFontTextWidth(vTwo, _oldWidth, width, 21);
                FontTextAdjustment.adjustFontTextWidth(weight, _oldWidth, width, 20);
                FontTextAdjustment.adjustFontTextWidth(weightTwo, _oldWidth, width, 20);
                
                longLine.setScaleX(width); // change the length of the line
            }
        });
        
        ball1Input.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue;
            if (!newValue.matches("\\d*")) {
                text = newValue.replaceAll("[^\\d]", "");
            }
            if (!text.equals("") && Integer.parseInt(text) > 3000) {
                text = "3000";
            }
            ball1Input.setText(text);
        });

        ball2Input.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue;
            if (!newValue.matches("\\d*")) {
                text = newValue.replaceAll("[^\\d]", "");
            }
            if (!text.equals("") && Integer.parseInt(text) > 3000) {
                text = "3000";
            }
            ball2Input.setText(text);
        });
    }
    
    @FXML
    private void onActionHelpClicked() throws IOException{
        Stage stage = new Stage();
        // create a new window using FirstLaw gui
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HelpScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL); // prevent from using the main windows
            stage.setTitle("Third law demo tutorial");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
