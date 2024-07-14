package Dao;

import Model.Animal;
import Model.Area;
import Model.Habitat;
import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    private AreaDAO areaDAO = new AreaDAO();
    private HabitatDAO habitatDAO = new HabitatDAO();

    public Animal getAnimalById(int id) {
        Animal animal = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Animal WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Area area = areaDAO.getAreaById(rs.getInt("area_id"));
                Habitat habitat = habitatDAO.getHabitatById(rs.getInt("habitat_id"));
                animal = new Animal(rs.getInt("id"), rs.getString("nombre"), rs.getString("especie"), rs.getDate("fechaNacimiento"),
                                    area, habitat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }

    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Animal")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Area area = areaDAO.getAreaById(rs.getInt("area_id"));
                Habitat habitat = habitatDAO.getHabitatById(rs.getInt("habitat_id"));
                animals.add(new Animal(rs.getInt("id"), rs.getString("nombre"), rs.getString("especie"), rs.getDate("fechaNacimiento"),
                                       area, habitat));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public boolean insertAnimal(Animal animal) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Animal (nombre, especie, fechaNacimiento, area_id, habitat_id) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, animal.getNombre());
            stmt.setString(2, animal.getEspecie());
            stmt.setDate(3, new java.sql.Date(animal.getFechaNacimiento().getTime()));
            stmt.setInt(4, animal.getArea().getId());
            stmt.setInt(5, animal.getHabitat().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAnimal(Animal animal) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE Animal SET nombre = ?, especie = ?, fechaNacimiento = ?, area_id = ?, habitat_id = ? WHERE id = ?")) {
            stmt.setString(1, animal.getNombre());
            stmt.setString(2, animal.getEspecie());
            stmt.setDate(3, new java.sql.Date(animal.getFechaNacimiento().getTime()));
            stmt.setInt(4, animal.getArea().getId());
            stmt.setInt(5, animal.getHabitat().getId());
            stmt.setInt(6, animal.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAnimal(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM Animal WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
