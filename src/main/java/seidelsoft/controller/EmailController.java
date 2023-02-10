package seidelsoft.controller;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import seidelsoft.dto.EmailLayout;
import seidelsoft.model.Email;
import seidelsoft.service.EmailService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@Getter
@Setter
@RequestScoped
@ManagedBean(name = "emailController")
public class EmailController implements Serializable {

    private EmailService emailService;

    private String emailRemetente;
    private String nomeRemetente;
    private String cargoRemetente;
    private String destinatario;
    private String assunto;
    private String mensagem;
    private String textoRodape;

    public EmailController() {
        this.emailService = new EmailService();
    }

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    public String enviarEmail() {
        validarEmail();
        emailService.enviar(montarEmail());

        return null;
    }

    private void validarEmail() {
        if (StringUtils.isEmpty(emailRemetente)) {
            return;
        } else if (StringUtils.isEmpty(nomeRemetente)) {
            return;
        } else if (StringUtils.isEmpty(cargoRemetente)) {
            return;
        } else if (StringUtils.isEmpty(destinatario)) {
            return;
        } else if (StringUtils.isEmpty(assunto)) {
            return;
        } else if (StringUtils.isEmpty(mensagem)) {
            return;
        } else if (StringUtils.isEmpty(textoRodape)) {
            return;
        }
    }

    private Email montarEmail() {
        EmailLayout layout = new EmailLayout();
        return layout.montarEmail(
                destinatario,
                assunto,
                mensagem,
                emailRemetente,
                nomeRemetente,
                cargoRemetente,
                textoRodape
        );
    }
}
