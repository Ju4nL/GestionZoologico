package Dao;

import Model.DatabaseConnection;
import Model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> getAllCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Categoria")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("contacto"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public Categoria getCategoriaById(int id) {
        Categoria categoria = null;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Categoria WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                categoria = new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("contacto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    public boolean insertCategoria(Categoria categoria) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("INSERT INTO Categoria (nombre, contacto) VALUES (?, ?)")) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getContacto());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCategoria(Categoria categoria) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("UPDATE Categoria SET nombre = ?, contacto = ? WHERE id = ?")) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getContacto());
            stmt.setInt(3, categoria.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategoria(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("DELETE FROM Categoria WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Categoria> getCategoriaByName(String name) {
        List<Categoria> categorias = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM Categoria WHERE nombre LIKE ?")) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("contacto"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

}
