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
import java.util.List;

@Getter
@Setter
@RequestScoped
@ManagedBean(name = "emailController")
public class EmailController implements Serializable {

    private List<Email> listEmails;
    private EmailService emailService;
    private String emailRemetente;
    private String destinatario;
    private String assunto;
    private String mensagem;

    public EmailController() {
        this.emailService = new EmailService();
    }

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    public String enviarEmail() throws Exception {
        validarEmail();
        Email email = montarEmail();
        listEmails.add(email);
        emailService.enviar(email);
        limparCampos();
        return null;
    }

    private void validarEmail() throws Exception {

        /*TODO
        *  1. Criar minhas exções personalizadas
        *  2. Lançar exeções na tela
        *  3. Realizar validações
        *  4. Limpar campos
        *
        **/

        if (StringUtils.isEmpty(emailRemetente)) {
            throw new Exception("Não deixe o remetente em branco!");
        } else if (StringUtils.isEmpty(destinatario)) {
            return;
        } else if (StringUtils.isEmpty(assunto)) {
            return;
        } else if (StringUtils.isEmpty(mensagem)) {
            return;
        }
    }

    private void limparCampos() {
        setEmailRemetente(null);
        setDestinatario(null);
        setAssunto(null);
        setMensagem(null);
    }

    private Email montarEmail() {
        EmailLayout layout = new EmailLayout();
        return layout.montarEmail(
                destinatario,
                assunto,
                mensagem,
                emailRemetente
        );
    }
}
