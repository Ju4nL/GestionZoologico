package Controller;

import Dao.AnimalDAO;
import Model.Animal;
import View.AnimalesFrame;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AnimalesController {

    private PrincipalController controller;
    private AnimalesFrame frame;
    private AnimalDAO modelDao;

    public AnimalesController(PrincipalController controller) {
        this.controller = controller;
        this.frame = new AnimalesFrame();
        this.modelDao = new AnimalDAO();
        initController();
    }

    private void initController() {
        frame.getBtnNuevo().addActionListener(e -> nuevo());
        frame.getBtnBuscar().addActionListener(e -> buscar());
        frame.getBtnEditar().addActionListener(e -> editar());
        frame.getBtnEditar().addActionListener(e -> eliminar());
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
            List<Animal> animales = modelDao.getAllAnimals();
            DefaultTableModel model = (DefaultTableModel) frame.getTblAnimales().getModel();
            model.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Especie", "Fecha Nacimiento", "Área", "Hábitat"});

            TableColumnModel columnModel = frame.getTblAnimales().getColumnModel();
            columnModel.getColumn(0).setMaxWidth(100);

            model.setRowCount(0);
            for (Animal animal : animales) {
                model.addRow(new Object[]{
                    animal.getId(),
                    animal.getNombre(),
                    animal.getEspecie(),
                    animal.getFechaNacimiento(),
                    animal.getArea(),
                    animal.getHabitat()
                });
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al cargar animales: " + e.getMessage());
        }
    }

    private void nuevo() {

    }

    private void buscar() {

    }

    private void editar() {

    }

    private void eliminar() {

    }

}
