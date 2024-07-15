 
package Controller;

import View.PrincipalFrame;

 
public class PrincipalController {
    private PrincipalFrame principalFrame;
    private AlimentosController alimentosController;
    
    
    public PrincipalController(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        initControllers();
    }
    
    private void initControllers() {
        alimentosController = new AlimentosController(this);
        principalFrame.getBtnAlimentos().addActionListener(e -> alimentosController.listar());
    }
}
