/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.menu;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import hust.soict.ictglobal.miniproject.utils.StageController;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 *
 * @author Duc Pham Le
 */
public class MenuController extends StageController implements Initializable {

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
            openNewWindow(true, "Biography of Issac Newton", "/hust/soict/ictglobal/miniproject/start/StartBtnWindow.fxml");
        }
        System.out.println("Clicked");
    }

    @FXML
    public void handleClosedBtnClick() {
        System.exit(0);
    }

    @FXML
    public void handleFirstLawBtnClick() throws IOException {
        openNewWindow(true, "First law demo", "/hust/soict/ictglobal/miniproject/firstlaw/FirstLaw.fxml");
    }
    
    @FXML
    public void handleSecondLawBtnClick() throws IOException {
        openNewWindow(true, "Second law demo", "/hust/soict/ictglobal/miniproject/secondlaw/SecondLaw.fxml");
    }
    
    @FXML
    public void handleThirdLawBtnClick() throws IOException {
        openNewWindow(true, "Third law demo", "/hust/soict/ictglobal/miniproject/thirdlaw/ThirdLaw.fxml");
    }

    @FXML
    public void handleAboutBtnClick() throws IOException {
        openNewWindow(false, "About us", "/hust/soict/ictglobal/miniproject/menu/AboutStage.fxml");
    }
    
    @FXML
    public void handleExitBtnClick() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        firstBtnLaw.setDisable(true);
        secondBtnLaw.setDisable(true);
        thirdBtnLaw.setDisable(true);
    }
}
