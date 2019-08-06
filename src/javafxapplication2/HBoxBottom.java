package javafxapplication2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import yazimHatalari.SpellingCorrector;

public class HBoxBottom {

    static String ilktext;
    static boolean b;
    static String sontext = "";
    static SpellingCorrector kelimeDuzeltici;

    public static HBox getHBoxBottom() {
        HBox hb = new HBox(20);
        hb.setPadding(new Insets(20, 20, 20, 20));
        Button correct = new Button("düzelt");
        kelimeDuzeltici = new SpellingCorrector(1300000);

        correct.setOnMouseClicked(e -> {
            ilktext = PaneClass.ta.getText().toString();

            sontext = duzelt(ilktext);

            PaneClass.ta.setText(sontext);
            sontext = "";
            b = true;
        });
        Button geri = new Button("Geri");
        geri.setOnAction(e -> {
            if (b) {
                PaneClass.ta.setText(ilktext);
                b = false;
            }
        }
        );

        hb.setAlignment(Pos.CENTER);

        hb.getChildren()
                .addAll(correct, geri);
        return hb;
    }

    public static String duzelt(String ilktext) {

        String strwithEmpty[] = ilktext.split(" ");
        String[] str = remove(strwithEmpty);

        for (int i = 0; i < str.length; i++) {

            if (str[i].startsWith("\"") && str[i].endsWith("\"")) {
                sontext += "\"" + kelimeDuzeltici.correct(str[i].substring(1, str[i].length() - 1)) + "\" ";
            } else if (str[i].startsWith("\"") && !str[i].endsWith("[.:,;!?]")) {
                sontext += "\"" + kelimeDuzeltici.correct(str[i].substring(1, str[i].length())) + " ";
            } else if (str[i].endsWith("\"")) {
                sontext += kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)) + "\" ";
            } else if (str[i].endsWith(",")) {
                StringBuffer sb = new StringBuffer(kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)));
                if (Character.isUpperCase(str[i].charAt(0))) {
                    sb.setCharAt(0, str[i].charAt(0));

                    sontext += sb.toString() + ", ";
                } else {
                    sontext += kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)) + ", ";
                }

            } else if (str[i].endsWith(";")) {
                StringBuffer sb = new StringBuffer(kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)));
                if (Character.isUpperCase(str[i].charAt(0))) {
                    sb.setCharAt(0, str[i].charAt(0));

                    sontext += sb.toString() + "; ";
                } else {
                    sontext += kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)) + "; ";
                }

            } else if (str[i].endsWith(":")) {
                StringBuffer sb = new StringBuffer(kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)));
                if (Character.isUpperCase(str[i].charAt(0))) {
                    sb.setCharAt(0, str[i].charAt(0));

                    sontext += sb.toString() + ": ";
                } else {
                    sontext += kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)) + ": ";
                }

            } else if (str[i].endsWith(".")) {
                StringBuffer sb = new StringBuffer(kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)));
                if (Character.isUpperCase(str[i].charAt(0))) {
                    sb.setCharAt(0, str[i].charAt(0));

                    sontext += sb.toString() + ". ";
                } else {
                    sontext += kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)) + ". ";
                }

            } else if (str[i].endsWith("?")) {
                StringBuffer sb = new StringBuffer(kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)));
                if (Character.isUpperCase(str[i].charAt(0))) {
                    sb.setCharAt(0, str[i].charAt(0));

                    sontext += sb.toString() + "? ";
                } else {
                    sontext += kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)) + "? ";
                }

            } else if (str[i].endsWith("!")) {
                StringBuffer sb = new StringBuffer(kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)));
                if (Character.isUpperCase(str[i].charAt(0))) {
                    sb.setCharAt(0, str[i].charAt(0));

                    sontext += sb.toString() + "! ";
                } else {
                    sontext += kelimeDuzeltici.correct(str[i].substring(0, str[i].length() - 1)) + "! ";
                }

            } else {
                StringBuffer sb = new StringBuffer(kelimeDuzeltici.correct(str[i].substring(0, str[i].length())));
                if (Character.isUpperCase(str[i].charAt(0))) {
                    sb.setCharAt(0, str[i].charAt(0));

                    sontext += sb.toString() + " ";
                } else {
                    sontext += kelimeDuzeltici.correct(str[i].substring(0, str[i].length())) + " ";
                }

            }

        }
        return sontext;
    }

    public static String[] remove(String[] str) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            if (!str[i].isEmpty()) {

                list.add(str[i]);

            }
        }

        return list.toArray(new String[list.size()]);
    }

}
