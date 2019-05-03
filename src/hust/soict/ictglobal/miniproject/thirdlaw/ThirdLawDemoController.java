package hust.soict.ictglobal.miniproject.thirdlaw;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ThirdLawDemoController implements Initializable {
    @FXML
    private TextField ball1Input;

    @FXML
    private TextField ball2Input;

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
