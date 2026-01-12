package obras;
import avaliacao.Avaliacao;
import java.util.ArrayList;
public abstract class Obra {
    private int id;
    private String titulo;
    private int ano;
    private String desenvolvedor;
    private double somaNotas;
    private int numAvaliacoes;
    private ArrayList<Avaliacao> avaliacoes;
    public Obra(int id, String titulo, int ano, String desenvolvedor) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.desenvolvedor = desenvolvedor;
        this.somaNotas = 0;
        this.numAvaliacoes = 0;
    }

    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getAno() {
        return ano;
    }
    public String getDesenvolvedor() {
        return desenvolvedor;
    }
    public double getSomaNotas() {
        return somaNotas;
    }
    public int getNumAvaliacoes() {
        return numAvaliacoes;
    }
    public double getMedia(){
        if(this.numAvaliacoes == 0){
            return 0.0;
        }
        return this.somaNotas/this.numAvaliacoes;
    }
    public void adicionarAvaliacao(Avaliacao novaAvaliacao){
        this.avaliacoes.add(novaAvaliacao);
        this.numAvaliacoes++;
        this.somaNotas =+ novaAvaliacao.getNota();

    }
    public ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

}
