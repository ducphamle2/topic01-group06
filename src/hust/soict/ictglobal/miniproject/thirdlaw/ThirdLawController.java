package hust.soict.ictglobal.miniproject.thirdlaw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ThirdLawController {
    @FXML
    private AnchorPane scene;

    public void openNewWindow() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ThirdLaw.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Third Law");
            stage.initModality(Modality.APPLICATION_MODAL); // prevent from using the main windows
            stage.show();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openDemo() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ThirdLawDemo.fxml"));
            scene.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
