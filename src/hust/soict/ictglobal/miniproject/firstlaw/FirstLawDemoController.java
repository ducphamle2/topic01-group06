package hust.soict.ictglobal.miniproject.firstlaw;

import hust.soict.ictglobal.miniproject.utils.FontTextAdjustment;
import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.util.Duration;

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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FirstLawDemoController implements Initializable {
    private int forceLevel = 0;

    private RotateTransition currentRotation;

    private TranslateTransition currentTransition;

    private Duration currentAnimationTime;

    @FXML
    private GridPane parentContainer; // scene

    @FXML
    private Group wheel;
    
    @FXML
    private Circle ball;
    
    @FXML
    private Line line; // vertical line of the ball
    
    @FXML
    private Line lineTwo; // horizontal line of the ball
    
    @FXML
    private Line lineThree; // horizontal line (surface)

    @FXML
    private Label rollingText;

    @FXML
    private Label stationaryText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wheel.setAutoSizeChildren(true);
        rollingText.setOpacity(0);
        stationaryText.setOpacity(1);
        
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
                line.setStartY(ball.getCenterY() - ball.getRadius()); // the bottom edge of the ball (starting point)
                line.setEndY(ball.getCenterY() + ball.getRadius()); // the top edge of the ball (end point)
                lineTwo.setStartX(ball.getCenterX() - ball.getRadius()); // the left edge of the ball (starting point)
                lineTwo.setEndX(ball.getCenterX() + ball.getRadius()); // the right edge of the ball (end point)
                
                //ball.fitHeightProperty().setValue(height / schoolHeight);
                
                FontTextAdjustment.adjustFontTextHeight(rollingText, _oldHeight, height, 21);
                FontTextAdjustment.adjustFontTextHeight(stationaryText, _oldHeight, height, 21);
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;

                System.out.println("old width: " + oldWidth);
                ball.setRadius(width / ballWidth); // change size of the ball
                line.setStartY(ball.getCenterY() - ball.getRadius()); // the bottom edge of the ball (starting point)
                line.setEndY(ball.getCenterY() + ball.getRadius()); // the top edge of the ball (end point)
                lineTwo.setStartX(ball.getCenterX() - ball.getRadius()); // the left edge of the ball (starting point)
                lineTwo.setEndX(ball.getCenterX() + ball.getRadius()); // the right edge of the ball (end point)
                lineThree.setScaleX(width); // change the length of the line
                //school.fitWidthProperty().setValue(width / schoolWidth);
                
                FontTextAdjustment.adjustFontTextWidth(rollingText, _oldWidth, width, 21);
                FontTextAdjustment.adjustFontTextWidth(stationaryText, _oldWidth, width, 21);
            }
        });
    }

    @FXML
    private void onActionPushRight() {
        if (this.forceLevel < 5) {
            this.forceLevel++;
        }
        System.out.println("force level push right: " + this.forceLevel);
        setTransition();
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
            stage.setTitle("First law demo tutorial");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onActionPushLeft() {
        if (this.forceLevel > -5) {
            this.forceLevel--;
        }
        System.out.println("force level push left: " + this.forceLevel);
        setTransition();
    }

    private void setTransition() {
        if (this.currentRotation != null) {
            this.currentRotation.stop();
        }
        if (this.currentTransition != null) {
            System.out.println("status: " + this.currentTransition.getStatus().name());
            currentAnimationTime = this.currentTransition.getStatus().equals(Animation.Status.STOPPED) ? (
                    currentRotation.getByAngle() * forceLevel > 0 ? currentAnimationTime : Duration.millis(1).subtract(currentAnimationTime)
                    ) : this.currentTransition.getCurrentTime().divide(this.currentTransition.getDuration().toMillis());
            System.out.println("current animation time in this.currentTransition: " + currentAnimationTime);
            this.currentTransition.stop();
        }
        if (this.forceLevel != 0) {
            // set text opacity
            rollingText.setOpacity(1);
            stationaryText.setOpacity(0);
            
            //init rotateTranslation with a duration
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(5000 / Math.abs(this.forceLevel)), wheel);
            rotateTransition.setCycleCount(Animation.INDEFINITE);
            rotateTransition.setInterpolator(Interpolator.LINEAR);
            rotateTransition.setByAngle(this.forceLevel > 0 ? 360 : -360);
            rotateTransition.play();
            this.currentRotation = rotateTransition;

            //init translateTransition with a duration and a node wheel
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(5000 / Math.abs(this.forceLevel)), wheel);
            translateTransition.setCycleCount(Animation.INDEFINITE); // infinite loop here
            translateTransition.setInterpolator(Interpolator.LINEAR); // going in linear - straight line
            
            if (this.forceLevel > 0) {
                // use to get get out of the scene and then come back in the opposite position
                translateTransition.setFromX(parentContainer.getLayoutBounds().getMaxX() / -1.75);
                translateTransition.setToX(parentContainer.getLayoutBounds().getMaxX() / 1.75);
            } else {
                translateTransition.setFromX(parentContainer.getLayoutBounds().getMaxX() / 1.75);
                translateTransition.setToX(parentContainer.getLayoutBounds().getMaxX() / -1.75);
            }
            Double duration = translateTransition.getDuration().toMillis();
            translateTransition.playFrom(currentAnimationTime != null ? currentAnimationTime.multiply(duration) : Duration.millis(2500));
            translateTransition.play();
            this.currentTransition = translateTransition;
        } else {
            // set text again
            rollingText.setOpacity(0);
            stationaryText.setOpacity(1);
        }
    }

    @FXML
    private void resetDemo() {
        rollingText.setOpacity(0);
        stationaryText.setOpacity(1);
        this.forceLevel = 0;
        this.currentAnimationTime = null;
        if (this.currentRotation != null) {
            this.currentRotation.stop();
            this.currentRotation.getNode().setRotate(0);
            this.currentRotation = null;
        }
        if (this.currentTransition != null) {
            this.currentTransition.stop();
            this.currentTransition.getNode().setTranslateX(0);
            this.currentTransition = null;
        }
    }
}
