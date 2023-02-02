package seidelsoft.dto;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.activation.DataHandler;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.util.ByteArrayDataSource;
import seidelsoft.model.Email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmailLayout {

    private static final String QUEBRA_LINHA = "<br>";
    private static final String BARRA_HORIZONTAL = "<hr/>";
    private static final String QUEBRA_LINHA_DUPLA = "<br><br>";

    public Email montarEmail(String destinatario, String assunto, String texto, String nomeAssinatura, String cargo, String textoRodape) {
        StringBuilder corpo = new StringBuilder();
        corpo.append(texto);
        gerarAssinatura(corpo, nomeAssinatura, cargo);
        gerarRodape(corpo, textoRodape);

        return new Email(destinatario, assunto,corpo.toString());
    }

    private void criarBarraHorizontal(StringBuilder texto) {
        texto
            .append(QUEBRA_LINHA)
            .append(BARRA_HORIZONTAL)
            .append(QUEBRA_LINHA);
    }

    private void gerarAssinatura(StringBuilder texto, String nome, String cargo) {
        texto.append(QUEBRA_LINHA_DUPLA)
            .append(BARRA_HORIZONTAL)
            .append(nome)
            .append(QUEBRA_LINHA)
            .append(cargo);
    }

    public void gerarRodape(StringBuilder texto, String textoRodape) {
        texto
            .append(QUEBRA_LINHA_DUPLA)
            .append(textoRodape);
    }

}
