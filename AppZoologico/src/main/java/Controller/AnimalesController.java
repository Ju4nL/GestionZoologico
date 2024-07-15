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

    private void nuevo(AnimalesFrameForm formCreate) {
        String nombre = formCreate.getTxtNombre().getText();
        String especie = formCreate.getTxtEspecie().getText();
        Area area = (Area) formCreate.getCbxArea().getSelectedItem();
        Habitat habitalModel = (Habitat) formCreate.getCbxHabitat().getSelectedItem();

    }

    private void buscar() {
        
    }

    private void editar(AnimalesFrameForm) {
        
    }

    private void eliminar() {

    }

    private void visibleFormCrear() {
        AnimalesFrameForm formCreate = new AnimalesFrameForm();
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
}
