package seidelsoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "mensagem")
    private String mensagem;
    @Column(name = "assunto")
    private String assunto;
    @Column(name = "email_remetente")
    private String emailRemetente;
    private String nomeRemetente;
    @Column(name = "destinatarios")
    private String destinatarios;

    @Column(name = "data_envio")
    @Temporal(TemporalType.TIME)
    private Calendar dataEnvio;


    public Email() {}

    public Email(Long id) {
        this.id = id;
    }

    public Email(String mensagem, String assunto, String emailRemetente, String destinatarios, Calendar dataEnvio) {
        this.mensagem = mensagem;
        this.assunto = assunto;
        this.emailRemetente = emailRemetente;
        this.destinatarios = destinatarios;
        this.dataEnvio = dataEnvio;
    }
}
