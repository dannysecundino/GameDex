package obras.expansao;

import obras.Obra;
import obras.jogos.Jogo;

public class Expansao extends Obra {
    private Jogo jogoBase;
    public Jogo getJogoBase() {
        return this.jogoBase;
    }
    public Expansao(int id, String titulo, String desenvolvedor, int ano, Jogo jogoBase) {
        super(id, titulo,ano,desenvolvedor);
        this.jogoBase = jogoBase;
    }
    @Override
    public String exibirDetalhes() {
        return String.format("EXPANSÃO: %s (%d)\nJogo Base: %s\nDesenvolvedora: %s\nMédia: %.1f",
                getTitulo(), getAno(), jogoBase.getTitulo(), getDesenvolvedor(), getMedia());
    }
}
