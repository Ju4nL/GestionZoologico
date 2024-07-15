package Controller;

import Dao.AlimentoDAO;
import Model.Alimento;
import View.AlimentosFrame;
import java.util.List;

 
public class AlimentosController {
    private PrincipalController controller;
    private AlimentosFrame frame;
    private AlimentoDAO modelDao;

    public AlimentosController(PrincipalController controller) {
        this.controller = controller;
        this.frame= new AlimentosFrame();
        this.modelDao = new AlimentoDAO();
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
         try {
            List<Alimento> alimentos = modelDao.getAllAlimentos();

            
        } catch (Exception e) {
            
        }
        
        
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
