package Controller;

import Dao.AlimentoDAO;
import View.AlimentosFrame;

 
public class AlimentosController {
    private PrincipalController controller;
    private AlimentosFrame frame;
    private AlimentoDAO modeldao;

    public AlimentosController(PrincipalController controller) {
        this.controller = controller;
        this.frame= new AlimentosFrame();
        this.modeldao = new AlimentoDAO();
        initController();
    }
    
    private void initController(){
        frame.getBtnNuevo().addActionListener(e -> nuevo()); 
        frame.getBtnBuscar().addActionListener(e -> buscar()); 
        frame.getBtnEditar().addActionListener(e -> editar()); 
        frame.getBtnEditar().addActionListener(e -> eliminar()); 
        listar();
    }
    private void listar(){
        
    }
    private void nuevo(){
        
    }
    
    private void buscar(){
        
    }
    
    private void editar(){
        
    }
    
    private void eliminar(){
        
    }
    
    
}
