package seidelsoft;

import lombok.Getter;
import lombok.Setter;
import seidelsoft.model.Email;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SessionScoped
@ManagedBean(name = "usuarioController")
public class UsuarioController {

    private String name;
    private String senha;
    private String texto;
    private List<UsuarioController> nomes = new ArrayList<>();

    public UsuarioController() {}

    public UsuarioController(String name, String senha, String texto) {
        this.name = name;
        this.senha = senha;
        this.texto = texto;
    }

    public String addNome() {
        nomes.add(new UsuarioController(name, senha, texto));
        return (nomes.size() >= 3 ? "segundapagina?faces-redirect=true" : "");
    }

    public void enviarEmail() {

    }

}
