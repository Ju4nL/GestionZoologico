package Controller;

import View.LoginFrame;
import View.PrincipalFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.DatabaseConnection;

public class LoginController {

    private LoginFrame loginFrame;
    private PrincipalController principalController;

    public LoginController(LoginFrame loginFrame, PrincipalFrame principalFrame) {
        this.loginFrame = loginFrame;
        this.principalController = new PrincipalController(principalFrame);
        initController();
    }

    private void initController() {
        loginFrame.getBtnIniciarSesion().addActionListener(e -> autenticar());
    }

    private void autenticar() {
        String username = loginFrame.getTxtUsername().getText();
        String password = new String(loginFrame.getPswPassword().getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            loginFrame.displayErrorMessage("Debe ingresar nombre de usuario y contraseña");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM Usuario WHERE nombreUsuario = ? AND contrasena = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                loginFrame.displaySucessMessage("Inicio de sesión exitoso");
                loginFrame.setVisible(false);
                principalController.showPrincipalFrame();
            } else {
                loginFrame.displayErrorMessage("Nombre de usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            loginFrame.displayErrorMessage("Error de conexión a la base de datos: " + e.getMessage());
        }
    }
}
