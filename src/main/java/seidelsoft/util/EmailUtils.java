package seidelsoft.util;

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

public class EmailUtils {

    public static void addAnexosEmail(Email email, Multipart multipart) throws IOException, MessagingException, DocumentException {
        int index = 0;
        email.setListAnexos(getListPdfExemplo());

        for (FileInputStream fis : email.getListAnexos()) {
            MimeBodyPart anexoEmail = new MimeBodyPart();
            anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fis, "application/pdf")));
            anexoEmail.setFileName("anexo"+index+".pdf");
            multipart.addBodyPart(anexoEmail);
            index++;
        }
    }

    public static FileInputStream getPdfExemplo() throws IOException, DocumentException {
        Document document = new Document();
        File file = new File("anexo.pdf");
        file.createNewFile();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(new Paragraph("TEste do texto em pdf"));
        document.close();

        return new FileInputStream(file);
    }

    public static List<FileInputStream> getListPdfExemplo() throws DocumentException, IOException {
        List<FileInputStream> listAnexos = new ArrayList<>();
        listAnexos.add(getPdfExemplo());
        listAnexos.add(getPdfExemplo());
        listAnexos.add(getPdfExemplo());
        listAnexos.add(getPdfExemplo());

        return listAnexos;
    }
}
