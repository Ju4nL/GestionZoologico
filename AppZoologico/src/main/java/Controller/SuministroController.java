package Controller;

import Dao.AlimentoDAO;
import Dao.ProveedorDAO;
import Dao.SuministroDAO;
import Model.Alimento;
import Model.Proveedor;
import Model.Suministro;
import View.SuministroFrame;
import View.SuministroFrameForm;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class SuministroController {

    private PrincipalController controller;
    private SuministroFrame frame;
    private SuministroDAO modelDao;

    public SuministroController(PrincipalController controller) {
        this.controller = controller;
        this.frame = new SuministroFrame();
        this.modelDao = new SuministroDAO();
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
            List<Suministro> suministroes = modelDao.getAllSuministros();
            DefaultTableModel model = (DefaultTableModel) frame.getTblSuministros().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Alimento", "Stock", "Fecha vencimiento", "Fecha ingreso", "Cantidad", "Proveedor"});

            TableColumnModel columnModel = frame.getTblSuministros().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Suministro suministro : suministroes) {
                model.addRow(new Object[]{
                    suministro.getId(),
                    suministro.getAlimento().getNombre(),
                    suministro.getStock(),
                    suministro.getFechaVencimiento(),
                    suministro.getFechaIngreso(),
                    suministro.getCantidad(),
                    suministro.getProveedor().getNombre(),});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar suministros: " + e.getMessage());
        }
    }

    private void nuevo(SuministroFrameForm formCreate) {
        try {
            Alimento alimento = (Alimento) formCreate.getCbxAlimento().getSelectedItem();
            int stock = Integer.parseInt(formCreate.getTxtStock().getText());;
            int cantidad = Integer.parseInt(formCreate.getTxtCantidad().getText());
            Proveedor proveedor = (Proveedor) formCreate.getCbxProveedor().getSelectedItem();
            java.util.Date fechaVencimiento = formCreate.getJdcFechaVencimiento().getDate();
            java.util.Date fechaIngreso = formCreate.getJdcFechaIngreso().getDate();

            Suministro nuevoSuministro = new Suministro(0, alimento, stock, fechaVencimiento, fechaIngreso, cantidad, proveedor);
            if (modelDao.insertSuministro(nuevoSuministro)) {
                frame.displaySucessMessage("Suministro creado con éxito");
                formCreate.setVisible(false);
                frame.setVisible(true);
                listar();
            } else {
                frame.displayErrorMessage("Error al crear suministro");
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al crear suministro: " + e.getMessage());
        }
    }

    private void buscar() {
        try {
            String keyword = frame.getTxtBuscar().getText();
            List<Suministro> suministros = modelDao.searchSuministros(keyword);
            DefaultTableModel model = (DefaultTableModel) frame.getTblSuministros().getModel();
            model.setRowCount(0);
            for (Suministro suministro : suministros) {
                model.addRow(new Object[]{
                    suministro.getId(),
                    suministro.getAlimento().getNombre(),
                    suministro.getStock(),
                    suministro.getFechaVencimiento(),
                    suministro.getFechaIngreso(),
                    suministro.getCantidad(),
                    suministro.getProveedor().getNombre(),});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al buscar suministros: " + e.getMessage());
        }
    }

    private void editar(SuministroFrameForm form, int id) {
        try {
            Alimento alimento = (Alimento) form.getCbxAlimento().getSelectedItem();
            int stock = Integer.parseInt(form.getTxtStock().getText());
            int cantidad = Integer.parseInt(form.getTxtCantidad().getText());
            Proveedor proveedor = (Proveedor) form.getCbxProveedor().getSelectedItem();
            java.util.Date fechaVencimiento = form.getJdcFechaVencimiento().getDate();
            java.util.Date fechaIngreso = form.getJdcFechaIngreso().getDate();

            Suministro suministro = new Suministro(id, alimento, stock, fechaVencimiento, fechaIngreso, cantidad, proveedor);
            if (modelDao.updateSuministro(suministro)) {
                frame.displaySucessMessage("Suministro actualizado con éxito");
                form.setVisible(false);
                frame.setVisible(true);
                listar();
            } else {
                frame.displayErrorMessage("Error al actualizar suministro");
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al actualizar suministro: " + e.getMessage());
        }
    }

    private void eliminar() {
        try {
            for (int row : this.frame.getTblSuministros().getSelectedRows()) {
                int id = (int) this.frame.getTblSuministros().getValueAt(row, 0);
                if (modelDao.deleteSuministro(id)) {
                    frame.displaySucessMessage("Se eliminó el suministro con ID " + id);
                } else {
                    frame.displayErrorMessage("Falló al eliminar el suministro con ID " + id);
                }
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Ocurrió un error al eliminar suministros");
        } finally {
            listar();
        }
    }

    private void visibleFormCrear() {
        SuministroFrameForm formCreate = new SuministroFrameForm();
        fillComboBoxes(formCreate);
        formCreate.setVisible(true);
        frame.setVisible(false);
        formCreate.getBtnGuardar().addActionListener(e -> nuevo(formCreate));
        formCreate.getBtnRetroceder().addActionListener(e -> {
            formCreate.setVisible(false);
            frame.setVisible(true);
        });
    }

    private void fillComboBoxes(SuministroFrameForm form) {
        var alimentoDao = new AlimentoDAO();
        List<Alimento> alimentos = alimentoDao.getAllAlimentos();
        form.getCbxAlimento().removeAllItems();
        for (Alimento alimento : alimentos) {
            form.getCbxAlimento().addItem(alimento);
        }

        var proveedorDao = new ProveedorDAO();
        List<Proveedor> proveedores = proveedorDao.getAllProveedores();
        form.getCbxProveedor().removeAllItems();
        for (Proveedor proveedor : proveedores) {
            form.getCbxProveedor().addItem(proveedor);
        }
    }

    private void visibleFormActualizar() {
        int rowIndex = this.frame.getTblSuministros().getSelectedRow();
        if (rowIndex == -1) {
            frame.displayErrorMessage("Seleccione un registro");
            return;
        }

        try {
            int id = Integer.parseInt(frame.getTblSuministros().getValueAt(rowIndex, 0).toString());
            Suministro suministro = modelDao.getSuministroById(id);

            if (suministro == null) {
                frame.displayErrorMessage("No se pudo encontrar el suministro seleccionado.");
                return;
            }

            SuministroFrameForm formUpdate = new SuministroFrameForm();
            fillComboBoxes(formUpdate);

            formUpdate.getCbxAlimento().setSelectedItem(suministro.getAlimento());
            formUpdate.getTxtStock().setText(String.valueOf(suministro.getStock()));
            formUpdate.getTxtCantidad().setText(String.valueOf(suministro.getCantidad()));
            formUpdate.getCbxProveedor().setSelectedItem(suministro.getProveedor());
            formUpdate.getJdcFechaVencimiento().setDate(suministro.getFechaVencimiento());
            formUpdate.getJdcFechaIngreso().setDate(suministro.getFechaIngreso());

            formUpdate.setVisible(true);
            frame.setVisible(false);

            formUpdate.getBtnGuardar().addActionListener(e -> editar(formUpdate, id));
            formUpdate.getBtnRetroceder().addActionListener(e -> {
                formUpdate.setVisible(false);
                frame.setVisible(true);
            });
        } catch (Exception e) {
            frame.displayErrorMessage("Error al actualizar el suministro: " + e.getMessage());
        }
    }

}
