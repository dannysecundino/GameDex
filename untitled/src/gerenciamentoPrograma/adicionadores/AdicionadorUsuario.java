package gerenciamentoPrograma.adicionadores;

import gerenciamentoPrograma.bancoDados.BancoDados;
import obras.Obra;
import usuarios.Usuario;
import usuarios.moderador.Moderador;

public class AdicionadorUsuario {

    public void adicionarUsuario(Usuario novoUsuario) {
        BancoDados.getInstancia().getUsuarios().add(novoUsuario);
    }
}
