package Service.Messaging;

public class EmailServiceImpl implements EmailService{
    @Override
    public void sendEmail(String recipient, String subject, String body) {
        System.out.println("Enviando email a: " + recipient);
        System.out.println("Sujeto : " + subject);
        System.out.println("Cuerpo: " + body);
    }
}
