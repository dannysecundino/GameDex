package excecoes;

public class PromocaoOuRebaixamentoInvalido extends RuntimeException {
    public PromocaoOuRebaixamentoInvalido(String mensagem) {
        super(mensagem);
    }
}
