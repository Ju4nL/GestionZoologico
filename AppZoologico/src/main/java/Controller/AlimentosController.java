package Controller;

import Dao.AlimentoDAO;
import Dao.CategoriaDAO;
import Model.Alimento;
import Model.Categoria;
import View.AlimentosFrame;
import View.AlimentosFrameForm;
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
        frame.getBtnNuevo().addActionListener(e -> visibleFormCrear()); 
        frame.getBtnBuscar().addActionListener(e -> buscar()); 
        frame.getBtnEditar().addActionListener(e -> editar()); 
        frame.getBtnEliminar().addActionListener(e -> eliminar()); 
        frame.getBtnRetroceder().addActionListener(e -> retroceder());
        listar();
    }
    
    public void visible(){
        frame.setVisible(true);        
    }
    
    private void retroceder() {
        frame.setVisible(false);  
        controller.showPrincipalFrame();  
    }
     
    private void listar(){
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
            frame.displayErrorMessage("Error al cargar vacantes: " + e.getMessage());
        }
        
        
    }
    private void nuevo(AlimentosFrameForm formCreate){
        
    }
    
    private void buscar(){
        
    }
    
    private void editar(){
        
    }
    
    private void eliminar(){
        try {
            for (int row : this.frame.getTblAlimentos().getSelectedRows()) {
                int id = (int) this.frame.getTblAlimentos().getValueAt(row, 0);
                if (modelDao.deleteAlimento(id)){ 
                    frame.displaySucessMessage("Se elimino el id "+id);
                }else{
                    frame.displayErrorMessage("Falló al eliminar el id "+ id);
                }
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Ocurrio un error");
        } finally{
            listar();
        }
    }
    
    
     private void visibleFormCrear() {
        AlimentosFrameForm formCreate = new AlimentosFrameForm();
        formCreate.setVisible(true); 
        frame.setVisible(false );
        fillComboBoxes(formCreate);
        formCreate.getBtnGuardar().addActionListener(e -> nuevo(formCreate));
        formCreate.getBtnRetroceder().addActionListener(e -> {
                formCreate.setVisible(false);
                frame.setVisible(true);});
    }
     
     private void fillComboBoxes(AlimentosFrameForm form) {
         
        CategoriaDAO categoriaDao= new CategoriaDAO();
        List<Categoria> categorias = categoriaDao.getAllCategorias(); 

        form.getCbxCategoria().removeAllItems(); 

        for (Categoria categoria : categorias) {
            form.getCbxCategoria().addItem(categoria);
        }
 
    }
    
 
}
