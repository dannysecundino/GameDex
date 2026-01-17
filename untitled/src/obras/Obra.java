package obras;
import avaliacao.Avaliacao;
import excecoes.NotaInvalidaException;
import interfaces.Exibivel;

import java.util.ArrayList;
public abstract class Obra implements Exibivel {
    private int id;
    private String titulo;
    private int ano;
    private String desenvolvedor;
    private double somaNotas;
    private int numAvaliacoes;
    private ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
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
    public void adicionarAvaliacao(Avaliacao novaAvaliacao)throws NotaInvalidaException {
        if (novaAvaliacao.getNota() < 0 || novaAvaliacao.getNota() > 10) {
            throw new NotaInvalidaException("A nota deve estar entre 0 e 10.");
        }
        this.avaliacoes.add(novaAvaliacao);
        this.numAvaliacoes++;
        this.somaNotas += novaAvaliacao.getNota();

    }

    public void removerAvaliacao(Avaliacao av) {
        if (this.avaliacoes.remove(av)) {
            this.somaNotas -= av.getNota();
            this.numAvaliacoes--;
        }
    }
    public ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public abstract String exibirDetalhes();



}
