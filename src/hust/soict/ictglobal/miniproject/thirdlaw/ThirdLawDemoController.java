package hust.soict.ictglobal.miniproject.thirdlaw;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ThirdLawDemoController implements Initializable {
    @FXML
    private TextField ball1Input;

    @FXML
    private TextField ball2Input;

    @FXML
    private Text ball1vText;

    @FXML
    private Text ball2vText;

    @FXML
    private Group ball1;

    @FXML
    private Group ball2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            int ball1Weight = Integer.parseInt(ball1WreightText);
            int ball2Weight = Integer.parseInt(ball2WreightText);

            double ball1Velocity = ((ball1Weight - ball2Weight) * 30f - 2 * ball2Weight * 30f)/(ball1Weight + ball2Weight);
            double ball2Velocity = (-(ball2Weight - ball1Weight) * 30f + 2 * ball1Weight * 30f)/(ball1Weight + ball2Weight);

            ball1vText.setText(String.format("v1' = %.2f", ball1Velocity));
            ball2vText.setText(String.format("v2' = %.2f", ball2Velocity));

            RotateTransition rotateTransition1 = new RotateTransition(Duration.millis(500), ball1);
            rotateTransition1.setCycleCount(Animation.INDEFINITE);
            rotateTransition1.setInterpolator(Interpolator.LINEAR);
            rotateTransition1.setByAngle(360);
            rotateTransition1.play();

            RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(500), ball2);
            rotateTransition2.setCycleCount(Animation.INDEFINITE);
            rotateTransition2.setInterpolator(Interpolator.LINEAR);
            rotateTransition2.setByAngle(-360);
            rotateTransition2.play();

        }
    }
}
