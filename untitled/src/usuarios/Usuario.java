package usuarios;

import avaliacao.Avaliacao;
import interfaces.Autenticavel;
import interfaces.Exibivel;
import obras.Obra;

import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario implements Autenticavel, Exibivel {
    protected String nome;
    protected String email;
    protected String login;
    protected String senha;
    protected LocalDate dataEntradaPlataforma;
    protected int totalJogosFinalizados;
    protected ArrayList<Avaliacao> minhasAvaliacoes;
    protected ArrayList<Obra> listaDesejos;
    public Usuario(String nome, String email, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataEntradaPlataforma = LocalDate.now();
        this.totalJogosFinalizados = 0;
        this.minhasAvaliacoes = new ArrayList<>();
        this.listaDesejos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {  this.nome = nome;}

    public void setEmail(String email) {  this.email = email;}

    public LocalDate getDataEntradaPlataforma() {
        return dataEntradaPlataforma;
    }

    public int getTotalJogosFinalizados() {
        return totalJogosFinalizados;
    }

    public ArrayList<Avaliacao> getMinhasAvaliacoes() {
        return minhasAvaliacoes;
    }
    @Override
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }
    @Override
    public String exibirDetalhes() {
        return "PERFIL: " + this.nome + " | Email: " + this.email + " | Login: " + this.login;
    }
}
