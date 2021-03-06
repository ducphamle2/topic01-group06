package hust.soict.ictglobal.miniproject.thirdlaw;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import hust.soict.ictglobal.miniproject.utils.FontTextAdjustment;
import java.io.IOException;

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
    private Circle ball1;

    @FXML
    private Circle ball2;

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

    @FXML
    private Group wheel1;

    @FXML
    private Group wheel2;

    @FXML
    private Button startBtn;

    private RotateTransition rotateTransition1;

    private RotateTransition rotateTransition2;

    private TranslateTransition translateTransition1;

    private TranslateTransition translateTransition2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // fix the min width of the textfield so that they dont get too small
        ball1Input.setMinWidth(ball1Input.getPrefWidth() / 1.25);
        ball2Input.setMinWidth(ball1Input.getPrefWidth() / 1.25);
        // init height and width of components
        double oldHeight = 716.0;
        double ballHeight = oldHeight / (ball1.getRadius());

        double oldWidth = 1276.0;
        double ballWidth = oldWidth / (ball1.getRadius());

        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;

                ball1.setRadius(height / ballHeight);
                ball2.setRadius(height / ballHeight);
                line.setStartY(ball1.getCenterY() - ball1.getRadius()); // the bottom edge of the ball1 (starting point)
                line.setEndY(ball1.getCenterY() + ball1.getRadius()); // the top edge of the ball1 (end point)
                lineTwo.setStartX(ball1.getCenterX() - ball1.getRadius()); // the left edge of the ball1 (starting point)
                lineTwo.setEndX(ball1.getCenterX() + ball1.getRadius()); // the right edge of the ball1 (end point)
                lineThree.setStartY(ball2.getCenterY() - ball2.getRadius()); // the left edge of the ball1 (starting point)
                lineThree.setEndY(ball2.getCenterY() + ball2.getRadius()); // the right edge of the ball1 (end point)
                lineFour.setStartX(ball2.getCenterX() - ball2.getRadius()); // the left edge of the ball1 (starting point)
                lineFour.setEndX(ball2.getCenterX() + ball2.getRadius()); // the right edge of the ball1 (end point)

                //ball1.fitHeightProperty().setValue(height / schoolHeight);

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
                ball1.setRadius(width / ballWidth);
                ball2.setRadius(width / ballWidth);
                line.setStartY(ball1.getCenterY() - ball1.getRadius()); // the bottom edge of the ball1 (starting point)
                line.setEndY(ball1.getCenterY() + ball1.getRadius()); // the top edge of the ball1 (end point)
                lineTwo.setStartX(ball1.getCenterX() - ball1.getRadius()); // the left edge of the ball1 (starting point)
                lineTwo.setEndX(ball1.getCenterX() + ball1.getRadius()); // the right edge of the ball1 (end point)
                lineThree.setStartY(ball2.getCenterY() - ball2.getRadius()); // the left edge of the ball1 (starting point)
                lineThree.setEndY(ball2.getCenterY() + ball2.getRadius()); // the right edge of the ball1 (end point)
                lineFour.setStartX(ball2.getCenterX() - ball2.getRadius()); // the left edge of the ball1 (starting point)
                lineFour.setEndX(ball2.getCenterX() + ball2.getRadius()); // the right edge of the ball1 (end point)
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
    private void startDemo() {
        String ball1WreightText = ball1Input.getText();
        String ball2WreightText = ball2Input.getText();
        if (!ball1WreightText.equals("") && !ball2WreightText.equals("")) {
            if (this.rotateTransition1 != null) {
                this.rotateTransition1.stop();
                this.rotateTransition2.stop();
                this.translateTransition1.stop();
                this.translateTransition2.stop();

                wheel1.setRotate(0);
                wheel2.setRotate(0);

                wheel1.setTranslateX(0);
                wheel2.setTranslateX(0);
            }

            int ball1Weight = Integer.parseInt(ball1WreightText);
            int ball2Weight = Integer.parseInt(ball2WreightText);

            double ball1Velocity = ((ball1Weight - ball2Weight) * 30f - 2 * ball2Weight * 30f) / (ball1Weight + ball2Weight);
            double ball2Velocity = (-(ball2Weight - ball1Weight) * 30f + 2 * ball1Weight * 30f) / (ball1Weight + ball2Weight);

            vOne.setText(String.format("v1' = %.2f", ball1Velocity));
            vTwo.setText(String.format("v2' = %.2f", ball2Velocity));

            startBtn.setDisable(true);

            RotateTransition rotateTransition1 = new RotateTransition(Duration.millis(500), wheel1);
            rotateTransition1.setInterpolator(Interpolator.LINEAR);
            rotateTransition1.setByAngle(180);
            rotateTransition1.play();

            RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(500), wheel2);
            rotateTransition2.setInterpolator(Interpolator.LINEAR);
            rotateTransition2.setByAngle(-180);
            rotateTransition2.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(500), wheel1);
            translateTransition1.setCycleCount(1);
            translateTransition1.setInterpolator(Interpolator.LINEAR);
            translateTransition1.setToX(parentContainer.getLayoutBounds().getWidth()/2 - wheel1.getLayoutX() - wheel1.getLayoutBounds().getWidth()/2);
            translateTransition1.play();

            TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(500), wheel2);
            translateTransition2.setCycleCount(1);
            translateTransition2.setInterpolator(Interpolator.LINEAR);
            translateTransition2.setToX(-parentContainer.getLayoutBounds().getWidth()/2 + parentContainer.getLayoutBounds().getWidth() - wheel2.getLayoutX() + wheel2.getLayoutBounds().getWidth()/2);
            translateTransition2.play();

            this.rotateTransition1 = rotateTransition1;
            this.rotateTransition2 = rotateTransition2;
            this.translateTransition1 = translateTransition1;
            this.translateTransition2 = translateTransition2;

            new Thread(() -> {
                try {
                    Thread.sleep(500);

                    rotateTransition1.stop();
                    rotateTransition1.setCycleCount(Animation.INDEFINITE);
                    rotateTransition1.setFromAngle(180);
                    rotateTransition1.setByAngle(ball1Velocity > 0 ? 360 : -360);
                    rotateTransition1.setDuration(Duration.millis(5000 / Math.abs(ball1Velocity) * 30));
                    rotateTransition1.playFromStart();

                    rotateTransition2.stop();
                    rotateTransition2.setCycleCount(Animation.INDEFINITE);
                    rotateTransition2.setFromAngle(-180);
                    rotateTransition2.setByAngle(ball2Velocity > 0 ? 360 : -360);
                    rotateTransition2.setDuration(Duration.millis(5000 / Math.abs(ball2Velocity) * 30));
                    rotateTransition2.playFromStart();

                    translateTransition1.stop();
                    translateTransition1.setFromX(parentContainer.getLayoutBounds().getWidth()/2 - wheel1.getLayoutX() - wheel1.getLayoutBounds().getWidth()/2);
                    translateTransition1.setToX(ball1Velocity > 0 ? parentContainer.getLayoutBounds().getWidth() : -parentContainer.getLayoutBounds().getWidth());
                    translateTransition1.setDuration(Duration.millis(500000 / Math.abs(ball1Velocity)));
                    translateTransition1.playFromStart();

                    translateTransition2.stop();
                    translateTransition2.setFromX(-parentContainer.getLayoutBounds().getWidth()/2 + parentContainer.getLayoutBounds().getWidth() - wheel2.getLayoutX() + wheel2.getLayoutBounds().getWidth()/2);
                    translateTransition2.setToX(ball2Velocity > 0 ? parentContainer.getLayoutBounds().getWidth() : -parentContainer.getLayoutBounds().getWidth());
                    translateTransition2.setDuration(Duration.millis(500000 / Math.abs(ball2Velocity)));
                    translateTransition2.playFromStart();

                    Thread.sleep(1000);
                    startBtn.setDisable(false);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
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
