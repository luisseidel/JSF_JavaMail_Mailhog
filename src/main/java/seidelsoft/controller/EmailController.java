package seidelsoft.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.activation.DataHandler;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.Getter;
import lombok.Setter;
import seidelsoft.dto.EmailLayout;
import seidelsoft.model.Email;
import seidelsoft.service.EmailService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequestScoped
@ManagedBean(name = "emailController")
public class EmailController implements Serializable {

    private EmailService emailService;

    public EmailController() {
        this.emailService = new EmailService();
    }

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    public String enviarEmail() {
        emailService.enviar(montarEmail());

        return null;
    }

    private Email montarEmail() {
        EmailLayout layout = new EmailLayout();
        return layout.montarEmail(
                "luis.seidel@celk.net",
                "Teste de email",
                "Esse é um texto genérico para o email de testes que estou enviando.",
                "Luis Guilherme",
                "Desenvolvedor",
                "Email automático, favor não responder!"
        );
    }
}
