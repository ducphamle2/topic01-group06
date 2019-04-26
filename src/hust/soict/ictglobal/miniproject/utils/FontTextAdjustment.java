/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.soict.ictglobal.miniproject.utils;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Duc Pham Le
 */
public class FontTextAdjustment {

    
    public static void adjustFontTextHeight(Label text, double oldHeight, double newHeight, double initFontSize) {
        double originalHeight = 716.0; // original height of the pane
        // used to adjust font size. If font <= 10 then we dont reduce it anymore cuz too small
        if (text.getFont().getSize() > 11) {
            if (newHeight < oldHeight) {
                Font newFont = Font.font("Arial", FontWeight.BOLD, (text.getFont().getSize() - 0.1) );
                text.setFont(newFont);
            }
        }

        // if new height is larger than old height => increase
        if (newHeight > oldHeight) {
            Font newFont = Font.font("Arial", FontWeight.BOLD, text.getFont().getSize() + 0.1);
            text.setFont(newFont);
        }

        // if we pass the orignial value => return the original font
        if (newHeight >= originalHeight || text.getFont().getSize() >= initFontSize) {
            text.setFont(Font.font("Arial", FontWeight.BOLD, initFontSize));
        }
    }

    public static void adjustFontTextWidth(Label text, double oldWidth, double newWidth, double initFontSize) {
        double originalWidth = 1276.0; // original width of the pane
        // same idea with height
        if (text.getFont().getSize() > 11) {
            if (newWidth < oldWidth) {
                Font newFont = Font.font("Arial", FontWeight.BOLD, text.getFont().getSize() - 0.1);
                text.setFont(newFont);
            }
        }

        if (newWidth > oldWidth) {
            Font newFont = Font.font("Arial", FontWeight.BOLD, text.getFont().getSize() + 0.1);
            text.setFont(newFont);
        }
        if (newWidth >= originalWidth || text.getFont().getSize() >= initFontSize) {
            text.setFont(Font.font("Arial", FontWeight.BOLD, initFontSize)); // 36 is the original font
        }
    }

}
