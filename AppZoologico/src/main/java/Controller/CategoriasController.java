package Controller; 


import Dao.CategoriaDAO; 
import Model.Categoria;
import View.CategoriasFrame;
import View.CategoriasFrameForm;
import View.CategoriasFrameForm;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CategoriasController {

    private PrincipalController controller;
    private CategoriasFrame frame;
    private CategoriaDAO modelDao;

    public CategoriasController(PrincipalController controller) {
        this.controller = controller;
        this.frame = new CategoriasFrame();
        this.modelDao = new CategoriaDAO();
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
            List<Categoria> categorias = modelDao.getAllCategorias();
            DefaultTableModel model = (DefaultTableModel) frame.getTblCategorias().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Nombre"});
            TableColumnModel columnModel = frame.getTblCategorias().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Categoria categoria : categorias) {
                model.addRow(new Object[]{categoria.getId(), categoria.getNombre()});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar categoria: " + e.getMessage());
        }

    }

    private void nuevo(CategoriasFrameForm form) {
        String nombre = (String) form.getTxtNombre().getText(); 

        Categoria categoria = new Categoria(0, nombre, "");
        modelDao.insertCategoria(categoria);
        frame.displaySucessMessage("Categoria añadido con éxito");
        form.setVisible(false);
        frame.setVisible(true);
        listar();
    }

    private void buscar() {
        try {
            String name= (String) frame.getTxtBuscar().getText();
            List<Categoria> categorias = modelDao.getCategoriaByName(name);
            DefaultTableModel model = (DefaultTableModel) frame.getTblCategorias().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Nombre"});
            TableColumnModel columnModel = frame.getTblCategorias().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Categoria categoria : categorias) {
                model.addRow(new Object[]{categoria.getId(), categoria.getNombre()});
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar categoria: " + e.getMessage());
        }
    }

    private void editar(CategoriasFrameForm form, int id) {
        String nombre = (String) form.getTxtNombre().getText(); 

        Categoria categoria = new Categoria(id, nombre, "");
        modelDao.updateCategoria(categoria);
        frame.displaySucessMessage("Categoria atualizado con éxito");
        form.setVisible(false);
        frame.setVisible(true);
        listar();
    }

    private void eliminar() {
        try {
            for (int row : this.frame.getTblCategorias().getSelectedRows()) {
                int id = (int) this.frame.getTblCategorias().getValueAt(row, 0);
                if (modelDao.deleteCategoria(id)) {
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
        CategoriasFrameForm formCreate = new CategoriasFrameForm();
        formCreate.setVisible(true);
        frame.setVisible(false); 
        formCreate.getBtnGuardar().addActionListener(e -> nuevo(formCreate));
        formCreate.getBtnRetroceder().addActionListener(e -> {
            formCreate.setVisible(false);
            frame.setVisible(true);
        });
    }

    private void visibleFormActualizar() {
        int rowIndex = this.frame.getTblCategorias().getSelectedRow();
        if (rowIndex == -1) {
            frame.displayErrorMessage("Selectciones un registro");
            return;
        }

        try {
            int id = Integer.parseInt(frame.getTblCategorias().getValueAt(rowIndex, 0).toString());
            Categoria categoria = modelDao.getCategoriaById(id);

            if (categoria == null) {
                frame.displayErrorMessage("No se pudo encontrar la vacante seleccionada.");
                return;
            }

            CategoriasFrameForm formUpdate = new CategoriasFrameForm(); 
 
            formUpdate.getTxtNombre().setText(categoria.getNombre());
            formUpdate.setVisible(true);
            frame.setVisible(false);
            formUpdate.getBtnGuardar().addActionListener(e -> editar(formUpdate, id));
            formUpdate.getBtnRetroceder().addActionListener(e -> {
                formUpdate.setVisible(false);
                frame.setVisible(true);
            });
        } catch (Exception e) {
            frame.displayErrorMessage("Error al actualizar el Categoria: " + e.getMessage());
        }
    }

      

}
