package hust.soict.ictglobal.miniproject.secondlaw;

import hust.soict.ictglobal.miniproject.utils.FontTextAdjustment;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SecondLawDemoController implements Initializable {
    @FXML
    private TextField forceInput;

    @FXML
    private TextField weightInput;

    @FXML
    private Label accelerationText;

    @FXML
    private Label distanceText;

    @FXML
    private Line kickingLeg;

    @FXML
    private Circle ball;
    
    @FXML
    private GridPane parentContainer;
    
    @FXML
    private Label force;
    
    @FXML
    private Label weight;
    
    @FXML
    private Line line;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // init height and width of components
        double oldHeight = 716.0;
        double ballHeight = oldHeight / (ball.getRadius());
        
        double oldWidth = 1276.0;
        double ballWidth = oldWidth / (ball.getRadius());
        System.out.println("width of textField: " + forceInput.getPrefWidth());
        
        // listen on the changes of the gridpane height and width
        parentContainer.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                double _oldHeight = (double) oldValue;
                
                ball.setRadius(height / ballHeight);
                
                FontTextAdjustment.adjustFontTextHeight(accelerationText, _oldHeight, height, 21);
                FontTextAdjustment.adjustFontTextHeight(distanceText, _oldHeight, height, 21);
                FontTextAdjustment.adjustFontTextHeight(force, _oldHeight, height, 20);
                FontTextAdjustment.adjustFontTextHeight(weight, _oldHeight, height, 20);
            }
        });

        parentContainer.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                double _oldWidth = (double) oldValue;
                
                ball.setRadius(width / ballWidth); // change size of the ball
                FontTextAdjustment.adjustFontTextWidth(accelerationText, _oldWidth, width, 21);
                FontTextAdjustment.adjustFontTextWidth(distanceText, _oldWidth, width, 21);
                FontTextAdjustment.adjustFontTextWidth(force, _oldWidth, width, 20);
                FontTextAdjustment.adjustFontTextWidth(weight, _oldWidth, width, 20);
                
                line.setScaleX(width);
            }
        });

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
            // calculate acceleration and distance
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

            // start animation
            Rotate rotate = new Rotate();
            rotate.setPivotX(kickingLeg.getEndX());
            rotate.setPivotY(kickingLeg.getEndY());

            kickingLeg.getTransforms() .add(rotate);

            Timeline timeline = new Timeline(50);

            KeyFrame frame1 = new KeyFrame(Duration.millis(0), new KeyValue(rotate.angleProperty(), 0));

            KeyFrame frame2 = new KeyFrame(Duration.millis(250), new KeyValue(rotate.angleProperty(), -120));

            timeline.getKeyFrames().addAll(frame1, frame2);

            timeline.play();

            new Thread(() -> {
                try {
                    Thread.sleep(250);

                    PathTransition pathTransition = new PathTransition(Duration.millis(1000), ball);
                    CubicCurveTo curve = new CubicCurveTo(100f, 100f, 200f, 100f, 600f, 600f);
                    Path path = new Path();
                    path.getElements().addAll(new MoveTo(ball.getCenterX(), ball.getCenterY()));
                    path.getElements().addAll(curve);
                    pathTransition.setPath(path);
                    pathTransition.play();
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
            stage.setTitle("Second law demo tutorial");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
