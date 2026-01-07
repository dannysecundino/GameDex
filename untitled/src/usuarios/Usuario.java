package usuarios;

import avaliacao.Avaliacao;
import obras.Obra;

import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {
    protected String nome;
    protected String email;
    protected String login;
    protected String senha;
    protected LocalDate dataEntradaPlataforma;
    protected int totalJogosFinalizados;
    protected ArrayList<Avaliacao> minhasAvaliacoes;
    protected ArrayList<Obra> listaDesejos;




}
