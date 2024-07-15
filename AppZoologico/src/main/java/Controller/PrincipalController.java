 
package Controller;

import View.PrincipalFrame;

 
public class PrincipalController {
    private PrincipalFrame principalFrame;
    private AlimentosController alimentosController;
    private AnimalesController animalesController;
    private CategoriasController categoriaController;
    private InventarioController inventarioController;
    private TransaccionesController transaccionesController;
    private ProveedoresController proveedoresController;
    private SuministroController suministroController;
    
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
        
        categoriaController = new CategoriasController(this);
        principalFrame.getBtnCategorias().addActionListener(e -> {
            principalFrame.setVisible(false); 
            categoriaController.visible();
        });
        
        inventarioController = new InventarioController(this);
        principalFrame.getBtnInventario().addActionListener(e -> {
            principalFrame.setVisible(false); 
            inventarioController.visible();
        });
        
        transaccionesController = new TransaccionesController(this);
        principalFrame.getBtnTransacciones().addActionListener(e -> {
            principalFrame.setVisible(false); 
            transaccionesController.visible();
        });
        
        proveedoresController = new ProveedoresController(this);
        principalFrame.getBtnProveedores().addActionListener(e -> {
            principalFrame.setVisible(false); 
            proveedoresController.visible();
        });
        
        suministroController = new SuministroController(this);
        principalFrame.getBtnSuministros().addActionListener(e -> {
            principalFrame.setVisible(false); 
            suministroController.visible();
        });

    }
    
    public void showPrincipalFrame() {
        principalFrame.setVisible(true);  
    }
}
