package Controller;

import Dao.ProveedorDAO;
import Model.Proveedor;
import View.ProveedoresFrame;
import View.ProveedoresFrameForm;
import View.ProveedoresFrameForm;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ProveedoresController {

    private PrincipalController controller;
    private ProveedoresFrame frame;
    private ProveedorDAO modelDao;

    public ProveedoresController(PrincipalController controller) {
        this.controller = controller;
        this.frame = new ProveedoresFrame();
        this.modelDao = new ProveedorDAO();
        initController();
    }

    private void initController() {
        frame.getBtnNuevo().addActionListener(e -> visibleFormCrear());
        frame.getBtnBuscar().addActionListener(e -> buscar());
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
            List<Proveedor> proveedores = modelDao.getAllProveedores();
            DefaultTableModel model = (DefaultTableModel) frame.getTblProveedores().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Contacto"});
            TableColumnModel columnModel = frame.getTblProveedores().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Proveedor proveedor : proveedores) {
                model.addRow(new Object[]{proveedor.getId(), proveedor.getNombre(), proveedor.getContacto()});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar proveedor: " + e.getMessage());
        }

    }

    private void nuevo(ProveedoresFrameForm form) {
        String nombre = (String) form.getTxtNombre().getText();
        String contacto = (String) form.getTxtContacto().getText();

        Proveedor proveedor = new Proveedor(0, nombre, contacto);
        modelDao.insertProveedor(proveedor);
        frame.displaySucessMessage("Proveedor añadido con éxito");
        form.setVisible(false);
        frame.setVisible(true);
        listar();
    }

    private void buscar() {
        try {
            String name = (String) frame.getTxtBuscar().getText();
            List<Proveedor> proveedores = modelDao.getProveedorByName(name);
            DefaultTableModel model = (DefaultTableModel) frame.getTblProveedores().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Nombre"});
            TableColumnModel columnModel = frame.getTblProveedores().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Proveedor proveedor : proveedores) {
                model.addRow(new Object[]{proveedor.getId(), proveedor.getNombre()});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar proveedor: " + e.getMessage());
        }
    }

    private void editar(ProveedoresFrameForm form, int id) {
        String nombre = (String) form.getTxtNombre().getText();
        String contacto = (String) form.getTxtContacto().getText();
        
        Proveedor proveedor = new Proveedor(0, nombre, contacto);
        modelDao.updateProveedor(proveedor);
        frame.displaySucessMessage("Proveedor atualizado con éxito");
        form.setVisible(false);
        frame.setVisible(true);
        listar();
    }

    private void eliminar() {
        try {
            for (int row : this.frame.getTblProveedores().getSelectedRows()) {
                int id = (int) this.frame.getTblProveedores().getValueAt(row, 0);
                if (modelDao.deleteProveedor(id)) {
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
        ProveedoresFrameForm formCreate = new ProveedoresFrameForm();
        formCreate.setVisible(true);
        frame.setVisible(false);
        formCreate.getBtnGuardar().addActionListener(e -> nuevo(formCreate));
        formCreate.getBtnRetroceder().addActionListener(e -> {
            formCreate.setVisible(false);
            frame.setVisible(true);
        });
    }

    private void visibleFormActualizar() {
        int rowIndex = this.frame.getTblProveedores().getSelectedRow();
        if (rowIndex == -1) {
            frame.displayErrorMessage("Selectciones un registro");
            return;
        }

        try {
            int id = Integer.parseInt(frame.getTblProveedores().getValueAt(rowIndex, 0).toString());
            Proveedor proveedor = modelDao.getProveedorById(id);

            if (proveedor == null) {
                frame.displayErrorMessage("No se pudo encontrar la proveedor seleccionada.");
                return;
            }

            ProveedoresFrameForm formUpdate = new ProveedoresFrameForm();

            formUpdate.getTxtNombre().setText(proveedor.getNombre());
            formUpdate.getTxtContacto().setText(proveedor.getContacto());
            formUpdate.setVisible(true);
            frame.setVisible(false);
            formUpdate.getBtnGuardar().addActionListener(e -> editar(formUpdate, id));
            formUpdate.getBtnRetroceder().addActionListener(e -> {
                formUpdate.setVisible(false);
                frame.setVisible(true);
            });
        } catch (Exception e) {
            frame.displayErrorMessage("Error al actualizar el Proveedor: " + e.getMessage());
        }
    }

}
