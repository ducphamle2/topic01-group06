/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.thirdlaw;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Duc Pham Le
 */
public class ThirdLawBtnController {
    
    public void openNewWindow(String fxmlName) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMinWidth((1276 * 600)/ 716);
            stage.setMinHeight(600);
            //stage.setResizable(false);
            stage.setTitle("Third Law");
            stage.initModality(Modality.APPLICATION_MODAL); // prevent from using the main windows
            stage.show();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
