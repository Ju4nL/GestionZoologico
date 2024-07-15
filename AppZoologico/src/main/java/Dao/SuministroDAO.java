package Dao;

import Model.Suministro;
import Model.Alimento;
import Model.Categoria;
import Model.Proveedor;
import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuministroDAO {

    public Suministro getSuministroById(int id) {
        Suministro suministro = null;
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(
                "SELECT s.id, s.stock, s.fechaVencimiento, s.fechaIngreso, s.cantidad, s.unidadMedida, "
                + "a.id AS alimento_id, a.nombre AS alimento_nombre, "
                + "p.id AS proveedor_id, p.nombre AS proveedor_nombre, p.contacto AS proveedor_contacto, "
                + "c.id AS categoria_id, c.nombre AS categoria_nombre, c.contacto AS categoria_contacto "
                + "FROM Suministro s "
                + "LEFT JOIN Alimento a ON s.alimento_id = a.id "
                + "LEFT JOIN Proveedor p ON s.proveedor_id = p.id "
                + "LEFT JOIN Categoria c ON a.categoria_id = c.id "
                + "WHERE s.id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_nombre"), rs.getString("categoria_contacto"));
                Alimento alimento = new Alimento(rs.getInt("alimento_id"), rs.getString("alimento_nombre"), categoria);
                Proveedor proveedor = new Proveedor(rs.getInt("proveedor_id"), rs.getString("proveedor_nombre"), rs.getString("proveedor_contacto"));
                suministro = new Suministro(
                    rs.getInt("id"),
                    alimento,
                    rs.getInt("stock"),
                    rs.getDate("fechaVencimiento"),
                    rs.getDate("fechaIngreso"),
                    rs.getInt("cantidad"),
                    rs.getString("unidadMedida"),
                    proveedor
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suministro;
    }

    public List<Suministro> getAllSuministros() {
        List<Suministro> suministros = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(
                "SELECT s.id, s.stock, s.fechaVencimiento, s.fechaIngreso, s.cantidad, s.unidadMedida, "
                + "a.id AS alimento_id, a.nombre AS alimento_nombre, "
                + "p.id AS proveedor_id, p.nombre AS proveedor_nombre, p.contacto AS proveedor_contacto, "
                + "c.id AS categoria_id, c.nombre AS categoria_nombre, c.contacto AS categoria_contacto "
                + "FROM Suministro s "
                + "LEFT JOIN Alimento a ON s.alimento_id = a.id "
                + "LEFT JOIN Proveedor p ON s.proveedor_id = p.id "
                + "LEFT JOIN Categoria c ON a.categoria_id = c.id")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_nombre"), rs.getString("categoria_contacto"));
                Alimento alimento = new Alimento(rs.getInt("alimento_id"), rs.getString("alimento_nombre"), categoria);
                Proveedor proveedor = new Proveedor(rs.getInt("proveedor_id"), rs.getString("proveedor_nombre"), rs.getString("proveedor_contacto"));
                Suministro suministro = new Suministro(
                    rs.getInt("id"),
                    alimento,
                    rs.getInt("stock"),
                    rs.getDate("fechaVencimiento"),
                    rs.getDate("fechaIngreso"),
                    rs.getInt("cantidad"),
                    rs.getString("unidadMedida"),
                    proveedor
                );
                suministros.add(suministro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suministros;
    }

    public boolean insertSuministro(Suministro suministro) {
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Suministro (alimento_id, stock, fechaVencimiento, fechaIngreso, cantidad, unidadMedida, proveedor_id) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setInt(1, suministro.getAlimento().getId());
            stmt.setInt(2, suministro.getStock());
            stmt.setDate(3, new java.sql.Date(suministro.getFechaVencimiento().getTime()));
            stmt.setDate(4, new java.sql.Date(suministro.getFechaIngreso().getTime()));
            stmt.setInt(5, suministro.getCantidad());
            stmt.setString(6, suministro.getUnidadMedida());
            stmt.setInt(7, suministro.getProveedor().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSuministro(Suministro suministro) {
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Suministro SET alimento_id = ?, stock = ?, fechaVencimiento = ?, fechaIngreso = ?, cantidad = ?, unidadMedida = ?, proveedor_id = ? WHERE id = ?")) {
            stmt.setInt(1, suministro.getAlimento().getId());
            stmt.setInt(2, suministro.getStock());
            stmt.setDate(3, new java.sql.Date(suministro.getFechaVencimiento().getTime()));
            stmt.setDate(4, new java.sql.Date(suministro.getFechaIngreso().getTime()));
            stmt.setInt(5, suministro.getCantidad());
            stmt.setString(6, suministro.getUnidadMedida());
            stmt.setInt(7, suministro.getProveedor().getId());
            stmt.setInt(8, suministro.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSuministro(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM Suministro WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Suministro> searchSuministros(String keyword) {
        List<Suministro> suministros = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(
                "SELECT s.id, s.stock, s.fechaVencimiento, s.fechaIngreso, s.cantidad, s.unidadMedida, "
                + "a.id AS alimento_id, a.nombre AS alimento_nombre, "
                + "p.id AS proveedor_id, p.nombre AS proveedor_nombre, p.contacto AS proveedor_contacto, "
                + "c.id AS categoria_id, c.nombre AS categoria_nombre, c.contacto AS categoria_contacto "
                + "FROM Suministro s "
                + "LEFT JOIN Alimento a ON s.alimento_id = a.id "
                + "LEFT JOIN Proveedor p ON s.proveedor_id = p.id "
                + "LEFT JOIN Categoria c ON a.categoria_id = c.id "
                + "WHERE a.nombre LIKE ? OR p.nombre LIKE ?")) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_nombre"), rs.getString("categoria_contacto"));
                Alimento alimento = new Alimento(rs.getInt("alimento_id"), rs.getString("alimento_nombre"), categoria);
                Proveedor proveedor = new Proveedor(rs.getInt("proveedor_id"), rs.getString("proveedor_nombre"), rs.getString("proveedor_contacto"));
                Suministro suministro = new Suministro(
                    rs.getInt("id"),
                    alimento,
                    rs.getInt("stock"),
                    rs.getDate("fechaVencimiento"),
                    rs.getDate("fechaIngreso"),
                    rs.getInt("cantidad"),
                    rs.getString("unidadMedida"),
                    proveedor
                );
                suministros.add(suministro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suministros;
    }
}