package Auth;

public class AuthenticationServiceImpl implements AuthenticationService {


    @Override
    public boolean login(String username, String password) {
        System.out.println("Lógica de autenticación para el usuario: " + username);
        return "admin".equals(username) && "password".equals(password);
    }

    @Override
    public void logout(String username) {
        System.out.println("Cerrando sesión para el usuario: " + username);
    }
}
