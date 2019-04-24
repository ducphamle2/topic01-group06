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
        setTransition();
    }

    @FXML
    private void onActionPushLeft() {
        if (this.forceLevel > -5) {
            this.forceLevel--;
        }
        setTransition();
    }

    private void setTransition() {
        if (this.forceLevel == 0) {
            rollingText.setOpacity(0);
            stationaryText.setOpacity(1);
        } else {
            rollingText.setOpacity(1);
            stationaryText.setOpacity(0);
        }
        if (this.currentRotation != null) {
            this.currentRotation.stop();
        }
        if (this.currentTransition != null) {
            currentAnimationTime = this.currentTransition.getStatus().equals(Animation.Status.STOPPED) ? Duration.millis(1).subtract(currentAnimationTime) : this.currentTransition.getCurrentTime().divide(this.currentTransition.getDuration().toMillis());
            this.currentTransition.stop();
        }
        if (this.forceLevel != 0) {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(5000 / Math.abs(this.forceLevel)), wheel);
            rotateTransition.setCycleCount(Animation.INDEFINITE);
            rotateTransition.setInterpolator(Interpolator.LINEAR);
            rotateTransition.setByAngle(this.forceLevel > 0 ? 360 : -360);
            rotateTransition.play();
            this.currentRotation = rotateTransition;

            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(5000 / Math.abs(this.forceLevel)), wheel);
            translateTransition.setCycleCount(Animation.INDEFINITE);
            translateTransition.setInterpolator(Interpolator.LINEAR);
            if (this.forceLevel > 0) {
                translateTransition.setFromX(scene.getLayoutBounds().getMaxX() / -2);
                translateTransition.setToX(scene.getLayoutBounds().getMaxX() / 2);
            } else {
                translateTransition.setFromX(scene.getLayoutBounds().getMaxX() / 2);
                translateTransition.setToX(scene.getLayoutBounds().getMaxX() / -2);
            }
            translateTransition.jumpTo(currentAnimationTime != null ? currentAnimationTime.multiply(translateTransition.getDuration()) : Duration.millis(2500));
            translateTransition.play();
            this.currentTransition = translateTransition;
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
