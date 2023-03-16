package seidelsoft.dto;

import seidelsoft.model.Email;

import java.util.Calendar;

public class EmailLayout {

    private static final String QUEBRA_LINHA = "<br>";
    private static final String BARRA_HORIZONTAL = "<hr/>";
    private static final String QUEBRA_LINHA_DUPLA = "<br><br>";

    public Email montarEmail(
        String mensagem,
        String assunto,
        String emailRemetente,
        String destinatario
    ) {
        StringBuilder corpo = new StringBuilder();
        corpo.append(mensagem);

        return new Email(corpo.toString(), assunto, emailRemetente, destinatario, Calendar.getInstance());
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
