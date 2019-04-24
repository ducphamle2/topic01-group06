package hust.soict.ictglobal.miniproject.secondlaw;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondLawDemoController implements Initializable {
    @FXML
    private TextField forceInput;

    @FXML
    private TextField weightInput;

    @FXML
    private Text accelerationText;

    @FXML
    private Text distanceText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        forceInput.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue;
            if (!newValue.matches("\\d*")) {
                text = newValue.replaceAll("[^\\d]", "");
            }
            if (!text.equals("") && Integer.parseInt(text) > 5000) {
                text = "5000";
            }
            forceInput.setText(text);
        });

        weightInput.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue;
            if (!newValue.matches("\\d*")) {
                text = newValue.replaceAll("[^\\d]", "");
            }
            if (!text.equals("") && Integer.parseInt(text) > 3000) {
                text = "3000";
            }
            weightInput.setText(text);
        });
    }

    @FXML
    private void kickStarted() {
        String forceText = forceInput.getText();
        String weightText = weightInput.getText();
        if (!forceText.equals("") && !weightText.equals("")) {
            int force = Integer.parseInt(forceText);
            int weight = Integer.parseInt(weightText);

            double acceleration = ((double) force) / weight;
            double velocity = acceleration * 0.1;

            double velocityX = velocity * Math.cos(Math.toRadians(30));
            double velocityY = velocity * Math.sin(Math.toRadians(30));

            double flyTime = Math.sqrt(2 * velocityY / 9.8);
            double distance = velocityX * flyTime;

            accelerationText.setText(String.format("Acceleration: %.5f m/s\u00B2", acceleration));
            distanceText.setText(String.format("Distance: %.5f m", distance));
        }
    }
}
