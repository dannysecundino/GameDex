package gerenciamentoPrograma.gerenciaLogin;
import usuarios.Usuario;
import gerenciamentoPrograma.bancoDados.BancoDados;
import gerenciamentoPrograma.excecoes.LoginInvalidoException;
public class LoginAut {
    private static Usuario usuarioLogado;

    public static void realizarLogin(String login, String senha) throws LoginInvalidoException {
        BancoDados bd = BancoDados.getInstancia();

        for (Usuario u : bd.getUsuarios()) {
            if (u.autenticar(login, senha)) {
                usuarioLogado = u;
                System.out.println("Login bem-sucedido! Bem-vindo, " + u.getNome());
                return;
            }
        }

        throw new LoginInvalidoException("Utilizador ou senha incorretos.");
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void realizarLogout() {
        usuarioLogado = null;
    }
}
