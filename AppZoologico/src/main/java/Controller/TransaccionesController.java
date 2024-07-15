package Controller;

import Dao.TransaccionesDAO;
import Dao.CategoriaDAO;
import Model.Transacciones;
import Model.Categoria;
import View.TransaccionesFrame; 
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class TransaccionesController {

    private PrincipalController controller;
    private TransaccionesFrame frame;
    private TransaccionesDAO modelDao;

    public TransaccionesController(PrincipalController controller) {
        this.controller = controller;
        this.frame = new TransaccionesFrame();
        this.modelDao = new TransaccionesDAO();
        initController();
    }

    private void initController() { 
        frame.getBtnBuscar().addActionListener(e -> buscar()); 
        frame.getBtnRetroceder().addActionListener(e -> retroceder());
        listar();
    }

    public void visible() {
        frame.setVisible(true);
    }

    private void retroceder() {
        frame.setVisible(false);
        controller.showPrincipalFrame();
    }

    private void listar() {
        try {
            List<Transacciones> transacciones = modelDao.getAllTransacciones();
            DefaultTableModel model = (DefaultTableModel) frame.getTblTransacciones().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Suministro", "Fecha accion","Cantidad","Accion"});
            TableColumnModel columnModel = frame.getTblTransacciones().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Transacciones transaccion : transacciones) {
                model.addRow(new Object[]{  transaccion.getId(), 
                                            transaccion.getSuministro().getAlimento().getNombre(), 
                                            transaccion.getFechaAccion(),
                                            transaccion.getCantidad(),
                                            transaccion.getAccion()});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar alimentos: " + e.getMessage());
        }

    }

    private void buscar() {
        
        try {
            String name= (String) frame.getTxtBuscar().getText();
            List<Transacciones> transacciones = modelDao.getAllTransaccionesBySuministro(name);
            DefaultTableModel model = (DefaultTableModel) frame.getTblTransacciones().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Suministro", "Fecha accion","Cantidad","Accion"});
            TableColumnModel columnModel = frame.getTblTransacciones().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Transacciones transaccion : transacciones) {
                model.addRow(new Object[]{  transaccion.getId(), 
                                            transaccion.getSuministro().getAlimento().getNombre(), 
                                            transaccion.getFechaAccion(),
                                            transaccion.getCantidad(),
                                            transaccion.getAccion()});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar model.addRow(new Object[]{categoria.getId(), categoria.getNombre()});: " + e.getMessage());
        }
        
        
        
    }

    
 
    

}
