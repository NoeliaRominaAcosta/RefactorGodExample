import Auth.AuthenticationService;
import Auth.AuthenticationServiceImpl;
import Database.DatabaseConnection;
import Interfaces.DbConnection;
import Repository.DatabaseRepository;
import Repository.DatabaseRepositoryImpl;
import Service.Messaging.EmailService;
import Service.Messaging.EmailServiceImpl;
import Service.ProcessFiles.ProcessFileService;
import Logging.LogService;
import Logging.LogServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. FASE DE CONFIGURACIÓN (Inyección de Dependencias manual)
        LogService logger = new LogServiceImpl();
        AuthenticationService authService = new AuthenticationServiceImpl();
        EmailService emailService = new EmailServiceImpl();
        ProcessFileService fileService = new ProcessFileService();

        DbConnection dbConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/mydatabase"); // constructor
        DatabaseRepository dbRepository = new DatabaseRepositoryImpl(dbConnection);

        logger.logInfo("Aplicación iniciada. Todos los servicios están configurados.");
        System.out.println("----------------------------------------------------");


        // 2.EJECUCIÓN

        logger.logInfo("Intentando autenticar al usuario 'admin'...");
        boolean isAuthenticated = authService.login("admin", "password");

        if (isAuthenticated) {
            logger.logInfo("Autenticación exitosa.");

            List<String> userData = dbRepository.getUserData("user123");
            logger.logInfo("Datos de usuario recuperados: " + userData);

            try {
                String txtResult = fileService.processFile("documento.txt");
                logger.logInfo("Archivo TXT procesado: " + txtResult);

                String xmlResult = fileService.processFile("datos.xml");
                logger.logInfo("Archivo XML procesado: " + xmlResult);
                
                String docResult = fileService.processFile("informe.doc");
                logger.logInfo("Archivo DOC procesado: " + docResult);

         //       logger.logInfo("Intentando procesar un tipo de archivo no soportado...");
        //        fileService.processFile("archivo.csv");

            } catch (IllegalArgumentException e) {
                logger.logError("Error esperado al procesar archivo: " + e.getMessage());
            }

            emailService.sendEmail("user@example.com", "Proceso completado", "Los archivos han sido procesados.");
            logger.logInfo("Correo de notificación enviado.");

            authService.logout("admin");

        } else {
            logger.logError("Fallo en la autenticación para el usuario 'admin'.");
        }

        System.out.println("----------------------------------------------------");
        logger.logInfo("Aplicación finalizada.");
    }
}