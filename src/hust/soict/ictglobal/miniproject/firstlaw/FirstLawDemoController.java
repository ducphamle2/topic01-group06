package hust.soict.ictglobal.miniproject.firstlaw;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstLawDemoController implements Initializable {
    private int forceLevel = 0;

    private RotateTransition currentRotation;

    private TranslateTransition currentTransition;

    private Duration currentAnimationTime;

    @FXML
    private AnchorPane scene;

    @FXML
    private Group wheel;

    @FXML
    private Text rollingText;

    @FXML
    private Text stationaryText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rollingText.setOpacity(0);
        stationaryText.setOpacity(1);
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
    private void onActionPushLeft() {
        if (this.forceLevel > -5) {
            this.forceLevel--;
        }
        System.out.println("force level push left: " + this.forceLevel);
        setTransition();
    }

    private void setTransition() {
        if (this.currentRotation != null) {
            System.out.println("current rotation is not null now");
            this.currentRotation.stop();
            //this.currentRotation.pause();
        }
        if (this.currentTransition != null) {
            System.out.println("current transition is not null now");
            System.out.println("status: " + this.currentTransition.getStatus().name());
            currentAnimationTime = this.currentTransition.getStatus().equals(Animation.Status.STOPPED) ? currentAnimationTime : this.currentTransition.getCurrentTime().divide(this.currentTransition.getDuration().toMillis());
            System.out.println("current animation time in this.currentTransition: " + currentAnimationTime);
            this.currentTransition.stop();
            //this.currentRotation.pause();
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
                translateTransition.setFromX(scene.getLayoutBounds().getMaxX() / -1.75);
                translateTransition.setToX(scene.getLayoutBounds().getMaxX() / 1.75);
            } else {
                translateTransition.setFromX(scene.getLayoutBounds().getMaxX() / 1.75);
                translateTransition.setToX(scene.getLayoutBounds().getMaxX() / -1.75);
            }
            Double duration = translateTransition.getDuration().toMillis();
            System.out.println("Current duration of translate transition: " + translateTransition.getDuration().toMillis());
            translateTransition.playFrom(currentAnimationTime != null ? currentAnimationTime.multiply(duration) : Duration.millis(2500));
            //translateTransition.play();
            this.currentTransition = translateTransition;
        }
        else {
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
