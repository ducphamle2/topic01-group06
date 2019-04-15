/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.start;

import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Duc Pham Le
 */
public class FadedTransition {
    /**
     * This method is used for fade transition to a different scene
     * @param duration - time for fade transition in seconds
     * @param fromValue - opacity level from what (1 is clearest - 0 is not visible)
     * @param toValue - to the level of opacity we need
     * @return the fade transition instance for other usage like play() or setOnFinished()
     */
    public static FadeTransition transition(int duration, int fromValue, int toValue) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDelay(Duration.seconds(duration));
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        return fadeTransition;
    }
    
    public void makeFadeInTransition(int duration, int fromValue, int toValue, StackPane parentContainer) {
        FadeTransition fadeTransition = FadedTransition.transition(duration, fromValue, toValue); // setup transition
        fadeTransition.setNode(parentContainer);
        fadeTransition.play();
    }
}
