public class Main {
    public static void main(String[] args) {
        God god = new God();

        // 1. Login
        god.login("admin", "password");

        // 2. Procesar un archivo
        god.processFile("txt", "C:\\Users\\romina_acosta\\Documents\\ejemplo refactor java\\file.txt");

        // 3. Obtener datos de usuario
        god.getUserData("123");

        // 4. Enviar correo
        god.sendEmail("test@example.com", "Hola", "Este es un correo de prueba.");

        // 5. Logout
        god.logout("admin");
    }
}

