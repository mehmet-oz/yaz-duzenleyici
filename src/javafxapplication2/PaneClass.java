package javafxapplication2;


import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import javafx.scene.text.Font;


public class PaneClass {

    public static TextArea ta = getTA();

    public BorderPane getBorderPane() {
        BorderPane pane = new BorderPane();
        pane.setStyle( "-fx-background-color:snow"); 
        pane.setTop(BorderPaneTop.getBorderPaneTop());
        pane.setBottom(HBoxBottom.getHBoxBottom());
        pane.setLeft(VBoxLeft.getVBoxLeft());
        pane.setRight(VBoxRight.getVBoxRight());
        ScrollPane spane = new ScrollPane(ta);
        spane.setFitToHeight(true);
        spane.setFitToWidth(true);
        pane.setCenter(spane);
        ta.setFont(Font.font(Fontlar.textSize));
        return pane;
    }

    public static TextArea getTA() {
        TextArea ta = new TextArea();
        ta.setWrapText(true);
        return ta;
    }

}
