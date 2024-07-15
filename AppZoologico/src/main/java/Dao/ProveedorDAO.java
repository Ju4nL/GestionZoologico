package Dao;

import Model.DatabaseConnection;
import Model.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public List<Proveedor> getAllProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Proveedor")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(rs.getInt("id"), rs.getString("nombre"), rs.getString("contacto"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    public Proveedor getProveedorById(int id) {
        Proveedor proveedor = null;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Proveedor WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                proveedor = new Proveedor(rs.getInt("id"), rs.getString("nombre"), rs.getString("contacto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedor;
    }

    public boolean insertProveedor(Proveedor proveedor) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("INSERT INTO Proveedor (nombre, contacto) VALUES (?, ?)")) {
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getContacto());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProveedor(Proveedor proveedor) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("UPDATE Proveedor SET nombre = ?, contacto = ? WHERE id = ?")) {
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getContacto());
            stmt.setInt(3, proveedor.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProveedor(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement("DELETE FROM Proveedor WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Proveedor> getProveedorByName(String name) {
    List<Proveedor> proveedores = new ArrayList<>();
    try (Connection connection = DatabaseConnection.getConnection(); 
         PreparedStatement stmt = connection.prepareStatement(
            "SELECT * FROM Proveedor WHERE nombre LIKE ?")) {
        stmt.setString(1, "%" + name + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Proveedor proveedor = new Proveedor(rs.getInt("id"), rs.getString("nombre"), rs.getString("contacto"));
            proveedores.add(proveedor);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return proveedores;
}

}
