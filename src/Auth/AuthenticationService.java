package Auth;

public interface AuthenticationService {
    boolean login(String username, String password);
    void logout(String username);
}
