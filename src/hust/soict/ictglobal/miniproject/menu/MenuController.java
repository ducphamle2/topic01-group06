/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.menu;

import hust.soict.ictglobal.miniproject.firstlaw.FirstLawBtnController;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import hust.soict.ictglobal.miniproject.start.MenuBtnWindowController;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Duc Pham Le
 */
public class MenuController implements Initializable {

    @FXML
    private Button firstBtnLaw;
    @FXML
    private Button secondBtnLaw;
    @FXML
    private Button thirdBtnLaw;
    @FXML
    private Button startButton;

    @FXML
    public void onMouseClicked(MouseEvent e) throws IOException {
        if (e.getSource().equals(startButton)) { //get source here to find the source that causes the event
            firstBtnLaw.setDisable(false);
            secondBtnLaw.setDisable(false);
            thirdBtnLaw.setDisable(false);
            new MenuBtnWindowController().openNewWindow("StartBtnWindow.fxml");
        }
        System.out.println("Clicked");
    }

    @FXML
    public void handleClosedBtnClick() {
        System.exit(0);
    }

    @FXML
    public void handleFirstLawBtnClick() throws IOException {
        new FirstLawBtnController().openNewWindow("FirstLaw.fxml");
    }
    
    @FXML
    public void handleSecondBtnClick() throws IOException {
        
    }

    @FXML
    public void handleAboutBtnClick() throws IOException {
        Stage stage = new Stage();
        // create a new window using FirstLaw gui
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AboutStage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("About us");
            stage.initModality(Modality.APPLICATION_MODAL); // prevent from using the main windows
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        firstBtnLaw.setDisable(true);
        secondBtnLaw.setDisable(true);
        thirdBtnLaw.setDisable(true);
    }
}
