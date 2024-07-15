package Base;

import Controller.LoginController;
import Controller.PrincipalController;
import View.LoginFrame;
import View.PrincipalFrame;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        UIManager.put("Button.arc", 25);
        LoginFrame loginFrame = new LoginFrame();
        PrincipalFrame principalFrame = new PrincipalFrame();
        
        LoginController loginController = new LoginController(loginFrame, principalFrame);

        loginFrame.setVisible(true);
    }
}
