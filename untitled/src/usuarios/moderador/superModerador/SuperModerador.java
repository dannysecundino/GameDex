//SuperModerador aceita ou não os pedidos de criação de contas de Moderadores, além de poder adicionar obras

package usuarios.moderador.superModerador;

import usuarios.moderador.Moderador;

public class SuperModerador extends Moderador {
    public SuperModerador(String nome, String email, String login, String senha) {
        super(nome, email, login, senha);
    }

}
