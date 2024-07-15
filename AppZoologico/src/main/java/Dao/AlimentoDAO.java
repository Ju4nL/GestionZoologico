package Dao;

import Model.Alimento;
import Model.Categoria;
import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO {

     public Alimento getAlimentoById(int id) {
        Alimento alimento = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                "SELECT a.id, a.nombre, c.id AS categoria_id, c.nombre AS categoria_nombre, c.contacto AS categoria_contacto " +
                "FROM Alimento a " +
                "JOIN Categoria c ON a.categoria_id = c.id " +
                "WHERE a.id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_nombre"), rs.getString("categoria_contacto"));
                alimento = new Alimento(rs.getInt("id"), rs.getString("nombre"), categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alimento;
    }

    public List<Alimento> getAllAlimentos() {
        List<Alimento> alimentos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                "SELECT a.id, a.nombre, c.id AS categoria_id, c.nombre AS categoria_nombre, c.contacto AS categoria_contacto " +
                "FROM Alimento a " +
                "JOIN Categoria c ON a.categoria_id = c.id")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_nombre"), rs.getString("categoria_contacto"));
                alimentos.add(new Alimento(rs.getInt("id"), rs.getString("nombre"), categoria));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alimentos;
    }


    public boolean insertAlimento(Alimento alimento) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Alimento (nombre, categoria_id) VALUES (?, ?)")) {
            stmt.setString(1, alimento.getNombre());
            stmt.setInt(2, alimento.getCategoria().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAlimento(Alimento alimento) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE Alimento SET nombre = ?, categoria_id = ? WHERE id = ?")) {
            stmt.setString(1, alimento.getNombre());
            stmt.setInt(2, alimento.getCategoria().getId());
            stmt.setInt(3, alimento.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAlimento(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM Alimento WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Alimento> getAlimentoByName(String name) {
        List<Alimento> alimentos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                "SELECT a.id, a.nombre, c.id AS categoria_id, c.nombre AS categoria_nombre, c.contacto AS categoria_contacto " +
                "FROM Alimento a " +
                "JOIN Categoria c ON a.categoria_id = c.id " +
                "WHERE a.nombre LIKE ?")) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_nombre"), rs.getString("categoria_contacto"));
                alimentos.add(new Alimento(rs.getInt("id"), rs.getString("nombre"), categoria));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alimentos;
    }
}
