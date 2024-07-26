
package BusinessLogic;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class VerificacionCodigoCorreo {
     public static void enviarMensajeDeVerificacionDeRegistro(String correo, String codigoVerificacion, String usuario) {
        // Configuración de las propiedades del servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Reemplaza con tu servidor SMTP
        props.put("mail.smtp.port", "587"); // Reemplaza con el puerto adecuado

        // Credenciales del remitente
        final String username = "poli.eats.2024@gmail.com";
        final String password = "kwyirsrinztdilyd";
        // Crear una sesión de correo
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear un mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("poli.eats.2024@gmail.com")); // Remitente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo)); // Destinatario
            message.setSubject("Verificación de Cuenta - Poli-Eats");
            message.setText("Saludos cordiales " + usuario + ",\n\n El codigo de verificacion para su cuenta es: \"" + codigoVerificacion +"\". \n\n\n\nAtentamente: \n\nPoli-Eats.");

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado con éxito");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
