package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    /*
    private static final String URL = "jdbc:mysql://localhost:3306/DbZoologico";
    private static final String USER = "tuUsuario";
    private static final String PASSWORD = "tuContraseña";*/
    
    private static final String URL = "jdbc:mysql://db-projects.cn62022agaif.us-east-2.rds.amazonaws.com:3306/DbZoologico";
    private static final String USER = "root";
    private static final String PASSWORD = "bjNCKeZEamuJIGdNAmPrjgwvaYkTkriA";

    static {
        try {
            // Asegúrate de que el driver JDBC está disponible
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            // Retorna una conexión usando el driver JDBC para MySQL
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
