package Controller;

import Dao.AlimentoDAO;
import Model.Alimento;
import View.AlimentosFrame;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

 
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
    public void listar(){
         try {
            List<Alimento> alimentos = modelDao.getAllAlimentos(); 
            DefaultTableModel model = (DefaultTableModel) frame.getTblAlimentos().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Categoria"});
            TableColumnModel columnModel = frame.getTblAlimentos().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Alimento alimento : alimentos) {
                model.addRow(new Object[]{alimento.getId(),alimento.getNombre(),alimento.getCategoria().getNombre()});
            }
        } catch (Exception e) {
            //frame.displayErrorMessage("Error al cargar vacantes: " + e.getMessage());
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
