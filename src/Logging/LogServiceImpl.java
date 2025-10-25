package Logging;

public class LogServiceImpl implements LogService {
    @Override
    public void logError(String message) {
        System.err.println("ERROR: " + message);
    }

    // 8. Log de información
    @Override
    public void logInfo(String message) {
        System.out.println("INFO: " + message);
    }
}
