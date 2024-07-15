
package Security;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    
    // Metodo para hashear un password
    public static String hashPassword (String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }
    
    // Metodo para verificar el password
    public static boolean verifyPassword (String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
