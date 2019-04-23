/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Duc Pham Le
 */
public class JavaNewton extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // build a loader for loading a stage (this case it is menu as we set its location using a constructor)
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/hust/soict/ictglobal/miniproject/menu/MenuView.fxml"));
        Parent root = menuLoader.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Something");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
