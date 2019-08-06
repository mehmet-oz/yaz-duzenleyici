package javafxapplication2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        Application.launch(args);

    }
 

    @Override
    public void start(Stage stg) throws Exception {
        PaneClass p = new PaneClass();
        BorderPane pane = p.getBorderPane();
        Scene scene = new Scene(pane, Color.CADETBLUE);
        stg.setScene(scene);
        stg.show();
    }
    
    
    

}
