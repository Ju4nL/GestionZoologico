package Dao;

import Model.DatabaseConnection;
import Model.Area;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {

    public List<Area> getAllAreas() {
        List<Area> areas = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Area")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Area area = new Area(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
                areas.add(area);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areas;
    }

    public Area getAreaById(int id) {
        Area area = null;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Area WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                area = new Area(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return area;
    }

    public boolean insertArea(Area area) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("INSERT INTO Area (nombre, descripcion) VALUES (?, ?)")) {
            stmt.setString(1, area.getNombre());
            stmt.setString(2, area.getDescripcion());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateArea(Area area) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("UPDATE Area SET nombre = ?, descripcion = ? WHERE id = ?")) {
            stmt.setString(1, area.getNombre());
            stmt.setString(2, area.getDescripcion());
            stmt.setInt(3, area.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteArea(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("DELETE FROM Area WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
