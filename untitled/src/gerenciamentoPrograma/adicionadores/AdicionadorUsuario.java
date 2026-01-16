package gerenciamentoPrograma.adicionadores;

import gerenciamentoPrograma.bancoDados.BancoDados;
import gerenciamentoPrograma.excecoes.CadastroInvalidoException;
import usuarios.Usuario;

public class AdicionadorUsuario {

    public static void adicionarUsuario(Usuario novoUsuario) throws CadastroInvalidoException {
        BancoDados bd = BancoDados.getInstancia();

        for (Usuario u : bd.getUsuarios()){
            if(u.getLogin().equals(novoUsuario.getLogin())){
                throw new CadastroInvalidoException("Este nome de usuário já está em uso.");
            }
            if(u.getEmail().equals(novoUsuario.getEmail())){
                throw new CadastroInvalidoException("Este e-mail já foi cadastrado.");
            }
        }


        bd.getUsuarios().add(novoUsuario);
    }
}
