 
package Base;

import Controller.PrincipalController;
import View.PrincipalFrame;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;

 
public class test {
     public static void main(String[] args) {
        FlatMacLightLaf.setup();
        UIManager.put("Button.arc", 25);

        PrincipalFrame frame = new PrincipalFrame(); 
        //PrincipalController controller = new PrincipalController( frame);  
        frame.setVisible(true);

    }
}
