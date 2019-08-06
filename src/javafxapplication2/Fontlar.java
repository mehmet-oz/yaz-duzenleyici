
package javafxapplication2;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Fontlar {
     public static int textSize = 18;
    public static FontWeight fw = FontWeight.NORMAL;
  public static  FontPosture fp = FontPosture.REGULAR;
     public static Font getFontNormal(){
         return Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18);
     }
      public static Font getFontBold(){
         return Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18);
     }
      public static Font getFontItalic(){
         return Font.font("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 18);
     }
       public static Font getFontTA(){
         return Font.font(PaneClass.ta.getFont().getFamily(), fw, fp, textSize);
     }
}
