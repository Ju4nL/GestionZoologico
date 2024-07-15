package Dao;

import Model.Alimento;
import Model.DatabaseConnection;
import Model.Transacciones;
import Model.Suministro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionesDAO {

    public Transacciones getTransaccionById(int id) {
        Transacciones transaccion = null;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(
                "SELECT ht.id AS transaccion_id, ht.fechaAccion, ht.cantidad AS transaccion_cantidad, ht.accion, "
                + "s.id AS suministro_id, a.id AS alimento_id, a.nombre AS alimento_nombre "
                + "FROM HistorialTransacciones ht "
                + "LEFT JOIN Suministro s ON ht.suministro_id = s.id "
                + "LEFT JOIN Alimento a ON s.alimento_id = a.id "
                + "WHERE ht.id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Alimento alimento = new Alimento(rs.getInt("alimento_id"), rs.getString("alimento_nombre"), null); // La categoría se puede obtener si es necesario
                Suministro suministro = new Suministro(rs.getInt("suministro_id"), alimento);
                transaccion = new Transacciones(
                        rs.getInt("transaccion_id"),
                        suministro,
                        rs.getDate("fechaAccion"),
                        rs.getInt("transaccion_cantidad"),
                        rs.getString("accion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaccion;
    }

    public List<Transacciones> getAllTransacciones() {
        List<Transacciones> transacciones = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(
                "SELECT ht.id AS transaccion_id, ht.fechaAccion, ht.cantidad AS transaccion_cantidad, ht.accion, "
                + "s.id AS suministro_id, a.id AS alimento_id, a.nombre AS alimento_nombre "
                + "FROM HistorialTransacciones ht "
                + "LEFT JOIN Suministro s ON ht.suministro_id = s.id "
                + "LEFT JOIN Alimento a ON s.alimento_id = a.id")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Alimento alimento = new Alimento(rs.getInt("alimento_id"), rs.getString("alimento_nombre"), null); // La categoría se puede obtener si es necesario
                Suministro suministro = new Suministro(rs.getInt("suministro_id"), alimento);
                transacciones.add(new Transacciones(
                        rs.getInt("transaccion_id"),
                        suministro,
                        rs.getDate("fechaAccion"),
                        rs.getInt("transaccion_cantidad"),
                        rs.getString("accion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    public boolean insertTransaccion(Transacciones transaccion) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("INSERT INTO HistorialTransacciones (suministro_id, fechaAccion, cantidad, accion) VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, transaccion.getSuministro().getId());
            stmt.setDate(2, new java.sql.Date(transaccion.getFechaAccion().getTime()));
            stmt.setInt(3, transaccion.getCantidad());
            stmt.setString(4, transaccion.getAccion());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTransaccion(Transacciones transaccion) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("UPDATE HistorialTransacciones SET suministro_id = ?, fechaAccion = ?, cantidad = ?, accion = ? WHERE id = ?")) {
            stmt.setInt(1, transaccion.getSuministro().getId());
            stmt.setDate(2, new java.sql.Date(transaccion.getFechaAccion().getTime()));
            stmt.setInt(3, transaccion.getCantidad());
            stmt.setString(4, transaccion.getAccion());
            stmt.setInt(5, transaccion.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTransaccion(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("DELETE FROM HistorialTransacciones WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Transacciones> getAllTransaccionesBySuministro(String keyword) {
    List<Transacciones> transacciones = new ArrayList<>();
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(
            "SELECT ht.id AS transaccion_id, ht.fechaAccion, ht.cantidad AS transaccion_cantidad, ht.accion, " +
            "s.id AS suministro_id, a.id AS alimento_id, a.nombre AS alimento_nombre " +
            "FROM HistorialTransacciones ht " +
            "LEFT JOIN Suministro s ON ht.suministro_id = s.id " +
            "LEFT JOIN Alimento a ON s.alimento_id = a.id " +
            "WHERE a.nombre LIKE ?")) {
        stmt.setString(1, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Alimento alimento = new Alimento(rs.getInt("alimento_id"), rs.getString("alimento_nombre"), null); // La categoría se puede obtener si es necesario
            Suministro suministro = new Suministro(rs.getInt("suministro_id"), alimento);
            transacciones.add(new Transacciones(
                rs.getInt("transaccion_id"),
                suministro,
                rs.getDate("fechaAccion"),
                rs.getInt("transaccion_cantidad"),
                rs.getString("accion")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return transacciones;
}

     
}
