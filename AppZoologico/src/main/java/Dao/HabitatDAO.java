package Dao;

import Model.DatabaseConnection;
import Model.Habitat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitatDAO {

    public List<Habitat> getAllHabitats() {
        List<Habitat> habitats = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Habitat")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Habitat habitat = new Habitat(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
                habitats.add(habitat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitats;
    }

    public Habitat getHabitatById(int id) {
        Habitat habitat = null;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Habitat WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                habitat = new Habitat(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitat;
    }

    public boolean insertHabitat(Habitat habitat) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("INSERT INTO Habitat (nombre, descripcion) VALUES (?, ?)")) {
            stmt.setString(1, habitat.getNombre());
            stmt.setString(2, habitat.getDescripcion());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateHabitat(Habitat habitat) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("UPDATE Habitat SET nombre = ?, descripcion = ? WHERE id = ?")) {
            stmt.setString(1, habitat.getNombre());
            stmt.setString(2, habitat.getDescripcion());
            stmt.setInt(3, habitat.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteHabitat(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("DELETE FROM Habitat WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
