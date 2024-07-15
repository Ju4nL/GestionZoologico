package Controller;

import Dao.AnimalDAO;
import Dao.AreaDAO;
import Dao.CategoriaDAO;
import Dao.HabitatDAO;
import Model.Animal;
import Model.Area;
import Model.Categoria;
import Model.Habitat;
import View.AlimentosFrameForm;
import View.AnimalesFrame;
import View.AnimalesFrameForm;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
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

    private void nuevo(AnimalesFrameForm formCreate) {
        try {
            String nombre = formCreate.getTxtNombre().getText();
            String especie = formCreate.getTxtEspecie().getText();
            Area area = (Area) formCreate.getCbxArea().getSelectedItem();
            Habitat habitat = (Habitat) formCreate.getCbxHabitat().getSelectedItem();
            java.util.Date fechaNacimiento = formCreate.getJdcFechaNac().getDate();

            Animal nuevoAnimal = new Animal(0, nombre, especie, fechaNacimiento, area, habitat);
            if (modelDao.insertAnimal(nuevoAnimal)) {
                frame.displaySucessMessage("Animal creado con éxito");
                formCreate.setVisible(false);
                frame.setVisible(true);
                listar();
            } else {
                frame.displayErrorMessage("Error al crear animal");
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al crear animal: " + e.getMessage());
        }
    }

    private void buscar() {
        try {
            String keyword = frame.getTxtBuscar().getText();
            List<Animal> animales = modelDao.searchAnimals(keyword);
            DefaultTableModel model = (DefaultTableModel) frame.getTblAnimales().getModel();
            model.setRowCount(0);
            for (Animal animal : animales) {
                model.addRow(new Object[]{
                    animal.getId(),
                    animal.getNombre(),
                    animal.getEspecie(),
                    animal.getFechaNacimiento(),
                    animal.getArea().getNombre(),
                    animal.getHabitat().getNombre()
                });
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al buscar animales: " + e.getMessage());
        }
    }

    private void editar(AnimalesFrameForm form, int id) {
        try {
            String nombre = form.getTxtNombre().getText();
            String especie = form.getTxtEspecie().getText();
            Area area = (Area) form.getCbxArea().getSelectedItem();
            Habitat habitat = (Habitat) form.getCbxHabitat().getSelectedItem();
            java.util.Date fechaNacimiento = form.getJdcFechaNac().getDate();

            Animal animal = new Animal(id, nombre, especie, fechaNacimiento, area, habitat);
            if (modelDao.updateAnimal(animal)) {
                frame.displaySucessMessage("Animal actualizado con éxito");
                form.setVisible(false);
                frame.setVisible(true);
                listar();
            } else {
                frame.displayErrorMessage("Error al actualizar animal");
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Error al actualizar animal: " + e.getMessage());
        }
    }

    private void eliminar() {
        try {
            for (int row : this.frame.getTblAnimales().getSelectedRows()) {
                int id = (int) this.frame.getTblAnimales().getValueAt(row, 0);
                if (modelDao.deleteAnimal(id)) {
                    frame.displayErrorMessage("Se eliminó el animal con ID " + id);
                } else {
                    frame.displayErrorMessage("Falló al eliminar el animal con ID " + id);
                }
            }
        } catch (Exception e) {
            frame.displayErrorMessage("Ocurrió un error al eliminar animales");
        } finally {
            listar();
        }
    }

    private void visibleFormCrear() {
        AnimalesFrameForm formCreate = new AnimalesFrameForm();
        fillComboBoxes(formCreate);
        formCreate.setVisible(true);
        frame.setVisible(false);
        formCreate.getBtnGuardar().addActionListener(e -> nuevo(formCreate));
        formCreate.getBtnRetroceder().addActionListener(e -> {
            formCreate.setVisible(false);
            frame.setVisible(true);
        });
    }

    private void fillComboBoxes(AnimalesFrameForm form) {

        var areaDao = new AreaDAO();
        List<Area> areas = areaDao.getAllAreas();
        form.getCbxArea().removeAllItems();
        for (Area area : areas) {
            form.getCbxArea().addItem(area);
        }

        var habitatDao = new HabitatDAO();
        List<Habitat> habitats = habitatDao.getAllHabitats();
        form.getCbxHabitat().removeAllItems();
        for (Habitat habitat : habitats) {
            form.getCbxHabitat().addItem(habitat);
        }
    }

    private void visibleFormActualizar() {
        int rowIndex = this.frame.getTblAnimales().getSelectedRow();
        if (rowIndex == -1) {
            frame.displayErrorMessage("Seleccione un registro");
            return;
        }

        try {
            int id = Integer.parseInt(frame.getTblAnimales().getValueAt(rowIndex, 0).toString());
            Animal animal = modelDao.getAnimalById(id);

            if (animal == null) {
                frame.displayErrorMessage("No se pudo encontrar el animal seleccionado.");
                return;
            }

            AnimalesFrameForm formUpdate = new AnimalesFrameForm();
            fillComboBoxes(formUpdate);

            formUpdate.getTxtNombre().setText(animal.getNombre());
            formUpdate.getTxtEspecie().setText(animal.getEspecie());
            formUpdate.getCbxArea().setSelectedItem(animal.getArea());
            formUpdate.getCbxHabitat().setSelectedItem(animal.getHabitat());
            formUpdate.getJdcFechaNac().setDate(animal.getFechaNacimiento());

            formUpdate.setVisible(true);
            frame.setVisible(false);

            formUpdate.getBtnGuardar().addActionListener(e -> editar(formUpdate, id));
            formUpdate.getBtnRetroceder().addActionListener(e -> {
                formUpdate.setVisible(false);
                frame.setVisible(true);
            });
        } catch (Exception e) {
            frame.displayErrorMessage("Error al actualizar el animal: " + e.getMessage());
        }
    }

}
