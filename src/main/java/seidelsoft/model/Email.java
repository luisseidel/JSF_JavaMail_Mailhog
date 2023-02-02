package seidelsoft.model;

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
import seidelsoft.util.EmailConfigs;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Email {

    private String mensagem;
    private String assunto;
    private String nomeRemetente;
    private String destinatarios;
    private List<FileInputStream> listAnexos;

    public Email() {}

    public Email(String destinatarios, String assunto, String mensagem) {
        this.destinatarios = destinatarios;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.listAnexos = new ArrayList<>();
    }

}
