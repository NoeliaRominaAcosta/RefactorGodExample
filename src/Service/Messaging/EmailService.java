package Service.Messaging;

public interface EmailService {
    void sendEmail(String recipient, String subject, String body);
}
