package javaAplication;

import gerenciamentoPrograma.bancoDados.BancoDados;
import interfaceGrafica.telasDadosUsuarios.TelaLogin;
import obras.expansao.Expansao;
import obras.jogo.Jogo;
import usuarios.Usuario;
import usuarios.moderador.Moderador;
import usuarios.moderador.superModerador.SuperModerador;

public class Main {


    public static void main(String[] args){

        BancoDados bd = BancoDados.getInstancia();
        Usuario player = new Usuario("jogador","jogador@gamedex.com","gamer","234");
        Moderador mod = new Moderador ("Joao", "mod@gmail.com", "j", "111");
        SuperModerador admin = new SuperModerador("admin", "adm@gamedex.com", "super", "123");
        Jogo metalgearrisingrevengeance = new Jogo(19, "Metal Gear Rising: Revengeance", "PlatinumGames", 2013, "Hack and Slash", "PC/PS3/XBOX");
        Jogo doometernal = new Jogo(20, "Doom Eternal", "id Software", 2020, "Arena Shooter", "PC/PS4/PS5/XONE/XSeries/Switch");
        Expansao theancientgods1 = new Expansao(21, "The Ancient Gods - Part One", "id Software", 2020, doometernal);
        Jogo dishonored = new Jogo(1, "Dishonored", "Arkane Studios", 2012, "Stealth/Ação", "PC/PS3/Xbox 360");
        Jogo control = new Jogo(2, "Control", "Remedy Entertainment", 2019, "Ação-Aventura", "PC/PS4/Xbox One");
        Jogo alanwake = new Jogo(3, "Alan Wake", "Remedy Entertainment", 2010, "Survival Horror", "PC/Xbox 360");
        Jogo halo3 = new Jogo(4, "Halo 3", "Bungie", 2007, "FPS", "Xbox 360");
        Jogo katanazero = new Jogo(5, "Katana Zero", "Askiisoft", 2019, "Ação/Plataforma", "PC/Switch");
        Jogo dmc5 = new Jogo(6, "Devil May Cry 5", "Capcom", 2019, "Hack and Slash", "PC/PS4/Xbox One");
        Jogo re4remake = new Jogo(7, "Resident Evil 4 Remake", "Capcom", 2023, "Survival Horror", "PC/PS5/Xbox Series");
        Jogo roblox = new Jogo(8, "Roblox", "Roblox Corp", 2006, "Sandbox", "PC/Mobile/Xbox One");
        Jogo hotlinemiami = new Jogo(9, "Hotline Miami", "Dennaton Games", 2012, "Ação/Top-down", "PC/PS3");
        Jogo gta5 = new Jogo(10, "GTA 5", "Rockstar North", 2013, "Ação-Aventura", "PC/PS3/Xbox 360");
        Jogo watchdogs = new Jogo(11, "Watch Dogs", "Ubisoft Montreal", 2014, "Ação-Aventura", "PC/PS4/Xbox One");
        Jogo farcry3 = new Jogo(12, "Far Cry 3", "Ubisoft Montreal", 2012, "FPS", "PC/PS3/Xbox 360");
        Jogo yakuzalikeadragon = new Jogo(13, "Yakuza: Like a Dragon", "Ryu Ga Gotoku Studio", 2020, "RPG", "PC/PS4/Xbox One");
        Jogo prey2017 = new Jogo(14, "Prey", "Arkane Studios", 2017, "Ficção Científica/FPS", "PC/PS4/Xbox One");

        Expansao knifedunwall = new Expansao(15, "The Knife of Dunwall", "Arkane Studios", 2013, dishonored);
        Expansao haloodst = new Expansao(16, "Halo 3: ODST", "Bungie", 2009, halo3);
        Expansao farcryblooddragon = new Expansao(17, "Far Cry 3: Blood Dragon", "Ubisoft Montreal", 2013, farcry3);
        Expansao separateways = new Expansao(18, "Separate Ways", "Capcom", 2023, re4remake);

        bd.getObras().add(metalgearrisingrevengeance);
        bd.getObras().add(doometernal);
        bd.getObras().add(theancientgods1);
        bd.getObras().add(dishonored);
        bd.getObras().add(control);
        bd.getObras().add(alanwake);
        bd.getObras().add(halo3);
        bd.getObras().add(katanazero);
        bd.getObras().add(dmc5);
        bd.getObras().add(re4remake);
        bd.getObras().add(roblox);
        bd.getObras().add(hotlinemiami);
        bd.getObras().add(gta5);
        bd.getObras().add(watchdogs);
        bd.getObras().add(farcry3);
        bd.getObras().add(yakuzalikeadragon);
        bd.getObras().add(prey2017);

        bd.getObras().add(knifedunwall);
        bd.getObras().add(haloodst);
        bd.getObras().add(farcryblooddragon);
        bd.getObras().add(separateways);

        bd.getUsuarios().add(admin);
        bd.getUsuarios().add(mod);
        bd.getUsuarios().add(player);
        new TelaLogin();
    }
}
