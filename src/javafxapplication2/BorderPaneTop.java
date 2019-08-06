package javafxapplication2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javafxapplication2.Fontlar.fp;
import static javafxapplication2.Fontlar.fw;
import static javafxapplication2.PaneClass.ta;
import static javafxapplication2.Fontlar.textSize;

public class BorderPaneTop {

    static BorderPane borderPane = new BorderPane();

    public static BorderPane getBorderPaneTop() {

        //put menubar
        putMenuBar();

        // bold işlemleri
        Label bold = new Label("B");
        bold.setFont(Fontlar.getFontNormal());
        mouseMoved(bold);
        mouseExited(bold);
        bold.setOnMouseClicked(e -> {
            if (fw.equals(FontWeight.NORMAL)) {
                fw = FontWeight.BOLD;
                PaneClass.ta.setFont(Fontlar.getFontTA());
                bold.setFont(Fontlar.getFontBold());
            } else {
                Fontlar.fw = FontWeight.NORMAL;
                PaneClass.ta.setFont(Fontlar.getFontTA());
                bold.setFont(Fontlar.getFontNormal());
            }
        });

        // italic işlemleri
        Label italic = new Label(" I ");
        mouseMoved(italic);
        mouseExited(italic);
        italic.setFont(Fontlar.getFontNormal());
        italic.setOnMouseClicked(e -> {
            if (Fontlar.fp.equals(FontPosture.REGULAR)) {
                Fontlar.fp = FontPosture.ITALIC;
                PaneClass.ta.setFont(Fontlar.getFontTA());
                italic.setFont(Fontlar.getFontItalic());
            } else {
                Fontlar.fp = FontPosture.REGULAR;
                PaneClass.ta.setFont(Fontlar.getFontTA());
                italic.setFont(Fontlar.getFontNormal());
            }
        });
        //get available font families
        ComboBox<String> comboBox = getComboBox();
        //yazıyı büyüt
        ImageView imgUp = new ImageView(new Image(new File("up.png").toURI().toString()));
        imgUp.setFitHeight(15);
        imgUp.setFitWidth(15);
        Label buyut = new Label("", imgUp);
        mouseMoved(buyut);
        mouseExited(buyut);
        buyut.setOnMouseClicked(e -> increaseSize());
        //yazıyı küçült
        ImageView imgDown = new ImageView(new Image(new File("down.png").toURI().toString()));
        imgDown.setFitHeight(15);
        imgDown.setFitWidth(15);
        Label kucult = new Label("", imgDown);
        mouseMoved(kucult);
        mouseExited(kucult);
        kucult.setOnMouseClicked(e -> decreaseSize());

      
        HBox hb = new HBox(20);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(bold, italic, comboBox, buyut, kucult, getComboBoxForTextSize(), getcolorpicker());
        borderPane.setCenter(hb);

        return borderPane;
    }

    public static void putMenuBar() {
        MenuBar mb = new MenuBar();
        HBox vb = new HBox(mb);

        Menu menu1 = new Menu("Dosyalar");
        mb.getMenus().addAll(menu1);

        MenuItem save = new MenuItem("kaydet");
        MenuItem open = new MenuItem("aç");
        MenuItem item3 = new MenuItem("yazdır");
        save.setOnAction(e -> kaydet());
        open.setOnAction(e -> dosyaAc());
        menu1.getItems().addAll(save, open, item3);
        borderPane.setTop(vb);

    }

    public static ComboBox getComboBox() {
        List<String> list = Font.getFamilies();
        ComboBox<String> cb = new ComboBox();
        for (String item : list) {
            cb.getItems().add(item);
        }
        cb.setValue("Arial");
        cb.setOnAction(e
                -> ta.setFont(Font.font(cb.getValue(), fw, fp, textSize))
        );
        return cb;
    }

    public static ComboBox<Integer> getComboBoxForTextSize() {

        ComboBox<Integer> cb = new ComboBox();
        for (int i = 5; i <= 80; i = i + 5) {
            cb.getItems().add(i);

        }
        cb.setValue(textSize);
        cb.setOnAction(e -> {
            textSize = cb.getValue();
            ta.setFont(Font.font(ta.getFont().getFamily(), fw, fp, textSize));
        }
        );
        return cb;
    }

    public static void increaseSize() {
        ta.setFont(Font.font(ta.getFont().getFamily(), fw, fp, ++textSize));
    }

    public static void decreaseSize() {
        if (textSize > 0) {
            ta.setFont(Font.font(ta.getFont().getFamily(), fw, fp, --textSize));
        }
    }

    public static void mouseMoved(Label L) {
        L.setOnMouseMoved(e
                -> L.setStyle("-fx-background-color:POWDERBLUE"));
    }

    public static void mouseExited(Label L) {
        L.setOnMouseExited(e
                -> L.setStyle("-fx-background-color:snow"));
    }

    public static void kaydet() {
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File f = filechooser.showSaveDialog(new Stage());
            PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
            pw.print(PaneClass.ta.getText());
            pw.close();
        } catch (Exception e) {
            System.out.println("kaydetme hatası");
        }

    }

    public static void dosyaAc() {
        Stage stg = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Dosya Seçiniz: ");
        extensionFilter(fileChooser);
        File f = fileChooser.showOpenDialog(stg);
        if (f != null) {
            try {
                Scanner sc = new Scanner(f);
                String s = "";
                while (sc.hasNext()) {
                    s += sc.nextLine() + "\n";
                }
                PaneClass.ta.setText(s);
                sc.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VBoxLeft.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void extensionFilter(FileChooser filechooser) {
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Dosyaları", "*.txt"));
        filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public static ColorPicker getcolorpicker() {

        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            String s = colorPicker.getValue().toString().replace("0x", "#");

            PaneClass.ta.setStyle("-fx-text-fill:" + s);
        }
        );
        return colorPicker;

    }
}
