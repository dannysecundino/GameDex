package javaAplication;

import gerenciamentoPrograma.bancoDados.BancoDados;
import gerenciamentoPrograma.interfaces.TelaCadastro;
import gerenciamentoPrograma.interfaces.TelaLogin;
import usuarios.Usuario;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args){

        BancoDados bd = BancoDados.getInstancia();

        Usuario admin = new Usuario("admin", "adm@gamedex.com", "super", "123");

        bd.getUsuarios().add(admin);

        new TelaLogin();
    }
}
