package Dao;

import Model.DatabaseConnection;
import Model.HistorialTransacciones;
import Model.Suministro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialTransaccionesDAO {

    private SuministroDAO suministroDAO = new SuministroDAO();

    public HistorialTransacciones getTransaccionById(int id) {
        HistorialTransacciones transaccion = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM HistorialTransacciones WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Suministro suministro = suministroDAO.getSuministroById(rs.getInt("suministro_id"));
                transaccion = new HistorialTransacciones(
                    rs.getInt("id"),
                    suministro,
                    rs.getDate("fechaAccion"),
                    rs.getInt("cantidad"),
                    rs.getString("accion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaccion;
    }

    public List<HistorialTransacciones> getAllTransacciones() {
        List<HistorialTransacciones> transacciones = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM HistorialTransacciones")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Suministro suministro = suministroDAO.getSuministroById(rs.getInt("suministro_id"));
                transacciones.add(new HistorialTransacciones(
                    rs.getInt("id"),
                    suministro,
                    rs.getDate("fechaAccion"),
                    rs.getInt("cantidad"),
                    rs.getString("accion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    public boolean insertTransaccion(HistorialTransacciones transaccion) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO HistorialTransacciones (suministro_id, fechaAccion, cantidad, accion) VALUES (?, ?, ?, ?)")) {
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

    public boolean updateTransaccion(HistorialTransacciones transaccion) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE HistorialTransacciones SET suministro_id = ?, fechaAccion = ?, cantidad = ?, accion = ? WHERE id = ?")) {
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
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM HistorialTransacciones WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
