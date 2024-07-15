package Dao;

import Model.Inventario;
import Model.Area;
import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    private AreaDAO areaDAO = new AreaDAO();

    public Inventario getInventarioById(int id) {
        Inventario inventario = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Inventario WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Area area = areaDAO.getAreaById(rs.getInt("area_id"));
                inventario = new Inventario(rs.getInt("id"), rs.getString("nombre"), area);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventario;
    }

    public List<Inventario> getAllInventarios() {
        List<Inventario> inventarios = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Inventario")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Area area = areaDAO.getAreaById(rs.getInt("area_id"));
                inventarios.add(new Inventario(rs.getInt("id"), rs.getString("nombre"), area));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    public boolean insertInventario(Inventario inventario) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Inventario (nombre, area_id) VALUES (?, ?)")) {
            stmt.setString(1, inventario.getNombre());
            stmt.setInt(2, inventario.getArea().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInventario(Inventario inventario) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE Inventario SET nombre = ?, area_id = ? WHERE id = ?")) {
            stmt.setString(1, inventario.getNombre());
            stmt.setInt(2, inventario.getArea().getId());
            stmt.setInt(3, inventario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteInventario(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM Inventario WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
