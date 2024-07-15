 
package Controller;

import View.PrincipalFrame;

 
public class PrincipalController {
    private PrincipalFrame principalFrame;
    private AlimentosController alimentosController;
    private AnimalesController animalesController;
    
    
    
    public PrincipalController(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        initControllers();
    }
    
    private void initControllers() {
        alimentosController = new AlimentosController(this);
        principalFrame.getBtnAlimentos().addActionListener(e -> {
            principalFrame.setVisible(false); 
            alimentosController.visible();
        });
        
        animalesController = new AnimalesController(this);
        principalFrame.getBtnAnimales().addActionListener(e -> {
            principalFrame.setVisible(false);
            animalesController.visible();
        });
    }
    
    public void showPrincipalFrame() {
        principalFrame.setVisible(true);  
    }
}
