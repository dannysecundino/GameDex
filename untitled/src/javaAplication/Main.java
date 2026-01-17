package javaAplication;

import gerenciamentoPrograma.bancoDados.BancoDados;
import interfaceGrafica.TelaLogin;
import obras.expansao.Expansao;
import obras.jogos.Jogo;
import usuarios.Usuario;
import usuarios.moderador.Moderador;
import usuarios.moderador.superModerador.SuperModerador;

public class Main {


    public static void main(String[] args){

        BancoDados bd = BancoDados.getInstancia();
        Usuario player = new Usuario("jogador","jogador@gamedex.com","gamer","234");
        Moderador mod = new Moderador ("Joao", "mod@gmail.com", "j", "111");
        SuperModerador admin = new SuperModerador("admin", "adm@gamedex.com", "super", "123");
        Jogo metalgearrisingrevengeance = new Jogo(1, "Metal Gear Rising: Revengeance", "PlatinumGames", 2013, "Hack and Slash", "PC/PS3/XBOX");
        Jogo doometernal = new Jogo(2, "Doom Eternal", "id Software", 2020, "Arena Shooter", "PC/PS4/PS5/XONE/XSeries/Switch");
        Expansao theancientgods1 = new Expansao(3, "The Ancient Gods - Part One", "id Software", 2020, doometernal);


        bd.getObras().add(metalgearrisingrevengeance);
        bd.getObras().add(doometernal);
        bd.getObras().add(theancientgods1);
        bd.getUsuarios().add(admin);
        bd.getUsuarios().add(mod);
        bd.getUsuarios().add(player);
        new TelaLogin();
    }
}
