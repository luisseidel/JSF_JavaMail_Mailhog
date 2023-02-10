package seidelsoft.util;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Setter
public class EmailConfigs {

    private Properties properties;

    public Message getMimeMessage() {
        Properties props = this.getProperties();
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getUser(), getSenha());
            }
        };

        System.out.println("Props is null: " + (props == null));
        System.out.println("auth is null: " + (auth == null));

        Session session = Session.getInstance(props, auth);
        Message message = new MimeMessage(session);

        return message;
    }

    public Properties getProperties() {
        if (properties == null) {
            try {
                InputStream input = new FileInputStream("src/main/resources/email.properties");
                properties = new Properties();
                properties.load(input);
            } catch (FileNotFoundException fx) {
                System.out.println("Não encontrei o arquivo!");
                fx.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return properties;
    }

    public String getHost() {
        return getProperties().getProperty("mail.smtp.host");
    }
    public String smtpPort() {
        return getProperties().getProperty("mail.smtp.port");
    }
    public String getUser() {
        return getProperties().getProperty("email.user");
    }
    public String getSenha() {
        return getProperties().getProperty("email.senha");
    }
    public String smtpStartTls() {
        return getProperties().getProperty("mail.smtp.starttls.enable");
    }

}
