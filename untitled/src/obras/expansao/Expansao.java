package obras.expansao;

import obras.Obra;

public class Expansao extends Obra {
    private Obra jogoBase;
    public Expansao(int id, String titulo, String desenvolvedor, int ano, Obra jogoBase) {
        super(id, titulo,ano,desenvolvedor);
        this.jogoBase = jogoBase;
    }
    public String exibirDLC() {
        return String.format("EXPANSÃO: %s (%d)\nJogo Base: %s\nDesenvolvedora: %s\nMédia: %.1f",
                getTitulo(), getAno(), jogoBase.getTitulo(), getDesenvolvedor(), getMedia());
    }
}
