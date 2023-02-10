package seidelsoft.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.Getter;
import lombok.Setter;
import seidelsoft.model.Email;

import javax.faces.bean.NoneScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
@Setter
@NoneScoped
public class EmailService extends Thread {

    public static final String HEADER_CONTEXT = "text/html; charset=utf-8";

    private List<Email> listEmails;

    public void enviar(Email email) {
        listEmails = new ArrayList<>();
        listEmails.add(email);
        send();
    }

    public void enviar(List<Email> emails) {
        listEmails = emails;
        send();
    }

    private EmailService copy() {
        EmailService es = new EmailService();
        es.listEmails = listEmails;
        return es;
    }

    private void send() {
        new Thread(this.copy()).start();
    }

    @Override
    public void run() {
        Properties props = new Properties();
        props.put("mail.smtp.tarttls.enable", "true");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "1025");

        Session s = Session.getInstance(props);
        s.setDebug(false);

        for (Email email : listEmails) {
            try {
                Message message = new MimeMessage(s);
                message.setFrom(new InternetAddress(email.getEmailRemetente(), email.getNomeRemetente()));

                if (email.getDestinatarios().contains("/")) {
                    List<InternetAddress> listDestinatarios = new ArrayList<>();
                    for (String destinatario : email.getDestinatarios().split("/")) {
                        listDestinatarios.add(new InternetAddress(destinatario));
                    }

                    message.addRecipients(Message.RecipientType.TO, listDestinatarios.toArray(new InternetAddress[0]));
                } else {
                    InternetAddress para = new InternetAddress(email.getDestinatarios());
                    message.addRecipient(Message.RecipientType.TO, para);
                }

                message.setSubject(email.getAssunto());
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setHeader("Content-Type", HEADER_CONTEXT);
                textPart.setContent(email.getMensagem(), HEADER_CONTEXT);

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(textPart);

                //EmailUtils.addAnexosEmail(email, multipart);

                message.setContent(multipart);

                Transport.send(message);

            } catch (MessagingException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
