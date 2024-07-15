package Controller;

import Dao.AreaDAO;
import Dao.InventarioDAO;
import Dao.CategoriaDAO;
import Model.Area;
import Model.Inventario;
import Model.Categoria;
import View.InventarioFrame;
import View.InventarioFrameForm;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class InventarioController {

    private PrincipalController controller;
    private InventarioFrame frame;
    private InventarioDAO modelDao;

    public InventarioController(PrincipalController controller) {
        this.controller = controller;
        this.frame = new InventarioFrame();
        this.modelDao = new InventarioDAO();
        initController();
    }

    private void initController() {
        frame.getBtnNuevo().addActionListener(e -> visibleFormCrear()); 
        frame.getBtnEditar().addActionListener(e -> visibleFormActualizar());
        frame.getBtnEliminar().addActionListener(e -> eliminar());
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
            List<Inventario> inventarios = modelDao.getAllInventarios();
            DefaultTableModel model = (DefaultTableModel) frame.getTblInventario().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Area"});
            TableColumnModel columnModel = frame.getTblInventario().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Inventario inventario : inventarios) {
                model.addRow(new Object[]{inventario.getId(), inventario.getNombre(), inventario.getArea().getNombre()});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar inventarios: " + e.getMessage());
        }

    }

    private void nuevo(InventarioFrameForm form) {
        String nombre = (String) form.getTxtNombre().getText();
        Area area = (Area) form.getCbxArea().getSelectedItem();

        Inventario inventario = new Inventario(0, nombre, area);
        modelDao.insertInventario(inventario);
        frame.displaySucessMessage("Inventario añadido con éxito");
        form.setVisible(false);
        frame.setVisible(true);
        listar();
    }

    

    private void editar(InventarioFrameForm form, int id) {
        String nombre = (String) form.getTxtNombre().getText();
        Area area = (Area) form.getCbxArea().getSelectedItem();

        Inventario inventario = new Inventario(id, nombre, area);
        modelDao.updateInventario(inventario);
        frame.displaySucessMessage("Inventario atualizado con éxito");
        form.setVisible(false);
        frame.setVisible(true);
        listar();
    }

    private void eliminar() {
        try {
            for (int row : this.frame.getTblInventario().getSelectedRows()) {
                int id = (int) this.frame.getTblInventario().getValueAt(row, 0);
                if (modelDao.deleteInventario(id)) {
                    frame.displaySucessMessage("Se elimino el id " + id);
                } else {
                    frame.displayErrorMessage("Falló al eliminar el id " + id);
                }
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Ocurrio un error");
        } finally {
            listar();
        }
    }

    private void visibleFormCrear() {
        InventarioFrameForm formCreate = new InventarioFrameForm();
        formCreate.setVisible(true);
        frame.setVisible(false);
        fillComboBoxes(formCreate);
        formCreate.getBtnGuardar().addActionListener(e -> nuevo(formCreate));
        formCreate.getBtnRetroceder().addActionListener(e -> {
            formCreate.setVisible(false);
            frame.setVisible(true);
        });
    }

    private void visibleFormActualizar() {
        int rowIndex = this.frame.getTblInventario().getSelectedRow();
        if (rowIndex == -1) {
            frame.displayErrorMessage("Selectciones un registro");
            return;
        }

        try {
            int id = Integer.parseInt(frame.getTblInventario().getValueAt(rowIndex, 0).toString());
            Inventario inventario = modelDao.getInventarioById(id);

            if (inventario == null) {
                frame.displayErrorMessage("No se pudo encontrar la vacante seleccionada.");
                return;
            }

            InventarioFrameForm formUpdate = new InventarioFrameForm();
            fillComboBoxes(formUpdate);

            formUpdate.getCbxArea().setSelectedItem(inventario.getArea());
            formUpdate.getTxtNombre().setText(inventario.getNombre());
            formUpdate.setVisible(true);
            frame.setVisible(false);
            formUpdate.getBtnGuardar().addActionListener(e -> editar(formUpdate, id));
            formUpdate.getBtnRetroceder().addActionListener(e -> {
                formUpdate.setVisible(false);
                frame.setVisible(true);
            });
        } catch (Exception e) {
            frame.displayErrorMessage("Error al actualizar el Inventario: " + e.getMessage());
        }
    }

    private void fillComboBoxes(InventarioFrameForm form) {

        AreaDAO areaDao = new AreaDAO();
        List<Area> areas = areaDao.getAllAreas();

        form.getCbxArea().removeAllItems();

        for (Area area : areas) {
            form.getCbxArea().addItem(area);
        }

    }

}
