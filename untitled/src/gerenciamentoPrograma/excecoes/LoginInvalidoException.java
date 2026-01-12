package gerenciamentoPrograma.excecoes;

public class LoginInvalidoException extends Exception {
    public LoginInvalidoException(String mensagem) {
        super(mensagem);
    }
}
