package Dao;

import Model.Suministro;
import Model.Alimento;
import Model.Proveedor;
import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuministroDAO {

    private AlimentoDAO alimentoDAO = new AlimentoDAO();
    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    public Suministro getSuministroById(int id) {
        Suministro suministro = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Suministro WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Alimento alimento = alimentoDAO.getAlimentoById(rs.getInt("alimento_id"));
                Proveedor proveedor = proveedorDAO.getProveedorById(rs.getInt("proveedor_id"));
                suministro = new Suministro(
                    rs.getInt("id"),
                    alimento,
                    rs.getInt("stock"),
                    rs.getDate("fechaVencimiento"),
                    rs.getDate("fechaIngreso"),
                    rs.getInt("cantidad"),
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
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Suministro")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Alimento alimento = alimentoDAO.getAlimentoById(rs.getInt("alimento_id"));
                Proveedor proveedor = proveedorDAO.getProveedorById(rs.getInt("proveedor_id"));
                suministros.add(new Suministro(
                    rs.getInt("id"),
                    alimento,
                    rs.getInt("stock"),
                    rs.getDate("fechaVencimiento"),
                    rs.getDate("fechaIngreso"),
                    rs.getInt("cantidad"),
                    proveedor
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suministros;
    }

    public boolean insertSuministro(Suministro suministro) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Suministro (alimento_id, stock, fechaVencimiento, fechaIngreso, cantidad, proveedor_id) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setInt(1, suministro.getAlimento().getId());
            stmt.setInt(2, suministro.getStock());
            stmt.setDate(3, new java.sql.Date(suministro.getFechaVencimiento().getTime()));
            stmt.setDate(4, new java.sql.Date(suministro.getFechaIngreso().getTime()));
            stmt.setInt(5, suministro.getCantidad());
            stmt.setInt(6, suministro.getProveedor().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSuministro(Suministro suministro) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE Suministro SET alimento_id = ?, stock = ?, fechaVencimiento = ?, fechaIngreso = ?, cantidad = ?, proveedor_id = ? WHERE id = ?")) {
            stmt.setInt(1, suministro.getAlimento().getId());
            stmt.setInt(2, suministro.getStock());
            stmt.setDate(3, new java.sql.Date(suministro.getFechaVencimiento().getTime()));
            stmt.setDate(4, new java.sql.Date(suministro.getFechaIngreso().getTime()));
            stmt.setInt(5, suministro.getCantidad());
            stmt.setInt(6, suministro.getProveedor().getId());
            stmt.setInt(7, suministro.getId());
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
}
